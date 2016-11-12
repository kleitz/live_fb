package com.fsmeeting.live.load;

/**
 * 负载策略
 * 
 * @author yicai.liu<moon>
 *
 */
public interface IStrategy<T> {

	/**
	 * 
	 * @param params
	 * @return T 对象
	 */
	T calculate(Object... params);
}
