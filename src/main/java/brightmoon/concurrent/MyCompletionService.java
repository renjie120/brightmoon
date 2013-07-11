package brightmoon.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletionService：已完成任务队列.
 * 会将已经完成的任务按照顺序，然后再take出来。
 * @author lsq
 *
 */
public class MyCompletionService implements Callable<String> {
	private static int count = 0;
	private int number = count++;
	private Random random = new Random();

	public String toString() {
		return "当前任务:" + number;
	}

	/**
	 * 这里的执行等待时间为随机产生的，不会一致.因此结束的时间会有近有远.
	 */
	public String call() throws Exception {
		int time = random.nextInt(10) * 100;
		Thread.sleep(time);
		return this + ":执行了" + time;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService service = Executors.newCachedThreadPool();
		//注意下面的实例化CompletionService的方式，ExecutorCompletionService的参数是一个连接池.
		CompletionService<String> completionService = new ExecutorCompletionService<String>(service);
		for(int i=0;i<10;i++)
			completionService.submit(new MyCompletionService());
		
		//下面得到结果自然是按照顺序输出的了.
		for(int i=0;i<10;i++)
			System.out.println(completionService.take().get());
		
		//shutdown的函数是顺序执行一遍全部的线程，然后不再接受新的线程.
		service.shutdown();
	}
}
