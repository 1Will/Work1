package com.yongjun.tdms.dao.runmaintenance.discard.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.discard.DiscardBillDtlDao;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBillDtl;

public class HibernateDiscardBillDtl extends BaseHibernateDao implements DiscardBillDtlDao{

	public DiscardBillDtl loadDiscardBillDtl(Long discardBillDtlId) {
		
		return this.load(DiscardBillDtl.class,discardBillDtlId);
	}

	public List<DiscardBillDtl> loadAllDiscardBillDtls(Long[] discardBillDtlIds) {
		
		return this.loadAll(DiscardBillDtl.class,discardBillDtlIds);
	}

	public void storeDiscardBillDtl(DiscardBillDtl discardBillDtl) {
		
		this.store(discardBillDtl);
	}

	public void deleteDiscardBillDtl(DiscardBillDtl discardBillDtl) {
		
		this.delete(discardBillDtl);
	}

	public void deleteAllDiscardBillDtl(Collection<DiscardBillDtl> discardBillDtls) {
		
		this.deleteAll(discardBillDtls);
	}

}
