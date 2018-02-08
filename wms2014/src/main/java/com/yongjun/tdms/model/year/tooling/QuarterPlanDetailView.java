package com.yongjun.tdms.model.year.tooling;

import java.util.Date;

import com.yongjun.tdms.model.BaseInfoEntity;

public class QuarterPlanDetailView extends BaseInfoEntity{

	private static final long serialVersionUID = 1L;
    //详细类别
	private String detailType;
	//季度类别
	private String qarterType;
	//季度计划编号
	private String planNo;
	//季度计划名称
	private String planName;
	//年度
	private String year;
	//部门名称
	private String deptName;
	//计划总费用
	private Double planAllFee;
	//图号
	private String graphNo;
	//品名
	private String name;
	//规格
	private String specification;
	//型号
	private String model;
	//单价
	private Double unitPrice;
	//数量
	private Integer number;
	//总价
	private Double allPrice;
	//需求日期
	private Date planFinishedDate;
	//需求原因
	private String requestReason;
	//备注
	private String comment;
	//种类名称
	private String categoryName;
	//详细种类名称
	private String detailCategoryName;
	//关联的年度计划id
	private Long quarterPlan;
	//数量单位
	private String calUnit;
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object arg0) {
		return false;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Double getPlanAllFee() {
		return planAllFee;
	}

	public void setPlanAllFee(Double planAllFee) {
		this.planAllFee = planAllFee;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public Date getPlanFinishedDate() {
		return planFinishedDate;
	}

	public void setPlanFinishedDate(Date planFinishedDate) {
		this.planFinishedDate = planFinishedDate;
	}

	public String getRequestReason() {
		return requestReason;
	}

	public void setRequestReason(String requestReason) {
		this.requestReason = requestReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public Long getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(Long quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

	public String getQarterType() {
		return qarterType;
	}

	public void setQarterType(String qarterType) {
		this.qarterType = qarterType;
	}
	
	public String getQuarterTypeName() {
		if (this.qarterType.equals("FIRST_QUARTER")) {
			return "第一季度";
		} else if (this.qarterType.equals("SECOND_QUARTER")) {
			return "第二季度";
		} else if (this.qarterType.equals("THIRD_QUARTER")) {
			return "第三季度";
		} else {
			return "第四季度";
		}
	}
	public String getYearPlanDetailType() {
		if (this.detailType.equals("TOOLING_MAKE")) {
			return "工装制作";
		} else if (this.detailType.equals("SPARE_PURCHASE")){
			return "备件采购";
		} else if (this.detailType.equals("REPAIR_MAINTENANCE")) {
			return "维修保养";
		} else {
			return "技术改造";
		}
	}

	public String getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(String calUnit) {
		this.calUnit = calUnit;
	}

}
