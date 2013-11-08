package brightmoon.redis.callback;

import redis.clients.jedis.Jedis;

public interface RedisCallBack<T> {
	public T execute(Jedis jedis);
}
