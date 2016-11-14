package com.fsmeeting.live.queue;
public class WorkServiceFactory {

	public static IWorkService getService(Class<?> clazz) {
		try {
			return (IWorkService) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}