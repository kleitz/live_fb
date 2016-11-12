package com.fsmeeting.live.server.task;

import com.fsmeeting.live.common.enums.Response;

public abstract class AbstractTask {

	public int result = Response.SUCCESS.getCode();

	public int getResult() {

		return result;

	}

	/**
	 * 
	 * @Title: getResult
	 * @Description: 判断结果码，resultCode为数据库查询结果，resultCode=Constants.
	 *               EMPTY_RESULT为数据库查询为空值 1.优先返回异常结果 2.数据查询为空 3.查询正常
	 * @param @param
	 *            resultCode 数据库查询结果
	 * @param @return
	 * @return int
	 */
	public int getResult(int resultCode) {

		if (result == Response.DB_EXCEPTION.getCode()) {
			return result;
		}
		if (resultCode == Response.EMPTY_RESULT.getCode()) {
			return resultCode;
		}
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	/**
	 * 
	 * @Title: execute
	 * @Description: 工作线程的执行方法
	 * @param
	 * @return void
	 */
	public abstract void doService();

}
