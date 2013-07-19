package brightmoon.redis;

import redis.clients.jedis.Jedis;

public abstract class JedisCallback {
	public abstract void execute(Jedis j);
}
