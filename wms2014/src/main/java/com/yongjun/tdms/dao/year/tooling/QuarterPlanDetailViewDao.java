package com.yongjun.tdms.dao.year.tooling;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.year.tooling.QuarterPlanDetailView;
public interface QuarterPlanDetailViewDao {
	
	List<QuarterPlanDetailView> loadQuarterPlanDetail(Long quarterPlanId);
	public List<QuarterPlanDetailView>loadQuarterPlanDetailView(String[] array)throws HibernateException;
}
