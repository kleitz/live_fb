package com.fsmeeting.live.common.enums;

/**
 * 服务器类型
 * 
 * @author yicai.liu<moon>
 */
public enum LiveServerType
{
	/**
	 * 未知
	 */
	UNKNOWN(0),

	/**
	 * 代理服务器
	 */
	PROXY(1),

	/**
	 * 直播服务器
	 */
	LIVE(2),

	/**
	 * 媒体服务器
	 */
	MEDIA(3),

	/**
	 * 图片服务器
	 */
	PIC(4);
	private int code;

	private LiveServerType(int code)
	{
		this.code = code;
	}

	public int getCode()
	{
		return code;
	}
}
