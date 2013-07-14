package brightmoon.designmodle.effective_java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 下面的方法说明方法重载可能引起的问题!!
 * 
 * @author lsq
 * 
 */
public class SetAndList {
	static Integer i;
	public static void main(String[] args) {
		Set<Integer> set = new TreeSet<Integer>();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = -3; i < 3; i++) {
			set.add(i);
			list.add(i);
		}

		for (int i = 0; i < 3; i++) {
			set.remove(i);
			// 注意这里的remove，不是直接删除对应的0，1，2，而是删除的是第0，1，2个位置！
			// 因为重载的原因！remove(object)和remove(index)造成了混乱。。。
			list.remove(i);
		}
		System.out.println(set + " " + list);
		System.out.println(1.01-0.42);
		
//		if(i==42){
//			System.out.println(123);
//		}
		
		Integer i1 = new Integer(42);
		Integer i2 = new Integer(42);
		System.out.println(i1==i2);
		System.out.println(i1.equals(i2));
		StringBuilder build = new StringBuilder();
	}
}
