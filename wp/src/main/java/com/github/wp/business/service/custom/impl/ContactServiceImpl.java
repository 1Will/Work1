package com.github.wp.business.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.custom.ContactDao;
import com.github.wp.business.pojo.custom.Bcontactarchives;
import com.github.wp.business.service.custom.ContactService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 联系人管理service实现类
 * @author wangping
 * @version 1.0
 * @since 2015年9月2日, 下午4:42:14
 */
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactDao contactDao;

	@Override
	public Pager<Bcontactarchives> findPage(Bcontactarchives contact, Pagination pagination) {
		return contactDao.findPage(contact, pagination);
	}
	
	@Override
	public Bcontactarchives findOne(Long id){
		return contactDao.findOne(id);
	}
	
	@Override
	public void saveOrUpdate(Bcontactarchives contact){
		contactDao.saveOrUpdate(contact);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		contactDao.deleteOne(ids);
	}
  
}
