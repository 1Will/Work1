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

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.PayDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillPayDetailManager;

/**
 * @author qs
 * @version $Id: ListPurchaseBillPayDetailAction.java 11328 2008-03-15 09:39:30Z mwei $
 */
@SuppressWarnings("serial")
public class ListPurchaseBillPayDetailAction extends ValueListAction {
  
  private final PurchaseBillPayDetailManager purchaseBillPayDetailManager;
  private final PurchaseBillManager purchaseBillManager;
  private List<PayDetail> payDetails;  //采购单付款明细集合
  private PurchaseBill purchaseBill;   //采购单付款明细所关联的采购单
  public ListPurchaseBillPayDetailAction(PurchaseBillManager purchaseBillManager,PurchaseBillPayDetailManager purchaseBillPayDetailManager){
	  this.purchaseBillManager=purchaseBillManager;
	  this.purchaseBillPayDetailManager=purchaseBillPayDetailManager;
	
  }
	public void prepare() {
		if (null == this.purchaseBill) {//获得采购单对象,目的是说明当前采购单付款明细对象是属于哪个采购单的
			if (this.hasId("purchaseBill.id")) {
				this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
				
			} 
		}
		if (this.payDetails == null && this.hasIds("payDtlIds")) {//获得采购单付款明细的集合
            this.payDetails = this.purchaseBillPayDetailManager.loadPayDetails(this.getIds("payDtlIds"));
        }
		this.setFirst(false);
	}
	
	public String execute() {
		
		if(this.hasKey("delete")){
			this.purchaseBillPayDetailManager.deleteAllPayDetail(payDetails,purchaseBill);
			this.addActionMessage(this.getText("payDetails.delete.success"));
		}
		
		return SUCCESS;
	}
	/**
	 * 往查询采购单付款明细的hql语句中设置采购单的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("purchaseBill.id")){
			map.put("purchaseBill.id", this.getId("purchaseBill.id"));
		}
		return map;
	}
	@Override
	protected String getAdapterName() {
		return "purchaseBillPayDetails";
	}

	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}

	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}

}
