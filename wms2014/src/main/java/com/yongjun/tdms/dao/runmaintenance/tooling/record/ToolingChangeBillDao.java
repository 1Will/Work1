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
package com.yongjun.tdms.dao.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;

/**
 * <p>Title: ToolingChangeBillDao
 * <p>Description: 工装变更单数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
public interface ToolingChangeBillDao {
	/**
	 * 根据传入的工装变更单ID，获取工装变更单
	 * 
	 * @param toolingChangeBillId 工装变更单ID
	 * @return ToolingChangeBill 工装变更单实体
	 */
	ToolingChangeBill loadToolingChangeBill(Long toolingChangeBillId);
	
	/**
	 * 根据传入的工装变更单ID集合，获取集合中的工装变更单
	 * 
	 * @param toolingChangeBillIds 工装变更单ID集合
	 * @return List 工装变更单集合
	 */
	List<ToolingChangeBill> loadAllToolingChangeBills(Long [] toolingChangeBillIds);
	
	/**
	 * 获取集合中的工装变更单
	 * 
	 * @return List 工装变更单集合
	 */
	List<ToolingChangeBill> loadAllToolingChangeBills();

	/**
	 * 保存工装变更单
	 * 
	 * @param toolingChangeBill 工装变更单实体
	 */
	void storeToolingChangeBill(ToolingChangeBill toolingChangeBill);
	
	/**
	 * 删除工装变更单
	 * 
	 * @param toolingChangeBill 工装变更单实体
	 */
	void deleteToolingChangeBill(ToolingChangeBill toolingChangeBill);
	
	/**
	 * 根据传入的工装变更单集合，删除集合中的工装变更单
	 * 
	 * @param toolingChangeBills 工装变更单集合
	 */
	void deleteAllToolingChangeBills(Collection<ToolingChangeBill> toolingChangeBills);
}
