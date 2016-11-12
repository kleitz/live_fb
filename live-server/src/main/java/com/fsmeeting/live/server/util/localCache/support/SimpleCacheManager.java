package com.fsmeeting.live.server.util.localCache.support;

import org.springframework.beans.factory.InitializingBean;

import com.fsmeeting.live.server.util.localCache.Cache;
import com.fsmeeting.live.server.util.localCache.CacheManager;
import com.fsmeeting.live.server.util.localCache.impl.ConcurrentMapCache;

public class SimpleCacheManager implements InitializingBean, CacheManager {

	private Cache cache;

	/**
	 * Specify the Cache instances to use for this CacheManager.
	 */
	public void setCache(Cache cache) {
		this.cache = cache;
	}

	public Cache getCache() {
		return cache;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (cache == null) {
			cache = new ConcurrentMapCache();
		}
	}

}
