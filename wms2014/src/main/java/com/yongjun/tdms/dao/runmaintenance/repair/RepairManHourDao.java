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
package com.yongjun.tdms.dao.runmaintenance.repair;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.repair.RepairManHour;

/**
 * <p>Title: RepairManHourDao
 * <p>Description: 维修工时明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairManHourDao.java 9851 2007-12-27 10:03:46Z zbzhang $
 */
public interface RepairManHourDao {
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
	void storeRepairManHour(RepairManHour repairManHour);
	
	/**
	 * 删除工时明细 
	 * @param repairTool 工时明细实体
	 */
	void deleteRepairManHour(RepairManHour repairManHour);
	
	/**
	 * 根据传入的工时明细 集合
	 * @param repairTools 工时明细集合
	 */
	void deleteAllRepairManHours(Collection<RepairManHour> repairManHours);

}
