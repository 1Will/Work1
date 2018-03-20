package com.ahctx.reward.service;

import java.util.List;

import com.ahctx.reward.entity.RegisterUser;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * RegisterUser 表数据服务层接口
 *
 */
public interface IRewardService extends ISuperService<RegisterUser> {

	public List<RegisterUser> selectRegisterUser(RegisterUser regUser, String startDate, String endDate, String dataComplete, int current, int limit);

	public int getRegUserCount(RegisterUser regUser, String startDate, String endDate, String dataComplete);

	public List<?> selectAllRegisterUser(RegisterUser regUser, String startDate, String endDate, String dataComplete);
}