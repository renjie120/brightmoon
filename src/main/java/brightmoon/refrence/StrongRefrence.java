package brightmoon.refrence;

/**
 * 强引用
 * 
 * @author lsq
 * 
 */
public class StrongRefrence {
	public static void main(String[] args) {
		StringBuffer buf = new StringBuffer("123");
		StringBuffer buf2 = buf;
		System.out.println(buf==buf2);//输出true
		buf2.append("hahha");
		
		System.out.println(buf==buf2);//还是输出true，因为都是指向同一个内存地址的强引用。==比较的是指向的堆
								      //空间地址是否正确.
		
		//强引用特点：
		//1.强引用可以直接访问对象
		//2.强引用指向的对象不会被系统回收，JVM宁愿抛出异常OOM
		//3.强引用会导致内存泄漏
		
		byte[] aa = new byte[4*1024*925];
		
	}
}
