package brightmoon.classloader;

/**
 * 演示一个自定义的ClassLoader
 * 
 * @author lsq
 * 
 */
public class MyClassLoader extends ClassLoader {
	private String name;// 类加载器的名字

	private String path = "d:\\";// 加载类的路径

	private final String fileType = ".class";// class文件的后缀名

	public MyClassLoader(String name) {
		super();// 让系统加载器成为该加载器的父加载器

		this.name = name;
	}

	public MyClassLoader(ClassLoader parent, String name) {
		super(parent);// 显示指定类加载器的父加载器。

		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String toString() {
		return this.name;
	}
	
	public byte[] loadClassData(String name){
//		InputStream is = null;
//		byte[] data = null;
		return null;
	}
}
