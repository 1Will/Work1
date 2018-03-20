package com.yongjun.edu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yongjun.edu.entity.User;

public interface UserDao {
	/**
	 * 通过ID查询用户
	 * 
	 * @param id
	 * @return
	 */
	User queryById(long id);
	
	
	/**
	 * 查询所有用户
	 * 
	 * @param topp 查询条数
	 * @param fromm 查询起始位置
	 * @return
	 */
	List<User> queryAll(@Param("topp") int topp, @Param("fromm") int fromm);
	
	
	void update(@Param("user") User user);
	
	
}
