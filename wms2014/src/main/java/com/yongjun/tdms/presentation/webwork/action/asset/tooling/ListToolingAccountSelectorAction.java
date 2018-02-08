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
package com.yongjun.tdms.presentation.webwork.action.asset.tooling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeDetailManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.product.ProductManager;

/**
 * <p>Title: ListToolingAccountSelectorAction
 * <p>Description: 工装台帐页面选择访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListToolingAccountSelectorAction extends ValueListAction {

	private static final long serialVersionUID = -2797485825914328083L;

	private final DeviceCardManager deviceCardManager;
	private final ToolingTypeManager toolingTypeManager;
	private final ToolingTypeDetailManager toolingTypeDetailManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final ProductManager productManager;
	private List<DeviceCard> toolings;
	private boolean invalid;
	private boolean includeDisabled;
	private String flag="";	//用于标示是哪个页面进行的查询
	public ListToolingAccountSelectorAction(DeviceCardManager deviceCardManager,
			      ToolingTypeManager toolingTypeManager,
			      ToolingTypeDetailManager toolingTypeDetailManager,
			      DepartmentManager departmentManager,
			      CodeValueManager codeValueManager,
			      ProductManager productManager) {
		this.deviceCardManager = deviceCardManager;
		this.toolingTypeManager = toolingTypeManager;
		this.toolingTypeDetailManager = toolingTypeDetailManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.productManager = productManager;
	}
	
	public void prepare() {
		if (this.hasId("toolingIds")) {
			this.toolings = this.deviceCardManager.loadAllDevices(this.getIds("toolingIds"));
		}
		if (this.hasId("flag")) {
			this.flag = request.getParameter("flag");
		}
	}
	
	public List getToolingTypes() {
		return this.toolingTypeManager.createSelectToolingType(this.getText("select.option.all"));
	}
	
	public List getToolingTypeDetails() {
		return this.toolingTypeDetailManager.createSelectToolingTypeDetail(this.getText("select.option.all"));
	}
	
	public List<CodeValue> getToolingStatus() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"), CodeConstants.TOOLING_STATUS,this.flag);
	}
	
	public List getProducts() {
		return this.productManager.createSelectProducts(this.getText("select.option.all"));
	}
	
	@Override
	protected String getAdapterName() {
		return "toolingSelector";
	}
	
	public List getToolings() {
		return toolings;
	}

	public void setToolings(List<DeviceCard> toolings) {
		this.toolings = toolings;
	}

	public boolean isInvalid() {
		return invalid;
	}

	public void setInvalid(boolean invalid) {
		this.invalid = invalid;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (invalid) {
			map.put("onlyNormalStatus", "placeholder");
		}
		if (!this.hasId("toolingStatus.code")) {
			if (getToolingStateId().size() != 0) {
				map.put("filterToolingStateIds",getToolingStateId());
			}
		}
		return map;
	}

	public List<Long> getToolingStateId() {
		//获取工装状态的所有Id值
		List<Long> allToolingStateIds = this.codeValueManager.LoadAllIdsByCode(CodeConstants.TOOLING_STATUS);
		//获取已经过滤得工装状态集合
		List<CodeValue> toolingStates = getToolingStatus();
		//过滤"所有"值
		toolingStates.remove(0);
		//过滤现有还存在的工装状态ID
		for (CodeValue toolingState : toolingStates) {
			allToolingStateIds.remove(toolingState.getId());
		}
		return allToolingStateIds;
	}
	
	public boolean isIncludeDisabled() {
		return includeDisabled;
	}

	public void setIncludeDisabled(boolean includeDisabled) {
		this.includeDisabled = includeDisabled;
	}
	
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	 public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
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
		 * 获取当前登陆的用户
		 * 
		 * @return User 用户实体
		 */
		public User getLoginUser() {
			return this.userManager.getUser();
		}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
