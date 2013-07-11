package brightmoon.util.concurrent;


/**
 * Imitate Callable interface in jdk1.5
 * @author Administrator
 *
 * @param <V>
 */
public interface Callable<V> {

	V call() throws Exception;
}
