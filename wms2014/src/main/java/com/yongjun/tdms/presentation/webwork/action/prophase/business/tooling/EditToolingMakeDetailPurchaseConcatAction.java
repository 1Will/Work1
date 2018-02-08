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

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.model.prophase.business.PurchaseTypeStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
/**
 * <p>Title:EditToolingMakeDetailPurchaseConcatAction
 * <p>Description:采购合同工装制作明细维护页面控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author smzhang@yj-technology.com
 * @version $Id: EditPurchaseToolingMakeDetailAction.java 2008-12-7 16:22:02 smzhang$
 */
public class EditToolingMakeDetailPurchaseConcatAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private PurchaseBill purchaseBill;
	private PurchaseBillDetail purchaseBillDetail;
	
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private final ToolingTypeManager toolingTypeManager;
	private final CodeValueManager codeValueManager;
	
	public EditToolingMakeDetailPurchaseConcatAction(PurchaseBillManager purchaseBillManager,
			PurchaseBillDetailManager purchaseBillDetailManager,ToolingTypeManager toolingTypeManager,
			CodeValueManager codeValueManager){
		this.purchaseBillManager = purchaseBillManager;
		this.purchaseBillDetailManager = purchaseBillDetailManager;
		this.toolingTypeManager = toolingTypeManager;
		this.codeValueManager = codeValueManager;
	}
	public void prepare() throws Exception {
//		工装制作明细所关联的采购合同
		if(this.hasId("purchaseBill.id")){
			this.purchaseBill = purchaseBillManager.loadPurchaseBill(this.getId("purchaseBill.id"));
		}
		if(this.hasId("toolingMakeDetail.id")){
			this.purchaseBillDetail = purchaseBillDetailManager.loadPurchaseBillDetail(this.getId("toolingMakeDetail.id"));
		}else{
			purchaseBillDetail = new PurchaseBillDetail();
		}
	}
	public String save(){
		boolean isNew = this.purchaseBillDetail.isNew();
		
		//设置工装
		if(this.hasId("category.id")){
			ToolingType toolingType = this.toolingTypeManager.loadToolingType(this.getId("category.id"));
			//设置工装类别ID
			purchaseBillDetail.setToolingCategory(toolingType);
			//设置工装类别名称
			purchaseBillDetail.setCategoryName(toolingType.getValue());
		}
		//设置计量单位
		if(this.hasId("calUnit.id")){
			purchaseBillDetail.setCalUnit(codeValueManager.loadCodeValue(this.getId("calUnit.id")));
		}
		//设置年度计划明细类别[工装制造明细]
		purchaseBillDetail.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);
		//设置默认状态：未验收
		purchaseBillDetail.setStatus(PurchaseBillDtlStatus.NEW);
		//设置关联的采购单
		purchaseBillDetail.setPurchaseBill(purchaseBill);
		
		this.purchaseBillDetailManager.storePurchaseBillDetail(purchaseBillDetail);
		if(isNew){
			this.addActionMessage(this.getText("toolingMakeDetail.add.success", Arrays
					.asList(new Object[] { purchaseBillDetail.getName() })));
			return NEW;
		}else{
			this.addActionMessage(this.getText("toolingMakeDetail.edit.success", Arrays
					.asList(new Object[] { purchaseBillDetail.getName() })));
			return SUCCESS;
		}
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
	public List getCategorys() {
		return this.toolingTypeManager.loadAllToolingTypes();
	}
	/**
	 * 获取工装计量单位集合
	 * @return List 工装计量单位
	 */
	public List getCalUnits() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.PRICKLE);
	}
	public PurchaseBill getPurchaseBill() {
		return purchaseBill;
	}
	public void setPurchaseBill(PurchaseBill purchaseBill) {
		this.purchaseBill = purchaseBill;
	}
	public PurchaseBillDetail getPurchaseBillDetail() {
		return purchaseBillDetail;
	}
	public void setPurchaseBillDetail(PurchaseBillDetail purchaseBillDetail) {
		this.purchaseBillDetail = purchaseBillDetail;
	}

}
