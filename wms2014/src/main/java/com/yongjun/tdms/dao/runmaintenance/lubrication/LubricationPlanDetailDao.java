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
package com.yongjun.tdms.dao.runmaintenance.lubrication;

import java.util.Date;
import java.util.List;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanDetail;

/**
 * @author qs
 * @version $Id: LubricationPlanDetailDao.java 11067 2008-02-24 01:13:29Z zbzhang $
 */
public interface LubricationPlanDetailDao {
	/**
	 * 根据传入的润滑计划对象,存储润滑计划对象
	 * 
	 * @param lubricationPlanDetail
	 *            润滑计划对象
	 * @return
	 */
	void storeLubricationPlanDetail(LubricationPlanDetail lubricationPlanDetail);

	/**
	 * 根据传入的润滑计划的Id,获取润滑计划对象
	 * 
	 * @param id
	 *            润滑计划Id号
	 * @return 润滑计划对象
	 */
	LubricationPlanDetail loadLubricationPlanDetail(Long id);


	/**
	 * 根据传入的润滑计划对象的集合,删除润滑计划对象集合
	 * 
	 * @param list
	 *            润滑计划对象集合
	 * @return
	 */
	void deleteAllLubricationPlanDetail(List<LubricationPlanDetail> list);

	/**
	 * 根据传入的润滑计划对象Id的数组,获取润滑计划对象集合
	 * 
	 * @param lubricationRuleIds
	 *            润滑计划对象Id的数组
	 * @return 润滑计划对象集合
	 */
	List<LubricationPlanDetail> loadAllLubricationPlanDetail(Long[] lubricationPlanDetailIds);

	/**
	 * 获取全部的润滑计划对象集合
	 * 
	 * @param
	 * @return 润滑计划对象集合
	 */
	List<LubricationPlanDetail> loadAllLubricationPlanDetail();
	
	/**
	 * 根据传入的设备id以及润滑规则id,获取最大的执行时间
	 * @param deviceId 设备id
	 * @param ruleId 润滑规则id
	 * @return
	 */
	Date loadMaxActualExecDateByDeviceIDAndRuleID(Long deviceId, Long ruleId);
}
