package brightmoon.redis;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Transaction;

/**
 * redis操作工具.
 * 
 * @author lisq
 * 
 */
public class RedisUtil {
	static JedisPool pool;
	static String ip;
	static String auth;
	static int port;
	protected static Log logger = LogFactory.getLog(RedisUtil.class);
	private static Properties prop;

	private static void init() {
		InputStream inputStream = null;
		try {
			// 加载属性文件
			if (ip == null) {
				// 读取hessian配置属性文件
				inputStream = RedisUtil.class
						.getResourceAsStream("/redis.properties");
				prop = new Properties();
				prop.load(inputStream);
				// 从属性文件得到属性

				ip = prop.getProperty("ip");
				auth = prop.getProperty("auth");
				port = Integer.parseInt(prop.getProperty("port") + "");
				logger.info("获取redis的配置:ip=" + ip + ",auth=" + auth + ",port="
						+ port);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	private RedisUtil() {
		init();
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxActive(20);
		config.setMaxIdle(5);
		config.setMaxWait(10000);
		config.setTestOnBorrow(false);
		pool = new JedisPool(config, ip, port);
	}

	public static RedisUtil getInstance() {
		return Nested.instance;
	}

	// 在第一次被引用时被加载
	static class Nested {
		private static RedisUtil instance = new RedisUtil();
	}

	public Jedis getJedis() {
		Jedis jedis = pool.getResource();
		jedis.auth(auth);
		return jedis;
	}

	/**
	 * 将一个对象保存到指定的键对应的缓存中.
	 * 
	 * @param key
	 * @param obj
	 */
	public void saveObject(String key, Object obj) {
		Jedis jedis = getJedis();
		Map m;
		Transaction tt;
		try {
			tt = jedis.multi();
			m = PropertyUtils.describe(obj);
			Iterator it = m.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry kk = (Map.Entry) it.next();
				Object k = kk.getKey();
				Object v = kk.getValue();
				if (v != null && !"".equals(v.toString())) {
					tt.hset(key, k.toString(), v.toString());
				}
			}
			tt.exec();
		} catch (IllegalAccessException e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			pool.returnBrokenResource(jedis);
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
	}

	public static void main(String[] args) {
		Jedis jedis = RedisUtil.getInstance().getJedis();
		jedis.set("lsq_test_redis", "haha");
		System.out.println(jedis.get("lsq_test_redis"));

		Set allKey = jedis.keys("*");
		Iterator<String> iter = jedis.keys("*").iterator();
		int i = 0;
		while (iter.hasNext()) {
			if (i++ > 10)
				break;
			String key = iter.next();
			System.out.println(key + ":type:" + jedis.type(key) + ",value="
					+ jedis.hvals(key));
		}

//		Atestjava aClass = new Atestjava();
//		aClass.setId("001");
//		aClass.setName("李水清");
//
//		RedisUtil.getInstance().saveObject("lsq_test", aClass);

		// Atestjava b =
		// RedisUtil.getInstance().getObject("lsq_test",Atestjava.class);
		// System.out.println(b.getId()+",,,"+b.getName());
	}
}
