package com.ahctx.reward.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ahctx.reward.entity.Permission;
import com.ahctx.reward.entity.vo.MenuVO;
import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * Permission 表数据库控制层接口
 *
 */
public interface PermissionMapper extends AutoMapper<Permission> {

	List<MenuVO> selectMenuByUserId(@Param("userId") Long userId, @Param("pid") Long pid);

	List<Permission> selectAllByUserId(@Param("userId") Long userId);

}