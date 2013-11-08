package brightmoon.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisCallback;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

/**
 * 实用的redis处理模板类.
 * 
 * @author lsq
 * 
 */
public class JedisTemplate<K, V> {
	private JedisUtil util;

	public JedisTemplate() {
		util = JedisUtil.getInstance();
	}

	protected Log log = LogFactory.getLog(this.getClass().getName());

	public <T> T execute(RedisCallback<T> action) {
		return execute(action);
	}

	/**
	 * 进行普通的处理.
	 * 
	 * @param j
	 */
	public void console(JedisCallback j) {
		Jedis jedis = util.getJedis();
		try {
			j.execute(jedis);
		} catch (Exception e) {
			log.error("console出现异常", e);
			util.releaseBrokenJedis(jedis);
		} finally {
			util.releaseJedis(jedis);
		}
	}

	/**
	 * 使用事务进行处理.
	 * 
	 * @param sth
	 */
	public void consoleWithTrancation(JedisTransactionCallback sth) {
		Jedis jedis = util.getJedis();
		Transaction t = jedis.multi();
		try {
			sth.execute(t);
			t.exec();
		} catch (Exception e) {
			log.error("consoleWithTrancation出现异常", e);
			util.releaseBrokenJedis(jedis);
		} finally {
			util.releaseJedis(jedis);
		}
	}

	/**
	 * 使用管道进行处理.
	 * 
	 * @param sth
	 */
	public void consoleWithPipe(JedisPipelineCallback sth) {
		Jedis jedis = util.getJedis();
		Pipeline p = jedis.pipelined();
		try {
			sth.execute(p);
			p.sync();
		} catch (Exception e) {
			log.error("consoleWithPipe出现异常", e);
			util.releaseBrokenJedis(jedis);
		} finally {
			util.releaseJedis(jedis);
		}
	}

}
