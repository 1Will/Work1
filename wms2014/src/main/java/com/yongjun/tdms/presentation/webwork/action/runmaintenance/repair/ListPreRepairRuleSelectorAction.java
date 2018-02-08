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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.ManagementLevel;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: ListPreRepairRuleSelectorAction
 * <p>Description: 预防性维修标准选择页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public class ListPreRepairRuleSelectorAction extends ValueListAction {

	private static final long serialVersionUID = -505404382567195489L;
	
	private final DeviceTypeManager deviceTypeManager;
	private final DepartmentManager departmentManager;
	
	public ListPreRepairRuleSelectorAction(DeviceTypeManager deviceTypeManager,
			DepartmentManager departmentManager) {
		this.deviceTypeManager = deviceTypeManager;
		this.departmentManager = departmentManager;
	}
	@Override
	protected String getAdapterName() {
		return "preRepairRuleSelector";
	}
	
	/**
	 * 获取设备类别所有值
	 * @return
	 */
	public List getDeviceTypes() {
		return deviceTypeManager.createSelectDeviceTypes(this
				.getText("select.option.all"));
	}
	
	/**
	 * 获取管理级别所有值
	 * @return
	 */
	public List<LabelValue> getManagementLevels() {
		LabelValue [] arrays = this.wrapEnum(ManagementLevel.class);
		LabelValue array = new LabelValue("-1",this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(array);
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
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

}
