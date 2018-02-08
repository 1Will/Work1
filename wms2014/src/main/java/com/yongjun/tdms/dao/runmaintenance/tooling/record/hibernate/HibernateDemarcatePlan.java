package com.yongjun.tdms.dao.runmaintenance.tooling.record.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.DemarcatePlanDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;

public class HibernateDemarcatePlan extends BaseHibernateDao implements DemarcatePlanDao{
	
    public DemarcatePlan loadDemarcatePlan(Long demarcatePlanId){
    	return this.load(DemarcatePlan.class,demarcatePlanId);
    }
	
	public List<DemarcatePlan> loadAllDemarcatePlans(Long [] demarcatePlanIds){
		return this.loadAll(DemarcatePlan.class,demarcatePlanIds);
	}
	
	public void storeDemarcatePlan(DemarcatePlan demarcatePlan){
		 this.store(demarcatePlan);
	}
	
	public void deleteDemarcatePlan(DemarcatePlan demarcatePlan){
		this.delete(demarcatePlan);
	}
	
	public void deleteAllDemarcatePlan(Collection<DemarcatePlan> demarcatePlans){
		this.deleteAll(demarcatePlans);
	}
}
