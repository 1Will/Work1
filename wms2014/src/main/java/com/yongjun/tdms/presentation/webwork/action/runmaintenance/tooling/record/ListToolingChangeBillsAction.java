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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.tooling.record;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * <p>Title: ListToolingChangeBillsAction
 * <p>Description: 工装变更单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: ListToolingChangeBillsAction.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class ListToolingChangeBillsAction extends ValueListAction {
	private static final long serialVersionUID = -2691679636670360346L;

	private final DeviceCardManager deviceCardManager;

	private DeviceCard tooling;

	public ListToolingChangeBillsAction(DeviceCardManager deviceCardManager) {
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() {
		if (this.hasId("tooling.id")) {
			this.tooling = this.deviceCardManager.loadDevice(this
					.getId("tooling.id"));
		}
		this.setFirst(false);
	}

	public String execute() {
		return SUCCESS;
	}

	@Override
	protected String getAdapterName() {
		return "toolingChangeRecord";
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

}
