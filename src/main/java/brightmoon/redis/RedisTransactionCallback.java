package brightmoon.redis;

import redis.clients.jedis.Transaction;

public abstract class RedisTransactionCallback {
	public abstract void execute(Transaction t);
}
