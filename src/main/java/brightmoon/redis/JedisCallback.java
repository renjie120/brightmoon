package brightmoon.redis;

import redis.clients.jedis.Jedis;

public interface JedisCallBack<T> {
	public T execute(Jedis jedis);
}
