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
package com.yongjun.tdms.dao.year.device.runmaintainPlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.PivotalSpareDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PivotalSpare;

/**
 * <p>Title: HibernatePivotalSpare
 * <p>Description: 运维计划的关键备件数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.runmaintainPlan.PivotalSpareDao
 * @version $Id:$
 */
public class HibernatePivotalSpare extends BaseHibernateDao implements
		PivotalSpareDao {

	public PivotalSpare loadPivotalSpare(Long pivotalSpareId) {
		return this.load(PivotalSpare.class, pivotalSpareId);
	}

	public List<PivotalSpare> loadAllPivotalSpares(Long[] pivotalSpareIds) {
		return this.loadAll(PivotalSpare.class, pivotalSpareIds);
	}

	public List<PivotalSpare> loadAllPivotalSpares() {
		return this.loadAll(PivotalSpare.class);
	}

	public void storePivotalSpare(PivotalSpare pivotalSpare) {
		this.store(pivotalSpare);
	}

	public void deletePivotalSpare(PivotalSpare pivotalSpare) {
		this.delete(pivotalSpare);
	}

	public void deleteAllPivotalSpares(Collection<PivotalSpare> pivotalSpares) {
		this.deleteAll(pivotalSpares);
	}

}
