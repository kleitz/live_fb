package com.fsmeeting.live.test.client;

import java.util.UUID;

import org.junit.Test;

import com.fastonz.live.sliceprotocol.model.AppHeartbeatModel;
import com.fastonz.live.sliceprotocol.model.AppModel;
import com.fsmeeting.live.common.enums.LiveServerType;

import Ice.StringHolder;

public class AppServiceTest extends LiveClientTest {

	@Test
	public void registry() {
		// for (int i = 0; i < 10; i++) {
		AppModel model = new AppModel();
		model.setAddress("南山 深圳");
		model.setAppId(UUID.randomUUID().toString().substring(0, 32));
		model.setAppType(0);
		model.setDesc("nike");
		model.setWeight(2000);
		int methodRes = appService.register(model);
		System.out.println(methodRes);
		// }
	}

	@Test
	public void heartbeat() {
		AppHeartbeatModel model = new AppHeartbeatModel();
		model.setAppId("12");
		model.setAppType(LiveServerType.LIVE.getCode());
		model.setCurLoad(256);
		int methodRes = appService.heartbeat(model);
		System.out.println(methodRes);

	}

	@Test
	public void getMinLoad() {

		int appType = LiveServerType.LIVE.getCode();
		String oldAddress = "192.168.1.1";
		StringHolder holder = new StringHolder();
		appService.getMinLoadService(appType, oldAddress, holder);
		System.out.println(holder.value);

	}

}
