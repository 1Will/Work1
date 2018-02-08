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
package com.yongjun.tdms.service.runmaintenance.repair;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;

/**
 * <p>Title: PreRepairPlanDetailManager
 * <p>Description: 预防性维修计划明细业务访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PreRepairPlanDetailManager.java 11160 2008-02-29 10:15:24Z zbzhang $
 */
@Transactional(readOnly=true)
public interface PreRepairPlanDetailManager {
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
	@Transactional
	void storePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) throws Exception;

	
	/**
	 * 保存预防性维修实施明细
	 * @param preRepairPlanDetail 预防性维修实施明细
	 */
	@Transactional
	void storePreRepairProcDetail(PreRepairPlanDetail preRepairProcDetail);
	
	/**
	 * 根据传入的责任单位字符串、责任人字符串、执行人字符串、计划预计执行时间字符串，维修等级字符串、明细ID字符串参数，保存预防性维修计划明细
	 *@param allPreRepairPlanDetailDutyDepartment  责任单位字符串
	 *@param allPreRepairPlanDetailDutyPeople  责任人字符串
	 *@param allPreRepairPlanDetailExecPeople  执行人字符串
	 *@param allPreRepairPlanDetailEstimateExecDate  计划预计执行时间字符串
	 *@param allPreRepairPlanDetailDutyRepairLevel  维修等级字符串
	 */
	@Transactional
	void storePreRepairPlanDetail(String allPreRepairPlanDetailDutyDepartment,
			String allPreRepairPlanDetailExternalHelp,
			String allPreRepairPlanDetailDutyPeople,
			String allPreRepairPlanDetailExecPeople,
			String allPreRepairPlanDetailEstimateExecDate,
			String allPreRepairPlanDetailDutyRepairLevel,
			String allPreRepairProcExecResult,
			String allPreRepairPlanDetailId);
	
	/**
	 * 根据传入的实际执行人、实际执行时间、执行结果、实施明细ID字符串参数，保存预防性维修实施明细
	 * @param allPreRepairProcDetailExecPeople    实际执行人
	 * @param allPreRepairProcDetailEstimateExecDate  实际执行时间
	 * @param allPreRepairProcExecResult   执行结果
	 * @param allPreRepairProcDetailId   实施明细ID
	 */
	@Transactional
	void storePreRepairProcDetail(String allPreRepairProcDetailExecPeople,
			String allPreRepairProcDetailEstimateExecDate,
			String allPreRepairProcExecResult,
			String allPreRepairProcDetailId);
	/**
	 * 删除预防性维修计划明细
	 * @param preRepairPlanDetail 预防性维修计划明细
	 */
	@Transactional
	void deletePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail);
	
	/**
	 * 根据传入的预防性维修计划明细集合，删除集合中的预防性维修计划明细
	 * @param preRepairPlanDetails 预防性维修计划明细集合
	 */
	@Transactional
	void deleteAllPreRepairPlamDetail(Collection<PreRepairPlanDetail> preRepairPlanDetails, PreRepairPlan plan)throws Exception;
	
	/**
	 * 置空预防性维修计划明细中的实施记录
	 * @param preRepairPlanDetail 预防性维修计划明细
	 */
	void resetPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail);
	
	/**
	 * 根据预防性维修实施ID,获取实施明细中最小的实际执行时间
	 * @param procId 预防性维修实施ID
	 * @return 实际执行时间
	 */
	Date GetMinProcEstimateExecDateByPreRepairProcId(Long procId);
	
	/**
	 * 根据传入的计划总费用和实施总费用，来更新详细里的计划和实施总费用
	 * @param planAllFee  计划总费用
	 * @param procAllFee  实施总费用
	 */
	void resetPreRepairPlanOrProcAllFee(PreRepairPlanDetail preRepairDetail,Double planAllFee, Double procAllFee);
	
	/**
	 * 根据传入的预防性维修明细的历史ID,重新保存为新的计划明细
	 * @param preRepairPlan  预防性维修计划对象
	 * @param preRepairPlanDetailHistoryIds 预防性维修明细的历史ID
	 */
	@Transactional
	void storePreRepairPlanDetails(PreRepairPlan preRepairPlan, String preRepairPlanDetailHistoryIds);
	
	/**
	 * 保存从日常检查单选择的设备到维修明细中
	 * @param check 日常检查单
	 * @param preRepairPlan 预防性维修计划
	 * @param addusualCheckIds  日常检查单Id
	 */
	@Transactional
	void storeUausalCheck(Check check,PreRepairPlan preRepairPlan, String addusualCheckIds);
	
	/**
	 * 保存从故障率表选择的设备到维修明细中
	 * @param brocken
	 * @param preRepairPlan
	 * @param addBrockenRateListIds
	 */
	@Transactional
	void storeBrockenRate(BrockenRate brocken,PreRepairPlan preRepairPlan,String addBrockenRateListIds);
	
	/**
	 * 保存从预维标准选择的设备到维修明细中
	 * @param preRepairPlan 维修明细
	 * @param addPreRepairRuleIds 预维标准id
	 */
	@Transactional
	void storePreRepairPlanDetailFromPreRepairRule(PreRepairPlan preRepairPlan, String addPreRepairRuleIds);
	@Transactional
	void storeEasilyDamagedSpare(PreRepairPlan preRepairPlan,String addeasilydamagedSpartIds);
}


