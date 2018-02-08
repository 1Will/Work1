package com.yongjun.tdms.dao.base.codevalue.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.codevalue.ToolingTypeDao;
import com.yongjun.tdms.model.base.codevalue.ToolingType;

public class HibernateToolingType extends BaseHibernateDao implements ToolingTypeDao{

	public ToolingType loadToolingType(Long toolingTypeId) {
		return this.load(ToolingType.class, toolingTypeId);
	}

	public List<ToolingType> loadAllToolingTypes(Long[] toolingTypeIds) {
		return this.loadAll(ToolingType.class, toolingTypeIds);
	}

	public List<ToolingType> loadAllToolingTypes() {
		return this.loadAll(ToolingType.class);
	}

	public void storeToolingType(ToolingType toolingType) {
		this.store(toolingType);
	}

	public void deleteToolingType(ToolingType toolingType) {
		this.delete(toolingType);
	}

	public void deleteAllToolingTypes(Collection<ToolingType> toolingTypes) {
		this.deleteAll(toolingTypes);
	}

}
