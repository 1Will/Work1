package com.ahctx.reward.service;

import com.ahctx.reward.entity.RegisterUser;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * RegisterUser 表数据服务层接口
 *
 */
public interface IRegisterUserService extends ISuperService<RegisterUser> {

	public boolean AddRegisterUser(RegisterUser registerUser);
	
	public int validDriverNum(RegisterUser registerUser);
	
	public int validUnique(String driverNumber, String phoneNumber);

}