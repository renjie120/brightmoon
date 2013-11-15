package com.renjie120.test.jedis;

import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import brightmoon.redis.JedisPipelineCallback;
import brightmoon.redis.JedisTemplate;
import brightmoon.redis.JedisTransactionCallback;
import brightmoon.redis.JedisCallBack;

public class JedisTemplateTest extends TestCase {
	JedisTemplate util;

	public void setUp() {
		util = new JedisTemplate();
	}

	/**
	 * 
	 * 
	 * <pre>
	 * 测试事务操作.
	 * </pre>
	 */
	@Test
	public void testTrancation() {
		util.executeWithTrancation(new JedisTransactionCallback() {
			@Override
			public void execute(Transaction t) {
				t.set("nname", "1111");
				t.set("nname1", "222");
				t.set("nname2", "333");
			}
		});
	}

	/**
	 * 在一个管道中处理多个redis操作.
	 */
	@Test
	public void testPipe() {
		util.executeWithPipe(new JedisPipelineCallback() {
			@Override
			public void execute(Pipeline p) {
				p.set("nn2ame", "1111");
				p.set("nn2ame1", "222");
				p.set("nn2ame2", "333");
			}
		});
	}

	/**
	 * 
	 * 
	 * <pre>
	 * 测试普遍的执行命令方法.
	 * </pre>
	 */
	@Test
	public void testExecute() {
		final String key2 = "dinnerorder:order:all_sno";
		Set<String> ans = util.execute(new JedisCallBack<Set<String>>() {
			@Override
			public Set<String> execute(Jedis jedis) {
				// TODO Auto-generated method stub
				return jedis.smembers(key2);
			}
		});
		System.out.println(key2 + "对应的结果是：" + ans);

		String result = util.execute(new JedisCallBack<String>() {
			@Override
			public String execute(Jedis jedis) {
				// TODO Auto-generated method stub
				return jedis.get("haha");
			}
		});
		System.out.println("打印出来的结果：" + result);
	}

}
