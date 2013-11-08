package brightmoon.redis.callback;


public class CallMainTest {
	public static void main(String[] args) {
		RedisFunction<String> s = new RedisFunction<String>();
		s.setCallBack(new RedisCallBack<String>() {

			@Override
			public String execute() {
				// TODO Auto-generated method stub
				return null;
			}
		});
		s.call();
	}
}
