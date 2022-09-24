package com.hdh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdh.service.UserService;
import com.hdh.vo.UserVo;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping("updateUser.do")
	@ResponseBody
	public void updateUser (UserVo Vo) {

		userService.updateUser(Vo);

	}
}
