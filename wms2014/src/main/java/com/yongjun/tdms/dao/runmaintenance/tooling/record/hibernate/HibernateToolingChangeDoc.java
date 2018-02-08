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
package com.yongjun.tdms.dao.runmaintenance.tooling.record.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeDoc;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingChangeDocDao;

/**
 * <p>Title: HibernateToolingChangeDoc
 * <p>Description: 工装变更附件数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 * @see com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingChangeDocDao
 */
public class HibernateToolingChangeDoc  extends BaseHibernateDao implements ToolingChangeDocDao {

	public ToolingChangeDoc loadToolingChangeDoc(Long toolingChangeDocId) {
		return this.load(ToolingChangeDoc.class, toolingChangeDocId);
	}

	public List<ToolingChangeDoc> loadAllToolingChangeDocs(Long[] toolingChangeDocIds) {
		return this.loadAll(ToolingChangeDoc.class, toolingChangeDocIds);
	}

	public List<ToolingChangeDoc> loadAllToolingChangeDocs() {
		return this.loadAll(ToolingChangeDoc.class);
	}

	public void storeToolingChangeDoc(ToolingChangeDoc toolingChangeDoc) {
		this.store(toolingChangeDoc);
	}

	public void deleteToolingChangeDoc(ToolingChangeDoc toolingChangeDoc) {
		this.delete(toolingChangeDoc);
	}

	public void deleteAllToolingChangeDocs(Collection<ToolingChangeDoc> toolingChangeDocs) {
		this.deleteAll(toolingChangeDocs);
	}
}
