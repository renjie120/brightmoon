package com.renjie120.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.ezmorph.Morpher;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.json.util.JSONUtils;

import org.junit.Before;

import brightmoon.temp.ComplexBean;
import brightmoon.temp.SimpleBean;
import brightmoon.temp.TestBean;
import brightmoon.util.NewJsonUtil;
import brightmoon.util.Util;

public class NewJsonTest {
	private String cache_str = "{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"}";
	private String cache_arr = "[{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"}]";
	private String complex_str = "{\"arr\":[{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"}],\"map\":{\"3\":{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},\"2\":{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},\"1\":{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},\"0\":{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},\"4\":{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"}},\"testInt\":1234,\"testStr\":\"test\"}";
	private String complex_str2 = "{\"arr\":[{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"},{\"expired\":true,\"key\":\"key\",\"name\":\"name\",\"timeOut\":123456,\"value\":\"iii4\"}],\"map\":{\"3\":{\"beanId\":\"beanId\",\"beanName\":\"beanName4\"},\"2\":{\"beanId\":\"beanId\",\"beanName\":\"beanName4\"},\"1\":{\"beanId\":\"beanId\",\"beanName\":\"beanName4\"},\"0\":{\"beanId\":\"beanId\",\"beanName\":\"beanName4\"},\"4\":{\"beanId\":\"beanId\",\"beanName\":\"beanName4\"}},\"testInt\":1234,\"testStr\":\"test\"}";
	SimpleBean c;
	List arr;
	Map map;
	ComplexBean bean;
	TestBean test;

	@Before
	public void init() {
		c = new SimpleBean();
		c.setExpired(true);
		c.setKey("key");
		c.setName("name");
		c.setTimeOut(123456);
		c.setValue("value");

		test = new TestBean();
		test.setBeanId("beanId");
		test.setBeanName("beanName");

		arr = new ArrayList();
		for (int i = 0; i < 5; i++) {
			c.setValue("iii" + i);
			arr.add(c);
		}

		map = new HashMap(4);
		for (int i = 0; i < 5; i++) {
			test.setBeanName("beanName" + i);
			map.put(i + "", test);
		}

		bean = new ComplexBean();
		bean.setArr(arr);
		bean.setMap(map);
		bean.setTestInt(1234);
		bean.setTestStr("test");
	}

	// @Test
	public void jsonToJavaTest() throws Exception {
		// 将一个对象转换为json字符串
		SimpleBean cc = (SimpleBean) NewJsonUtil.jsonToJava(cache_str,
				SimpleBean.class);
		System.out.println(cc);
	}

	// @Test
	public void jsonToArrTest() throws Exception {
		// 将一个对象转换为json字符串
		SimpleBean[] cc = (SimpleBean[]) NewJsonUtil.jsonToArr(cache_arr,
				SimpleBean.class);
		for (SimpleBean b : cc)
			System.out.println(b);
	}

	//@Test
	public void jsonToListTest() throws Exception {
		// 将一个对象转换为json字符串
		List cc = (List) NewJsonUtil.jsonToList(cache_arr, SimpleBean.class);
		for (Object b : cc) {
			SimpleBean bb = (SimpleBean) b;
			System.out.println(bb);
		}
	}

	// @Test
	public void jsonToJava2Test() throws Exception {
		MorpherRegistry morpherRegistry = JSONUtils.getMorpherRegistry();
		Morpher dynaMorpher = new BeanMorpher(SimpleBean.class, morpherRegistry);
		Morpher dynaMorpher2 = new BeanMorpher(TestBean.class, morpherRegistry);
		morpherRegistry.registerMorpher(dynaMorpher2);
		morpherRegistry.registerMorpher(dynaMorpher);

		ComplexBean bean = (ComplexBean) NewJsonUtil.jsonToJava(complex_str2,
				ComplexBean.class);
		List<SimpleBean> output = new ArrayList();
		List arr = bean.getArr();
		Map m = bean.getMap();
		for (Iterator i = arr.iterator(); i.hasNext();) {
			// 使用注册器对指定DynaBean进行对象变换
			output.add((SimpleBean) morpherRegistry.morph(SimpleBean.class,
					i.next()));
		}

		for (Object o : m.entrySet()) {
			Entry en = (Entry) o;
			TestBean b = (TestBean) morpherRegistry.morph(TestBean.class,
					en.getValue());
			System.out.println(b);
		}

		for (Object o : output) {
			SimpleBean c = (SimpleBean) o;
			// PropertyUtils.getProperty(bean, name)-----可以直接输出对应的对象里面的属性.
			System.out.println(c);
		}
		System.out.println(bean.getTestInt() + ",," + bean.getTestStr() + ",,"
				+ bean.getArr() + ",," + bean.getMap());
	}
 
	// @Test
	public void objToJsonTest() throws Exception {
		// 将一个对象转换为json字符串
		Util.p(NewJsonUtil.javaToJson(c).toString());
	}

	// @Test
	public void arrToJsonTest() throws Exception {
		// 将一个对象转换为json字符串
		Util.p(NewJsonUtil.arrToJson(c).toString());
	}

}