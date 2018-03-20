/**
 * 
 */
package com.ahctx.reward.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ahctx.reward.entity.RegisterUser;
import com.ahctx.reward.mapper.RewardMapper;
import com.ahctx.reward.service.IRewardService;
import com.ahctx.reward.service.support.BaseServiceImpl;

/**
*
* User 表数据服务层接口实现类
*
*/
@Service("iRewardService")
public class RewardServiceImpl extends BaseServiceImpl<RewardMapper, RegisterUser> implements IRewardService {

	@Override
	public List<RegisterUser> selectRegisterUser(RegisterUser regUser, String startDate, String endDate,
			String dataComplete, int current, int limit) {
		return baseMapper.selectRegisterUser(regUser, startDate, endDate, dataComplete, current, limit);	
	}

	@Override
	public int getRegUserCount(RegisterUser regUser, String startDate, String endDate, String dataComplete) {
		return baseMapper.getRegUserCount(regUser, startDate, endDate, dataComplete);
	}

	@Override
	public List<?> selectAllRegisterUser(RegisterUser regUser, String startDate, String endDate, String dataComplete) {
		return baseMapper.selectAllRegisterUser(regUser, startDate, endDate, dataComplete);
	}

}
