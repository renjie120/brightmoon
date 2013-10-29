package brightmoon.redis;

import redis.clients.jedis.Transaction;

public abstract class JedisTransactionCallback {
	public abstract void execute(Transaction t);
}
