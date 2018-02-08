package com.yongjun.tdms.model.base.document;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.pluto.model.tracking.OrganizationTracking;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;

public class ApplicationDoc extends BaseInfoEntity implements
CreatedTimeTracking , LastOperatorTracking, LastModifiedTimeTracking, OrganizationTracking {
	private static final long serialVersionUID = 1L;
	private String fileNo;					//文件编号--（工装变更） |  帮助手册分享中版本号
	private String fileName;				//文件名
	private String name;					//技术资料名称
	private String description;				//描述
	private String position;				//文件在服务器上的位置
	private String manualVersion;            //手册版本号
	private String creator;                  //上传人
	private ApplicationDocType docFlag;     //文档类型标识
	private DeviceCard device;				//设备ID
	private CodeValue category;				
	private ToolingChangeBill changeBill;	//工装变更单ID
	private PurchaseBill purchaseBill;     //采购单的ID
	private Check check;                   //日常检查的id
	private AcceptBill acceptBill;         //验收单的ID
	private Spare spare;					//备件单ID
	private CalibrationDetail calibrationDetail;//工装标定明细
	private PreRepairPlanDetail preRepairPlanDetail;     //预防性维修明细关联的技术资料
	private RepairPlanAndProcDetail yearRepairPlanDetail;  //大项修明细关联的技术资料
	private FaultRepair faultRepair;                       //故障维修关联的技术资料 

	public ApplicationDoc(){
		
	}
	
	public ToolingChangeBill getChangeBill() {
		return changeBill;
	}

	public void setChangeBill(ToolingChangeBill changeBill) {
		this.changeBill = changeBill;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public CodeValue getCategory() {
		return category;
	}

	public void setCategory(CodeValue category) {
		this.category = category;
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}

	@Override
	public boolean equals(Object o) {
			if (o == this) { return true; }
			if (!(o instanceof ApplicationDoc)) { return false; }

		return true;
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public RepairPlanAndProcDetail getYearRepairPlanDetail() {
		return yearRepairPlanDetail;
	}

	public void setYearRepairPlanDetail(RepairPlanAndProcDetail yearRepairPlanDetail) {
		this.yearRepairPlanDetail = yearRepairPlanDetail;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

	public AcceptBill getAcceptBill() {
		return acceptBill;
	}

	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}

	public ApplicationDocType getDocFlag() {
		return docFlag;
	}

	public void setDocFlag(ApplicationDocType docFlag) {
		this.docFlag = docFlag;
	}

	public String getManualVersion() {
		return manualVersion;
	}

	public void setManualVersion(String manualVersion) {
		this.manualVersion = manualVersion;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public CalibrationDetail getCalibrationDetail() {
		return calibrationDetail;
	}

	public void setCalibrationDetail(CalibrationDetail calibrationDetail) {
		this.calibrationDetail = calibrationDetail;
	}

}
