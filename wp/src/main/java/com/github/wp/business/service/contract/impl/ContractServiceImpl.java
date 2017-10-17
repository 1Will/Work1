package com.github.wp.business.service.contract.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.contract.ContractDao;
import com.github.wp.business.pojo.qiandan.Hcontractmanagement;
import com.github.wp.business.service.contract.ContractService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 合同管理service实现类
 * @author 杜鹏
 * @version 1.0
 * @since 2016年08月11日
 */
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

	@Override
	public Pager<Hcontractmanagement> findPage(Hcontractmanagement contract, Pagination pagination) {
		return contractDao.findPage(contract, pagination);
	}
	
	@Override
	public Hcontractmanagement findOne(Long id){
		return contractDao.findOne(id);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		contractDao.deleteOne(ids);
	}
	
	@Override
	public void saveOrUpdate(Hcontractmanagement contract){
		contractDao.saveOrUpdate(contract);
	}
	
  
}
