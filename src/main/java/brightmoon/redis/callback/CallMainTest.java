package brightmoon.redis.callback;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;


public class CallMainTest {
	public static void main(String[] args) {
		final String key = "testlishuiqing";
//		RedisFunction<String> s = new RedisFunction<String>();
//		s.setCallBack(new RedisCallBack<String>() {
//
//			@Override
//			public String execute(Jedis jedis) {
//				// TODO Auto-generated method stub
//				return jedis.get(key);
//			}
// 
//		});
		
		RedisFunction<Set<String>> s2 = new RedisFunction<Set<String>>();
		final String key2 = "dinnerorder:order:all_sno";
		s2.setCallBack(new RedisCallBack<Set<String>>() {

			@Override
			public Set<String> execute(Jedis jedis) {
				// TODO Auto-generated method stub
				return jedis.smembers(key2);
			}
 
		});
		Set<String> ans = s2.call();
		System.out.println(key2+"对应的结果是："+ans);
	}
}
