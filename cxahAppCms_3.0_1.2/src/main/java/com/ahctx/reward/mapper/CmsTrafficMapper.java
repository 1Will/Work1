package com.ahctx.reward.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ahctx.reward.entity.CmsTraffic;
import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * CmsTraffic 表数据库控制层接口
 *
 */
public interface CmsTrafficMapper extends AutoMapper<CmsTraffic> {
	
	List<CmsTraffic> selectAllTrafficInfo(CmsTraffic cmsTraffic, int current, int limit);
	
	int getTrafficCount(CmsTraffic cmsTraffic);

	CmsTraffic getTrafficEntity(@Param("Id") Long Id);
	
	void deleteByTrafficId(@Param("Id") Long Id);
	
}