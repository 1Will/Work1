package com.github.wp.business.service.contract;

import com.github.wp.business.pojo.qiandan.HBillingrecord;
import com.github.wp.business.vo.RecordCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 开票记录service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月23日
 */
public interface RecordService {

	
	public Pager<RecordCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination);
	
	public HBillingrecord findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(HBillingrecord record);
}
