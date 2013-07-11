package brightmoon.temp;


public class SimpleBean {
	private String key;// 缓存ID
	private String name;//缓存名称
	private Object value;// 缓存数据
	private long timeOut;// 更新时间
	private boolean expired; // 是否终止
	public String toString(){
		return key+","+name+","+value+","+timeOut+","+expired;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(long timeOut) {
		this.timeOut = timeOut;
	}
	public boolean isExpired() {
		return expired;
	}
	public void setExpired(boolean expired) {
		this.expired = expired;
	}
	
}
