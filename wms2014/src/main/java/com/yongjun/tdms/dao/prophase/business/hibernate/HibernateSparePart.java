package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.SparePartDao;
import com.yongjun.tdms.model.prophase.business.SparePart;

public class HibernateSparePart  extends BaseHibernateDao implements SparePartDao{

	public SparePart loadSparePart(Long id) {
		
		return this.load(SparePart.class,id);
	}

	public List<SparePart> loadSpareParts(Long[] SparePartIds) {
		
		return this.loadAll(SparePart.class,SparePartIds);
	}

	public void storeSparePart(SparePart sparePart) {
		this.store(sparePart);
		
	}

	public void deleteSparePart(SparePart sparePart) {
		   this.delete(sparePart);
		
	}

	public void deleteAllSparePart(Collection<SparePart> SparePartIds) {
		this.deleteAll(SparePartIds);
		
	}

}
