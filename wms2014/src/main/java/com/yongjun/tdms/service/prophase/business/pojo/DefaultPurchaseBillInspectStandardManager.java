package com.yongjun.tdms.service.prophase.business.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.InspectStandardDao;
import com.yongjun.tdms.model.prophase.business.InspectStandard;
import com.yongjun.tdms.service.prophase.business.PurchaseBillInspectStandardManager;

public class DefaultPurchaseBillInspectStandardManager extends BaseManager implements PurchaseBillInspectStandardManager{
   private final InspectStandardDao inspectStandardDao;
   public DefaultPurchaseBillInspectStandardManager(InspectStandardDao inspectStandardDao){
	   this.inspectStandardDao=inspectStandardDao;
   }
public InspectStandard loadInspectStandard(Long id) {
	
	return inspectStandardDao.loadInspectStandard(id);
}
public List<InspectStandard> loadInspectStandards(Long[] InspectStandardIds) {
	
	return inspectStandardDao.loadInspectStandards(InspectStandardIds);
}
public void storeInspectStandard(InspectStandard inspectStandard) {
	
	inspectStandardDao.storeInspectStandard(inspectStandard);
}
public void deleteInspectStandard(InspectStandard inspectStandard) {
	inspectStandardDao.deleteInspectStandard(inspectStandard);
	
}
public void deleteAllInspectStandard(Collection<InspectStandard> inspectStandardIds) {
	inspectStandardDao.deleteAllInspectStandard(inspectStandardIds);
	
}
	
}
