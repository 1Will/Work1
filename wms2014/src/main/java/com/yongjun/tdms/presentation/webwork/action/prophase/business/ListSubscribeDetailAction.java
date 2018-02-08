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
package com.yongjun.tdms.presentation.webwork.action.prophase.business;



import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.service.base.event.EventNewManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * @author qs
 * @version $Id: ListSubscribeDetailAction.java 11309 2008-03-13 05:51:55Z mwei $
 */
@SuppressWarnings("serial")
public class ListSubscribeDetailAction extends ValueListAction {
	private final SubscribeManager subscribeManager;	
	private List<SubscribeDetail> subscribeDetails;
	private SubscribeDetail subscribeDetail;
	private Subscribe subscribe;               
	private String addQuarterPlanIds;       		//新添加的季度计划的id集合 
	private String addSpareAccountIds;		//新添加的设备管理中的备件台帐选择的id集合
    private String toolingDevFlag;
    
    private String allSubscribeDetailId;			//申购单明细所有的ID
    private String allSubscribeDetailAmountNumber;	//申购单明细所有的数量
    private String allSubscribeDetailUnitPrice;		//申购单明细所有的单价
    private String allSubscribeDetailRequestDate;	//申购单明细所有的需要日期
    private String allSubscribeDetailSupplierName;	//申购单明细所有的供应商名称
    private String allSubscribeDetailComment;		//申购单明细所有的备注
    private String allSubscribeDetailBuyeAmount;	//申购单明细所有的采购数量
    private String allBeizhu ;//所有备注的值
    private String addSpareWareHouseIds;
    
	public ListSubscribeDetailAction(SubscribeManager subscribeManager) {
		this.subscribeManager = subscribeManager;
		
	}
	
