package brightmoon.designmodle.bridge;

import java.util.Observable;

public class Client {
	public static void main(String[] args){
		MessageImplementor impl = new MessageSMS();
		AbstractMessage message = new CommonMessage(impl);
		message.sendMessage("普通消息", "li");
		
		impl = new MessageEmail();
		message = new UrgencyMessage(impl);
		message.sendMessage("加急消息", "li");
		
		Observable test = new Observable();
	}
}
