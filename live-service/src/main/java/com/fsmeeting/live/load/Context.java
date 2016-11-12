package com.fsmeeting.live.load;

import org.springframework.stereotype.Component;

/**
 * 策略上下文角色：持有一个Strategy的引用
 * 
 * @author yicai.liu<moon>
 *
 */

//@Component
public class Context<T> {

	/**
	 * 策略
	 */
	private IStrategy<T> strategy;

	public Context(IStrategy<T> strategy) {
		this.strategy = strategy;
	}

	public T load(Object... params) {
		return strategy.calculate(params);
	}
}
