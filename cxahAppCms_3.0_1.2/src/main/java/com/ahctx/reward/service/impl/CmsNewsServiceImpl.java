package com.ahctx.reward.service.impl;

import org.springframework.stereotype.Service;

import com.ahctx.reward.mapper.CmsNewsMapper;
import com.ahctx.reward.entity.CmsNews;
import com.ahctx.reward.service.ICmsNewsService;
import com.ahctx.reward.service.support.BaseServiceImpl;
import com.baomidou.framework.annotations.Log;

/**
 *
 * CmsNews 表数据服务层接口实现类
 *
 */
@Service
public class CmsNewsServiceImpl extends BaseServiceImpl<CmsNewsMapper, CmsNews> implements ICmsNewsService {

	
	@Log("删除新闻资讯")
	@Override
	public void delNews(Long newsId) {
		//删除新闻资讯
		super.deleteById(newsId);
	}

}