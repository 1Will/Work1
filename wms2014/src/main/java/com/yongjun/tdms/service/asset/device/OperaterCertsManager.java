package com.yongjun.tdms.service.asset.device;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.OperateCert;

@Transactional(readOnly = true)
public interface OperaterCertsManager {
	List<OperateCert> loadAllOperateCert(Long [] operateCertIds);
	
	@Transactional
	void deleteAllOperateCert(Collection<OperateCert> operateCert);
	
	OperateCert loadOperateCert(Long operateCertId);
	
	@Transactional
	void storeOperateCert(OperateCert operateCert);
}
