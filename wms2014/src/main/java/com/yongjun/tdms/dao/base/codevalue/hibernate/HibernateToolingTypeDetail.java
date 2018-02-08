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
package com.yongjun.tdms.dao.base.codevalue.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.codevalue.ToolingTypeDetailDao;
import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;

/**
 * @author qs
 * @version $Id: HibernateToolingTypeDetail.java 8431 2007-11-20 01:57:15Z qsun $
 */
public class HibernateToolingTypeDetail extends BaseHibernateDao implements ToolingTypeDetailDao{

	public ToolingTypeDetail loadToolingTypeDetail(Long toolingTypeDetailId) {
		return this.load(ToolingTypeDetail.class, toolingTypeDetailId);
	}

	public List<ToolingTypeDetail> loadAllToolingTypeDetails(Long[] toolingTypeDetailIds) {
		return this.loadAll(ToolingTypeDetail.class, toolingTypeDetailIds);
	}

	public List<ToolingTypeDetail> loadAllToolingTypeDetails() {
		return this.loadAll(ToolingTypeDetail.class);
	}

	public void storeToolingTypeDetail(ToolingTypeDetail toolingTypeDetail) {
		this.store(toolingTypeDetail);
	}

	public void deleteToolingTypeDetail(ToolingTypeDetail toolingTypeDetail) {
		this.delete(toolingTypeDetail);
	}

	public void deleteAllToolingTypeDetails(Collection<ToolingTypeDetail> toolingTypeDetails) {
		this.deleteAll(toolingTypeDetails);
	}

}
