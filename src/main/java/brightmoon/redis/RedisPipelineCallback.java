package brightmoon.redis;

import redis.clients.jedis.Pipeline;

public abstract class RedisPipelineCallback {
	public abstract void execute(Pipeline p);
}
