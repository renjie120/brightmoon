package brightmoon.util.concurrent;


/**
 * The implement of Future interface
 * @author Administrator
 *
 */
class FutureTask implements Future<Object> {
	
	private Object returnObj = null;
	private Thread executeThread = null;

	/**
	 * 从这里看到作者使用了join.
	 */
	@Override
	public Object get() {
		if (returnObj == null) {
			try {
				executeThread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return returnObj;
	}
	
	public void set(Object obj){
		returnObj = obj;
	}

	/**
	 * @return the executeThread
	 */
	public Thread getExecuteThread() {
		return executeThread;
	}

	/**
	 * @param executeThread the executeThread to set
	 */
	public void setExecuteThread(Thread executeThread) {
		this.executeThread = executeThread;
	}
	
	

}
