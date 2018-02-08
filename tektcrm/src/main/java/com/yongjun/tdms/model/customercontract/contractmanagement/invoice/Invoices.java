package com.yongjun.tdms.model.customercontract.contractmanagement.invoice;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.workReport.nextWeekPlan.pojo.DefaultNextWeekPlanManager;
/**
 * 发货单
 * @author xcg
 *
 */
@SuppressWarnings("serial")
public class Invoices extends BaseInfoEntity{
	private String deliveryNum;//出库单号,发货单编码
	private Date deliveryDate;//发货日期
	private PersonnelFiles deliveryPerson;//发货人，负责人
	/*private String invoiceNum;//发票号*/	
	private ContractManagement contractManagement;//合同
	private ProductList productList;//待发产品
	private Integer deliveryCount = 0;//发货数量
	
	private CustomerInfo customerInfo;//客户
	private String contacter;//联系人
	private String telephone;//联系电话
	
	private CodeValue deliveryWay;//发货方式
	private String delivreyAddress;//发货地址
	private CodeValue deliveryStatus;//发货状态
	private double deliveryPrice;//发货金额
	private Date receiptDate;//回单日期
	private Department department;//部门
	private String contactWay;//联系方式
	private String mark;//备注
	private String isSaved;// 存在并且等于0，方可提交
	
	
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public String getContacter() {
		return contacter;
	}
	public void setContacter(String contacter) {
		this.contacter = contacter;
	}
	public CustomerInfo getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	
	public Date getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}
	public CodeValue getDeliveryWay() {
		return deliveryWay;
	}
	public void setDeliveryWay(CodeValue deliveryWay) {
		this.deliveryWay = deliveryWay;
	}
	public String getDelivreyAddress() {
		return delivreyAddress;
	}
	public void setDelivreyAddress(String delivreyAddress) {
		this.delivreyAddress = delivreyAddress;
	}
	public CodeValue getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(CodeValue deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public double getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(double deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public Integer getDeliveryCount() {
		return deliveryCount;
	}
	public void setDeliveryCount(Integer deliveryCount) {
		this.deliveryCount = deliveryCount;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public PersonnelFiles getDeliveryPerson() {
		return deliveryPerson;
	}
	public void setDeliveryPerson(PersonnelFiles deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}
	/*public String getInvoiceNum() {
		return invoiceNum;
	}
	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}*/
	public ContractManagement getContractManagement() {
		return contractManagement;
	}
	public void setContractManagement(ContractManagement contractManagement) {
		this.contractManagement = contractManagement;
	}
	public ProductList getProductList() {
		return productList;
	}
	public void setProductList(ProductList productList) {
		this.productList = productList;
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
	
	
}
