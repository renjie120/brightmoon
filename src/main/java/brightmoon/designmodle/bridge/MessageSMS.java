package brightmoon.designmodle.bridge;

/**
 * 发送短信息的实现类.
 * @author lsq
 *
 */
public class MessageSMS implements MessageImplementor{

	public void send(String message, String toUser) {
		System.out.println("使用系统内部消息的方法:"+message+"给"+toUser);
	}

}
