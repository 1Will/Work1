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
package com.yongjun.tdms.dao.year.repair;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.repair.RepairPlanAndProc;

/**
 * <p>Title: RepairPlanAndProcDao
 * <p>Description: 大项修计划和实施数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:  $
 */
public interface RepairPlanAndProcDao {
	/**
	 * 根据传入的大项修计划或实施的ID,获取大项修计划或实施对象
	 * @param planOrProcId 大项修计划或实施的ID 
	 * @return RepairPlanAndProc 大项修计划或实施对象
	 */
	RepairPlanAndProc loadRepairPlanOrProc(Long planOrProcId);
	
	/**
	 * 根据传入的大项修计划或实施的ID集合,获取大项修计划或实施对象集合
	 * @param planOrProcIds 大项修计划或实施的ID集合
	 * @return List 大项修计划或实施对象集合
	 */
	List<RepairPlanAndProc> loadAllRepairPlanOrProcs(Long[] planOrProcIds);

	/**
	 * 获取大项修计划或实施对象集合
	 * @return List 大项修计划或实施对象集合
	 */
	List<RepairPlanAndProc> loadAllRepairPlanOrProcs();
	
	/**
	 * 保存传入的大项修计划或实施对象
	 * @param repairPlanOrProc 大项修计划或实施对象
	 */
	void storeRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc);
	
	/**
	 * 删除传入的大项修计划或实施对象
	 * @param repairPlanOrProc 大项修计划或实施对象
	 */
	void deleteRepairPlanOrProc(RepairPlanAndProc repairPlanOrProc);
	
	/**
	 * 根据传入的大项修计划或实施对象集合,删除集合中的大项修计划或实施对象
	 * @param repairPlanOrProcs 大项修计划或实施对象集合
	 */
	void deleteAllRepairPlanOrProcs(Collection<RepairPlanAndProc> repairPlanOrProcs);
}
