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

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlan;
import com.yongjun.tdms.model.year.device.purchasePlan.PurchasePlanDetail;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanDetailManager;
import com.yongjun.tdms.service.year.device.purchasePlan.PurchasePlanManager;

/**
 * 
 * <p>Title: ListPurchasePlanDetailAction
 * <p>Description: 设备年度采购明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListPurchasePlanDetailAction extends ValueListAction {
	private static final long serialVersionUID = -2452046034432917152L;
	
	private final PurchasePlanDetailManager purchasePlanDetailManager;
	private final PurchasePlanManager purchasePlanManager;
	
	//设备年度采购计划
	private PurchasePlan purchasePlan;
	//设备年度采购计划明细
	private List<PurchasePlanDetail> purchasePlanDetails;
	
	public ListPurchasePlanDetailAction(PurchasePlanDetailManager purchasePlanDetailManager,
			PurchasePlanManager purchasePlanManager) {
		this.purchasePlanDetailManager = purchasePlanDetailManager;
		this.purchasePlanManager = purchasePlanManager;
	}

	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("purchasePlan.id")) {         //如果请求中包含"purchasePlan.id",则根据"purchasePlan.id"获取设备年度采购计划对象
			this.purchasePlan = this.purchasePlanManager.loadPurchasePlan(this.getId("purchasePlan.id"));
		}
		if (this.hasIds("purchasePlanDetailIds")) {  //如果请求中包含"purchasePlanDetailIds",则根据"purchasePlanDetailIds"获取设备年度采购计划明细集合   
			this.purchasePlanDetails = this.purchasePlanDetailManager.loadAllPurchasePlanDetails(this.getIds("purchasePlanDetailIds"));
		}
		this.setFirst(false);
	}
	
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除页面选中的设备年度采购计划明细
	 * @return SUCCESS
	 */
	public String delete() {
		this.purchasePlanDetailManager.deleteAllPurchasePlanDetails(this.purchasePlan,this.purchasePlanDetails);
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "devicePurchasePlanDetails";
	}

	/**
	 * 往查询设备年度采购计划明细的hql语句中设置设备年度采购计划或设备年度采购计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("purchasePlan.id")){
			map.put("purchasePlan.id", this.getId("purchasePlan.id"));
		}
		return map;
	}
	
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

}
