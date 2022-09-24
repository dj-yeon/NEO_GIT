package com.hdh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hdh.service.EmployeeregisterService;
import com.hdh.vo.Vo_companyemployee;
import com.hdh.vo.Vo_companyregister;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class Con_employeeregister {

	@Autowired
	EmployeeregisterService employeeregisterService;

	/*
	 * @GetMapping("/") public String doFirst() { return "/index.html"; }
	 */

	/* 사원 목록 전체 조회 */
	@GetMapping("/employeelist")
	public String doEmployeeList(Model model) {

		List<Vo_companyemployee> list = new ArrayList<>();
		list = employeeregisterService.doEmplList();

		model.addAttribute("list", list);

		System.out.println(list);

		return "employeeRegister/employee_list";
	}

	/* 사원 입력 */
	@GetMapping("/empinsert")
	public String doIns() {
		return "employeeRegister/employee_register";
	}

	@PostMapping("empinsert_exe")
	public String doInsExe(@ModelAttribute Vo_companyemployee vo_companyemployee) {
		int intI = employeeregisterService.doEmpins(vo_companyemployee);

		return "redirect:employeelist";
	}

	/* 사원 저장 */
	@PostMapping("/insertEmp_exe")
	public void doInsEmpExe(List<Vo_companyemployee> list) {
		System.out.println(" 컨트롤러에서 받는 List : " + list);
		// int intI = companyregisterService.doComEmpIns(list);
	}

	/* 직원 리스트 엑셀로 업로드 */
	@RequestMapping(value = "/employeeexcelupload.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Vo_companyemployee> readExcel(@RequestParam(value = "file") MultipartFile file) throws IOException {

		List<Vo_companyemployee> dataList = new ArrayList<>();

		String extension = FilenameUtils.getExtension(file.getOriginalFilename());

		if (!extension.equals("xlsx") && !extension.equals("xls")) {
			throw new IOException("엑셀파일만 업로드 해주세요.");
		}

		Workbook workbook = null;

		if (extension.equals("xlsx")) {
			workbook = new XSSFWorkbook(file.getInputStream());
		} else if (extension.equals("xls")) {
			workbook = new HSSFWorkbook(file.getInputStream());
		}

		Sheet worksheet = workbook.getSheetAt(0);

		// 최상단 공백이 있을때 : + 1
		int cnt = worksheet.getPhysicalNumberOfRows();

		for (int i = 1; i < cnt; i++) {

			Row row = worksheet.getRow(i);

			Vo_companyemployee vo_companyemployee = new Vo_companyemployee();
			DataFormatter formatter = new DataFormatter();

			String name_kor = formatter.formatCellValue(row.getCell(1));
			String edu = formatter.formatCellValue(row.getCell(2));
			String science_study = formatter.formatCellValue(row.getCell(3));
			String birthdate = formatter.formatCellValue(row.getCell(4));
			String experience = formatter.formatCellValue(row.getCell(5));
			String businessLicenseNumber = formatter.formatCellValue(row.getCell(6));

			vo_companyemployee.setNameKor(name_kor);
			vo_companyemployee.setEdu(edu);
			vo_companyemployee.setScienceStudy(science_study);
			vo_companyemployee.setBirthdate(birthdate);
			vo_companyemployee.setExperience(experience);
			vo_companyemployee.setBusinessLicenseNumber(businessLicenseNumber);

			if (name_kor != "" && edu != "" && science_study != "" && birthdate != "" && experience != ""
					&& businessLicenseNumber != "") {
				dataList.add(vo_companyemployee);
				System.err.println(vo_companyemployee);

				// DAO호출 = MAPPER호출 = DB에 INSERT
				employeeregisterService.insertEmployeeList(vo_companyemployee);
			}

		}

		return dataList;
	}

	/*
	 * 사원 목록 수정
	 * 
	 * @GetMapping("/empmodify") public String doMod(@RequestParam(value =
	 * "name_kor") String name_kor, Model model) {
	 * 
	 * model.addAttribute("Vo", employeeregisterService.doEmpListOne(name_kor));
	 * return "companyRegister/company_edit"; }
	 */

	/* 사원 목록 수정 */
	@GetMapping("/empmodify")
	public String doMod(@ModelAttribute Vo_companyemployee vo_companyemployee) {

		vo_companyemployee = employeeregisterService.doEmpListOne(vo_companyemployee);
		return "companyRegister/company_edit";
	}

	/* 사원 목록 수정 실행 */
	@PostMapping("/empmodify_exe")
	public String doModExe(@ModelAttribute Vo_companyemployee vo_companyemployee) {

		int intI = employeeregisterService.doEmpUp(vo_companyemployee);

		return "redirect:employeelist";
	}

	/* 회사 목록 삭제 */
	@GetMapping("/empdelete")
	public String doComDel(@ModelAttribute Vo_companyemployee vo_companyemployee) {

		int intI = employeeregisterService.doEmpDel(vo_companyemployee);

		return "redirect:employeelist";
	}

}
