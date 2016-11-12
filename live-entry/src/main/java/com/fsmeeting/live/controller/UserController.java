package com.fsmeeting.live.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fsmeeting.live.common.bean.LiveRoom;
import com.fsmeeting.live.common.bean.ResponseMsg;
import com.fsmeeting.live.common.bean.vo.LoginRequestVO;
import com.fsmeeting.live.common.bean.vo.LoginResponseVO;
import com.fsmeeting.live.common.enums.InviteRequirement;
import com.fsmeeting.live.service.IUserService;

@Controller
@RequestMapping("/live")
public class UserController {

	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/{liveURI}", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView getLiveRoomInfo(@PathVariable String liveURI, HttpServletResponse resp) throws IOException {
		ModelAndView result = new ModelAndView();
		ResponseMsg<LiveRoom> response = userService.getLiveRoomInfo(liveURI);
		LiveRoom room = response.getData();
		if (null == room) {
			result.addObject("msg", "直播URL非法授权");
			result.setViewName("error");
			return result;
		}
		result.addObject("room", room);
		if (InviteRequirement.YES.equals(room.getVerifyMode())) {
			result.setViewName("login_invitation");
		} else {
			result.setViewName("login_nickname");
		}
		return result;
	}

	@RequestMapping(value = "/{liveURI}/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg<LoginResponseVO> login(@PathVariable String liveURI, LoginRequestVO request,
			BindingResult validateResult) {

		return userService.login(liveURI, request);
	}
}
