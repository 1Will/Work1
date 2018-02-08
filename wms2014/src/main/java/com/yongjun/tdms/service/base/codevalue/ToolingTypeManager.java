package com.yongjun.tdms.service.base.codevalue;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.base.financeType.FinanceType;

@Transactional(readOnly = true)
public interface ToolingTypeManager {
	ToolingType loadToolingType(Long toolingTypeId);
	
	List<ToolingType> loadAllToolingTypes(Long [] toolingTypeIds);

	List<ToolingType> loadAllToolingTypes();
	
	@Transactional
	void storeToolingType(ToolingType toolingType);
	
	@Transactional
	void deleteToolingType(ToolingType toolingType);
	
	@Transactional
	void deleteAllToolingTypes(Collection<ToolingType> toolingTypes);
	
	List createSelectToolingType(String name);
	
	@Transactional
	void disabledAllToolingTypes(Collection<ToolingType> toolingTypes);
    
	@Transactional
	void enabledAllToolingTypes(Collection<ToolingType> toolingTypes);
}
