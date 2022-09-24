package com.hdh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.companyregisterDao;
import com.hdh.dao.employeeregisterDao;
import com.hdh.vo.ExcelVo;
import com.hdh.vo.UserVo;
import com.hdh.vo.Vo_companyemployee;
import com.hdh.vo.Vo_companyregister;

@Service
public class EmployeeregisterService {

	@Autowired
	employeeregisterDao daoemployeeregister;
	
	public List<Vo_companyemployee> doEmplList() {
		
		return daoemployeeregister.doEmplList();
	}

	public void insertExcel(List<Vo_companyemployee> list) {

		for (Vo_companyemployee i : list) {
			System.out.println(i);
		}

	}

	public void insertEmployeeList(Vo_companyemployee vo_companyemployee) {

		daoemployeeregister.insertEmployeeList(vo_companyemployee);
	}
	
	public int doEmpins(Vo_companyemployee vo_companyemployee) {

		int intI = daoemployeeregister.doEmpins(vo_companyemployee);

		return intI;
	}

	public int doEmpUp(Vo_companyemployee vo_companyemployee) {
		
		int intI = daoemployeeregister.doEmpUp(vo_companyemployee);
		
		return intI;
	}

	public int doEmpDel(Vo_companyemployee vo_companyemployee) {
		
		int intI = daoemployeeregister.doEmpUp(vo_companyemployee);
		
		return intI;
	}

	public Vo_companyemployee doEmpListOne(Vo_companyemployee vo_companyemployee) {


		vo_companyemployee = daoemployeeregister.doEmpListOne(vo_companyemployee);
		
		return vo_companyemployee;
		
	}
	


}































