package com.fsmeeting.live.queue;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.config.Task;

/**
 * @author yicai.liu
 * @version
 * @date 2016年11月14日 下午8:59:53
 */
public class Worker implements Runnable
{

	private static final Logger	LOG	= LoggerFactory.getLogger(Task.class);

	private MessageQueue		queue;

	public Worker(MessageQueue queue)
	{
		this.queue = queue;
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				QueueData data = queue.poll();

				if (data != null && null != data.getData())
				{
					IWorkService service = WorkServiceFactory.getService(data.getHandlerClass());
					service.process(data);
				}
				else
				{
					TimeUnit.SECONDS.sleep(5);
				}

			}
			catch (Throwable e)
			{
				LOG.warn("catch an exception in worker.", e);
			}
		}
	}

}
