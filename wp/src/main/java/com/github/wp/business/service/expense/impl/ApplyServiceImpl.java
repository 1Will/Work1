package com.github.wp.business.service.expense.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.expense.ExpenseapplyDao;
import com.github.wp.business.pojo.cw.CExpenseapply;
import com.github.wp.business.service.expense.ApplyService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 费用申请service实现类
 * @author 杜鹏
 * @version 1.0
 * @since 2016年08月26日
 */
@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ExpenseapplyDao applyDao;

	@Override
	public Pager<CExpenseapply> findPage(CExpenseapply apply, Pagination pagination){
		return applyDao.findPage(apply, pagination);
	}
	
	@Override
	public CExpenseapply findOne(Long id){
		return applyDao.findOne(id);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		applyDao.deleteOne(ids);
	}
	
	@Override
	public void saveOrUpdate(CExpenseapply apply){
		applyDao.saveOrUpdate(apply);
	}
	
  
}
