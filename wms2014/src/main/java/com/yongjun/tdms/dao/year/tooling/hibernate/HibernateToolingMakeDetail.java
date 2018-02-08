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
package com.yongjun.tdms.dao.year.tooling.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.ToolingMakeDetailDao;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;


/**
 * 
 * 
 * <p>Title: HibernateToolingMakeDetail
 * <p>Description: 年度计划数据访问是实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.yearPlan.ToolingMakeDetailDao
 * @version $Id:$
 */
public class HibernateToolingMakeDetail extends BaseHibernateDao implements
		ToolingMakeDetailDao {

	public ToolingMakeDetail loadToolingMakeDetail(Long toolingMakeDetailId) {
		return this.load(ToolingMakeDetail.class, toolingMakeDetailId);
	}

	public List<ToolingMakeDetail> loadAllToolingMakeDetails(Long[] toolingMakeDetailIds) {
		return this.loadAll(ToolingMakeDetail.class, toolingMakeDetailIds);
	}

	public List<ToolingMakeDetail> loadAllToolingMakeDetails() {
		return this.loadAll(ToolingMakeDetail.class);
	}

	public void storeToolingMakeDetail(ToolingMakeDetail toolingMakeDetail) {
		this.store(toolingMakeDetail);
	}

	public void deleteToolingMakeDetail(ToolingMakeDetail toolingMakeDetail) {
		this.delete(toolingMakeDetail);
	}

	public void deleteAllToolingMakeDetail(Collection<ToolingMakeDetail> toolingMakeDetails) {
		this.deleteAll(toolingMakeDetails);
	}

}
