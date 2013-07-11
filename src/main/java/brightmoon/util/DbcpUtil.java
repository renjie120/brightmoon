package brightmoon.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import brightmoon.jdbc.DataHandler;
import brightmoon.jdbc.DbTemplate;
import brightmoon.jdbc.DbUtil;

/**
 * @author space
 * @date Aug 12, 2008 3:25:49 PM
 * 
 *       dbcp 实用类，提供了dbcp连接，不允许继承；
 * 
 *       该类需要有个地方来初始化 DS ，通过调用initDS
 *       方法来完成，可以在通过调用带参数的构造函数完成调用，可以在其它类中调用，也可以在本类中加一个static{}来完成；
 */
public final class DbcpUtil extends DbTemplate {
	/** 数据源，static */
	private static DataSource DS;
	protected Log log = LogFactory.getLog(this.getClass().getName());
	/**
	 * 配置文件
	 */
	private Properties pros;

	private DbcpUtil() {
		InputStream in = null;
		try {
			pros = new Properties();
			in = this.getClass().getResourceAsStream("/dbcp.properties");
			pros.load(in);
		} catch (Exception ex) {
			log.error("没有找到连接池配置文件", ex);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		createPool();
	}

	private void createPool() {
		String userName = pros.getProperty("userName");
		String password = pros.getProperty("password");
		String driverName = pros.getProperty("driverName");
		String url = pros.getProperty("url");
		int initialSize, maxActive, maxIdle, maxWait, minIdle;
		try {
			initialSize = Integer.valueOf(pros.getProperty("initialSize", "5"))
					.intValue();
			maxActive = Integer.valueOf(pros.getProperty("maxActive", "100"))
					.intValue();
			maxIdle = Integer.valueOf(pros.getProperty("maxIdle", "30"))
					.intValue();
			minIdle = Integer.valueOf(pros.getProperty("minIdle", "3"))
					.intValue();
			// 获得连接的最大等待毫秒数,和数据库连接操作的耗时有关
			maxWait = Integer.valueOf(pros.getProperty("maxWait", "10000"))
					.intValue();
		} catch (NumberFormatException ex) {
			initialSize = 0;
			maxActive = 0;
			maxIdle = 0;
			maxWait = 0;
			minIdle = 0;
		}
		initDS(url, userName, password, driverName, initialSize, maxActive,
				maxIdle, minIdle, maxWait);
	}

	private static DbcpUtil util = null;

	public static DbUtil getInstance() {
		if (util == null)
			util = new DbcpUtil();
		return util;
	}

	/**
	 * 指定所有参数连接数据源
	 * 
	 * @param connectURI
	 *            数据库
	 * @param username
	 *            用户名
	 * @param pswd
	 *            密码
	 * @param driverClass
	 *            数据库连接驱动名
	 * @param initialSize
	 *            初始连接池连接个数
	 * @param maxActive
	 *            最大激活连接数
	 * @param maxIdle
	 *            最大闲置连接数
	 * @param maxWait
	 *            获得连接的最大等待毫秒数
	 * @return
	 */
	private static void initDS(String connectURI, String username, String pswd,
			String driverClass, int initialSize, int maxActive, int maxIdle,
			int minIdle, int maxWait) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUsername(username);
		ds.setPassword(pswd);
		ds.setMinIdle(minIdle);
		ds.setUrl(connectURI);
		// 设置一个时间间隔，启动一个线程，进行最小连接数的补充，或者超出最多连接数并空闲超时连接的自动关闭。
		// 由dbcp进行维护.
		ds.setTimeBetweenEvictionRunsMillis(500);
		ds.setMinEvictableIdleTimeMillis(500);
		ds.setInitialSize(initialSize); // 初始的连接数；
		ds.setMaxActive(maxActive);
		ds.setTestWhileIdle(true);
		ds.setTestOnBorrow(true);
		ds.setTestOnReturn(true);
		ds.setMaxIdle(maxIdle);
		ds.setMaxWait(maxWait);
		DS = ds;
	}

	/** 获得数据源连接状态 */
	public static Map<String, Integer> getDataSourceStats() throws SQLException {
		BasicDataSource bds = (BasicDataSource) DS;
		Map<String, Integer> map = new HashMap<String, Integer>(2);
		map.put("active_number", bds.getNumActive());
		map.put("idle_number", bds.getNumIdle());
		System.out.println("active_number=" + bds.getNumActive()
				+ ",,,idle_number=" + bds.getNumIdle());
		return map;
	}

	/** 关闭数据源 */
	protected static void shutdownDataSource() throws SQLException {
		BasicDataSource bds = (BasicDataSource) DS;
		bds.close();
	}

	public static void main(String[] args) {
		DbUtil db = DbcpUtil.getInstance();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = db.getDBConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select 'a' from dual   ");
			System.out.println("Results:");
			int numcols = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				for (int i = 1; i <= numcols; i++) {
					System.out.print("\t" + rs.getString(i) + "\t");
				}
				System.out.println("");
			}
			System.out.println(getDataSourceStats());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
				if (db != null)
					shutdownDataSource();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List queryList(String sql, List argList, DataHandler handler)
			throws SQLException {
		List ans = new ArrayList();
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		try {
			conn = getDBConn();
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setObject(i + 1, argList.get(i));
				}
			}
			rs = cstmt.executeQuery();
			// 下面就调用抽象类的进行行处理的方法.
			while (rs.next()) {
				handler.processRow(rs);
			}
			ans = handler.getList();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closeStatement(cstmt);
		}
		return ans;
	}

	@Override
	public Connection getDBConn() {
		try {
			return DS.getConnection();
		} catch (SQLException e) {
			System.out.println("获得连接出错！");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object queryOneRecord(String sql, List argList, DataHandler handler)
			throws SQLException {
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		Object ans = null;
		try {
			conn = getDBConn();
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setObject(i + 1, argList.get(i));
				}
			}
			rs = cstmt.executeQuery();

			if (rs.next()) {
				handler.processRow(rs);
				return handler.getList().get(0);
			}

		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		} finally {
			closeStatement(cstmt);
			if (conn != null && !conn.isClosed())
				conn.close();
		}
		return ans;
	}

	@Override
	public int updateRecords(String sql, List argList) throws SQLException {
		int ans = 0;
		Connection conn = null;
		PreparedStatement cstmt = null; 
		conn = getDBConn();
		if (conn.isClosed())
			conn.notify(); 
		cstmt = conn.prepareStatement(sql);
		if (argList != null && argList.size() > 0) {
			for (int i = 0; i < argList.size(); i++) {
				cstmt.setObject(i + 1, argList.get(i));
			}
		}
		ans = cstmt.executeUpdate();
		closeStatement(cstmt);
		if (conn != null && !conn.isClosed())
			conn.close();
		return ans;
	}

}
