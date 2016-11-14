package com.fsmeeting.live.queue;

public class QueueData
{

	private Class<?>	handlerClass;
	private Object		data;

	public QueueData(Object data, Class<?> handlerClass)
	{
		this.handlerClass = handlerClass;
		this.data = data;
	}

	public Class<?> getHandlerClass()
	{
		return handlerClass;
	}

	public void setHandlerClass(Class<?> handlerClass)
	{
		this.handlerClass = handlerClass;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

}