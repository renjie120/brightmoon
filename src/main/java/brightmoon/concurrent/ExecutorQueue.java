package brightmoon.concurrent;

import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class ExecutorQueue implements Executor {
	final Queue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
	final Executor executor;
	Runnable active;

	public ExecutorQueue(Executor e) {
		this.executor = e;
	}

	public synchronized void execute(final Runnable r) {
		// 添加到队列中.
		tasks.offer(new Runnable() {
			public void run() {
				try {
					r.run();
				} finally {
					scheduleNext();
				}
			}
		});
		if (active == null)
			scheduleNext();
	}

	// 移除得到队列里面的第一个元素
	protected synchronized void scheduleNext() {
		if ((active = tasks.poll()) != null)
			executor.execute(active);
	}

	public static void main(String[] args) {
		Executor e1 = new Executor() {
			public void execute(Runnable command) {
				System.out.println("在执行程序1中，当前准备执行线程" + command);
				new Thread(command).start();
			}
		};
		// ExecutorQueue my = new ExecutorQueue(e); 
		e1.execute(new Thread1("test1"));
		e1.execute(new Thread1("test2"));
		e1.execute(new Thread1("test3"));
		e1.execute(new Thread1("test4"));

	}
}
