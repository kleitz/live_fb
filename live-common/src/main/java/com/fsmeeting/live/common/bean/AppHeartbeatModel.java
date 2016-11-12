package com.fsmeeting.live.common.bean;

import java.io.Serializable;

/**
 * @author yicai.liu
 * @version
 * @date 2016年11月12日 下午12:04:49
 */
public class AppHeartbeatModel implements Serializable
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	private String				appId;

	private int					appType;

	private int					curLoad;

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public int getAppType()
	{
		return appType;
	}

	public void setAppType(int appType)
	{
		this.appType = appType;
	}

	public int getCurLoad()
	{
		return curLoad;
	}

	public void setCurLoad(int curLoad)
	{
		this.curLoad = curLoad;
	}

}
