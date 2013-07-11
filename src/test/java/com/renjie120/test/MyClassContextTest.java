package com.renjie120.test;

import org.junit.Test;

import brightmoon.context.ITest;
import brightmoon.context.MyClass;

public class MyClassContextTest {
	/**
	 * 测试模拟的一个spring容器的类，测试classoader的功能.
	 * @throws Exception
	 */
	@Test
	public void mySpringTest() throws Exception {  
		MyClass cls = MyClass.getInstance();
		ITest test1 = cls.getService("TEST1");
		ITest test2 = cls.getService("TEST2");
		ITest test3 = cls.getService("TEST3");
		System.out.println(test1.sayHello());
		System.out.println(test2.sayHello());
		System.out.println(test3.sayHello());
	} 
}
