package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.Arrays;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

public class EditRepairDocAction extends FileTransportAction {
	private static final long serialVersionUID = 6596867316363148801L;
	private final ApplicationDocManager applicationDocManager;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final FileTransportManager fileTransportManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;
	
	private ApplicationDoc repairDoc;
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private FaultRepair faultRepair;
	private String planProcFlag;                         //标识为计划，还是实施
	private String preYearFlag="";						 //标识为预防性维修，还是大项修
	public EditRepairDocAction(ApplicationDocManager applicationDocManager,
			PreRepairPlanDetailManager preRepairPlanDetailManager,
			FileTransportManager fileTransportManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager){
		this.applicationDocManager = applicationDocManager;
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.fileTransportManager = fileTransportManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.faultRepairManager = faultRepairManager;
	}
	
	public void prepare() throws Exception {
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
		if (null == this.repairDoc) {
			if (this.hasId("doc.id")) {
				this.repairDoc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			} else {
				repairDoc = new ApplicationDoc();
			}
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			delete();
		} 
		return SUCCESS;
	}
	
	public String save() throws Exception {
		boolean isNew = this.repairDoc.isNew();
		
		String location = fileTransportManager.upload(request, getFile(), "origFileName");
		
		String orgFileName = request.getParameter("origFileName");
		repairDoc.setFileName(orgFileName);          //设置文件原始的名字
		repairDoc.setPosition(location);             //设置文件在服务器上的名字，GUUID
		repairDoc.setFileNo(location);               //设置默认的文件编号
		if(this.preYearFlag.equals("YEAR")){	
			repairDoc.setYearRepairPlanDetail(repairPlanOrProcDetail);
		} else if(this.preYearFlag.equals("PRE")){
			repairDoc.setPreRepairPlanDetail(preRepairPlanDetail);
		} else {
			repairDoc.setFaultRepair(faultRepair);
		}
		
		
		this.applicationDocManager.storeApplicationDoc(repairDoc);
		
		if (isNew) {
			this.addActionMessage(this.getText("repairDoc.add.success", Arrays
					.asList(new Object[] { repairDoc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("repairDoc.edit.success", Arrays
					.asList(new Object[] { repairDoc.getName() })));
			return SUCCESS;
		}
	}
	
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("repairDoc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("repairDoc.id"));
		}
		doc.setName(repairDoc.getName());
		doc.setDescription(repairDoc.getDescription());
		this.applicationDocManager.storeApplicationDoc(doc);
		
		this.addActionMessage(this.getText("repairDoc.edit.success", Arrays
				.asList(new Object[] { repairDoc.getName() })));
		
		return SUCCESS;
	}
	
//	TODO: 删除文件
	public void delete() {
		this.applicationDocManager.deleteApplicationDoc(this.repairDoc);
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public ApplicationDoc getRepairDoc() {
		return repairDoc;
	}

	public void setRepairDoc(ApplicationDoc repairDoc) {
		this.repairDoc = repairDoc;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
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
	
	/**
	 * 用来判断是更新还是上传新的文件
	 * @return
	 */
	public String getFirst() {
		return request.getParameter("first");
	}
}	
