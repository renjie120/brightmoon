package brightmoon.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理很简单，不过需要被代理的类实现接口.
 * 需要实现接口InvocationHandler接口（含有invoke方法）,以及使用Proxy.newProxyInstance动态生成代理对象.
 * 
 * @author lsq
 * 
 */
public class CountDynamicProxy implements InvocationHandler {
	private Object target;

	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		System.out.println("动态代理开始");
		result = method.invoke(target, args);
		System.out.println("动态代理结束");
		return result;
	}

	public static void main(String[] args) {
		CountDynamicProxy p = new CountDynamicProxy();
		Count c = (Count) p.bind(new CommonCount());
		c.queryCount();
		c.updateCount();
	}
}
