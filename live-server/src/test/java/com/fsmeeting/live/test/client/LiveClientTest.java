package com.fsmeeting.live.test.client;

import org.junit.BeforeClass;

import com.fastonz.live.sliceprotocol.controller.AppServicePrx;
import com.fastonz.live.sliceprotocol.controller.AppServicePrxHelper;

import Ice.Communicator;
import Ice.Util;

public class LiveClientTest {

	protected static Communicator ic = null;

	protected static AppServicePrx appService;

	@BeforeClass
	public static void init() {

		/**
		 * <pre>
		 * 生产环境测试 
		 *  String endpoint = String.format("%s:%s -h %s -p %s", "FMServiceIceGrid/Locator", "tcp", "120.24.85.241", "10001");
			String endpoint = String.format("%s:%s -h %s -p %s", "FMServiceIceGrid/Locator", "tcp", "192.168.5.56", "10001");
			InitializationData localInitializationData = new InitializationData();
			localInitializationData.properties = Util.createProperties();
			localInitializationData.properties.setProperty("Ice.Default.Locator", endpoint);
			ic = Util.initialize(localInitializationData);
			String str = "";
		 * </pre>
		 */
		/* 开发环境本机测试 */
		ic = Util.initialize();
		//String str = ":default -h 192.168.7.77 -p 33001";
		String str = ":default -h 192.168.5.157 -p 33001";
		appService = AppServicePrxHelper.checkedCast(ic.stringToProxy("appService" + str));
		if (null == appService)
			throw new Error("Invalid proxy");

	}

}
