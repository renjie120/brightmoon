package brightmoon.context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import sun.misc.Service;

public class MyClass {
	private static MyClass framework;
	private Map<String, Object> services = new HashMap<String, Object>();

	public static MyClass getInstance() {
		if (framework == null) {
			framework = new MyClass();
			framework.start();
		}
		return framework;
	}

	public ITest getService(String name) {
		return (ITest) services.get(name);
	}
	
	private void start() {
		ClassLoader classLoader = MyClass.class.getClassLoader();
		Iterator itr = Service.providers(ITest.class, classLoader);
		while (itr.hasNext()) {
			try {
				ITest service = (ITest) itr.next();
				services.put(service.getName(), service);
			} catch (Exception err) {
				err.printStackTrace();
				continue;
			}
		}
 
	}

}