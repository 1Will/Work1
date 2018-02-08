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
package com.yongjun.tdms.presentation.webwork.action.year.device.runmaintainPlan;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.device.runmaintainPlan.DailyRepair;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PricisionCheck;
import com.yongjun.tdms.model.year.device.runmaintainPlan.RunmaintainPlanDetail;
import com.yongjun.tdms.service.year.device.runmaintainPlan.PricisionCheckManager;
import com.yongjun.tdms.service.year.device.runmaintainPlan.RunmaintainPlanDetailManager;

/**
 * <p>Title: EditPricisionCheckAction
 * <p>Description: 运维计划的精度检查维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditPricisionCheckAction extends PrepareAction {

	private static final long serialVersionUID = 669930111891152027L;
	
	private final RunmaintainPlanDetailManager runmaintainPlanDetailManager;
	private final PricisionCheckManager pricisionCheckManager;
	
	//运维计划明细
	private RunmaintainPlanDetail runmaintainPlanDetail;
	//精度检查
	private PricisionCheck pricisionCheck;

	public EditPricisionCheckAction(RunmaintainPlanDetailManager runmaintainPlanDetailManager,
			PricisionCheckManager pricisionCheckManager) {
		this.runmaintainPlanDetailManager = runmaintainPlanDetailManager;
		this.pricisionCheckManager = pricisionCheckManager;
	}
	
	/**
	 * 为其他方法准备数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("runmaintainPlanDetail.id")) {
			//如果请求中包含"runmaintainPlanDetail.id",则根据"runmaintainPlanDetail.id"获取运维计划明细对象
			this.runmaintainPlanDetail = this.runmaintainPlanDetailManager.loadRunmaintainPlanDetail(this.getId("runmaintainPlanDetail.id"));
		}
		if (null == this.pricisionCheck) {
			if (this.hasId("pricisionCheck.id")) {
				//如果请求中包含"pricisionCheck.id",则根据"pricisionCheck.id"获取精度检查对象
				this.pricisionCheck = this.pricisionCheckManager.loadPricisionCheck(this.getId("pricisionCheck.id"));
			} else {
				//如果请求中不包含"pricisionCheck.id",则创建新的精度检查对象
				this.pricisionCheck = new PricisionCheck();
			}
		}
	}

	/**
	 * 如果点击保存按钮，保存维日常维修
	 * @return
	 */
    public String save() {
    	boolean isNew = this.pricisionCheck.isNew();
        //设置关联的运维计划明细
    	this.pricisionCheck.setRunmaintainPlanDetail(this.runmaintainPlanDetail);
    	this.pricisionCheckManager.storePricisionCheck(this.pricisionCheck);
    	
		if (isNew) {
			this.addActionMessage(this.getText("pricisionCheck.add.success", Arrays
					.asList(new Object[] {  })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("pricisionCheck.edit.success",
							Arrays.asList(new Object[] {  })));
			return SUCCESS;
		} 
    }
    
	public PricisionCheck getPricisionCheck() {
		return pricisionCheck;
	}

	public void setPricisionCheck(PricisionCheck pricisionCheck) {
		this.pricisionCheck = pricisionCheck;
	}

	public RunmaintainPlanDetail getRunmaintainPlanDetail() {
		return runmaintainPlanDetail;
	}

	public void setRunmaintainPlanDetail(RunmaintainPlanDetail runmaintainPlanDetail) {
		this.runmaintainPlanDetail = runmaintainPlanDetail;
	}

}
