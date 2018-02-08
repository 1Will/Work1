package com.yongjun.tdms.service.customercontract.contractmanagement.invoices.invoicesList.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.customercontract.contractmanagement.invoice.invoicesList.InvoicesListDao;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList.InvoicesList;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.invoicesList.InvoicesListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/** 
  * @author 创建人: xichunguang
  * @date 创建时间：2017年11月14日 上午11:29:36 
  * 类说明 
  */
public class DefaultInvoicesListManager implements InvoicesListManager {
	private InvoicesListDao invoicesListDao;
	private final YongJunSequenceManager yongJunSequenceManager;
	private final ProductListManager productListManager;
	private final InvoicesManager invoicesManager;
	private final ProductsManager productsManager;
	
	public DefaultInvoicesListManager(InvoicesListDao invoicesListDao,YongJunSequenceManager yongJunSequenceManager,ProductListManager productListManager
			,InvoicesManager invoicesManager,ProductsManager productsManager) {
		// TODO Auto-generated constructor stub
		this.invoicesListDao = invoicesListDao;
		this.yongJunSequenceManager = yongJunSequenceManager;
		this.productListManager = productListManager;
		this.invoicesManager = invoicesManager;
		this.productsManager = productsManager;
	}
	
	public void storeInvoicesList(InvoicesList inList) {
		// TODO Auto-generated method stub
		if(inList.isNew()){
			inList.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_INVOICESLIST));
		}
		this.invoicesListDao.storeInvoicesList(inList);
	}

	public InvoicesList loadInvoicesList(Long inListId) {
		// TODO Auto-generated method stub
		return this.invoicesListDao.loadInvoicesList(inListId);
	}

	public List<InvoicesList> loadAllInvoicesList() {
		// TODO Auto-generated method stub
		return this.invoicesListDao.loadAllInvoicesList();
	}

	public List<InvoicesList> loadAllInvoicesList(Long[] inListIds) {
		// TODO Auto-generated method stub
		return this.invoicesListDao.loadAllInvoicesList(inListIds);
	}

	public void deleteInvoicesList(InvoicesList inList) {
		// TODO Auto-generated method stub
		this.invoicesListDao.deleteInvoicesList(inList);
	}

	public void deleteAllInvoicesList(List<InvoicesList> inList) {
		// TODO Auto-generated method stub
		this.invoicesListDao.deleteAllInvoicesList(inList);
	}

	public List<InvoicesList> loadByKey(String key, Object value) throws DaoException {
		// TODO Auto-generated method stub
		return this.invoicesListDao.loadByKey(key, value);
	}

	public void saveInvoicesList(String productListId,String invoicesId) {
		// TODO Auto-generated method stub
		Invoices invoices = invoicesManager.loadInvoices(Long.parseLong(invoicesId));
		String[] arr = productListId.split(",");
		if("productList".equals(arr[arr.length-1])){
			if(arr!=null && arr.length>1){
				for(int i = 0;i < arr.length-1;i++){
					ProductList productList = productListManager.loadProductList(Long.parseLong(arr[i]));
					InvoicesList invoicesList = new InvoicesList();
					invoicesList.setProductList(productList);
					invoicesList.setInvoices(invoices);
					invoicesList.setRestSum(productList.getCount()-productList.getDeliveryedCount());
					invoicesList.setIsSaved("0");
					invoicesList.setDeliveryPrice(productList.getUnitPrice()*productList.getDeliveryedCount());
					this.storeInvoicesList(invoicesList);
				}
			}
		}else if("product".equals(arr[arr.length-1])){
			if(arr!=null && arr.length>1){
				for(int i = 0;i < arr.length-1;i++){
					Products products = productsManager.loadProducts(Long.parseLong(arr[i]));
					InvoicesList invoicesList = new InvoicesList();
					invoicesList.setProducts(products);
					invoicesList.setInvoices(invoices);
					invoicesList.setRestSum(0);
					invoicesList.setIsSaved("0");
					invoicesList.setDeliveryPrice(Double.parseDouble("0"));
					this.storeInvoicesList(invoicesList);
				}
			}
		}
	}

}
