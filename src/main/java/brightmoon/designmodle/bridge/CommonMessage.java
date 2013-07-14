package brightmoon.designmodle.bridge;

/**
 * 普通消息.
 * 
 * @author lsq
 * 
 */
public class CommonMessage extends AbstractMessage {

	public CommonMessage(MessageImplementor impl) {
		super(impl);
		// PMS Auto-generated constructor stub
	}

	public void sendMessage(String message, String toUser) {
		super.sendMessage(message, toUser);
	}
}
