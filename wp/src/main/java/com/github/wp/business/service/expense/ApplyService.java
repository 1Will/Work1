package com.github.wp.business.service.expense;

import com.github.wp.business.pojo.cw.CExpenseapply;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 费用申请service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月26日
 */
public interface ApplyService {

	public Pager<CExpenseapply> findPage(CExpenseapply apply, Pagination pagination);
	
	public CExpenseapply findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(CExpenseapply apply);
	
}
