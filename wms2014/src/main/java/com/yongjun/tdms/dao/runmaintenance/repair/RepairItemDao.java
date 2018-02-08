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

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.repair.RepairItem;

/**
 * <p>Title: RepairItemDao
 * <p>Description: 维修内容明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: RepairItemDao.java 10080 2008-01-03 10:38:03Z wzou $
 */
public interface RepairItemDao {
	/**
	 * 根据传入的维修详细单ID，获取维修详细单
	 * 
	 * @param repairItemId 维修详细单ID
	 * @return RepairItem 维修详细单
	 */
	@Transactional
	public RepairItem loadRepairItem(Long repairItemId);
	
	/**
	 * 保存维修详细单
	 * 
	 * @param repairItem 维修详细单实体
	 * @return
	 */
	@Transactional
	void storeRepairItem(RepairItem repairItem);
	
	/**
	 * 根据传入的维修详细单集合，删除集合中的维修详细单
	 * 
	 * @param RepairItems 维修详细单集合
	 * @return
	 */
	@Transactional
	void deleteAllRepairItem(Collection<RepairItem> RepairItems);
	
	/**
	 * 根据传入的维修详细单ID集合，获取集合中的维修详细单
	 * 
	 * @param itemIds 维修详细单ID集合
	 * @return List 维修详细单集合
	 */
	public List<RepairItem> loadAllRepairItems(Long[] itemIds);
}
