package com.fsmeeting.live.common.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Token 基础信息
 * 
 * @author yicai.liu<moon>
 *
 */
public class TokenInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private int id;

	/**
	 * 用户ID
	 */
	private int userId;

	/**
	 * token
	 */
	private String token;

	/**
	 * 直播ID
	 */
	private int liveId;

	/**
	 * 用户代理服务器ID
	 */
	private int appId;

	/**
	 * 过期时间
	 */
	private int expires;

	/**
	 * 状态(0:未激活，1：激活，-1：被删除)
	 */
	private int status;

	/**
	 * 激活时间（userProxy验证通过时间
	 */
	private Date activeTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getLiveId() {
		return liveId;
	}

	public void setLiveId(int liveId) {
		this.liveId = liveId;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getExpires() {
		return expires;
	}

	public void setExpires(int expires) {
		this.expires = expires;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}

}
