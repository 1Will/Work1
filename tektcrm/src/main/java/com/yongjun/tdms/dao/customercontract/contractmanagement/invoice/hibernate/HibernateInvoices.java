package com.yongjun.tdms.dao.customercontract.contractmanagement.invoice.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.invoice.InvoiceDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;

public class HibernateInvoices extends BaseHibernateDao implements InvoiceDao{

	public void storeInvoices(Invoices in) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().clear();
		super.store(in);
	}

	public Invoices loadInvoices(Long inId) {
		// TODO Auto-generated method stub
		return super.load(Invoices.class, inId);
	}

	public List<Invoices> loadAllInvoices() {
		// TODO Auto-generated method stub
		return super.loadAll(Invoices.class);
	}

	public List<Invoices> loadAllInvoices(Long[] inIds) {
		// TODO Auto-generated method stub
		return super.loadAll(Invoices.class, inIds);
	}

	public void deleteInvoices(Invoices in) {
		super.delete(in);
	}

	public void deleteAllInvoices(List<Invoices> inIds) {
		// TODO Auto-generated method stub
		super.deleteAll(inIds);
	}

	public List<Invoices> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return super.loadByKey(Invoices.class, key, value);
	}

}