	public void prepare() {
		if (this.hasId("subscribe.id")) {
			this.subscribe = this.subscribeManager.loadSubscribe(getId("subscribe.id"));
		}
		if (this.subscribeDetails == null && this.hasIds("subscribeDtlIds")) {
            this.subscribeDetails = this.subscribeManager.loadAllSubscribeDetails(this.getIds("subscribeDtlIds"));
        }
		if (null == this.addQuarterPlanIds) {//给据页面传来的申购单的ids,获得所有的申购单ids,(季度计划)
			if (!StringUtils.isEmpty(request.getParameter("addquarterPlanIds"))) {
				this.addQuarterPlanIds = request.getParameter("addquarterPlanIds");
			}
//			if(this.hasId("toolingDevFlag")){
//				this.toolingDevFlag=request.getParameter("toolingDevFlag");
//			}
		}
		if(null == this.addSpareAccountIds){//根据页面传来的申购单的ids，获得所有的申购单ids,(设备管理/工装管理中的备件台帐)
			if (!StringUtils.isEmpty(request.getParameter("addSpareDetailIds"))) {
				this.addSpareAccountIds = request.getParameter("addSpareDetailIds");
			}
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
		}
		this.setFirst(false);
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b>函数处理。
	 */
	public String execute() throws Exception {
		if (this.isAddQuarterPlan()) {//判断是否添加季度计划
			return this.saveAddQuarterPlan();
		}
		if(this.isAddSpareAcount()){//判断是否添加设备管理/工装管理中的备件台帐的信息
			return this.saveAddSpareAccount();
		}
		if (this.isAddSpareWareHouseOfLessMinStocks()) { //判断是否从低于安全库存中选择
			return this.saveAddSpareWareHouse();
		}
		if(this.isSaveDetail()){
			return this.SaveSubscribeDetail();
		}
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断页面是否保存申购单明细"
	 * @return
	 */
	private boolean isSaveDetail() {
	   if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		  }
	    }
		return false;
	}
	private String SaveSubscribeDetail(){
		//获得所有申购单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailIds"))){
			this.allSubscribeDetailId = request.getParameter("allSubscribeDetailIds");
		}
		//获取所有申购单明细的数量
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailAmountNumber"))){
			this.allSubscribeDetailAmountNumber = request.getParameter("allSubscribeDetailAmountNumber");
		}
		//获取所有申购单明细的单价
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailUnitPrice"))){
			this.allSubscribeDetailUnitPrice = request.getParameter("allSubscribeDetailUnitPrice");
		}
		//获取所有申购单明细的需要日期
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailRequestDate"))){
			this.allSubscribeDetailRequestDate = request.getParameter("allSubscribeDetailRequestDate");
		}
		//获取所有申购单明细的供应商名称
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailSupplierName"))){
			this.allSubscribeDetailSupplierName = request.getParameter("allSubscribeDetailSupplierName");
		}
		//获取所有申购单明细的备注
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailComment"))){
			this.allSubscribeDetailComment = request.getParameter("allSubscribeDetailComment");
		}
		//获取所有申购单明细的采购数量
		if(!StringUtils.isEmpty(request.getParameter("allSubscribeDetailBuyeAmount"))){
			this.allSubscribeDetailBuyeAmount = request.getParameter("allSubscribeDetailBuyeAmount");
		}
		//获取所有申购单明细的备注 zzb
		if(!StringUtils.isEmpty(request.getParameter("allBeizhu"))){
			this.allBeizhu = request.getParameter("allBeizhu");
		}
		
		if(null!=allSubscribeDetailComment||null!=allSubscribeDetailSupplierName||
				null!=allSubscribeDetailRequestDate||null!=allSubscribeDetailUnitPrice||
				null!=allSubscribeDetailAmountNumber||null!=allSubscribeDetailId){
				this.subscribeManager.storeSubscribeDetail(allSubscribeDetailId,
														   allSubscribeDetailAmountNumber,
														   allSubscribeDetailUnitPrice,
														   allSubscribeDetailRequestDate,
														   allSubscribeDetailSupplierName,
														   allSubscribeDetailComment,
														   allSubscribeDetailBuyeAmount,null,
														   subscribe,allBeizhu);
		}
		return SUCCESS;
	}
	
	/**
	 * 判断页面传来的addSubscribe变量是否有值，且值是否等于addSubscribes
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddQuarterPlan() {
		if (!StringUtils.isEmpty(request.getParameter("addquarterPlan"))) {
			if (request.getParameter("addquarterPlan").equals("addquarterPlans"))
				return true;
		}
		return false;
	}
	
	/**
	 * 保存页面传来的所有申购单明细
	 * @return
	 */
	public String saveAddQuarterPlan() {
		this.subscribeManager.storQuarterPlan(subscribe,addQuarterPlanIds);
		return SUCCESS;
	}

	/**
	 * 判断页面是不是从设备管理/工装管理中的备件台帐选择而来
	 * @return
	 */
	private boolean isAddSpareAcount(){
		if (!StringUtils.isEmpty(request.getParameter("spareAccountSelector"))) {
			if (request.getParameter("spareAccountSelector").equals("spareAccount"))
				return true;
		}
		return false;
	}
	/**
	 * 判断页面是不是从低于备件库台帐选择而来
	 * @return
	 */
	private boolean isAddSpareWareHouseOfLessMinStocks(){
		if (!StringUtils.isEmpty(request.getParameter("spareWareHouseSelector"))) {
			if (request.getParameter("spareWareHouseSelector").equals("spareWareHouseOfLessMinStocks"))
				return true;
		}
		return false;
	}
	
	
	/**
	 * 将设备管理/工装管理中的备件台帐的信息copy到申购明细中
	 * @return
	 */
	public String saveAddSpareAccount() {
		this.subscribeManager.storeSpareAccount(subscribe,addSpareAccountIds);
		return SUCCESS;
	}
	
	
	public String saveAddSpareWareHouse(){
		//获得所有申购单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("addSpareWareHouseIds"))){
			this.addSpareWareHouseIds = request.getParameter("addSpareWareHouseIds");
		}
		this.subscribeManager.storeSubscribeDetailByLessMinStocks(subscribe,addSpareWareHouseIds);
		return SUCCESS;
	}
	
	
	/**
	 * 删除选择的申购单明细
	 */
	public String delete() {
		
		try {
			this.subscribeManager.deleteAllSubscribeDetails(subscribeDetails,subscribe);
			//计算明细的数量 zzb
		   this.updateSubNum(subscribe.getSubscribeDetails());
		
			
		} catch (Exception e) {
            e.printStackTrace();
			return ERROR;
		}
		this.addActionMessage(this.getText("subscribeDetails.delete.success"));
		return SUCCESS;
	}
	
    /**
     * 更新采购单的 采购、申购、入库数量
     * @param set
     */
	public void updateSubNum(Set<SubscribeDetail> set){
		//采购数量
		int purNum=0;
		//入库数量
		int insNum=0;
		//申购数量
		int subNum=0;
		//不采购
		int noNum=0;
		
		
		
		if(null != set &&set.size()>0){
			for(SubscribeDetail detail:set){
				if(SubscribeDetailStatus.PURCHASEED.equals(detail.getStatus())){
					purNum ++;
				}
				if(SubscribeDetailStatus.INSPECTED.equals(detail.getStatus())){
					insNum++;
				}
				if(SubscribeDetailStatus.NOTPURCHASEED.equals(detail.getStatus())){
					noNum++;
				}
				 
			}
		}
		subNum = set.size()-noNum;
		subscribe.setSumDetail(set.size());
		subscribe.setSubNum(subNum);
		subscribe.setPurNum(purNum+insNum);
		subscribe.setInsNum(insNum);
		
		this.subscribeManager.storeSubscribe(subscribe);
		
	}
	
	
    private User getLoginUser(){
    	return this.userManager.getUser();
    }
	
	
	@Override
	protected String getAdapterName() {
		return "subscribeDetails";
	}
	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}
	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

}
