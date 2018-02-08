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

import com.yongjun.tdms.model.year.device.runmaintainPlan.PricisionCheck;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;

/**
 * <p>Title: PricisionCheckManager
 * <p>Description: 运维计划的精度检查业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface PricisionCheckManager {
	/**
	 * 根据传入运维计划的精度检查ID,获取运维计划的精度检查对象
	 * @param pricisionCheckId 运维计划的精度检查ID
	 * @return PricisionCheck 运维计划的精度检查对象
	 */
	PricisionCheck loadPricisionCheck(Long pricisionCheckId);
	
	/**
	 * 根据传入运维计划的精度检查ID集合,获取集合运维计划的精度检查对象
	 * @param pricisionCheckIds 运维计划的精度检查ID集合
	 * @return List 集合运维计划的精度检查对象
	 */
	List<PricisionCheck> loadAllPricisionChecks(Long [] pricisionCheckIds);
	
	/**
	 * 获取集合运维计划的精度检查对象
	 * @return  List 集合运维计划的精度检查对象
	 */
	List<PricisionCheck> loadAllPricisionChecks();
	
	/**
	 * 保存传入的运维计划的精度检查对象
	 * @param pricisionCheck 运维计划的精度检查对象
	 */
	@Transactional
	void storePricisionCheck(PricisionCheck pricisionCheck);
	
	/**
	 * 删除传入的运维计划的精度检查对象
	 * @param pricisionCheck 运维计划的精度检查对象
	 */
	@Transactional
	void deletePricisionCheck(PricisionCheck pricisionCheck);
	
	/**
	 * 删除传入的集合中的运维计划的精度检查对象,并更新相关的运维计划以及运维计划明细的费用
	 * @param pricisionChecks 运维计划的精度检查对象集合
	 */
	@Transactional
	void deleteAllPricisionChecks(RunmaintainPlanDetail runmaintainPlanDetail, Collection<PricisionCheck> pricisionChecks);
}
