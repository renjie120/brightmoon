package brightmoon.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MultiDbTool {
	/**
	 * 查询返回集合
	 * 
	 * @param sql
	 * @param argList
	 * @param handler
	 * @return
	 */
	public List queryList(String dbId,String sql, List argList, DataHandler handler)
			throws SQLException {
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		List ans = new ArrayList();
		try {
			conn = MultiDBPoolManager.getInstance().getDBConn(dbId);
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setString(i + 1, argList.get(i).toString());
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
			MultiDBPoolManager.getInstance().freeDBConnection(dbId,conn);
		}
		return ans;
	}

	public List queryList(String dbId,String sql, DataHandler handler) {
		List list = new ArrayList();
		try {
			list = queryList(sql, null, handler);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return list;
	}

	/**
	 * 查询一条记录返回结果.
	 * 
	 * @param sql
	 * @param argList
	 * @return
	 */
	public Object queryOneRecord(String dbId,String sql, List argList) throws SQLException {
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		Object ans = null;
		try {
			conn = MultiDBPoolManager.getInstance().getDBConn(dbId);
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setString(i + 1, argList.get(i).toString());
				}
			}
			rs = cstmt.executeQuery();

			if (rs.next()) {
				ans = rs.getObject(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closeStatement(cstmt);
			MultiDBPoolManager.getInstance().freeDBConnection(dbId,conn);
		}
		return ans;
	}

	/**
	 * 查询一条记录返回结果.
	 * 
	 * @param sql
	 * @param argList
	 * @return 多加一个参数 用来返回一条结果集
	 */
	public Object queryOneRecord(String dbId,String sql, List argList, DataHandler handler)
			throws SQLException {
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		Object ans = null;
		try {
			conn = MultiDBPoolManager.getInstance().getDBConn(dbId);
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setString(i + 1, argList.get(i).toString());
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
			MultiDBPoolManager.getInstance().freeDBConnection(dbId,conn);
		}
		return ans;
	}

	/**
	 * 更新一条语句,适用于插入语句,或者执行删除,更新操作.
	 * 
	 * @param sql
	 *            sql语句
	 * @param argList
	 *            参数
	 * @return
	 */
	public int updateRecords(String dbId,String sql, List argList) throws SQLException {
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		int ans = 0;
		try {
			conn = MultiDBPoolManager.getInstance().getDBConn(dbId);
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setString(i + 1, argList.get(i).toString());
				}
			}
			ans = cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closeStatement(cstmt);
			MultiDBPoolManager.getInstance().freeDBConnection(dbId,conn);
		}
		return ans;
	}

	/**
	 * 更新一条语句,适用于插入语句,或者执行删除,更新操作.
	 * 
	 * @param sql
	 * @return
	 */
	public int updateRecords(String dbId,String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement cstmt = null;
		ResultSet rs = null;
		int ans = 0;
		try {
			conn = MultiDBPoolManager.getInstance().getDBConn(dbId);
			cstmt = conn.prepareStatement(sql);
			ans = cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException();
		} finally {
			closeStatement(cstmt);
			MultiDBPoolManager.getInstance().freeDBConnection(dbId,conn);
		}
		return ans;
	}
	  

	// 关闭打开的Statement
	private void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
				stmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 加了个参数handler 用于获取一条数据记录，SQL语句不用传参数时用
	 */
	public Object queryOneRecord(String dbId,String sql, DataHandler handler)
			throws SQLException {
		return queryOneRecord(sql, null, handler);
	}

	public int queryForInt(String dbId,String sql) throws SQLException {
		return queryForInt(dbId,sql, null);
	}

	public int queryForInt(String dbId,String sql, List argList) throws SQLException {
		return Integer.parseInt(queryOneRecord(dbId,sql, argList).toString());
	}

	public double queryForDouble(String dbId,String sql) throws SQLException {
		return queryForDouble(dbId,sql, null);
	}

	public double queryForDouble(String dbId,String sql, List argList) throws SQLException {
		return Double.parseDouble(queryOneRecord(dbId,sql, argList).toString());
	}

	public String queryForString(String dbId,String sql) throws SQLException {
		return queryForString(dbId,sql, null);
	}

	public String queryForString(String dbId,String sql, List argList) throws SQLException {
		return queryOneRecord(dbId,sql, argList).toString();
	}

	/**
	 * 事务回滚.
	 * 
	 * @param conn
	 */
	private void transactionRollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
