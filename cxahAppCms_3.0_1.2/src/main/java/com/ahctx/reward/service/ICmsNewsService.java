package com.ahctx.reward.service;

import com.ahctx.reward.entity.CmsNews;
import com.baomidou.framework.service.ISuperService;

/**
 *
 * CmsNews 表数据服务层接口
 *
 */
public interface ICmsNewsService extends ISuperService<CmsNews> {
	
	/**
	 * 删除新闻
	 * 
	 * @param newsId 新闻ID
	 * @author hs
	 * @date 2016-08-30
	 */
	void delNews(Long newsId);
	
}