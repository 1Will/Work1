package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.fault.FaultRepair;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;
import com.yongjun.tdms.model.year.repair.RepairPlanAndProcDetail;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultRepairManager;
import com.yongjun.tdms.service.runmaintenance.repair.PreRepairPlanDetailManager;
import com.yongjun.tdms.service.year.repair.RepairPlanAndProcDetailManager;

public class ListRepairDocAction extends ValueListAction {
	private static final long serialVersionUID = 4124607245811378792L;
	private final PreRepairPlanDetailManager preRepairPlanDetailManager;
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final RepairPlanAndProcDetailManager repairPlanAndProcDetailManager;
	private final FaultRepairManager faultRepairManager;
	
	private PreRepairPlanDetail preRepairPlanDetail;
	private RepairPlanAndProcDetail repairPlanOrProcDetail;
	private List<ApplicationDoc> repairDocs;
	private ApplicationDoc doc;
	private FaultRepair faultRepair;
	private String planProcFlag;                    //标识为是计划,还是实施
	private String preYearFlag="PRE";						 //标识为预防性维修，还是大项修
	
	public ListRepairDocAction(PreRepairPlanDetailManager preRepairPlanDetailManager,
			ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager,
			RepairPlanAndProcDetailManager repairPlanAndProcDetailManager,
			FaultRepairManager faultRepairManager){
		this.preRepairPlanDetailManager = preRepairPlanDetailManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
		this.repairPlanAndProcDetailManager = repairPlanAndProcDetailManager;
		this.faultRepairManager = faultRepairManager;
	}

	public void prepare() throws Exception {
		
		if(this.hasId("preYearFlag")) {
			this.preYearFlag = request.getParameter("preYearFlag");
			if(this.preYearFlag.equals("YEAR")){						//获取大项修对象
				if(this.hasId("repairPlanOrProcDetail.id")) {
					this.repairPlanOrProcDetail =this.repairPlanAndProcDetailManager.loadRepairPlanAndProcDetail(this.getId("repairPlanOrProcDetail.id"));
				}
			} else if (this.preYearFlag.equals("PRE")){                //获取预防性维修对象
				if(this.hasId("preRepairPlanDetail.id")) {
					this.preRepairPlanDetail =this.preRepairPlanDetailManager.loadPreRepairPlanDetail(this.getId("preRepairPlanDetail.id"));
				}
			} else {                                                   //获取故障维修对象
				if (this.hasId("faultRepair.id")) {
					this.faultRepair = this.faultRepairManager.loadFaultRepair(this.getId("faultRepair.id"));
				}
			}
		}
		if (this.hasId("repairDocIds")) {
			this.repairDocs = this.applicationDocManager.loadAllApplicationDocs(this.getIds("repairDocIds"));
		}
		if (this.hasId("doc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			this.download();
		}
		if (this.hasId("planProcFalg")) {
			this.planProcFlag = request.getParameter("planProcFlag");
			System.out.println("planProcFlag=="+planProcFlag);
		}
		this.setFirst(false);

	}
	
	public String execute() {
		//如果点击删除按钮，则执行删除操作
		if (isDelete()) {
			this.delete();
		}
		return SUCCESS;
	}
	
	private void delete() {
		for (Iterator iter = repairDocs.iterator(); iter.hasNext(); ) {
    		ApplicationDoc doc = (ApplicationDoc)iter.next(); 
    		this.fileTransportManager.delete(request, doc.getPosition());
    	}
		if(this.preYearFlag.equals("YEAR")){
			this.repairPlanOrProcDetail.getRepairDoc().removeAll(this.repairDocs);
			this.repairPlanAndProcDetailManager.storeRepairPlanDetail(this.repairPlanOrProcDetail);
		} else if (this.preYearFlag.equals("PRE")) {
			this.preRepairPlanDetail.getRepairDoc().removeAll(this.repairDocs);
			try {
				this.preRepairPlanDetailManager.storePreRepairPlanDetail(preRepairPlanDetail);
			} catch (Exception e) {
				
			}
		} else {
			this.faultRepair.getRepairDoc().removeAll(this.repairDocs);
			this.faultRepairManager.storeFaultRepair(faultRepair);
		}
		this.addActionMessage(this.getText("repairDocs.delete.success"));
	}
	
	public String download(){
		fileTransportManager.download(request, response, doc.getFileName(), doc.getPosition());
		return null;
	}

	public PreRepairPlanDetail getPreRepairPlanDetail() {
		return preRepairPlanDetail;
	}

	public void setPreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.preRepairPlanDetail = preRepairPlanDetail;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

	@Override
	protected String getAdapterName() {
		if(this.preYearFlag.equals("YEAR")){
			return "yearRepairDocs";
		} else if(this.preYearFlag.equals("PRE")) {
			return "preRepairDocs";
		} else {
			return "faultRepairDocs";
		}
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(this.preYearFlag.equals("YEAR")){
			if(this.hasId("repairPlanOrProcDetail.id")) {
				map.put("yearRepairPlanOrProcDetail.id",this.getId("repairPlanOrProcDetail.id"));
			}
		} else if(this.preYearFlag.equals("PRE")){
			if(this.hasId("preRepairPlanDetail.id")) {
				map.put("preRepairPlanDetail.id",this.getId("preRepairPlanDetail.id"));
			}
		} else {
			if(this.hasId("faultRepair.id")) {
				map.put("faultRepair.id",this.getId("faultRepair.id"));
			}
		}
		return map;
	}

	public RepairPlanAndProcDetail getRepairPlanOrProcDetail() {
		return repairPlanOrProcDetail;
	}

	public void setRepairPlanOrProcDetail(
			RepairPlanAndProcDetail repairPlanOrProcDetail) {
		this.repairPlanOrProcDetail = repairPlanOrProcDetail;
	}

	public ApplicationDoc getDoc() {
		return doc;
	}

	public void setDoc(ApplicationDoc doc) {
		this.doc = doc;
	}

	public String getPreYearFlag() {
		return preYearFlag;
	}

	public void setPreYearFlag(String preYearFlag) {
		this.preYearFlag = preYearFlag;
	}

	public List<ApplicationDoc> getRepairDocs() {
		return repairDocs;
	}

	public void setRepairDocs(List<ApplicationDoc> repairDocs) {
		this.repairDocs = repairDocs;
	}

	public FaultRepair getFaultRepair() {
		return faultRepair;
	}

	public void setFaultRepair(FaultRepair faultRepair) {
		this.faultRepair = faultRepair;
	}

}
