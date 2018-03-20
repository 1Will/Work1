package com.ahctx.reward.mapper;

import org.apache.ibatis.annotations.Param;

import com.ahctx.reward.entity.RegisterUser;
import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * User 表数据库控制层接口
 *
 */
public interface RegisterUserMapper extends AutoMapper<RegisterUser> {

	public int filterRegisterUser(@Param(value = "driverNumber") String driverNumber,
			@Param(value = "phoneNumber") String phoneNumber);
}