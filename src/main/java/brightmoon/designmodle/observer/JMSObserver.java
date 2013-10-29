package brightmoon.designmodle.observer;

import java.util.Observable;
import java.util.Observer;

public class JMSObserver implements Observer {

	public void update(Observable o, Object arg) {
		System.out.println("JMSObserver:" + o);
		System.out.println("发送消息给JMS服务器的观察者被触发");
	}

}
