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

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.service.year.tooling.ToolingMakeDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * 
 * <p>Title: ListToolingMakeDetailAction
 * <p>Description: 工装制造详细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListToolingMakeDetailAction extends ValueListAction {

	private static final long serialVersionUID = -8302164826693866513L;
	
	private final YearPlanDetailManager yearPlanDetailDetailManager;
	private final YearPlanManager yearPlanManager;
	private final QuarterPlanManager quarterPlanManager;
	
	//工装制作集合
	private List<YearPlanDetail> toolingMakeDetails;
	//年度计划
	private YearPlan yearPlan;
	//季度计划
	private QuarterPlan quarterPlan;
	//年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN()]
	private String yearQuarterFlag = "YEAR_PLAN";
	//选中的年度计划的工装制作明细ID集合
	private String yearToolingMakeDetailIds;
	
	public ListToolingMakeDetailAction(YearPlanDetailManager yearPlanDetailDetailManager, 
			YearPlanManager yearPlanManager,
			QuarterPlanManager quarterPlanManager) {
		this.yearPlanDetailDetailManager = yearPlanDetailDetailManager;
		this.yearPlanManager = yearPlanManager;
		this.quarterPlanManager = quarterPlanManager;
	}
	
	/**
	 * 准备其他方法需要使用的数据,工装制作集合的数据
	 */
	public void prepare() {
		if (this.hasId("yearPlan.id")) {               //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
			this.yearPlan = this.yearPlanManager.loadYearPlan(this.getId("yearPlan.id"));
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
        //如果请求中包含"toolingMakeDetailIds",则根据"toolingMakeDetailIds"获取工装制作详细集合
		if (this.hasIds("toolingMakeDetailIds")) {    
			this.toolingMakeDetails = this.yearPlanDetailDetailManager.loadAllYearPlanDetails(this.getIds("toolingMakeDetailIds"));
		}
		if (this.hasId("yearQuarterFlag")) {          //根据请求中的"yearQuarterFlag"参数的值,来设置年度计划与季度计划标识
			this.yearQuarterFlag = request.getParameter("yearQuarterFlag");
		}
		if (this.hasId("yearToolingMakeDetailIds")) {
			this.yearToolingMakeDetailIds = request.getParameter("yearToolingMakeDetailIds");
		} 
		this.setFirst(false);                
	}
	
	/**
	 * 1、当页面点击删除按钮,则删除选中的工装制作详细
	 * 2、当页面点击从年度计划中选择按钮,则从年度计划的工装制作明细中创建季度计划的工装制作明细
	 */
	public String execute() {
		//删除选定的年度计划的工装制作详细或季度计划的工装制作详细
		if (this.isDelete()) {     
			return delete();
		}
		//把年度计划的工装制作详细拷贝到季度计划的工装制作详细
		if (this.isCopy()) {
			return createQuarterPlanFromYearPlan();
		}
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击了"从年度计划选择的按钮"
	 * @return
	 */
	public boolean isCopy() {
		return this.hasKey("copy");
	}
	
	/**
	 * 判断是否点击了"锁定"按钮
	 * @return true | false
	 */
	private boolean isLocked() {
		return this.hasKey("locked");
	}
	
	/**
	 * 判断是否点击了"解锁"按钮
	 * @return true | false
	 */
	private boolean isUnLocked() {
		return this.hasKey("unLocked");
	}
	
	/**
	 * 从年度计划的工装制作明细中创建季度计划的工装制作明细
	 * @return
	 */
	public String createQuarterPlanFromYearPlan() {
		//this.toolingMakeDetailManager.storeQuarterPlanToolingMakeDetail(this.quarterPlan,yearToolingMakeDetailIds);
		return SUCCESS;
	}
	/**
	 * 删除页面选中的工装制作详细
	 * @return
	 */
	public String delete() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     
             //如果是年度计划,删除年度计划所关联的工装制作明细
			try {
				this.yearPlanDetailDetailManager.deleteAllToolingMakeDetailOfYearPlan(this.toolingMakeDetails,this.yearPlan);
			} catch (Exception e) {
				return ERROR;
			}
			
		} else {
			//如果是季度计划,删除季度计划所关联的工装制作明细
			//this.toolingMakeDetailManager.deleteAllToolingMakeDetailOfQuarterPlan(this.toolingMakeDetails, this.quarterPlan);
		}
		
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
			return "yearToolingMakeDetails";
		} else {                                            //如果是季度计划
			return "quarterToolingMakeDetails";
		}
		
	}
	
	/**
	 * 往查询工装制作详细的hql语句中设置年度计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
	        if (this.hasId("yearPlan.id")){
				map.put("yearPlan.id", this.getId("yearPlan.id"));
			}
		} else {                                           //如果是季度计划
	        if (this.hasId("quarterPlan.id")){
				map.put("quarterPlan.id", this.getId("quarterPlan.id"));
			}
		}
		return map;
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

}
