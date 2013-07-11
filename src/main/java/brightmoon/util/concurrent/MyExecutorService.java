package brightmoon.util.concurrent;

/***
 * Imitate ExecutorService
 * 
 * @author Administrator
 * 
 */
public class MyExecutorService {

	private static MyExecutorService es = null;
	private Object returnCall = null;

	private MyExecutorService() {
	}

	public static MyExecutorService newInstance() {
		if (es == null) {
			es = new MyExecutorService();
		}
		return es;
	}
 
	/**
	 * 作者将callable对象直接包装在一个thread对象中，然后进行join到主线程,
	 * 同时将call的结果设置进入到feature.
	 * @param call
	 * @return
	 */
	public Future<Object> submit(final Callable<Object> call) {
		final FutureTask future = new FutureTask();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					returnCall = call.call();
					future.set(returnCall);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		thread.start();
		future.setExecuteThread(thread);
		return future;
	}
}
