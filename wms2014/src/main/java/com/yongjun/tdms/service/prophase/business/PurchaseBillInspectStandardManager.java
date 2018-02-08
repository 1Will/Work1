package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.business.InspectStandard;
@Transactional(readOnly=true)
public interface PurchaseBillInspectStandardManager {
	@Transactional
	InspectStandard loadInspectStandard(Long id);
    List<InspectStandard> loadInspectStandards(Long[] InspectStandardIds);
	@Transactional
	void storeInspectStandard(InspectStandard inspectStandard);
	@Transactional
	void deleteInspectStandard(InspectStandard inspectStandard);
	@Transactional
	void deleteAllInspectStandard(Collection<InspectStandard> inspectStandardIds);
}
