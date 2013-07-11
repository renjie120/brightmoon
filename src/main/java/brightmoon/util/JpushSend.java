package brightmoon.util;

import java.util.HashMap;
import java.util.Map;

import brightmoon.web.HttpClientUtil;

public class JpushSend {
	private String username;
	private int sendno;
	private String appkeys;
	private int receiver_type;
	private String receiver_value;
	private String verification_code;
	private int msg_type;
	private String msg_content;
	private String send_description;
	private String callback_url;
	private String platform;

	public String getUsername() {
		return username;
	}

	public final static String URL = "http://api.jpush.cn:8800/sendmsg/sendmsg";

	public void send() {
		if (msg_content == null)
			throw new IllegalArgumentException("msg_content不得为空！");
		if (username == null)
			throw new IllegalArgumentException("username不得为空！");
		if (sendno == 0)
			throw new IllegalArgumentException("sendno不得为空！");
		if (appkeys == null)
			throw new IllegalArgumentException("appkeys不得为空！");
		if (receiver_type == 0)
			throw new IllegalArgumentException("receiver_type不得为空！");
		HttpClientUtil util = new HttpClientUtil();
		Map params = new HashMap(10);
		/*
		 * params.put("username", username);
		 * //发送编号（最大支持32位）。由开发者自己维护，标识一次发送请求。建议每次推送这个编号递增。 params.put("sendno",
		 * sendno+""); //待发送的应用程序(appKey)，只能填一个 params.put("appkeys", appkeys);
		 * //接收者类型 params.put("receiver_type", receiver_type+"");// 对全部的用户推送 //
		 * params.put("receiver_value", "123"); params.put("verification_code",
		 * calcMd5()); params.put("msg_type", "1");// 消息类型：通知
		 * params.put("msg_content", msg_content); params.put("platform",
		 * "android");
		 */

		params.put("username", "renjie120");
		params.put("sendno", "123");
		params.put("appkeys", "b2095f5f09da000825c50b9b");
		params.put("receiver_type", "4");// 对全部的用户推送
		params.put("verification_code", calcMd5());
		params.put("msg_type", "1");// 消息类型：通知
		params.put("msg_content", "test");
		params.put("platform", "android");
		System.out.println(""
				+ util.postUrlWithParams(URL, params, true, "GBK"));

	}

	private String calcMd5() {
		String md5Password = CoderUtil.encryptMd5("lsqjpush1987");
		md5Password = md5Password.toUpperCase();
		String input = username + sendno + receiver_type + receiver_value
				+ md5Password;
		return CoderUtil.encryptMd5(input).toUpperCase();
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSendno() {
		return sendno;
	}

	public void setSendno(int sendno) {
		this.sendno = sendno;
	}

	public String getAppkeys() {
		return appkeys;
	}

	public void setAppkeys(String appkeys) {
		this.appkeys = appkeys;
	}

	public int getReceiver_type() {
		return receiver_type;
	}

	public void setReceiver_type(int receiver_type) {
		this.receiver_type = receiver_type;
	}

	public String getReceiver_value() {
		return receiver_value;
	}

	public void setReceiver_value(String receiver_value) {
		this.receiver_value = receiver_value;
	}

	public String getVerification_code() {
		return verification_code;
	}

	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}

	public int getMsg_type() {
		return msg_type;
	}

	public void setMsg_type(int msg_type) {
		this.msg_type = msg_type;
	}

	public String getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(String msg_content) {
		this.msg_content = msg_content;
	}

	public String getSend_description() {
		return send_description;
	}

	public void setSend_description(String send_description) {
		this.send_description = send_description;
	}

	public String getCallback_url() {
		return callback_url;
	}

	public void setCallback_url(String callback_url) {
		this.callback_url = callback_url;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
