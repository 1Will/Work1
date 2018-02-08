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
package com.yongjun.tdms.presentation.webwork.action.year.tooling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.year.tooling.SparePurchaseDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * <p>Title: EditSparePurchaseDetailAction
 * <p>Description:  备件采购明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class EditSparePurchaseDetailAction extends PrepareAction {
	private static final long serialVersionUID = 5526591421768579087L;
	
	private final SparePurchaseDetailManager sparePurchaseDetaiManager;
	private final YearPlanManager yearPlanManager;
	private final CodeValueManager codeValueManager;
	private final SpareDetailTypeManager spareDetailTypeManager;
	private final QuarterPlanManager quarterPlanManager;
	
     //	年度计划
	private YearPlan yearPlan;
	//备件采购明细
	private SparePurchaseDetail sparePurchaseDetail;
	//季度计划
	private QuarterPlan quarterPlan;
    //年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN(季度计划)]
	private String yearQuarterFlag = "YEAR_PLAN";
	
	public EditSparePurchaseDetailAction(SparePurchaseDetailManager sparePurchaseDetaiManager,
			YearPlanManager yearPlanManager,
			CodeValueManager codeValueManager,
			SpareDetailTypeManager spareDetailTypeManager,
			QuarterPlanManager quarterPlanManager) {
		this.sparePurchaseDetaiManager = sparePurchaseDetaiManager;
		this.yearPlanManager = yearPlanManager;
		this.codeValueManager = codeValueManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.quarterPlanManager = quarterPlanManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("yearPlan.id")) {           //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
			this.yearPlan = this.yearPlanManager.loadYearPlan(this.getId("yearPlan.id"));
		}
		if (null == this.sparePurchaseDetail) {
			if (this.hasId("sparePurchaseDetail.id")) { //如果请求中有"sparePurchaseDetail.id",则根据"sparePurchaseDetail.id"获取备件采购明细
				this.sparePurchaseDetail = this.sparePurchaseDetaiManager.loadSparePurchaseDetail(this.getId("sparePurchaseDetail.id"));
			} else {                                    //如果请求中没有"sparePurchaseDetail.id",则创建备件采购明细
				this.sparePurchaseDetail = new SparePurchaseDetail();
			}
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
		if (this.hasId("yearQuarterFlag")) {          //根据请求中的"yearQuarterFlag"参数的值,来设置年度计划与季度计划标识
			this.yearQuarterFlag = request.getParameter("yearQuarterFlag");
		}
	}
	/**
	 * 如果点击保存按钮，保存备件采购明细
	 * @return
	 */
	public String save() {
		boolean isNew = this.sparePurchaseDetail.isNew();
		
//		if (this.hasId("category.id")) {                  //设置备件大类
//			this.sparePurchaseDetail.setCategory(this.codeValueManager.loadCodeValue(this.getId("category.id")));
//		} 
//		if (this.hasId("detailCategory.id")) {          //设置备件明细分类
//			this.sparePurchaseDetail.setDetailCategory(this.spareDetailTypeManager.loadSpareDetailType(this.getId("detailCategory.id")));
//		}
		
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
			this.sparePurchaseDetail.setYearPlan(yearPlan);    //设置备件采购明细关联的年度计划
		} else {                                           //如果是季度计划
			sparePurchaseDetail.setQuarterPlan(this.quarterPlan);  //设置备件采购明细关联的季度计划
		}
		this.sparePurchaseDetaiManager.storeSparePurchaseDetail(this.sparePurchaseDetail);
		
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

	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
	}

	public SparePurchaseDetail getSparePurchaseDetail() {
		return sparePurchaseDetail;
	}

	public void setSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail) {
		this.sparePurchaseDetail = sparePurchaseDetail;
	}

	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}

	public String getYearQuarterFlag() {
		return yearQuarterFlag;
	}

	public void setYearQuarterFlag(String yearQuarterFlag) {
		this.yearQuarterFlag = yearQuarterFlag;
	}

}
