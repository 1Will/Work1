package com.yongjun.tdms.dao.prophase.business;

import java.util.Collection;
import java.util.List;


import com.yongjun.tdms.model.prophase.business.MiddleCheck;

public interface MiddleCheckDao {

	MiddleCheck loadMiddleCheck(Long id);
    List<MiddleCheck> loadMiddleChecks(Long[] MiddleCheckIds);
	void storeMiddleCheck(MiddleCheck middleCheck);
	void deleteMiddleCheck(MiddleCheck middleCheck);
	void deleteAllMiddleCheck(Collection<MiddleCheck> MiddleCheckIds);
}
