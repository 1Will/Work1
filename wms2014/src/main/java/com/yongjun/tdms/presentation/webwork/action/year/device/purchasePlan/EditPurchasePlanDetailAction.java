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
package com.yongjun.tdms.presentation.webwork.action.year.device.purchasePlan;

import java.util.Arrays;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanDetailManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

/**
 * 
 * <p>Title: EditPurchasePlanDetailAction
 * <p>Description: 设备年度采购计划明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditPurchasePlanDetailAction extends PrepareAction {
	private static final long serialVersionUID = 234284588215626970L;
	
	private final PurchasePlanDetailManager purchasePlanDetailManager;
	private final PurchasePlanManager purchasePlanManager;
	
	//设备年度采购计划
	private PurchasePlan purchasePlan;
	//设备年度采购计划明细
	private PurchasePlanDetail purchasePlanDetail;
	
	public EditPurchasePlanDetailAction(PurchasePlanDetailManager purchasePlanDetailManager,
			PurchasePlanManager purchasePlanManager) {
		this.purchasePlanDetailManager = purchasePlanDetailManager;
		this.purchasePlanManager = purchasePlanManager;
	}

	/**
	 * 为其他方法准备数据
	 */
	public void prepare() throws Exception {
		if (this.hasId("purchasePlan.id")) {      //如果请求中包含"purchasePlan.id",则根据"purchasePlan.id"获取设备年度采购计划对象
			this.purchasePlan = this.purchasePlanManager.loadPurchasePlan(this.getId("purchasePlan.id"));
		}
		if (null == this.purchasePlanDetail) {
			if (this.hasId("purchasePlanDetail.id")) {    
				//如果请求中有"purchasePlanDetail.id",则根据"purchasePlanDetail.id"获取设备年度采购计划明细
				this.purchasePlanDetail = this.purchasePlanDetailManager.loadPurchasePlanDetail(this.getId("purchasePlanDetail.id"));
			} else {
				//如果请求中没有"purchasePlanDetail.id",则创建设备年度采购计划明细
				this.purchasePlanDetail = new PurchasePlanDetail();
			}
		}
	}

	/**
	 * 如果点击保存按钮，保存维修保养明细
	 * @return
	 */
    public String save() {
    	boolean isNew = this.purchasePlanDetail.isNew();
    	
    	//设置设备年度采购计划
    	this.purchasePlanDetail.setPurchasePlan(this.purchasePlan);
    	this.purchasePlanDetailManager.storePurchasePlanDetail(this.purchasePlanDetail);
    	
		if (isNew) {
			this.addActionMessage(this.getText("purchasePlanDetail.add.success", Arrays
					.asList(new Object[] { purchasePlanDetail.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("purchasePlanDetail.edit.success", Arrays
					.asList(new Object[] {purchasePlanDetail.getName() })));
			return SUCCESS;
		}
    }
    
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public PurchasePlanDetail getPurchasePlanDetail() {
		return purchasePlanDetail;
	}

	public void setPurchasePlanDetail(PurchasePlanDetail purchasePlanDetail) {
		this.purchasePlanDetail = purchasePlanDetail;
	}

}
