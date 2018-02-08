/**
 * 
 */
package com.yongjun.tdms.presentation.webwork.action.year.tooling.quarterPlan;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * @author zhangzhibao
 *
 */
public class ListQuarterSparePurchaseDetailAction extends ValueListAction {
	private static final long serialVersionUID = 3253098083954735628L;

	private final Log log = LogFactory.getLog(getClass());
	
	private final QuarterPlanDetailManager sparePurchaseDetailManager;
	private final QuarterPlanManager quarterPlanManager;
	private final BudgetDetailManager budgetDetailManager;
	
	//备件采购明细集合
	private List<QuarterPlanDetail> sparePurchaseDetail;
	//季度计划
	private QuarterPlan quarterPlan;
	//新建备件标识
	private static final String ADD_SPARES = "addSpares";
	//选中的年度计划的备件采购明细ID集合
	private String yearSparePurchaseDetailIds;
	//新建的备件ID
	private String addSpareIds;
	//备件采购明细id
	private String allSparePurchaseDetailId;
	//备件采购明细数量
	private String allNumber;
	//备件采购明细需求日期
	private String allRequestDate;
	//备件采购明细需求原因
	private String allRequestReason;
	//备件采购明细备注
	private String allComment;
	//备件采购明细单价
	private String allUnitPrice;
	
	public ListQuarterSparePurchaseDetailAction(QuarterPlanDetailManager sparePurchaseDetailManager,
			QuarterPlanManager quarterPlanManager,
			BudgetDetailManager budgetDetailManager) {
		this.sparePurchaseDetailManager = sparePurchaseDetailManager;
		this.quarterPlanManager = quarterPlanManager;
		this.budgetDetailManager = budgetDetailManager;
	}
	
	/**
	 * 为其他方法执行准备数据
	 */
	public void prepare() {
		if (this.hasId("quarterPlan.id")) {               //如果请求中包含"quarterPlan.id",则根据"quarterPlan.id"获取季度度计划对象
			this.quarterPlan = this.quarterPlanManager.loadQuarterPlan(this.getId("quarterPlan.id"));
		}
		if (this.hasIds("sparePurchaseDetailIds")) { //如果请求中包含"sparePurchaseDetailIds",则根据"sparePurchaseDetailIds"获取年度计划对象集合   
			this.sparePurchaseDetail = this.sparePurchaseDetailManager.loadAllQuarterPlanDetails(this.getIds("sparePurchaseDetailIds"));
		}
		if (!StringUtils.isEmpty(request.getParameter("yearSparePurchaseDetailIds"))) {
			//获取请求中从年度计划中选择的备件采购明细ID
			this.yearSparePurchaseDetailIds = request.getParameter("yearSparePurchaseDetailIds");
		}
		if (null == this.addSpareIds) {
			if (!StringUtils.isEmpty(request.getParameter("addSpareIds"))) {
				this.addSpareIds = request.getParameter("addSpareIds");
				log.debug("spareIds " + addSpareIds);
			}
		}
		this.setFirst(false);
	}
	
