package com.github.wp.business.service.contract;

import com.github.wp.business.pojo.qiandan.Hcontractmanagement;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 合同信息service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月11日
 */
public interface ContractService {

	public Pager<Hcontractmanagement> findPage(Hcontractmanagement contract, Pagination pagination);
	
	public Hcontractmanagement findOne(Long id);
	
	public void saveOrUpdate(Hcontractmanagement contract);
	
	public void deleteOne(Long[] ids);
	
}
