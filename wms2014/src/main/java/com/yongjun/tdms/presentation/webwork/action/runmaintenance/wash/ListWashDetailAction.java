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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.wash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairModel;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetailResult;
import com.yongjun.tdms.service.base.product.ProductManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashDetailManager;
import com.yongjun.tdms.service.runmaintenance.wash.WashManager;

/**
 * <p>Title: ListWashDetailAction
 * <p>Description: 清洗计划或实施明细列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListWashDetailAction.java 10851 2008-02-01 09:39:51Z zbzhang $
 */
public class ListWashDetailAction extends ValueListAction {
	private static final long serialVersionUID = 369851130668698200L;
	private final Log log = LogFactory.getLog(getClass());;
	
	private final WashDetailManager washDetailManager;
	private final WashManager washManager;
	private final ProductManager productManager;
	
	private Wash wash;
	private List<WashDetail> washDetails;
	private String planProcFlag;                   //计划或实施标识
	private String addToolingIds;                  //新添加的工装ID字符串
	private String allWashDetailId;                //明细列表中所有的ID值
	private String allPlanWashDate;                //明细列表中所有非空的计划清洗日期的值，以及其相对应的清洗明细ID值
	private String allDutyPeople;                  //明细列表中所有非空的负责人的值，以及其相对应的清洗明细ID值
	private String allSupervisePeople;             //明细列表中所有非空的监督人的值，以及其相对应的清洗明细ID值
	private String allProductModel;                //明细列表中所有非空的产品型号的值，以及其相对应的清洗明细ID值
	private String allComment;                     //明细列表中所有非空的备注的值，以及其相对应的清洗明细ID值
	private String allWashResult;                   //明细列表中所有非空的清洗结果的值，以及其相对应的清洗明细ID值
	
	public ListWashDetailAction(WashDetailManager washDetailManager,
			WashManager washManager,
			ProductManager productManager) {
		this.washDetailManager = washDetailManager;
		this.washManager = washManager;
		this.productManager = productManager;
	}

	public void prepare() {
		if (this.hasId("wash.id")) {
			this.wash = this.washManager.loadWash(this.getId("wash.id"));
		}
		if (this.hasIds("washDetailIds")) {
			this.washDetails = this.washDetailManager.loadAllWashDetails(this.getIds("washDetailIds"));
		}
		if (null == this.addToolingIds) {
			if (!StringUtils.isEmpty(request.getParameter("addToolingIds"))) {
				this.addToolingIds = request.getParameter("addToolingIds");
				log.debug("addToolingIds is " + this.addToolingIds);
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isAddToolingWash()) {
			return this.saveAddToolingWash();
		}
		if (this.isSave()) {
			return save();
		}
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	/**
	 * 判断页面传来的addTooling变量是否有值，且值是否等于addToolings
	 * 
	 * @return boolean true 添加新的工装 | false 不添加
	 */
	private boolean isAddToolingWash() {
		if (!StringUtils.isEmpty(request.getParameter("addTooling"))) {
			if (request.getParameter("addTooling").equals("addToolings"))
				return true;
		}
		return false;
	}
	
	/**
	 * 判断页面是否点击保存按钮
	 * @return true | false
	 */
	private boolean isSave() {
		return this.hasKey("save");
	}
	
	/**
	 * 保存新添加的工装为清洗计划明细
	 * @return SUCCESS
	 */
	public String saveAddToolingWash() {
		this.washDetailManager.storeWashDetail(wash,addToolingIds);
		return SUCCESS;
	}
	
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("allWashDetailId"))) {
			this.allWashDetailId = request.getParameter("allWashDetailId");
		}
		if (!StringUtils.isEmpty(request.getParameter("allPlanWashDate"))) {
			this.allPlanWashDate = request.getParameter("allPlanWashDate");
		}
		if (!StringUtils.isEmpty(request.getParameter("allDutyPeople"))) {
			this.allDutyPeople = request.getParameter("allDutyPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("allSupervisePeople"))) {
			this.allSupervisePeople = request.getParameter("allSupervisePeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("allProductModel"))) {
			this.allProductModel = request.getParameter("allProductModel");
		}
		if (!StringUtils.isEmpty(request.getParameter("allComment"))) {
			this.allComment = request.getParameter("allComment");
		}
		if (!StringUtils.isEmpty(request.getParameter("allWashResult"))) {
			this.allWashResult = request.getParameter("allWashResult");
		}
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			this.washDetailManager.storeWashDetail(allWashDetailId, allPlanWashDate,
					allDutyPeople, allSupervisePeople, allProductModel, allComment);
		} else {
			this.washDetailManager.storeWashDetail(allWashDetailId, allPlanWashDate, allWashResult);
		}

		return SUCCESS;
	}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
			return "washPlanDetails";
		} else {
			return "washProcDetails";
		}
	}
	
	public String delete() {
		try {
			this.washDetailManager.delteAllWashDetailPlans(washDetails);
		} catch (Exception e) {
			this.addActionMessage(this.getText("delete.washPlanDetail.failure"));
			return ERROR;
		}
		this.addActionMessage(this.getText("delete.washPlanDetail.success"));
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("wash.id")){
        	if (this.planProcFlag.equals(PreRepairModel.PLAN.toString())) {
        		map.put("washPlan.id",this.getId("wash.id"));	
        	} else {
        		map.put("washProc.id",this.getId("wash.id"));
        	}
		}
		return map;
	}

	public Wash getWash() {
		return wash;
	}

	public void setWash(Wash wash) {
		this.wash = wash;
	}
	
	/**
	 * 获取产品型号的所有值
	 * @return List 产品型号的集合
	 */
	public List<Product> getProductModels() {
		return this.productManager.createSelectProducts(this.getText(""));
	}
	
	/**
	 * 获取清洗结果的所有值
	 * @return List 清洗结果集合
	 */
	public List getWashResults() {
		LabelValue[] arrays = this.wrapEnum(WashDetailResult.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

}
