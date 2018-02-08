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
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcDetailManager;

/**
 * @author qs
 * @version $Id: ListCheckPointProcDetailAction.java 8881 2007-12-02 03:05:28Z qsun $
 */
public class ListCheckPointProcDetailAction extends ValueListAction {
	private static final long serialVersionUID = 6916464679474192883L;

	private final CheckPointProcDetailManager checkPointProcDetailManager;

	private List<CheckPointProcDetail> checkPointProcDetail;

	private CheckPointProc proc;

	Long procId;

	public ListCheckPointProcDetailAction(
			CheckPointProcDetailManager checkPointProcDetailManager) {
		this.checkPointProcDetailManager = checkPointProcDetailManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("proc.id")) {
			procId = this.getId("proc.id");
		}
		if (null == this.checkPointProcDetail
				&& this.hasId("checkPointProcDetailIds")) {
			this.checkPointProcDetail = this.checkPointProcDetailManager
					.loadAllCheckPointProcDetail(this
							.getIds("checkPointProcDetailIds"));
			// this.deviceId =
			// String.valueOf(this.attachTools.get(0).getDevice().getId());
		}
	}
	
	public String execute() {
		if (this.isDelete()) {
			delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		//this.device.getAttachTool().removeAll(attachTools);
		this.checkPointProcDetailManager.deleteAllCheckPointProcDetail(checkPointProcDetail);
		//this.checkPointProcManager.storeCheckPointProc(proc);
		this.addActionMessage(this.getText("attachTool.delete.success"));
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "checkPointProcs";
	}

	public CheckPointProc getProc() {
		return proc;
	}

	public void setProc(CheckPointProc proc) {
		this.proc = proc;
	}

	public List<CheckPointProcDetail> getCheckPointProcDetail() {
		return checkPointProcDetail;
	}

	public void setCheckPointProcDetail(List<CheckPointProcDetail> checkPointProcDetail) {
		this.checkPointProcDetail = checkPointProcDetail;
	}
}
