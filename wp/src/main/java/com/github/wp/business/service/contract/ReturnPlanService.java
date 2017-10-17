package com.github.wp.business.service.contract;

import com.github.wp.business.pojo.qiandan.HReturnPlan;
import com.github.wp.business.vo.PlanCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 合同信息service
 * @author dupeng
 * @version 1.0
 * @since 2016年08月17日
 */
public interface ReturnPlanService {

/*	public Pager<PlanCustom> findPage(Long customId, Pagination pagination);*/
	
	public Pager<PlanCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination);
	
	public HReturnPlan findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(HReturnPlan plan);
}
