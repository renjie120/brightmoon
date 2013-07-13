package brightmoon.redis;

import redis.clients.jedis.Jedis;

public abstract class RedisCallback {
	public abstract void execute(Jedis j);
}
