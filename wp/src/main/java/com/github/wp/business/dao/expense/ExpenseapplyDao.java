package com.github.wp.business.dao.expense;


import com.github.wp.business.pojo.cw.CExpenseapply;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;



/**
 * <p>
 * 费用申请的dao层：杜鹏
 * <p>
 * Date: 08-26-16
 * <p>
 * Version: 1.0
 */
public interface ExpenseapplyDao {

	/**
	 * 查询分页数据
	 * @param CExpenseapply
	 * @return
	 * @author 杜鹏
	 * @since 08-26-16
	 */
	public Pager<CExpenseapply> findPage(CExpenseapply apply, Pagination pagination);
	
	/**
	 * @param id
	 * @return CExpenseapply 客户信息
	 * @author dupeng
	 * @since 08-26-16
	 */
	public CExpenseapply findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(CExpenseapply apply);
	
}
