package brightmoon.redis.callback;

import brightmoon.redis.JedisUtil;
import redis.clients.jedis.Jedis;

public class RedisFunction<T> {
	RedisCallBack<T> callback;
	private JedisUtil util;
	public RedisFunction(){
		util = JedisUtil.getInstance();
	}
	public T call() {
		Jedis jedis = util.getJedis();
		T result = null;
		try {
			result = jedis.getDB();  
		} catch (Exception e) { 
			util.releaseBrokenJedis(jedis);
		} finally {
			util.releaseJedis(jedis);
		}
		return result;
	}

	public void setCallBack(RedisCallBack<T> callback) {
		this.callback = callback;
	}
}
