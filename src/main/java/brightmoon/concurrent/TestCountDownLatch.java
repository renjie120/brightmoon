package brightmoon.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCountDownLatch {
	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch begin = new CountDownLatch(1);
		final CountDownLatch end = new CountDownLatch(10);
		final ExecutorService exec = Executors.newFixedThreadPool(10);

		for (int i = 0; i < 10; i++) {
			final int NO = i + 1;
			Runnable run = new Runnable() {
				public void run() {
					try {
						System.out.println("还没有开始....");
						begin.await();// 等待开始的哨声倒计时为0 .
						System.out.println("编码" + NO + "开始准备就绪!");
						Thread.sleep((long) Math.random() * 1000);
						System.out.println("编码" + NO + "准备就绪!");
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						end.countDown();
					}
				}
			};
			exec.submit(run);
		}
		Thread.sleep(1000);
		System.out.println("游戏开始");
		begin.countDown();
		end.await();// 等待最后一个end里面的统计数结束为0，就退出等待...
		System.out.println("游戏结束");
		exec.shutdown();
	}
}
