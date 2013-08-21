package brightmoon.designmodle.concurrent.future;

import java.util.concurrent.Callable;

/**
 * 使用jdk多线程包里面的可执行对象.
 * @author lsq
 *
 */
public class JdkRealData implements Callable<String> {
	private String para;

	public JdkRealData(String para) {
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		System.out.println("进入真实数据的生成");
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 30; i++) {
			buf.append(para);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("真实数据的生成完毕");
		return buf.toString();
	}

}