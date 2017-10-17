package com.github.wp.business.service.custom;

import com.github.wp.business.pojo.custom.Bcustomerinfo;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 客户信息service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月09日
 */
public interface CustomService {

	public Pager<Bcustomerinfo> findPage(Bcustomerinfo custom, Pagination pagination);
	
	public Bcustomerinfo findOne(Long id);
	
	public void saveOrUpdate(Bcustomerinfo custom);
	
	public void deleteOne(Long[] ids);
	
}
