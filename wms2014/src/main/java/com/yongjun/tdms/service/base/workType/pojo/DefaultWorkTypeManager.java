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
package com.yongjun.tdms.service.base.workType.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.base.workType.WorkTypeDao;
import com.yongjun.tdms.model.base.workType.WorkType;
import com.yongjun.tdms.service.base.workType.WorkTypeManager;
/**
 * <p>Title: DefaultPreRepairPlanDetailManager
 * <p>Description: 预防性维修计划详细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: DefaultPreRepairPlanDetailManager.java 11153 2008-02-28 13:05:34Z zbzhang $
 * @see com.yongjun.tdms.service.base.workType.WorkTypeManager
 */
public class DefaultWorkTypeManager implements WorkTypeManager {

	private final WorkTypeDao workTypeDao;
	
	public DefaultWorkTypeManager(WorkTypeDao workTypeDao) {
		this.workTypeDao = workTypeDao;
	}
	
	public WorkType loadWorkType(Long workTypeId) {
		return this.workTypeDao.loadWorkType(workTypeId);
	}

	public List<WorkType> loadAllWorkTypes(Long[] workTypeIds) {
		return this.workTypeDao.loadAllWorkTypes(workTypeIds);
	}

	public List<WorkType> loadAllWorkTypes() {
		return this.workTypeDao.loadAllWorkTypes();
	}

	public void storeWorkType(WorkType workType) {
		this.workTypeDao.storeWorkType(workType);
	}

	public void deleteWorkType(WorkType workType) {
		this.workTypeDao.deleteWorkType(workType);
	}

	public void deleteAllWorkTypes(Collection<WorkType> workTypes) {
		this.workTypeDao.deleteAllWorkTypes(workTypes);
	}

}
