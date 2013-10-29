package brightmoon.designmodle.concurrent.worker;

import java.util.Map;
import java.util.Queue;

/**
 * Worker进程.
 * @author lsq
 *
 */
public class Worker implements Runnable {
	protected Queue<Object> workQueue;
	protected Map<String, Object> resultMap;

	/**
	 * 主要的执行过程，就是从任务队列中取出值，然后进行处理,将结果返回到结果集合中.
	 */
	@Override
	public void run() {
		while (true) {
			Object input = workQueue.poll();
			if (input == null)
				break;
			Object handle = handle(input);
			resultMap.put(Integer.toString(input.hashCode()), handle);
		}
	}

	// 在子类中具体实现.
	public Object handle(Object input) {
		return input;
	}

	public void setWorkQueue(Queue<Object> workQueue) {
		this.workQueue = workQueue;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
