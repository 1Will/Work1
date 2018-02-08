package com.yongjun.tdms.model.prophase.business;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;

public class AcceptBillDetail  extends BaseInfoEntity{

	private static final long serialVersionUID = 1L;
	//验收项目
    private String acceptProject;
    //验收情况
    private String acceptSituation;
    //处理意见
    private String handleOpinion;
    //备注
    private String memo;
    //验收单明细所关联的验收单
    private AcceptBill acceptBill;
    /*----新增加的验收单明细的字段------------------*/
     //图号
	private String graphNo;
	//品名
	private String name;
	//型号
	private String model;
	//规格
	private String specification;
	//数量
	private Integer amount=0;
	//单价
	private Double unitPrice=0.0;
	//总价
	private Double totalPrice=0.0;
    //验收单所关联的采购合同明细
    private PurchaseBillDetail purchaseContractDtl;
   //采购合同明细的类别[工装制作|备件采购|维修保养|技术改造]
	private YearPlanDetailCategory detailType; 
	//备件分类所冗余的字段
	private String categoryName;
	//备件明细分类所冗余的字段
	private String detailCategoryName;
	//所关联的工装
	private DeviceCard tooling;
	//关联的备件类别
	private SpareType category;
	//备件明细类
	private SpareDetailType detailCategory;
    //工装类别
	private ToolingType toolingCategory;
	//验收结果 默认为合格
	private AcceptResult result=AcceptResult.QUALIFIED;
	private Spare spare;
    //计量单位[工装]
 	private CodeValue calUnit;
 	//是否创建台帐 默认为未创建
 	private AcceptEstablishAccount establish=AcceptEstablishAccount.UNESTABLISH;
 	//采购部门
   private Department department;
   private String departmentName;
   //标识验收单明细是否执行了保存方法  默认为false
   private boolean judageSave=false;
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

	public String getAcceptProject() {
		return acceptProject;
	}

	public void setAcceptProject(String acceptProject) {
		this.acceptProject = acceptProject;
	}

	public String getAcceptSituation() {
		return acceptSituation;
	}

	public void setAcceptSituation(String acceptSituation) {
		this.acceptSituation = acceptSituation;
	}

	public String getHandleOpinion() {
		return handleOpinion;
	}

	public void setHandleOpinion(String handleOpinion) {
		this.handleOpinion = handleOpinion;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public AcceptBill getAcceptBill() {
		return acceptBill;
	}

	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}

	public SpareType getCategory() {
		return category;
	}

	public void setCategory(SpareType category) {
		this.category = category;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public SpareDetailType getDetailCategory() {
		return detailCategory;
	}

	public void setDetailCategory(SpareDetailType detailCategory) {
		this.detailCategory = detailCategory;
	}

	public String getDetailCategoryName() {
		return detailCategoryName;
	}

	public void setDetailCategoryName(String detailCategoryName) {
		this.detailCategoryName = detailCategoryName;
	}

	public YearPlanDetailCategory getDetailType() {
		return detailType;
	}

	public void setDetailType(YearPlanDetailCategory detailType) {
		this.detailType = detailType;
	}

	public PurchaseBillDetail getPurchaseContractDtl() {
		return purchaseContractDtl;
	}

	public void setPurchaseContractDtl(PurchaseBillDetail purchaseContractDtl) {
		this.purchaseContractDtl = purchaseContractDtl;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public ToolingType getToolingCategory() {
		return toolingCategory;
	}

	public void setToolingCategory(ToolingType toolingCategory) {
		this.toolingCategory = toolingCategory;
	}

	public AcceptResult getResult() {
		return result;
	}

	public void setResult(AcceptResult result) {
		this.result = result;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public CodeValue getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(CodeValue calUnit) {
		this.calUnit = calUnit;
	}

	public AcceptEstablishAccount getEstablish() {
		return establish;
	}

	public void setEstablish(AcceptEstablishAccount establish) {
		this.establish = establish;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public boolean isJudageSave() {
		return judageSave;
	}

	public void setJudageSave(boolean judageSave) {
		this.judageSave = judageSave;
	}



}
