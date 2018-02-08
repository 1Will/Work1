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

import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

/**
 * <p>Title: RepairPlanAndProcDetailDao
 * <p>Description: 大项修计划和实施明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id:  $
 */
public interface RepairPlanAndProcDetailDao {
	
	/**
	 * 根据传入的大项修计划或实施明细的ID,获取大项修计划或实施明细
	 * @param planOrProcDetailId 大项修计划或实施明细的ID
	 * @return RepairPlanAndProcDetail 大项修计划或实施明细
	 */
	RepairPlanAndProcDetail loadRepairPlanAndProcDetail(Long planOrProcDetailId);
	
	/**
	 *  根据传入的大项修计划或实施明细的ID集合,获取大项修计划或实施明细集合
	 * @param planOrProcDetailIds 大项修计划或实施明细的ID集合
	 * @return List 大项修计划或实施明细集合
	 */
	List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails(Long [] planOrProcDetailIds);
	
	/**
	 * 获取大项修计划或实施明细集合
	 * @return List 大项修计划或实施明细集合
	 */
	List<RepairPlanAndProcDetail> loadAllRepairPlanAndProcDetails();
	
	/**
	 * 保存大项修计划或实施明细
	 * @param planAndProcDetail 大项修计划或实施明细
	 */
	void storeRepairPlanAndProcDetail(RepairPlanAndProcDetail planAndProcDetail);
	
	/**
	 * 删除大项修计划或实施明细
	 * @param planAndProcDetail 大项修计划或实施明细
	 */
	void deleteRepairPlanAndProcDetail(RepairPlanAndProcDetail planAndProcDetail);
	
	/**
	 * 根据传入的大项修计划或实施明细的集合，删除集合中的大项修计划或实施
	 * @param planAndProcDetails 大项修计划或实施明细的集合
	 */
	void deleteAllRepairPlanAndProcDetails(Collection<RepairPlanAndProcDetail> planAndProcDetails);
	
}
