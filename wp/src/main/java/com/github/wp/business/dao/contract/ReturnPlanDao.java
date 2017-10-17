package com.github.wp.business.dao.contract;


import com.github.wp.business.pojo.qiandan.HReturnPlan;
import com.github.wp.business.vo.PlanCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;



/**
 * 回款计划管理的dao层：杜鹏
 * Date: 08-17-16
 * Version: 1.0
 */
public interface ReturnPlanDao {

	/**
	 * 查询回款计划
	 * @param HReturnPlan
	 * @return
	 * @author 杜鹏
	 * @since 08-17-16
	 */
	/*public Pager<PlanCustom> findPage(Long customId, Pagination pagination);*/
	
	public Pager<PlanCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination);
	
	public HReturnPlan findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(HReturnPlan plan);
}
