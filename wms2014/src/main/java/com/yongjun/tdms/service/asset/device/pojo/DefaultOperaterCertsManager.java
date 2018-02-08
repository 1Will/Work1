package com.yongjun.tdms.service.asset.device.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.dao.asset.device.OperaterCertDao;
import com.yongjun.tdms.model.asset.device.OperateCert;
import com.yongjun.tdms.service.asset.device.OperaterCertsManager;

public class DefaultOperaterCertsManager implements OperaterCertsManager{
	private final OperaterCertDao operaterCertDao;
	
	public DefaultOperaterCertsManager(OperaterCertDao operaterCertDao){
		this.operaterCertDao = operaterCertDao;
	}
	
	public List<OperateCert> loadAllOperateCert(Long[] operateCertIds) {
		return this.operaterCertDao.loadAllOperateCert(operateCertIds);
	}

	public void deleteAllOperateCert(Collection<OperateCert> operateCert) {
		this.operaterCertDao.deleteAllOperateCert(operateCert);
	}

	public OperateCert loadOperateCert(Long operateCertId) {
		return this.operaterCertDao.loadOperateCert(operateCertId);
	}

	public void storeOperateCert(OperateCert operateCert) {
		this.operaterCertDao.storeOperateCert(operateCert);
	}

}
