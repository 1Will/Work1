package com.yongjun.tdms.dao.base.codevalue;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;

public interface ToolingTypeDetailDao {
	ToolingTypeDetail loadToolingTypeDetail(Long  toolingTypeDetailId);

	List<ToolingTypeDetail> loadAllToolingTypeDetails(Long []  toolingTypeDetailIds);
	
	List<ToolingTypeDetail> loadAllToolingTypeDetails();
	
	void storeToolingTypeDetail(ToolingTypeDetail toolingTypeDetail);
	
	void deleteToolingTypeDetail(ToolingTypeDetail toolingTypeDetail);
	
	void deleteAllToolingTypeDetails(Collection<ToolingTypeDetail> toolingTypeDetails);
}
