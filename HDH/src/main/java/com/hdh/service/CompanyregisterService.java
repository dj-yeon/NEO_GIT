package com.hdh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.companyregisterDao;
import com.hdh.vo.ExcelVo;
import com.hdh.vo.UserVo;
import com.hdh.vo.Vo_companyemployee;
import com.hdh.vo.Vo_companyregister;

@Service
public class CompanyregisterService {

	@Autowired
	companyregisterDao daocompanyregister;

	public List<Vo_companyregister> doCompanyList(UserVo vo) {

		List<Vo_companyregister> list = new ArrayList<>();
		list = daocompanyregister.doCompanyList(vo);

		return list;

	}


	public List<Vo_companyregister> doCompanyList2() {

		return daocompanyregister.doCompanyList2();
	}

	/* One Row Select */

	public Vo_companyregister doCompanyListOne(String businessLicenseNumber) {

		Vo_companyregister vo_companyregister = new Vo_companyregister();

		vo_companyregister = daocompanyregister.doCompamyListOne(businessLicenseNumber);
		return vo_companyregister;
	}
	
	/* 사업자 수정 */
	public int doCompanyUp(Vo_companyregister vo_companyregister) {
		
		int intI = daocompanyregister.doCompanyUp(vo_companyregister);
		
		return intI;
	}
	


	public int doCompanyIns(Vo_companyregister vo_companyregister) {

		int intI = daocompanyregister.doCompanyIns(vo_companyregister);

		return intI;
	}
	


	public void insertCompany(Vo_companyregister Vo) {

		daocompanyregister.doCompanyIns(Vo);

	}


	public int doCompanyDel(String businessLicenseNumber) {
		
		int intI = daocompanyregister.doCompanyDel(businessLicenseNumber);
		
		return intI;
	}


	public List<ExcelVo> getC() {
		
		return daocompanyregister.getC();
	}
	public List<ExcelVo> getNoC() {
		
		return daocompanyregister.getNoC();
	}
}
