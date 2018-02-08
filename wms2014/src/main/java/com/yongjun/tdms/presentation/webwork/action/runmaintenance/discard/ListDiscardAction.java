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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardManager;

/**
 * <p>Title: ListDiscardAction
 * <p>Description: 报废单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListDiscardAction.java 9577 2007-12-20 12:11:00Z wzou $
 */
public class ListDiscardAction extends ValueListAction{
	private static final long serialVersionUID = 4277330858623800893L;
	private final DiscardManager discardManager;
	private final DepartmentManager departmentManager;
	private final ToolingTypeManager toolingTypeManager;
	private final DeviceTypeManager deviceTypeManager;
	private boolean toolingDevFlag; 
	
	private List<Discard> discards;
	
	public ListDiscardAction(
			DiscardManager discardManager,
			DepartmentManager departmentManager,
			ToolingTypeManager toolingTypeManager,
			DeviceTypeManager deviceTypeManager
			){
		this.discardManager=discardManager;
		this.departmentManager=departmentManager;
		this.toolingTypeManager=toolingTypeManager;
		this.deviceTypeManager=deviceTypeManager;
	}
	
	/**
	 * 获取页面参数 <b>toolingDiscardIds</b>，如果得到，就根据ID获取报废单List
	 * 同时根据传递的"toolingDevFlag"来判断是工装还是设备的报废单
	 */
	public void prepare() throws Exception {
		if (this.discards == null && this.hasIds("discardIds")) {
			this.discards = this.discardManager.loadAllDiscards(this
					.getIds("discardIds"));
		}
		if(this.hasId("toolingDevFlag")){
			if(request.getParameter("toolingDevFlag").equals("true")){
				this.toolingDevFlag=true;
			}
			else{
				this.toolingDevFlag=false;
			}
		}
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b>函数处理。
	 */
//	public String execute() throws Exception {
//		if (this.isDelete()) {
//			delete();
//		}
//		return SUCCESS;
//	}
//	
//	/**
//	 * 删除选择的报废单
//	 */
//	public String delete() {
//		try {
//			this.discardManager.deleteAllDiscard(this.discards);
//		} catch (Exception e) {
//			this.addActionMessage(this.getText("discard.delete.error"));
//			return ERROR;
//		}
//
//		this.addActionMessage(this.getText("discard.delete.success"));
//		return SUCCESS;
//	}
	
	
	 public String execute() {
			if (this.isDisabled()) {
				return disabled();
			}
			if (this.isEnabled()) {
				return this.enabled();
			}
			return SUCCESS;
		}
	    public String disabled() {
			this.discardManager.disabledAllDiscards(discards);
			this.addActionMessage(this.getText("discard.disabled.success"));
			return SUCCESS;
		}

		public String enabled() {
			this.discardManager.enabledAllDiscards(discards);
			this.addActionMessage(this.getText("discard.enabled.success"));
			return SUCCESS;
		}
		private boolean isEnabled() {
			return this.hasKey("enabled");
		}
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 * 同时根据"toolingDevFlag"判断是工装还是设备的查询，返回不同的ID
	 */
	@Override
	protected String getAdapterName() {
		if(this.toolingDevFlag){
			return "toolingDiscards";
		}else{
			return "deviceDiscards";
		}
	}
	
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
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
	
	/**
	 * 获得选择的工装类型列表
	 * @return
	 */
	public List getToolingTypes() {
		return this.toolingTypeManager.createSelectToolingType(this.getText("select.option.all"));
	}

	public boolean isToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(boolean toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public DeviceTypeManager getDeviceTypeManager() {
		return deviceTypeManager;
	}

	public DiscardManager getDiscardManager() {
		return discardManager;
	}
}
