package brightmoon.designmodle.bridge;

/**
 * 发送短信的抽象接口.
 * @author lsq
 *
 */
public interface MessageImplementor {
	public void send(String message, String toUser);
}
