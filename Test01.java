package com.yc.bean04;

public class Test01 {
	public static void main(String[] args) {
		ThreadPoolManager manager=new ThreadPoolManager(5);
		manager.process(new CountTask(new MyNotify(){
			public void notifyResult(Object obj){
				System.out.println(Thread.currentThread().getName()+"---"+obj);
			}})
		);
//		for(int i=0;i<10;i++) {
//			manager.process(new CountTask(new MyNotify() {//这里是异步
//				
//				@Override
//				public void notifyResult(Object obj) {
//					System.out.println(Thread.currentThread().getName()+"---"+obj);//接口实现
//				}
//			}));
//		}
	}
}
//计数的任务
class CountTask implements Runnable{
	private MyNotify myNotify;
	
	public CountTask(MyNotify myNotify) {
		this.myNotify=myNotify;
	}
	@Override
	public void run() {
		int i=10;
		while(true) {
			if(null!=myNotify) {
				myNotify.notifyResult(i);
			}
			i--;
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			if(i<=0) {
				break;
			}
		}
	}
	
}
