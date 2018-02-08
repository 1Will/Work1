package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.accept;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;

public class EditAcceptRepairMaintenanceDetailAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
    private final AcceptBillManager acceptBillManager;
    private final AcceptBillDetailManager acceptBillDetailManager;
    private final DeviceCardManager deviceCardManager;
    private AcceptBill acceptBill;
    private AcceptBillDetail acceptBillDetail;
    private DeviceCard tooling;
    public EditAcceptRepairMaintenanceDetailAction(AcceptBillManager acceptBillManager,AcceptBillDetailManager acceptBillDetailManager
    		,DeviceCardManager deviceCardManager){
    	this.acceptBillManager=acceptBillManager;
    	this.acceptBillDetailManager=acceptBillDetailManager;
    	this.deviceCardManager=deviceCardManager;
    }
	public void prepare() throws Exception {
	  if(this.hasId("acceptBill.id")){
		  acceptBill=acceptBillManager.loadAcceptBill(this.getId("acceptBill.id"));
	  }
		if (null == acceptBillDetail) {
			if (this.hasId("acceptBillDetail.id")) {
				acceptBillDetail = acceptBillDetailManager.loadAcceptBillDetail(this.getId("acceptBillDetail.id"));
				if(!this.hasId("tooling.id")){
					tooling = acceptBillDetail.getTooling();
				}
			} else {
				acceptBillDetail = new AcceptBillDetail();
			}
		}
		
	}
	/**
	 * 如果点击保存按钮，保存维修保养明细
	 * @return
	 */
    public String save() {
    	boolean isNew = acceptBillDetail.isNew();
    	if (this.hasId("tooling.id")){		//设置工装
    		this.tooling = deviceCardManager.loadDevice(this.getId("tooling.id"));
    		//设置工装id
    		this.acceptBillDetail.setTooling(tooling);
    		//设置工装图号
    		this.acceptBillDetail.setGraphNo(tooling.getDeviceNo());
    		//设置工装名称
    		this.acceptBillDetail.setName(tooling.getName());
    		//设置工装单位
    		if(tooling.getCalUnit()!=null){
    			acceptBillDetail.setCalUnit(tooling.getCalUnit());
    		}
    		//设置工装类别
    		if (null != tooling.getToolingType()) {
    			this.acceptBillDetail.setCategoryName(tooling.getToolingType().getValue());
    			this.acceptBillDetail.setToolingCategory(tooling.getToolingType());
    		}
    		//关联工装明细类别
    		if(null != tooling.getToolingTypeDetail()){
    			acceptBillDetail.setDetailCategoryName(tooling.getToolingTypeDetail().getName());
    		}
    		//设置工装型号
    		this.acceptBillDetail.setModel(tooling.getModel());
    		//设置部门
    	//	this.acceptBillDetail.setDepartment(tooling.getDepartment().getName());
    		//设置工装规格
    		this.acceptBillDetail.setSpecification(tooling.getSpecification());
    	}
    	//设置关联的采购单
    	this.acceptBillDetail.setAcceptBill(acceptBill);
    	//设置明细类别
    	this.acceptBillDetail.setDetailType(YearPlanDetailCategory.REPAIR_MAINTENANCE);
    	
    	this.acceptBillDetailManager.storeToolingAcceptBillToolingMakeDetail(acceptBillDetail);
		if (isNew) {
			this.addActionMessage(this.getText("repairMaintenanceDetail.add.success", Arrays
					.asList(new Object[] { acceptBillDetail.getTooling().getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("repairMaintenanceDetail.edit.success", Arrays
					.asList(new Object[] {acceptBillDetail.getTooling().getName() })));
			return SUCCESS;
		}
    }
	
	public String execute() {
		return SUCCESS;
	}
	public AcceptBill getAcceptBill() {
		return acceptBill;
	}
	public void setAcceptBill(AcceptBill acceptBill) {
		this.acceptBill = acceptBill;
	}
	public AcceptBillDetail getAcceptBillDetail() {
		return acceptBillDetail;
	}
	public void setAcceptBillDetail(AcceptBillDetail acceptBillDetail) {
		this.acceptBillDetail = acceptBillDetail;
	}
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
}
