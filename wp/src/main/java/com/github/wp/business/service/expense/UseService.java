package com.github.wp.business.service.expense;

import com.github.wp.business.pojo.cw.CExpenseuse;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 费用申请service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月26日
 */
public interface UseService {

	public Pager<CExpenseuse> findPage(CExpenseuse use, Pagination pagination);
	
	public CExpenseuse findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(CExpenseuse use);
	
}
