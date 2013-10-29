package brightmoon.designmodle.iterator;

/**
 * 白箱聚集与外禀迭代子. 迭代子是一个外部类，聚集对象提供了便利迭代子的接口.
 * 
 * @author lsq
 * 
 */
public class WhiteboxIterator {
	private Iterator it;
	private Aggregate agg = new ConcreteAggregate();
	public void operation(){
		it = agg.createIterator();
		while(!it.isDone()){
			System.out.println(it.currentItem().toString());
			it.next();
		}
	}
	
	public static void main(String[] args){
		WhiteboxIterator test = new WhiteboxIterator();
		test.operation();
	}
}

/**
 * 抽象聚集类.
 * @author lsq
 *
 */
abstract class Aggregate {
	public Iterator createIterator() {
		return null;
	}
}

/**
 * 抽象迭代器.
 * @author lsq
 *
 */
interface Iterator {
	void first();

	void next();

	boolean isDone();

	Object currentItem();
}

/**
 * 具体聚集类,含有内容的列表.
 * 以及含有创建一个迭代器的方法.
 * @author lsq
 *
 */
class ConcreteAggregate extends Aggregate {
	private Object[] obj = { "test1", "test2", "test3", "test4" };

	public Iterator createIterator() {
		return new ConcreteIterator(this);
	}

	public Object getElement(int index) {
		if (index < obj.length)
			return obj[index];
		else
			return null;
	}

	public int size() {
		return obj.length;
	}
}

/**
 * 具体迭代器.
 * @author lsq
 *
 */
class ConcreteIterator implements Iterator {
	ConcreteAggregate agg;
	private int index = 0;
	private int size = 0;

	public ConcreteIterator(ConcreteAggregate agg) {
		this.agg = agg;
		size = agg.size();
		index = 0;
	}

	public Object currentItem() {
		// PMS Auto-generated method stub
		return agg.getElement(index);
	}

	public void first() {
		index = 0;
	}

	public boolean isDone() {
		// PMS Auto-generated method stub
		return index >= size;
	}

	public void next() {
		if (index < size)
			index++;
	}

}
