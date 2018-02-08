/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.year.tooling;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.service.year.tooling.TechAlterDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * @author Administrator
 *
 */
public class ListTechAlterDetailAction extends ValueListAction {
	private static final long serialVersionUID = -4400117688263488955L;
	
	private final YearPlanDetailManager techAlterDetailManager;
	private final YearPlanManager yearPlanManager;
	private final QuarterPlanManager quarterPlanManager;
	
	//年度计划
	private YearPlan yearPlan;
	//技术改造明细
	private List<YearPlanDetail> techAlterDetails;
	//季度计划
	private QuarterPlan quarterPlan;
	//年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN()]
	private String yearQuarterFlag = "YEAR_PLAN";
	//选中的年度计划的技术改造明细ID集合
	private String yearTechAlterDetailIds;

	public ListTechAlterDetailAction(YearPlanDetailManager techAlterDetailManager,
			YearPlanManager yearPlanManager,
			QuarterPlanManager quarterPlanManager) {
		this.techAlterDetailManager = techAlterDetailManager;
		this.yearPlanManager = yearPlanManager;
		this.quarterPlanManager = quarterPlanManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("yearPlan.id")) {         //如果请求中包含"yearPlan.id",则根据"yearPlan.id"获取年度计划对象
			this.yearPlan = this.yearPlanManager.loadYearPlan(this.getId("yearPlan.id"));
		}
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
		if (this.hasIds("techAlterDetailIds")) { //如果请求中包含"techAlterDetailIds",则根据"techAlterDetailIds"获取技术改造明细集合   
			this.techAlterDetails = this.techAlterDetailManager.loadAllYearPlanDetails(this.getIds("techAlterDetailIds"));
		}
		if (this.hasId("yearQuarterFlag")) {          //根据请求中的"yearQuarterFlag"参数的值,来设置年度计划与季度计划标识
			this.yearQuarterFlag = request.getParameter("yearQuarterFlag");
		}
		if (!StringUtils.isEmpty(request.getParameter("yearTechAlterDetailIds"))) {
			//获取请求中从年度计划中选择的技术改造明细ID
			this.yearTechAlterDetailIds = request.getParameter("yearTechAlterDetailIds");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		//当页面点击从年度计划中选择按钮,则从年度计划的工装制作明细中创建季度计划的工装制作明细
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
	 * 从年度计划的技术改造明细中创建季度计划的技术改造明细
	 * @return
	 */
	public String createQuarterPlanFromYearPlan() {
		//this.techAlterDetailManager.storeQuarterPlanTechAlterDetail(this.quarterPlan, this.yearTechAlterDetailIds);
		return SUCCESS;
	}
	
	/**
	 * 删除页面选中的技术改造明细
	 * @return SUCCESS
	 */
	public String delete() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {    
			//如果是年度计划,删除年度计划所关联的技术改造明细
			try {
				this.techAlterDetailManager.deleteAllTechAlterDetailOfYearPlan(this.techAlterDetails, this.yearPlan);
			} catch (Exception e) {
				return ERROR;
			}
			
		} else {
			//如果是季度计划,删除季度计划所关联的技术改造明细
			//this.techAlterDetailManager.deleteAllTechAlterDetailOfQuarterPlan(this.techAlterDetails, this.quarterPlan);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
			return "yearTechAlterDetails";
		} else {                                           //如果是季度计划
			return "quarterTechAlterDetails";
		}
		
	}
	
	/**
	 * 往查询技术改造详细的hql语句中设置年度计划或季度计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
	        if (this.hasId("yearPlan.id")){
				map.put("yearPlan.id", this.getId("yearPlan.id"));
			}
		} else {                                            //如果是季度计划
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
