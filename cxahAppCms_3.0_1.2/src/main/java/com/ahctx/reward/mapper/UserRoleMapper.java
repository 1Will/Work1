package com.ahctx.reward.mapper;

import org.apache.ibatis.annotations.Param;

import com.ahctx.reward.entity.UserRole;
import com.ahctx.reward.entity.vo.UserJoinRole;
import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * UserRole 表数据库控制层接口
 *
 */
public interface UserRoleMapper extends AutoMapper<UserRole> {

	UserJoinRole selectUserJoRole(@Param("id") Long id);

}