package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.InspectStandardDao;
import com.yongjun.tdms.model.prophase.business.InspectStandard;

public class HibernateInspectStandard extends BaseHibernateDao implements InspectStandardDao {


	public InspectStandard loadInspectStandard(Long id) {
	
		return this.load(InspectStandard.class,id);
	}

	public List<InspectStandard> loadInspectStandards(Long[] InspectStandardIds) {

		return this.loadAll(InspectStandard.class,InspectStandardIds);
	}

	public void storeInspectStandard(InspectStandard inspectStandard) {

		this.store(inspectStandard);
	}

	public void deleteInspectStandard(InspectStandard inspectStandard) {
		
		this.delete(inspectStandard);
	}

	public void deleteAllInspectStandard(Collection<InspectStandard> inspectStandardIds) {
	
		this.deleteAll(inspectStandardIds);
		
	}

}
