package brightmoon.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MultiDBPoolManager {
	protected Log log = LogFactory.getLog(this.getClass().getName());
	/**
	 * 连接池管理对象
	 */
	private static MultiDBPoolManager dbManager = null;
	/**
	 * 配置文件
	 */
	private Properties pros;
	/**
	 * 数据库连接池
	 */
	private Map<String,DBPool> pools;
	/**
	 * 建立的数据库的驱动的集合（可以建立多个数据库连接池的驱动）
	 */
	private List drivers = new ArrayList();
	/**
	 * 表示正在客户端使用的连接数。
	 */
	private static int clientCount;
	
	public static void main(String[] a){ 
	}
	/**
	 * 返回连接池管理对象的实例
	 * @return
	 */
	public static MultiDBPoolManager getInstance() {
		if(dbManager==null){
			dbManager = new MultiDBPoolManager();
		}
		//创建了一次就计数累加一次。
		clientCount++;
		return dbManager;
	}

	public static void setDbManager(MultiDBPoolManager dbManager) {
		MultiDBPoolManager.dbManager = dbManager;
	}

	private MultiDBPoolManager() {
		//构造函数中进行初始化操作
		init();
	}

	private void init() {
		InputStream in = null;
		try {
			pros = new Properties();
			in = this.getClass().getResourceAsStream("/multiDb.properties");
			pros.load(in);
		} catch (Exception ex) {
			log.error("没有找到连接池配置文件", ex);
		}finally{
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) { 
					e.printStackTrace();
				}
		}
		pools = new HashMap<String,DBPool>(5);
		loadDriver();
		createPool();
	}

	private void loadDriver() {
		//得到数据库驱动的名称（如果有多个数据库的驱动名称，将建立多个数据库的连接池） 
		String driverClasses = pros.getProperty("driverName");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			try {
				Driver driver = (Driver) Class.forName(driverClassName)
						.newInstance();
				DriverManager.registerDriver(driver);
				drivers.add(driver);
			} catch (Exception ex) {
				log.error("创建数据库连接池出错", ex);
			}
		}
	}

	/**
	 * 创建数据库连接池.
	 */
	private void createPool() {
		String dataes =  pros.getProperty("dataes");
		if(!"".equals(dataes)){
			String[] dbs = dataes.split(",");
			for(int i=0;i<dbs.length;i++){
				String userName = pros.getProperty(dbs[i]+".username");
				String password = pros.getProperty(dbs[i]+".password");
				String url = pros.getProperty(dbs[i]+".url");
				int maxConn;
				int minConn;
				try {
					maxConn = Integer.valueOf(pros.getProperty(dbs[i]+".maxConnection", "0"))
							.intValue();
					minConn = Integer.valueOf(pros.getProperty(dbs[i]+".minConnection", "0"))
						.intValue();
				} catch (NumberFormatException ex) {
					maxConn = 0;
					minConn = 0;
				}
				pools.put(dbs[i], new DBPool(userName, password, url, maxConn, minConn)) ;
			}
		}
	}

	/**
	 * 得到创建的数据库连接的示例数目.
	 * @return
	 */
	public synchronized int getClientCount() {
		return clientCount;
	}

	/**
	 * 返回一个可用的数据库连接.
	 * @return
	 */
	public Connection getDBConn(String dbId) {
		if (pools != null) {
			return (pools.get(dbId).getDbConn());
		}
		return (null);
	}

	/**
	 * 根据超时时间返回一个可用的数据库连接.
	 * @param timeout
	 * @return
	 */
	public Connection getDbConn(String dbId,long timeout) {
		if (pools != null) {
			return (pools.get(dbId).getDbConn(timeout));
		}
		return (null);
	}

	/**
	 * 释放一个可用的数据库连接.
	 * @param conn
	 */
	public synchronized void freeDBConnection(String dbId,Connection conn) {
		if (pools != null) {
			pools.get(dbId).freeDBConnection(conn);
			clientCount--;
		}
	}

	/**
	 * 释放连接池的全部连接.
	 */
	public void release(String dbId) {
		//只要客户端有数据库连接处于运行状态，就不会强制关闭连接
		if (clientCount != 0) {
			return;
		}
		if (pools != null) {
			pools.get(dbId).release();
			Iterator iter = drivers.iterator();
			while (iter.hasNext()) {
				Driver driver = (Driver) iter.next();
				try {
					DriverManager.deregisterDriver(driver);
				} catch (Exception ex) {
					log.error("注销数据库驱动出错.", ex);
				}
			}
		}
	}
}
