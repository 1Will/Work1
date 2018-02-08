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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * <p>Title:ListToolingMakeDetail
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListToolingMakeDetail.java 2008-12-7 11:03:27 yli$
 */
public class ListPurchaseRepairMaintenanceDetailAction extends ValueListAction {	

	private static final long serialVersionUID = 5498177256301105256L;
	
	private Subscribe subscribe;
	private SubscribeDetail subscribeDetail;
	private List<SubscribeDetail> subscribeDetails;
    private String addQuarterPlanDetailIds;				//从季度计划的维修保养明细选择而来
    
	private final SubscribeManager subscribeManager;
	/**
	 * 构造方法
	 * @param subscribeManager
	 */
	public ListPurchaseRepairMaintenanceDetailAction(SubscribeManager subscribeManager){
		this.subscribeManager = subscribeManager;
	}
	@Override
	public void prepare() throws Exception {
		//加载sunscribe对象
		if (this.hasId("subscribe.id")) {
			this.subscribe = this.subscribeManager.loadSubscribe(getId("subscribe.id"));
		}
		if(this.subscribeDetails == null && this.hasIds("purToolingPurchaseMakeDtlIds")){
			subscribeDetails = subscribeManager.loadAllSubscribeDetails(this.getIds("purToolingPurchaseMakeDtlIds"));
		}
		//获得所有从备件台帐选择的备件ids
		if(null==this.addQuarterPlanDetailIds){
			if(!StringUtils.isEmpty(request.getParameter("addQuarterPlanDetailIds"))){
				this.addQuarterPlanDetailIds = request.getParameter("addQuarterPlanDetailIds");
			}
		}

//		subscribeManager.updateToolingSubscribeStatus(subscribe,"REPAIR_MAINTENANCE");
		
		this.setFirst(false);
	}

	@Override
	public String execute() throws Exception {
		if(this.isDelete()){
			return delete();
		}
		if(this.isAddQuarterPlanDetail()){
			return saveAddQuarterPlanDetail();
		}
		return SUCCESS;
	}

	/**
	 * 删除选择的维修保养明细
	 */
	public String delete() {
		try {
			this.subscribeManager.deleteAllToolingSubscribeDetails(subscribeDetails,subscribe);
		} catch (Exception e) {
            e.printStackTrace();
			return ERROR;
		}
		this.addActionMessage(this.getText("subscribeDetails.delete.success"));
		return SUCCESS;
	}

	/**
	 * 从季度计划的维修保养明细copy到采购单的维修保养明细
	 */
	public String saveAddQuarterPlanDetail() {
		this.subscribeManager.storToolingQuarterPlan(subscribe,addQuarterPlanDetailIds,"REPAIR_MAINTENANCE");
		return SUCCESS;
	}
	
	/**
	 * 判断页面是不是季度计划维修保养明细选择而来
	 * @return true | false
	 */
	private boolean isAddQuarterPlanDetail(){
		if (!StringUtils.isEmpty(request.getParameter("quarterPlanDetailSelector"))) {
			if (request.getParameter("quarterPlanDetailSelector").equals("quartePlanDetail"))
				return true;
		}
		return false;
	}

	@Override
	protected String getAdapterName() {
		return "purRepairMaimtenDtls";
	}
	
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("subscribe.id",this.getId("subscribe.id"));
		return map;
	}
	/*-------------------setter、getter方法--------------------*/
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}
	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}

}
