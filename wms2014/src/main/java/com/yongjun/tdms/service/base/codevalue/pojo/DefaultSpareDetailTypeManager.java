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
package com.yongjun.tdms.service.base.codevalue.pojo;

import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.codevalue.SpareDetailTypeDao;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.service.base.codevalue.SpareDetailTypeManager;

/**
 * <p>Title:DefaultSpareDetailTypeManager
 * <p>Description:备件分类明细服务控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class DefaultSpareDetailTypeManager extends BaseManager implements
		SpareDetailTypeManager {
	private final SpareDetailTypeDao spareDetailTypeDao;
	public DefaultSpareDetailTypeManager(SpareDetailTypeDao spareDetailTypeDao){
		this.spareDetailTypeDao = spareDetailTypeDao;
	}
	public List<SpareDetailType> loadAllSpareDetailTypes() {
		return spareDetailTypeDao.loadAllSpareDetailTypes();
	}

	public SpareDetailType loadSpareDetailTypeByCode(String code) {
		return this.spareDetailTypeDao.loadSpareDetailTypeByCode(code);
	}

	public SpareDetailType loadSpareDetailType(Long id) {
		return this.spareDetailTypeDao.loadSpareDetailType(id);
	}
	public void storeSpareDetailType(SpareDetailType spareDetailType) {
		this.spareDetailTypeDao.storeSpareDetailType(spareDetailType);
	}
	public void deleteSpareDetailType(SpareDetailType spareDetailType) {
		this.spareDetailTypeDao.deleteSpareDetailType(spareDetailType);
	}
	public void deleteAllSpareDetailType(List<SpareDetailType> list) {
		this.spareDetailTypeDao.deleteAllSpareDetailType(list);
	}
	public List<SpareDetailType> loadAllSpareDetailTypes(Long[] ids) {
		return this.spareDetailTypeDao.loadAllSpareDetailTypes(ids);
	}
	public List loadSpareDetailBySpareTypeId(String value) {
		Long v = Long.valueOf(value);
		return this.spareDetailTypeDao.loadSpareDetailBySpareTypeId(v);
	}

}
