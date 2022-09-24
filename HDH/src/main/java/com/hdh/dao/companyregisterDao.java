package com.hdh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hdh.vo.ExcelVo;
import com.hdh.vo.UserVo;
import com.hdh.vo.Vo_companyemployee;
import com.hdh.vo.Vo_companyregister;

@Mapper
public interface companyregisterDao {
	
	public List<ExcelVo> doCodeList(ExcelVo vo);

	// 회원 아이디 리스트
	public List<Vo_companyregister> doCompanyList(UserVo vo);
	
	// 전체 리스트
	public List<Vo_companyregister> doCompanyList2();
	
	// 하나의 리스트
	public Vo_companyregister doCompamyListOne(String businessLicenseNumber);
	
	// 회사 정보 수정
	public int doCompanyUp(Vo_companyregister vo_companyregister);
	
	/* 사업자 삭제 */
	public int doCompanyDel(String businessLicenseNumber);
	
	// 등록
	public int doCompanyIns(Vo_companyregister vo_companyregister);

	public List<ExcelVo> getC();
	public List<ExcelVo> getNoC();

	
	

}
