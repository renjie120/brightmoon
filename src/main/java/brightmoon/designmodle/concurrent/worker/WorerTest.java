package brightmoon.designmodle.concurrent.worker;

import java.util.Map;
import java.util.Set;

/**
 * 计算一个数字的立方.
 * 
 * @author lsq
 * 
 */
public class WorerTest extends Worker {
	public Object handle(Object obj) {
		Integer i = (Integer) obj;
		return test(i);
	}

	public static void main(String[] args) {
		int max = 10000000;
		long start = System.currentTimeMillis();
		Master master = new Master(new WorerTest(), 10);
		for (int i = 0; i < max; i++)
			master.submit(i);

		master.execute();
		int result = 0;
		Map<String, Object> resultMap = master.getResultMap();
		while (resultMap.size() > 0 || !master.isComplete()) {
			Set<String> keys = resultMap.keySet();
			String key = null;
			for (String k : keys) {
				key = k;
				break;
			}
			Integer i = null;
			if (key != null)
				i = (Integer) resultMap.get(key);
			if (i != null)
				result += i;
			if (key != null)
				resultMap.remove(key);
		}

		System.out.println("结果是:" + result + ",耗时："
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		result = 0;
		for (int i = 0; i < max; i++)
			result += test(i);
		System.out.println("结果是:" + result + ",耗时："
				+ (System.currentTimeMillis() - start));

	}

	public static int test(int i) {
		return (i - 1) * i * (i + 1);
	}
}
