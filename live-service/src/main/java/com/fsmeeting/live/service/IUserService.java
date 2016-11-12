package com.fsmeeting.live.service;

import com.fsmeeting.live.common.bean.LiveRoom;
import com.fsmeeting.live.common.bean.ResponseMsg;
import com.fsmeeting.live.common.bean.vo.LoginRequestVO;
import com.fsmeeting.live.common.bean.vo.LoginResponseVO;

/**
 * 用户业务
 * 
 * @author yicai.liu<moon>
 *
 */
public interface IUserService {

	/**
	 * 获取直播房间信息
	 * 
	 * @param 直播URL
	 */
	ResponseMsg<LiveRoom> getLiveRoomInfo(String liveURI);

	/**
	 * 登陆业务
	 * 
	 * @param liveURI
	 *            直播URL
	 * @param bean
	 *            登录请求post信息
	 * @return
	 */
	ResponseMsg<LoginResponseVO> login(String liveURI, LoginRequestVO bean);

	/**
	 * 激活用户（校验用户）
	 * 
	 * @param userId
	 * @param token
	 * @return
	 */
	boolean activeToken(int userId, String token);

	/**
	 * 释放单个token
	 * 
	 * @param token
	 * @return
	 */
	boolean releaseToken(String token);

	/**
	 * 释放一个服务上的所有token
	 * 
	 * @param appId
	 * @return
	 */
	boolean releaseTokenByAppId(String appId);

	/**
	 * 释放某个代理上某个房间的token appId、liveRoomId必填
	 * 
	 * @param appId
	 * @param liveRoomId
	 * @return
	 */
	boolean releaseTokenByAppIdAndLiveRoom(String appId, int liveRoomId);

}
