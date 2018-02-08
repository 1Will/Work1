package com.yongjun.tdms.service.runmaintenance.tooling.record;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeDoc;

/**
 * <p>Title: ToolingChangeBillManager
 * <p>Description: 工装变更附件管理业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */

@Transactional(readOnly = true)
public interface ToolingChangeDocManager {
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
	@Transactional
	void storeToolingChangeDoc(ToolingChangeDoc toolingChangeDoc);
	
	/**
	 * 删除工装变更附件
	 * 
	 * @param toolingChangeDoc 工装变更附件实体
	 */
	@Transactional
	void deleteToolingChangeDoc(ToolingChangeDoc toolingChangeDoc);
	
	/**
	 * 根据传入的工装变更附件集合，删除集合中的工装变更附件
	 * 
	 * @param toolingChangeDocs 工装变更附件集合
	 */
	@Transactional
	void deleteAllToolingChangeDocs(Collection<ToolingChangeDoc> toolingChangeDocs);

}
