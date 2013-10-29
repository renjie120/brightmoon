package brightmoon.designmodle.concurrent.future;

public class Client {
	public static void main(String[] args) {
		Client c = new Client();
		//请求一个比较耗时的方法，会立马返回一个future对象.
		Data d = c.request("name");
		System.out.println("客户端开始请求......");
		
		//下面模拟的是进行其他的业务操作.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		}
		
		//最后在需要使用结果的时候，才会慢慢的加载.
		System.out.println("数据结果是："+d.getReuqest());
	}

	public Data request(final String name) {
		final FutureData future = new FutureData();
		//启动多线程进行负责的运算.
		new Thread() {
			public void run() {
				//因为很复杂的逻辑运算都在realData的构造函数中，所以这里
				//放在多线程里面，一旦构造完毕，就会立即通过future返回result.
				RealData data = new RealData(name);
				future.setResult(data);
			}
		}.start();
		return future;
	}
}
