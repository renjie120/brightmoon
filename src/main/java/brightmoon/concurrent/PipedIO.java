package brightmoon.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;

/**
 * 使用多线程演示关于管道输入输出的io方法.
 * @author lsq
 *
 */
public class PipedIO {
	public static void main(String[] args) throws IOException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender); 
		sender.start();
		receiver.start();
		new TimeOut(10000,"程序结束!");
//		new Timer().schedule(new TimerTask() {
//			@Override
//			public void run() {
//				System.out.println(str);
//				System.exit(0);
//			}
//		}, delay);
	}
}

class Receiver extends Thread {
	private PipedReader in;

	public Receiver(Sender s) throws IOException {
		this.in = new PipedReader(s.getWriter());
	}

	public void run() {
		try {
			while (true) {
				System.out.println("Reader:" + (char) in.read());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Sender extends Thread {
	private Random rand = new Random();
	private PipedWriter out = new PipedWriter();

	public PipedWriter getWriter() {
		return out;
	}

	public void run() {
		while (true) {
			for (char c = 'A'; c <= 'z'; c++) {
				try {
					out.write(c);
					sleep(rand.nextInt(100));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
}