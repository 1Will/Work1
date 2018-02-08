/**
 * 
 */
package com.yongjun.tdms.service.base.codevalue.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.codevalue.SpareTypeDao;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;

/**
 *<p>Title:DefaultSpareTypeManager.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public class DefaultSpareTypeManager extends BaseManager implements SpareTypeManager {

	private final SpareTypeDao spareTypeDao;
	
	public DefaultSpareTypeManager(SpareTypeDao spareTypeDao){
		this.spareTypeDao = spareTypeDao;
	}
	
	public void deleteAllSpareType(Collection<SpareType> spareTypes) {
		this.spareTypeDao.deleteAllSpareType(spareTypes);
	}

	public void deleteSpareType(SpareType spareType) {
		spareTypeDao.deleteSpareType(spareType);
	}

	public List<SpareType> loadAllSpareType(Long[] spareTypeIds) {
		return spareTypeDao.loadAllSpareType(spareTypeIds);
	}

	public List<SpareType> loadAllSpareType() {
		return spareTypeDao.loadAllSpareType();
	}

	public SpareType loadSpareType(Long spareTypeId) {
		return spareTypeDao.loadSpareType(spareTypeId);
	}

	public void storeSpareType(SpareType spareType) {
		spareTypeDao.storeSpareType(spareType);
	}

	
	public void disabledAllSpareType(Collection<SpareType> spareTypes) {
		for(SpareType spareType : spareTypes){
			spareType.setDisabled(true);
			spareTypeDao.storeSpareType(spareType);
		}
	}

	public void endabledAllSpareType(Collection<SpareType> spareTypes) {
		for(SpareType spareType : spareTypes){
			spareType.setDisabled(false);
			spareTypeDao.storeSpareType(spareType);
		}
	}

	public List<SpareType> loadAllSpareTypeByToolingDevFlag(String toolingDevFlag) {
		return this.spareTypeDao.loadAllSpareTypeByToolingDevFlag(toolingDevFlag);
	}
}
