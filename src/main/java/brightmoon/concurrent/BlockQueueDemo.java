package brightmoon.concurrent;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 典型的阻塞队列用于生产者和消费者模式.
 * @author lsq
 *
 */
public class BlockQueueDemo {
	public static void main(String[] args) {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
		Producer p = new Producer("工厂1", queue);
		Producer p2 = new Producer("工厂2", queue);
		Customer c = new Customer(queue);
		for (int i = 0; i < 10; i++)
			new Thread(p).start();
		for (int i = 0; i < 10; i++)
			new Thread(p2).start();
		for (int i = 0; i < 20; i++)
			new Thread(c).start();
	}
}

class Customer implements Runnable {
	private BlockingQueue<String> queue;

	public Customer(BlockingQueue<String> qeueue) {
		this.queue = qeueue;
	}

	public void run() {
		try {
			String uuid = queue.take();
			System.out.println("得到产品进行消费:" + uuid);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class Producer implements Runnable {
	private BlockingQueue<String> queue;
	private String name;

	public Producer(String name, BlockingQueue<String> queue) {
		this.queue = queue;
		this.name = name;
	}

	public void run() {
		String uuid = UUID.randomUUID().toString();
		try { 
			queue.put(uuid);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(name + "生产了一个元素:" + uuid);
	}
}