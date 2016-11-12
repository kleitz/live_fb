package com.fsmeeting.live.load;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fsmeeting.live.common.bean.LiveService;
import com.fsmeeting.live.mapper.UserMapper;

/**
 * 代理服务器最小策略
 * 
 * @author yicai.liu<moon>
 *
 */
@Component("minLoadProxy")
public class MinLoadProxyPicker implements IStrategy<LiveService> {

	@Autowired
	private UserMapper userMapper;

	@Override
	public LiveService calculate(Object... params) {
		return userMapper.pickProxyServer();
	}

}
