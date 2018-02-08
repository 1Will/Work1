package com.yongjun.tdms.service.prophase.business.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.MiddleCheckDao;
import com.yongjun.tdms.model.prophase.business.MiddleCheck;
import com.yongjun.tdms.service.prophase.business.PurchaseBillMiddleCheckManager;

public class DefaultPurchaseBillMiddleCheckManager extends BaseManager implements PurchaseBillMiddleCheckManager{
 private final MiddleCheckDao middleCheckDao;
public  DefaultPurchaseBillMiddleCheckManager(MiddleCheckDao middleCheckDao){
	this.middleCheckDao=middleCheckDao;
	 
 }
	public MiddleCheck loadMiddleCheck(Long id) {
		
		return middleCheckDao.loadMiddleCheck(id);
	}

	public List<MiddleCheck> loadMiddleChecks(Long[] MiddleCheckIds) {
	
		return middleCheckDao.loadMiddleChecks(MiddleCheckIds);
	}

	public void storeMiddleCheck(MiddleCheck middleCheck) {
		
		middleCheckDao.storeMiddleCheck(middleCheck);
	}

	public void deleteMiddleCheck(MiddleCheck middleCheck) {
		
		middleCheckDao.deleteMiddleCheck(middleCheck);
	}

	public void deleteAllMiddleCheck(Collection<MiddleCheck> MiddleCheckIds) {
		
		middleCheckDao.deleteAllMiddleCheck(MiddleCheckIds);
	}

}
