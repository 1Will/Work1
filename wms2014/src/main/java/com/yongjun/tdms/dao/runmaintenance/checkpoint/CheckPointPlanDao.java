package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;

/**
 * @author qs
 * @version $Id: CheckPointPlanDao.java 7925 2007-10-22 02:37:55Z qsun $
 */
public interface CheckPointPlanDao {
	void storePlan(CheckPointPlan plan);

	CheckPointPlan loadPlan(Long id);

	List<CheckPointPlan> loadAllCheckPointPlan(Long[] checkPointPlanIds);
	
	List<CheckPointPlan> loadAllUnrelatedCheckPointPlan();
	
	void deleteAllCheckPointPlan(List list);

	CheckPointPlan loadLastPlanByCheckPointRuleId(Long ruleId);
	
	CheckPointPlan loadPlanByCheckPointPlanId(Long planId);
}
