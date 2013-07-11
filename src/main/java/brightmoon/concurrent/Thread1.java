package brightmoon.concurrent;

public class Thread1 extends Thread {
	private static int count;
	private int number = count++;
	private String name;

	public Thread1(String name) {
		this.name = name;
	}

	public String toString() {
		return "线程" + number + ":" + name;
	}

	public void run() {
		System.out.println(this + "--开始执行"); 
	}
}