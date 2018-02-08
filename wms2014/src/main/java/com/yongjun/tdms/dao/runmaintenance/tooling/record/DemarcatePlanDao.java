package com.yongjun.tdms.dao.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;

public interface DemarcatePlanDao {
    DemarcatePlan loadDemarcatePlan(Long demarcatePlanId);
	
	List<DemarcatePlan> loadAllDemarcatePlans(Long [] demarcatePlanIds);
	
	void storeDemarcatePlan(DemarcatePlan demarcatePlan);
	
	void deleteDemarcatePlan(DemarcatePlan demarcatePlan);
	
	void deleteAllDemarcatePlan(Collection<DemarcatePlan> demarcatePlans);
}
