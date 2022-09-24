package com.hdh.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hdh.service.OutputService;
import com.hdh.vo.CalculationVo;
import com.hdh.vo.UserVo;
import com.hdh.vo.Vo_companyregister;

@Controller
public class OutputController {

	@Autowired
	OutputService outputService;

	@RequestMapping("/output")
	public String output(HttpSession session, Vo_companyregister Vo, Model model) {

		UserVo UVo = new UserVo();

		UVo.setId((String)session.getAttribute("id"));
		UVo.setBusinessLicenseNumber(Vo.getBusinessLicenseNumber());
		// TODO 연도 추가필요

		List<CalculationVo> list = outputService.getCalculation(UVo);
		String data = list.get(0).getIndustrialClassificationCode();

		model.addAttribute("List", list);
		model.addAttribute("AvgList", outputService.getcorporateManagementAnalysis(data));

		return "/output";
	}
}
