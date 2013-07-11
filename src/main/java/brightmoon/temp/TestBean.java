package brightmoon.temp;


public class TestBean {
	 private String beanId;
	 private String beanName;
	public String getBeanId() {
		return beanId;
	}
	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String toString(){
		return beanId+","+beanName;
	}
}
