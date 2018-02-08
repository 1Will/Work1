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

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

/**
 * @author xschen
 * @version $Id: ListPurchaseBillDetailAction.java 11328 2008-03-15 09:39:30Z mwei $
 */
@SuppressWarnings("serial")
public class ListPurchaseBillDetailAction extends ValueListAction {
	
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private String toolingDevFlag;
	private String purchaseContractType;
	private PurchaseBillDetail purchaseBillDtl;//采购单明细的对象
	private PurchaseBill purchaseBill;         //采购单对象
	private List<PurchaseBillDetail> purchaseBillDetails; //采购单明细集合 
	private Subscribe subscribe;                          //采购明细所关联的申购单,因为采购单明细列表中的数据可以从申购单明细列表中取值 
	private String addSubscribeIds;                       // 新添加到申购单的Ids   
	private String addSpareAccountIds;		      //新添加的设备管理中的备件台帐选择的id集合
	private String addPurchaseBillDtlIds;                 //新添加到采购单明细的 ids 
	private String addPurchaseBillDetailAmountNumber;     //新添加到采购单明细的数量
	private String addPurchaseBillDetailUnitPrice;        //新添加到采购单明细的单价
	private String addPurchaseBillDetailRequestDate;      //新添加到采购单明细的需求日期
	private String addPurchaseBillDetailComment;          //新添加到采购单明细的备注
	private final UserManager userManager;
	private Long[] purchaseBillDtlIds;
	
	
	public ListPurchaseBillDetailAction(PurchaseBillManager purchaseBillManager,
			PurchaseBillDetailManager purchaseBillDetailManager, UserManager userManager){
		this.purchaseBillManager=purchaseBillManager;
		this.purchaseBillDetailManager=purchaseBillDetailManager;
		this.userManager =userManager;
	}
	public void prepare() {
		if (this.hasId("purchaseBill.id")) {     //获得采购单对象,目的是说明当前采购单明细对象是属于哪个采购单的
			this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
			
		} 
		if (this.hasId("purchaseBillDtl.id")) {     //获得采购单对象,目的是说明当前采购单明细对象是属于哪个采购单的
			this.purchaseBillDtl = this.purchaseBillDetailManager.loadPurchaseBillDetail(getId("purchaseBillDtl.id"));
			
		} 
		if (this.purchaseBillDetails == null && this.hasIds("purchaseBillDtlIds")) {//获得采购单明细对象
            this.purchaseBillDetails = this.purchaseBillDetailManager.loadPurchaseBillDetails(this.getIds("purchaseBillDtlIds"));
            purchaseBillDtlIds = this.getIds("purchaseBillDtlIds");		//获得采购单明细ID集合
        } 	
		if (null == this.addSubscribeIds) {//给据页面传来的申购单的ids,获得所有的申购单ids,
			if (!StringUtils.isEmpty(request.getParameter("addSubscribeIds"))) {
				this.addSubscribeIds = request.getParameter("addSubscribeIds");
			}
		}
		if(null==this.addSpareAccountIds){ //给据页面传来的备件台帐的ids 获得备件台帐的ids
			if (!StringUtils.isEmpty(request.getParameter("addSpareDetailIds"))) {
				this.addSpareAccountIds = request.getParameter("addSpareDetailIds");
			}
		}
		
      if(this.hasId("toolingDevFlag")){
    	  this.toolingDevFlag = request.getParameter("toolingDevFlag");
      }
      if(this.hasId("purchaseContractType")){
    	  this.purchaseContractType=request.getParameter("purchaseContractType");
      }
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isAddSubscribe()) {//判断是否添加申购单明细到采购单明细中来
			return this.saveAddSubscribe();
		}
		if(this.isAddSpareAcount()){//判断是否添加设备管理中的备件台帐的信息
			return this.saveAddSpareAccount();
		}
		if(this.isSaveDetail()){
			return saveDetail();
		}
		if(this.isDelete()){   
			return this.delete();
		}
		return SUCCESS;
	}
	public String delete(){
		 try{
//		      根据采购明细ID集合获取对应的申购明细ID集合
			 String subDetailIds = this.purchaseBillDetailManager.updSubDetailIds(StringUtils.join(purchaseBillDtlIds, ","));
		     this.purchaseBillDetailManager.deleteAllPurchaseBillDetails(purchaseBillDetails,purchaseBill);
//			 根据申购明细ID集合更新申购单、汇总单的采购项
			 this.purchaseBillDetailManager.updStatus(subDetailIds+",");
//			 this.purchaseBillDetailManager.updateNnmber(purchaseBill); 废除不用 jyang 2011-5-18
		 }catch(Exception e){
			 	e.printStackTrace();
		 return ERROR;
		 }
		 this.addActionMessage(this.getText("PurchaseBillDetails.delete.success"));   
		 return SUCCESS;
	}
	/**
	 * 判断页面传来的addSubscribe变量是否有值，且值是否等于addSubscribes
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddSubscribe() {
		if (!StringUtils.isEmpty(request.getParameter("addSubscribe"))) {
			if (request.getParameter("addSubscribe").equals("addSubscribes"))
				return true;
		}
		return false;
	}
	/**
	 * 保存页面传来的所有申购单明细
	 */
	public String saveAddSubscribe() {
		this.purchaseBillDetailManager.storSubscribeDtl( purchaseBill,addSubscribeIds);
//		this.purchaseBillDetailManager.updateNnmber(purchaseBill);	废除不用	jyang 2011-5-18
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
	 * 保存采购单明细
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
	public String saveDetail(){
		//获得所有采购单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseBillDtlIds"))){
			this.addPurchaseBillDtlIds= request.getParameter("allPurchaseBillDtlIds");
		}
		//获得采购单明细的数量
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseBillDetailAmountNumber"))){
			this.addPurchaseBillDetailAmountNumber= request.getParameter("allPurchaseBillDetailAmountNumber");
		}
		//获得采购单明细的单价
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseBillDetailUnitPrice"))){
			this.addPurchaseBillDetailUnitPrice= request.getParameter("allPurchaseBillDetailUnitPrice");
		}
		//获得采购单明细的期望到货日期
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseBillDetailRequestDate"))){
			this.addPurchaseBillDetailRequestDate= request.getParameter("allPurchaseBillDetailRequestDate");
		}
		//获得采购单明细的备注
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseBillDetailComment"))){
			this.addPurchaseBillDetailComment= request.getParameter("allPurchaseBillDetailComment");
		}
		if(null!=addPurchaseBillDtlIds||null!=addPurchaseBillDetailAmountNumber||null!=addPurchaseBillDetailUnitPrice
				||null!=addPurchaseBillDetailRequestDate||null!=addPurchaseBillDetailComment){
			this.purchaseBillDetailManager.storPurchaseBillDetail(purchaseBill,addPurchaseBillDtlIds,
																  addPurchaseBillDetailAmountNumber,
																  addPurchaseBillDetailUnitPrice,
																  addPurchaseBillDetailRequestDate,
																  addPurchaseBillDetailComment,this.getLoginUser());
		}
		//获得采购明细ID集合对应的申购明细ID集合
		String subDetailIds = this.purchaseBillDetailManager.updSubDetailIds(addPurchaseBillDtlIds);
		//根据申购明细ID集合更新申购单、汇总单的采购项
		this.purchaseBillDetailManager.updStatus(subDetailIds);
		return SUCCESS;
	}
	/**
	 * 将设备管理中/工装管理的备件台帐的信息copy到申购明细中
	 * @return
	 */
	public String saveAddSpareAccount() {
		this.purchaseBillDetailManager.storPurchaseBillDetail(purchaseBill,addSpareAccountIds);
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		return "purchaseBillDtls";
	}

	/**
	 * 往查询采购明细的hql语句中设置采购单的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("purchaseBill.id")){
			map.put("purchaseBill.id", this.getId("purchaseBill.id"));
		}
		return map;
	}
	
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	public PurchaseBillDetail getPurchaseBillDtl() {
		return purchaseBillDtl;
	}
	public void setPurchaseBillDtl(PurchaseBillDetail purchaseBillDtl) {
		this.purchaseBillDtl = purchaseBillDtl;
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
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
