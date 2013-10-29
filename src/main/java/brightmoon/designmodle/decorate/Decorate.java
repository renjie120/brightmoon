package brightmoon.designmodle.decorate;

/**
 * 抽象构件.
 * 
 * @author lsq
 * 
 */
interface Animal {
	int getLifeCount();

	void move();

	void attacted();
}

/**
 * 抽象装饰对象.
 * 
 * @author lsq
 * 
 */
abstract class AnimalDecorator implements Animal {
	protected Animal cat;

	public AnimalDecorator(Animal a) {
		cat = a;
	}

	public abstract void attacted();

	public int getLifeCount() {
		return cat.getLifeCount();
	}

	public abstract void move();
}

/**
 * 具体构建角色.
 * 
 * @author lsq
 * 
 */
class NineLivesCat implements Animal {
	private int livecount = 9;

	public void attacted() {
		livecount--;
		System.out.println("被攻击了,还有" + livecount + "条命!");
	}

	public int getLifeCount() {
		// PMS Auto-generated method stub
		return livecount;
	}

	public void move() {
		System.out.println("在运动中");
	}
}

/**
 * 具体装饰对象.---继承了抽象的装饰对象类.
 * 
 * @author lsq
 * 
 */
class FlyingCat extends AnimalDecorator {
	public FlyingCat(Animal cat) {
		super(cat);
	}

	@Override
	public void attacted() {
		cat.attacted();
	}

	public int getLifeCount() {
		return cat.getLifeCount();
	}

	/**
	 * 添加上新的行为.
	 */
	@Override
	public void move() {
		//cat.move();
		System.out.println("我在飞");
	}
}

class SwimmingCat extends AnimalDecorator {
	public SwimmingCat(Animal cat) {
		super(cat);
	}

	@Override
	public void attacted() {
		cat.attacted();
	}

	public int getLifeCount() {
		return cat.getLifeCount();
	}

	@Override
	public void move() {
		//cat.move();
		System.out.println("我会游泳");
	}
}

class BigDog {
	public void attack(Animal cat) {
		cat.attacted();
	}
}

public class Decorate {
	public static void main(String[] args) {
		Animal cat = new NineLivesCat();
		System.out.println(cat.getClass().getName());
		BigDog dog = new BigDog();
		cat.move();
		dog.attack(cat);

		//变成会游泳的猫
		cat = new SwimmingCat(cat);
		System.out.println(cat.getClass().getName());
		cat.move();
		dog.attack(cat);

		//变成会飞的猫
		cat = new FlyingCat(cat);
		System.out.println(cat.getClass().getName());
		cat.move();
		dog.attack(cat);
	}
}
