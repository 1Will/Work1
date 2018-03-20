package com.ahctx.reward.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.mapper.CmsDicMapper;
import com.ahctx.reward.entity.CmsDic;
import com.ahctx.reward.service.ICmsDicService;
import com.ahctx.reward.service.support.BaseServiceImpl;

/**
 *
 * CmsDic 表数据服务层接口实现类
 *
 */
@Service
public class CmsDicServiceImpl extends BaseServiceImpl<CmsDicMapper, CmsDic> implements ICmsDicService {

	@Autowired
	private CmsDicMapper cmsDicMapper;
	
	@Override
	public List<CmsDic> getCmsDicByPid(Long pId) {
		return cmsDicMapper.getCmsDicByPid(pId);
	}

}