package brightmoon.designmodle.memento;

class Originator {
	private String state;

	public Memento createMemento() {
		return new Memento(state);
	}

	public void restoreMemento(Memento memento) {
		this.state = memento.getState();
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
		System.out.println("当前状态：" + this.state);
	}
}

class Memento {
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

class Caretaker {
	private Memento memento;

	public Memento retrieveMemento() {
		return this.memento;
	}

	public void saveMemento(Memento memento) {
		this.memento = memento;
	}
}

/**
 * 备忘录模式.
 * 
 * @author lsq
 * 
 */
public class MementoTest {
	private static Originator o = new Originator();
	private static Caretaker c = new Caretaker();

	public static void main(String[] args) {
		//设置发起人对象的状态
		o.setState("On");
		//创建备忘录对象，将发起人对象的状态保存起来
		c.saveMemento(o.createMemento());
		//修改发起人对象的状态
		o.setState("Off");
		//恢复发起人对象的状态.
		o.restoreMemento(c.retrieveMemento());
	}
}
