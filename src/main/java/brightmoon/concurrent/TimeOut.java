package brightmoon.concurrent;

import java.util.Timer;
import java.util.TimerTask;

public class TimeOut extends Timer {
	public TimeOut(int delay, final String str) {
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println(str);
				System.exit(0);
			}
		}, delay);
	}

	public static void main(String[] args) {
		new TimeOut(1000, "超时了！");
	}
}
