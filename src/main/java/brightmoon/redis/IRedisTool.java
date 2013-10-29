package brightmoon.redis;

import java.util.List;
import java.util.Set;

/**
 * 基础的redis操作封装. 只提供关于只读方法的封装，对于写操作可能涉及到事务等操作,示例代码：DictionaryRedis.java
 * 
 * @author lisq
 * 
 */
public interface IRedisTool {
	 
	public List<byte[]> getList(final byte[] k);

	/**
	 * 返回一个键对应的字符串缓存值.
	 */
	public String getKey(final byte[] k) ;

	/**
	 * 返回list的长度.
	 * 
	 * @param k
	 * @return
	 */
	public Long getLen(final byte[] k) ;

	/**
	 * 判断一个key是否存在
	 * 
	 * @param key
	 * @return
	 */
	public boolean existsKey(final byte[] key);

	/**
	 * 返回一个键对应的值,包含各种类型.
	 * @param key
	 * @return
	 */
	public Object getVal(final byte[] key) ;
	
	/**
	 * 返回一个键对应的json串形式.
	 * @param key
	 * @return
	 */
	public String getJsonData(final byte[] key);
	/**
	 * 返回匹配的全部key的集合.
	 * 
	 * @param key
	 * @return
	 */
	public Set<byte[]> allKeys(final byte[] key);
	/**
	 * 返回数据库的大小.
	 * 
	 * @return
	 */
	public Long dbSize();

	/**
	 * 返回指定的配置信息.
	 * 
	 * @return
	 */
	public List<String> getConfig(final String str);

	/**
	 * 删除匹配的全部对应的key.
	 * 
	 * @param key
	 */
	public void deletePreKeys(String key);
}
