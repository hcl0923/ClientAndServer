package com.yc.bean04;
/**
 * 回调接口
 * @author hp
 *
 */
public interface MyNotify {
	/**
	 * 回调方法：当子线程完成操作，向主线程通信
	 * @param obj 回调参数 用来返回给主线程的结果
	 */
	public void notifyResult(Object obj);
}
