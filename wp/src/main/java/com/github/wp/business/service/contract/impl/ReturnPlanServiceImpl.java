package com.github.wp.business.service.contract.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.contract.ReturnPlanDao;
import com.github.wp.business.pojo.qiandan.HReturnPlan;
import com.github.wp.business.service.contract.ReturnPlanService;
import com.github.wp.business.vo.PlanCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 合同管理service实现类
 * @author 杜鹏
 * @version 1.0
 * @since 2016年08月11日
 */
@Service
public class ReturnPlanServiceImpl implements ReturnPlanService {

    @Autowired
    private ReturnPlanDao returnPlanDao;

	@Override
	public Pager<PlanCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination){
		return returnPlanDao.findPage1(code, contractname, customCode, name, pagination);
	}
	
	public HReturnPlan findOne(Long id){
		return returnPlanDao.findOne(id);
	}
	
	public void deleteOne(Long[] ids){
		returnPlanDao.deleteOne(ids);
	}
	
	public void saveOrUpdate(HReturnPlan plan){
		returnPlanDao.saveOrUpdate(plan);
	}
	
  
}
