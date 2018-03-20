package com.ahctx.reward.service;

import com.ahctx.reward.entity.User;
import com.ahctx.reward.entity.vo.UserJoinRole;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends ISuperService<User> {

	User selectByLoginName(String loginName);

	void deleteUser(Long userId);

	boolean updateUser(User user, Long rid);

	boolean insertUser(User user, Long rid);

	UserJoinRole selectUserJoRole(Long id);
}