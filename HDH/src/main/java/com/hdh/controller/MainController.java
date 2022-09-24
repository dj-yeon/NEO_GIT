package com.hdh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdh.service.CompanyregisterService;
import com.hdh.service.LoginService;
import com.hdh.service.UserService;
import com.hdh.vo.UserVo;
import com.hdh.vo.Vo_companyregister;

import lombok.extern.slf4j.Slf4j;

@Controller
public class MainController {

	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;

	@Autowired
	CompanyregisterService companyregisterService;

	@RequestMapping("/")
	public String index(HttpSession session) {

		return "/index";
	}

	@RequestMapping("/main")
	public String main(HttpSession session, Model model) {

		if(session.getAttribute("id") == null || session.getAttribute("id") == "" || session.getAttribute("id") == "undefined") {

			return "index";

		} else {

			// service 불러오기
			UserVo Vo = new UserVo();
			Vo.setId((String)session.getAttribute("id"));

			List<Vo_companyregister> list = companyregisterService.doCompanyList(Vo);

			// 모델에다가 쏴주기(리스트를)
			model.addAttribute("List", list);

			return "/main";

		}
	}

	@RequestMapping("/signup")
	public String signup() {

		return "/signup";
	}

	@RequestMapping("login.do")
	@ResponseBody
	public boolean logindo (UserVo Vo, HttpSession session) {

		session.setAttribute("roll", loginService.getLoginRoll(Vo));
		session.setAttribute("id", Vo.getId());

		return loginService.LoginCheck(Vo);
	}

	@RequestMapping("/logout.do")
	public String logoutdo (HttpSession session) {

		session.invalidate();

		return "/index";
	}

	@RequestMapping("signup.do")
	@ResponseBody
	public String signupdo (UserVo Vo) {

		if(Vo != null) {
			loginService.signupdo(Vo);
			return Vo.getId();
		}

		return "회원가입 실패";
	}

}
