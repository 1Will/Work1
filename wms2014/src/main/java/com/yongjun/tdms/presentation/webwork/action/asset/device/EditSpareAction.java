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

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;

/**
 * @author qs
 * @version $Id: EditSpareAction.java 9886 2007-12-28 03:03:41Z qsun $
 */
public class EditSpareAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final SpareManager spareManager;
	private final DeviceCardManager deviceCardManager;
	private DeviceCard device;
	private Spare spare;
	
	public EditSpareAction(SpareManager spareManager, DeviceCardManager deviceCardManager) {
		this.spareManager = spareManager;
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() throws Exception {
		if (null == this.spare) {
			if (this.hasId("spare.id")) {
				this.spare = this.spareManager.loasSpare(this.getId("spare.id"));
				//this.device=this.spare.getDevice();
			} else {
				this.spare = new Spare();
				this.device=new DeviceCard();
			}
		}
		
		if (null == this.device) {
			if (this.hasId("device.id")) {
				this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			}
		}
	}
	
	public String save() {
		boolean isNew = this.spare.isNew();
		//this.spare.setDevice(device);
		
		this.spareManager.storeSpare(spare);
		
		if (isNew) {
			this.addActionMessage(this.getText("spare.add.success", Arrays
					.asList(new Object[] { spare.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("spare.edit.success", Arrays
					.asList(new Object[] { spare.getName() })));
			return SUCCESS;
		}
	}

	public Spare getSpare() {
		return spare;
	}

	public void setSpare(Spare spare) {
		this.spare = spare;
	}

	public DeviceCard getDevice() {
		return device;
	}

}
