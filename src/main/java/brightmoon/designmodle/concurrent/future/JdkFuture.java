package brightmoon.designmodle.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeoutException;

/**
 * jdk内置的future结构.
 * @author lsq
 *
 */
public class JdkFuture {
	public static void main(String[] args) throws InterruptedException,
			ExecutionException, TimeoutException {
		// 构造一个可以返回结果等待对象的多线程.FutureTask---表示有返回值的任务.
		FutureTask<String> future = new FutureTask<String>(new JdkRealData("a"));
		// 初始化一个线程池
		ExecutorService service = Executors.newFixedThreadPool(1);
		// 执行程序.
		service.submit(future);

		System.out.println("客户端请求完毕!");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//future可以设置超时时间。。。50L, TimeUnit.MILLISECONDS 
		//下面加上前面等待的2秒，一起有3秒时间.
		//System.out.println("数据结果：" + future.get(1,TimeUnit.SECONDS));
		
		System.out.println("数据结果：" + future.get());
	}
}
