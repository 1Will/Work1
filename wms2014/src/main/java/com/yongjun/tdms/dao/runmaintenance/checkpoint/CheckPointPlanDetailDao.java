package com.yongjun.tdms.dao.runmaintenance.checkpoint;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlan;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointPlanDetail;

public interface CheckPointPlanDetailDao {
	void storePlanDetail(CheckPointPlanDetail planDetail);
	
	CheckPointPlanDetail loadCheckPointPlanDetail(Long id);
	
	List<CheckPointPlanDetail> loadAllCheckPointPlanDetail(Long[] checkPointPlanDetailIds);
	
	void deleteAllCheckPointPlanDetail(List list);

	Long getPlanDetailMaxSerialNo(CheckPointPlan plan);
	
}
