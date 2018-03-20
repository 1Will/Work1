package com.ahctx.reward.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.entity.User;
import com.ahctx.reward.entity.UserRole;
import com.ahctx.reward.entity.vo.UserJoinRole;
import com.ahctx.reward.mapper.UserMapper;
import com.ahctx.reward.service.IRoleService;
import com.ahctx.reward.service.IUserRoleService;
import com.ahctx.reward.service.IUserService;
import com.ahctx.reward.service.support.BaseServiceImpl;
import com.baomidou.framework.annotations.Log;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private IRoleService  roleService;
	
	@Autowired
	private IUserRoleService  userRoleService;
	
    @Log("登录")
	@Override
	public User selectByLoginName(String loginName) {
		User user = new User();
		user.setLoginName(loginName);
		return super.selectOne(user);
	}

    @Log("删除用户")
	@Override
	public void deleteUser(Long userId) {
		//删除用户角色，再删除用户
		roleService.deleteByUserId(userId);
		super.deleteById(userId);
	}

    @Log("添加用户")
    @Override
    public boolean insertSelective(User entity) {
    	return super.insertSelective(entity);
    }

	@Override
	public boolean updateUser(User user, Long rid) {
		UserRole ur = new UserRole();
		ur.setUid(user.getId());
		userRoleService.deleteSelective(ur);
		userRoleService.insertRoleUser(user.getId(), rid);
		return super.updateSelectiveById(user);
	}

	@Override
	public boolean insertUser(User user, Long rid) {
		user.setCrTime(new Date());
		user.setLastTime(user.getCrTime());
		insertSelective(user);
		UserRole ur = new UserRole();
		ur.setUid(user.getId());
		ur.setRid(rid);
		return userRoleService.insert(ur);
	}

	@Override
	public UserJoinRole selectUserJoRole(Long id) {
		return userRoleService.selectUserJoRole(id);
	}
}