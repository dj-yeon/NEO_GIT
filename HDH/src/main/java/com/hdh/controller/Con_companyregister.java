package com.hdh.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hdh.service.CompanyregisterService;
import com.hdh.vo.ExcelVo;
import com.hdh.vo.Vo_companyregister;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class Con_companyregister {

	@Autowired
	CompanyregisterService companyregisterService;

	@GetMapping("/")
	public String doFirst() {
		return "/index.html";
	}
	
	/* 회사 목록 전체 조회 */
	@GetMapping("/companylist")
	public String doCompanyList(Model model) {

		List<Vo_companyregister> list = new ArrayList<>();
		list = companyregisterService.doCompanyList2();

		model.addAttribute("list", list);

		return "companyRegister/companylist";
	}

	/* 회사 입력 */
	@GetMapping("/insert")
	public String doIns() {
		return "companyRegister/companyregister";
	}

	/* 회사 입력 실행 */
	@PostMapping("/insert_exe")
	public String doInsExe(@ModelAttribute Vo_companyregister vo_companyregister) {
		System.out.println(" 컨트롤러에서 받는 Vo : " + vo_companyregister);
		int intI = companyregisterService.doCompanyIns(vo_companyregister);

		return "index";
	}

	/* 회사 목록 수정 */
	@GetMapping("/companymodify")
	public String doMod(@RequestParam(value = "businessLicenseNumber") String businessLicenseNumber, Model model) {
		// String strbusinessLicenseNumber =
		// request.getParameter("businessLicenseNumber");
		// Vo_companyregister vo_companyregister = new Vo_companyregister();
		// vo_companyregister =
		// companyregisterService.doCompanyListOne(businessLicenseNumber);
		// request.setAttribute("vo_companyregister", vo_companyregister);

		model.addAttribute("Vo", companyregisterService.doCompanyListOne(businessLicenseNumber));
		return "companyRegister/company_edit";
	}

	/* 회사 목록 수정 실행 */
	@PostMapping("/companymodify_exe")
	public String doModExe(@ModelAttribute Vo_companyregister vo_companyregister) {

		int intI = companyregisterService.doCompanyUp(vo_companyregister);

		return "redirect:companylist";
	}

	/* 회사 목록 삭제 */
	@GetMapping("/companydelete")
	public String doComDel(@RequestParam(value = "businessLicenseNumber") String businessLicenseNumber) {

		int intI = companyregisterService.doCompanyDel(businessLicenseNumber);
		log.info("intI => " + intI);
		return "redirect:companylist";
	}

	/* 산업분류코드 */
	@RequestMapping("/code.do")
	@ResponseBody
	public List<ExcelVo> codedo (String data) {
		
		System.out.println(data);
		
		// 산업분류명, 산업분류코드 가져오기
		if(data.equals("C")) {
			
			System.out.println(companyregisterService.getC());
			return companyregisterService.getC(); 
		} 
		
		if(data.equals("NOC")) {
			
			System.out.println(companyregisterService.getNoC());
			return companyregisterService.getNoC(); 
		}
		
		return null;
	}
 }
