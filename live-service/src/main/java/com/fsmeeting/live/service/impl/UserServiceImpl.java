package com.fsmeeting.live.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsmeeting.live.common.bean.LiveRoom;
import com.fsmeeting.live.common.bean.ResponseMsg;
import com.fsmeeting.live.common.bean.TokenInfo;
import com.fsmeeting.live.common.bean.vo.LoginRequestVO;
import com.fsmeeting.live.common.bean.vo.LoginResponseVO;
import com.fsmeeting.live.common.enums.InviteRequirement;
import com.fsmeeting.live.common.enums.Response;
import com.fsmeeting.live.common.util.IDGenerator;
import com.fsmeeting.live.common.util.TokenUtils;
import com.fsmeeting.live.mapper.UserMapper;
import com.fsmeeting.live.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public ResponseMsg<LiveRoom> getLiveRoomInfo(String liveURI) {
		ResponseMsg<LiveRoom> result = new ResponseMsg<>();
		LiveRoom room = userMapper.getLiveRoomInfo(liveURI);
		result.setData(room);
		return result;
	}

	@Override
	public ResponseMsg<LoginResponseVO> login(String liveURI, LoginRequestVO request) {

		ResponseMsg<LoginResponseVO> result = new ResponseMsg<>();
		LoginResponseVO loginResp = new LoginResponseVO();

		if (StringUtils.isBlank(request.getNickName())) {
			result.setCode(Response.NICKNAME_NOT_NULL.getCode());
			result.setMsg("昵称不能为空！");
			return result;
		}
		// 1 获取直播房间信息
		LiveRoom room = getLiveRoomInfo(liveURI).getData();

		if (null == room) {
			result.setCode(Response.ROOM_NOT_EXIST.getCode());
			result.setMsg("直播房间不存在！");
			return result;
		}

		// 2 输入校验（邀请码）
		if (InviteRequirement.YES.getCode() == room.getVerifyMode()) {
			String inviteCode = request.getInviteCode();
			if (!inviteCode.equals(room.getInviteCode())) {
				result.setCode(Response.INVALID_INVITE_CODE.getCode());
				result.setMsg("邀请码不正确！");
				return result;
			}
		}
		// 3 点数校验
		int liveId = room.getId();
		int onlineUserCount = userMapper.countOnlineUser(liveId);
		if (onlineUserCount >= room.getMaxUserCount()) {
			result.setCode(Response.ROOM_FULL.getCode());
			result.setMsg("房间人数已满！");
			return result;
		}

		// 4 分配用户ID、token
		int userId = IDGenerator.generate();
		String token = TokenUtils.generate();
		// 5 分派用户代理服务器appId
		/*
		 * LiveServer proxyServer = userMapper.pickProxyServer();// TODO 负载策略
		 * 有待商量 if (null == proxyServer) {
		 * result.setCode(Response.SERVER_NOT_EXIST.getCode());
		 * result.setMsg("代理服务器不存在！"); return result; } String appId =
		 * proxyServer.getAppId();
		 */
		String appId = "13";
		loginResp.setLiveRoomId(Integer.toString(liveId));
		loginResp.setProxyAddr(appId);
		loginResp.setToken(token);
		loginResp.setUserId(Integer.toString(userId));

		TokenInfo tokenInfo = new TokenInfo();
		tokenInfo.setAppId(Integer.parseInt(appId));
		tokenInfo.setToken(token);
		tokenInfo.setLiveId(liveId);
		tokenInfo.setUserId(userId);

		// 6 保存token信息
		userMapper.addTokenInfo(tokenInfo);

		result.setData(loginResp);
		return result;
	}

	@Override
	public boolean activeToken(int userId, String token) {
		return true;
	}

	@Override
	public boolean releaseToken(String token) {
		return true;
	}

	@Override
	public boolean releaseTokenByAppId(String appId) {
		return true;
	}

	@Override
	public boolean releaseTokenByAppIdAndLiveRoom(String appId, int liveRoomId) {
		return true;
	}

}
