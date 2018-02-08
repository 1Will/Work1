package com.yongjun.tdms.model.prophase.business;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
@SuppressWarnings("serial")
public class AcceptBill extends BaseInfoEntity{
   
	//验收单编号
	private String acceptBillNo;
	//验收单名称
	private String acceptBilName;
	//验收单部门
	private  Department department; 
	//验收人
	private User acceptPeople;
	//验收日期
	private Date acceptDate=new  Date(); 
	//验收地点
	private String acceptPlace;
	//品名
	private String merchandiseName;
	//规格
	private String specification;
	//型号
	private String model;
	//标识[工装][设备]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	//验收单所关联采购单明细对象
	private PurchaseBillDetail purchaseBillDtl;
	//验收单所关联采购单对象
	private PurchaseBill purchaseBill;
	//验收单所关的上传文件
	private Set<ApplicationDoc> changeDoc = new HashSet<ApplicationDoc>();
	//数量
	private Integer amounts; 
	//验收单所关联的供应商
	private Supplier supplier;
	// 验收单所关联的验收单明细
	private Set<AcceptBillDetail> acceptBillDtl=new HashSet<AcceptBillDetail>();
	//验收单所关联的备品备件
	private Set<SparePart> spare=new HashSet<SparePart>();
	//供应商代表
	private String supplierRepresentative;
	//备注
	private String memo;
	
	private Boolean submit = false;					//是否‘提交’标识
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getAcceptBillNo() {
		return acceptBillNo;
	}

	public void setAcceptBillNo(String acceptBillNo) {
		this.acceptBillNo = acceptBillNo;
	}

	public String getAcceptBilName() {
		return acceptBilName;
	}

	public void setAcceptBilName(String acceptBilName) {
		this.acceptBilName = acceptBilName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getAcceptPeople() {
		return acceptPeople;
	}

	public void setAcceptPeople(User acceptPeople) {
		this.acceptPeople = acceptPeople;
	}

	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public String getAcceptPlace() {
		return acceptPlace;
	}

	public void setAcceptPlace(String acceptPlace) {
		this.acceptPlace = acceptPlace;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}



	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public String getSupplierRepresentative() {
		return supplierRepresentative;
	}

	public void setSupplierRepresentative(String supplierRepresentative) {
		this.supplierRepresentative = supplierRepresentative;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public PurchaseBillDetail getPurchaseBillDtl() {
		return purchaseBillDtl;
	}

	public void setPurchaseBillDtl(PurchaseBillDetail purchaseBillDtl) {
		this.purchaseBillDtl = purchaseBillDtl;
	}

	public Integer getAmounts() {
		return amounts;
	}

	public void setAmounts(Integer amounts) {
		this.amounts = amounts;
	}

	public Set<AcceptBillDetail> getAcceptBillDtl() {
		return acceptBillDtl;
	}

	public void setAcceptBillDtl(Set<AcceptBillDetail> acceptBillDtl) {
		this.acceptBillDtl = acceptBillDtl;
	}

	public Set<SparePart> getSpare() {
		return spare;
	}

	public void setSpare(Set<SparePart> spare) {
		this.spare = spare;
	}

	public Set<ApplicationDoc> getChangeDoc() {
		return changeDoc;
	}

	public void setChangeDoc(Set<ApplicationDoc> changeDoc) {
		this.changeDoc = changeDoc;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

	public Boolean getSubmit() {
		return submit;
	}

	public void setSubmit(Boolean submit) {
		this.submit = submit;
	}

	
	

}
