package com.yongjun.tdms.dao.customercontract.contractmanagement.invoice.invoicesList.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.invoice.invoicesList.InvoicesListDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList.InvoicesList;

public class HibernateInvoicesList extends BaseHibernateDao implements InvoicesListDao{

	public void storeInvoicesList(InvoicesList inList) {
		// TODO Auto-generated method stub
		super.getHibernateTemplate().clear();
		super.store(inList);
	}

	public InvoicesList loadInvoicesList(Long inListId) {
		// TODO Auto-generated method stub
		return super.load(InvoicesList.class, inListId);
	}

	public List<InvoicesList> loadAllInvoicesList() {
		// TODO Auto-generated method stub
		return super.loadAll(InvoicesList.class);
	}

	public List<InvoicesList> loadAllInvoicesList(Long[] inListIds) {
		// TODO Auto-generated method stub
		return super.loadAll(InvoicesList.class, inListIds);
	}

	public void deleteInvoicesList(InvoicesList inList) {
		// TODO Auto-generated method stub
		super.delete(inList);
	}

	public void deleteAllInvoicesList(List<InvoicesList> inList) {
		// TODO Auto-generated method stub
		super.deleteAll(inList);
	}

	public List<InvoicesList> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return super.loadByKey(InvoicesList.class, key, value);
	}

}
