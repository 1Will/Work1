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
package com.yongjun.tdms.service.year.device.runmaintainPlan;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlan;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;

/**
 * 
 * <p>Title: RunmaintainPlanDetailManager
 * <p>Description: 年度运维计划明细业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface RunmaintainPlanDetailManager {
	/**
	 * 根据传入设备运维计划明细ID,获取设备运维计划明细对象
	 * @param runmaintainPlanDetailId 设备运维计划明细ID
	 * @return RunmaintainPlanDetail 设备运维计划明细对象
	 */
	RunmaintainPlanDetail loadRunmaintainPlanDetail(Long runmaintainPlanDetailId);
	
	/**
	 * 根据传入设备运维计划明细ID集合,获取集合设备运维计划明细对象
	 * @param runmaintainPlanDetailIds 设备运维计划明细ID集合
	 * @return List 集合设备运维计划明细对象
	 */
	List<RunmaintainPlanDetail> loadAllRunmaintainPlanDetails(Long [] runmaintainPlanDetailIds);
	
	/**
	 * 获取集合设备运维计划明细对象
	 * @return  List 集合设备运维计划明细对象
	 */
	List<RunmaintainPlanDetail> loadAllRunmaintainPlanDetails();
	
	/**
	 * 保存传入的设备运维计划明细对象
	 * @param runmaintainPlanDetail 设备运维计划明细对象
	 */
	@Transactional
	void storeRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail);
	
	/**
	 * 删除传入的设备运维计划明细对象
	 * @param runmaintainPlanDetail 设备运维计划明细对象
	 */
	@Transactional
	void deleteRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail);
	
	/**
	 * 删除传入的集合中的设备运维计划明细对象
	 * @param runmaintainPlanDetails 设备运维计划明细对象集合
	 */
	@Transactional
	void deleteAllRunmaintainPlanDetails(RunmaintainPlan runmaintainPlan, Collection<RunmaintainPlanDetail> runmaintainPlanDetails);
	
	/**
	 * 根据传入的设备运维计划，以及设备的ID字符串，保存新添加的计划明细
	 * @param runmaintainPlan 设备运维计划
	 * @param addDeviceIds 设备的ID字符串
	 */
	@Transactional
	void storeRunmaintainPlanDetail(RunmaintainPlan runmaintainPlan, String addDeviceIds);
}
