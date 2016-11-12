package com.fsmeeting.live.load;

import com.fsmeeting.live.common.bean.LiveService;

public class Client {

	public static void main(String[] args) {
		IStrategy<LiveService> strategy = new MinLoadProxyPicker();
		Context<LiveService> context = new Context<LiveService>(strategy);
		LiveService server = context.load();
		System.out.println(server);
	}
}
