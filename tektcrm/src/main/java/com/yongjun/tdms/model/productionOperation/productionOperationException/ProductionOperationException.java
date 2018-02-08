package com.yongjun.tdms.model.productionOperation.productionOperationException;

import java.util.Date;

import com.yongjun.pluto.model.BaseInfoEntity;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
@SuppressWarnings("serial")
public class ProductionOperationException extends BaseInfoEntity{
	private String code;//异常编码
	private String name;//异常名称
	private ProductionOperationDetail productionOperationDetail;//产品计划
	private ProjectInfoPlan projectInfoPlan;//所属计划任务
	private PersonnelFiles findPersion;//异常提出人
	private Date findDate;//异常 提出时间
	private String describe;//异常描述
	private PersonnelFiles solvePersion;//异常解决人
	private Date solveDate;//异常 解决时间
	private String reason;//异常原因
	private String solution;//解决方案
	private String isSaved;
	private CodeValue statu;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ProductionOperationDetail getProductionOperationDetail() {
		return productionOperationDetail;
	}
	public void setProductionOperationDetail(ProductionOperationDetail productionOperationDetail) {
		this.productionOperationDetail = productionOperationDetail;
	}
	public ProjectInfoPlan getProjectInfoPlan() {
		return projectInfoPlan;
	}
	public void setProjectInfoPlan(ProjectInfoPlan projectInfoPlan) {
		this.projectInfoPlan = projectInfoPlan;
	}
	public PersonnelFiles getFindPersion() {
		return findPersion;
	}
	public void setFindPersion(PersonnelFiles findPersion) {
		this.findPersion = findPersion;
	}
	public Date getFindDate() {
		return findDate;
	}
	public void setFindDate(Date findDate) {
		this.findDate = findDate;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public PersonnelFiles getSolvePersion() {
		return solvePersion;
	}
	public void setSolvePersion(PersonnelFiles solvePersion) {
		this.solvePersion = solvePersion;
	}
	public Date getSolveDate() {
		return solveDate;
	}
	public void setSolveDate(Date solveDate) {
		this.solveDate = solveDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public CodeValue getStatu() {
		return statu;
	}
	public void setStatu(CodeValue statu) {
		this.statu = statu;
	}
	
	
	
}
