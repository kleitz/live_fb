package com.fsmeeting.live.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fsmeeting.live.common.bean.AppHeartbeatModel;
import com.fsmeeting.live.common.bean.AppModel;
import com.fsmeeting.live.common.enums.LiveServerType;
import com.fsmeeting.live.service.IAppService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class AppServiceTest
{
	private static final Logger	LOG	= LoggerFactory.getLogger(AppServiceTest.class);
	private static final Random	RMD	= new Random();

	@Autowired
	private IAppService			appService;

	@Test
	public void registry() throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			AppModel model = new AppModel();
			model.setAddress("南山 深圳");
			model.setAppId("20161112:" + UUID.randomUUID().toString().substring(0, 32));
			model.setAppType(RMD.nextInt(3));
			model.setDesc("nike");
			model.setWeight(2000);
			boolean methodRes = appService.register(model);
			System.out.println(methodRes);
		}
	}

	@Test
	public void heartbeat() throws Exception
	{
		for (int i = 0; i < 10; i++)
		{
			AppHeartbeatModel model = new AppHeartbeatModel();
			model.setAppId("20161112:65b568d0-1478-44a1-8f20-381f669e");
			model.setAppType(LiveServerType.UNKNOWN.getCode());
			model.setCurLoad(RMD.nextInt(2000));
			appService.heartbeat(model);

			long sleep = RMD.nextInt(10);
			LOG.info(String.valueOf(sleep));
			TimeUnit.SECONDS.sleep(sleep);
		}

	}

	@Test
	public void getMinLoad() throws Exception
	{
		List<String> appList = new ArrayList<>();
		appList.add("20161112:820bacf2-d2bf-486b-b1fa-757c6c1e");
		appList.add("20161112:e10b2f87-706d-4d38-87ca-9d24acd2");
		appList.add("20161112:4651fa20-36b4-43d1-ab60-1e16cf15");
		appList.add("20161112:1db5aec6-939a-46ba-a65e-334d8ec0");
		appList.add("20161112:d1aa2002-e2c4-4ec8-bd20-963412bc");
		appList.add("20161112:2f14683c-1ab0-472f-9826-bf4c6ec7");
		appList.add("20161112:7f228b58-69ac-4855-93fc-1dd7864d");
		appList.add("20161112:288f615e-5e9e-4671-acc7-fdb79c71");
		for (String appId : appList)
		{
			AppHeartbeatModel model = new AppHeartbeatModel();
			model.setAppId(appId);
			model.setAppType(LiveServerType.UNKNOWN.getCode());
			model.setCurLoad(RMD.nextInt(2000));
			appService.heartbeat(model);
		}
		appService.getMinLoadService(LiveServerType.UNKNOWN.getCode());

	}

}
