package com.yongjun.tdms.model.prophase.supplier;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeValue;


public class SupplierEvaluate extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;


   //原等级
   private CodeValue originalLevel;
   //等级
    private CodeValue level;
	
	//评估部门
    private Department department;
    //戎余部门的名称
    private String deptName;
    //评定人
    private User evaluateUser;
    
    //戎余评定人的名称
    private String evaluateName;
    
    //评定日期
    private Date evaluatedate;
    
    //评定年度
    private Date unitYear;
    
    //评定分数
    private Double  evaluateMinute;
    
    //评估所关联的供应商
    private Supplier supplier;
    //供应商名称
    private String supplierName;
    
    //资产标识[设备][工装]
	private SysModel toolingDevFlag = SysModel.DEVICE;
	
	//关联的供应商评估详细信息
	private Set<SupplierEvaluateDetail> supplierEvaluateDetail=new HashSet<SupplierEvaluateDetail>();
	
   @Override
   public int hashCode() {
	
	//return supplierNo.hashCode();
	   return 0;
  }

   @Override
  public boolean equals(Object o) {
	if(o==this){
		return true;
	}
	if (!(o instanceof SupplierEvaluate)) { return false; }

	return true;
  }


public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

public User getEvaluateUser() {
	return evaluateUser;
}

public void setEvaluateUser(User evaluateUser) {
	this.evaluateUser = evaluateUser;
}

public Date getEvaluatedate() {
	return evaluatedate;
}

public void setEvaluatedate(Date evaluatedate) {
	this.evaluatedate = evaluatedate;
}


public SysModel getToolingDevFlag() {
	return toolingDevFlag;
}

public void setToolingDevFlag(SysModel toolingDevFlag) {
	this.toolingDevFlag = toolingDevFlag;
}

public Supplier getSupplier() {
	return supplier;
}

public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}

public Set<SupplierEvaluateDetail> getSupplierEvaluateDetail() {
	return supplierEvaluateDetail;
}

public void setSupplierEvaluateDetail(
		Set<SupplierEvaluateDetail> supplierEvaluateDetail) {
	this.supplierEvaluateDetail = supplierEvaluateDetail;
}

public CodeValue getLevel() {
	return level;
}

public void setLevel(CodeValue level) {
	this.level = level;
}

public CodeValue getOriginalLevel() {
	return originalLevel;
}

public void setOriginalLevel(CodeValue originalLevel) {
	this.originalLevel = originalLevel;
}

public Double getEvaluateMinute() {
	return evaluateMinute;
}

public void setEvaluateMinute(Double evaluateMinute) {
	this.evaluateMinute = evaluateMinute;
}

public Date getUnitYear() {
	return unitYear;
}

public void setUnitYear(Date unitYear) {
	this.unitYear = unitYear;
}

public String getEvaluateName() {
	return evaluateName;
}

public void setEvaluateName(String evaluateName) {
	this.evaluateName = evaluateName;
}

public String getDeptName() {
	return deptName;
}

public void setDeptName(String deptName) {
	this.deptName = deptName;
}

public String getSupplierName() {
	return supplierName;
}

public void setSupplierName(String supplierName) {
	this.supplierName = supplierName;
}



}
