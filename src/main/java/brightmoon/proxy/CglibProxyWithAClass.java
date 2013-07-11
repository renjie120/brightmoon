package brightmoon.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 注意测试的被代理类是带参的对象.
 * @author lsq
 *
 */
public class CglibProxyWithAClass implements MethodInterceptor {
	private Object target;

	public Object getInstance(Object target, String initArg) {
		this.target = target;
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(this.target.getClass());
		enhancer.setCallback(this);
		//这里动态创建的一个子类，是调用的带参的构造函数
		return enhancer.create(new Class[] { String.class },
				new Object[] { initArg });
	}

	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("cglib动态代理开始,调用的方法是:" + method.getName()
				+ ",生成的代理方法是：" + proxy.getSuperName());
		// 注意这里是调用的invokeSuper-----------如果是invoke就会出现不停调用本身的死循环！
		proxy.invokeSuper(obj, args);
		System.out.println("cglib动态代理结束");
		return null;
	}

	public static void main(String[] args) throws Exception {
		CglibProxyWithAClass test = new CglibProxyWithAClass();
		// CGlib创建带有参数的普通类的代理类
		AClass proxy = (AClass) test.getInstance(new AClass(null), "123");
		proxy.test();
	}
}
