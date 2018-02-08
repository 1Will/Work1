package com.yongjun.tdms.service.base.codevalue;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;
@Transactional(readOnly = true)
public interface ToolingTypeDetailManager {
	ToolingTypeDetail loadToolingTypeDetail(Long  toolingTypeDetailId);

	List<ToolingTypeDetail> loadAllToolingTypeDetails(Long []  toolingTypeDetailIds);
	
	List<ToolingTypeDetail> loadAllToolingTypeDetails();
	@Transactional
	void storeToolingTypeDetail(ToolingTypeDetail toolingTypeDetail);
	@Transactional
	void deleteToolingTypeDetail(ToolingTypeDetail toolingTypeDetail);
	@Transactional
	void deleteAllToolingTypeDetails(Collection<ToolingTypeDetail> toolingTypeDetails);
	@Transactional
	List createSelectToolingTypeDetail(String name);

	
}