	/**
	 * 1、当页面点击删除按钮,则删除选中的备件采购明细
	 * 2、当页面点击从年度计划中选择按钮,则从年度计划的备件采购明细中创建季度计划的备件采购明细
	 */
	public String execute() {
		//如果页面点击的删除按钮,则执行删除操作
		if (this.isDelete()) {
			return delete();
		}
		//点击新建触发
		if (this.isAddSpares()) {
			return saveNewAddSpare();
		}
		//点击保存按钮，更新备件采购明细的数量，需求日期，需求原因，备注的值
		if (this.isSave()) {
			return storeSparePurchaseDetail();
		}
		//当页面点击从年度计划中选择按钮,则从年度计划的备件采购明细中创建季度计划的备件采购明细
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
	 * 判断是否点击了保存按钮
	 * @return true | false
	 */
	private boolean isSave() {
		return this.hasKey("save");
	}
	/**
	 * 判断页面传来的addSpare变量是否有值，且值是否等于ADD_SPARES
	 * 
	 * @return boolean true 添加备件|false 不添加
	 */
	private boolean isAddSpares() {
		if (!StringUtils.isEmpty(request.getParameter("addSpares"))) {
			if (request.getParameter("addSpares").equals(ADD_SPARES)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 从年度计划的备件采购明细中创建季度计划的备件采购明细
	 * @return
	 */
	public String createQuarterPlanFromYearPlan() {
		this.sparePurchaseDetailManager.storeQuarterPlanSparePurchaseDetail(this.quarterPlan, this.yearSparePurchaseDetailIds);
		return SUCCESS;
	}
	
	/**
	 * 更新备件采购明细的数量，需求日期，需求原因，备注的值
	 * @return SUCCESS
	 */
	public String storeSparePurchaseDetail() {
		//获取备件采购明细的id
		if (!StringUtils.isEmpty(request.getParameter("allSparePurchaseDetailId"))) {
			this.allSparePurchaseDetailId = request.getParameter("allSparePurchaseDetailId");
		}
		//获取备件采购明细的数量
		if (!StringUtils.isEmpty(request.getParameter("allNumber"))) {
			this.allNumber = request.getParameter("allNumber");
		}
		//获取备件采购明细的需求日期
		if (!StringUtils.isEmpty(request.getParameter("allRequestDate"))) {
			this.allRequestDate = request.getParameter("allRequestDate");
		}
		//获取备件采购明细的需求原因
		if (!StringUtils.isEmpty(request.getParameter("allRequestReason"))) {
			this.allRequestReason = request.getParameter("allRequestReason");
		}
		//获取备件采购明细的备注
		if (!StringUtils.isEmpty(request.getParameter("allComment"))) {
			this.allComment = request.getParameter("allComment");
		}
		//获取备件采购明细的备注
		if (!StringUtils.isEmpty(request.getParameter("allUnitPrice"))) {
			this.allUnitPrice = request.getParameter("allUnitPrice");
		}
		this.sparePurchaseDetailManager.storeSpareForQuarterPlan(quarterPlan,allSparePurchaseDetailId,allNumber,
				allRequestDate, allRequestReason, allComment,allUnitPrice);
		return SUCCESS;
	}
	/**
	 * 删除页面选中的备件采购明细
	 * @return SUCCESS
	 */
	public String delete() {                                        
	    //删除季度计划所关联的备件采购明细,如果备件采购明细已被制作为采购单，则不能删除
		try {
			this.sparePurchaseDetailManager.deleteAllSparePurchaseDetailOfQuarterPlan(this.sparePurchaseDetail, this.quarterPlan);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 保存新添加的备件
	 * @return
	 */
	public String saveNewAddSpare() {
		this.sparePurchaseDetailManager.storeNewAddSpareForQuarterPlan(quarterPlan,this.addSpareIds);
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {                                           
		return "quarterSparePurchaseDetails";
	}

	/**
	 * 往查询备件采购明细的hql语句中设置年度计划或季度计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
         //如果是季度计划
        if (this.hasId("quarterPlan.id")){
			map.put("quarterPlan.id", this.getId("quarterPlan.id"));
		}
		return map;
	}

	public QuarterPlan getQuarterPlan() {
		return quarterPlan;
	}

	public void setQuarterPlan(QuarterPlan quarterPlan) {
		this.quarterPlan = quarterPlan;
	}
	//获取超出年度预算的费用
	public Double getOverBudgetFee() {
		if (null != this.quarterPlan) {
			//获取该部门的年度预算明细
			BudgetDetail budgetDetail = this.budgetDetailManager.getBudgetDetail(quarterPlan.getDepartment(),
					quarterPlan.getYear(), YearPlanDetailCategory.SPARE_PURCHASE.toString());
			if (null != budgetDetail) {
				if (budgetDetail.getQuarterPlanFee() > budgetDetail.getFee()) {
					return budgetDetail.getQuarterPlanFee() - budgetDetail.getFee();
				}
			}
		}
		return null;
	}

}
