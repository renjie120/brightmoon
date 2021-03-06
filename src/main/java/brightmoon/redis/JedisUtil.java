package brightmoon.redis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import brightmoon.util.Util;

/**
 * redis处理工具类. 包含读取配置文件，初始化jedispool，关闭连接等步骤.
 * 
 * @author lsq
 * 
 */
public class JedisUtil {
	protected Log log = LogFactory.getLog(this.getClass().getName());
	private Properties pros;

	private JedisUtil() {
		init();
		initialPool();
	}

	private static class SingletonHolder {
		private static JedisUtil instance = new JedisUtil();
	}

	public static JedisUtil getInstance() {
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
