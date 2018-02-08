/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.runmaintenance.tooling.record.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingChangeDocDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeDoc;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeDocManager;

/**
 * <p>Title: DefaultToolingChangeBillManager
 * <p>Description: 工装变更附件管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeDocManager
 */

public class DefaultToolingChangeDocManager implements ToolingChangeDocManager {
	private final ToolingChangeDocDao toolingChangeDocDao;

	public DefaultToolingChangeDocManager(ToolingChangeDocDao toolingChangeDocDao) {
		this.toolingChangeDocDao = toolingChangeDocDao;
	}

	public ToolingChangeDoc loadToolingChangeDoc(Long toolingChangeDocId) {
		return this.toolingChangeDocDao.loadToolingChangeDoc(toolingChangeDocId);
	}

	public List<ToolingChangeDoc> loadAllToolingChangeDocs(Long[] toolingChangeDocIds) {
		return this.toolingChangeDocDao.loadAllToolingChangeDocs(toolingChangeDocIds);
	}

	public List<ToolingChangeDoc> loadAllToolingChangeDocs() {
		return this.toolingChangeDocDao.loadAllToolingChangeDocs();
	}

	public void storeToolingChangeDoc(ToolingChangeDoc toolingChangeDoc) {
		this.toolingChangeDocDao.storeToolingChangeDoc(toolingChangeDoc);
	}

	public void deleteToolingChangeDoc(ToolingChangeDoc toolingChangeDoc) {
		this.toolingChangeDocDao.deleteToolingChangeDoc(toolingChangeDoc);
	}

	public void deleteAllToolingChangeDocs(Collection<ToolingChangeDoc> toolingChangeDocs) {
		this.toolingChangeDocDao.deleteAllToolingChangeDocs(toolingChangeDocs);
	}
	
	

}
