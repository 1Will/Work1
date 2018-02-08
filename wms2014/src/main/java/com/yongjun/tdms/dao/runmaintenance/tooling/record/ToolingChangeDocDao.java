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

import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeDoc;


/**
 * <p>Title: ToolingChangeBillDao
 * <p>Description: 工装变更附件数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
public interface ToolingChangeDocDao {
	/**
	 * 根据传入的工装变更附件ID，获取工装变更附件
	 * 
	 * @param toolingChangeDocId 工装变更附件ID
	 * @return ToolingChangeDoc 工装变更附件
	 */
	ToolingChangeDoc loadToolingChangeDoc(Long toolingChangeDocId);
	
	/**
	 * 根据传入的工装变更附件ID集合，获取集合中的工装变更附件
	 * 
	 * @param toolingChangeDocIds 工装变更附件ID集合
	 * @return List 工装变更附件集合
	 */
	List<ToolingChangeDoc> loadAllToolingChangeDocs(Long [] toolingChangeDocIds);
	
	/**
	 * 获取集合中的工装变更附件
	 * 
	 * @return List 工装变更附件集合
	 */
	List<ToolingChangeDoc> loadAllToolingChangeDocs();

	/**
	 * 保存工装变更附件
	 * 
	 * @param toolingChangeDoc 工装变更附件实体
	 */
	void storeToolingChangeDoc(ToolingChangeDoc toolingChangeDoc);
	
	/**
	 * 删除工装变更附件
	 * 
	 * @param toolingChangeDoc 工装变更附件实体
	 */
	void deleteToolingChangeDoc(ToolingChangeDoc toolingChangeDoc);
	
	/**
	 * 根据传入的工装变更附件集合，删除集合中的工装变更附件
	 * 
	 * @param toolingChangeDocs 工装变更附件集合
	 */
	void deleteAllToolingChangeDocs(Collection<ToolingChangeDoc> toolingChangeDocs);

}
