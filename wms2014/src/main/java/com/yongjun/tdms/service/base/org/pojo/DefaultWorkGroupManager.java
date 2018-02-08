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
package com.yongjun.tdms.service.base.org.pojo;

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.org.WorkGroupDao;
import com.yongjun.tdms.model.base.org.WorkGroup;
import com.yongjun.tdms.service.base.org.WorkGroupManager;

/**
 * @author qs
 * @version $Id: DefaultWorkGroupManager.java 8510 2007-11-21 07:21:41Z qsun $
 */
public class DefaultWorkGroupManager extends BaseManager implements
		WorkGroupManager {
	private final WorkGroupDao workGroupDao;
	
	public DefaultWorkGroupManager(final WorkGroupDao workGroupDao) {
		this.workGroupDao = workGroupDao;
	}
	
	public List<WorkGroup> loadAllWorkGroups() {
		return workGroupDao.loadAllWorkGroups();
	}

	public List<WorkGroup> createSelectWorkGroups(String label) {
		WorkGroup w = new WorkGroup();
		w.setId(Long.valueOf(-1L));
		w.setName(label);
		List<WorkGroup> tmp = loadAllWorkGroups();
		tmp.add(0, w);
		return tmp;
	}
}
