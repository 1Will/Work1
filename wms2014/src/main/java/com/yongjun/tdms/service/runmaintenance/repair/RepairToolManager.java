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

import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: RepairToolManager
 * <p>Description: 维修工具明细业务访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: RepairToolManager.java 11153 2008-02-28 13:05:34Z zbzhang $
 */
@Transactional(readOnly=true)
public interface RepairToolManager {
	/**
	 * 根据传入的维修工具ID,获取维修工具
	 * @param repairToolId 维修工具ID
	 * @return  维修工具
	 */
	RepairTool loadRepairTool(Long repairToolId);
	
	/**
	 * 根据传入的维修工具ID集合，获取集合中的维修工具
	 * @param repairToolIds 维修工具ID集合
	 * @return  List 维修工具集合
	 */
	List<RepairTool> loadAllRepairTools(Long [] repairToolIds);
	
	/**
	 * 获取集合中的维修工具
	 * @return List 维修工具集合
	 */
	List<RepairTool> loadAllRepairTools();
	
	/**
	 * 保存维修工具
	 * @param repairTool 维修工具实体
	 */
	@Transactional
	void storeRepairTool(RepairTool repairTool);
	
	/**
	 * 删除维修工具 
	 * @param repairTool 维修工具实体
	 */
	@Transactional
	void deleteRepairTool(RepairTool repairTool);
	
	/**
	 * 根据传入的维修工具集合
	 * @param repairTools 维修工具集合
	 */
	@Transactional
	void deleteAllRepairTools(Collection<RepairTool> repairTools);

	@Transactional
	void resetRepairTool(PreRepairPlanDetail detail);
	
	/**
	 * 根据传入的维修工具ID字符串，实际使用数量字符串，更新维修工具
	 * @param allRepairToolId 维修工具ID字符串
	 * @param allRepairToolProcUseNum 实际使用数量字符串
	 */
	@Transactional
	void storeRepairTool(String allRepairToolId, String allRepairToolProcUseNum);
	
	/**
	 * 从老的维修计划明细中相关的计划维修工具拷贝到新的维修计划明细中
	 * @param newDetail 新的维修计划明细
	 * @param oldDetail 老的维修计划明细
	 */
	@Transactional
	void resetRepairTool(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail);
}
