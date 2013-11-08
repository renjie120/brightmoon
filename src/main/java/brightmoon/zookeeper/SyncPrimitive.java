package brightmoon.zookeeper;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class SyncPrimitive implements Watcher {
	static ZooKeeper zk = null;
	// 互斥变量
	static Integer mutex;
	String root;

	SyncPrimitive(String address) {
		if (zk == null) {
			try {
				System.out.println("Starting zk...");
				zk = new ZooKeeper(address, 300000, this);
				mutex = new Integer(-1);
				System.out.println("Finished starting zk:" + zk);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				zk = null;
			}
		}
	}

	@Override
	synchronized public void process(WatchedEvent event) {
		synchronized (mutex) {
			mutex.notify();
		}
	}

	static public class Barrier extends SyncPrimitive {
		int size;
		String name = null;
		List<String> ids ;

		// 内部静态类，必须要有构造函数》》
		Barrier(String address, String root, int size) {
			super(address);
			this.root = root;
			this.size = size;
			ids = new ArrayList<String>();
			if (zk != null) {
				try {
					Stat s = zk.exists(root, false);
					if (s == null) {
						zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE,
								CreateMode.PERSISTENT);
					}
				} catch (KeeperException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					name = new String(InetAddress.getLocalHost()
							.getCanonicalHostName().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		boolean enter() throws KeeperException, InterruptedException {
			String str = zk.create(root + "/" + name, new byte[0], Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			ids.add(str); 
			while (true) {
				synchronized (mutex) {
					List<String> list = zk.getChildren(root, true);
					if (list.size() < size) {
						mutex.wait();
					} else
						return true;
				}
			}
		}

		boolean leave() throws InterruptedException, KeeperException {
			if(ids!=null&&ids.size()>0){
				 String id = ids.remove(0);
				zk.delete(id, 0);
				System.out.println("移除节点："+id+",,,"+ids.size());
			}
			//zk.delete(root + "/" + name, 0);
			while (true) {
				synchronized (mutex) {
					List<String> list = zk.getChildren(root, true);
					if (list.size() > 0) {
						mutex.wait();
					} else
						return true;
				}
			}
		}
	}

	static public class Queue extends SyncPrimitive {

		Queue(String address, String name) {
			super(address);
			this.root = name;
			if (zk != null) {
				Stat s;
				try {
					s = zk.exists(root, false);
					if (s == null) {
						zk.create(root, new byte[0], Ids.OPEN_ACL_UNSAFE,
								CreateMode.PERSISTENT);
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

		boolean produce(int i) throws KeeperException, InterruptedException {
			ByteBuffer b = ByteBuffer.allocate(4);
			byte[] value;
			b.putInt(i);
			value = b.array();
			zk.create(root + "/element", value, Ids.OPEN_ACL_UNSAFE,
					CreateMode.PERSISTENT_SEQUENTIAL);
			return true;
		}

		// 注意下面的返回整形的函数，不一定非要每个分支都有return，如果是一个死循环里面，就可以不用return int了。
		int consume() throws KeeperException, InterruptedException {
			int retvalue = -1;
			Stat stat = null;
			while (true) {
				synchronized (mutex) {
					List<String> list = zk.getChildren(root, true);
					if (list.size() == 0) {
						System.out.println("going to wait");
						mutex.wait();
					} else {
						Integer min = new Integer(list.get(0).substring(7));
						for (String s : list) {
							Integer tmp = new Integer(s.substring(7));
							if (tmp < min)
								min = tmp;
						}
						System.out.println("temporary value:" + root
								+ "/element" + min);
						byte[] b = zk.getData(root + "/element" + min, false,
								stat);
						zk.delete(root + "/element" + min, 0);
						ByteBuffer buffer = ByteBuffer.wrap(b);
						retvalue = buffer.getInt();
						return retvalue;
					}
				}
			}
		}

	}

	public static void main(String[] args) { 
		Barrier b = new Barrier("127.0.0.1:2182", "/b1", 10);
		BarrierEnter enter = new BarrierEnter(b);
		BarrierLeave leave = new BarrierLeave(b);
		for (int i = 0; i < 10; i++) {
			new Thread(enter).start(); 
		} 
		
		for (int i = 0; i < 10; i++) {
			 new Thread(leave).start();
		} 
	} 

	private static void queueTest(String[] args) {
		Queue q = new Queue(args[1], "/app1");
		System.out.println("input:" + args[1]);
		int i;
		Integer max = new Integer(args[2]);
		if (args[3].equals("p")) {
			System.out.println("Producer");
			for (i = 0; i < max; i++) {
				try {
					q.produce(10 + i);
				} catch (KeeperException e) {
				} catch (InterruptedException e) {
				}
			}
		} else {
			System.out.println("consumer");
			for (i = 0; i < max; i++) {
				int r;
				try {
					r = q.consume();
					System.out.println("Item:" + r);
				} catch (KeeperException e) {
					i--;
				} catch (InterruptedException e) {
				}

			}
		}
	}
}
