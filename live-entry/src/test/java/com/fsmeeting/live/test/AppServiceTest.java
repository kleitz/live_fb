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
			model.setAppType(0);
			model.setDesc("nike");
			model.setWeight(2000);
			boolean methodRes = appService.register(model);
			System.out.println(methodRes);
		}
	}

	@Test
	public void heartbeat() throws Exception
	{
		for (int i = 0; i < 20; i++)
		{
			AppHeartbeatModel model = new AppHeartbeatModel();
			model.setAppId("20161112:65b568d0-1478-44a1-8f20-381f669e");
			model.setAppType(LiveServerType.UNKNOWN.getCode());
			model.setCurLoad(RMD.nextInt(2000));
			appService.heartbeat(model);

			long sleep = RMD.nextInt(20);
			LOG.info(String.valueOf(sleep));
			TimeUnit.SECONDS.sleep(sleep);
		}

	}

	@Test
	public void getMinLoad() throws Exception
	{
		List<String> appList = new ArrayList<>();
		appList.add("20161112:65b568d0-1478-44a1-8f20-381f669e");
		appList.add("20161112:d45f2237-2e40-4018-a9e3-3f2809cc");
		appList.add("20161112:e2815a6b-f922-4f8b-b3b3-20084b61");
		appList.add("20161112:0020c2c6-9be6-4f6a-88f2-a88df9d4");
		appList.add("20161112:88aece19-b10b-48c0-bd0a-9ee82900");
		appList.add("20161112:6ba83011-b882-4442-a344-7ce0a2a6");
		appList.add("20161112:bec87cf5-82bc-4a2b-a123-83755dfb");
		appList.add("20161112:4eee2b0d-8f14-404f-9370-564440e0");
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
