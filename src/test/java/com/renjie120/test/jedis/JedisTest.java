package com.renjie120.test.jedis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

public class JedisTest extends TestCase {
	JedisPool pool;
	Jedis jedis;
	final String ip ="192.168.20.169"; 

	public void setUp() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(10000);
		config.setTestOnBorrow(false);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(new JedisShardInfo(""));
		pool = new JedisPool(new JedisPoolConfig(), "192.168.20.169");
		jedis = pool.getResource();
		jedis.auth("123456");
	}

	@Test
	public void testBasicString() {
		jedis.set("lsq_test_redis", "haha");
		System.out.println(jedis.get("lsq_test_redis"));
	}
	
	@Test
	public void testKeys(){
		Set allKey = jedis.keys("*");
		Iterator<String> iter = jedis.keys("*").iterator();
		int i=0;
		while(iter.hasNext()){
			if(i++>10)
				break;
			String key = iter.next();
			System.out.println(key+":type:"+jedis.type(key)+",value="+jedis.hkeys(key));
		}
	}
}
