package brightmoon.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用信号量进行控制.
 * @author lsq
 *
 */
public class XinhaoliangDemo extends Thread {
	Semaphore s;
	private int id;

	public XinhaoliangDemo(int i, Semaphore s) {
		this.id = i;
		this.s = s;
	}

	public void run() {
		try {
			if (s.availablePermits() > 0)
				System.out.println("排队成功，可以继续操作!");
			else
				System.out.println("进入排队中，请等待....");
			s.acquire();

			System.out.println("开始进行操作。。。");
			Thread.sleep(1000);
			System.out.println("结束操作...");
			s.release();
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		ExecutorService list = Executors.newCachedThreadPool();
		Semaphore xinhaoliang = new Semaphore(2);
		for(int i=0;i<10;i++)
			list.submit(new XinhaoliangDemo(i, xinhaoliang));
	}
}
