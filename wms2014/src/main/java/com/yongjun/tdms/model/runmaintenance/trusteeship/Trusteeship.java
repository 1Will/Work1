package com.yongjun.tdms.model.runmaintenance.trusteeship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.supplier.Supplier;


public class Trusteeship extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = 1L;

	// 托管编号
	private String billNo;

	// 托管名称
	private String billName;

	// 托管日期
	private Date trusteeshipRequestDate;

	//现外协厂
	private Supplier supplier;
	
	// 托管人
	private String trusteeshipUser;

	// 原所属部门
	private Department trusteeshipDepartment;
	
	//设备托管申请部门
    private Department trusteeshipRequestDepartment;
    
    //托管原因
    private String reason;
    
	// 托管相关的资产[工装]|[设备]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	
    //关联的托管详细
	private Set<TrusteeshipDetail> trusteeshipDetail = new HashSet<TrusteeshipDetail>();
	
	@Override
	public int hashCode() {

		return billNo.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Trusteeship)) {
			return false;
		} else {
			Trusteeship trusteeship = (Trusteeship) o;
			if (trusteeship.getBillNo().equals(this.getBillNo())) {
				return true;
			}
		}

		return false;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public Date getTrusteeshipRequestDate() {
		return trusteeshipRequestDate;
	}

	public void setTrusteeshipRequestDate(Date trusteeshipRequestDate) {
		this.trusteeshipRequestDate = trusteeshipRequestDate;
	}


	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public Set<TrusteeshipDetail> getTrusteeshipDetail() {
		return trusteeshipDetail;
	}

	public void setTrusteeshipDetail(Set<TrusteeshipDetail> trusteeshipDetail) {
		this.trusteeshipDetail = trusteeshipDetail;
	}

	public Department getTrusteeshipDepartment() {
		return trusteeshipDepartment;
	}

	public void setTrusteeshipDepartment(Department trusteeshipDepartment) {
		this.trusteeshipDepartment = trusteeshipDepartment;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Department getTrusteeshipRequestDepartment() {
		return trusteeshipRequestDepartment;
	}

	public void setTrusteeshipRequestDepartment(
			Department trusteeshipRequestDepartment) {
		this.trusteeshipRequestDepartment = trusteeshipRequestDepartment;
	}



	public String getTrusteeshipUser() {
		return trusteeshipUser;
	}

	public void setTrusteeshipUser(String trusteeshipUser) {
		this.trusteeshipUser = trusteeshipUser;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
