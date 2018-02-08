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
package com.yongjun.tdms.dao.runmaintenance.repair;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;

/**
 * <p>Title: PreRepairPlanDetailDao
 * <p>Description: 预防性维修计划明细数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: PreRepairPlanDetailDao.java 10020 2007-12-30 03:14:12Z zbzhang $
 */
public interface PreRepairPlanDetailDao {
	/**
	 * 根据传入的预防性维修计划明细ID,获取预防性维修计划明细
	 * @param preRepairPlanDetailId 预防性维修计划明细ID
	 * @return PreRepairPlanDetail 预防性维修计划明细
	 */
	PreRepairPlanDetail loadPreRepairPlanDetail(Long preRepairPlanDetailId);
	
	/**
	 * 根据传入的预防性维修计划明细ID集合,获取集合预防性维修计划明细
	 * @param preRepairPlanDetailId 预防性维修计划明细ID集合
	 * @return List 预防性维修计划明细集合
	 */
	List<PreRepairPlanDetail> loadAllPreRepairPlanDetails(Long [] preRepairPlanDetailIds);
	
	/**
	 * 获取集合预防性维修计划明细
	 * @return List 预防性维修计划明细集合
	 */
	List<PreRepairPlanDetail> loadAllPreRepairPlanDetails();
	
	/**
	 * 保存预防性维修计划明细
	 * @param preRepairPlanDetail 预防性维修计划明细
	 */
	void storePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail);
	
	/**
	 * 删除预防性维修计划明细
	 * @param preRepairPlanDetail 预防性维修计划明细
	 */
	void deletePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail);
	
	/**
	 * 根据传入的预防性维修计划明细集合，删除集合中的预防性维修计划明细
	 * @param preRepairPlanDetails 预防性维修计划明细集合
	 */
	void deleteAllPreRepairPlamDetail(Collection<PreRepairPlanDetail> preRepairPlanDetails);
	
	/**
	 * 根据预防性维修实施ID,获取实施明细中最小的实际执行时间
	 * @param procId 预防性维修实施ID
	 * @return 实际执行时间
	 */
	Date GetMinProcEstimateExecDateByPreRepairProcId(Long procId);
	
	/**
	 * 根据预防性维修计划id以及设备id获取预防性维修计划明细对象
	 * @param planId 预防性维修计划id
	 * @param deviceId 设备id
	 * @return 预防性维修计划明细对象
	 */
	PreRepairPlanDetail getPreRepairPlanDetailByPlanIdAndDeviceId(Long planId, Long deviceId);
	PreRepairPlanDetail getSparePreRepairPlanDetailByPlanIdAndDeviceId(Long planId, Long deviceId);
	List getPreRepairPlanDetailByAndDeviceNo(String deviceNo);
}
