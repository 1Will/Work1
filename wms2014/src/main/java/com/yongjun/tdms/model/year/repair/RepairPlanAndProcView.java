package com.yongjun.tdms.model.year.repair;

import java.util.Date;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.BaseInfoEntity;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

public class RepairPlanAndProcView extends BaseInfoEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1849603888943145997L;
	//年份
	private String year;
	//部门
	private String departName;
	//工作令号
	private String workNo;
	//设备编号
	private String deviceNo;
	//设备名称
	private String deviceName;
	//规格型号
	private String model;
    //复杂系数的机械标识    true表示选中机械
	private boolean machineFlag = false; 
	//复杂系数的电器标识  true表示选中电器
	private boolean electricalFlag = false;
	//修理类别
	private CodeValue category;
	//使用单位
	private String useDept;
	//承修单位
	private String repairDept;
	
	//计划修理费用（元）
	private Double planDetailAllFee;
	//实际修理费用（元）
	private Double procDetailAllFee;
	//计划维修时间（月）
	private Date planRepairDate;
	//实际维修时间（月）
	private Date procRepairDate;
	//计划动工时间
	private Date planBeginDate;
	//实际动工时间
	private Date procBeginDate;
	//计划完工时间
	private Date planEndDate;
	//实际完工时间
	private Date procEndDate;
	//执行人
	private User procExecPeople;
	//执行结果
	private String procResult;
	//备注
	private String content;
	
	private Long yearRepairPlanAndProcId;
	
	public Long getYearRepairPlanAndProcId() {
		return yearRepairPlanAndProcId;
	}
	public void setYearRepairPlanAndProcId(Long yearRepairPlanAndProcId) {
		this.yearRepairPlanAndProcId = yearRepairPlanAndProcId;
	}
	public CodeValue getCategory() {
		return category;
	}
	public void setCategory(CodeValue category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDepartName() {
		return departName;
	}
	public void setDepartName(String departName) {
		this.departName = departName;
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
	public boolean isElectricalFlag() {
		return electricalFlag;
	}
	public void setElectricalFlag(boolean electricalFlag) {
		this.electricalFlag = electricalFlag;
	}
	public boolean isMachineFlag() {
		return machineFlag;
	}
	public void setMachineFlag(boolean machineFlag) {
		this.machineFlag = machineFlag;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Date getPlanBeginDate() {
		return planBeginDate;
	}
	public void setPlanBeginDate(Date planBeginDate) {
		this.planBeginDate = planBeginDate;
	}
	public Double getPlanDetailAllFee() {
		return planDetailAllFee;
	}
	public void setPlanDetailAllFee(Double planDetailAllFee) {
		this.planDetailAllFee = planDetailAllFee;
	}
	public Date getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}
	public Date getPlanRepairDate() {
		return planRepairDate;
	}
	public void setPlanRepairDate(Date planRepairDate) {
		this.planRepairDate = planRepairDate;
	}
	public Date getProcBeginDate() {
		return procBeginDate;
	}
	public void setProcBeginDate(Date procBeginDate) {
		this.procBeginDate = procBeginDate;
	}
	public Double getProcDetailAllFee() {
		return procDetailAllFee;
	}
	public void setProcDetailAllFee(Double procDetailAllFee) {
		this.procDetailAllFee = procDetailAllFee;
	}
	public Date getProcEndDate() {
		return procEndDate;
	}
	public void setProcEndDate(Date procEndDate) {
		this.procEndDate = procEndDate;
	}
	public User getProcExecPeople() {
		return procExecPeople;
	}
	public void setProcExecPeople(User procExecPeople) {
		this.procExecPeople = procExecPeople;
	}
	public Date getProcRepairDate() {
		return procRepairDate;
	}
	public void setProcRepairDate(Date procRepairDate) {
		this.procRepairDate = procRepairDate;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}

	public String getRepairDept() {
		return repairDept;
	}
	public void setRepairDept(String repairDept) {
		this.repairDept = repairDept;
	}
	public String getUseDept() {
		return useDept;
	}
	public void setUseDept(String useDept) {
		this.useDept = useDept;
	}
	public String getWorkNo() {
		return workNo;
	}
	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
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
	public String getExecResult() {
		if (this.procResult.equals("UNFINISHED")) {
			return "未完成";
		} else if (this.procResult.equals("FINISHED")) {
			return "已完成";
		} else if (this.procResult.equals("CANCEL")) {
			return "取消";
		} else {
			return "转移";
		}
	}
	
}
