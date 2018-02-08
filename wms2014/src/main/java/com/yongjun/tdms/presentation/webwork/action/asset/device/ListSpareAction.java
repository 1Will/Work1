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

import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;

/**
 * @author qs
 * @version $Id$
 */
public class ListSpareAction extends PrepareAction{
	private static final long serialVersionUID = -3561166568744121919L;
	private final SpareManager spareManager;
	private final DeviceCardManager deviceCardManager;
	private DeviceCard device;
	private List<Spare> spares;
	private String pagingPage;
	
	public String getPagingPage() {
		return pagingPage;
	}

	public void setPagingPage(String pagingPage) {
		this.pagingPage = pagingPage;
	}

	public ListSpareAction(SpareManager spareManager, DeviceCardManager deviceCardManager) {
		this.spareManager = spareManager;
		this.deviceCardManager = deviceCardManager;
	}

	public void prepare() {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
		}
		
		if (null == this.spares && this.hasId("spareIds")) {
			this.spares = this.spareManager.loadAllSpares(this.getIds("spareIds"));
		}
		if (null == this.pagingPage && this.hasId("pagingPage")) {
			this.pagingPage=(String) request.getParameter("pagingPage");
		}
		
	}
	
	public String execute() {
		if (this.isDelete()) {
			this.delete();
		}
		if(this.isDisable()){
			this.disabled();
		}
		return SUCCESS;
	}
	
	 private boolean isDisable() {
	        return this.hasKey("disabled");
	    }
	
	public String disabled(){
		this.spareManager.invalidateSpare(spares);
		this.addActionMessage(this.getText("spare.disable.success"));
        return SUCCESS;
	}
	
	public void delete() {
		this.spareManager.deleteAllSpare(spares);
		this.addActionMessage(this.getText("spare.delete.success"));
	}
	
	public List<Spare> getSpares() {
		return spares;
	}

	public void setSpares(List<Spare> spares) {
		this.spares = spares;
	}

	public DeviceCard getDevice() {
		return device;
	}

}
