package org.kymjs.kjframe.http.impl;

/**
 * 异步任务返回结果监听器.
 * 
 * @since 1.0.0
 */
public interface HttpPostListener<T> {

	/**
	 * 任务返回后将要执行的操作.
	 * 
	 * @param result
	 */
	public void onResult(HttpResult<T> result);
	
}
