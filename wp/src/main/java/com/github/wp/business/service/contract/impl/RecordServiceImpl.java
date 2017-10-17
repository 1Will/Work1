package com.github.wp.business.service.contract.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.contract.RecordDao;
import com.github.wp.business.pojo.qiandan.HBillingrecord;
import com.github.wp.business.service.contract.RecordService;
import com.github.wp.business.vo.RecordCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 开票记录service实现类
 * @author 杜鹏
 * @version 1.0
 * @since 2016年08月23日
 */
@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordDao recordDao;

	@Override
	public Pager<RecordCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination){
		return recordDao.findPage1(code, contractname, customCode, name, pagination);
	}
	
	public HBillingrecord findOne(Long id){
		return recordDao.findOne(id);
	}
	
	public void deleteOne(Long[] ids){
		recordDao.deleteOne(ids);
	}
	
	public void saveOrUpdate(HBillingrecord record){
		recordDao.saveOrUpdate(record);
	}
	
  
}
