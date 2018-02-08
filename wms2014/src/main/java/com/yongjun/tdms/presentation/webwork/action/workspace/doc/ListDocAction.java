package com.yongjun.tdms.presentation.webwork.action.workspace.doc;

import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;
import com.yongjun.tdms.workflow.service.doctype.WfDocTypeManager;

/**
 * @author qs
 * @version $Id: ListDocAction.java 8726 2007-11-28 09:51:48Z qsun $
 */
public class ListDocAction extends ValueListAction {
	private static final long serialVersionUID = -3004368700479295855L;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final WfDocTypeManager wfDocTypeManager;
	private final WfDocStateManager wfDocStateManager;
	
	public ListDocAction(UserManager userManager,
			DepartmentManager departmentManager,
			WfDocTypeManager wfDocTypeManager,
			WfDocStateManager wfDocStateManager) {
		this.userManager = userManager;
		this.departmentManager = departmentManager;
		this.wfDocStateManager = wfDocStateManager;
		this.wfDocTypeManager = wfDocTypeManager;
	}
	
	public User getUser() {
		return userManager.getUser();
	}
	
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	public List getDocTypes() {
		return wfDocTypeManager.createSelectDocType(this.getText("select.option.all"));
	}
	
	public List getDocStates() {
		return wfDocStateManager.createSelectDocStates(this.getText("select.option.all"));
	}
	
	@Override
	protected String getAdapterName() {
		return "myApprovedDocs";
	}

}
