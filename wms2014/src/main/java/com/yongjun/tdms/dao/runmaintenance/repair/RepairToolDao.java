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

import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;

/**
 * <p>Title: RepairToolDao
 * <p>Description: 维修工具明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairToolDao.java 9833 2007-12-27 06:56:16Z zbzhang $
 */
public interface RepairToolDao {
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
	void storeRepairTool(RepairTool repairTool);
	
	/**
	 * 删除维修工具 
	 * @param repairTool 维修工具实体
	 */
	void deleteRepairTool(RepairTool repairTool);
	
	/**
	 * 根据传入的维修工具集合
	 * @param repairTools 维修工具集合
	 */
	void deleteAllRepairTools(Collection<RepairTool> repairTools);
}
