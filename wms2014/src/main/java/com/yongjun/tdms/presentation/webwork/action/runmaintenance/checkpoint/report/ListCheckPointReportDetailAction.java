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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.checkpoint.report;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportDetail;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportDetailManager;
import com.yongjun.tdms.service.runmaintenance.checkpoint.CheckPointReportManager;

/**
 * <p>Title: ListCheckPointReportDetailAction
 * <p>Description: 设备点检明细列表访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListMaintenanceDetailAction.java 11114 2008-02-26 07:10:36Z wzou $
 */
public class ListCheckPointReportDetailAction extends ValueListAction {
	private static final long serialVersionUID = 4858372868517176041L;
	private final CheckPointReportDetailManager checkPointReportDetailManager;
	private final CheckPointReportManager checkPointReportManager;
	private List<CheckPointReportDetail> details;
	private CheckPointReport report;
	private String addDeviceIds;
	private String saveDetails;
	
	public ListCheckPointReportDetailAction(CheckPointReportDetailManager checkPointReportDetailManager,
			CheckPointReportManager checkPointReportManager) {
		this.checkPointReportDetailManager = checkPointReportDetailManager;
		this.checkPointReportManager = checkPointReportManager;
	}

	public void prepare() {
		if (this.hasId("report.id")) {
			this.report = this.checkPointReportManager.loadCheckPointReport(this.getId("report.id"));
		}
		if (this.hasIds("checkPointReportDetailIds")) {
			this.details = this.checkPointReportDetailManager.loadAllCheckPointReportDetails(this.getIds("checkPointReportDetailIds"));
		}
		if (null == this.addDeviceIds) {
			if (!StringUtils.isEmpty(request.getParameter("addDeviceIds"))) {
				this.addDeviceIds = request.getParameter("addDeviceIds");
			}
		}
		if (null == saveDetails) {
			if (!StringUtils.isEmpty(request.getParameter("saveDetails"))) {
				this.saveDetails = request.getParameter("saveDetails");
			}
		}
		this.setFirst(false);
	}
	
	public String execute() {
		if (this.isAddDetail()) {
			return saveAddDetail();
		}
		if(this.isSaveDetail()){
			this.saveDetails();
		}
		if(this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	public String delete() {
		this.checkPointReportDetailManager.deleteAllCheckPointReportDetails(details);
		return SUCCESS;
	}
	
	/**
	 * 判断页面传来的addDevice变量是否有值，且值是否等于addDevices
	 * 
	 * @return boolean true 添加新的设备 | false 不添加
	 */
	private boolean isAddDetail() {
		if (!StringUtils.isEmpty(request.getParameter("addDevice"))) {
			if (request.getParameter("addDevice").equals("addDevices"))
				return true;
		}
		return false;
	}
	/**
	 * 保存新添加的设备为鉴定明细
	 * @return SUCCESS
	 */
	public String saveAddDetail() {
		this.checkPointReportDetailManager.storeCheckPointReportDetail(report, addDeviceIds);
		return SUCCESS;
	}
	
	private boolean isSaveDetail() {
		if(this.hasKey("save")){
			if (!StringUtils.isEmpty(request.getParameter("saveDetails"))) {
				return true;
			}
		}
		return false;
	}
	
	private String saveDetails(){
		this.checkPointReportDetailManager.storeCheckPointReportDetail(this.saveDetails);
		this.addActionMessage(this.getText("spare.installPlace.success"));
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击保存按钮
	 * @return true | false
	 */
	public boolean isSave(){
		return this.hasKey("save");
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("report.id")){
        		map.put("report.id", this.getId("report.id"));
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "checkPointReportDetails";
	}
	
	public List<CheckPointReportDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CheckPointReportDetail> details) {
		this.details = details;
	}

	public CheckPointReport getReport() {
		return report;
	}

	public void setReport(CheckPointReport report) {
		this.report = report;
	}

	public String getAddDeviceIds() {
		return addDeviceIds;
	}

	public void setAddDeviceIds(String addDeviceIds) {
		this.addDeviceIds = addDeviceIds;
	}

	public String getSaveDetails() {
		return saveDetails;
	}

	public void setSaveDetails(String saveDetails) {
		this.saveDetails = saveDetails;
	}

}
