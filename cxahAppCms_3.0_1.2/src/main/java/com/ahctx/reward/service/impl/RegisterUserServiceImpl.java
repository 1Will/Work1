/**
 * 
 */
package com.ahctx.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.entity.RegisterUser;
import com.ahctx.reward.mapper.RegisterUserMapper;
import com.ahctx.reward.service.IRegisterUserService;
import com.ahctx.reward.service.support.BaseServiceImpl;

/**
*
* User 表数据服务层接口实现类
*
*/
@Service
public class RegisterUserServiceImpl extends BaseServiceImpl<RegisterUserMapper, RegisterUser> implements IRegisterUserService {

	@Autowired
	public RegisterUserMapper registerUserMapper;
	
	@Override
	public boolean AddRegisterUser(RegisterUser registerUser) {
    	return super.insertSelective(registerUser);
	}

	@Override
	public int validDriverNum(RegisterUser registerUser) {
		return super.selectCount(registerUser);
	}

	@Override
	public int validUnique(String driverNumber, String phoneNumber) {
		return registerUserMapper.filterRegisterUser(driverNumber, phoneNumber);
	}

}
