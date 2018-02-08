package com.yongjun.tdms.model.runmaintenance.wash;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.product.Product;

public class WashPlanView extends BaseInfoEntity{

	/**
	 * 
	 */
	//月份
	private Date month;
	//部门名称
	private String deptName;
	//清洗计划编号
	private String washPlanno;
	//清洗计划名称
	private String washPlanname;
	//工装编号
	private String deviceNo;
	//工装名称
	private String deviceName;
	//工装图号
	private String graphNo;
	//产品型号
	private Product productModel;
	//工序号
	private String processNo;
	//责任人
	private String dutyPeople;
	//监督人
	private String supervisePeople;
	//计划清洗日期
	private Date planwashDate;
	//实际清洗日期
	private Date procWashDate;
    //清洗结果，默认为未完成
	private String result;
	//备注
	private String comment;
	//清洗ID
	private Long washId;
	private static final long serialVersionUID = 1L;

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

	

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getWashPlanname() {
		return washPlanname;
	}

	public void setWashPlanname(String washPlanname) {
		this.washPlanname = washPlanname;
	}

	public String getWashPlanno() {
		return washPlanno;
	}

	public void setWashPlanno(String washPlanno) {
		this.washPlanno = washPlanno;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Product getProductModel() {
		return productModel;
	}

	public void setProductModel(Product productModel) {
		this.productModel = productModel;
	}

	public Date getPlanwashDate() {
		return planwashDate;
	}

	public void setPlanwashDate(Date planwashDate) {
		this.planwashDate = planwashDate;
	}

	public Date getProcWashDate() {
		return procWashDate;
	}

	public void setProcWashDate(Date procWashDate) {
		this.procWashDate = procWashDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getWashId() {
		return washId;
	}

	public void setWashId(Long washId) {
		this.washId = washId;
	}

	public String getDutyPeople() {
		return dutyPeople;
	}

	public void setDutyPeople(String dutyPeople) {
		this.dutyPeople = dutyPeople;
	}

	public String getSupervisePeople() {
		return supervisePeople;
	}

	public void setSupervisePeople(String supervisePeople) {
		this.supervisePeople = supervisePeople;
	}

}

