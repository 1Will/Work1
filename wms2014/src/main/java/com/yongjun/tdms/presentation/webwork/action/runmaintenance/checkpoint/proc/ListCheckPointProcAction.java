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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.proc;

import java.util.List;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * @author qs
 * @version $Id: ListCheckPointProcAction.java 8881 2007-12-02 03:05:28Z qsun $
 */
public class ListCheckPointProcAction extends ValueListAction {
	private static final long serialVersionUID = 972679594389429771L;

	private final CheckPointProcManager checkPointProcManager;

	private final DepartmentManager departmentManager;
	
	private final WfDocStateManager docStateManager;

	private List<CheckPointProc> checkPointProc;

	public ListCheckPointProcAction(
			CheckPointProcManager checkPointProcManager,
			DepartmentManager departmentManager,
			WfDocStateManager docStateManager) {
		this.checkPointProcManager = checkPointProcManager;
		this.departmentManager = departmentManager;
		this.docStateManager=docStateManager;
	}

	@Override
	protected String getAdapterName() {
		return "checkPointProcs";

	}
	

	public void prepare() throws Exception {
		if (this.checkPointProc == null && this.hasIds("checkPointProcIds")) {
			this.checkPointProc = this.checkPointProcManager.loadAllCheckPointProc(this.getIds("checkPointProcIds"));
		}	
	}
	
	public String execute() throws Exception {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}

	public String delete() {
		try {
			this.checkPointProcManager.deleteAllCheckPointProc(checkPointProc);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("checkPointProc.delete.error"));
		}
		this.addActionMessage(this.getText("checkPointProc.delete.success"));
		return SUCCESS;
	}
	
	public List getCheckPointProc() {
		return checkPointProc;
	}

	public void setCheckPointProc(List<CheckPointProc> checkPointProc) {
		this.checkPointProc = checkPointProc;
	}
	
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	
	public List getDocStates() {
		return docStateManager.createSelectDocStates(this.getText("select.option.all"));
	}
}
