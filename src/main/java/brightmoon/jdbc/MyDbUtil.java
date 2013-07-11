package brightmoon.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class MyDbUtil extends DbTemplate {
	public List queryList(String sql, List argList, DataHandler handler)
			throws SQLException {
		List ans = new ArrayList();
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
			DBPoolManager.getInstance().freeDBConnection(conn);
		}
		return ans;
	}

	Connection conn = null;
	PreparedStatement cstmt = null;
	ResultSet rs = null;
	Object ans = null;

	/**
	 * 查询一条记录返回结果.
	 * 
	 * @param sql
	 * @param argList
	 * @return 多加一个参数 用来返回一条结果集
	 */
	public Object queryOneRecord(String sql, List argList, DataHandler handler)
			throws SQLException {
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
			DBPoolManager.getInstance().freeDBConnection(conn);
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
	public int updateRecords(String sql, List argList) throws SQLException {
		int ans = 0;
		try {
			conn = getDBConn();
			cstmt = conn.prepareStatement(sql);
			if (argList != null && argList.size() > 0) {
				for (int i = 0; i < argList.size(); i++) {
					cstmt.setObject(i + 1, argList.get(i));
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
			DBPoolManager.getInstance().freeDBConnection(conn);
		}
		return ans;
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

	@Override
	public Connection getDBConn() {
		return DBPoolManager.getInstance().getDBConn();
	}
}
