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

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;

/**
 * <p>Title: RepairManHourManager
 * <p>Description: 维修工时明细业务访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairManHourManager.java 11153 2008-02-28 13:05:34Z zbzhang $
 */
@Transactional(readOnly=true)
public interface RepairManHourManager {
	/**
	 * 根据传入的工时明细ID,获取工时明细
	 * @param repairToolId 工时明细ID
	 * @return  工时明细
	 */
	RepairManHour loadRepairManHour(Long repairManHourId);
	
	/**
	 * 根据传入的工时明细ID集合，获取集合中的工时明细
	 * @param repairToolIds 工时明细ID集合
	 * @return  List 工时明细集合
	 */
	List<RepairManHour> loadAllRepairManHours(Long [] repairManHourIds);
	
	/**
	 * 获取集合中的工时明细
	 * @return List 工时明细集合
	 */
	List<RepairManHour> loadAllRepairManHours();
	
	/**
	 * 保存工时明细
	 * @param repairTool 工时明细实体
	 */
	@Transactional
	void storeRepairManHour(RepairManHour repairManHour);
	
	/**
	 * 删除工时明细 
	 * @param repairTool 工时明细实体
	 */
	@Transactional
	void deleteRepairManHour(RepairManHour repairManHour);
	
	/**
	 * 根据传入的工时明细 集合
	 * @param repairTools 工时明细集合
	 */
	@Transactional
	void deleteAllRepairManHours(Collection<RepairManHour> repairManHours);

	@Transactional
	void resetRepairManHour(PreRepairPlanDetail detail);
	
	/**
	 * 从老的维修计划明细中相关的计划维修工时拷贝到新的维修计划明细中
	 * @param newDetail 新的维修计划明细
	 * @param oldDetail 老的维修计划明细
	 */
	@Transactional
	void resetRepairManHour(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail);
	
	/**
	 * 根据维修工时ID值的字符串，实际工时数量值得字符串，更新维修工时的实际工时数量的值
	 * @param allRepairManHourId  维修工时ID值的字符串
	 * @param allRepairProcManHourNum 实际工时数量值得字符串
	 */
	@Transactional
	void storeRepairManHour(String allRepairManHourId, String allRepairProcManHourNum);
	
	/**
	 * 根据传入的预防性维修详细、维修工时、未更新前的计划工时数量、未更新前的实际工时数量，
	 * 来保存维修工时、更新预防性维修的计划总费用和实际总费用
	 * @param preRepairDetail  预防性维修详细
	 * @param repairManHour    维修工时
	 * @param oldManHourNum    未更新前的计划工时数量 
	 * @param oldProcManHourNum  未更新前的实际工时数量
	 * @param planProcFlag     预防性维修的状态：计划或实施
	 */
//	@Transactional
//	void storeRepairManHour(PreRepairPlanDetail preRepairDetail, RepairManHour repairManHour,
//			String oldManHourNum, String oldProcManHourNum, String planProcFlag);
	
	/**
	 * 根据传入的维修工时集合、预防性维修详细，删除集合中的维修工时，并更新计划总费用和实际总费用
	 * @param repairManHours 维修工时集合
	 * @param planDetail 预防性维修详细
	 */
//	@Transactional
//	void deleteAllRepairManHour(Collection<RepairManHour> repairManHours, PreRepairPlanDetail planDetail);
	

	
	
}
