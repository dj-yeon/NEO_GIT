package com.hdh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.ILoginDao;
import com.hdh.vo.UserVo;

@Service
public class LoginService {
	
	@Autowired
	ILoginDao LoginDao;
	
	public boolean LoginCheck (UserVo Vo) {
		
		return LoginDao.LoginCheck(Vo);
	}
	
	public String getLoginRoll (UserVo Vo) {
		
		return LoginDao.getLoginRoll(Vo);
	}
	
	public void signupdo (UserVo Vo) {
		
		LoginDao.signupdo(Vo);
	}
}
