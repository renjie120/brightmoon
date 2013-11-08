package brightmoon.zookeeper;

import brightmoon.zookeeper.SyncPrimitive.Barrier;

public class BarrierLeave implements Runnable {
	private Barrier b;

	BarrierLeave(Barrier b) {
		this.b = b;
	}

	@Override
	public void run() {
		try {
			System.out.println("准备离开。。。。。");
			boolean flag = b.leave();
			if (!flag)
				System.out.println("离开节点barrier失败.");
			else
				System.out.println("离开节点barrier成功.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
