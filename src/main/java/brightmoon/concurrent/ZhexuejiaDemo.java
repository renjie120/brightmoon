package brightmoon.concurrent;

import java.util.Random;

public class ZhexuejiaDemo {
	public static void main(String[] args) {
		Kuaizi left = new Kuaizi(), right = new Kuaizi(), first = left;
		int zhexuejiaCount = 5;
		Zhexuejia[] p = new Zhexuejia[zhexuejiaCount];
		Zhexuejia.ponder = 5;
		for (int i = 0; i < zhexuejiaCount - 1; i++) {
			p[i] = new Zhexuejia(left, right);
			left = right;
			right = new Kuaizi();
		}
		//下面的最后一个哲学家如果先获取左边的筷子，再获取之前的第一个筷子的话，就形成了有序等待，在等待时间ponder设置很小的情况下就形成了死锁。
		//取消死锁的方法很简单，打破有序等待： new Zhexuejia(first ,left);即可.
		p[zhexuejiaCount - 1] = new Zhexuejia(left, first);
		new TimeOut(10000, "程序结束了");
	}
}

class Kuaizi {
	private static int count = 0;
	private int number = count++;

	public String toString() {
		return "当前筷子:" + number;
	}
}

class Zhexuejia extends Thread {
	private Kuaizi left;
	private Kuaizi right;
	private static int count = 0;
	private int number = count++;
	static int ponder = 0;// 思考因子

	public Zhexuejia(Kuaizi l, Kuaizi r) {
		this.left = l;
		this.right = r;
		start();
	}

	public String toString() {
		return "哲学家:" + number;
	}

	private void waitKuaizi() {
		if (ponder > 0)
			try {
				System.out.println(this + "开始思考");
				Thread.sleep(new Random().nextInt(ponder));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}

	private void eat() {
		synchronized (left) {
			System.out.println(this + "拿住左边的筷子:" + left + ",在等待右边的筷子" + right);
			synchronized (right) {
				System.out.println(this + "拿住右边的筷子:" + right + "开始吃东西");
			}
		}
	}

	public void run() {
		//多线程的代码都放在死循环里面！
		while (true) {
			waitKuaizi();
			eat();
		}
	}
}
