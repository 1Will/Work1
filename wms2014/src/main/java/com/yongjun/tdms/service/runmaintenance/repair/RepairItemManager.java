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
import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;


/**
 * <p>Title: RepairItemManager
 * <p>Description: 预防性维修计划的维修详细业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairItemManager.java 11153 2008-02-28 13:05:34Z zbzhang $
 */
@Transactional(readOnly=true)
public interface RepairItemManager {
	/**
	 * 根据传入的预防性维修计划的维修详细对象ID，导出该预防性维修计划的维修详细对象
	 * 
	 * @param repairItemId	预防性维修计划ID
	 * @return
	 */
	@Transactional
	RepairItem loadRepairItem(Long repairItemId);
	
	/**
	 * 根据传入的预防性维修计划的维修详细对象，保存该预防性维修计划的维修详细对象
	 * 
	 * @param preRepairPlan	预防性维修计划对象
	 * @return
	 */
	@Transactional
	void storeRepairItem(RepairItem repairItem);
	
	/**
	 * 根据传入的预防性维修计划的维修详细集合，删除集合中的预防性维修计划的维修详细
	 * 
	 * @param RepairItems	预防性维修计划的维修详细集合
	 * @return
	 */
	@Transactional
	void deleteAllRepairItem(Collection<RepairItem> RepairItems);
	
	/**
	 * 根据传入的预防性维修计划的维修详细集合、预防性维修明细，删除集合中的预防性维修计划的维修详细,并更新明细中的维修部位和维修内容摘要
	 * @param detail        预防性维修明细
	 * @param RepairItems   维修详细集合
	 */
//	@Transactional
//	void deleteAllRepairItem(PreRepairPlanDetail detail, Collection<RepairItem> RepairItems);
	/**
     * 传入预防性维修计划的维修详细ID集合，获取集合到数据库
     * 
     * @param itemIds	预防性维修计划的维修详细ID集合
     * @return List				预防性维修计划集合
     */
	List<RepairItem> loadAllRepairItems(Long[] itemIds);
	
	/**
	 * 重新设置预防性维修明细中实施录入的维修备注
	 * @param detail 预防性维修计划、实施明细
	 * @TODO: 建立一个基类 BaseRepairDetail 让预防性维修和大项修从这个基类继承
	 */
	@Transactional
	void resetRepairItem(PreRepairPlanDetail detail);
	
	/**
	 * 根据传入的维修详细ID,实际执行人字符串,实际完成时间字符串,备注字符串,更新维修详细
	 * @param allRepairItemId 维修详细ID
	 * @param allProcExecPeople 实际执行人字符串
	 * @param allRepairItemProcCompleteDate  实际完成时间字符串
	 * @param allRepairItemComment  备注字符串
	 */
	@Transactional
	void storeRepairItem(String allRepairItemId, String allProcExecPeople,
			String allRepairItemProcCompleteDate, String allRepairItemComment);
	
	/**
	 * 从老的维修计划明细中相关的计划维修详细拷贝到新的维修计划明细中
	 * @param newDetail 新的维修计划明细
	 * @param oldDetail 老的维修计划明细
	 */
	@Transactional
	void resetRepairItem(PreRepairPlanDetail newDetail, PreRepairPlanDetail oldDetail);
}
