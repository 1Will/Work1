package com.yongjun.tdms.dao.runmaintenance.tooling.record.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.DemarcatePlanDetailDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlanDetail;

public class HibernateDemarcatePlanDetail extends  BaseHibernateDao implements DemarcatePlanDetailDao{
    public DemarcatePlanDetail loadDemarcatePlanDetail(Long demarcatePlanDetailId){
    	return this.load(DemarcatePlanDetail.class,demarcatePlanDetailId);
    }
	
	public List<DemarcatePlanDetail> loadAllDemarcatePlanDetails(Long [] demarcatePlanDetailIds){
		return this.loadAll(DemarcatePlanDetail.class,demarcatePlanDetailIds);
	}
	
	
	public void storeDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail){
		this.store(demarcatePlanDetail);
	}
	
	public void deleteDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail){
		this.delete(demarcatePlanDetail);
	}
	
	public void deleteAllDemarcatePlanDetail(Collection<DemarcatePlanDetail> DemarcatePlanDetails){
		this.deleteAll(DemarcatePlanDetails);
	}
}
