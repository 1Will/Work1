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
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * <p>Title:EditPurchaseToolingMakeDetailAction
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: EditPurchaseToolingMakeDetailAction.java 2008-12-7 16:22:02 yli$
 */
public class EditPurchaseToolingMakeDetailAction extends PrepareAction {
	private static final long serialVersionUID = -5771141136224646050L;
	private Subscribe subscribe;
	private SubscribeDetail subscribeDtl;
	private final SubscribeManager subscribeManager;
	private final ToolingTypeManager toolingTypeManager;
	private final CodeValueManager codeValueManager;
	/**
	 * 构造函数
	 * @param subscribeManager
	 */
	public EditPurchaseToolingMakeDetailAction(SubscribeManager subscribeManager,ToolingTypeManager toolingTypeManager,
			CodeValueManager codeValueManager){
		this.subscribeManager = subscribeManager;
		this.toolingTypeManager = toolingTypeManager;
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() throws Exception {
		//工装制作明细所关联的采购单
		if(this.hasId("subscribe.id")){
			this.subscribe = subscribeManager.loadSubscribe(this.getId("subscribe.id"));
		}
		if(this.hasId("subscribeDtl.id")){
			this.subscribeDtl = subscribeManager.loadSubscribeDetail(this.getId("subscribeDtl.id"));
		}else{
			subscribeDtl = new SubscribeDetail();
		}
	}
	
	public String save(){
		boolean isNew = this.subscribeDtl.isNew();
		
		//设置工装
		if(this.hasId("category.id")){
			ToolingType toolingType = this.toolingTypeManager.loadToolingType(this.getId("category.id"));
			//设置工装类别ID
			subscribeDtl.setToolingCategory(toolingType);
			//设置工装类别名称
			subscribeDtl.setCategoryName(toolingType.getValue());
		}
		//设置计量单位
		if(this.hasId("calUnit.id")){
			subscribeDtl.setCalUnit(this.codeValueManager.loadCodeValue(this.getId("calUnit.id")));
		}
		//设置采购单明细类别[工装制造明细]
		subscribeDtl.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);
		//设置关联的采购单
		subscribeDtl.setSubscribe(subscribe);
		
		this.subscribeManager.storeSubscribeDetail(subscribeDtl);
		if(isNew){
			this.addActionMessage(this.getText("toolingMakeDetail.add.success", Arrays
					.asList(new Object[] { subscribeDtl.getName() })));
			return NEW;
		}else{
			this.addActionMessage(this.getText("toolingMakeDetail.edit.success", Arrays
					.asList(new Object[] { subscribeDtl.getName() })));
			return SUCCESS;
		}
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
	/*----------------setter、getter方法------------------*/
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}

	public SubscribeDetail getSubscribeDtl() {
		return subscribeDtl;
	}

	public void setSubscribeDtl(SubscribeDetail subscribeDtl) {
		this.subscribeDtl = subscribeDtl;
	}
}
