package brightmoon.designmodle.chain;

/**
 * 抽象处理者.
 * 
 * @author lsq
 * 
 */
abstract class Handler {
	protected Handler successor;

	/**
	 * 处理方法.
	 */
	public abstract void handleRequest();

	public void setSuccessor(Handler successor) {
		this.successor = successor;
	}

	/**
	 * 返回处理者
	 * 
	 * @return
	 */
	public Handler getSuccessor() {
		return successor;
	}
}

/**
 * 在具体处理对象里面继续处理.
 * @author lsq
 *
 */
class ConcreateHandler extends Handler {

	/**
	 * 如果找得到下一个处理对象，就
	 */
	@Override
	public void handleRequest() {
		if (getSuccessor() != null) {
			System.out.println("请求将传递到下一个对象进行处理");
			getSuccessor().handleRequest();
		} else {
			System.out.println("没有找到下一个对象，处理到此为止.");
		}
	}

}

public class ChainTest {
	private static Handler handler1, handler2;

	public static void main(String[] args) {
		handler1 = new ConcreateHandler();
		handler2 = new ConcreateHandler();

		handler1.setSuccessor(handler2);
		handler1.handleRequest();
	}
}
