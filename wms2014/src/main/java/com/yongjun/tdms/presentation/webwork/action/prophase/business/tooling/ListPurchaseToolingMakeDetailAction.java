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
public class ListPurchaseToolingMakeDetailAction extends ValueListAction {	

	private static final long serialVersionUID = 5498177256301105256L;
	
	private Subscribe subscribe;
	private SubscribeDetail subscribeDetail;
	private List<SubscribeDetail> subscribeDetails;
    private String allPurToolingMakeDetailId;			//工装制作明细所有的ID
    private String allPurToolingMakeDetailUnitPrice;	//工装制作明细所有的数量
    private String allPurToolingMakeDetailAmount;		//工装制作明细所有的单价
    private String allPurToolingMakeDetailRequestDate;	//工装制作明细所有的需要日期
    private String addQuarterPlanDetailIds;				//从季度计划的工装制作明细选择而来
    private String valueAry;							//页面上可输入项的信息
    
	private final SubscribeManager subscribeManager;
	/**
	 * 构造方法
	 * @param subscribeManager
	 */
	public ListPurchaseToolingMakeDetailAction(SubscribeManager subscribeManager){
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
		//页面信息的回传
		if(valueAry==null){
			this.valueAry = request.getParameter("valueAry");

		}

		this.setFirst(false);
	}

	@Override
	public String execute() throws Exception {

	

		//删除

		if(this.isDelete()){
			return delete();
		}
		//保存工装制作明细
		if(this.isSavePurchaseToolingMakeDetail()){
			return savePurchaseToolingMakeDetail();
		}
		//保存季度计划明细[工装制作明细]
		if(this.isAddQuarterPlanDetail()){
			return saveAddQuarterPlanDetail();
		}
		return SUCCESS;
	}

	/**
	 * 删除选择的工装制作明细
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
	 * 保存工装制作明细
	 * @return
	 */
	public String savePurchaseToolingMakeDetail() {
		//获得所有工装制作明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allPurToolingMakeDetailIds"))){
			this.allPurToolingMakeDetailId = request.getParameter("allPurToolingMakeDetailIds");
		}
		//获得所有工装制作明细的单价
		if(!StringUtils.isEmpty(request.getParameter("allPurToolingMakeDetailUnitPrice"))){
			this.allPurToolingMakeDetailUnitPrice = request.getParameter("allPurToolingMakeDetailUnitPrice");
		}
		//获得所有工装制作明细的数量
		if(!StringUtils.isEmpty(request.getParameter("allPurToolingMakeDetailAmount"))){
			this.allPurToolingMakeDetailAmount = request.getParameter("allPurToolingMakeDetailAmount");
		}
		//获得所有工装制作明细的需要日期
		if(!StringUtils.isEmpty(request.getParameter("allPurToolingMakeDetailRequestDate"))){
			this.allPurToolingMakeDetailRequestDate = request.getParameter("allPurToolingMakeDetailRequestDate");
		}
		if(allPurToolingMakeDetailId!=null||allPurToolingMakeDetailUnitPrice!=null||
				allPurToolingMakeDetailAmount!=null||allPurToolingMakeDetailRequestDate!=null){
			this.subscribeManager.storeToolingSubscribeDtl(allPurToolingMakeDetailId,
													   allPurToolingMakeDetailAmount,
													   allPurToolingMakeDetailUnitPrice,
													   allPurToolingMakeDetailRequestDate,
													   null,
													   null,
					   subscribe);
		}
		return SUCCESS;
	}
	
	/**
	 * 从季度计划的工装制作明细copy到采购单的工装制作明细
	 */
	public String saveAddQuarterPlanDetail() {
		this.subscribeManager.storToolingQuarterPlan(subscribe,addQuarterPlanDetailIds,"TOOLING_MAKE");
		return SUCCESS;
	}
	
	/**
	 * 判断页面是不是季度计划工装制作明细选择而来
	 * @return true | false
	 */
	private boolean isAddQuarterPlanDetail(){
		if (!StringUtils.isEmpty(request.getParameter("quarterPlanDetailSelector"))) {
			if (request.getParameter("quarterPlanDetailSelector").equals("quartePlanDetail"))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断页面是否保存工装制作明细"
	 * @return
	 */
	private boolean isSavePurchaseToolingMakeDetail() {
		if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		  }
	}
		return false;
	}
	@Override
	protected String getAdapterName() {
		return "purToolingMakeDtls";
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
	public String getValueAry() {
		return valueAry;
	}
}
