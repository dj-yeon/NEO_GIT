package com.hdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.IAdminDao;
import com.hdh.vo.PolicyFundingConditionsVo;

@Service
public class PolicyFundService {

	@Autowired
	IAdminDao adminDao;

	public void insertPolicyFund (PolicyFundingConditionsVo Vo) {

		adminDao.insertPolicyFund(Vo);
	}

	public List<PolicyFundingConditionsVo> getPolicyFund() {

		return adminDao.getPolicyFund();
	}
}
