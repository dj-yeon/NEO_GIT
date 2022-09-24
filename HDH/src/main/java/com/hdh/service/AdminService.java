package com.hdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.IAdminDao;
import com.hdh.vo.ExcelVo;

@Service
public class AdminService {

	@Autowired
	IAdminDao adminDao;

	public void insertExcel(List<ExcelVo> list) {

		for(ExcelVo i : list) {
			System.out.println(i);
		}

	}

	public void insertcorporateManagementAnalysis(ExcelVo Vo) {

		adminDao.insertcorporateManagementAnalysis(Vo);
	}

	public List<ExcelVo> getCorporateManagementAnalysis () {

		return adminDao.getCorporateManagementAnalysis();
	}

	public void insertCMdo (List<ExcelVo> list) {

		for(int i = 0; i < list.size(); i++ ) {
			adminDao.insertcorporateManagementAnalysis(list.get(i));
		}
	}

	public void updateCMdo (List<ExcelVo> list) {

		for(int i = 0; i < list.size(); i++ ) {
			adminDao.updateCMdo(list.get(i));
		}
	}

	public void deleteCMdo () {
		adminDao.deleteCMdo();
	}
}
