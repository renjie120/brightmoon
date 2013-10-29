package brightmoon.refrence;

public class MyObject {
	protected void finalize() throws Throwable{
		super.finalize();
		System.out.println("MyObject被回收");
	}
	
	public String toString(){
		return "这是myObject";
	}
}
