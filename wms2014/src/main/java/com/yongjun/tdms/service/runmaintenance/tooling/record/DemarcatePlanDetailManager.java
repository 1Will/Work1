package com.yongjun.tdms.service.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;
import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlanDetail;

@Transactional(readOnly = true)
public interface DemarcatePlanDetailManager {
	    DemarcatePlanDetail loadDemarcatePlanDetail(Long demarcatePlanDetailId);
		
		List<DemarcatePlanDetail> loadAllDemarcatePlanDetails(Long [] demarcatePlanDetailIds);
		
		
		@Transactional
		void deleteDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail);
		
		@Transactional
		void deleteAllDemarcatePlanDetail(Long toolingDemarcatePlanId, Collection<DemarcatePlanDetail> demarcatePlanDetail);
		
		@Transactional
		void storeDemarcatePlanDetail(DemarcatePlanDetail demarcatePlanDetail);
		
		@Transactional
		void storeDemarcatePlanDetail(String storeDemarcateDetailString); 
		
		@Transactional
		void storeDemarcatePlanDetail(String newDemarcateRuleIds,DemarcatePlan demarcatePlan);
		
		@Transactional
		void storeDemarcatePlamDetail(DemarcatePlan demarcaretePlan, 
				String allToolingDemarcateManager,
				String allThisDemarcateDateTm,
				String allDemarcateDetailComment);
		
		/**
		 * 根据传入标定结果值，更新标定计划详细的标定结果值
		 * @param toolingDemarcatePlanId    标定计划ID
		 * @param allDemarcateResult     标定结果值
		 */
		@Transactional
		void storeDemarcatePlamDetailResult(Long toolingDemarcatePlanId, String allDemarcateResult);
		
}
