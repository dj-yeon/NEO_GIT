package com.hdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdh.dao.IUserDao;
import com.hdh.vo.UserVo;

@Service
public class UserService {

	@Autowired
	IUserDao userDao;

	public List<UserVo> getUserList() {

		return userDao.getUserList();
	}

	public void updateUser(UserVo Vo) {

		String[] noArray = Vo.getNo().split(",");
		String[] idArray = Vo.getId().split(",");
		String[] passwordArray = Vo.getPassword().split(",");
		String[] rollArray = Vo.getRoll().split(",");

		UserVo PVo = new UserVo();

		for(int i = 0; i < idArray.length; i++) {
			PVo.setNo(noArray[i]);
			PVo.setId(idArray[i]);
			PVo.setPassword(passwordArray[i]);
			PVo.setRoll(rollArray[i]);

			userDao.updateUser(PVo);
		}
	}
}
