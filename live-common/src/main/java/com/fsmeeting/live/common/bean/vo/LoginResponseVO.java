package com.fsmeeting.live.common.bean.vo;

import java.io.Serializable;

/**
 * 登录返回封装对象
 * 
 * @author yicai.liu<moon>
 *
 */
public class LoginResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * token
	 */
	private String token;

	/**
	 * 直播房间ID
	 */
	private String liveRoomId;

	/**
	 * 代理服务器地址
	 */
	private String proxyAddr;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLiveRoomId() {
		return liveRoomId;
	}

	public void setLiveRoomId(String liveRoomId) {
		this.liveRoomId = liveRoomId;
	}

	public String getProxyAddr() {
		return proxyAddr;
	}

	public void setProxyAddr(String proxyAddr) {
		this.proxyAddr = proxyAddr;
	}

}
