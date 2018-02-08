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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.maintenance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceDetail;
import com.yongjun.tdms.model.runmaintenance.maintenance.MaintenanceResultType;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairDetailResult;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.maintenance.MaintenanceDetailManager;

/**
 * <p>Title: EditMaintenanceDetailAction
 * <p>Description: 设备保养明细页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditMaintenanceDetailAction.java 10972 2008-02-17 03:05:50Z wzou $
 */
public class EditMaintenanceDetailAction extends PrepareAction {
	private static final long serialVersionUID = -7263546348514268252L;
	
	private final MaintenanceDetailManager maintenanceDetailManager;
	private final CodeValueManager codeValueManager;
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	
	private MaintenanceDetail detail;
	private DeviceCard device;
	private String planProcFlag;
	
	public EditMaintenanceDetailAction(MaintenanceDetailManager maintenanceDetailManager,
			CodeValueManager codeValueManager,
			DeviceCardManager deviceCardManager,
			UserManager userManager) {
		this.maintenanceDetailManager = maintenanceDetailManager;
		this.codeValueManager = codeValueManager;
		this.deviceCardManager = deviceCardManager;
		this.userManager = userManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.detail) {
			if(this.hasId("maintenanceDetail.id")) {
				this.detail = this.maintenanceDetailManager.loadMaintenanceDetail(Long.valueOf(request.getParameter("maintenanceDetail.id").replace(",", "")));
			} else {
				this.detail = new MaintenanceDetail();
			}
		}
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(Long.valueOf(request.getParameter("device.id").replace(",", "")));
			}
		}
		if (this.hasId("planProcFlag")) {
			this.planProcFlag = request.getParameter("planProcFlag");
		}
	}
	
	public String save() {
		boolean isNew = this.detail.isNew();
		detail.setDevice(this.device);
//		if (!StringUtils.isEmpty(request.getParameter("resultTypeID"))) {
//			detail.setResultType(this.codeValueManager.loadCodeValue(this.getId("resultTypeID")));
//		}
		if (!StringUtils.isEmpty(request.getParameter("dutyPeople.id"))) {
			detail.setDutyPeople(this.userManager.loadUser(this.getId("dutyPeople.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("resultType.id"))) {
			detail.setResultType(this.codeValueManager.loadCodeValue(this
					.getId("resultType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("result"))) {
			if (request.getParameter("result").equals("FINISHED")) {
				detail.setResult(MaintenanceResultType.FINISHED);
			} else {
				detail.setResult(MaintenanceResultType.UNFINISHED);
			}
		}
		this.maintenanceDetailManager.storeMaintenanceDetail(detail);
		
		if (isNew) {
			this.addActionMessage(this.getText("maintenanceDetail.add.success",
					Arrays.asList(new Object[] { detail.getDevice().getName() })));
			return NEW;
		}else {
			this.addActionMessage(this.getText("maintenanceDetail.edit.success",
					Arrays.asList(new Object[] { detail.getDevice().getName() })));
			return SUCCESS;
		}
	}
	
	public List<LabelValue> getResults() {
		LabelValue [] arrays = this.wrapEnum(MaintenanceResultType.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public List getResultType() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.MAINTENANCE_CATEGORY);
	}

	public MaintenanceDetail getDetail() {
		return detail;
	}

	public void setDetail(MaintenanceDetail detail) {
		this.detail = detail;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public String getPlanProcFlag() {
		return planProcFlag;
	}

	public void setPlanProcFlag(String planProcFlag) {
		this.planProcFlag = planProcFlag;
	}

}
