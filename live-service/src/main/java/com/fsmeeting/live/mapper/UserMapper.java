package com.fsmeeting.live.mapper;

import com.fsmeeting.live.common.bean.LiveRoom;
import com.fsmeeting.live.common.bean.LiveService;
import com.fsmeeting.live.common.bean.TokenInfo;

/**
 * 用户数据库交互接口
 * 
 * @author yicai.liu<moon>
 *
 */
public interface UserMapper {

	/**
	 * 获取直播房间信息
	 * 
	 * @param liveURI
	 * @return
	 */
	LiveRoom getLiveRoomInfo(String liveURI);

	/**
	 * 计算房间在线人数
	 * 
	 * @param liveId
	 *            直播房间
	 * @return
	 */
	int countOnlineUser(int liveId);

	/**
	 * 持久化登录成功后的TOKEN信息
	 * 
	 * @param token
	 * 
	 */
	void addTokenInfo(TokenInfo token);

	/**
	 * 拣选代理服务器
	 * 
	 * @return 代理服务器
	 */
	LiveService pickProxyServer();
}
