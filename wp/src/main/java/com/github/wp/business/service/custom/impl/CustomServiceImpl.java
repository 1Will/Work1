package com.github.wp.business.service.custom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.custom.CustomDao;
import com.github.wp.business.pojo.custom.Bcustomerinfo;
import com.github.wp.business.service.custom.CustomService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 客户管理service实现类
 * @author 杜鹏
 * @version 1.0
 * @since 2016年08月09日
 */
@Service
public class CustomServiceImpl implements CustomService {

    @Autowired
    private CustomDao customDao;

	@Override
	public Pager<Bcustomerinfo> findPage(Bcustomerinfo custom, Pagination pagination) {
		return customDao.findPage(custom, pagination);
	}
	
	@Override
	public Bcustomerinfo findOne(Long id){
		return customDao.findOne(id);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		customDao.deleteOne(ids);
	}
	
	@Override
	public void saveOrUpdate(Bcustomerinfo custom){
		customDao.saveOrUpdate(custom);
	}
	
  
}
