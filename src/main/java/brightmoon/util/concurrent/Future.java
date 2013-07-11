package brightmoon.util.concurrent;


/***
 * imitate Future interface in jdk1.5
 * @author Administrator
 */
public interface Future<V> {

	V get();
}
