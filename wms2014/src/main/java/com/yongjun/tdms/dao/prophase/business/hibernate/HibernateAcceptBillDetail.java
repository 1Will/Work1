package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDetailDao;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;

public class HibernateAcceptBillDetail  extends BaseHibernateDao implements AcceptBillDetailDao{

	public AcceptBillDetail loadAcceptBillDetail(Long AcceptBillDetailId) {
		
		return this.load(AcceptBillDetail.class,AcceptBillDetailId);
	}

	public List<AcceptBillDetail> loadAcceptBillDetais(Long[] AcceptBillDetaiIds) {
		
		return this.loadAll(AcceptBillDetail.class,AcceptBillDetaiIds);
	}

	public void storeAcceptBillDetail(AcceptBillDetail acceptBillDetai) {
		    this.store(acceptBillDetai);
		
	}

	public void deleteAcceptBillDetail(AcceptBillDetail acceptBillDetail) {
	        this.delete(acceptBillDetail);
		
	}

	public void deleteAllAcceptBillDetails(Collection<AcceptBillDetail> AcceptBillDetails) {
		    this.deleteAll(AcceptBillDetails);
		
	}

	public void storeToolingAcceptBillToolingMakeDetail(AcceptBillDetail acceptBillDetai) {
		this.store(acceptBillDetai);
		
	}

	public void deleteToolingAllAcceptBillMakeDetails(Collection<AcceptBillDetail> AcceptBillDetails) {
		this.deleteAll(AcceptBillDetails);
		
	}

}
