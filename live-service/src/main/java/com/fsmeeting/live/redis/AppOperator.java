package com.fsmeeting.live.redis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.fsmeeting.live.common.Constants;
import com.fsmeeting.live.common.bean.AppHeartbeatModel;
import com.fsmeeting.live.common.bean.LiveService;

/**
 * App redis 操作
 * 
 * @author yicai.liu
 * @version
 * @date 2016年11月13日 下午10:11:20
 */
public class AppOperator
{

	private static final Logger								LOG	= LoggerFactory.getLogger(AppOperator.class);

	@Autowired
	private RedisTemplate<String, Map<String, LiveService>>	redisTemplate;

	@Autowired
	private RedisTemplate<String, Integer>					redisIntValue;

	public void register(LiveService liveService)
	{
		ValueOperations<String, Map<String, LiveService>> valueOper = redisTemplate.opsForValue();

		Map<String, LiveService> services = valueOper.get(Constants.Redis.Key.SERVICE_LIST);

		if (null == services)
		{
			LOG.info("第一次注册");
			services = new HashMap<>();
		}
		services.put(liveService.getAppId(), liveService);
		valueOper.set(Constants.Redis.Key.SERVICE_LIST, services);
	}

	public void heartbeat(AppHeartbeatModel model)
	{

		String appId = model.getAppId();
		int curLoad = model.getCurLoad();

		// <appId,curLoad>
		ValueOperations<String, Integer> intValueOper = redisIntValue.opsForValue();

		if (null == intValueOper.get(Constants.Redis.Key.SERVICE_ACTIVE_PRIFIXER + appId))
		{
			LOG.info(Constants.Redis.Key.SERVICE_ACTIVE_PRIFIXER + appId + " not exist");
		}
		else
		{
			LOG.info(Constants.Redis.Key.SERVICE_ACTIVE_PRIFIXER + appId + " binggo");
		}

		intValueOper.set(Constants.Redis.Key.SERVICE_ACTIVE_PRIFIXER + appId, curLoad, Constants.HEATBEAT_EXPIRE_SECOND,
				TimeUnit.SECONDS);

		/**
		 * 更新全量缓存
		 */
		ValueOperations<String, Map<String, LiveService>> mapValueOper = redisTemplate.opsForValue();
		Map<String, LiveService> maps = mapValueOper.get(Constants.Redis.Key.SERVICE_LIST);
		LiveService service = maps.get(appId);
		if (null != service)
		{
			service.setCurLoad(curLoad);
			service.setLastActiveTime(new Date());
			mapValueOper.set(Constants.Redis.Key.SERVICE_LIST, maps);
		}

	}

	public String getMinLoadService(int appType)
	{
		/**
		 * 获取服务全量缓存
		 */
		ValueOperations<String, Map<String, LiveService>> mapValueOper = redisTemplate.opsForValue();
		Map<String, LiveService> maps = mapValueOper.get(Constants.Redis.Key.SERVICE_LIST);
		LOG.info("服务全量缓存:" + maps.size());
		/**
		 * 获取服务活跃缓存
		 */
		ValueOperations<String, Integer> intValueOper = redisIntValue.opsForValue();

		Set<String> appIds = maps.keySet();

		for (Iterator<String> itr = appIds.iterator(); itr.hasNext();)
		{
			String key = itr.next();
			LiveService service = maps.get(key);
			if (null == intValueOper.get(Constants.Redis.Key.SERVICE_ACTIVE_PRIFIXER + key)
					|| appType != service.getAppType())
			{
				itr.remove();
			}
		}

		List<Map.Entry<String, LiveService>> list = new ArrayList<>(maps.entrySet());

		if (list.size() <= 0)
		{
			return null;
		}

		Collections.sort(list, new Comparator<Map.Entry<String, LiveService>>() {

			@Override
			public int compare(Entry<String, LiveService> left, Entry<String, LiveService> right)
			{

				BigDecimal leftLoadRate = new BigDecimal(Integer.toString(left.getValue().getCurLoad()))
						.divide(new BigDecimal(Integer.toString(left.getValue().getLoad())), 4, RoundingMode.HALF_UP);

				BigDecimal rightLoadRate = new BigDecimal(Integer.toString(right.getValue().getCurLoad()))
						.divide(new BigDecimal(Integer.toString(right.getValue().getLoad())), 4, RoundingMode.HALF_UP);

				return leftLoadRate.compareTo(rightLoadRate);
			}

		});
		for (Map.Entry<String, LiveService> item : list)
		{
			LOG.info(new BigDecimal(item.getValue().getCurLoad())
					.divide(new BigDecimal(item.getValue().getLoad()), 4, RoundingMode.HALF_UP).toString());
		}
		LOG.info("Pick the first one:" + new BigDecimal(list.get(0).getValue().getCurLoad())
				.divide(new BigDecimal(list.get(0).getValue().getLoad()), 4, RoundingMode.HALF_UP).toString());
		return list.get(0).getValue().getAddress();
	}
}
