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
import com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingChangeBillDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;

/**
 * <p>Title: HibernateToolingChangeBill
 * <p>Description: 工装变更单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 * @see com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingChangeBillDao
 */
public class HibernateToolingChangeBill extends BaseHibernateDao implements
		ToolingChangeBillDao {

	public ToolingChangeBill loadToolingChangeBill(Long toolingChangeBillId) {
		return this.load(ToolingChangeBill.class, toolingChangeBillId);
	}

	public List<ToolingChangeBill> loadAllToolingChangeBills(
			Long[] toolingChangeBillIds) {
		return this.loadAll(ToolingChangeBill.class, toolingChangeBillIds);
	}

	public List<ToolingChangeBill> loadAllToolingChangeBills() {
		return this.loadAll(ToolingChangeBill.class);
	}

	public void storeToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.store(toolingChangeBill);
	}

	public void deleteToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.delete(toolingChangeBill);
	}

	public void deleteAllToolingChangeBills(
			Collection<ToolingChangeBill> toolingChangeBills) {
		this.deleteAll(toolingChangeBills);
	}

}
