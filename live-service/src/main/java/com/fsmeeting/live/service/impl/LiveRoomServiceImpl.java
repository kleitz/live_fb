package com.fsmeeting.live.service.impl;

import com.fsmeeting.live.common.bean.LiveRoom;
import com.fsmeeting.live.service.ILiveRoomService;

public class LiveRoomServiceImpl implements ILiveRoomService {

	@Override
	public LiveRoom getLiveRoom(int liveRoomId) {
		LiveRoom room = new LiveRoom();
		return room;
	}

	@Override
	public boolean activeLiveRoom(int liveRoomId) {
		return true;
	}

	@Override
	public boolean releaseLiveRoom(int liveRoomId) {
		return true;
	}

}
