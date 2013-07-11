package brightmoon.generic;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 关于反射的一些使用例子.
 * @author lsq
 *
 */
public class FanxingFanshe {
	private List<String> myList;

	/**
	 * 下面得到的是带有泛型参数的泛型类型.
	 * 
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	public void getFanxingType() throws SecurityException, NoSuchFieldException {
		Field field = FanxingFanshe.class.getDeclaredField("myList");
		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			Type[] actualTypes = paramType.getActualTypeArguments();
			for (Type atype : actualTypes) {
				if (atype instanceof Class) {
					Class clz = (Class) atype;
					System.out.println(clz.getName());
				}
			}
		}
	}

	/**
	 * 演示反射的时候对一个list限制使用add方法.
	 * @param list
	 * @return
	 */
	public List getList(final List list) {
		return (List) Proxy.newProxyInstance(
				FanxingFanshe.class.getClassLoader(),
				new Class[] { List.class }, new InvocationHandler() {
					public Object invoke(Object proxy, Method method,
							Object[] args) throws Throwable {
						if ("add".equals(method.getName()))
							throw new UnsupportedOperationException();
						else
							return method.invoke(list, args);
					}
				});
	}

	public static void main(String[] args) throws SecurityException,
			NoSuchFieldException {
		FanxingFanshe test = new FanxingFanshe();
		test.getFanxingType();

		List<String> initList = new ArrayList<String>();
		initList.add("123");
		initList = test.getList(initList);
		initList.add("haha"); 
	}
}
