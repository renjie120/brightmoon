package brightmoon.designmodle.concurrent.worker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Master-Worer模式：Master是主进程，维护一个worker进程队列，子任务队列，以及子结果集.Worker队列不同从任务队列中取要进行处理的
 * 子任务，然后将子任务处理的结果放到子结果集合中.
 * 
 * worker主要负责维护worker进程，并整合最终处理结果.
 * @author lsq
 *
 */
public class Master {
	//worker进程队列(存放多个worker进程（线程）)
	protected Queue<Object> workQueue = new ConcurrentLinkedQueue<Object>();
	//子任务队列
	protected Map<String, Thread> threadMap = new HashMap<String, Thread>();
	//子集合对象.
	protected Map<String, Object> resultMap = new ConcurrentHashMap<String, Object>();

	//判断队列中线程是否执行完毕.
	public boolean isComplete() {
		for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
			if (entry.getValue().getState() != Thread.State.TERMINATED)
				return false;
		}
		return true;
	}

	/**
	 * 构造主进程.
	 * @param w worker逻辑
	 * @param countWorker worker进程数量.
	 */
	public Master(Worker w, int countWorker) {
		//将worker进程和当前的进程队列，结果集关联起来....
		//设置主进程里面的队列为当前的这个队列.
		w.setWorkQueue(workQueue);
		//设置结果集.
		w.setResultMap(resultMap);
		//设置线程集合----也就是带处理的线程集合.
		for (int i = 0; i < countWorker; i++) {
			threadMap.put(Integer.toString(i),
					new Thread(w, Integer.toString(i)));
		}
	}

	//提交一个任务
	public void submit(Object obj) {
		workQueue.add(obj);
	}

	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	
	//开始运行所有的work线程
	public void execute(){
		for(Map.Entry<String, Thread> entry:threadMap.entrySet()){
			entry.getValue().start();
		}
	}
}
