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
package com.yongjun.tdms.dao.year.tooling;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.tooling.RepairMaintenanceDetail;

/**
 * 
 * <p>Title: RepairMaintenanceDetailDao
 * <p>Description: 维修保养数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface RepairMaintenanceDetailDao {
	/**
	 * 根据传入的维修保养明细ID,获取维修保养明细
	 * @param repairMaintenanceDetailId 维修保养明细ID
	 * @return RepairMaintenanceDetail 维修保养明细实体
	 */
	RepairMaintenanceDetail loadRepairMaintenanceDetail(Long repairMaintenanceDetailId);
	
	/**
	 * 根据传入的维修保养明细ID集合,获取维修保养明细集合
	 * @param repairMaintenanceDetailIds 维修保养明细ID集合
	 * @return List 维修保养明细集合
	 */
	List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails(Long [] repairMaintenanceDetailIds);
	
	/**
	 * 获取维修保养明细集合
	 * @return List 维修保养明细集合
	 */
	List<RepairMaintenanceDetail> loadAllRepairMaintenanceDetails();
	
	/**
	 * 保存传入的维修保养明细
	 * @param repairMaintenanceDetail 维修保养明细实体
	 */
	void storeRepairMaintenanceDetail(RepairMaintenanceDetail repairMaintenanceDetail);
	
	/**
	 * 删除传入的维修保养明细
	 * @param repairMaintenanceDetail 维修保养明细实体
	 */
	void deleteRepairMaintenanceDetail(RepairMaintenanceDetail repairMaintenanceDetail);
	
	/**
	 * 删除传入的集合维修保养明细
	 * @param repairMaintenanceDetails 集合维修保养明细
	 */
	void deleteAllRepairMaintenanceDetail(Collection<RepairMaintenanceDetail> repairMaintenanceDetails);
}
