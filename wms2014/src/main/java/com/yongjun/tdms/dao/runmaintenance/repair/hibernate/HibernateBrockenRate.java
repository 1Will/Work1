package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.repair.BrockenRateDao;
import com.yongjun.tdms.model.runmaintenance.repair.BrockenRate;

public class HibernateBrockenRate extends BaseHibernateDao  implements BrockenRateDao{



	public List<BrockenRate> loadAllBrockenRates(Long[] brockenRateIds) {
		
		return this.loadAll(BrockenRate.class,brockenRateIds);
	}

	public BrockenRate loadBrockenRate(Long brockenRateId) {
		
		return this.load(BrockenRate.class,brockenRateId);
	}

	
}
