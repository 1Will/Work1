package com.ahctx.reward.service;

import java.util.List;

import com.ahctx.reward.entity.CmsDic;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * CmsDic 表数据服务层接口
 *
 */
public interface ICmsDicService extends ISuperService<CmsDic> {

	public List<CmsDic> getCmsDicByPid(Long pId);

}