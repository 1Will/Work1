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
package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;

/**
 * <p>Title: EditQuarterTechAlterDetailAction
 * <p>Description: 季度计划技术改造明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditQuarterTechAlterDetailAction extends PrepareAction {
	private static final long serialVersionUID = 6792578472675286734L;
	
	private final QuarterPlanDetailManager techAlterDetailManager;
	private final DeviceCardManager deviceCardManager;
	private final QuarterPlanManager quarterPlanManager;
	
	//技术改造
	private QuarterPlanDetail techAlterDetail;
	//工装
	private DeviceCard tooling;
	//季度计划
	private QuarterPlan quarterPlan;

	public EditQuarterTechAlterDetailAction(QuarterPlanDetailManager techAlterDetailManager,
			DeviceCardManager deviceCardManager,
			QuarterPlanManager quarterPlanManager) {
		this.techAlterDetailManager = techAlterDetailManager;
		this.deviceCardManager = deviceCardManager;
		this.quarterPlanManager = quarterPlanManager;
	}
	/**
	 * 为其他方法准备数据
	 */
	public void prepare() throws Exception {
		if (null == techAlterDetail) {
			if (this.hasId("techAlterDetail.id")) {
		 	   //如果请求中有"repairMaintenanceDetail.id",则根据"repairMaintenanceDetail.id"获取维修保养明细
				this.techAlterDetail = this.techAlterDetailManager.loadQuarterPlanDetail(this.getId("techAlterDetail.id"));
			    if (!this.hasId("tooling.id")) {
			    	this.tooling = this.techAlterDetail.getTooling();
			    }
			} else {   //如果请求中没有"repairMaintenanceDetail.id",则创建维修保养明细
				this.techAlterDetail = new QuarterPlanDetail();
			}
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
	}
	/**
	 * 如果点击保存按钮，保存维修保养明细
	 * @return
	 */
    public String save() {
    	boolean isNew = this.techAlterDetail.isNew();
    	
    	if (this.hasId("tooling.id")) {               //设置工装
    		this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
    		this.techAlterDetail.setTooling(tooling);
    		//拷贝工装图号
    		this.techAlterDetail.setGraphNo(tooling.getDeviceNo());
    		//拷贝工装名称
    		this.techAlterDetail.setName(tooling.getName());
    		//关联上单位
    		if(tooling.getCalUnit()!=null){
    			techAlterDetail.setCalUnit(tooling.getCalUnit());
    		}
    		//拷贝工装类别
    		if (null != tooling.getToolingType()) {
    			this.techAlterDetail.setCategoryName(tooling.getToolingType().getValue());
    		}
    		if(null != tooling.getToolingTypeDetail()){
    			techAlterDetail.setDetailCategoryName(tooling.getToolingTypeDetail().getName());
    		}
    		//拷贝工装型号
    		this.techAlterDetail.setModel(tooling.getModel());
    		//拷贝工装规格
    		this.techAlterDetail.setSpecification(tooling.getSpecification());
    		
    	}
		techAlterDetail.setQuarterPlan(this.quarterPlan);  //设置技术改造明细关联的季度计划
		//设置明细类别[技术改造]
		this.techAlterDetail.setDetailType(YearPlanDetailCategory.TECH_ALTER);
		this.techAlterDetail.setUnitPrice(null);          //设置默认单价为空
		this.techAlterDetail.setNumber(null);             //设置
    	this.techAlterDetailManager.storeQuarterPlanDetail(this.techAlterDetail);
    	
   		if (isNew) {
			this.addActionMessage(this.getText("techAlterDetail.add.success", Arrays
					.asList(new Object[] {  })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("techAlterDetail.edit.success", Arrays
					.asList(new Object[] { })));
			return SUCCESS;
		}
    }
    
	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}
	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	public QuarterPlanDetail getTechAlterDetail() {
		return techAlterDetail;
	}
	public void setTechAlterDetail(QuarterPlanDetail techAlterDetail) {
		this.techAlterDetail = techAlterDetail;
	}
}
