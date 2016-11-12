package com.fsmeeting.live.common.enums;

/**
 * 房间状态
 * 
 * @author yicai.liu<moon>
 *
 */
public enum LiveRoomStatus {

	/**
	 * 被占用
	 */
	OCCUPIED(1),

	/**
	 * 空闲
	 */
	IDLE(0);

	private int code;

	private LiveRoomStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
