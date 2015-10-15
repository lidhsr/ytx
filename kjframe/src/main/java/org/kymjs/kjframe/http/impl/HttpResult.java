package org.kymjs.kjframe.http.impl;

/**
 * 任务执行结果.
 * 
 * @param <T> 任务返回结果的数据类型
 * @since 1.0
 */
public class HttpResult<T> {
	
	/** 任务执行成功(true) */
	public static final int OK = 1;
	/** 已到达末页 */
	public static final int ENDPAGE = 11;
	/** 任务执行失败(false) */
	public static final int FAILED = -1;
	/** 任务执行成功, 但返回结果为空 */
	public static final int EMPTY = -2;
	/** 任务执行抛出错误 */
	public static final int ERROR = -3;
	
	/** 任务ID */
	private int taskKey = 0;
	
	/** 任务执行完成后的结果码 */
	private int ret = 0;
	
	/** 返回的数据 */
	private T data = null;
	
	/** 服务器返回的提示信息(当返回值为 'OK' or 'FAILED', 从该字段取信息) */
	private String msg = "";
	
	/** 服务器返回的错误信息(当返回值为 'ERROR', 从该字段取信息) */
	private int code;
	
	/** 当任务执行抛出异常时, 此属性不为空 */
	private Exception exception = null;
	
	private boolean lastPage;
	private boolean firstPage;

	/**
	 * @return 任务执行完成后的结果码
	 */
	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	/**
	 * @return 任务返回的数据
	 */
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return 当任务执行抛出异常时, 该方法返回结果不为空.
	 */
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getTaskKey() {
		return taskKey;
	}

	public void setTaskKey(int taskKey) {
		this.taskKey = taskKey;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isLastPage() {
		return lastPage;
	}

	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isFirstPage() {
		return firstPage;
	}

	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	
}
