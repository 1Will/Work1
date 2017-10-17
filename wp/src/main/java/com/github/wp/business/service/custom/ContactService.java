package com.github.wp.business.service.custom;

import com.github.wp.business.pojo.custom.Bcontactarchives;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 联系人管理service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月3日
 */
public interface ContactService {

	public Pager<Bcontactarchives> findPage(Bcontactarchives contact, Pagination pagination);
	
	public Bcontactarchives findOne(Long id);
	
	public void saveOrUpdate(Bcontactarchives contact);
	
	public void deleteOne(Long[] ids);
}
