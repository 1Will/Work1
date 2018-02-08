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

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;

/**
 * @author qs
 * @version $Id: ListToolingBorrowHistoryAction.java 8881 2007-12-02 03:05:28Z
 *          qsun $
 */
public class ListToolingBorrowHistoryAction extends PrepareAction {
	private static final long serialVersionUID = -5860815577327740268L;

	private final DeviceCardManager deviceCardManager;

	private DeviceCard tooling;

	public ListToolingBorrowHistoryAction(DeviceCardManager deviceCardManager) {
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() throws Exception {
		if (this.hasId("tooling.id")) {
			this.tooling = deviceCardManager.loadDevice(this
					.getId("tooling.id"));
		}
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

}
