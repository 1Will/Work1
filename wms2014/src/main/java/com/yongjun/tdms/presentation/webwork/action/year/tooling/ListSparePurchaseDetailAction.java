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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlan;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetail;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.year.tooling.SparePurchaseDetailManager;
import com.yongjun.tdms.service.year.tooling.quarterPlan.QuarterPlanManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanDetailManager;
import com.yongjun.tdms.service.year.tooling.yearPlan.YearPlanManager;

/**
 * 
 * <p>Title: ListSparePurchaseDetailAction
 * <p>Description: 备件采购明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListSparePurchaseDetailAction extends ValueListAction {
	private static final long serialVersionUID = 144841271015840587L;
	private final Log log = LogFactory.getLog(getClass());
	
	private final YearPlanDetailManager sparePurchaseDetailManager;
	private final YearPlanManager yearPlanManager;
	private final QuarterPlanManager quarterPlanManager;
	
	//年度计划
	private YearPlan yearPlan;
	//备件采购明细集合
	private List<YearPlanDetail> sparePurchaseDetail;
	//季度计划
	private QuarterPlan quarterPlan;
	//年度计划与季度计划标识[YEAR_PLAN(年度计划)][QUARTER_PLAN()]
	private String yearQuarterFlag = "YEAR_PLAN";
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
	
	public ListSparePurchaseDetailAction(YearPlanDetailManager sparePurchaseDetailManager,
			YearPlanManager yearPlanManager,
			QuarterPlanManager quarterPlanManager) {
		this.sparePurchaseDetailManager = sparePurchaseDetailManager;
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
		if (this.hasIds("sparePurchaseDetailIds")) { //如果请求中包含"sparePurchaseDetailIds",则根据"sparePurchaseDetailIds"获取年度计划对象集合   
			this.sparePurchaseDetail = this.sparePurchaseDetailManager.loadAllYearPlanDetails(this.getIds("sparePurchaseDetailIds"));
		}
		if (this.hasId("yearQuarterFlag")) {          //根据请求中的"yearQuarterFlag"参数的值,来设置年度计划与季度计划标识
			this.yearQuarterFlag = request.getParameter("yearQuarterFlag");
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
		//this.sparePurchaseDetailManager.storeQuarterPlanSparePurchaseDetail(this.quarterPlan, this.yearSparePurchaseDetailIds);
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
		this.sparePurchaseDetailManager.storeSpareForYearPlan(yearPlan,allSparePurchaseDetailId,allNumber,
				allRequestDate, allRequestReason, allComment,allUnitPrice);
		return SUCCESS;
	}
	/**
	 * 删除页面选中的备件采购明细
	 * @return SUCCESS
	 */
	public String delete() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //年度计划
            //如果是年度计划,删除年度计划所关联的备件采购明细
			try {
				this.sparePurchaseDetailManager.deleteAllSparePurchaseDetailOfYearPlan(this.sparePurchaseDetail, this.yearPlan);
			} catch (Exception e) {
				return ERROR;
			}
		} else {                                           //季度计划
			//如果是季度计划,删除季度计划所关联的备件采购明细
			//this.sparePurchaseDetailManager.deleteAllSparePurchaseDetailOfQuarterPlan(this.sparePurchaseDetail, this.quarterPlan);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 保存新添加的备件
	 * @return
	 */
	public String saveNewAddSpare() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //年度计划
			this.sparePurchaseDetailManager.storeNewAddSpareForYearPlan(yearPlan,this.addSpareIds);
		} else {
			
		}
		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //年度计划
			return "yearSparePurchaseDetails";
		} else {                                            //季度计划 
			return "quarterSparePurchaseDetails";
		}
		
	}

	/**
	 * 往查询备件采购明细的hql语句中设置年度计划或季度计划的ID值
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.yearQuarterFlag.equals("YEAR_PLAN")) {     //如果是年度计划
	        if (this.hasId("yearPlan.id")){
				map.put("yearPlan.id", this.getId("yearPlan.id"));
			}
		} else {                                          //如果是季度计划
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
