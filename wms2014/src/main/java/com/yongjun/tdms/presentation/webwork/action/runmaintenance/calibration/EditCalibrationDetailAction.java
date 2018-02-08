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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationDetail;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationPassType;
import com.yongjun.tdms.model.runmaintenance.calibration.CalibrationResultType;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.calibration.CalibrationDetailManager;

/**
 * <p>Title: EditCalibrationDetailAction
 * <p>Description: 工装标定明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditCalibrationDetailAction.java 10972 2008-02-17 03:05:50Z wzou $
 */
public class EditCalibrationDetailAction extends PrepareAction {
	private static final long serialVersionUID = -8020993355332185158L;
	
	private final CalibrationDetailManager calibrationDetailManager;
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	
	private CalibrationDetail detail;
	private DeviceCard tooling;
	private String planProcFlag;
	
	public EditCalibrationDetailAction(CalibrationDetailManager calibrationDetailManager,
			DeviceCardManager deviceCardManager,
			UserManager userManager){
		this.calibrationDetailManager = calibrationDetailManager;
		this.deviceCardManager = deviceCardManager;
		this.userManager = userManager;
	}
	public void prepare() throws Exception {
		if (null == this.detail) {
			if(this.hasId("calibrationDetail.id")) {
				this.detail = this.calibrationDetailManager.loadCalibrationDetail(Long.valueOf(request.getParameter("calibrationDetail.id").replace(",", "")));
			} else {
				this.detail = new CalibrationDetail();
			}
		}
		if (null == this.tooling) {
			if (this.hasId("tooling.id")) {
				this.tooling = this.deviceCardManager.loadDevice(Long.valueOf(request.getParameter("tooling.id").replace(",", "")));
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.detail.isNew();
		detail.setTooling(this.tooling);
		
		if (!StringUtils.isEmpty(request.getParameter("dutyPeople.id"))) {
			detail.setDutyPeople(this.userManager.loadUser(this.getId("dutyPeople.id")));
		}
		
		
		if (!StringUtils.isEmpty(request.getParameter("result"))) {
			if (request.getParameter("result").equals("FINISHED")) {
				detail.setResult(CalibrationResultType.FINISHED);
			} else {
				detail.setResult(CalibrationResultType.UNFINISHED);
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("calibrationResult"))) {
			if (request.getParameter("calibrationResult").equals("PASS")) {
				detail.setCalibrationResult(CalibrationPassType.PASS);
			} else {
				detail.setCalibrationResult(CalibrationPassType.NOPASS);
			}
		}
		
		this.calibrationDetailManager.storeCalibrationDetail(detail);
		
		if (isNew) {
			this.addActionMessage(this.getText("calibrationDetail.add.success",
					Arrays.asList(new Object[] { detail.getTooling().getName() })));
			return NEW;
		}else {
			this.addActionMessage(this.getText("calibrationDetail.edit.success",
					Arrays.asList(new Object[] { detail.getTooling().getName() })));
			return SUCCESS;
		}
		
	}
	
	public List<LabelValue> getResults() {
		LabelValue [] arrays = this.wrapEnum(CalibrationResultType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	public List<LabelValue> getCalibrationResults() {
		LabelValue [] arrays = this.wrapEnum(CalibrationPassType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	public CalibrationDetail getDetail() {
		return detail;
	}
	public void setDetail(CalibrationDetail detail) {
		this.detail = detail;
	}
	public DeviceCard getTooling() {
		return tooling;
	}
	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}
	public String getPlanProcFlag() {
		return planProcFlag;
	}
	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}
	
	

}
