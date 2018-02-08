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
package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
/**
 * <p>Title:EditRepairMaintenancePurchaseConcatAction
 * <p>Description:采购合同维修保养明细维护页面控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author smzhang@yj-technology.com
 * @version $Id: EditRepairMaintenancePurchaseConcatAction.java 2008-12-7 16:22:02 smzhang$
 */
public class EditRepairMaintenancePurchaseConcatAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
	private PurchaseBill purchaseBill;
	private PurchaseBillDetail purchaseBillDetail;
	//	工装
	private DeviceCard tooling;
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private final ToolingTypeManager toolingTypeManager;
	private final DeviceCardManager deviceCardManager;
	
	public EditRepairMaintenancePurchaseConcatAction(PurchaseBillManager purchaseBillManager,
			PurchaseBillDetailManager purchaseBillDetailManager,ToolingTypeManager toolingTypeManager,
			DeviceCardManager deviceCardManager){
		this.purchaseBillManager = purchaseBillManager;
		this.purchaseBillDetailManager = purchaseBillDetailManager;
		this.toolingTypeManager = toolingTypeManager;
		this.deviceCardManager = deviceCardManager;
	}
	public void prepare() throws Exception {
//		工装制作明细所关联的采购合同
		if(this.hasId("purchaseBill.id")){
			this.purchaseBill = purchaseBillManager.loadPurchaseBill(this.getId("purchaseBill.id"));
		}
		if (null == purchaseBillDetail) {
		if(this.hasId("toolingMakeDetail.id")){
			this.purchaseBillDetail = purchaseBillDetailManager.loadPurchaseBillDetail(this.getId("toolingMakeDetail.id"));
			if(!this.hasId("tooling.id")){
				tooling = purchaseBillDetail.getTooling();
			}
		}else{
			purchaseBillDetail = new PurchaseBillDetail();
		}
		}
	}
	public String save(){
		boolean isNew = this.purchaseBillDetail.isNew();
		
    	if (this.hasId("tooling.id")){		//设置工装
    		this.tooling = deviceCardManager.loadDevice(this.getId("tooling.id"));
    		//设置工装id
    		this.purchaseBillDetail.setTooling(tooling);
    		//设置工装图号
    		this.purchaseBillDetail.setGraphNo(tooling.getDeviceNo());
    		//设置工装名称
    		this.purchaseBillDetail.setName(tooling.getName());
    		//设置工装单位
    		if(tooling.getCalUnit()!=null){
    			purchaseBillDetail.setCalUnit(tooling.getCalUnit());
    		}
    		//设置工装类别
    		if (null != tooling.getToolingType()) {
    			this.purchaseBillDetail.setCategoryName(tooling.getToolingType().getValue());
    			this.purchaseBillDetail.setToolingCategory(tooling.getToolingType());
    		}
    		//关联工装明细类别
    		if(null != tooling.getToolingTypeDetail()){
    			purchaseBillDetail.setDetailCategoryName(tooling.getToolingTypeDetail().getName());
    		}
    		//设置工装型号
    		this.purchaseBillDetail.setModel(tooling.getModel());
    		//设置工装规格
    		this.purchaseBillDetail.setSpecification(tooling.getSpecification());
    	}
    	//设置关联的采购单
    	this.purchaseBillDetail.setPurchaseBill(purchaseBill);
    	//设置明细类别
    	this.purchaseBillDetail.setDetailType(YearPlanDetailCategory.REPAIR_MAINTENANCE);
		
		this.purchaseBillDetailManager.storePurchaseBillDetail(purchaseBillDetail);
		if(isNew){
			this.addActionMessage(this.getText("repairMaintenanceDetail.add.success", Arrays
					.asList(new Object[] { purchaseBillDetail.getName() })));
			return NEW;
		}else{
			this.addActionMessage(this.getText("repairMaintenanceDetail.edit.success", Arrays
					.asList(new Object[] { purchaseBillDetail.getName() })));
			return SUCCESS;
		}
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
	public List getCategorys() {
		return this.toolingTypeManager.loadAllToolingTypes();
	}
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	public PurchaseBillDetail getPurchaseBillDetail() {
		return purchaseBillDetail;
	}
	public void setPurchaseBillDetail(PurchaseBillDetail purchaseBillDetail) {
		this.purchaseBillDetail = purchaseBillDetail;
	}
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

}
