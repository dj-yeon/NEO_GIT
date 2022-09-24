package com.hdh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hdh.vo.ExcelVo;
import com.hdh.vo.UserVo;
import com.hdh.vo.Vo_companyemployee;
import com.hdh.vo.Vo_companyregister;

@Mapper
public interface employeeregisterDao {
	
	// 사원 추가(엑셀)
	public int insertEmployeeList(Vo_companyemployee vo_companyemployee);
	// 사원 추가(손으로)
	public int doEmpins(Vo_companyemployee vo_companyemployee);


	public List<Vo_companyemployee> doEmplList();
	
	public int doEmpUp(Vo_companyemployee vo_companyemployee);
	
	public int doEmpDel(Vo_companyemployee vo_companyemployee);
	
	public Vo_companyemployee doEmpListOne(Vo_companyemployee vo_companyemployee);

	
	
}
