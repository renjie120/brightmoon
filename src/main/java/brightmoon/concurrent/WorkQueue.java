package brightmoon.concurrent;

import java.util.LinkedList;

public class WorkQueue {
	public static void main(String[] args) {
		WorkQueue test = new WorkQueue(5);
		for (int i = 0; i < 10; i++) {
			final int ii = i;
			test.execute(new Runnable() {
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程:" + ii);
				}
			});
		}
	}

	private final int nTheads;
	private final PoolWorker[] threads;
	public final LinkedList queue;

	public WorkQueue(int nTheads) {
		this.nTheads = nTheads;
		queue = new LinkedList();
		threads = new PoolWorker[nTheads];
		for (int i = 0; i < nTheads; i++) {
			threads[i] = new PoolWorker();
			threads[i].start();
		}
	}

	public void execute(Runnable r) {
		synchronized (queue) {
			queue.addLast(r);
			queue.notify();
		}
	}

	/**
	 * 存放在消息队列里面的每一个处理单元。在检查队列，一旦发现队列不为空就处理，负责就等待.
	 * 处理完毕runnable之后，将继续进行查询队列---因为在一个死循环while中.
	 * 
	 * @author lsq
	 * 
	 */
	private class PoolWorker extends Thread {
		public void run() {
			Runnable r;
			while (true) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException i) {

						}
					}
					r = (Runnable) queue.removeFirst();
				}
				try {
					r.run();
				} catch (RuntimeException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
