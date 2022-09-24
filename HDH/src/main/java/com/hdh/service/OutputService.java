package com.hdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.IOutputDao;
import com.hdh.vo.CalculationVo;
import com.hdh.vo.ExcelVo;
import com.hdh.vo.UserVo;

@Service
public class OutputService {

	@Autowired
	IOutputDao outputDao;

	public List<CalculationVo> getCalculation (UserVo Vo) {

		return outputDao.getCalculation(Vo);
	}
	public List<ExcelVo> getcorporateManagementAnalysis (String data){

		return outputDao.getcorporateManagementAnalysis(data);
	}
}
