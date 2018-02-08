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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.device.lubrication;

import java.util.List;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlan;
import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationPlanStatus;


/**
 * <p>Title: ListLubricationProcAction
 * <p>Description: 润滑实施列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: ListLubricationProcAction.java 10415 2008-01-16 01:52:19Z mwei $
 */
public class ListLubricationProcAction extends ValueListAction {
	private static final long serialVersionUID = -1130945198078325553L;

	//private final DepartmentManager departmentManager;

	//private final LubricationProcManager lubricationProcManager;

	private String lubricationPlanInfo;
	
	private String lubricationProcInfo;

	private List<LubricationPlan> lubricationProcs;

	public ListLubricationProcAction() {
		//this.departmentManager = departmentManager;
		//this.lubricationProcManager = lubricationProcManager;
	}

	public void prepare() {
		if (this.lubricationPlanInfo == null) {
			if (this.hasId("lubricationPlanInfo")) {
				this.lubricationPlanInfo = (String) request
						.getParameter("lubricationPlanInfo");
			} else {
				this.lubricationPlanInfo = "";
			}
		}
		
		if (this.lubricationProcInfo == null) {
			if (this.hasId("lubricationProcInfo")) {
				this.lubricationProcInfo = (String) request
						.getParameter("lubricationProcInfo");
			} else {
				lubricationProcInfo = "";
			}
		}

		/*
		if (this.lubricationProcs == null && this.hasIds("lubricationProcIds")) {
			this.lubricationProcs = this.lubricationProcManager
					.loadAllLubricationProc(this.getIds("lubricationProcIds"));
		}
		*/
	}

	public String execute() {
		if (isSaveLubricationProc()) {
			return saveLubricationProc();
		}
		
		if(isSaveLubrication()){
			return modify();
		}
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}

	private boolean isSaveLubricationProc() {
		if (this.lubricationPlanInfo.equals("")) {
			return false;
		}
		return true;
	}

	private String saveLubricationProc() {
		//this.lubricationProcManager.storeLubricationProc(lubricationPlanInfo);
		this.addActionMessage(this.getText("lubricationproc.add.success"));
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "lubricationProc";
	}

	public List getDepartments() {
		//return departmentManager.createSelectDepartments(this
				//.getText("select.option.all"));
		return null;
	}

	public String getLubricationPlanInfo() {
		return lubricationPlanInfo;
	}

	public void setLubricationPlanInfo(String lubricationPlanInfo) {
		this.lubricationPlanInfo = lubricationPlanInfo;
	}

	private String delete() {
		try {
			//this.lubricationProcManager.deleteAllLubricationProc(lubricationProcs);
			this.addActionMessage(this
					.getText("lubricationProc.delete.success"));
		} catch (Exception ex) {
			ex.printStackTrace();
			this.addActionMessage(this
					.getText("lubricationProc.delete.failure"));
		}
		return SUCCESS;
	}
	
	private boolean isSaveLubrication() {
		return this.hasKey("save");
	}
	
	private String modify(){
		if (!this.lubricationProcInfo.equals("")) {
			//this.lubricationProcManager.modifyLubricationProc(lubricationProcInfo);
			this.addActionMessage(this
					.getText("lubricationproc.modify.success"));
		}
		return SUCCESS;
	}

	public List<LubricationPlan> getLubricationProcs() {
		return lubricationProcs;
	}

	public void setLubricationProcs(List<LubricationPlan> lubricationProcs) {
		this.lubricationProcs = lubricationProcs;
	}

	public String getLubricationProcInfo() {
		return lubricationProcInfo;
	}

	public void setLubricationProcInfo(String lubricationProcInfo) {
		this.lubricationProcInfo = lubricationProcInfo;
	}
	
	public LabelValue[] getLubricationPlanStatus() {
		return this.wrapEnum(LubricationPlanStatus.class);
	}
}
