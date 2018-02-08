package com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;

/** 
  * @author 创建人:xcg 
  * @date 创建时间：2017年11月14日 上午10:23:01 
  * 发货单明细model
  */
public class InvoicesList extends BaseInfoEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;//发货单明细编码
	private ProductList productList;//合同编号，合同名称，合同数量,产品名称，产品型号，已发数量，单价
	private double deliveryPrice = Double.valueOf(0);//发货金额
	private long currentSum = Long.valueOf(0);//本次数量，可编辑
	private long restSum = Long.valueOf(0);//未发数量
	private long finishedSum = Long.valueOf(0);//已发数量
	private String mark;//备注，可编辑
	private Products products;
	private Invoices invoices;//所属发货单
	private String isSaved;//是否保存过（编辑）
	
	
	public Invoices getInvoices() {
		return invoices;
	}
	public void setInvoices(Invoices invoices) {
		this.invoices = invoices;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public ProductList getProductList() {
		return productList;
	}
	public void setProductList(ProductList productList) {
		this.productList = productList;
	}
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public long getCurrentSum() {
		return currentSum;
	}
	public void setCurrentSum(long currentSum) {
		this.currentSum = currentSum;
	}
	public long getRestSum() {
		return restSum;
	}
	public void setRestSum(long restSum) {
		this.restSum = restSum;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	public long getFinishedSum() {
		return finishedSum;
	}
	public void setFinishedSum(long finishedSum) {
		this.finishedSum = finishedSum;
	}
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	
	
	
}	
