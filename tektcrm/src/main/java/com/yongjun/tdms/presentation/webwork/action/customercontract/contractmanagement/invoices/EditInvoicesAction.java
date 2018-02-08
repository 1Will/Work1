package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.invoices;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class EditInvoicesAction extends PrepareAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final InvoicesManager invoicesManager;
	private final ContractManagementManager contractManagementManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final ProductListManager productListManager;
	private ContractManagement contractManagement;
	private Invoices invoices;
	private ProductList productList;
	private int dNumber=0;
	
	public EditInvoicesAction(InvoicesManager invoicesManager,ContractManagementManager contractManagementManager,PersonnelFilesManager personnelFilesManager,ProductListManager productListManager){
		this.invoicesManager = invoicesManager;
		this.contractManagementManager = contractManagementManager;
		this.personnelFilesManager = personnelFilesManager;
		this.productListManager=productListManager;
	}


	public void prepare() throws Exception {
		
		if(hasId("invoices.id")){
			this.invoices = invoicesManager.loadInvoices(getId("invoices.id"));
			dNumber=invoices.getDeliveryCount();
		}else {
			this.invoices = new Invoices();
		}
		if(hasId("contractManagement.id")){
			this.contractManagement = contractManagementManager.loadContractManagement(getId("contractManagement.id"));
			this.invoices.setContractManagement(this.contractManagement);
		}
		if(hasId("productList.id")){
			this.productList=productListManager.loadProductList(getId("productList.id"));
			this.invoices.setProductList(productList);
		}
	}
	
	
	public String save() {
		boolean isNew = invoices.isNew();
		try {
			if(hasId("deliveryPerson.id")){
				this.invoices.setDeliveryPerson(personnelFilesManager.loadPersonnel(getId("deliveryPerson.id")));
			}
			if(request.getParameter("invoices.deliveryCount").length()>0){
				System.out.println(Integer.parseInt(request.getParameter("invoices.deliveryCount")));
				int num=productList.getDeliveryedCount()+Integer.parseInt(request.getParameter("invoices.deliveryCount"))-dNumber;
				productList.setDeliveryedCount(num);
				productListManager.storeProductList(this.productList);
			}
			this.invoicesManager.storeInvoices(this.invoices);
			if (isNew) {
				
				addActionMessage(getText("invoices.add.success"));
				return SUCCESS;
			}else {
				addActionMessage(getText("invoices.update.success"));
				return SUCCESS;
			}
		} catch (Exception e) {
			if (isNew) {
				addActionMessage(getText("invoices.add.error"));
				return ERROR;
			}else {
				addActionMessage(getText("invoices.update.error"));
				return ERROR;
			}
		}
	}


	public ContractManagement getContractManagement() {
		return contractManagement;
	}


	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}


	public Invoices getInvoices() {
		return invoices;
	}


	public void setInvoices(Invoices invoices) {
		this.invoices = invoices;
	}


	public ProductList getProductList() {
		return productList;
	}


	public void setProductList(ProductList productList) {
		this.productList = productList;
	}


}
