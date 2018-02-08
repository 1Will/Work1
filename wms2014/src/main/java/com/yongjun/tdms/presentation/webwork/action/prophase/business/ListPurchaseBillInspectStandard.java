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
import com.yongjun.tdms.model.prophase.business.InspectStandard;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillInspectStandardManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;

public class ListPurchaseBillInspectStandard extends ValueListAction{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	  private final   PurchaseBillInspectStandardManager prchaseBillInspectStandardManager;
	  private final PurchaseBillManager purchaseBillManager;
	  private PurchaseBill purchaseBill;   //采购单验收标准所关联的采购单对象
	  private List<InspectStandard> inspectStandards;//获得验收标准的集合
	  public ListPurchaseBillInspectStandard(PurchaseBillManager purchaseBillManager,PurchaseBillInspectStandardManager prchaseBillInspectStandardManager){
		  this.purchaseBillManager=purchaseBillManager;
		  this.prchaseBillInspectStandardManager=prchaseBillInspectStandardManager;
	  }
	  public void prepare() {
			if (null == this.purchaseBill) {
				if (this.hasId("purchaseBill.id")) {
					this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
					
				} 
			}
			if (this.inspectStandards == null && this.hasIds("inpDtlIds")) {
	            this.inspectStandards = this.prchaseBillInspectStandardManager.loadInspectStandards(this.getIds("inpDtlIds"));
	        }
			this.setFirst(false);
		}
	  public String execute() {
			if(this.hasKey("delete")){
				this.prchaseBillInspectStandardManager.deleteAllInspectStandard(inspectStandards);
				this.addActionMessage(this.getText("prchaseBillInspectStandardManager.delete.success"));
			}
			
			return SUCCESS;
		}
      // 往查询验收标准的hql语句中设置采购单的ID值
		@SuppressWarnings("unchecked")
		protected Map getRequestParameterMap() {
			Map map = super.getRequestParameterMap();
	        if (this.hasId("purchaseBill.id")){
				map.put("purchaseBill.id", this.getId("purchaseBill.id"));
			}
			return map;
		}
		//验收标准在VALUELIST匹配的查询语句
	@Override
	protected String getAdapterName() {
		
		return "inspectStandards";
	}
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public List<InspectStandard> getInspectStandards() {
		return inspectStandards;
	}
	public void setInspectStandards(List<InspectStandard> inspectStandards) {
		this.inspectStandards = inspectStandards;
	}
}