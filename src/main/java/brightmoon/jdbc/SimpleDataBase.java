package brightmoon.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SimpleDataBase {
	protected Log log = LogFactory.getLog(this.getClass().getName()); 
	/**
	 * 数据库查询结果集
	 */
	private ResultSet rs;

	/**
	 * 数据库操作
	 */
	private Statement stm;

	/**
	 * 数据库操作驱动
	 */
	private final String dbDriver = Content.CLASSNAME;

	/**
	 * 数据库连接
	 */
	private final String url = Content.URL;

	/**
	 * 数据库连接用户名
	 */
	private final String userName = Content.USER;

	/**
	 * 数据库连接密码
	 */
	private final String userPwd = Content.PWD;

	/**
	 * 数据库连接实例
	 */
	private Connection con = null;

	/**
	 * 加载数据库,检查是不是存在jdbc的相关包.
	 */
	public SimpleDataBase() {
		try {
			Class.forName(dbDriver).newInstance();
		} catch (Exception ex) {
			System.out.print("数据库加载失败！");
		}
	}

	/**
	 * 连接数据库.
	 * @return
	 */
	public Connection getConnection() {
		try {
			con = DriverManager.getConnection(url, userName, userPwd);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return con;
	}

	/**
	 * 关闭数据库连接.
	 */
	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (stm != null) {
				stm.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一个不敏感的数据库语句对象.
	 * @return
	 */
	public Statement getStatement() {
		try {
			con = getConnection();
			stm = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return stm;
	}

	/**
	 * 创建一个可以滚动的但不敏感的数据库语句对象.
	 * @return
	 */
	public Statement getInsStatement() {
		try {
			con = getConnection();
			stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		return stm;
	}
	
	//关闭打开的Statement  
    private void closeStatement(Statement stmt){  
        if(stmt!=null){  
            try {  
                stmt.close();  
                stmt=null;  
            } catch (SQLException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }
        }  
    }  
    //关闭打开的Connection   
    private void closeConnection(Connection conn){  
        if(conn!=null){  
            try {  
                conn.close();  
                conn=null;  
            } catch (SQLException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }
        }  
    }   
   
    /**
     * 事务回滚.
     * @param conn
     */
    private void transactionRollback(Connection conn){  
        if(conn!=null){  
            try {  
                conn.rollback();  
            } catch (SQLException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
          
    }  
	/**
	 * 执行sql语句,并返回结果集.
	 * @param sql
	 * @return
	 */
	public ResultSet getResultSet(String sql) {
		if (sql == null) {
			sql = "";
		}
		try {
			stm = getInsStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
