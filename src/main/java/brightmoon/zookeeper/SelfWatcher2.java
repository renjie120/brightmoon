package brightmoon.zookeeper;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * 
 * <pre>
 * 演示的是在进行操作的时候，实时绑定的具体的事件监听器.
 * </pre>
 *
 * @since 
 *
 * <pre>
 *	  modify by lisq on 2013-10-29
 *    fix->1.
 *         2.
 * </pre>
 */
public class SelfWatcher2 {
	ZooKeeper zk = null;

	private Watcher getWatcher(final String msg) {
		return new Watcher() {
			public void process(WatchedEvent event) {
				System.out.println(msg + "\n" + event.toString());
			}
		};
	}

	SelfWatcher2(String address) {
		try {
			zk = new ZooKeeper(address, 3000, null);
			zk.create("/root", new byte[0], Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void setWatcher() {
		Stat s;
		try {
			s = zk.exists("/root", getWatcher("EXISTS"));
			if (s != null) {
				zk.getData("/root", getWatcher("GETDATA"), s);
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void trigeWatcher() {
		Stat s;
		try {
			s = zk.exists("/root", false);
			zk.setData("/root", "a".getBytes(), s.getVersion());
			
			//临时节点，不可以有孩子节点！！所以下面的会报错！！
			//zk.create("/root/haha1", "a".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			 
			//因为是临时节点，在close会话的时候，会删除节点，然后就触发了getChildren的事件.
			//下面表示查询孩子节点，同时在孩子节点上面创建了一个监听事件.
			zk.getChildren("/root", getWatcher("LISTCHILDREN"));
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void disconnect() {
		if(zk!=null)
			try {
				zk.close();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		SelfWatcher2 inst = new SelfWatcher2("127.0.0.1:2182");
		inst.setWatcher();
		inst.trigeWatcher();
		inst.disconnect();
	}
	
	//关于事件的具体触发情况！详细见帖子：http://www.cnblogs.com/zhangchaoyang/articles/2536178.html！！
}
