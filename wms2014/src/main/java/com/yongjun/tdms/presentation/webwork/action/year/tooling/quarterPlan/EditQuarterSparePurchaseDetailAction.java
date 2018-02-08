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
package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;

/**
 * <p>Title: EditQuarterSparePurchaseDetailAction
 * <p>Description: 季度计划备件采购维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class EditQuarterSparePurchaseDetailAction extends PrepareAction {

	private static final long serialVersionUID = 1344421785280922371L;

	private final QuarterPlanDetailManager sparePurchaseDetaiManager;
	private final CodeValueManager codeValueManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final QuarterPlanManager quarterPlanManager;
	private final SpareTypeManager spareTypeManager;
	
	//备件采购明细
	private QuarterPlanDetail sparePurchaseDetail;
	//季度计划
	private QuarterPlan quarterPlan;
	
	public EditQuarterSparePurchaseDetailAction(QuarterPlanDetailManager sparePurchaseDetaiManager,
			CodeValueManager codeValueManager,
			SpareDetailTypeManager spareDetailTypeManager,
			QuarterPlanManager quarterPlanManager,
			SpareTypeManager spareTypeManager) {
		this.sparePurchaseDetaiManager = sparePurchaseDetaiManager;
		this.codeValueManager = codeValueManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.quarterPlanManager = quarterPlanManager;
		this.spareTypeManager = spareTypeManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.sparePurchaseDetail) {
			if (this.hasId("sparePurchaseDetail.id")) { //如果请求中有"sparePurchaseDetail.id",则根据"sparePurchaseDetail.id"获取备件采购明细
				this.sparePurchaseDetail = this.sparePurchaseDetaiManager.loadQuarterPlanDetail(this.getId("sparePurchaseDetail.id"));
			} else {                                    //如果请求中没有"sparePurchaseDetail.id",则创建备件采购明细
				this.sparePurchaseDetail = new QuarterPlanDetail();
			}
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
	}
	
	/**
	 * 如果点击保存按钮，保存备件采购明细
	 * @return
	 */
	public String save() {
		boolean isNew = this.sparePurchaseDetail.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("category.id"))) {   //设置备件大类
//			CodeValue category = this.codeValueManager.loadCodeValue(this.getId("category.id"));
//			this.sparePurchaseDetail.setCategory(category);
//			this.sparePurchaseDetail.setCategoryName(category.getValue());
			SpareType category = this.spareTypeManager.loadSpareType(this.getId("category.id"));
			this.sparePurchaseDetail.setCategory(category);
			this.sparePurchaseDetail.setCategoryName(category.getName());
		}
		if (!StringUtils.isEmpty(request.getParameter("detailCategory.id"))) {
			SpareDetailType detailType = this.spareDetailTypeManager.loadSpareDetailType(this.getId("detailCategory.id"));
			this.sparePurchaseDetail.setDetailCategory(detailType);
			this.sparePurchaseDetail.setDetailCategoryName(detailType.getName());
		}
		sparePurchaseDetail.setQuarterPlan(this.quarterPlan);  //设置备件采购明细关联的季度计划
		sparePurchaseDetail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
		this.sparePurchaseDetaiManager.storeQuarterPlanSparePurchaseDetail(this.sparePurchaseDetail);
		
		if (isNew) {
			this.addActionMessage(this.getText("sparePurchaseDetail.add.success", Arrays
					.asList(new Object[] { sparePurchaseDetail.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("sparePurchaseDetail.edit.success", Arrays
					.asList(new Object[] { sparePurchaseDetail.getName() })));
			return SUCCESS;
		}
	}
	
	/**
	 * 获取备件大分类
	 */
	@SuppressWarnings("unchecked")
	public List getSpareType() {
		List allSpareTypeList = new ArrayList();
		List spareTypeList = new ArrayList();
		allSpareTypeList =codeValueManager.LoadAllValuesByCode(CodeConstants.SPARE_TYPE);
		for (int i=0;i<allSpareTypeList.size();i++){        //区分是工装[TOOLING]或设备[DEVICE]
			if (((CodeValue)allSpareTypeList.get(i)).getRealCode().toString().equals(SysModel.TOOLING.toString())){
				spareTypeList.add(allSpareTypeList.get(i));
			}
		}
		return spareTypeList;
	}
	
	/**
	 * 获取备件明细分类
	 * @return
	 */
	public List getSpareDetailType (){
		return spareDetailTypeManager.loadAllSpareDetailTypes();
	}

	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

	public QuarterPlanDetail getSparePurchaseDetail() {
		return sparePurchaseDetail;
	}

	public void setSparePurchaseDetail(QuarterPlanDetail sparePurchaseDetail) {
		this.sparePurchaseDetail = sparePurchaseDetail;
	}


}
