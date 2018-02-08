package com.yongjun.tdms.dao.asset.device;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.asset.device.OperateCert;

public interface OperaterCertDao {
	List<OperateCert> loadAllOperateCert(Long [] operateCertIds);
	
	void deleteAllOperateCert(Collection<OperateCert> operateCert);
	
	OperateCert loadOperateCert(Long operateCertId);
	
	void storeOperateCert(OperateCert operateCert);
}
