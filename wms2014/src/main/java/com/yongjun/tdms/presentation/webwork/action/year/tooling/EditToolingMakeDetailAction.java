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

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;

import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.ToolingType;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.year.tooling.ToolingMakeDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * <p>Title: EditToolingMakeDetailAction
 * <p>Description: 工装制作详细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class EditToolingMakeDetailAction extends PrepareAction {
	private static final long serialVersionUID = 7860821972841020409L;
	
	private final YearPlanDetailManager yearPlanDetailDetailManager;
	private final YearPlanManager yearPlanManager;
	private final ToolingTypeManager toolingTypeManager;
	private final QuarterPlanManager quarterPlanManager;
	private final CodeValueManager codeValueManager;
	
	//工装制作明细集合
	private YearPlanDetail toolingMakeDetail;
	//年度计划
	private YearPlan yearPlan;
	//季度计划
	private QuarterPlan quarterPlan;
    //年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN(季度计划)]
	private String yearQuarterFlag = "YEAR_PLAN";
	
	public EditToolingMakeDetailAction(YearPlanDetailManager yearPlanDetailDetailManager,
			YearPlanManager yearPlanManager,
			ToolingTypeManager toolingTypeManager,
			QuarterPlanManager quarterPlanManager,
			CodeValueManager codeValueManager) {
		this.yearPlanDetailDetailManager = yearPlanDetailDetailManager;
		this.yearPlanManager = yearPlanManager;
		this.toolingTypeManager = toolingTypeManager;
		this.quarterPlanManager = quarterPlanManager;
		this.codeValueManager = codeValueManager;
	}
	
	/**
	 * 为其他方法准备工装制作,年度计划的数据
	 */
	public void prepare() throws Exception {
		if (null == this.yearPlan) {
			if (this.hasId("toolingMakeDetail.id")) {     //如果请求中有"toolingMakeDetail.id",则根据"toolingMakeDetail.id"获取工装制作详细
				this.toolingMakeDetail = this.yearPlanDetailDetailManager.loadYearPlanDetail(this.getId("toolingMakeDetail.id"));
			} else {                                     //如果请求中没有"toolingMakeDetail.id",则创建工装制作详细
				this.toolingMakeDetail = new YearPlanDetail();
			}
		}
		
		if (this.hasId("yearPlan.id")) {          //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
			this.yearPlan = this.yearPlanManager.loadYearPlan(this.getId("yearPlan.id"));
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
		if (this.hasId("yearQuarterFlag")) {          //根据请求中的"yearQuarterFlag"参数的值,来设置年度计划与季度计划标识
			this.yearQuarterFlag = request.getParameter("yearQuarterFlag");
		}
	}

	/**
	 * 当页面点击保存按钮,则保存工装制作对象
	 * @return SUCCESS
	 */
	public String save() {
		boolean isNew = this.toolingMakeDetail.isNew();
		
		if (this.hasId("category.id")) {      //设置工装类别
			ToolingType toolingType = this.toolingTypeManager.loadToolingType(this.getId("category.id"));
			toolingMakeDetail.setToolingCategory(toolingType);    //设置工装类别id
			toolingMakeDetail.setCategoryName(toolingType.getValue());    //设置工装类别名称
			
		}
		if(this.hasId("calUnit.id")){
			//设置工装计量单位
			toolingMakeDetail.setCalUnit(codeValueManager.loadCodeValue(this.getId("calUnit.id")));
		}
		//设置年度计划明细类别[工装制造明细]
		toolingMakeDetail.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
			//设置关联的年度计划
			toolingMakeDetail.setYearPlan(this.yearPlan);
		}
//		} else {                                           //如果是季度计划
//			//设置关联的季度计划
//			toolingMakeDetail.setQuarterPlan(this.quarterPlan);
//		}

		this.yearPlanDetailDetailManager.storeYearPlanDetail(toolingMakeDetail);
		if (isNew) {
			this.addActionMessage(this.getText("toolingMakeDetail.add.success", Arrays
					.asList(new Object[] { toolingMakeDetail.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("toolingMakeDetail.edit.success",
							Arrays.asList(new Object[] { toolingMakeDetail.getName() })));
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

	public YearPlan getYearPlan() {
		return yearPlan;
	}

	public void setYearPlan(YearPlan yearPlan) {
		this.yearPlan = yearPlan;
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

	public YearPlanDetail getToolingMakeDetail() {
		return toolingMakeDetail;
	}

	public void setToolingMakeDetail(YearPlanDetail toolingMakeDetail) {
		this.toolingMakeDetail = toolingMakeDetail;
	}

}
