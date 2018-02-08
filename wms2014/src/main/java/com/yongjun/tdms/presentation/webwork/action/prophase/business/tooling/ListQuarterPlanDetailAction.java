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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.year.tooling.quarterPlan.QuarterType;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

/**
 * <p>Title:ListPurchaseToolingMakeDetailFromQuarterPlanDetail
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListPurchaseToolingMakeDetailFromQuarterPlanDetail.java 2008-12-8 13:27:07 yli$
 */
public class ListQuarterPlanDetailAction extends
		ValueListAction {

	private static final long serialVersionUID = -4237348523959357643L;
	
	private String detailType;							//根据传入的季度计划明细的类别[工装制作|备件采购|维修保养|技术改造]来查询
	private Long subscribeId;							//过滤掉已经选择的记录
	private List<Long> oldQuarterPlanDetailIds;			//已经的记录id集合
	private final DepartmentManager departmentManager;
	private final ToolingTypeManager toolingTypeManager;
	private SubscribeManager subscribeManager;
	public ListQuarterPlanDetailAction(DepartmentManager departmentManager,ToolingTypeManager toolingTypeManager,SubscribeManager subscribeManager){
		this.departmentManager = departmentManager;
		this.toolingTypeManager = toolingTypeManager;
		this.subscribeManager = subscribeManager;
	}
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		//季度计划明细的类别[工装制作|备件采购|维修保养|技术改造]
		if(!StringUtils.isEmpty(request.getParameter("detailType"))){
			detailType = request.getParameter("detailType");
		}
		//传入的采购单id
		if(this.hasId("subscribeId")){
			subscribeId = new Long(request.getParameter("subscribeId"));
		}
	}

	@Override
	protected String getAdapterName() {
		return "quarterPlanDtl";
	}
	
   	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           		//如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {   //如果用户不属于任何部门,置部门ID为-1
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	 
	/**
	 * 获取所有的季度值集合
	 * @return List 季度值集合
	 */
	public List<LabelValue> getQarterTypes() {
		LabelValue [] arrays = this.wrapEnum(QuarterType.class);
		LabelValue array = new LabelValue("-1",this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(array);
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
	public List getCategorys() {
		return this.toolingTypeManager.createSelectToolingType(this.getText("select.option.all"));
	}
	
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	@Override
	protected Map getRequestParameterMap() {
		oldQuarterPlanDetailIds = new ArrayList<Long>();
		if(subscribeId!=null){
			Subscribe subscribe = subscribeManager.loadSubscribe(subscribeId);
			for(SubscribeDetail detail : subscribe.getSubscribeDetails()){
				if(detail.getQuarterPlanDetail()!=null){
					oldQuarterPlanDetailIds.add(detail.getQuarterPlanDetail().getId());
				}
			}
		}
		Map map = super.getRequestParameterMap();
		//根据传入的季度计划明细的类别[工装制作|备件采购|维修保养|技术改造]来查询
        map.put("detailType",detailType);
        if(!oldQuarterPlanDetailIds.isEmpty()){
        	//过滤掉采购单明细中已经存在的记录
        	map.put("subscribeId",oldQuarterPlanDetailIds);
        }
		return map;
	}
}
