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

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;

/**
 * <p>Title: ListCalibrationDocAction
 * <p>Description: 工装标定明细文档控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListCalibrationDocAction.java 10972 2008-02-17 03:05:50Z wzou $
 */
public class ListCalibrationDocAction extends ValueListAction {
	private static final long serialVersionUID = 7942941476844331950L;
	
	private final CalibrationDetailManager calibrationDetailManager;
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	
	private CalibrationDetail detail;
	private List<ApplicationDoc> calibrationDocs;
	private ApplicationDoc doc;
	
	public ListCalibrationDocAction(CalibrationDetailManager calibrationDetailManager,
			ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.calibrationDetailManager = calibrationDetailManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	public void prepare() throws Exception {
		
		if (this.hasId("calibrationDetail.id")) {
			this.detail = this.calibrationDetailManager
					.loadCalibrationDetail(this.getId("calibrationDetail.id"));
		}
		if (this.hasId("calibrationDetailIds")) {
			this.calibrationDocs = this.applicationDocManager
					.loadAllApplicationDocs(this.getIds("calibrationDetailIds"));
		}
		if (this.hasId("doc.id")) {
			doc = this.applicationDocManager
					.loadApplicationDoc(this.getId("doc.id"));
			this.download();
		}
		this.setFirst(false);
	}
	public String execute() {
		//如果点击删除按钮，则执行删除操作
		if (isDelete()) {
			this.delete();
		}
		return SUCCESS;
	}
	private void delete() {
		for (Iterator iter = calibrationDocs.iterator(); iter.hasNext();) {
			ApplicationDoc doc = (ApplicationDoc) iter.next();
			this.fileTransportManager.delete(request, doc.getPosition());
		}
		this.detail.getCalibrationDoc().removeAll(calibrationDocs);
		this.calibrationDetailManager.storeCalibrationDetail(detail);
		this.addActionMessage(this.getText("calibrationDoc.delete.success"));

	}
	public String download() {
		fileTransportManager.download(request, response, doc
				.getFileName(), doc.getPosition());
		return null;
	}
	
	@Override
	protected String getAdapterName() {
		return "calibrationDoc";
	}
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(this.hasId("calibrationDetail.id")) {
			map.put("calibrationDetail.id",this.getId("calibrationDetail.id"));
		}
		return map;
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
