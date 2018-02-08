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
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlan;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairFee;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairFeeManager
 * <p>Description: 预防性维修计划的费用详细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairFeeManager.java 11225 2008-03-09 05:57:04Z zbzhang $
 */

@Transactional(readOnly=true)
public interface RepairFeeManager {
	@Transactional
	public RepairFee loadRepairFee(Long repairFeeId);
	
	@Transactional
	void storeRepairFee(RepairFee repairFee);
	
	@Transactional
	void deleteAllRepairItem(Collection<RepairFee> RepairFees);

	public List<RepairFee> loadAllRepairFees(Long[] feeIds);
	
	@Transactional
	void resetRepairFee(PreRepairPlanDetail detail);
	
	/**
	 * 根据传入的预防性维修详细、维修费用、未更新前的计划费用、未更新前的实际费用，
	 * 来保存维修费用、更新预防性维修的计划总费用和实际总费用
	 * @param preRepairDetail 预防性维修详细
	 * @param repairFee       维修费用
	 * @param oldPlanFee      未更新前的计划费用
	 * @param oldProcFee      未更新前的实际费用
	 * @param planProcFlag    预防性维修的状态：计划或实施
	 */
//	@Transactional
//	void storeRepairFee(PreRepairPlanDetail preRepairDetail, RepairFee repairFee,
//			String oldPlanFee, String oldProcFee, String planProcFlag);
	
	/**
	 * 根据传入的维修费用、未更新前的计划总费用、未更新前的计划详细的总费用，
	 * 来更新预防性维修的计划总费用
	 * @param repairFee       维修费用
	 * @param planAllFee      未更新前的计划总费用
	 * @param oldPlanFee      未更新前的计划费用
	 */
	@Transactional
	void storeRepairFee(PreRepairPlan preRepairPlan,RepairFee repairFee,String planAllFee,String oldPlanFee);
	
	/**
	 * 根据传入的计划明细对象、已更新维修费用的维修费用对象、维修费用对象未更新前的费用，
	 * 来更新计划的总费用、计划明细总费用、维修费用对象的费用
	 * @param planDetail 预防性维修计划明细对象
	 * @param repairFee  维修费用对象
	 * @param oldPlanFee 维修费用对象未更新前的费用
	 */
	@Transactional
	void storeRepairFee(PreRepairPlanDetail planDetail, RepairFee repairFee, String oldPlanFee);
	
	/**
	 * 根据传入的计划明细对象、已更新维修费用的维修费用对象、维修费用对象未更新前的费用，
	 * 来更新计划的总费用、计划明细总费用、维修费用对象的费用
	 * @param planDetail 大项修计划明细对象
	 * @param repairFee  维修费用对象
	 * @param oldPlanFee 维修费用对象未更新前的费用
	 */
	@Transactional
	void storeRepairFee(RepairPlanAndProcDetail planDetail, RepairFee repairFee, String oldPlanFee);
	
	/**
	 * 根据传入的维修费用集合、预防性维修详细，删除集合中的维修费用，并更新计划总费用和实际总费用
	 * @param repairManHours 维修费用集合
	 * @param planDetail 预防性维修详细
	 */
	@Transactional
	void deleteAllRepairFee(Collection<RepairFee> RepairFees, PreRepairPlanDetail planDetail);
	
	/**
	 * 根据传入的维修费用集合、大项修详细，删除集合中的维修费用，并更新计划总费用和实际总费用
	 * @param repairManHours 维修费用集合
	 * @param planDetail 大项修详细
	 */
	@Transactional
	void deleteAllRepairFee(Collection<RepairFee> RepairFees, RepairPlanAndProcDetail planDetail);
	
	/**
	 * 根据传入的维修费用集合、预防性计划详细，删除集合中的维修费用，并更新计划总费用
	 * @param repairManHours 维修费用集合
	 * @param PreRepairPlan 预防性计划详细
	 */
	@Transactional
	void deletePlanProcAllFee(Collection<RepairFee> repairFees, PreRepairPlan preRepairPlan);
	
	/**
	 * 根据传入的维修费用ID,实际费用字符串,更新维修费用
	 * @param allRepairFeeId 维修费用ID
	 * @param allRepairFeeProcFee 实际费用字符串
	 */
	@Transactional
	void storeRepairFee(Long preRepairPlanId,Long preRepairPlanDetailId,String allRepairFeeId, String allRepairFeeProcFee);
	
	/**
	 * 从老的维修计划明细中相关的计划维修费用拷贝到新的维修计划明细中
	 * @param newDetail 新的维修计划明细
	 * @param oldDetail 老的维修计划明细
	 */
	@Transactional
	void resetRepairFee(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail);
	
	/**
	 * 根据传入的大项修明细ID,所有该大项修明细所关联的维修费用ID,所有的实际维修费用,来更新大项修相关的维修费用
	 * @param yearRepairPlanDetailId 大项修明细ID
	 * @param allRepairFeeId 维修费用ID
	 * @param allRepairFeeProcFee 实际维修费用
	 */
	@Transactional
	public void storeRepairFee(Long yearRepairPlanDetailId,
			String allRepairFeeId, String allRepairFeeProcFee);
	
	/**
	 * 根据传入的故障维修对象、已更新维修费用的维修费用对象、维修费用对象未更新前的费用，
	 * 来更新故障维修总费用、维修费用对象的费用
	 * @param faultRepair 故障维修对象
	 * @param repairFee  维修费用对象
	 * @param oldPlanFee 维修费用对象未更新前的费用
	 */
	@Transactional
	void storeRepairFee(FaultRepair faultRepair, RepairFee repairFee, String oldPlanFee);
	
	/**
	 * 根据传入的维修费用集合、故障维修对象，删除集合中的维修费用，并更新故障维修总费用
	 * @param repairFees 维修费用集合
	 * @param faultRepair 故障维修对象
	 */
	@Transactional
	void deleteAllRepairFee(Collection<RepairFee> repairFees, FaultRepair faultRepair);
}
