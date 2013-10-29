package brightmoon.designmodle.bridge;

/**
 * 邮件端信息的实现类.
 * @author lsq
 *
 */
public class MessageEmail implements MessageImplementor {

	public void send(String message, String toUser) {
		System.out.println("使用邮件短信的方式发送信息:" + message + "给" + toUser);
	}

}
