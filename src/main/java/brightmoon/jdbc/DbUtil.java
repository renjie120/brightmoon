package brightmoon.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库操作接口.
 * 
 * @author lsq
 * 
 */
public interface DbUtil {
	/**
	 * 查询返回集合
	 * 
	 * @param sql
	 * @param argList
	 * @param handler
	 * @return
	 */
	public List queryList(String sql, List argList, DataHandler handler)
			throws SQLException;

	public Connection getDBConn();
	
	public List queryList(String sql, DataHandler handler) throws SQLException;

	/**
	 * 查询一条记录返回结果.
	 * 
	 * @param sql
	 * @param argList
	 * @return
	 */
	public Object queryOneRecord(String sql, List argList) throws SQLException;

	public Object queryOneRecord(String sql, List argList, DataHandler handler)
			throws SQLException;

	public int updateRecords(String sql, List argList) throws SQLException;

	public int updateRecords(String sql) throws SQLException;

	public Object queryOneRecord(String sql, DataHandler handler)
			throws SQLException;

	public int queryForInt(String sql) throws SQLException;

	public int queryForInt(String sql, List argList) throws SQLException;

	public double queryForDouble(String sql) throws SQLException;

	public double queryForDouble(String sql, List argList) throws SQLException;

	public String queryForString(String sql) throws SQLException;

	public String queryForString(String sql, List argList) throws SQLException;
}
