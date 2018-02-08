package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDao;
import com.yongjun.tdms.model.prophase.business.AcceptBill;

public class HibernateAcceptBill extends BaseHibernateDao implements AcceptBillDao{

	public AcceptBill loadAcceptBill(Long id) {
		
		return this.load(AcceptBill.class,id);
	}

	public List<AcceptBill> loadAcceptBills(Long[] AcceptBillIds) {
		
		return this.loadAll(AcceptBill.class,AcceptBillIds);
	}

	public void storeAcceptBill(AcceptBill acceptBill) {
		
		this.store(acceptBill);
	}

	public void deleteAcceptBill(AcceptBill acceptBill) {
		this.delete(acceptBill);
		
	}

	public void deleteAllAcceptBill(Collection<AcceptBill> acceptBillIds) {
		
		this.deleteAll(acceptBillIds);
	}

}
