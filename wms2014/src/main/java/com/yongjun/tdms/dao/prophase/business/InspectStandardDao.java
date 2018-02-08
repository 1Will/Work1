package com.yongjun.tdms.dao.prophase.business;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.prophase.business.InspectStandard;

public interface InspectStandardDao {
	InspectStandard loadInspectStandard(Long id);
    List<InspectStandard> loadInspectStandards(Long[] InspectStandardIds);
	void storeInspectStandard(InspectStandard inspectStandard);
	void deleteInspectStandard(InspectStandard inspectStandard);
	void deleteAllInspectStandard(Collection<InspectStandard> inspectStandardIds);
}
