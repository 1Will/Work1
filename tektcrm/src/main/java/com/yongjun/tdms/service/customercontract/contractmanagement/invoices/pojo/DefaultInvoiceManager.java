package com.yongjun.tdms.service.customercontract.contractmanagement.invoices.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.additionalInfo.ContractAdditionalInfoDao;
import com.yongjun.tdms.dao.customercontract.contractmanagement.invoice.InvoiceDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultInvoiceManager extends BaseManager implements InvoicesManager {
	
	// 发货单数据访问层接口
	private final InvoiceDao invoiceDao;
	private final YongJunSequenceManager yongJunSequenceManager;
	public DefaultInvoiceManager(InvoiceDao invoiceDao,YongJunSequenceManager yongJunSequenceManager) {
		// TODO Auto-generated constructor stub
		this.invoiceDao = invoiceDao;
		this.yongJunSequenceManager=yongJunSequenceManager;
	}
	
	public void storeInvoices(Invoices in) {
		/*if(in.isNew()){
			in.setDeliveryNum((String)this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_INVOICES));
		    }*/
		invoiceDao.storeInvoices(in);
	}

	public Invoices loadInvoices(Long inId) {
		// TODO Auto-generated method stub
		return invoiceDao.loadInvoices(inId);
	}

	public List<Invoices> loadAllInvoices() {
		// TODO Auto-generated method stub
		return invoiceDao.loadAllInvoices();
	}

	public List<Invoices> loadAllInvoices(Long[] inIds) {
		// TODO Auto-generated method stub
		return invoiceDao.loadAllInvoices(inIds);
	}

	public void deleteInvoices(Invoices in) {
		// TODO Auto-generated method stub
		invoiceDao.deleteInvoices(in);
	}

	public void deleteAllInvoices(List<Invoices> inIds) {
		// TODO Auto-generated method stub
		invoiceDao.deleteAllInvoices(inIds);
	}

	public List<Invoices> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return invoiceDao.loadByKey(key, value);
	}

}
