package brightmoon.generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 泛型的测试代码.
 * 
 * @author lsq
 * 
 */
public class MyFanxingTest {
	// 泛型单例工厂模式-------------特别适合对函数处理的情况.
	// 下面演示一种泛型单例工厂模式，特别适合于函数处理的相关情况.例如，这里是对一个数字求平方的情况.
	// 注意，这里是静态变量，所有的泛型类型都公用的这里面的静态变量，因为不要定义局部变量!!
	// 如果不用单例工厂模式的话，就需要对所有的Number的子类进行一个个的处理
	public static NumberWrapper<Integer> myInteger = new NumberWrapper<Integer>() {
		public double square(Integer arg) {
			return arg.doubleValue() * arg.doubleValue();
		}
	};
	public static NumberWrapper<Double> myDouble = new NumberWrapper<Double>() {
		public double square(Double arg) {
			return arg.doubleValue() * arg.doubleValue();
		}
	};
	public static NumberWrapper<Float> myFloat = new NumberWrapper<Float>() {
		public double square(Float arg) {
			return arg.doubleValue() * arg.doubleValue();
		}
	};

	private static NumberWrapper<Number> numWrapper = new NumberWrapper<Number>() {
		public double square(Number arg) {
			return arg.doubleValue() * arg.doubleValue();
		}

	};

	@SuppressWarnings("unchecked")
	public static <T extends Number> NumberWrapper getWrapperInstance() {
		return (NumberWrapper<T>) numWrapper;
	}

	public static void main(String[] args) {
		// 下面是用的上面的泛型静态工厂创建的类型。
		// 如果没有的话，就应该写为Map<String, String> a = new HashMap<String,String>();
		Map<String, String> a = MyFanxingTest.newHashMap();
		Collections.emptyList();
		a.put("123", "ccc");
		System.out.println(a.get("123"));

		MyFanxingTest mytest = new MyFanxingTest();
		mytest.test();
		//
		// // 下面演示可以定义通配的泛型集合
		List<?>[] aaa = new ArrayList<?>[4];

		NumberWrapper<Integer> interWrapper = getWrapperInstance();
		System.out.println("123的平方是：" + interWrapper.square(123));

		NumberWrapper<Double> doubleWrapper = getWrapperInstance();
		System.out.println("123.9的平方是：" + doubleWrapper.square(123.9));

		NumberWrapper<Float> floatWrapper = getWrapperInstance();
		System.out.println("123.9的平方是：" + floatWrapper.square(123.9f));

		System.out.println("999的平方是:" + myInteger.square(999));
		System.out.println("999的平方是:" + myDouble.square(999d));
		System.out.println("999的平方是:" + myFloat.square(999f));

		FanxingFanshe aa = new FanxingFanshe();
	}

	// 下面是一个泛型静态工厂方法，使得创建一个泛型变量的时候节约了代码.
	public static <K, V> HashMap<K, V> newHashMap() {
		return new HashMap<K, V>();
	}

	public void inspect(Collection<Object> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
		list.add(new Integer(12));
	}

	public void inspect2(Collection<Integer> list) {
		for (Object obj : list) {
			System.out.println(obj);
		}
	}

	public void test() {
		List<Object> strs = new ArrayList<Object>();
		strs.add("aaaaaa");
		inspect(strs);

		List<Integer> ints = new ArrayList<Integer>();
		ints.add(123);
		inspect2(ints);
	}

}

class MyString<A> implements Comparable<A> {

	public int compareTo(A o) {
		// TODO Auto-generated method stub
		return 0;
	}

}