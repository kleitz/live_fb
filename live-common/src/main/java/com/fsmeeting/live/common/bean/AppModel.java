package com.fsmeeting.live.common.bean;

import java.io.Serializable;

/**
 * @author yicai.liu
 * @version
 * @date 2016年11月12日 上午11:41:09
 */
public class AppModel implements Serializable
{

	private static final long	serialVersionUID	= 1L;

	private String				appId;

	private int					appType;

	private String				desc;

	private String				address;

	private int					weight;

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

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public int getWeight()
	{
		return weight;
	}

	public void setWeight(int weight)
	{
		this.weight = weight;
	}

}
