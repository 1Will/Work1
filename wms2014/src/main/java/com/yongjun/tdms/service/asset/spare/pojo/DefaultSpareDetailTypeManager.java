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
package com.yongjun.tdms.service.asset.spare.pojo;

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.codevalue.SpareDetailTypeDao;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;

/**
 * <p>Title: DefaultSpareDetailTypeManager
 * <p>Description: 备件明细管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public class DefaultSpareDetailTypeManager extends BaseManager implements SpareDetailTypeManager{
	private final SpareDetailTypeDao spareDetailTypeDao;
	
	public DefaultSpareDetailTypeManager (SpareDetailTypeDao spareDetailTypeDao){
		this.spareDetailTypeDao = spareDetailTypeDao;
	}

	public List<SpareDetailType> loadAllSpareDetailTypes() {
		return this.spareDetailTypeDao.loadAllSpareDetailTypes();
	}

	public SpareDetailType loadSpareDetailType(Long spareDetailTypeId) {
		return spareDetailTypeDao.loadSpareDetailType(spareDetailTypeId);
	}
	
	public List createSelectSpareDetailType(String name) {
		List<SpareDetailType> spareDetailTypes = this.loadAllSpareDetailTypes();
		SpareDetailType spareDetailType = new SpareDetailType();
		spareDetailType.setId(Long.valueOf(-1L));
		spareDetailType.setName(name);
		spareDetailTypes.add(0,spareDetailType);
		return spareDetailTypes;
	}

}
