package com.ahctx.reward.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.entity.Role;
import com.ahctx.reward.entity.UserRole;
import com.ahctx.reward.mapper.RoleMapper;
import com.ahctx.reward.mapper.UserRoleMapper;
import com.ahctx.reward.service.IRoleService;
import com.ahctx.reward.service.support.BaseServiceImpl;

/**
 *
 * Role 表数据服务层接口实现类
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public void deleteByUserId(Long userId) {
		UserRole ur = new UserRole();
		ur.setUid(userId);
		userRoleMapper.deleteSelective(ur);
	}
}