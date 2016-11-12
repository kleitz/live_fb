package com.fsmeeting.live.server.util;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class GlobalVar {

	private static Logger logger = Logger.getLogger(GlobalVar.class);

	public static int workingQueue = 0;

	public static int requestNumbers = 0;

	public static int rejectNumbers = 0;

	public static int receiveNumbers = 0;

	final static Timer timer = new Timer();

	public GlobalVar() {
		startTimer();
	}

	public static void startTimer() {

		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				logger.info(" requestNumbers : " + requestNumbers + "; receiveNumbers : " + receiveNumbers
						+ "; rejectNumbers : " + rejectNumbers);

			}
		}, 0, 150000);

	}
	
}
