package com.fsmeeting.live.common;

/**
 * 直播常量
 * 
 * @author yicai.liu<moon>
 */
public class Constants
{

	/**
	 * 心跳检测超时时间
	 */
	public static final long HEATBEAT_EXPIRE_SECOND = 15;

	/**
	 * Redis 信息
	 *
	 * @author yicai.liu
	 * @date 2016年11月13日 上午1:18:49
	 */
	public static final class Redis
	{
		/**
		 * 分隔符
		 */
		public static final String SPLITER = ":";
		// String tableName = Constants.Table.T_LiveService.NAME;

		public static final class Key
		{
			/**
			 * 服务列表
			 */
			public static final String	SERVICE_LIST			= "List" + Redis.SPLITER
					+ Constants.Table.T_LiveService.NAME;

			/**
			 * 活跃服务前缀
			 */
			public static final String	SERVICE_ACTIVE_PRIFIXER	= Constants.Table.T_LiveService.NAME + Redis.SPLITER
					+ Constants.Table.T_LiveService.FIELD_APPID + Redis.SPLITER;

		}

	}

	/**
	 * 表信息
	 * 
	 * @author yicai.liu<moon>
	 */
	public static final class Table
	{

		public static final class T_LiveService
		{
			public static final String	NAME			= "t_liveservice";
			public static final String	FIELD_APPID		= "appId";
			public static final String	FIELD_APPTYPE	= "appType";
		}

		public static final class T_LiveRoom
		{
			public static final String NAME = "T_LiveRoom";
		}
	}
}
