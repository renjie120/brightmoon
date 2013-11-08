package brightmoon.zookeeper;

import brightmoon.zookeeper.SyncPrimitive.Barrier;

public class BarrierEnter implements Runnable{
	private Barrier b;
	BarrierEnter(Barrier b){
		this.b = b;
	}
	@Override
	public void run() {
		 try {
			boolean flag = b.enter();
			if (!flag)
				System.out.println("进入节点barrier失败.");
			else
				System.out.println("进入节点barrier成功.");
		} catch (Exception e) {
			 
		}  
	}

}
