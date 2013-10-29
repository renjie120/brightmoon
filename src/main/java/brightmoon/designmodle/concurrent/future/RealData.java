package brightmoon.designmodle.concurrent.future;

/**
 * 真实的数据来源。
 * 可以比较耗时的操作.
 * @author lsq
 *
 */
public class RealData implements Data {
	protected final String result;

	/**
	 * 在构造函数里面就进行耗时的数据操作！！
	 * @param para
	 */
	public RealData(String para) {
		System.out.println("进入真实数据的生成");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 10; i++) {
			buf.append(para);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("真实数据的生成完毕");
		result = buf.toString(); 
	}

	@Override
	public String getReuqest() { 
		return result;
	}

}
