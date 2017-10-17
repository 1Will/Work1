package com.github.wp.business.dao.contract.impl;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.github.wp.business.dao.contract.ReturnPlanDao;
import com.github.wp.business.pojo.qiandan.HReturnPlan;
import com.github.wp.business.vo.PlanCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;


/**
 * 回款计划实现类
 * @author dupeng
 * @version 1.0
 * @since 2016年8月17日
 */
@Repository
public class ReturnPlanDaoImpl implements ReturnPlanDao {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSuperSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("rawtypes")
	public Pager<PlanCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination){
		StringBuffer hql = new StringBuffer("select c.code as contractCode,c.contractname,b.code as customCode,b.name,p.phone,p.planDate,p.yingfu,p.batch,p.isOrNot,p.billingOrIs,p.id");
		hql.append(" from HReturnPlan p , Hcontractmanagement c ,Bcustomerinfo b where p.contractManagerId = c.id");
		hql.append(" and c.customerInfoId = b.id");
		if(name != null && !"".equals(name)){
		hql.append(" and b.name like '%"+name+"%'");
		}
		Query query = this.getSuperSession().createQuery(hql.toString());
		query.setFirstResult(pagination.getFirstSize());//初始行数   
		query.setMaxResults(pagination.getMaxSize());
		Pager<PlanCustom> pager = new Pager<PlanCustom>();
		List list = query.list();
		//int count = list.size();
		pager.setTotal(list.size());
		List<PlanCustom> planList = new ArrayList<PlanCustom>();
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size();i++){
				PlanCustom plan = new PlanCustom();
				Object[] object = (Object[])list.get(i);
				plan.setCode((String)object[0]);
				plan.setContractname((String)object[1]);
				plan.setCustomCode((String)object[2]);
				plan.setCustoName((String)object[3]);
				plan.setPhone((String)object[4]);
				plan.setPlanDate((Timestamp)object[5]);
				plan.setYingfu((String)object[6]);
				plan.setBatch((String)object[7]);
				plan.setIsOrNot((Character)object[8]);
				plan.setBillingOrIs((Character)object[9]);
				
				planList.add(plan);
				pager.setRows(planList);
			}
		}else{
			pager.setRows(null);
		}
		return pager;
		
	}
	
	@Override
	public void saveOrUpdate(HReturnPlan plan){
		this.getSuperSession().saveOrUpdate(plan);
	}
	
	@Override
	public void deleteOne(Long[] ids){
		for (Long id : ids) {
			HReturnPlan plan = findOne(id);
			plan.setEffectflag('D');
			this.getSuperSession().saveOrUpdate(plan);
		}
	}
	
	@Override
	public HReturnPlan findOne(Long id) {
		return (HReturnPlan) this.getSuperSession().get(
				HReturnPlan.class, id);
	}
	
}
