package brightmoon.proxy;

public class AClass {
	private String name;
 

	public AClass(String name) {
		this.name = name;
	}

	public void test() {
		System.out.println("这里是一个普通的类没有实现接口，里面的方法" + name);
	}
}
