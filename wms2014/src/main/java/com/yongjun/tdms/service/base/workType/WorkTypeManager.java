package com.yongjun.tdms.service.base.workType;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.workType.WorkType;
/**
 * <p>Title: PreRepairPlanDetailManager
 * <p>Description: 工种业务访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PreRepairPlanDetailManager.java 11153 2008-02-28 13:05:34Z zbzhang $
 */
@Transactional(readOnly=true)
public interface WorkTypeManager {
	/**
	 * 根据传入工种ID,获取工种
	 * @param workTypeId 工种ID
	 * @return WorkType 工种
	 */
	WorkType loadWorkType(Long workTypeId);
	
	/**
	 * 根据传入工种ID集合,获取集合工种
	 * @param preRepairRuleIds 工种ID集合
	 * @return List 工种集合
	 */
	List<WorkType> loadAllWorkTypes(Long [] workTypeIds);
	
	/**
	 * 获取集合工种
	 * @return List 工种集合
	 */
	List<WorkType> loadAllWorkTypes();
	
	/**
	 * 保存工种
	 * @param workType 工种
	 */
	@Transactional
	void storeWorkType(WorkType workType);
	
	/**
	 * 删除工种
	 * @param workType 工种
	 */
	@Transactional
	void deleteWorkType(WorkType workType);
	
	/**
	 * 根据传入工种集合，删除集合工种
	 * @param preRepairRules 工种集合
	 */
	@Transactional
	void deleteAllWorkTypes(Collection<WorkType> workTypes);
}
