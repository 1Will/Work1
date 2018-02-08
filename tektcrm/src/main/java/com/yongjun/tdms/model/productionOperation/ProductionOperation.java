package com.yongjun.tdms.model.productionOperation;

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
public class ProductionOperation extends BaseInfoEntity{
	private String code;//计划编码
	private String name;//名称
	private PersonnelFiles makeUpPerson;//编制人
	private Date makeUpDate;//b编制日期
	private PersonnelFiles auditingPerson;//审核人
	private Date auditingDate;//审核日期
	private String mark;//备注
	private String isSaved;// 存在并且等于0，方可提交
	private String managerType;//production生产经营计划design设计 通知单
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
	public PersonnelFiles getMakeUpPerson() {
		return makeUpPerson;
	}
	public void setMakeUpPerson(PersonnelFiles makeUpPerson) {
		this.makeUpPerson = makeUpPerson;
	}
	public Date getMakeUpDate() {
		return makeUpDate;
	}
	public void setMakeUpDate(Date makeUpDate) {
		this.makeUpDate = makeUpDate;
	}
	public PersonnelFiles getAuditingPerson() {
		return auditingPerson;
	}
	public void setAuditingPerson(PersonnelFiles auditingPerson) {
		this.auditingPerson = auditingPerson;
	}
	public Date getAuditingDate() {
		return auditingDate;
	}
	public void setAuditingDate(Date auditingDate) {
		this.auditingDate = auditingDate;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(String isSaved) {
		this.isSaved = isSaved;
	}
	public ProductionOperation(String code, String name, PersonnelFiles makeUpPerson, Date makeUpDate,
			PersonnelFiles auditingPerson, Date auditingDate, String mark, String isSaved,String managerType) {
		this.code = code;
		this.name = name;
		this.makeUpPerson = makeUpPerson;
		this.makeUpDate = makeUpDate;
		this.auditingPerson = auditingPerson;
		this.auditingDate = auditingDate;
		this.mark = mark;
		this.isSaved = isSaved;
		this.managerType=managerType;
	}
	public ProductionOperation() {
	}
	public String getManagerType() {
		return managerType;
	}
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}
	
	
}
