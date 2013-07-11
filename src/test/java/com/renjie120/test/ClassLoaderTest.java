package com.renjie120.test;

import java.util.Random;

import org.junit.Test;

public class ClassLoaderTest {
	//@Test
	public void classLoader() throws ClassNotFoundException {
		// 下面是根加载器,输出null
		Class clazz1 = Class.forName("java.lang.String");
		System.out.println(clazz1.getClassLoader());

		// sun.misc.Launcher$AppClassLoader@addbf1 项目是应用类加载器
		Class clazz2 = Class.forName("com.renjie120.test.C");
		System.out.println(clazz2.getClassLoader());
		
		//下面输出的是1，1
		C c1 = C.getInstance();
		System.out.println("var1="+c1.var1);
		System.out.println("var2="+c1.var2);
		
		//下面输出的是1，0为什么？ 因为类初始化的顺序问题，静态变量先复制为
		//默认值，然后再一起赋值为设置的初始化值。见下面的分析。
		D c2 = D.getInstance();
		System.out.println("var1="+c2.var1);
		System.out.println("var2="+c2.var2);
		
	}
	
	//@Test
	public void initialTest(){
		//下面输出就是2
		System.out.println(E.x);
		
		//下面这种情况，又发生了新的事情！！输出了static里面的语句！！
		//因为没有使用final！！！
		System.out.println(E2.x);
		
		//下面会调用static代码块里面的语句，然后再输出。为什么？？
		System.out.println(F.x);
	}
	
	static{
		System.out.println("Test static block");
	}
	
	//@Test
	public void initialTest2(){
		//主动调用类的时候，会使类进行静态变量初始化。
		 System.out.println(Child.b);
		 /**
		  * 输出结果：
		  * Test static block
			parent static block
			child static block
			4
		  */
	}
	
	//@Test
	public void test5Test(){
		Test5.test();
		/**
		 * 打印结果：
		 * Test5 static block
		 * ---------------
		 * parent2 static block
		 * 4
		 * child2 static block
		 * 5
		 */
	}
	
	//@Test
	public void test6Test(){
		//测试对test6的进行初始化调用.
		//测试这点：没有对类主动使用，就不会进行初始化
		Test6.test();
	}
	
	@Test
	public void test7Test() throws ClassNotFoundException{
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		//进行loader.loadClass并不会对类进行主动调用，就不会引起类的初始化.loadClass仅仅完成加载步骤。
		Class<?> clazz = loader.loadClass("com.renjie120.test.Test6");
		System.out.println("--------------");
		//使用Class.forName就会引起类的主动调用！引起类主动调用的六种情况！
		/*  1.创建类的实例
			2.访问某个类，或者接口的静态变量，或者对该静态变量赋值
			3.调用类的静态方法
			4 反射 Class.forName
			5.初始化一个类的子类，看作对父类的主动使用
			6.java虚拟机启动时被标记为启动类的类（输入命令例如：java TestABC.java）
		*/
		clazz = Class.forName("com.renjie120.test.Test6");
		/**
		 * 结果：
		 * ------------
		 * Test6 static block
		 */
	}
}

class E{
	//
	public final static int x = 6/3;//这里是一个常量，仅仅调用类的变量，不会导致类的初始化过程
	static{
		System.out.println("E staitc block");
	}  
}

class E2{
	//
	public static int x = 6/3;//这里是一个常量，仅仅调用类的变量，不会导致类的初始化过程
	static{
		System.out.println("E staitc block");
	}  
}


class F{
	public final static int x=new Random().nextInt(100);//这里是一个变量，需要在运行时确定，需要进行类的初始化，这样就调用了静态代码块的部分。
	static{
		System.out.println("F staitc block");
	}  
}
class C {
	public static int var1;
	public static int var2 = 0; 
	private static C c = new C();
	 
	/**
	 * 上面等价于：
	 * public static int var1=0;
	 * public static int var2=0;
	 * private static C=null;
	 * static{
	 * 	var2=0;
	 *  c=new C();
	 * }
	 */

	private C() {
		var1++;
		var2++;
	}

	public static C getInstance() {
		return c;
	}
}

class D {

	private static D c = new D();

	public static int var1;
	public static int var2 = 0;
	/**
	 * 上面等价于：  
	 * private static C=null;
	 * public static int var1=0;
	 * public static int var2=0;
	 * static{ 
	 *  c=new C();
	 *  var2=0;
	 * }
	 */
	private D() {
		var1++;
		var2++;
	}

	public static D getInstance() {
		return c;
	}
}


class Parent{
	static int a=3;
	static{
		System.out.println("parent static block");
	}
}

class Child extends Parent{
	static int b=4;
	static{
		System.out.println("child static block");
	}
}

class Parent2{
	static int a=4;
	static{
		System.out.println("parent2 static block");
	}
}

class Child2 extends Parent2{
	static int b=5;
	static{
		System.out.println("child2 static block");
	}
}

class Test5{
	static {
		System.out.println("Test5 static block");
	}
	
	public static void test(){
		//这里虽然是定义了parent2，但是没有主动使用，所以不会主动进行
		//parent2的初始化！！
		Parent2 parent; 
		System.out.println("--------------");
		parent = new Parent2();
		System.out.println(Parent2.a);
		//对子类的主动使用，会主动导致父类的主动使用，进而初始化！
		System.out.println(Child2.b);
	}
	
}

class Parent3{
	public static int  a = 3;
	static{
		System.out.println("进行parent3的static模块");
	}
	public static void doSomething(){
		System.out.println("parent3 do something...");
	}
}


class Child3 extends Parent3{
	static{
		System.out.println("child3 的static模块");
	}
}

class Test6{
	static {
		System.out.println("Test6 static block");
	}
	
	public static void test(){
		//这里虽然调用了child3的a,但是a是在基类parent3里面定义的，不是主动
		//使用，所以就不会进行初始化。
		System.out.println(Child3.a);
		Child3.doSomething();
		//输出：
		/**
		 * Test6 static block
		 * 进行parent3的static模块
		 * 3
		 * parent3 do something...
		 */
	}
	
}
