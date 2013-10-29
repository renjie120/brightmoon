package brightmoon.designmodle.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 具体主题.
 * @author lsq
 *
 */
public class Subject extends Observable {
	public void doBusiness() {
		if (true) {
			super.setChanged();
		}
		notifyObservers("现在还没有参数");
	}

	public static void main(String[] args) {
		//被观察者对象
		Subject subject = new Subject();
		//观察者
		Observer mailObserver = new MailObserver();
		Observer jmsObserver = new JMSObserver();
		//添加观察者到被观察者里面的列表
		subject.addObserver(mailObserver);
		subject.addObserver(jmsObserver);
		subject.doBusiness(); 
	}
}
