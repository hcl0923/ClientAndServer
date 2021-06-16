package com.yc.bean04;

public class SimpleThread extends Thread{
	
	private int threadNumber;//线程编号
	private boolean runningFlag;//当前线程运行状态true-->进入wait状态
	private Runnable task;
	private boolean flag=false;
	public SimpleThread(int threadNumber) {
		this.threadNumber=threadNumber;
		this.runningFlag=false;
		System.out.println("线程:"+this.threadNumber+"创建....");
	}
	public boolean isRunningFlag() {
		return runningFlag;
	}
	//获取任务
	public Runnable getTask() {
		return task;
	}
	public void setTask(Runnable task) {
		this.task=task;
	}
	public int getThreadNumber() {
		return threadNumber;
	}
	//关键方法 通过这个方法唤醒线程
	public synchronized void setRunningFlag(boolean runningFlag) {
		this.runningFlag=runningFlag;
		//资源锁的问题当前线程running后，要通知其他线程，可能进入阻塞-->notify
		if(this.runningFlag) {
			this.notifyAll();
		}
	}
	public synchronized void run() {
		try {
			while(!flag) {
				if(!runningFlag) {
					//当前线程还不能启动，则让当前线程进入wait()状态，这样不消耗cpu
					this.wait();
				}else {
					//当前线程运行起来
					System.out.println("线程："+this.threadNumber+"开始执行任务。。。");
					if(null!=task) {
						this.task.run();
					}
					//当任务执行完成后
					Thread.sleep(1000);
					System.out.println("线程："+this.threadNumber+"重新进入等待状态。。。等待下次调用");
					this.setRunningFlag(false);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void close() {
		this.flag=true;
	}
}







