package brightmoon.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 可执行对象返回结果的演示demo.
 * @author lsq
 *
 */
public class CallableReturnFutureDemo {
	public void test() throws Exception {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		// Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
		// 计算完成后只能使用 get 方法来获取结果，如有必要，计算完成前可以阻塞此方法。取消则由cancel 方法来执行。
		// 还提供了其他方法，以确定任务是正常完成还是被取消了。一旦计算完成，就不能再取消计算。
		Callable<String> call = new Callable<String>() {
			public String call() throws Exception {
				Thread.sleep(3000);
				return "从多线程返回一些信息";
			}
		};
		Future<String> task = exec.submit(call);
		Thread.sleep(1000);
		System.out.println("做一些重要的事情");
		System.out.println(task.get());
		exec.shutdown();
	}

	public void test2() throws Exception {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		// 如果是直接将runnable对象封装为callable对象的话，future就是返回的结果为null.
		Callable c = Executors.callable(new Thread1("test1"));
		Future<Object> f = exec.submit(c);
		System.out.println(f.get());
	}

	String[] urls = { "http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com",
			"http://www.baidu.com", "http://www.baidu.com" };

	public void testFixedThreadPool() throws Exception {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		long s = System.currentTimeMillis();

		for (int i = 0; i < urls.length; i++) {
			String url = urls[i];
			Future<String> task = exec.submit(new CallableDemo(url,
					"d:\\test\\" + url.substring(url.indexOf(":") + 2) + i
							+ ".txt"));
			System.out.println(i + "---" + task.get());
		}
		long e = System.currentTimeMillis();
		System.out.println("testFixedThreadPool耗时:" + (e - s));
		exec.shutdown();
	}

	/**
	 * 测试使用newCachedThreadPool创建线程池，
	 * @throws Exception
	 */
	public void testCachedThreadPool() throws Exception {
		final ExecutorService exec = Executors.newCachedThreadPool();
		long s = System.currentTimeMillis();

		for (int i = 0; i < urls.length; i++) {
			String url = urls[i];
			Future<String> task = exec.submit(new CallableDemo(url,
					"d:\\test\\" + url.substring(url.indexOf(":") + 2) + i
							+ ".txt"));
			System.out.println(i + "---" + task.get());
		}
		long e = System.currentTimeMillis();
		System.out.println("testCachedThreadPool耗时:" + (e - s));
		exec.shutdown();
	}

	public static void main(String[] args) throws Exception {
		CallableReturnFutureDemo demo = new CallableReturnFutureDemo();
		demo.testFixedThreadPool();

		demo.testCachedThreadPool();
	}
}
