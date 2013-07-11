package brightmoon.concurrent;

import java.util.concurrent.Executor;

/**
 * 基础的executor的使用.
 * @author lsq
 *
 */
public class ExecutorBase implements Executor {
	public void execute(Runnable command) {
		new Thread(command).run();
	}

	public static void main(String[] args) {
		ExecutorBase base = new ExecutorBase();
		base.execute(new Thread1("test1"));
		base.execute(new Thread1("test2"));
		base.execute(new Thread1("test3"));
		base.execute(new Thread1("test4"));
		base.execute(new Thread1("test5"));
		base.execute(new Thread1("test6"));
	}
} 