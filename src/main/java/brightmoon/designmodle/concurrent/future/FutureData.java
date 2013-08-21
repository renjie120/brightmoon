package brightmoon.designmodle.concurrent.future;

/**
 * 快速返回一个RealData的包装对象.
 * 调用FutureData的getResult方法会阻塞，直到有结果返回为止.
 * 这是最关键的地方，是真实数据的代理，封装了获取realData的等待过程.
 * @author lsq
 *
 */
public class FutureData implements Data {
	protected RealData realData = null;
	protected boolean isReady = false;

	/**
	 * 方法是同步。
	 * 因为realData的构造过程很慢，所以会在这里造成阻塞.
	 * 一旦构造完毕就会进入本方法的第一行！！！
	 * @param data
	 */
	public synchronized void setResult(RealData data) {
		System.out.println("future对象开始进行设置结果对象......");
		if (isReady)
			return;
		this.realData = data;
		isReady = true;
		notifyAll();
	}

	/**
	 * 一旦准备完毕了，就会激活等待的对象，返回结果.
	 */
	@Override
	public synchronized String getReuqest() {
		/**
		 * 这里的future对象一直在等待，直接真实的对象加载完毕才返回实际结果
		 */
		while (!isReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("真实数据结果生成了，通过future对象返回结果");
		return realData.result;
	}

}
