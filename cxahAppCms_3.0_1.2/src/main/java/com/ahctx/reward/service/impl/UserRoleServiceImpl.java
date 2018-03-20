package com.ahctx.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.entity.UserRole;
import com.ahctx.reward.entity.vo.UserJoinRole;
import com.ahctx.reward.mapper.UserRoleMapper;
import com.ahctx.reward.service.IUserRoleService;
import com.ahctx.reward.service.support.BaseServiceImpl;

/**
 *
 * UserRole 表数据服务层接口实现类
 *
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

	@Autowired
	public UserRoleMapper userRoleMapper;
	
	@Override
	public boolean existRoleUser( Long roleId ) {
		UserRole ur = new UserRole();
		ur.setRid(roleId);
		int rlt = baseMapper.selectCount(ur);
		return rlt >= 1;
	}

	@Override
	public void insertRoleUser(Long userId, Long roleId) {
		UserRole ur = new UserRole();
		ur.setRid(roleId);
		ur.setUid(userId);
		baseMapper.insert(ur);
	}

	@Override
	public UserJoinRole selectUserJoRole(Long id) {
		return userRoleMapper.selectUserJoRole(id);
	}

}