package brightmoon.designmodle.bridge;

/**
 * 加急消息.
 * @author lsq
 *
 */
public class UrgencyMessage extends AbstractMessage{

	public UrgencyMessage(MessageImplementor impl) {
		super(impl); 
	}

	public void sendMessage(String message,String toUser){
		message+="加急:"+message;
		super.sendMessage(message, toUser);
	}
	
	/**
	 * 额外扩展的状态.
	 * @param messageId
	 * @return
	 */
	public Object watch(String messageId){
		return null;
	}
}
