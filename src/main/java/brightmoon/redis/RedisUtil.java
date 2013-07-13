package brightmoon.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import brightmoon.util.Util;

/**
 * redis处理工具类.
 * 包含读取配置文件，初始化jedispool，关闭连接等步骤.
 * @author lsq
 *
 */
public class RedisUtil {
	protected Log log = LogFactory.getLog(this.getClass().getName());
	private Properties pros;

	private RedisUtil() {
		init();
		initialPool();
	}

	private static class SingletonHolder {
		private static RedisUtil instance = new RedisUtil();
	}

	public static RedisUtil getInstance() {
		return SingletonHolder.instance;
	}

	private String host;
	private String port;
	private String maxActive;
	private String maxIdle;
	private String testOnBorrow;
	private String maxWait;
	private JedisPool jedisPool;

	private void init() {
		InputStream in = null;
		try {
			pros = new Properties();
			in = this.getClass().getResourceAsStream("/redis.properties");
			pros.load(in);

			host = pros.getProperty("host");
			port = pros.getProperty("port");
			maxActive = Util.notBlank(pros.getProperty("maxActive"), "20");
			maxWait = Util.notBlank(pros.getProperty("maxWait"), "5");
			maxIdle = Util.notBlank(pros.getProperty("maxIdle"), "1000");
			testOnBorrow = Util.notBlank(pros.getProperty("testOnBorrow"),
					"false");
		} catch (Exception ex) {
			log.error("没有找到redis配置文件", ex);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private void initialPool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(Integer.parseInt(maxActive));
		config.setMaxIdle(Integer.parseInt(maxIdle));
		config.setMaxWait(Integer.parseInt(maxWait));
		config.setTestOnBorrow("true".equals(testOnBorrow));
		jedisPool = new JedisPool(config, host, Integer.parseInt(port));
	}

	public Jedis getJedis() {
		return jedisPool.getResource();
	}

	/**
	 * 进行普通的处理.
	 * @param j
	 */
	public void console(RedisCallback j) {
		Jedis jedis = getJedis(); 
		try {
			j.execute(jedis); 
		} catch (Exception e) {
			log.error("console出现异常", e);
			releaseBrokenJedis(jedis);
		} finally {
			releaseJedis(jedis);
		}
	}
	
	/**
	 * 使用事务进行处理.
	 * 
	 * @param sth
	 */
	public void consoleWithTrancation(RedisTransactionCallback sth) {
		Jedis jedis = getJedis();
		Transaction t = jedis.multi();
		try {
			sth.execute(t);
			t.exec();
		} catch (Exception e) {
			log.error("consoleWithTrancation出现异常", e);
			releaseBrokenJedis(jedis);
		} finally {
			releaseJedis(jedis);
		}
	}

	/**
	 * 使用管道进行处理.
	 * @param sth
	 */
	public void consoleWithPipe(RedisPipelineCallback sth) {
		Jedis jedis = getJedis();
		Pipeline p = jedis.pipelined();
		try {
			sth.execute(p);
			p.sync();
		} catch (Exception e) {
			log.error("consoleWithPipe出现异常", e);
			releaseBrokenJedis(jedis);
		} finally {
			releaseJedis(jedis);
		}
	}

	/**
	 * 释放被损坏的jedis.
	 * 
	 * @param jd
	 */
	public void releaseBrokenJedis(Jedis jd) {
		jedisPool.returnBrokenResource(jd);
		jd = null;
	}

	/**
	 * 从连接池中释放jedis
	 * 
	 * @param jd
	 */
	public void releaseJedis(Jedis jd) {
		jedisPool.returnResource(jd);
		jd = null;
	}
}
