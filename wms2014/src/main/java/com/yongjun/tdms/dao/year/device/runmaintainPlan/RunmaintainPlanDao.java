/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.dao.year.device.runmaintainPlan;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;

/**
 * 
 * <p>Title: RunmaintainPlanDao
 * <p>Description: 设备运维计划数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */

public interface RunmaintainPlanDao {
	
	/**
	 * 根据传入设备运维计划ID,获取设备运维计划对象
	 * @param runmaintainPlanId 设备运维计划ID
	 * @return RunmaintainPlan 设备运维计划对象
	 */
	RunmaintainPlan loadRunmaintainPlan(Long runmaintainPlanId);
	
	/**
	 * 根据传入设备运维计划ID集合,获取集合设备运维计划对象
	 * @param runmaintainPlanIds 设备运维计划ID集合
	 * @return List 集合设备运维计划对象
	 */
	List<RunmaintainPlan> loadAllRunmaintainPlans(Long [] runmaintainPlanIds);
	
	/**
	 * 获取集合设备运维计划对象
	 * @return  List 集合设备运维计划对象
	 */
	List<RunmaintainPlan> loadAllRunmaintainPlans();
	
	/**
	 * 保存传入的设备运维计划对象
	 * @param runmaintainPlan 设备运维计划对象
	 */
	void storeRunmaintainPlan(RunmaintainPlan runmaintainPlan);
	
	/**
	 * 删除传入的设备运维计划对象
	 * @param runmaintainPlan 设备运维计划对象
	 */
	void deleteRunmaintainPlan(RunmaintainPlan runmaintainPlan);
	
	/**
	 * 删除传入的集合中的设备运维计划对象
	 * @param runmaintainPlans 设备运维计划对象集合
	 */
	void deleteAllRunmaintainPlans(Collection<RunmaintainPlan> runmaintainPlans);
	
}
