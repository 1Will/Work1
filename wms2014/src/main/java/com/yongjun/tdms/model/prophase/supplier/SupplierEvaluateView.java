package com.yongjun.tdms.model.prophase.supplier;

import java.util.Date;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;

public class SupplierEvaluateView  extends BaseInfoEntity implements CreatorTracking,
CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 //评定年度
    private Date unitYear;
    //戎余部门的名称
    private String deptName;
    
    private String evaluateName;
    //评定分数
    private Double  evaluateMinute;
    //供应商名称
    private String supplierName;
    //评定日期
    private Date evaluatedate;
    //评分
    private Double  grade;
    //备注
    private String coment;
    //供应商评估的id
    private Long supplierEvaluate;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getComent() {
		return coment;
	}

	public void setComent(String coment) {
		this.coment = coment;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Date getEvaluatedate() {
		return evaluatedate;
	}

	public void setEvaluatedate(Date evaluatedate) {
		this.evaluatedate = evaluatedate;
	}

	public Double getEvaluateMinute() {
		return evaluateMinute;
	}

	public void setEvaluateMinute(Double evaluateMinute) {
		this.evaluateMinute = evaluateMinute;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public Long getSupplierEvaluate() {
		return supplierEvaluate;
	}

	public void setSupplierEvaluate(Long supplierEvaluate) {
		this.supplierEvaluate = supplierEvaluate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

	

}
