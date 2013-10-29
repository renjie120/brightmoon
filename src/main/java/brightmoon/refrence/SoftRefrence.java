package brightmoon.refrence;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 软引用：
 *  
 * @author lsq
 *
 */
public class SoftRefrence {
	public  static void main(String[] args){
		MyObject obj = new MyObject();
		//创建一个软引用队列
		ReferenceQueue softQueue = new  ReferenceQueue<MyObject>();
		SoftReference<MyObject> softRef  = new SoftReference<MyObject>(obj,softQueue);
		
		new Thread(new CheckRefQueue(softQueue)).start();
		obj = null;
		System.gc();
		
		System.out.println("垃圾回收之后："+softRef.get());
		
		System.out.println("分配大内存，引起强迫GC");
		
		byte[] b = new byte[4*1024*1024*1024*1024];
		System.out.println("在分配大内存之后："+softRef.get());
	}
}

class CheckRefQueue implements Runnable{
	ReferenceQueue<MyObject> softQueue;
	CheckRefQueue(ReferenceQueue<MyObject> softQueue){
		this.softQueue = softQueue;
	}
	@Override
	public void run() {
		 Reference<MyObject> obj = null;
		 
		 try {
			obj = (Reference<MyObject>)softQueue.remove();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(obj!=null)
			System.out.println("软引用中的对象是："+obj.get());
	}
	
}