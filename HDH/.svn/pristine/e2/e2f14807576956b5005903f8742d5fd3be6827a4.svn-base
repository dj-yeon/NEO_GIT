package com.hdh.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdh.service.CompanyregisterService;
import com.hdh.service.iFinanceService;
import com.hdh.vo.FinanceVo;
import com.hdh.vo.UserVo;

@Controller
public class FinanceController {

	@Autowired
	iFinanceService financeService;

	@Autowired
	CompanyregisterService companyregisterService;

	@RequestMapping("/finance")
	public String finance(Model model, HttpSession session) {

		UserVo Vo = new UserVo();
		Vo.setId((String)session.getAttribute("id"));

		model.addAttribute("companyList", companyregisterService.doCompanyList(Vo));

		return "finance";
	}

	@RequestMapping("/insertFinance.do")
	@ResponseBody
	public void insertFinancedo (FinanceVo Vo) {
		System.out.println(Vo);
	}

}
