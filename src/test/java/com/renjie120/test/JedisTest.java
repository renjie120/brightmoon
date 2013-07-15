package com.renjie120.test;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import brightmoon.jdbc.DataHandler;
import brightmoon.jdbc.MyDbUtil;
import brightmoon.redis.RedisCallback;
import brightmoon.redis.RedisPipelineCallback;
import brightmoon.redis.RedisTemplate;
import brightmoon.redis.RedisTransactionCallback;

public class JedisTest {
	// @Test
	public void firstTest() throws Exception {
		RedisTemplate util = new RedisTemplate();
		/**
		 * 在一个事务中提交多个redis操作
		 */
		util.consoleWithTrancation(new RedisTransactionCallback() {
			@Override
			public void execute(Transaction t) {
				t.set("nname", "1111");
				t.set("nname1", "222");
				t.set("nname2", "333");
			}
		});

		/**
		 * 在一个管道中处理多个redis操作.
		 */
		util.consoleWithPipe(new RedisPipelineCallback() {
			@Override
			public void execute(Pipeline p) {
				p.set("nn2ame", "1111");
				p.set("nn2ame1", "222");
				p.set("nn2ame2", "333");
			}
		});

		/**
		 * 进行一个redis操作.
		 */
		util.console(new RedisCallback() {
			@Override
			public void execute(Jedis j) {
				j.set("haha", "还是是多少多少看");
			}
		});
	}

	final static String PREX = "TALLY_TYPE";

	/**
	 * 从数据库保存到redis.
	 * 
	 * @throws Exception
	 */
	// @Test
	public void copyFromDbToRedis() throws Exception {
		RedisTemplate util = new RedisTemplate();

		final String sql = "select * from tally_type_t";

		util.consoleWithPipe(new RedisPipelineCallback() {
			@Override
			public void execute(Pipeline pp) {
				final Pipeline p = pp;
				MyDbUtil db = new MyDbUtil();
				try {
					db.queryList(sql, null, new DataHandler() {
						@Override
						public void processRow(ResultSet rs)
								throws SQLException {
							int typeSno = rs.getInt(1);
							String type_desc = rs.getString(2);
							String money_type = rs.getString(3);
							String parent_type = rs.getString(4);
							String type_code = rs.getString(5);
							if (type_desc != null)
								p.set(PREX + ":" + typeSno + ":DESC", type_desc);
							if (money_type != null)
								p.set(PREX + ":" + typeSno + ":MONEY_TYPE",
										money_type);
							if (parent_type != null)
								p.set(PREX + ":" + typeSno + ":PARENT_TYPE",
										parent_type);
							if (type_code != null)
								p.set(PREX + ":" + typeSno + ":TYPE_CODE",
										type_code);
						}
					});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Test
	public void getTallayFromRedis() throws Exception {
		RedisTemplate util = new RedisTemplate();
		final String tallyType = "5";
		util.console(new RedisCallback() {
			@Override
			public void execute(Jedis j) {
				System.out.println("moneyType = "
						+ j.get(PREX + ":" + tallyType + ":MONEY_TYPE"));
				System.out.println("DESC = "
						+ j.get(PREX + ":" + tallyType + ":DESC"));
				System.out.println("PARENT_TYPE = "
						+ j.get(PREX + ":" + tallyType + ":PARENT_TYPE"));
				System.out.println("TYPE_CODE = "
						+ j.get(PREX + ":" + tallyType + ":TYPE_CODE"));

			}
		});
	}

	@Test
	public void deleteTallyFromRedis() throws Exception {
		RedisTemplate util = new RedisTemplate();
		final String tallyType = "5"; 
		util.consoleWithTrancation(new RedisTransactionCallback() {
			
			@Override
			public void execute(Transaction t) {
				 
			}
		});
	}
}
