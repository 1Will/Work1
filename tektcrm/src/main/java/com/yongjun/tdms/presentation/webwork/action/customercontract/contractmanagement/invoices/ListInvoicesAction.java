package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.invoices;

import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;

@SuppressWarnings({"rawtypes","uncheck"})
public class ListInvoicesAction extends ValueListAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final InvoicesManager invoicesManager;
	private final ContractManagementManager contractManagementManager;
	private final ProductListManager productListManager;
	private ContractManagement contractManagement;
    private ProductList productList;
	private List<Invoices> contractInvoiceses = null;
	
	public ListInvoicesAction(InvoicesManager invoicesManager,ContractManagementManager contractManagementManager,ProductListManager productListManager){
		this.invoicesManager = invoicesManager;
		this.contractManagementManager = contractManagementManager;
		this.productListManager=productListManager;
	}

	public void prepare() throws Exception {
		if(hasId("productList.id")){
			this.productList=productListManager.loadProductList(getId("productList.id"));
		}
		if(hasId("contractManagement.id")){
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
		}
		if ((null == this.contractInvoiceses) && (hasIds("invoicesIds"))){
			this.contractInvoiceses = invoicesManager.loadAllInvoices(getIds("invoicesIds"));
		}
	}

	protected String getAdapterName() {
		return "invoicesHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	public String execute() throws Exception {
		if (isDelete()) {
			String flag="flag";
			Cookie cookie = new Cookie("invoiceFlag", flag);  
			System.out.println(cookie);  
	        cookie.setMaxAge(24 * 60 * 60);  
	        response.addCookie(cookie);  
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			int number=this.productList.getDeliveryedCount();
			for(Invoices invoices:contractInvoiceses){
				number-=invoices.getDeliveryCount();
			}
			productList.setDeliveryedCount(number);
			productListManager.storeProductList(productList);
			this.invoicesManager.deleteAllInvoices(this.contractInvoiceses);
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public ContractManagement getContractManagement() {
		return contractManagement;
	}

	public ProductList getProductList() {
		return productList;
	}

	public void setProductList(ProductList productList) {
		this.productList = productList;
	}

	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}

	public List<Invoices> getContractInvoiceses() {
		return contractInvoiceses;
	}

	public void setContractInvoiceses(List<Invoices> contractInvoiceses) {
		this.contractInvoiceses = contractInvoiceses;
	}


}
