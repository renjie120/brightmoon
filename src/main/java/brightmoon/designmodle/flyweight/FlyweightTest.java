package brightmoon.designmodle.flyweight;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式--感觉和不变模式比较像. 以共享的方式高效的支持大量的细粒度对象,能够做到共享的关键是区分内蕴状态和外蕴状态.
 * 内蕴状态时存储在享元对象内部的,不随环境的改变而有所不同,一个享元可以具有内蕴状态并可以共享;
 * 外蕴状态时随环境改变而改变的,不可以共享的状态,必须由客户端保存,并在享元创建以后,在需要的时候再传入到享元对象内部.
 * 
 * 外蕴状态和内蕴状态是相互独立的.
 * 
 * 编辑器系统,一个文本编辑器的多个字体,虽然字体不一样,但是都是同一个字母,字母不变;
 * java中的string类型就是享元模式,String对象是不变对象,在jvm中的两个String包含的字符串相同的话,实际只创建了一个String对象而提供两个不同的引用.
 * 
 * @author lsq
 * 
 */

// 享元接口
interface Flyweight {
	/**
	 * 判断传入的安全实体和权限,是否和享元对象的内部状态匹配.
	 * 
	 * @param securityEntity
	 * @param permit
	 * @return
	 */
	public boolean match(String securityEntity, String permit);
}

/**
 * 享元对象.
 * 
 * @author lsq
 * 
 */
class AuthorizationFlyweight implements Flyweight {
	// 内部状态,安全实体.
	private String securityEntity;
	// 内部状态,权限
	private String permit;

	public AuthorizationFlyweight(String state) {
		String ss[] = state.split(",");
		this.securityEntity = ss[0];
		this.permit = ss[1];
	}

	public boolean match(String securityEntity, String permit) {
		if (this.securityEntity.equals(securityEntity)
				&& this.permit.equals(permit)) {
			return true;
		}
		return false;
	}

	public String getSecurityEntity() {
		return securityEntity;
	}

	public String getPermit() {
		return permit;
	}
}

/**
 * 享元工厂，一般是单例.
 * 
 * @author lsq
 * 
 */
class FlyweightFactory {
	private static FlyweightFactory factory = new FlyweightFactory();

	private FlyweightFactory() {

	}

	// 缓存多个flyweight对象.
	private Map<String, Flyweight> fsMap = new HashMap<String, Flyweight>();

	public static FlyweightFactory getInstance() {
		return factory;
	}

	// 获取享元对象.
	public Flyweight getFlyweight(String key) {
		Flyweight f = fsMap.get(key);
		if (f == null) {
			f = new AuthorizationFlyweight(key);
			fsMap.put(key, f);
		}
		return f;
	}
}

/**
 * 模拟客户端，登录效果.也是做为单例的类.
 * 
 * @author lsq
 * 
 */
class SecurityMgr {
	private static SecurityMgr securityMgr = new SecurityMgr();

	private SecurityMgr() {

	}

	public static SecurityMgr getInstance() {
		return securityMgr;
	}

	/**
	 * 在运行期间，用来存放登陆人员对应的权限 在Web应用中，这些数据通常存放到session中
	 */
	private Map<String, Collection<Flyweight>> map = new HashMap<String, Collection<Flyweight>>();

	/**
	 * 登陆功能,登录的时候查询登录人所有的权限对象缓存起来.
	 * 
	 * @param user
	 */
	public void login(String user) {
		Collection<Flyweight> col = queryByUser(user);
		map.put(user, col);
	}

	/**
	 * 模拟从数据库中获取某人所拥有的权限
	 * 
	 * @param user
	 * @return
	 */
	private Collection<Flyweight> queryByUser(String user) {
		Collection<Flyweight> col = new ArrayList<Flyweight>();
		for (String s : TestDB.colDB) {
			String ss[] = s.split(",");
			if (ss[0].equals(user)) {
				// 将权限数据作为享元对象实例化保存起来!这样节省了创建很多的对象!!
				Flyweight fm = FlyweightFactory.getInstance().getFlyweight(
						ss[1] + "," + ss[2]);
				col.add(fm);
			}
		}
		return col;
	}

	/**
	 * 判断用户对某个安全实体是否拥有某种权限
	 * 
	 * @param user
	 *            用户
	 * @param securityEntity
	 *            安全实体
	 * @param permit
	 *            权限
	 * @return
	 */
	public boolean hasPermit(String user, String securityEntity, String permit) {
		Collection<Flyweight> col = map.get(user);
		if (col == null || col.size() == 0) {
			System.out.println(user + "没有登陆或没有被分配任何权限");
			return false;
		}
		for (Flyweight fm : col) {
			System.out.println("fm == " + fm);
			if (fm.match(securityEntity, permit)) {
				return true;
			}
		}
		return false;
	}
}

class TestDB {
	public static Collection<String> colDB = new ArrayList<String>();

	static {
		colDB.add("张三,人员列表,查看");
		colDB.add("李四,人员列表,查看");
		colDB.add("李四,薪资列表,查看");
		colDB.add("李四,薪资列表,修改");

		for (int i = 0; i < 3; i++) {
			colDB.add("张三" + i + ",人员列表,查看");
		}
	}
}

public class FlyweightTest {
	public static void main(String[] args) {
		SecurityMgr mgr = SecurityMgr.getInstance();
		mgr.login("张三");
		mgr.login("李四");

		boolean f1 = mgr.hasPermit("张三", "薪资列表", "查看");
		boolean f2 = mgr.hasPermit("李四", "薪资列表", "查看");
		boolean f3 = mgr.hasPermit("张三", "人员列表", "查看");

		System.out.println("f1 == " + f1);
		System.out.println("f2 == " + f2);
		System.out.println("f3 == " + f3);

		for (int i = 0; i < 3; i++) {
			mgr.login("张三" + i);
			mgr.hasPermit("张三" + i, "薪资列表", "查看");
		}
	}
}
