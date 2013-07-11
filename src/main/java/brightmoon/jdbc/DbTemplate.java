package brightmoon.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * 数据库操作接口.
 * 
 * @author lsq
 * 
 */
public abstract class DbTemplate implements DbUtil {

	@Override
	public abstract List queryList(String sql, List argList, DataHandler handler)
			throws SQLException;

	@Override
	public abstract Connection getDBConn();

	@Override
	public abstract Object queryOneRecord(String sql, List argList,
			DataHandler handler) throws SQLException;

	@Override
	public abstract int updateRecords(String sql, List argList)
			throws SQLException;

	@Override
	public int updateRecords(String sql) throws SQLException {
		return updateRecords(sql, null);
	}

	@Override
	public List queryList(String sql, DataHandler handler) throws SQLException {
		return queryList(sql, null, handler);
	}

	@Override
	public Object queryOneRecord(String sql, List argList) throws SQLException {
		return queryOneRecord(sql, argList, new DataHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				addRecord(rs.getObject(1));
			}
		});
	}

	@Override
	public Object queryOneRecord(String sql, DataHandler handler)
			throws SQLException {
		return queryOneRecord(sql, null, handler);
	}

	@Override
	public int queryForInt(String sql) throws SQLException {
		return queryForInt(sql, null);
	}

	@Override
	public int queryForInt(String sql, List argList) throws SQLException {
		Object obj = queryOneRecord(sql, argList);
		if (obj != null)
			return Integer.parseInt(queryOneRecord(sql, argList).toString());
		else
			throw new SQLException("未查询到数据!");
	}

	@Override
	public double queryForDouble(String sql) throws SQLException {
		return queryForDouble(sql, null);
	}

	@Override
	public double queryForDouble(String sql, List argList) throws SQLException {
		return Double.parseDouble(queryOneRecord(sql, argList).toString());
	}

	@Override
	public String queryForString(String sql) throws SQLException {
		return queryForString(sql, null);
	}

	@Override
	public String queryForString(String sql, List argList) throws SQLException {
		Object obj = queryOneRecord(sql, argList);
		if (obj != null)
			return obj.toString();
		else
			return null;
	}

	// 关闭打开的Statement
	protected void closeStatement(Statement stmt) {
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
}
