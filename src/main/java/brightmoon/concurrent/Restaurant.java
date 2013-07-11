package brightmoon.concurrent;

public class Restaurant {
	Order order;// 资源

	public static void main(String[] args) {
		Restaurant r = new Restaurant();
		Waiter w = new Waiter(r);// 服务员是消费者
		Chef c = new Chef(r, w);// 厨师是生产者 
	}
}

class Order {
	private static int i = 0;
	private int count = i++;

	public Order() {
		if (count == 10) {
			System.out.println("原材料已经做完了，可以收工了");
			System.exit(0);
		}
	}

	public String toString() {
		return "第" + count + "份菜单";
	}
}

class Waiter extends Thread {
	private Restaurant r;

	public Waiter(Restaurant r) {
		this.r = r;
		start();
	} 
	
	public void run() {
		while (true) {// 多线程程序一般在一个死循环里面
			while (r.order == null) {
				synchronized (this) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			System.out.println("开始上菜:" + r.order);
			r.order = null;
		}
	}
}

class Chef extends Thread {
	private Restaurant r;
	private Waiter w;

	public Chef(Restaurant r, Waiter w) {
		this.r = r;
		this.w = w;
		start();
	}

	public void run() {
		while (true) {//模仿生产者进行生产元素的过程!
			while (r.order == null) {
				r.order = new Order();
				System.out.println("做完菜了");
				synchronized (w) {
					w.notify();
				}
			}
			try {
				Thread.sleep(1000);
				System.out.println("稍微休息一下");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
