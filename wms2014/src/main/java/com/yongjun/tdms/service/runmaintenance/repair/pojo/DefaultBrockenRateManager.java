package com.yongjun.tdms.service.runmaintenance.repair.pojo;

import java.util.List;

import com.yongjun.tdms.dao.runmaintenance.repair.BrockenRateDao;
import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;
import com.yongjun.tdms.service.runmaintenance.repair.BrockenRateManager;

public class DefaultBrockenRateManager implements BrockenRateManager{
    private final BrockenRateDao   	brockenRateDao ;
    public DefaultBrockenRateManager(BrockenRateDao   	brockenRateDao){
    	this.brockenRateDao=brockenRateDao;
    }
	
	public List<BrockenRate> loadAllBrockenRates(Long[] brockenRateIds) {
		return brockenRateDao.loadAllBrockenRates(brockenRateIds);
	
	}

	public BrockenRate loadBrockenRate(Long brockenRateId) {
		
		return brockenRateDao.loadBrockenRate(brockenRateId);
	}

}
