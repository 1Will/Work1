package com.yongjun.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yongjun.edu.dao.UserDao;
import com.yongjun.edu.entity.User;
import com.yongjun.edu.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	// 注入Service依赖
	@Autowired
	private UserDao UserDao;

	@Override
	public User getById(long id) {
		return UserDao.queryById(id);
	}

	@Override
	public List<User> getList(int top ,int from) {
		return UserDao.queryAll(top, from);
	}

	@Override
	public void update(User user) {
		UserDao.update(user);
	}

}
