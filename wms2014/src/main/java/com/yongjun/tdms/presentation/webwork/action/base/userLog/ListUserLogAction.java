package com.yongjun.tdms.presentation.webwork.action.base.userLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.log.EventLog;
import com.yongjun.tdms.service.base.log.UserLogManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title: ListWorkWarnningAction
 * <p>Description: 用户日志查询页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zsmuig@yj-technology.com
 * @version $Id:$
 */

public class ListUserLogAction extends ValueListAction {
	private static final long serialVersionUID = 326722012368469501L;
	
	private final UserLogManager userLogManager;
	private final DepartmentManager departmentManager;
	private List<EventLog> eventLogs;
	
	public ListUserLogAction(UserLogManager userLogManager,DepartmentManager departmentManager) {
		this.userLogManager = userLogManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 为执行excute()执行准备数据
	 */
	public void prepare() {
		if (this.hasIds("userLogIds")) {
			this.eventLogs = this.userLogManager.loadAllUserLog(this.getIds("userLogIds"));
		}
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 */
	@Override
	protected String getAdapterName() {
		return "userLog";
	}
	
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
}