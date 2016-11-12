package com.fsmeeting.live.service;

import com.fsmeeting.live.common.bean.AppHeartbeatModel;
import com.fsmeeting.live.common.bean.AppModel;

/**
 * App 业务
 * 
 * @author yicai.liu<moon>
 */
public interface IAppService
{

	/**
	 * 注册
	 * 
	 * @param model
	 * @throws Exception
	 */
	boolean register(AppModel model) throws Exception;

	/**
	 * 心跳
	 * 
	 * @param model
	 * @return
	 */
	boolean heartbeat(AppHeartbeatModel model) throws Exception;

	/**
	 * 获取最小负载的服务的地址
	 * 
	 * @param appType
	 * @return
	 */
	String getMinLoadService(int appType) throws Exception;

	/**
	 * 获取直播服务器地址
	 * 
	 * @param liveRoomId
	 * @return
	 */
	String getLiveSrvAddr(int liveRoomId) throws Exception;

}
