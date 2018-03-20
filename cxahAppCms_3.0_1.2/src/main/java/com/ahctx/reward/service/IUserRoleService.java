package com.ahctx.reward.service;

import com.ahctx.reward.entity.UserRole;
import com.ahctx.reward.entity.vo.UserJoinRole;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * UserRole 表数据服务层接口
 *
 */
public interface IUserRoleService extends ISuperService<UserRole> {

	/**
	 * <p>
	 * 判断是否存在角色对应的用户
	 * </p>
	 * 
	 * @param roleId
	 *            角色ID
	 * @return
	 */
	boolean existRoleUser(Long roleId);
	
	void insertRoleUser(Long roleId, Long userId);

	UserJoinRole selectUserJoRole(Long id);

}