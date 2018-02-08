package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.business.MiddleCheck;
@Transactional(readOnly=true)
public interface PurchaseBillMiddleCheckManager {
	@Transactional
	MiddleCheck loadMiddleCheck(Long id);
    List<MiddleCheck> loadMiddleChecks(Long[] MiddleCheckIds);
	@Transactional
	void storeMiddleCheck(MiddleCheck middleCheck);
	@Transactional
	void deleteMiddleCheck(MiddleCheck middleCheck);
	@Transactional
	void deleteAllMiddleCheck(Collection<MiddleCheck> MiddleCheckIds);
}
