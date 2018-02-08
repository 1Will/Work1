
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

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * 
 * <p>Title: EditQuarterRepairMaintenanceDetailAction
 * <p>Description: 季度计划维修保养明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class EditPurchaseRepairMaintenanceDetailAction extends PrepareAction {
	private static final long serialVersionUID = -9100656006606721651L;
	
	private final DeviceCardManager deviceCardManager;
	private final SubscribeManager subscribeManager;
	//工装
	private DeviceCard tooling;
	//采购单
	private Subscribe subscribe;
	//采购单明细(维修保养明细)
	private SubscribeDetail subscribeDtl;
	

	public EditPurchaseRepairMaintenanceDetailAction(
			DeviceCardManager deviceCardManager,
			SubscribeManager subscribeManager) {
		this.deviceCardManager = deviceCardManager;
		this.subscribeManager = subscribeManager;
	}

	public void prepare() throws Exception {
		if (null == subscribeDtl) {
			if (this.hasId("subscribeDtl.id")) {
				subscribeDtl = subscribeManager.loadSubscribeDetail(this.getId("subscribeDtl.id"));
				if(!this.hasId("tooling.id")){
					tooling = subscribeDtl.getTooling();
				}
			} else {
				subscribeDtl = new SubscribeDetail();
			}
		}
		if (this.hasId("subscribe.id")) {  
			subscribe = subscribeManager.loadSubscribe(this.getId("subscribe.id"));
		}
	}
	
	/**
	 * 如果点击保存按钮，保存维修保养明细
	 * @return
	 */
    public String save() {
    	boolean isNew = subscribeDtl.isNew();
    	if (this.hasId("tooling.id")){		//设置工装
    		this.tooling = deviceCardManager.loadDevice(this.getId("tooling.id"));
    		//设置工装id
    		this.subscribeDtl.setTooling(tooling);
    		//设置工装图号
    		this.subscribeDtl.setGraphNo(tooling.getDeviceNo());
    		//设置工装名称
    		this.subscribeDtl.setName(tooling.getName());
    		//关联工装单位
    		if(tooling.getCalUnit()!=null){
    			subscribeDtl.setCalUnit(tooling.getCalUnit());
    		}
    		//设置工装类别
    		if (null != tooling.getToolingType()) {
    			this.subscribeDtl.setCategoryName(tooling.getToolingType().getValue());
    			this.subscribeDtl.setToolingCategory(tooling.getToolingType());
    		}
    		//关联工装明细类别
    		if(null != tooling.getToolingTypeDetail()){
    			subscribeDtl.setDetailCategoryName(tooling.getToolingTypeDetail().getName());
    		}
    		//设置工装型号
    		this.subscribeDtl.setModel(tooling.getModel());
    		//设置部门
    		this.subscribeDtl.setDepartment(tooling.getDepartment().getName());
    		//设置工装规格
    		this.subscribeDtl.setSpecification(tooling.getSpecification());
    	}
    	//设置关联的采购单
    	this.subscribeDtl.setSubscribe(subscribe);
    	//设置明细类别
    	this.subscribeDtl.setDetailType(YearPlanDetailCategory.REPAIR_MAINTENANCE);
    	
    	this.subscribeManager.storeSubscribeDetail(subscribeDtl);
    	
		if (isNew) {
			this.addActionMessage(this.getText("repairMaintenanceDetail.add.success", Arrays
					.asList(new Object[] { subscribeDtl.getTooling().getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("repairMaintenanceDetail.edit.success", Arrays
					.asList(new Object[] {subscribeDtl.getTooling().getName() })));
			return SUCCESS;
		}
    }
	
	public DeviceCard getTooling() {
		return tooling;
	}
	
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	public SubscribeDetail getSubscribeDtl() {
		return subscribeDtl;
	}
	public void setSubscribeDtl(SubscribeDetail subscribeDtl) {
		this.subscribeDtl = subscribeDtl;
	}
}
