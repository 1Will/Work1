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
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
/**
 * <p>Title:ListTechAlterDetailsPurchaseConcatAction
 * <p>Description:采购合同技术改造明细列表页面控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author smzhang@yj-technology.com
 * @version $Id: ListTechAlterDetailsPurchaseConcatAction.java 2008-12-7 16:22:02 smzhang$
 */
public class ListTechAlterDetailsPurchaseConcatAction extends ValueListAction {

private static final long serialVersionUID = 5498177256301105256L;
	
	private PurchaseBill purchaseBill;
	private List<com.yongjun.tdms.model.prophase.business.PurchaseBillDetail> purchaseBillDetails;
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	
	private String addNewPurchaseBillDetailIds;				  //添加采购单明细的 ids
	private String addPurchaseContractDtlIds;                 //需要修改采购单明细的 ids 
	private String addPurchaseContractDetailAmountNumber;     //新添加到采购单明细的数量
	private String addPurchaseContractDetailUnitPrice;        //新添加到采购单明细的单价
	private String addPurchaseContractDetailRequestDate;      //新添加到采购单明细的需求日期
	
	/**
	 * 构造方法
	 * @param purchaseBillManager,
	 * 		  purchaseBillDetailManager
	 */
	public ListTechAlterDetailsPurchaseConcatAction(PurchaseBillManager purchaseBillManager,
			PurchaseBillDetailManager purchaseBillDetailManager){
		this.purchaseBillManager = purchaseBillManager;
		this.purchaseBillDetailManager = purchaseBillDetailManager;
	}
	@Override
	public void prepare() throws Exception {
		//加载purchaseBill对象
		if (this.hasId("purchaseBill.id")) {
			this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
		}
		if(this.purchaseBillDetails == null && this.hasIds("purchaseContractToolingMakeIds")){
			purchaseBillDetails = purchaseBillDetailManager.loadPurchaseBillDetails(this.getIds("purchaseContractToolingMakeIds"));
		}
		if (null == this.addNewPurchaseBillDetailIds) {//给据页面传来的采购单的ids,获得所有的采购单ids,
			if (!StringUtils.isEmpty(request.getParameter("addPurchaseBillDetailIds"))) {
				this.addNewPurchaseBillDetailIds = request.getParameter("addPurchaseBillDetailIds");
			}
		}
		this.setFirst(false);
	}

	@Override
	public String execute() throws Exception {
		if(this.isDelete()){
			return delete();
		}
		if(this.isSaveDetail()){
			return saveDetail();
		}
		if(this.isAddPurchaseBill()){
			return saveAddPurchaseBill();
		}
		return SUCCESS;
	}
	/**
	 * 判断是否保存采购合同明细
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
	
	/**
	 * 保存页面传来的所有采购单明细
	 * @return
	 */
	public String saveAddPurchaseBill() {
		this.purchaseBillDetailManager.storeTechAlterDetailsPurchaseConcatDtl( purchaseBill,addNewPurchaseBillDetailIds);
		return SUCCESS;
	}
	/**
	 * 判断页面传来的addPurchaseBill变量是否有值，且值是否等于addPurchaseBills
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddPurchaseBill() {
		if (!StringUtils.isEmpty(request.getParameter("addPurchaseBill"))) {
			if (request.getParameter("addPurchaseBill").equals("addPurchaseBills"))
				return true;
		}
		return false;
	}
	public String saveDetail(){
		//获得所有采购单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseContractDtlIds"))){
			this.addPurchaseContractDtlIds= request.getParameter("allPurchaseContractDtlIds");
		}
		//获得采购单明细的数量
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseContractDetailAmountNumber"))){
			this.addPurchaseContractDetailAmountNumber= request.getParameter("allPurchaseContractDetailAmountNumber");
		}
		//获得采购单明细的单价
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseContractDetailUnitPrice"))){
			this.addPurchaseContractDetailUnitPrice= request.getParameter("allPurchaseContractDetailUnitPrice");
		}
		//获得采购单明细的期望到货日期
		if(!StringUtils.isEmpty(request.getParameter("allPurchaseContractDetailRequestDate"))){
			this.addPurchaseContractDetailRequestDate= request.getParameter("allPurchaseContractDetailRequestDate");
		}
		if(null!=addPurchaseContractDtlIds||null!=addPurchaseContractDetailAmountNumber||null!=addPurchaseContractDetailUnitPrice
				||null!=addPurchaseContractDetailRequestDate){
			this.purchaseBillDetailManager.storPurchaseContractDetail(purchaseBill,addPurchaseContractDtlIds,
																		addPurchaseContractDetailAmountNumber,
																		addPurchaseContractDetailUnitPrice,
																		addPurchaseContractDetailRequestDate,null);
		}
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		return "techAlterDetailsPurchaseConcats";
	}
	public String delete(){
		 try{
		     this.purchaseBillDetailManager.deleteAllPurchaseBillDetails(purchaseBillDetails,purchaseBill);
		 }catch(Exception e){
					e.printStackTrace();
		    	  return ERROR;
		      }
		  this.addActionMessage(this.getText("PurchaseBillDetails.delete.success"));   
		  return SUCCESS;
	}
	
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("purchaseBill.id",this.getId("purchaseBill.id"));
		return map;
	}
	/*-------------------setter、getter方法--------------------*/
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}


}
