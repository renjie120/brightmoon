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
 * 演示的是在创建zk的时候绑定默认的监听器.
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
public class SelfWatcher implements Watcher {
	ZooKeeper zk = null;

	public void process(WatchedEvent event) {
		System.out.println(event.toString());
	}

	SelfWatcher(String address) {
		try {
			//在创建这个zk的时候就指定了默认的监听器.在getData，exists，getChildren时受到通知时，都会调用这个函数.
			zk = new ZooKeeper(address, 3000, this);
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
		Stat stat;
		try {
			//WatchEvent有三种类型：NodeDataChanged、NodeDeleted和NodeChildrenChanged。
			//调用setData()时会触发NodeDataChanged;
			//调用create()时会触发NodeDataChanged和NodeChildrenChanged;
			//调用delete()时上述三种event都会触发。
			stat = zk.exists("/root", true); 
			if (stat != null) {
				zk.getData("/root", false, stat) ;
			}
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//在server端用一个map来存放watcher，所以相同的watcher在map中只会出现一次，只要watcher被回调一次，它就会被删除----
	//map解释了watcher的一次性。比如如果在getData()和exists()上设置的是同一个data watcher，调用setData()会触发data watcher，
	//但是getData()和exists()只有一个会收到通知。
	void triggerWatcher(){
		Stat s;
		try {
			s = zk.exists("/root", false);
			zk.setData("/root", "a".getBytes(), s.getVersion());
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	void disconnect(){
		if(zk!=null){
			try {
				zk.close();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		SelfWatcher inst = new SelfWatcher("127.0.0.1:2182");
		inst.setWatcher();
		inst.triggerWatcher();
		inst.disconnect();
	}
}
