package com.fsmeeting.live.server.task.factory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastonz.live.sliceprotocol.controller.AMD_AppService_getLiveSrvAddr;
import com.fastonz.live.sliceprotocol.controller.AMD_AppService_getMinLoadService;
import com.fastonz.live.sliceprotocol.controller.AMD_AppService_heartbeat;
import com.fastonz.live.sliceprotocol.controller.AMD_AppService_register;
import com.fastonz.live.sliceprotocol.model.AppHeartbeatModel;
import com.fastonz.live.sliceprotocol.model.AppModel;
import com.fsmeeting.live.common.enums.Response;
import com.fsmeeting.live.server.task.AbstractTask;
import com.fsmeeting.live.service.IAppService;

/**
 * 任务工厂:负责生产任务
 * 
 * @author yicai.liu<moon>
 *
 */
@Component
public class AppServiceTaskFactory {

	private static final Logger LOG = LoggerFactory.getLogger(AppServiceTaskFactory.class);

	@Autowired
	private IAppService appService;

	/**
	 * 创建注册任务
	 * 
	 * @param register
	 * @param model
	 * @return
	 */
	public AbstractTask createRegistryTask(final AMD_AppService_register register, final AppModel model) {

		return new AbstractTask() {

			@Override
			public void doService() {
				try {
					boolean success = appService.register(model);
					if (!success) {
						result = Response.SERVER_REGISTERED.getCode();
					}
				} catch (Exception e) {
					result = Response.DB_EXCEPTION.getCode();

					LOG.error(getClass().getName() + " createRegistryTask execute fail:", e);
				}
				register.ice_response(result);
			}
		};
	}

	/**
	 * 创建心跳任务
	 * 
	 * @param heartbeat
	 * @param model
	 * @return
	 */
	public AbstractTask createHeartbeatTask(final AMD_AppService_heartbeat heartbeat, final AppHeartbeatModel model) {
		return new AbstractTask() {

			@Override
			public void doService() {

				try {
					boolean success = appService.heartbeat(model);
					if (!success) {
						result = Response.FAIL.getCode();
					}
				} catch (Exception e) {
					result = Response.DB_EXCEPTION.getCode();
					LOG.error(getClass().getName() + " createHeartbeatTask execute fail:", e);
				}
				heartbeat.ice_response(result);

			}
		};
	}

	/**
	 * 创建获取直播服务器地址任务
	 * 
	 * @param liveSrvAddr
	 * @param liveRoomId
	 * @return
	 */
	public AbstractTask createGetLiveSrvAddrTask(final AMD_AppService_getLiveSrvAddr liveSrvAddr,
			final int liveRoomId) {
		return new AbstractTask() {

			@Override
			public void doService() {

				String address = null;
				try {
					address = appService.getLiveSrvAddr(liveRoomId);
					if (StringUtils.isBlank(address)) {
						result = Response.EMPTY_RESULT.getCode();
					}
				} catch (Exception e) {
					result = Response.DB_EXCEPTION.getCode();
					LOG.error(getClass().getName() + " createGetLiveSrvAddrTask execute fail:", e);
				}
				liveSrvAddr.ice_response(result, address);

			}
		};
	}

	/**
	 * 
	 * 创建获取最小负载的服务任务
	 * 
	 * @param minload
	 * @param appType
	 * @param oldAddress
	 * @return
	 */
	public AbstractTask createGetMinLoadServiceTask(final AMD_AppService_getMinLoadService minload, final int appType,
			String oldAddress) {
		return new AbstractTask() {

			@Override
			public void doService() {

				String address = null;
				try {
					address = appService.getMinLoadService(appType);
					if (StringUtils.isBlank(address)) {
						result = Response.EMPTY_RESULT.getCode();
					}
				} catch (Exception e) {
					result = Response.DB_EXCEPTION.getCode();
					LOG.error(getClass().getName() + " createGetMinLoadServiceTask execute fail:", e);
				}
				minload.ice_response(result, address);

			}
		};
	}

}
