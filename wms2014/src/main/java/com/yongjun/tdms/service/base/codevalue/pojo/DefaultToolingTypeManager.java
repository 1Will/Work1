package com.yongjun.tdms.service.base.codevalue.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.codevalue.ToolingTypeDao;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;

public class DefaultToolingTypeManager extends BaseManager implements ToolingTypeManager{
	private final ToolingTypeDao toolingTypeDao;
	
	public DefaultToolingTypeManager(ToolingTypeDao toolingTypeDao) {
		this.toolingTypeDao = toolingTypeDao;
	}
	
	public ToolingType loadToolingType(Long toolingTypeId) {
		return this.toolingTypeDao.loadToolingType(toolingTypeId);
	}

	public List<ToolingType> loadAllToolingTypes(Long[] toolingTypeIds) {
		return this.toolingTypeDao.loadAllToolingTypes(toolingTypeIds);
	}

	public List<ToolingType> loadAllToolingTypes() {
		return this.toolingTypeDao.loadAllToolingTypes();
	}

	public void storeToolingType(ToolingType toolingType) {
		this.toolingTypeDao.storeToolingType(toolingType);
	}

	public void deleteToolingType(ToolingType toolingType) {
		this.toolingTypeDao.deleteToolingType(toolingType);
	}

	public void deleteAllToolingTypes(Collection<ToolingType> toolingTypes) {
		this.toolingTypeDao.deleteAllToolingTypes(toolingTypes);
	}

	public List createSelectToolingType(String name) {
		List<ToolingType> toolingTypes = this.loadAllToolingTypes();
		ToolingType toolingType = new ToolingType();
		toolingType.setId(Long.valueOf(-1L));
		toolingType.setValue(name);
		toolingTypes.add(0,toolingType);
		return toolingTypes;
	}

	public void disabledAllToolingTypes(Collection<ToolingType> toolingTypes) {
		for(ToolingType toolingType : toolingTypes){
			toolingType.setDisabled(true);
			this.toolingTypeDao.storeToolingType(toolingType);
		}
	}

	public void enabledAllToolingTypes(Collection<ToolingType> toolingTypes) {
		for(ToolingType toolingType : toolingTypes){
			toolingType.setDisabled(false);
			this.toolingTypeDao.storeToolingType(toolingType);
		}
	}

}
