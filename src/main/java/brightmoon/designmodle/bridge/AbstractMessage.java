package brightmoon.designmodle.bridge;

/**
 * 抽象消息类.
 * 
 * @author lsq
 * 
 */
public class AbstractMessage {
	MessageImplementor impl;

	public AbstractMessage(MessageImplementor impl) {
		this.impl = impl;
	}
	
	public void sendMessage(String message,String toUser){
		this.impl.send(message, toUser);
	}
}
