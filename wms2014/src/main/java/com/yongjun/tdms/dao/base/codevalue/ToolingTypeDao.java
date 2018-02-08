package com.yongjun.tdms.dao.base.codevalue;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.codevalue.ToolingType;

public interface ToolingTypeDao {
	ToolingType loadToolingType(Long toolingTypeId);
	
	List<ToolingType> loadAllToolingTypes(Long [] toolingTypeIds);

	List<ToolingType> loadAllToolingTypes();
	
	void storeToolingType(ToolingType toolingType);
	
	void deleteToolingType(ToolingType toolingType);
	
	void deleteAllToolingTypes(Collection<ToolingType> toolingTypes);
}
