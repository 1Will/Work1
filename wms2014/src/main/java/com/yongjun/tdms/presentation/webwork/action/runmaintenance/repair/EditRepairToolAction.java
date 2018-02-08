package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.runmaintenance.repair.RepairTool;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.runmaintenance.repair.RepairToolManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

public class EditRepairToolAction extends PrepareAction {
	private static final long serialVersionUID = 7906735715886235905L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final RepairToolManager repairToolManager;
	private final CodeValueManager codeValueManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private RepairTool repairTool;
	private FaultRepair faultRepair;
	private String planProcFlag;                    //标识为是计划,还是实施
	private String preYearFlag="";						 //标识为预防性维修，还是大项修
	
	public EditRepairToolAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			RepairToolManager repairToolManager,
			CodeValueManager codeValueManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager) {
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.repairToolManager = repairToolManager;
		this.codeValueManager = codeValueManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.faultRepairManager = faultRepairManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.repairTool) {
			if (this.hasId("repairTool.id")) {
				this.repairTool = this.repairToolManager.loadRepairTool(this.getId("repairTool.id"));
			} else {
				this.repairTool = new RepairTool();
			}
		}
		if(this.hasId("preYearFlag")) {
			this.preYearFlag = request.getParameter("preYearFlag");
			if(this.preYearFlag.equals("YEAR")){               //大项修
				if (this.repairPlanOrProcDetail == null) {
					if (this.hasId("repairPlanOrProcDetail.id")) {
						this.repairPlanOrProcDetail = this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this
								.getId("repairPlanOrProcDetail.id"));

					}
				}
			} else if (this.preYearFlag.equals("PRE")){           //预防性维修
				if (this.preRepairPlanDetail == null) {
					if (this.hasId("preRepairPlanDetail.id")) {
						this.preRepairPlanDetail = this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this
								.getId("preRepairPlanDetail.id"));
					}
				}
			} else {                                           //故障维修对象
				if (this.hasId("faultRepair.id")) {
					this.faultRepair = this.faultRepairManager.loadFaultRepair(this
							.getId("faultRepair.id"));
				}
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.repairTool.isNew();
		
		if (this.preYearFlag.equals("YEAR")){	//设置大项修对象
			repairTool.setRepairPlanAndProcDetail(repairPlanOrProcDetail);
		} else if (this.preYearFlag.equals("PRE")){     //设置预防性对象
			repairTool.setPreRepairDetail(preRepairPlanDetail);
		} else {                                  //设置故障维修对象
			repairTool.setFaultRepair(faultRepair);
		}
		this.repairToolManager.storeRepairTool(repairTool);
		
		 if (isNew) {
			 this.addActionMessage(this.getText("repairTool.add.success",
						Arrays.asList(new Object[] { repairTool.getName() })));
			 return NEW;
		 }  else {
				this.addActionMessage(this.getText("repairTool.edit.success",
						Arrays.asList(new Object[] { repairTool.getName() })));
				return SUCCESS;
		}
	}
	
	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}
	
	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}
	
	public RepairTool getRepairTool() {
		return repairTool;
	}
	
	public void setRepairTool(RepairTool repairTool) {
		this.repairTool = repairTool;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}
	
	public List getCalcUnit() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}

	public String getPreYearFlag() {
		return preYearFlag;
	}

	public void setPreYearFlag(String preYearFlag) {
		this.preYearFlag = preYearFlag;
	}

	public RepairPlanAndProcDetail getRepairPlanOrProcDetail() {
		return repairPlanOrProcDetail;
	}

	public void setRepairPlanOrProcDetail(
			RepairPlanAndProcDetail repairPlanOrProcDetail) {
		this.repairPlanOrProcDetail = repairPlanOrProcDetail;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

}
