package brightmoon.zookeeper;

import java.io.IOException;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZookeeperHello {

	public static final Logger logger = LoggerFactory
			.getLogger(ZookeeperHello.class);
	private Watcher watcher = new Watcher() {
		public void process(WatchedEvent event) {
			logger.error("触发了事件：：：:" + event.getType());
		}
	};
	private ZooKeeper zk;
	private final static int SESSION_TIMEOUT = 3000;

	@Before
	public void connect() throws IOException {
		zk = new ZooKeeper("localhost:2181,localhost:2182,localhost:2183",
				SESSION_TIMEOUT, watcher);
	}

	@After
	public void close() {
		try {
			zk.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		String result = null;
		try {
			// 创建路径，znode内容，ACL控制列表，znode创建类型
			/*
			 * CreateMode PERSISTENT：创建后只要不删就永久存在 EPHEMERAL：会话结束年结点自动被删除
			 * SEQUENTIAL：节点名末尾会自动追加一个10位数的单调递增的序号，同一个节点的所有子节点序号是单调递增的
			 * PERSISTENT_SEQUENTIAL：结合PERSISTENT和SEQUENTIAL
			 * EPHEMERAL_SEQUENTIAL：结合EPHEMERAL和SEQUENTIAL
			 */
			result = zk.create("/zk001", "lsqtest".getBytes(),
					Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

			// 创建带有序号的节点
			zk.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);
			zk.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);
			zk.create("/node-", "same data".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);
			List<String> children = zk.getChildren("/", null);
			System.out.println("Children of root node:");
			for (String child : children) {
				System.out.println(child);
			}

		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.error("create result", result);
	}

	@Test
	public void testDelete() {
		try {
			// 删除的路径，版本，如果为-1，就是不检测版本直接删除.如果版本号与znode的版本号不一致，将无法删除，是一种乐观加锁机制.
			zk.delete("/zk001", -1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		System.out.println("testDelete");
	}

	@Test
	public void testGetData() {
		String result = null;
		byte[] bytes;
		try {
			// 获取znode上面的数据.路径，数据，版本号。
			bytes = zk.getData("/zk001", null, null);
			result = new String(bytes);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("testGetData:::" + result);
	}

	@Test
	public void testGetDataWatch() {
		String result = null;
		try {
			byte[] bytes = zk.getData("/zk001", new Watcher() {
				public void process(WatchedEvent event) {
					logger.error("test getDataWatch:", event.getType());

				}
			}, null);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("get data result:testGetDataWatch------");
		try {
			// 设置节点数据，如果版本为-1，就跳过版本检测。
			zk.setData("/zk001", "testSetData000".getBytes(), -1);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//@Test
	public void testExists() {
		Stat stat = null;
		try {
			// 判断某个节点是否存在。路径，设置是否存在这个目录节点.
			stat = zk.exists("/zk001", false);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (stat != null) 
		logger.error(
				"exists result:",
				stat.getCzxid() + ",," + stat.getAversion() + ",,"
						+ stat.getDataLength());
	}

	//@Test
	public void testSetData() {
		Stat stat = null;
		try {
			stat = zk.setData("/zk001", "testSetData".getBytes(), -1);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (stat != null) 
			logger.error("exists after set data:", stat.getVersion());
	}

	  //@Test
	public void testExistsWatch1() {
		Stat stat = null;
		try {
			// 设置监控这个目录节点.如果设置要监听这个节点，如果这个路径节点存在，就会留下一个监听者，在节点创建或者删除时候被触发。
			stat = zk.exists("/zk001", true);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// 试着删除一下。
			zk.delete("/zk001", -1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 @Test
	public void testExistsWatch2() {
		Stat stat = null;
		try {
			stat = zk.exists("/zk002", new Watcher() {
				public void process(WatchedEvent event) {
					logger.error("testExistsWatch2 watch:", event.getType());
				}
			});
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// 触发一次watch重的process方法，nodeDataChanged
			zk.setData("/zk002", "testExistsWatch2".getBytes(), -1);
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * Watcher & Version watcher分为两大类：data watches和child
		 * watches。getData()和exists()上可以设置data watches，getChildren()上可以设置child
		 * watches。 setData()会触发data watches; create()会触发data watches和child
		 * watches; delete()会触发data watches和child watches.
		 * 如果对一个不存在的节点调用了exists()
		 * ，并设置了watcher，而在连接断开的情况下create/delete了该znode，则watcher会丢失。
		 * 在server端用一个map来存放watcher
		 * ，所以相同的watcher在map中只会出现一次，只要watcher被回调一次，它就会被删除
		 * ----map解释了watcher的一次性。比如如果在getData()和exists()上设置的是同一个data
		 * watcher，调用setData()会触发data watcher，但是getData()和exists()只有一个会收到通知。
		 */

		try {
			// 删除的时候不会再次触发了。
			zk.delete("/zk002", -1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// @Test
	public void testGetChild() {
		try {
			zk.create("/zk/001", "001".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);
			zk.create("/zk/002", "002".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT);

			List<String> list = zk.getChildren("/zk", true);
			for (String node : list) {
				logger.error("node{}:", node);
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
