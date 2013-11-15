package brightmoon.redis;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

/**
 * 实用的redis处理模板类.
 * 
 * @author lsq
 * 
 */
public class JedisTemplate implements IRedisTool {
	private JedisUtil util;

	public JedisTemplate() {
		util = JedisUtil.getInstance();
	}

	protected Log log = LogFactory.getLog(this.getClass().getName());

	/**
	 * 
	 * 
	 * <pre>
	 * 通用的普通的调用jedis客户端的工具类.
	 * </pre>
	 * 
	 * @param callback
	 * @return
	 */
	public <T> T execute(JedisCallBack<T> callback) {
		Jedis jedis = util.getJedis();
		T result = null;
		try {
			result = callback.execute(jedis);
		} catch (Exception e) {
			util.releaseBrokenJedis(jedis);
		} finally {
			util.releaseJedis(jedis);
		}
		return result;
	} 

	/**
	 * 使用事务进行处理.
	 * 
	 * @param sth
	 */
	public void executeWithTrancation(JedisTransactionCallback sth) {
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
	public void executeWithPipe(JedisPipelineCallback sth) {
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

	@Override
	public List<byte[]> getList(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<List<byte[]>>() {
			@Override
			public List<byte[]> execute(Jedis jedis) {
				return jedis.lrange(key, 0, -1);
			}
		});
	}

	private String byteArrToStr(byte[] b) {
		try {
			return new String(b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	@Override
	public String getKey(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<String>() {
			@Override
			public String execute(Jedis jedis) {
				return byteArrToStr(jedis.get(key));
			}
		});
	}

	@Override
	public Long getLen(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<Long>() {
			public Long execute(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	@Override
	public boolean existsKey(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<Boolean>() {
			public Boolean execute(Jedis jedis) {
				return jedis.exists(key);
			}
		});
	}

	@Override
	public Object getVal(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<Object>() {
			public Object execute(Jedis jedis) {
				String tp = jedis.type(key);
				if ("string".equals(tp)) {
					return byteArrToStr(jedis.get(key));
				} else if ("hash".equals(tp)) {
					return jedis.hgetAll(key);
				} else if ("list".equals(tp)) {
					return jedis.lrange(key, 0, -1);
				} else if ("set".equals(tp)) {
					return jedis.smembers(key);
				} else if ("zset".equals(tp)) {
					return jedis.zrangeWithScores(key, 0, -1);
				} else
					return "";
			}
		});
	}

	@Override
	public String getJsonData(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<String>() {
			public String execute(Jedis jedis) {
				String tp = jedis.type(key);
				if ("string".equals(tp)) {
					return "{'type':'string','value':'"
							+ byteArrToStr(jedis.get(key)) + "'}";
				} else if ("hash".equals(tp)) {
					Map<byte[], byte[]> m = jedis.hgetAll(key);
					Iterator<Entry<byte[], byte[]>> it = m.entrySet()
							.iterator();
					StringBuilder bui = new StringBuilder(100);
					bui.append("[");
					while (it.hasNext()) {
						Entry<byte[], byte[]> e = it.next();
						String key = byteArrToStr(e.getKey());
						String val = byteArrToStr(e.getValue());
						bui.append("{'key':'" + key + "','value':'" + val
								+ "'},");
					}
					if (m != null && m.size() > 0) {
						bui = bui.deleteCharAt(bui.lastIndexOf(","));
					}
					return "{'type':'hash','value':"
							+ bui.append("]").toString() + "}";
				} else if ("list".equals(tp)) {
					List<byte[]> list = jedis.lrange(key, 0, -1);
					StringBuilder bui = new StringBuilder(100);
					bui.append("[");
					for (byte[] b : list) {
						bui.append("'" + byteArrToStr(b) + "',");
					}
					if (list != null && list.size() > 0) {
						bui = bui.deleteCharAt(bui.lastIndexOf(","));
					}
					return "{'type':'list','value':"
							+ bui.append("]").toString() + "}";
				} else if ("set".equals(tp)) {
					Set<byte[]> s = jedis.smembers(key);
					StringBuilder bui = new StringBuilder(100);
					bui.append("[");
					for (byte[] b : s) {
						bui.append("'" + new String(b) + "',");
					}
					if (s != null && s.size() > 0) {
						bui = bui.deleteCharAt(bui.lastIndexOf(","));
					}
					return "{'type':'set','value':"
							+ bui.append("]").toString() + "}";
				} else if ("zset".equals(tp)) {
					Set<Tuple> s = jedis.zrangeWithScores(key, 0, -1);
					StringBuilder bui = new StringBuilder(100);
					bui.append("[");
					for (Tuple b : s) {
						bui.append("{'value':'" + b.getElement()
								+ "','score':'" + b.getScore() + "'},");
					}
					if (s != null && s.size() > 0) {
						bui = bui.deleteCharAt(bui.lastIndexOf(","));
					}
					return "{'type':'zset','value':"
							+ bui.append("]").toString() + "}";
				} else
					return "";
			}
		});
	}

	@Override
	public Set<byte[]> allKeys(byte[] k) {
		final byte[] key = k;
		return execute(new JedisCallBack<Set<byte[]>>() {
			public Set<byte[]> execute(Jedis jedis) {
				return jedis.keys(key);
			}
		});
	}

	@Override
	public Long dbSize() {
		return execute(new JedisCallBack<Long>() {
			public Long execute(Jedis jedis) {
				return jedis.dbSize();
			}
		});
	}

	@Override
	public List<String> getConfig(String str) {
		final String key = str;
		return execute(new JedisCallBack<List<String>>() {
			public List<String> execute(Jedis jedis) {
				return jedis.configGet(key);
			}
		});
	}

	@Override
	public void deletePreKeys(String key) { 
		if (key == null)
			return;
		//查询全部的key
		final Set<byte[]> allKeys = allKeys(key.getBytes());
		//在一个事务里面进行删除操作.
		executeWithTrancation(new JedisTransactionCallback() { 
			@Override
			public void execute(Transaction t) {
				Iterator<byte[]> it = allKeys.iterator();
				while (it.hasNext()) {
					byte[] key = it.next();
					if (key != null) {
						t.del(key);
					}
				}
			} 
		}); 
	} 
}
