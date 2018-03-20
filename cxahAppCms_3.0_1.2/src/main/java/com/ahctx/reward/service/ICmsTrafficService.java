package com.ahctx.reward.service;

import java.util.List;

import com.ahctx.reward.entity.CmsTraffic;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * CmsTraffic 表数据服务层接口
 *
 */
public interface ICmsTrafficService extends ISuperService<CmsTraffic> {

	/**
	 * 查询所有有效数据
	 * 
	 * @param cmsTraffic 实时路况实体对象作为查询条件
	 * 		  current 当前页
	 *        limit 每页显示数量
	 * @author hs
	 * @date 2016-09-02
	 */
	List<CmsTraffic> selectAllTrafficInfo(CmsTraffic cmsTraffic, int current, int limit);
	
	/**
	 * 获取所有有效数据的数量
	 * 
	 * @param cmsTraffic 实时路况实体对象作为查询条件
	 * @author hs
	 * @date 2016-09-02
	 */
	int getTrafficCount(CmsTraffic cmsTraffic);
	
	/**
	 * 获取一条路况数据记录
	 * 
	 * @param Id 实时路况编号
	 * @author hs
	 * @date 2016-09-02
	 */
	CmsTraffic getTrafficEntity(Long Id);
	
	/**
	 * 删除实时路况
	 * 
	 * @param Id 实时路况编号
	 * @author hs
	 * @date 2016-09-02
	 */
	void deleteByTrafficId(Long Id);
}