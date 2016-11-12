package com.fsmeeting.live.service;

import com.fsmeeting.live.common.bean.LiveRoom;

/**
 * 直播房间服务
 * 
 * @author yicai.liu<moon>
 *
 */
public interface ILiveRoomService {

	/**
	 * 获取直播房间信息
	 * 
	 * @param liveRoomId
	 * @return
	 */
	LiveRoom getLiveRoom(int liveRoomId);

	/**
	 * 激活直播房间
	 * 
	 * @param liveRoomId
	 * @return
	 */
	boolean activeLiveRoom(int liveRoomId);

	/**
	 * 释放直播房间
	 * 
	 * @param liveRoomId
	 * @return
	 */
	boolean releaseLiveRoom(int liveRoomId);
}
