package brightmoon.refrence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
	//运行程序的时候，设置JVM参数：-Xmx5M
	public static void main(String[] args) {
		Map map = new WeakHashMap();
		List l = new ArrayList();
		//下面是WeakHashMap不会引起OOM
		for(int i=0;i<10000;i++)
		{
			Integer in = new Integer(i);
			map.put(in, i);
		}
		//下面就会引起OOM
		map = new HashMap();
		for(int i=0;i<10000;i++)
		{
			Integer in = new Integer(i);
			map.put(in, i);
		}
	}
}
