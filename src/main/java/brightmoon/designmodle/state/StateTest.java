package brightmoon.designmodle.state;

/**
 * 环境角色. 定义客户端感兴趣的接口，并且保留一个具体状态的实例.
 * 
 * @author lsq
 * 
 */
class Context {
	private State state;

	public void sampleOperation() {
		state.sampleOperation();
	}

	public void setState(State state) {
		this.state = state;
	}
}

/**
 * 抽象状态角色.用以封装环境对象的特定状态所对应的行为.
 * 
 * @author lsq
 * 
 */
interface State {
	void sampleOperation();
}

/**
 * 具体状态角色. 每一个具体状态类都实现环境的一个状态对应的行为.
 * 
 * @author lsq
 * 
 */
class ConcreteState implements State {
	public void sampleOperation() {
		System.out.println("具体状态1下面的操作");
	}
}

class ConcreteState2 implements State {
	public void sampleOperation() {
		System.out.println("具体状态2下面的操作");
	}
}

public class StateTest {
	public static void main(String[] args) {
		Context context = new Context();
		State state = new ConcreteState();
		context.setState(state); 
		context.sampleOperation();

		State state2 = new ConcreteState2();
		context.setState(state2); 
		context.sampleOperation();
	}
}
