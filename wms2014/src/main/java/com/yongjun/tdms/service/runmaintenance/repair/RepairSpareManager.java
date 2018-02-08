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
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairSpare;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairSpareManager
 * <p>Description: 预防性维修计划的备件详细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairSpareManager.java 11225 2008-03-09 05:57:04Z zbzhang $
 */

@Transactional(readOnly=true)
public interface RepairSpareManager {
	List<RepairSpare> loadRepairSpares(Long[] spareIds);
	
	@Transactional
	void storeRepairSpare(PreRepairPlanDetail preRepairPlanDetail, String addSpareIds);
	
	@Transactional
	void storeRepairSpare(RepairPlanAndProcDetail repairPlanAndProcDetail, String addSpareIds);
	
	@Transactional
	void deleteAllRepairSpare(Collection<RepairSpare> repairSpare);

	@Transactional
	public RepairSpare loadRepairSpare(Long repairSpareId);
	
	@Transactional
	void storeRepairSpare(RepairSpare repairSpare);
	
	@Transactional
	void resetRepairSpare(PreRepairPlanDetail detail);
	
	/**
	 * 删除选中的维修备件集合，并更新计划总费用
	 * @param repairSpares  维修备件集合
	 * @param planDetail 预防性维修计划
	 */
	//@Transactional
	//void deleteAllRepairSpare(Collection<RepairSpare> repairSpares, PreRepairPlanDetail planDetail);
	
	/**
	 * 保存预防性维修备件，并更新预防性维修计划或实施的总费用
	 * @param preRepairPlanDetail  预防性维修计划或实施
	 * @param repairSpare  预防性维修备件]
	 * @param oldPlanUsedNum 未更新前的计划使用数量
	 * @param oldProcUsedNum 未更新前的实际使用数量 
	 */
	//@Transactional
	///void storeRepairSpare(PreRepairPlanDetail preRepairPlanDetail, RepairSpare repairSpare,
			//String oldPlanUsedNum, String oldProcUsedNum);
	
	@Transactional
	public void storeRepairSpare(String saveSpareIds,String planProcFlag); 
	
	/**
	 * 从老的维修计划明细中相关的计划维修备件拷贝到新的维修计划明细中
	 * @param newDetail 新的维修计划明细
	 * @param oldDetail 老的维修计划明细
	 */
	@Transactional
	void resetRepairSpare(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail);
	
	/**
	 * 根据传入的故障维修，备件的ID，保存故障维修中的维修备件
	 * @param faultRepair 故障维修
	 * @param addSapreIds 备件的ID
	 */
	@Transactional
	void storeRepairSpare(FaultRepair faultRepair, String addSapreIds);
}
