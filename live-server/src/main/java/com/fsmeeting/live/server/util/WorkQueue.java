package com.fsmeeting.live.server.util;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fsmeeting.live.server.task.AbstractTask;

@Component
public class WorkQueue {

	/**
	 * 最大等待任务数
	 */
	private int maxWaitingTasks;

	/**
	 * 工作线程数
	 */
	private int workingThreads;

	/**
	 * 任务数
	 */
	private static List<AbstractTask> tasks = new LinkedList<AbstractTask>();

	private static Logger logger = Logger.getLogger(WorkQueue.class);

	public WorkQueue() {
		this.maxWaitingTasks = 500;
		this.workingThreads = 50;
	}

	@PostConstruct
	public void init() {
		final ExecutorService workerPool = Executors.newCachedThreadPool();
		for (int i = 0; i < workingThreads; i++) {
			Worker worker = new Worker();
			workerPool.execute(worker);
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				workerPool.shutdown();
			}
		}));
	}

	public boolean isBusy() {
		GlobalVar.requestNumbers++;
		if (tasks.size() > maxWaitingTasks) {
			GlobalVar.rejectNumbers++;
			logger.info("system is busy... current waiting tasks : " + tasks.size());
			return true;
		}

		return false;
	}

	public void addTask(AbstractTask task) {
		synchronized (tasks) {
			tasks.add(task);
			GlobalVar.receiveNumbers++;
			logger.info("add task success; current tasks:" + tasks.size());
			tasks.notify();
		}
	}

	private class Worker implements Runnable {

		@Override
		public void run() {

			AbstractTask task;
			while (true) {
				synchronized (tasks) {
					while (tasks.isEmpty()) {
						try {
							tasks.wait();
						} catch (InterruptedException ignored) {
							logger.error("", ignored);
						}
					}
				}
				try {
					task = tasks.remove(0);
					task.doService();
				} catch (Exception e) {
					logger.error("execute task Exception", e);
				}
			}
		}

	}

	public int getMaxWaitingTasks() {
		return maxWaitingTasks;
	}

	public int getWorkingThreads() {
		return workingThreads;
	}

	public int getTasksNum() {
		return tasks.size();
	}

}
