package brightmoon.designmodle.observer;

import java.util.Observable;
import java.util.Observer;

public class MailObserver implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("MailObserver:"+o);
		System.out.println("发送邮件的观察者被触发");
	}

}
