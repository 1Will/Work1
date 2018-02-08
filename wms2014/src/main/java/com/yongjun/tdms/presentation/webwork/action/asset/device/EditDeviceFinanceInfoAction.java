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
package com.yongjun.tdms.presentation.webwork.action.asset.device;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceFinanceInfo;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceFinanceInfoManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.financeType.FinanceTypeManager;

/**
 * @author qs
 * @version $Id: EditDeviceFinanceInfoAction.java 11238 2008-03-10 01:50:59Z wzou $
 */
public class EditDeviceFinanceInfoAction extends PrepareAction {
	private static final long serialVersionUID = -7987608238529658381L;
	private final DeviceFinanceInfoManager deviceFinanceInfoManager;
	private final DeviceCardManager deviceCardManager;
	private final FinanceTypeManager financeTypeManager;
	private final CodeValueManager codeValueManager;
	private DeviceFinanceInfo deviceFinanceInfo;
	private DeviceCard device;
	
	public EditDeviceFinanceInfoAction(DeviceFinanceInfoManager deviceFinanceInfoManager,
			FinanceTypeManager financeTypeManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager) {
		this.deviceFinanceInfoManager = deviceFinanceInfoManager;
		this.deviceCardManager = deviceCardManager;
		this.financeTypeManager = financeTypeManager;
		this.codeValueManager = codeValueManager;
	}
	
	public void prepare() throws Exception {
		if (null == this.device && this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		
		if (null == this.deviceFinanceInfo) {
			if (this.hasId("deviceFinanceInfo.id")) {
				this.deviceFinanceInfo = this.deviceFinanceInfoManager.loadDeviceFinanceInfo(this.getId("deviceFinanceInfo.id"));
			} else if (this.device.hasFinanceInfo()) {
				this.deviceFinanceInfo = (DeviceFinanceInfo)this.device.getFinanceInformation();
			} else {
				this.deviceFinanceInfo = new DeviceFinanceInfo();
			}
		}
	}
	
	public String save() {
		boolean isNew = this.deviceFinanceInfo.isNew();
		this.deviceFinanceInfo.setDevice(device);
		
		if (!StringUtils.isEmpty(request.getParameter("financeType.id"))) {
			deviceFinanceInfo.setFinanceType(financeTypeManager.loadFinanceType(this.getId("financeType.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("foreignCurrencyName.id"))) {
			deviceFinanceInfo.setForeignCurrencyName(this.codeValueManager.loadCodeValue(this
					.getId("foreignCurrencyName.id")));
		}
		
		this.deviceFinanceInfoManager.storeDeviceFinanceInfo(deviceFinanceInfo);
		
		if (isNew) {
			this.addActionMessage(this.getText("deviceFinanceInfo.add.success", Arrays
					.asList(new Object[] { deviceFinanceInfo.getDevice().getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("deviceFinanceInfo.edit.success", Arrays
					.asList(new Object[] { deviceFinanceInfo.getDevice().getName() })));
			return SUCCESS;
		}
	}

	public DeviceFinanceInfo getDeviceFinanceInfo() {
		return deviceFinanceInfo;
	}

	public void setDeviceFinanceInfo(DeviceFinanceInfo deviceFinanceInfo) {
		this.deviceFinanceInfo = deviceFinanceInfo;
	}

	public DeviceCard getDevice() {
		return device;
	}
	
	/**
	 * 获取外币名称的所有值
	 * @return
	 */
	public List<CodeValue> getForeignCurrencyName() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.FOREIGN_CURRENCY_CATEGORY);
	}
}
