package com.github.wp.business.dao.expense;


import com.github.wp.business.pojo.cw.CExpenseuse;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;



/**
 * <p>
 * 费用使用的dao层：杜鹏
 * <p>
 * Date: 08-29-16
 * <p>
 * Version: 1.0
 */
public interface ExpenseuseDao {

	/**
	 * 查询分页数据
	 * @param CExpenseuse
	 * @return
	 * @author 杜鹏
	 * @since 08-29-16
	 */
	public Pager<CExpenseuse> findPage(CExpenseuse use, Pagination pagination);
	
	/**
	 * @param id
	 * @return CExpenseuse 客户信息
	 * @author dupeng
	 * @since 08-29-16
	 */
	public CExpenseuse findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(CExpenseuse use);
	
}
