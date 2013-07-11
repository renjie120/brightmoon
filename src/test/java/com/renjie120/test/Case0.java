package com.renjie120.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

import javax.sql.DataSource;

import junit.framework.TestCase;

import org.apache.commons.dbcp.BasicDataSource;

import com.alibaba.druid.pool.DruidDataSource;

public class Case0 extends TestCase {

	private String jdbcUrl;
	private String user;
	private String password;
	private String driverClass;
	private int initialSize = 10;
	private int minIdle = 3;
	private int maxIdle = 8;
	private int maxActive = 20;
	private String validationQuery = "SELECT 1";
	private boolean testOnBorrow = false;

	private long minEvictableIdleTimeMillis = 3000;
	public final int LOOP_COUNT = 5;
	public final int COUNT = 1000 * 10;

	protected void setUp() throws Exception {
		// jdbcUrl = "jdbc:fake:dragoon_v25masterdb";
		// user = "dragoon25";
		// password = "dragoon25";
		// driverClass = "com.alibaba.druid.mock.MockDriver";

		jdbcUrl = "jdbc:oracle:thin:@127.0.0.1:1521:lsq";
		user = "lsqtest";
		password = "lsqtest";
	}

	public void test_0() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();

		dataSource.setInitialSize(initialSize);
		dataSource.setMaxActive(maxActive);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(jdbcUrl);
		dataSource.setPoolPreparedStatements(true);
		dataSource.setUsername(user);
		dataSource.setPassword(password);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTestOnBorrow(testOnBorrow);
		dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

		for (int i = 0; i < LOOP_COUNT; ++i) {
			p0(dataSource, "druid");
		}
		System.out.println();
	}

	public void test_1() throws Exception {
		// final BasicDataSource dataSource = new BasicDataSource();
		//
		// dataSource.setInitialSize(initialSize);
		// dataSource.setMaxActive(maxActive);
		// dataSource.setMinIdle(minIdle);
		// dataSource.setMaxIdle(maxIdle);
		// dataSource.setPoolPreparedStatements(true);
		// dataSource.setDriverClassName(driverClass);
		// dataSource.setUrl(jdbcUrl);
		// dataSource.setPoolPreparedStatements(true);
		// dataSource.setUsername(user);
		// dataSource.setPassword(password);
		// dataSource.setValidationQuery(validationQuery);
		// dataSource.setTestOnBorrow(testOnBorrow);
		// dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		//
		// for (int i = 0; i < LOOP_COUNT; ++i) {
		// p0(dataSource, "dbcp");
		// }
		// System.out.println();
	}

	public void test_2() throws Exception {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setMinIdle(minIdle);
		ds.setUrl(jdbcUrl);
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
		ds.setMinIdle(maxIdle);
		for (int i = 0; i < LOOP_COUNT; ++i) {
			p0(ds, "dbcp");
		}
		System.out.println();
	}

	private void p0(DataSource dataSource, String name) throws SQLException {
		long startMillis = System.currentTimeMillis();
		// long startYGC = TestUtil.getYoungGC();
		// long startFullGC = TestUtil.getFullGC();

		for (int i = 0; i < COUNT; ++i) {
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1 FROM dual");
			rs.close();
			stmt.close();
			conn.close();
		}
		long millis = System.currentTimeMillis() - startMillis;
		// long ygc = TestUtil.getYoungGC() - startYGC;
		// long fullGC = TestUtil.getFullGC() - startFullGC;

		System.out.println(name + " millis : "
				+ NumberFormat.getInstance().format(millis));
		// + ", YGC " + ygc + " FGC " + fullGC);
	}
}