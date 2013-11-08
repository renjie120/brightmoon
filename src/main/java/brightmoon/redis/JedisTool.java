package brightmoon.redis;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * 基础的redis操作封装. 只提供关于只读方法的封装，对于写操作可能涉及到事务等操作,示例代码：DictionaryRedis.java
 * 
 * @author lisq
 * 
 */
public class JedisTool implements IRedisTool {
	private JedisTemplate template;
	public JedisTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JedisTemplate template) {
		this.template = template;
	}

	@Override
	public List<byte[]> getList(byte[] k) { 
		return null;
	}

	@Override
	public String getKey(byte[] k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getLen(byte[] k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsKey(byte[] key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getVal(byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJsonData(byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<byte[]> allKeys(byte[] key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long dbSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getConfig(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePreKeys(String key) {
		// TODO Auto-generated method stub
		
	}
	 
}
