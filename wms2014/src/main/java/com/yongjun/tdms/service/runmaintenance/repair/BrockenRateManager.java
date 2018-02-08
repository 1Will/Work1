package com.yongjun.tdms.service.runmaintenance.repair;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
@Transactional(readOnly=true)
public interface BrockenRateManager {
	List<BrockenRate> loadAllBrockenRates(Long [] brockenRateIds);
	@Transactional
	BrockenRate loadBrockenRate(Long brockenRateId);
}
