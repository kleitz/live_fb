package com.fsmeeting.live.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 消息队列
 * 
 * @author yicai.liu
 * @version
 * @date 2016年11月14日 下午8:49:14
 */
public class MessageQueue
{

	/**
	 * 队列的容量默认值
	 */
	private static final int	DEFAULT_CAPACITY	= 5000;

	/**
	 * 消费设备信息的线程数
	 */
	private static final int	DEFAULT_THREADS		= 10;

	/**
	 * 队列的容量,默认为5000
	 */
	private int					capacity			= DEFAULT_CAPACITY;

	/**
	 * 消费设备信息的线程数
	 */
	private int					threads				= DEFAULT_THREADS;

	/**
	 * 队列
	 */
	private Queue<QueueData>	queue				= null;

	/**
	 * 线程池
	 */
	private ExecutorService		executor;

	public MessageQueue(int capacity)
	{
		setCapacity(capacity);

		queue = new ArrayBlockingQueue<>(capacity);
	}

	/**
	 * @Description 初始化函数
	 */
	public void init()
	{
		executor = Executors.newFixedThreadPool(threads);
		for (int i = 0; i < threads; i++)
		{
			executor.submit(new Worker(this));
		}

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run()
			{
				executor.shutdown();
			}
		}));

	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public int getThreads()
	{
		return threads;
	}

	public void setThreads(int threads)
	{
		this.threads = threads;
	}

	public ExecutorService getExecutor()
	{
		return executor;
	}

	public void setExecutor(ExecutorService executor)
	{
		this.executor = executor;
	}

	public boolean offer(QueueData data)
	{
		return queue.offer(data);
	}

	public QueueData poll()
	{
		return queue.poll();
	}
}
