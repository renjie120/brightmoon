package brightmoon.redis;

import java.util.BitSet;

public class BitSetTest {
	private BitSet used = new BitSet();

	public BitSetTest(String str) {
		for (int i = 0; i < str.length(); i++)
			used.set(str.charAt(i)); // set bit for char
	}

	public String toString() {
		String desc = "[";
		int size = used.size();
		for (int i = 0; i < size; i++) {
			if (used.get(i))
				desc += (char) i;
		}
		return desc + "]";
	}

	public static void main(String args[]) {
		BitSetTest w = new BitSetTest("大家好");
		System.out.println(w);
	}
}
