package com.fsmeeting.live.server.ice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fastonz.live.sliceprotocol.controller.AMD_AppService_getLiveSrvAddr;
import com.fastonz.live.sliceprotocol.controller.AMD_AppService_getMinLoadService;
import com.fastonz.live.sliceprotocol.controller.AMD_AppService_heartbeat;
import com.fastonz.live.sliceprotocol.controller.AMD_AppService_register;
import com.fastonz.live.sliceprotocol.controller._AppServiceDisp;
import com.fastonz.live.sliceprotocol.model.AppHeartbeatModel;
import com.fastonz.live.sliceprotocol.model.AppModel;
import com.fsmeeting.live.common.enums.Response;
import com.fsmeeting.live.server.task.factory.AppServiceTaskFactory;
import com.fsmeeting.live.server.util.WorkQueue;

import Ice.Current;

@Component
public class AppServiceMgr extends _AppServiceDisp {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AppServiceTaskFactory appServiceTaskFactory;

	@Autowired
	private WorkQueue workQueue;

	@Override
	public void getLiveSrvAddr_async(AMD_AppService_getLiveSrvAddr liveSrvAddr, int liveRoomId, Current cur) {
		if (workQueue.isBusy()) {
			liveSrvAddr.ice_response(Response.SYSTEM_BUSY.getCode(), null);
			return;
		}

		workQueue.addTask(appServiceTaskFactory.createGetLiveSrvAddrTask(liveSrvAddr, liveRoomId));
	}

	@Override
	public void getMinLoadService_async(AMD_AppService_getMinLoadService minload, int appType, String oldAddress,
			Current cur) {
		if (workQueue.isBusy()) {
			minload.ice_response(Response.SYSTEM_BUSY.getCode(), "192.168.7.77");
			return;
		}

		workQueue.addTask(appServiceTaskFactory.createGetMinLoadServiceTask(minload, appType, oldAddress));
	}

	@Override
	public void heartbeat_async(AMD_AppService_heartbeat heartbeat, AppHeartbeatModel model, Current cur) {
		if (workQueue.isBusy()) {
			heartbeat.ice_response(Response.SYSTEM_BUSY.getCode());
			return;
		}

		workQueue.addTask(appServiceTaskFactory.createHeartbeatTask(heartbeat, model));
	}

	@Override
	public void register_async(AMD_AppService_register register, AppModel model, Current cur) {

		if (workQueue.isBusy()) {
			register.ice_response(Response.SYSTEM_BUSY.getCode());
			return;
		}

		workQueue.addTask(appServiceTaskFactory.createRegistryTask(register, model));

	}

}
