/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.model.asset.device;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.base.codevalue.ToolingTypeDetail;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointRule;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceRule;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairRule;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;

/**
 * @author qs
 * @version $Id: DeviceCard.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public class DeviceCard extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = -4470230728898893758L;
	// 标示[设备编号][工装图号]
	private String deviceNo;
	// 资产编号
	private String assetNo;
	// 产品图号
	private String graphNo;
	// 名称[设备]|[工装]
	private String name;
	// 型号
	private String model;
	
	// 规格
	private String specification;
	
	// 安装地点
	private String installPlace;
	
	// 建卡日期
	private Date cardCreatedTime;
	
	// 购买日期
	private Date boughtDate;
	
	// 【设备】制造厂
	private String factory;
	
	// 制造者[工装]
	private String maker;
	
	// 使用定额
	private Double usedQuota;
	
	// 工装启用日期
	private Date usedStartedTime; 
	
	// 制造日期[工装]
	private Date madeTime;
	
	// 设计日期[工装]
	private Date designTime; 
	
	// 完工日期[工装]
	private Date completeTime; 
	
	// 检验日期[工装]
	private Date checkTime; 
	
	// 验收日期[工装]
	private Date acceptanceTime; 
	
	// 累计产量
	private Long totalOutput; 
	
	// 检验员意见[工装]
	private String suggestion;	
	
	// 放架编号[工装]
	private String putNo;
	
	// 工装所在工序号[工装]
	private String processNo;
	
	//模具参数[工装]
	private String mouldParam;
	
	//模具重量[工装]
	private Double mouldWeight;
	
	// 报废日期[工装]
	private Date disabledTime;
	
	// 工装所使用设备[工装]
	private String usingDevice; 
	
	// 工装最后领用人
	private String borrower;
	
	// 工装或设备标识
	private SysModel toolingDevFlag;
	
	// 工作流提交备注
	private String submitComment;
	
	// 备注
	private String comment;
	
	// 上次标定时间（工装）
	private Date preDemarcateTime;
	
	// 标定周期 （工装）
	private Short demarcateCycle;
	
	// 质保期 （工装，以月份为单位）
	private Short qualityEnsure;
	
	// 工装状态
	private CodeValue toolingStatus;
	
	// 工装类型
	private ToolingType toolingType;
	
	// 工装类型详细
	private ToolingTypeDetail toolingTypeDetail;
	// 
	private String toolingTypeDetailString;
	
	// 使用单位
	private Department department;
	
	// 检验员[工装]
	private User checker; 
	// 检验员[工装]冗于字段
	private String checkerString;
	
	// [设备]负责人，[工装]负责人
	private User manager; 
	
	//[工装]设计人
	private User toolingDesigner;
	
	//新增工装设计人冗余字段
	private String stringToolingDesigner;
	
	// [工装]设计人冗于字段
	private String toolingDesignerString;
	
	// 产品
	private Product product;
	// 产品的冗于字段
	private String productString;
	
	// 供应商[设备][工装]
	private Supplier supplier;
	
	// 托管供应商[设备][工装]
	private Supplier trusteeshipSupplier;
	
	//  托管供应商名称
	private String trusteeshipSupplierName;
	
	// 设备类型
	private DeviceType deviceType;
	
	// 所在生产线
	private ProductionLine productionLine;
	
	// 属性(目前未发现使用)
 	private CodeValue property;
 	
 	// 特别属性
 	private CodeValue specificationProp;
 	// 字符串型：特别属性
 	private String specificationPropString;
 	
 	// 设备状态
 	private CodeValue deviceStatus;
 	
 	//计量单位[工装]
 	private CodeValue calUnit;
 	//计量单位[工装]冗于字段
 	private String calUnitString;
 	
 	//分公司(编码中的值)
// 	private CodeValue filiale;
 	
 	//分公司(分公司管理)
 	private Filiale filiale;
 	// 字符串型：分公司
 	private String filialeString;
 	
 	//数量[工装] ,设备帐面数量
 	private Long number; 
 	
 	// 管理级别
 	private String managementLevel;
 	
 	// 设备状态变动日期
 	private Date stateAlterTime;
 	
 	// 设备文档集合
 	private Set<ApplicationDoc> deviceDoc = new HashSet<ApplicationDoc>();
 	
 	// 设备附属工具集合
	private Set<AttachTool> attachTool = new HashSet<AttachTool>();
	
	// 设备附加信息
	//private Set<DeviceExtInfo> extInfo = new HashSet<DeviceExtInfo>();
	
	// 设备附属设备集合
	private Set<AccessoryDevice> accessoryDevices = new HashSet<AccessoryDevice>();
	
	// 设备备品备件集合
	private Set<DeviceSpare> spares = new HashSet<DeviceSpare>();
	
	// 设备参数集合
	private Set<DeviceArgs> args = new HashSet<DeviceArgs>();
	
	// 财务信息
	private Set<DeviceFinanceInfo> financeInfo = new HashSet<DeviceFinanceInfo>();
	
	// 领用记录集合
	private Set<ToolingBorrowBill> toolingBorrowBill = new HashSet<ToolingBorrowBill>();
	
	// 事故集合
	private Set<FaultBill> toolingFaultBill = new HashSet<FaultBill>();
	
	// 润滑标准集合
	private Set<LubricationRule> lubricationRules = new HashSet<LubricationRule>();
	
	//预防性维修标准明细集合
	private Set<PreRepairRule> preRepairRules = new HashSet<PreRepairRule>();
	
	//点检准明细集合
	private Set<CheckPointRule> checkPointRules = new HashSet<CheckPointRule>();
	
	//保养准明细集合
	private Set<MaintenanceRule> maintenanceRules = new HashSet<MaintenanceRule>();
	
	// 操作证集合
	private Set<OperateCert> operateCerts = new HashSet<OperateCert>();
	
	// 默认为有效.      false为失效
	private boolean enabled=true;   
	
	// 报表标识（是否符合生成点检，保养报表）
	private boolean report=false;
	//TODO 工装台帐所关联的验收单,目前可用可不用，因为验收单创建台帐功能变更之后，所关联的是验收单明细
    private AcceptBill acceptBill;
	//工装台帐所关联的验收单明细
    private AcceptBillDetail acceptBillDetail;
//--------------------------------------------------------------------原设备附加信息	
	private boolean full;					 // 完好
	private boolean emphasis;				 //	 重点设备
	private boolean standard;				 //是否标准
	private CodeValue useCategory;           //生产用途 
	private String useCategoryString;		 //字符串型：生产用途
	//private CodeValue excellentBigSparse;	 //精大稀
	private boolean accuracy;				 //精
	private boolean large;					 //大
	private boolean rare;					 //稀
	private String madeIn;                     //制造国别
	private String material;                   //材质
	private String productNo;                 //出厂编号
	private Date   madeDate;                  //出厂时间
//	private String graphNo;                  //图号
	private Date  installedDate;             //安装时间
	private String zone;                     //地理区域名称                 
	private Double weight;                   //重量
	private Double power;                    //功率
	private String size;                     //外形尺寸
	private String statusDescription;         //完好状态描述 
	private Double systemDaisTime ;				//	制度台时(时)
// 	private Long number; 						//	帐面数量
	private Date lastModifiedMgrStatusDate;   //
//	private String comment;                   //备注
	private String opCondition;               //操作条件
	private Integer equippedCapacitor;        //装机容量
	private Integer oilCapacity;        	 //油箱容量
	private DeviceCard device;                //关联的设备
	private Date inFactoryTime;              //进厂时间
//	private Integer machineHour;             //制度台时
	private String centerCode;				//工作中心代码
	private String machMaintenancePeople; 	//机械保养人
	private String elecMaintenancePeople; 	//电器保养人
	private Double machCoefficient;			//机械复杂系数
	private Double elecCoefficient;			//电器复杂系数
	private Integer elecMotorNum;			//附属电机数
	private Integer bigRepairDayNum;		//大修周期
	private Integer oneMaintenanceTime;		//一保台时
	private Integer twoMaintenanceTime;		//二保台时
	private Date lastOneMaintenanceTime;	//上次一保日期
	private Date lastTwoMaintenanceTime;	//上次二保日期
	private Integer oneMaintenanceHour;		//一保累积台时
	private Integer twoMaintenanceHour;		//二保累积台时
	private String identifiers;				//班次标识
	
	private Integer keepNo;					//当keepNo=1、2、3、4时进行一保，当keepNo=5时进行二保
	
	public DeviceCard() {
		
	}
	
	public Set<OperateCert> getOperateCerts() {
		return operateCerts;
	}

	public void setOperateCerts(Set<OperateCert> operateCerts) {
		this.operateCerts = operateCerts;
	}
	
	public String getBorrower() {
		return borrower;
	}

	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}

	public String getUsingDevice() {
		return usingDevice;
	}

	public void setUsingDevice(String usingDevice) {
		this.usingDevice = usingDevice;
	}
//
//	public void enabled() {
//		this.setEnabled(true);
//	}
	
	public void disabled() {
		this.setEnabled(false);
	}
	
	public String getSubmitComment() {
		return submitComment;
	}
	
	public void setSubmitComment(String submitComment) {
		this.submitComment = submitComment;
	}
	
	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public Date getCardCreatedTime() {
		return cardCreatedTime;
	}

	public void setCardCreatedTime(Date cardCreatedTime) {
		this.cardCreatedTime = cardCreatedTime;
	}
	
	public String getDeviceNo() {
		return deviceNo;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public Date getBoughtDate() {
		return this.boughtDate;
	}
	
	public void setBoughtDate(Date boughtDate) {
		this.boughtDate = boughtDate;
	}
	
	public String getInstallPlace() {
		return installPlace;
	}

	public void setInstallPlace(String installPlace) {
		this.installPlace = installPlace;
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

	public Department getDepartment() {
		return this.department;
	}
	
	public void setDepartment(Department department) {
		this.department = department;
	}

	public CodeValue getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(CodeValue deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	public CodeValue getProperty() {
		return property;
	}

	public void setProperty(CodeValue property) {
		this.property = property;
	}

	public CodeValue getSpecificationProp() {
		return specificationProp;
	}

	public void setSpecificationProp(CodeValue specificationProp) {
		this.specificationProp = specificationProp;
	}
	
	public Set<ApplicationDoc> getDeviceDoc() {
		return deviceDoc;
	}

	public void setDeviceDoc(Set<ApplicationDoc> deviceDoc) {
		this.deviceDoc = deviceDoc;
	}

	public Set<AttachTool> getAttachTool() {
		return attachTool;
	}

	public void setAttachTool(Set<AttachTool> attachTool) {
		this.attachTool = attachTool;
	}

	/**public Set<DeviceExtInfo> getExtInfo() {
		return this.extInfo;
	}
	
	public DeviceExtInfo getDeviceExtInfo() {
		if (!this.extInfo.isEmpty()) {
			return (DeviceExtInfo)this.extInfo.toArray()[0];
		}
		return null;
	}
	
	public void setExtInfo(Set<DeviceExtInfo> exitInfo) {
		this.extInfo = exitInfo;
	}
	*/
	
	public void setAccessoryDevices(Set<AccessoryDevice> accessoryDevices) {
		this.accessoryDevices = accessoryDevices;
	}
	
	public Set<AccessoryDevice> getAccessoryDevices() {
		return this.accessoryDevices;
	}
	
	public Set<DeviceSpare> getSpares() {
		return this.spares;
	}
	
	public void setSpares(Set<DeviceSpare> spares) {
		this.spares = spares;
	}
	
	public void setArgs(Set<DeviceArgs> args) {
		this.args = args;
	}
	
	public Set<DeviceArgs> getArgs() {
		return this.args;
	}
	
	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public DeviceType getDeviceType() {
		return this.deviceType;
	}
	
	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public void setProductionLine(ProductionLine line) {
		this.productionLine = line;
	}
	
	public ProductionLine getProductionLine() {
		return this.productionLine;
	}
	
	public void setFinanceInfo(Set<DeviceFinanceInfo> financeInfo) {
		this.financeInfo = financeInfo;
	}
	
	public Set<DeviceFinanceInfo> getFinanceInfo() {
		return this.financeInfo;
	}
	
	public DeviceFinanceInfo getDeviceFinanceInfo() {
		if (!this.financeInfo.isEmpty()) {
			return (DeviceFinanceInfo)this.financeInfo.toArray()[0];
		}
		return null;
	}

	/**public boolean hasExtInfo() {
		return getExtInfo().size()>0;
	}

	public DeviceExtInfo getDeviceExtensionInfo() {
		if (!hasExtInfo()) {
			return null;
		}
		return (DeviceExtInfo)getExtInfo().toArray()[0];
	}
	*/

	public boolean hasFinanceInfo() {
		return getFinanceInfo().size()>0;
	}

	public DeviceFinanceInfo getFinanceInformation() {
		if (!hasFinanceInfo()) {
			return null;
		}
		return (DeviceFinanceInfo)getFinanceInfo().toArray()[0];
	}

	public Date getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}

	public User getChecker() {
		return checker;
	}

	public void setChecker(User checker) {
		this.checker = checker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getDesignTime() {
		return designTime;
	}

	public void setDesignTime(Date designTime) {
		this.designTime = designTime;
	}

	public String getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(String graphNo) {
		this.graphNo = graphNo;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getPutNo() {
		return putNo;
	}

	public void setPutNo(String putNo) {
		this.putNo = putNo;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public SysModel getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(SysModel toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public CodeValue getToolingStatus() {
		return toolingStatus;
	}

	public void setToolingStatus(CodeValue toolingStatus) {
		this.toolingStatus = toolingStatus;
	}

	public ToolingType getToolingType() {
		return toolingType;
	}

	public void setToolingType(ToolingType toolingType) {
		this.toolingType = toolingType;
	}

	public Double getUsedQuota() {
		return usedQuota;
	}

	public void setUsedQuota(Double usedQuota) {
		this.usedQuota = usedQuota;
	}

	public Date getUsedStartedTime() {
		return usedStartedTime;
	}

	public void setUsedStartedTime(Date usedStartedTime) {
		this.usedStartedTime = usedStartedTime;
	}
	
	@Override
	public int hashCode() {
		return this.getDeviceNo().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof DeviceCard)) { return false; }
		
		DeviceCard dc = (DeviceCard)o;
		
		if (!(deviceNo.equals(dc.getDeviceNo()))) { return false; } 
			
		return true;
	}

	public Date getDisabledTime() {
		return disabledTime;
	}

	public void setDisabledTime(Date disabledTime) {
		this.disabledTime = disabledTime;
	}

	public ToolingTypeDetail getToolingTypeDetail() {
		return toolingTypeDetail;
	}

	public void setToolingTypeDetail(ToolingTypeDetail toolingTypeDetail) {
		this.toolingTypeDetail = toolingTypeDetail;
	}

	public String getProcessNo() {
		return processNo;
	}

	public void setProcessNo(String processNo) {
		this.processNo = processNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getMadeTime() {
		return madeTime;
	}

	public void setMadeTime(Date madeTime) {
		this.madeTime = madeTime;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<ToolingBorrowBill> getToolingBorrowBill() {
		return toolingBorrowBill;
	}

	public void setToolingBorrowBill(Set<ToolingBorrowBill> toolingBorrowBill) {
		this.toolingBorrowBill = toolingBorrowBill;
	}

	public Long getTotalOutput() {
		return totalOutput;
	}

	public void setTotalOutput(Long totalOutput) {
		this.totalOutput = totalOutput;
	}

	public Date getPreDemarcateTime() {
		return preDemarcateTime;
	}

	public void setPreDemarcateTime(Date preDemarcateTime) {
		this.preDemarcateTime = preDemarcateTime;
	}

	public Short getDemarcateCycle() {
		return demarcateCycle;
	}

	public void setDemarcateCycle(Short demarcateCycle) {
		this.demarcateCycle = demarcateCycle;
	}

	public Set<FaultBill> getToolingFaultBill() {
		return toolingFaultBill;
	}

	public void setToolingFaultBill(Set<FaultBill> toolingFaultBill) {
		this.toolingFaultBill = toolingFaultBill;
	}

	public String getManagementLevel() {
		return managementLevel;
	}

	public void setManagementLevel(String managementLevel) {
		this.managementLevel = managementLevel;
	}
	
	public void setLubricationRules(Set<LubricationRule> rules) {
		this.lubricationRules = rules;
	}
	
	public Set<LubricationRule> getLubricationRules() {
		return this.lubricationRules;
	}

	public Set<PreRepairRule> getPreRepairRules() {
		return preRepairRules;
	}

	public void setPreRepairRules(Set<PreRepairRule> preRepairRules) {
		this.preRepairRules = preRepairRules;
	}

	public User getToolingDesigner() {
		return toolingDesigner;
	}

	public void setToolingDesigner(User toolingDesigner) {
		this.toolingDesigner = toolingDesigner;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public CodeValue getCalUnit() {
		return calUnit;
	}

	public void setCalUnit(CodeValue calUnit) {
		this.calUnit = calUnit;
	}

	public String getMouldParam() {
		return mouldParam;
	}

	public void setMouldParam(String mouldParam) {
		this.mouldParam = mouldParam;
	}

	public Double getMouldWeight() {
		return mouldWeight;
	}

	public void setMouldWeight(Double mouldWeight) {
		this.mouldWeight = mouldWeight;
	}

	public Set<CheckPointRule> getCheckPointRules() {
		return checkPointRules;
	}

	public void setCheckPointRules(Set<CheckPointRule> checkPointRules) {
		this.checkPointRules = checkPointRules;
	}

	public Date getStateAlterTime() {
		return stateAlterTime;
	}

	public void setStateAlterTime(Date stateAlterTime) {
		this.stateAlterTime = stateAlterTime;
	}

	public Set<MaintenanceRule> getMaintenanceRules() {
		return maintenanceRules;
	}

	public void setMaintenanceRules(Set<MaintenanceRule> maintenanceRules) {
		this.maintenanceRules = maintenanceRules;
	}

	public boolean isEmphasis() {
		return emphasis;
	}

	public void setEmphasis(boolean emphasis) {
		this.emphasis = emphasis;
	}

//	public CodeValue getExcellentBigSparse() {
//		return excellentBigSparse;
//	}
//
//	public void setExcellentBigSparse(CodeValue excellentBigSparse) {
//		this.excellentBigSparse = excellentBigSparse;
//	}

	public boolean isFull() {
		return full;
	}

	public void setFull(boolean full) {
		this.full = full;
	}

	public boolean isStandard() {
		return standard;
	}

	public void setStandard(boolean standard) {
		this.standard = standard;
	}

	public CodeValue getUseCategory() {
		return useCategory;
	}

	public void setUseCategory(CodeValue useCategory) {
		this.useCategory = useCategory;
	}

	public Integer getBigRepairDayNum() {
		return bigRepairDayNum;
	}

	public void setBigRepairDayNum(Integer bigRepairDayNum) {
		this.bigRepairDayNum = bigRepairDayNum;
	}

	public String getCenterCode() {
		return centerCode;
	}

	public void setCenterCode(String centerCode) {
		this.centerCode = centerCode;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Double getElecCoefficient() {
		return elecCoefficient;
	}

	public void setElecCoefficient(Double elecCoefficient) {
		this.elecCoefficient = elecCoefficient;
	}

	public String getElecMaintenancePeople() {
		return elecMaintenancePeople;
	}

	public void setElecMaintenancePeople(String elecMaintenancePeople) {
		this.elecMaintenancePeople = elecMaintenancePeople;
	}

	public Integer getElecMotorNum() {
		return elecMotorNum;
	}

	public void setElecMotorNum(Integer elecMotorNum) {
		this.elecMotorNum = elecMotorNum;
	}

	public Integer getEquippedCapacitor() {
		return equippedCapacitor;
	}

	public void setEquippedCapacitor(Integer equippedCapacitor) {
		this.equippedCapacitor = equippedCapacitor;
	}

	public String getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(String identifiers) {
		this.identifiers = identifiers;
	}

	public Date getInFactoryTime() {
		return inFactoryTime;
	}

	public void setInFactoryTime(Date inFactoryTime) {
		this.inFactoryTime = inFactoryTime;
	}

	public Date getInstalledDate() {
		return installedDate;
	}

	public void setInstalledDate(Date installedDate) {
		this.installedDate = installedDate;
	}

	public Date getLastModifiedMgrStatusDate() {
		return lastModifiedMgrStatusDate;
	}

	public void setLastModifiedMgrStatusDate(Date lastModifiedMgrStatusDate) {
		this.lastModifiedMgrStatusDate = lastModifiedMgrStatusDate;
	}

	public Date getLastOneMaintenanceTime() {
		return lastOneMaintenanceTime;
	}

	public void setLastOneMaintenanceTime(Date lastOneMaintenanceTime) {
		this.lastOneMaintenanceTime = lastOneMaintenanceTime;
	}

	public Date getLastTwoMaintenanceTime() {
		return lastTwoMaintenanceTime;
	}

	public void setLastTwoMaintenanceTime(Date lastTwoMaintenanceTime) {
		this.lastTwoMaintenanceTime = lastTwoMaintenanceTime;
	}

	public Double getMachCoefficient() {
		return machCoefficient;
	}

	public void setMachCoefficient(Double machCoefficient) {
		this.machCoefficient = machCoefficient;
	}


	public String getMachMaintenancePeople() {
		return machMaintenancePeople;
	}

	public void setMachMaintenancePeople(String machMaintenancePeople) {
		this.machMaintenancePeople = machMaintenancePeople;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getOilCapacity() {
		return oilCapacity;
	}

	public void setOilCapacity(Integer oilCapacity) {
		this.oilCapacity = oilCapacity;
	}

	public Integer getOneMaintenanceTime() {
		return oneMaintenanceTime;
	}

	public void setOneMaintenanceTime(Integer oneMaintenanceTime) {
		this.oneMaintenanceTime = oneMaintenanceTime;
	}

	public String getOpCondition() {
		return opCondition;
	}

	public void setOpCondition(String opCondition) {
		this.opCondition = opCondition;
	}

	public Double getPower() {
		return power;
	}

	public void setPower(Double power) {
		this.power = power;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public Double getSystemDaisTime() {
		return systemDaisTime;
	}

	public void setSystemDaisTime(Double systemDaisTime) {
		this.systemDaisTime = systemDaisTime;
	}

	public Integer getTwoMaintenanceTime() {
		return twoMaintenanceTime;
	}

	public void setTwoMaintenanceTime(Integer twoMaintenanceTime) {
		this.twoMaintenanceTime = twoMaintenanceTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Integer getOneMaintenanceHour() {
		return oneMaintenanceHour;
	}

	public void setOneMaintenanceHour(Integer oneMaintenanceHour) {
		this.oneMaintenanceHour = oneMaintenanceHour;
	}

	public Integer getTwoMaintenanceHour() {
		return twoMaintenanceHour;
	}

	public void setTwoMaintenanceHour(Integer twoMaintenanceHour) {
		this.twoMaintenanceHour = twoMaintenanceHour;
	}

	public String getSpecificationPropString() {
		return specificationPropString;
	}

	public void setSpecificationPropString(String specificationPropString) {
		this.specificationPropString = specificationPropString;
	}

	public String getUseCategoryString() {
		return useCategoryString;
	}

	public void setUseCategoryString(String useCategoryString) {
		this.useCategoryString = useCategoryString;
	}

//	public CodeValue getFiliale() {
//		return filiale;
//	}
//
//	public void setFiliale(CodeValue filiale) {
//		this.filiale = filiale;
//	}

	public Filiale getFiliale() {
		return filiale;
	}

	public void setFiliale(Filiale filiale) {
		this.filiale = filiale;
	}
	
	public String getFilialeString() {
		return filialeString;
	}

	public void setFilialeString(String filialeString) {
		this.filialeString = filialeString;
	}

	public String getProductString() {
		return productString;
	}

	public void setProductString(String productString) {
		this.productString = productString;
	}

	public String getToolingTypeDetailString() {
		return toolingTypeDetailString;
	}

	public void setToolingTypeDetailString(String toolingTypeDetailString) {
		this.toolingTypeDetailString = toolingTypeDetailString;
	}

	public String getCheckerString() {
		return checkerString;
	}

	public void setCheckerString(String checkerString) {
		this.checkerString = checkerString;
	}

	public String getToolingDesignerString() {
		return toolingDesignerString;
	}

	public void setToolingDesignerString(String toolingDesignerString) {
		this.toolingDesignerString = toolingDesignerString;
	}

	public String getCalUnitString() {
		return calUnitString;
	}

	public void setCalUnitString(String calUnitString) {
		this.calUnitString = calUnitString;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}

	public boolean isAccuracy() {
		return accuracy;
	}

	public void setAccuracy(boolean accuracy) {
		this.accuracy = accuracy;
	}

	public boolean isLarge() {
		return large;
	}

	public void setLarge(boolean large) {
		this.large = large;
	}

	public boolean isRare() {
		return rare;
	}

	public void setRare(boolean rare) {
		this.rare = rare;
	}

	public Short getQualityEnsure() {
		return qualityEnsure;
	}

	public void setQualityEnsure(Short qualityEnsure) {
		this.qualityEnsure = qualityEnsure;
	}

	public Supplier getTrusteeshipSupplier() {
		return trusteeshipSupplier;
	}

	public void setTrusteeshipSupplier(Supplier trusteeshipSupplier) {
		this.trusteeshipSupplier = trusteeshipSupplier;
	}

	public String getTrusteeshipSupplierName() {
		return trusteeshipSupplierName;
	}

	public void setTrusteeshipSupplierName(String trusteeshipSupplierName) {
		this.trusteeshipSupplierName = trusteeshipSupplierName;
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}

	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}

	public String getStringToolingDesigner() {
		return stringToolingDesigner;
	}

	public void setStringToolingDesigner(String stringToolingDesigner) {
		this.stringToolingDesigner = stringToolingDesigner;
	}

	public AcceptBillDetail getAcceptBillDetail() {
		return acceptBillDetail;
	}

	public void setAcceptBillDetail(AcceptBillDetail acceptBillDetail) {
		this.acceptBillDetail = acceptBillDetail;
	}

	public Integer getKeepNo() {
		return keepNo;
	}

	public void setKeepNo(Integer keepNo) {
		this.keepNo = keepNo;
	}


}
