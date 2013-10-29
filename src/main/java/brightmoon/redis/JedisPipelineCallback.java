package brightmoon.redis;

import redis.clients.jedis.Pipeline;

public abstract class JedisPipelineCallback {
	public abstract void execute(Pipeline p);
}
