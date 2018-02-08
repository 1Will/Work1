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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.calibration;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;

public class EditCalibrationDocAction extends FileTransportAction{
	private static final long serialVersionUID = 1L;
	
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private final CalibrationDetailManager calibrationDetailManager;
	
	private ApplicationDoc doc; 
	private CalibrationDetail detail;
	private List<ApplicationDoc> calibrationDocs;
	
	public EditCalibrationDocAction (ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager,
			CalibrationDetailManager calibrationDetailManager) {
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
		this.calibrationDetailManager = calibrationDetailManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("calibrationDetail.id")) {
			this.detail = this.calibrationDetailManager
					.loadCalibrationDetail(this.getId("calibrationDetail.id"));
		}
		if (null == this.doc) {
			if (this.hasId("doc.id")) {
				this.doc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			} else {
				this.doc = new ApplicationDoc();
			}
		}
	}
	
	public String save() throws Exception {
		boolean isNew = this.doc.isNew();
		String location = fileTransportManager.upload(request, getFile(), "origFileName");  
		String orgFileName = request.getParameter("origFileName");
		doc.setFileName(orgFileName);
		doc.setPosition(location);
		doc.setCalibrationDetail(detail);
		doc.setFileNo(location);
		this.applicationDocManager.storeApplicationDoc(doc);
		if (isNew) {
			this.addActionMessage(this.getText("doc.add.success", Arrays
					.asList(new Object[] { doc.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("doc.edit.success", Arrays
					.asList(new Object[] { doc.getName() })));
			return SUCCESS;
		}
	}
	
	public String modify() {
		ApplicationDoc doc = new ApplicationDoc();
		
		if (this.hasId("doc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
		}
		doc.setName(doc.getName());
		doc.setDescription(doc.getDescription());
		doc.setFileNo(doc.getFileNo());
		
		this.applicationDocManager.storeApplicationDoc(doc);
		this.addActionMessage(this.getText("doc.edit.success", Arrays
				.asList(new Object[] { doc.getName() })));
		
		return SUCCESS;
	}
	
	/**
	 * 用来判断是更新还是上传新的文件
	 * @return
	 */
	public String getFirst() {
		return request.getParameter("first");
	}

	public List<ApplicationDoc> getCalibrationDocs() {
		return calibrationDocs;
	}

	public void setCalibrationDocs(List<ApplicationDoc> calibrationDocs) {
		this.calibrationDocs = calibrationDocs;
	}

	public CalibrationDetail getDetail() {
		return detail;
	}

	public void setDetail(CalibrationDetail detail) {
		this.detail = detail;
	}

	public ApplicationDoc getDoc() {
		return doc;
	}

	public void setDoc(ApplicationDoc doc) {
		this.doc = doc;
	}
	
}
