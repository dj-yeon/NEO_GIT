package com.hdh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hdh.service.AdminService;
import com.hdh.service.PolicyFundService;
import com.hdh.service.UserService;
import com.hdh.vo.ExcelVo;
import com.hdh.vo.PolicyFundingConditionsVo;

@Controller
public class AdminController {

	@Autowired
	UserService userService;

	@Autowired
	AdminService adminService;

	@Autowired
	PolicyFundService policyFundService;

	@RequestMapping("/admin")
	public String admin (HttpSession session) {

		if("3".equals(session.getAttribute("roll"))) {

			return "/admin/admin";

		} else {

			return "/index";

		}
	}

	@RequestMapping("/user")
	public String user (Model model, HttpSession session) {

		if("3".equals(session.getAttribute("roll"))) {

			model.addAttribute("List", userService.getUserList());
			return "/admin/user";

		} else {

			return "/index";

		}
	}

	@RequestMapping("/corporateManagement")
	public String corporateManagement (Model model, HttpSession session) {

		if("3".equals(session.getAttribute("roll"))) {

			model.addAttribute("List", adminService.getCorporateManagementAnalysis());

			return "/admin/corporateManagement";
		} else {

			return "/index";

		}
	}

	/** Excel 업로드 기능  */
	@RequestMapping("/CMexcelupload.do")
	@ResponseBody
	public List<ExcelVo> readExcel(@RequestParam("file") MultipartFile file) throws IOException {

		List<ExcelVo> dataList = new ArrayList<>();

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
		int cnt = (worksheet.getPhysicalNumberOfRows() + 1);

		for (int i = 2; i < cnt; i++) {

			Row row = worksheet.getRow(i);

			ExcelVo data = new ExcelVo();
			DataFormatter formatter = new DataFormatter();

			String industrialClassificationCode = formatter.formatCellValue(row.getCell(1));
			String majorCommodity = formatter.formatCellValue(row.getCell(2));
			String increaseSales = formatter.formatCellValue(row.getCell(3));
			String earningsRatioSalesOperation = formatter.formatCellValue(row.getCell(4));
			String equityCapitalRatio = formatter.formatCellValue(row.getCell(5));
			String interestCoverageRatio = formatter.formatCellValue(row.getCell(6));
			String turnoverRateAccountsReceivable = formatter.formatCellValue(row.getCell(7));

			data.setIndustrialClassificationCode(industrialClassificationCode);
			data.setMajorCommodity(majorCommodity);
			data.setIncreaseSales(increaseSales);
			data.setEarningsRatioSalesOperation(earningsRatioSalesOperation);
			data.setEquityCapitalRatio(equityCapitalRatio);
			data.setInterestCoverageRatio(interestCoverageRatio);
			data.setTurnoverRateAccountsReceivable(turnoverRateAccountsReceivable);

			if(industrialClassificationCode != "" && majorCommodity != "" && increaseSales != "" && earningsRatioSalesOperation != "" && equityCapitalRatio != "" && interestCoverageRatio != "" && turnoverRateAccountsReceivable != "") {
				dataList.add(data);

				// adminService.insertcorporateManagementAnalysis(data);
			}
		}

		return dataList;
	}

	@RequestMapping("/insertCM.do")
	@ResponseBody
	public void insertCMdo (  @RequestParam(value = "industrialClassificationCode") List<String> industrialClassificationCode
							, @RequestParam(value = "majorCommodity") List<String> majorCommodity
							, @RequestParam(value = "increaseSales") List<String> increaseSales
							, @RequestParam(value = "earningsRatioSalesOperation") List<String> earningsRatioSalesOperation
							, @RequestParam(value = "equityCapitalRatio") List<String> equityCapitalRatio
							, @RequestParam(value = "interestCoverageRatio") List<String> interestCoverageRatio
							, @RequestParam(value = "turnoverRateAccountsReceivable") List<String> turnoverRateAccountsReceivable) {
		List<ExcelVo> list = new ArrayList<>();

		if(industrialClassificationCode.size() == majorCommodity.size()) {

			for(int i = 0; i < industrialClassificationCode.size(); i++) {
				ExcelVo PVo = new ExcelVo();

				PVo.setIndustrialClassificationCode(industrialClassificationCode.get(i));
				PVo.setMajorCommodity(majorCommodity.get(i));
				PVo.setIncreaseSales(increaseSales.get(i));
				PVo.setEarningsRatioSalesOperation(earningsRatioSalesOperation.get(i));
				PVo.setEquityCapitalRatio(equityCapitalRatio.get(i));
				PVo.setInterestCoverageRatio(interestCoverageRatio.get(i));
				PVo.setTurnoverRateAccountsReceivable(turnoverRateAccountsReceivable.get(i));

				list.add(PVo);

			}

			adminService.insertCMdo(list);
		}

	}

	@RequestMapping("/updateCM.do")
	@ResponseBody
	public void updateCMdo (  @RequestParam(value = "industrialClassificationCode") List<String> industrialClassificationCode
							, @RequestParam(value = "majorCommodity") List<String> majorCommodity
							, @RequestParam(value = "increaseSales") List<String> increaseSales
							, @RequestParam(value = "earningsRatioSalesOperation") List<String> earningsRatioSalesOperation
							, @RequestParam(value = "equityCapitalRatio") List<String> equityCapitalRatio
							, @RequestParam(value = "interestCoverageRatio") List<String> interestCoverageRatio
							, @RequestParam(value = "turnoverRateAccountsReceivable") List<String> turnoverRateAccountsReceivable
							, @RequestParam(value = "no") List<String> no) {
		List<ExcelVo> list = new ArrayList<>();

		if(industrialClassificationCode.size() == majorCommodity.size()) {

			for(int i = 0; i < industrialClassificationCode.size(); i++) {
				ExcelVo PVo = new ExcelVo();

				PVo.setIndustrialClassificationCode(industrialClassificationCode.get(i));
				PVo.setMajorCommodity(majorCommodity.get(i));
				PVo.setIncreaseSales(increaseSales.get(i));
				PVo.setEarningsRatioSalesOperation(earningsRatioSalesOperation.get(i));
				PVo.setEquityCapitalRatio(equityCapitalRatio.get(i));
				PVo.setInterestCoverageRatio(interestCoverageRatio.get(i));
				PVo.setTurnoverRateAccountsReceivable(turnoverRateAccountsReceivable.get(i));
				PVo.setNo(no.get(i));

				list.add(PVo);

			}

			adminService.updateCMdo(list);
		}

	}

	@RequestMapping("/deleteCM.do")
	@ResponseBody
	public void deleteCMdo () {
		adminService.deleteCMdo();
	}

	@RequestMapping("/policyFundManagement")
	public String policyFundManagement (HttpSession session, Model model) {

		if("3".equals(session.getAttribute("roll"))) {

			model.addAttribute("List", policyFundService.getPolicyFund());
			return "/admin/policyFundManagement";

		} else {

			return "/index";

		}

	}

	@RequestMapping("/insertPolicyFund.do")
	@ResponseBody
	public void insertPolicyFunddo (PolicyFundingConditionsVo Vo) {
		System.out.println(Vo);

		policyFundService.insertPolicyFund(Vo);
	}


}
