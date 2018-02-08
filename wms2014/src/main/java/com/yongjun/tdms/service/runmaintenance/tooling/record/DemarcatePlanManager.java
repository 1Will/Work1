package com.yongjun.tdms.service.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.tooling.record.DemarcatePlan;

/**
 * <p>Title: ToolingChangeBillManager
 * <p>Description: 工装标定计划明细管理业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */
@Transactional(readOnly = true)
public interface DemarcatePlanManager {
	/**
	 * 根据传入的工装变更单ID，获取工装变更单
	 * @param demarcatePlanId
	 * @return
	 */
    DemarcatePlan loadDemarcatePlan(Long demarcatePlanId);
	
	List<DemarcatePlan> loadAllDemarcatePlans(Long [] demarcatePlanIds);
	
	
	@Transactional
	void deleteDemarcatePlan(DemarcatePlan demarcatePlan);
	
	@Transactional
	void deleteAllDemarcatePlan(Collection<DemarcatePlan> demarcatePlan)throws Exception;

	@Transactional
	void storeDemarcatePlan(DemarcatePlan demarcatePlan);
	
	/**
	 * 根据获得的标定计划ID,查看该标定计划中的明细是否都已实施，还是未实施，还是在实施中
	 * @param demarcatePlanId  标定计划ID
	 */
	@Transactional
	void updateDemarcatePlanStatus(Long demarcatePlanId);
	
	
	
}
