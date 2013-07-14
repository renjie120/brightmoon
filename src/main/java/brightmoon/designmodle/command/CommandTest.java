package brightmoon.designmodle.command;

/**
 * 命令接口.
 * 
 * @author lsq
 * 
 */
interface Command {
	void execute();
}

/**
 * 请求者角色.
 * 
 * @author lsq
 * 
 */
class Invoker {
	private Command command;

	public Invoker(Command command) {
		this.command = command;
	}

	public void execute() {
		command.execute();
	}
}

/**
 * 接收者角色.
 * 
 * @author lsq
 * 
 */
class Receiver {
	public Receiver() {

	}

	public void action() {
		System.out.println("开始执行action");
	}
}

/**
 * 具体命令对象.
 * 
 * @author lsq
 * 
 */
class ConcreteCommand implements Command {
	private Receiver receiver;

	/**
	 * 构造函数中含有命令的接收者.
	 * 
	 * @param receiver
	 */
	public ConcreteCommand(Receiver receiver) {
		this.receiver = receiver;
	}

	public void execute() {
		receiver.action();
	}

}

public class CommandTest {
	public static void main(String[] args) {
		// 定义命令的接收者
		Receiver receiver = new Receiver();
		// 初始化一个命令，并同时传进去命令的接收者。
		Command command = new ConcreteCommand(receiver);
		// 创建一个命令执行者，传入命令对象
		Invoker invoker = new Invoker(command);
		// 执行命令!
		invoker.execute();
	}
}
