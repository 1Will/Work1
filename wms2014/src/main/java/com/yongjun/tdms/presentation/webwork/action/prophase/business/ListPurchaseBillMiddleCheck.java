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
 *//*
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
import com.yongjun.tdms.model.prophase.business.MiddleCheck;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillMiddleCheckManager;

public class ListPurchaseBillMiddleCheck extends ValueListAction {
	/**
	 * <p>Title: ListRepairPlanAndProcAction
	 * <p>Description: 采购单中途检测页面访问控制类</p>
	 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
	 * <p>Company: www.yj-technology.com</p>
	 * @author wzou@yj-technology.com
	 * @version $Id: ListRepairPlanAndProcAction.java 11184 2008-03-03 13:39:26Z zbzhang $
	 */
	private static final long serialVersionUID = 1L;
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillMiddleCheckManager purchaseBillMiddleCheckManager;
	private PurchaseBill  purchaseBill ;  //采购单中途检测所用到的采购单对象
	private List<MiddleCheck> middleChecks;//采购单中途检测的集合
	public ListPurchaseBillMiddleCheck(PurchaseBillManager purchaseBillManager,PurchaseBillMiddleCheckManager purchaseBillMiddleCheckManager){
		this.purchaseBillManager=purchaseBillManager;
		this.purchaseBillMiddleCheckManager=purchaseBillMiddleCheckManager;
		
	}
	public void prepare() {
		if (null == this.purchaseBill) {//获得采购单对象
			if (this.hasId("purchaseBill.id")) {
				this.purchaseBill = this.purchaseBillManager.loadPurchaseBill(getId("purchaseBill.id"));
				
			} 
		}
		if (this.middleChecks == null && this.hasIds("middleCheckIds")) {//获得采购单中途检测的集合
            this.middleChecks = this.purchaseBillMiddleCheckManager.loadMiddleChecks(this.getIds("middleCheckIds"));
        }
		this.setFirst(false);
	}
	

	public String execute() {
		if(this.hasKey("delete")){//单个删除或批量删除采购单中途检测的集合
			this.purchaseBillMiddleCheckManager.deleteAllMiddleCheck(middleChecks);
			this.addActionMessage(this.getText("middleCheck.delete.success"));
		}
		return SUCCESS;
	}
	

	// 往查询中途检测的hql语句中设置采购单的ID值
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("purchaseBill.id")){
			map.put("purchaseBill.id", this.getId("purchaseBill.id"));
		}
		return map;
	}
	//valelist所匹配的查询
	@Override
	protected String getAdapterName() {
		
		return "middlechecks";
	}


	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}


	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}




}
