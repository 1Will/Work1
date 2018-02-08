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

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProcDetail;
import com.yongjun.tdms.model.runmaintenance.checkresult.CheckResult;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointProcManager;

/**
 * @author qs
 * @version $Id: EditCheckPointProcDetailAction.java 7961 2007-10-23 07:03:11Z qsun $
 */
public class EditCheckPointProcDetailAction extends PrepareAction {
	private static final long serialVersionUID = -315920952917673049L;
	private Log log = LogFactory.getLog(this.getClass());
	private CheckPointProc proc;
	private CheckPointProcDetail detail;
	private final CheckPointProcManager checkPointProcManager;
	private final CheckPointProcDetailManager checkPointProcDetailManager;

	public CheckPointProcDetail getDetail() {
		return detail;
	}

	public void setDetail(CheckPointProcDetail detail) {
		this.detail = detail;
	}

	public CheckPointProc getProc() {
		return proc;
	}

	public void setProc(CheckPointProc proc) {
		this.proc = proc;
	}

	public EditCheckPointProcDetailAction(
			CheckPointProcManager checkPointProcManager,
			CheckPointProcDetailManager checkPointProcDetailManager) {
		this.checkPointProcManager = checkPointProcManager;
		this.checkPointProcDetailManager = checkPointProcDetailManager;
	}

	public void prepare() throws Exception {
		if (this.proc == null) {
			if (this.hasId("proc.id")) {
				this.proc = this.checkPointProcManager.loadCheckPointProc(this
						.getId("proc.id"));
			} else {
				proc = new CheckPointProc();
			}
		}

		if (this.detail == null) {
			if (this.hasId("detail.id")) {
				detail = this.checkPointProcDetailManager
						.loadCheckPointProcDetail(this.getId("detail.id"));
			} else {
				detail = new CheckPointProcDetail();
			}
		}
	}
	
	public String save() {
		if (this.isDelete()) {
			return this.delete();
		}
		boolean isNew = this.detail.isNew();
		
		String result =  request.getParameter("detail.checkResult");
		log.debug("check result : " + result);
		detail.setCheckResult(CheckResult.valueOf(result));
		checkPointProcDetailManager.storeCheckPointProcDetail(detail, proc);

		if (isNew) {
			this.addActionMessage(this.getText(
					"checkPointProcDetail.add.success", Arrays
							.asList(new Object[] { proc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText(
					"checkPointProcDetail.edit.success", Arrays
							.asList(new Object[] { proc.getName() })));
			return SUCCESS;
		}
	}

	public String delete() {
		this.addActionMessage(this.getText(
				"checkPointProcManager.invalid.success", Arrays
						.asList(new Object[] { proc.getName() })));
		return SUCCESS;
	}

	public LabelValue[] getCheckResults() {
		return this.wrapEnum(CheckResult.class);
	}
}
