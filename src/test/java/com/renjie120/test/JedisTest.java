package com.renjie120.test;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import brightmoon.redis.RedisCallback;
import brightmoon.redis.RedisPipelineCallback;
import brightmoon.redis.RedisTemplate;
import brightmoon.redis.RedisTransactionCallback;

public class JedisTest {
	@Test
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
}
