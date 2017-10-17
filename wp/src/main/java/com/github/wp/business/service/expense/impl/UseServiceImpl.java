package com.github.wp.business.service.expense.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.expense.ExpenseuseDao;
import com.github.wp.business.pojo.cw.CExpenseuse;
import com.github.wp.business.service.expense.UseService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 费用申请service实现类
 * @author 杜鹏
 * @version 1.0
 * @since 2016年08月26日
 */
@Service
public class UseServiceImpl implements UseService {

    @Autowired
    private ExpenseuseDao useDao;

	@Override
	public Pager<CExpenseuse> findPage(CExpenseuse use, Pagination pagination){
		return useDao.findPage(use, pagination);
	}
	
	@Override
	public CExpenseuse findOne(Long id){
		return useDao.findOne(id);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		useDao.deleteOne(ids);
	}
	
	@Override
	public void saveOrUpdate(CExpenseuse use){
		useDao.saveOrUpdate(use);
	}
	
  
}
