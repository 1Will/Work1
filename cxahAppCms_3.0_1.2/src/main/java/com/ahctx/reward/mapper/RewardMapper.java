package com.ahctx.reward.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ahctx.reward.entity.RegisterUser;
import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * User 表数据库控制层接口
 *
 */
public interface RewardMapper extends AutoMapper<RegisterUser> {

	public List<RegisterUser> selectRegisterUser(@Param("regUser") RegisterUser regUser,
			@Param("startDate") String startDate, @Param("endDate") String endDate,
			@Param("dataComplete") String dataComplete, @Param("current") int current, @Param("limit") int limit);

	public int getRegUserCount(@Param("regUser") RegisterUser regUser, @Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("dataComplete") String dataComplete);

	public List<?> selectAllRegisterUser(@Param("regUser") RegisterUser regUser, @Param("startDate") String startDate,
			@Param("endDate") String endDate, @Param("dataComplete") String dataComplete);
}