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
package com.yongjun.tdms.dao.base.workType.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.workType.WorkTypeDao;
import com.yongjun.tdms.model.base.workType.WorkType;

/**
 * <p>Title: HibernatePreRepairPlan
 * <p>Description: 工种数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernatePreRepairPlan.java 10303 2008-01-10 06:37:07Z wzou $
 */
public class HibernateWorkType extends BaseHibernateDao implements WorkTypeDao {

	public WorkType loadWorkType(Long workTypeId) {
		return this.load(WorkType.class, workTypeId);
	}

	public List<WorkType> loadAllWorkTypes(Long[] workTypeIds) {
		return this.loadAll(WorkType.class, workTypeIds);
	}

	public List<WorkType> loadAllWorkTypes() {
		return this.loadAll(WorkType.class);
	}

	public void storeWorkType(WorkType workType) {
		this.store(workType);
	}

	public void deleteWorkType(WorkType workType) {
		this.delete(workType);
	}

	public void deleteAllWorkTypes(Collection<WorkType> workTypes) {
		this.deleteAll(workTypes);
	}

}
