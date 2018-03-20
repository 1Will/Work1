package com.yongjun.edu.service;

import java.util.List;

import com.yongjun.edu.entity.User;

/**
 * 业务接口：站在"使用者"角度设计接口 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
 */
public interface UserService {

	/**
	 * 查询一本用户
	 * 
	 * @param UserId
	 * @return
	 */
	User getById(long id);

	/**
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> getList(int top ,int from);
	
	/**
	 * 修改用户
	 * 
	 * @return
	 */
	void update(User user);

}
