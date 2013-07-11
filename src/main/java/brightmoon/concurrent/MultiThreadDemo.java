package brightmoon.concurrent;

public class MultiThreadDemo {

	public static void main(String[] args) {
		new InnerRunnable("test");

		new InnerRunnable2("test2");

		new MethodThread("test3").runThread();
	}
}

/**
 * 使用实现接口的内部类的方式实现的多线程.
 * 
 * @author lsq
 * 
 */
class InnerRunnable {
	private int countDown = 5;
	private Inner inner;

	public InnerRunnable(String str) {
		inner = new Inner(str);
	}

	private class Inner implements Runnable {
		Thread t;

		Inner(String name) {
			t = new Thread(this, name);
			t.start();
		}

		public void run() {
			while (true) {
				System.out.println(this);
				if (--countDown == 0)
					return;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public String toString() {
			return t.getName() + ":" + countDown;
		}
	}
}

/**
 * 使用继承Thread子类的内部类的方式实现的多线程.
 * 
 * @author lsq
 * 
 */
class InnerRunnable2 {
	private int countDown = 5;
	private Thread inner;

	public InnerRunnable2(String str) {
		inner = new Thread(new Runnable() {
			public void run() {
				while (true) {
					System.out.println(this);
					if (--countDown == 0)
						return;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			public String toString() {
				return Thread.currentThread().getName() + ":" + countDown;
			}
		});

		inner.start();
	}
}

/**
 * 在方法内部启动一个多线程
 * 
 * @author lsq
 * 
 */
class MethodThread {
	private int countDown = 5;
	private Thread t;
	private String name;

	public MethodThread(String name) {
		this.name = name;
	}

	public void runThread() {
		if (t == null) {
			t = new Thread(name) {
				public void run() {
					while (true) {
						System.out.println(this);
						if (--countDown == 0)
							return;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				public String toString() {
					return getName() + ":" + countDown;
				}
			};

			t.start();
		}
	}
}