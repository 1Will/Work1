package com.ahctx.reward.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.mapper.CmsTrafficMapper;
import com.ahctx.reward.entity.CmsTraffic;
import com.ahctx.reward.service.ICmsTrafficService;
import com.ahctx.reward.service.support.BaseServiceImpl;
import com.baomidou.framework.annotations.Log;

/**
 *
 * CmsTraffic 表数据服务层接口实现类
 *
 */
@Service
public class CmsTrafficServiceImpl extends BaseServiceImpl<CmsTrafficMapper, CmsTraffic> implements ICmsTrafficService {
	@Autowired
	public CmsTrafficMapper cmsTrafficMapper;
	
	@Log("查询所有有效数据")
	@Override
	public List<CmsTraffic> selectAllTrafficInfo(CmsTraffic cmsTraffic, int current, int limit) {
		//获取一条路况数据记录
		return cmsTrafficMapper.selectAllTrafficInfo(cmsTraffic, current, limit);
	}
	
	@Log("获取所有有效数据的数量")
	@Override
	public int getTrafficCount(CmsTraffic cmsTraffic) {
		//获取一条路况数据记录
		return cmsTrafficMapper.getTrafficCount(cmsTraffic);
	}

	@Log("获取一条路况数据记录")
	@Override
	public CmsTraffic getTrafficEntity(Long Id) {
		//获取一条路况数据记录
		return cmsTrafficMapper.getTrafficEntity(Id);
	}
	
	@Log("删除实时路况")
	@Override
	public void deleteByTrafficId(Long Id) {
		//删除实时路况
		cmsTrafficMapper.deleteByTrafficId(Id);
	}
}