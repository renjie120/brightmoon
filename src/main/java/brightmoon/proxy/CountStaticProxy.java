package brightmoon.proxy;

/**
 * 静态代理.
 * 静态代理的好处是简单，缺点是对于每一个代理实现都要写一个类.
 * 解决方法是使用动态代理.
 * @author lsq
 * 
 */
public class CountStaticProxy implements Count {
	private Count impl;

	public CountStaticProxy(Count impl) {
		this.impl = impl;
	}

	public void queryCount() {
		System.out.println("代理中执行queryCount前");
		impl.queryCount();
		System.out.println("代理中执行queryCount后");
	}

	public void updateCount() {
		System.out.println("代理中执行updateCount前");
		impl.updateCount();
		System.out.println("代理中执行updateCount后");
	}

	public static void main(String[] args) {
		Count myCount = new CommonCount();
		CountStaticProxy proxy = new CountStaticProxy(myCount);
		proxy.queryCount();
		proxy.updateCount();
	}
}
