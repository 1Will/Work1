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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.alteration;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;
import com.yongjun.tdms.model.runmaintenance.alteration.UnSealedStatus;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.alteration.AlterationManager;

/**
 * <p>Title: EditAlterationAction
 * <p>Description: 工装变动页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditAlterationAction.java 11015 2008-02-19 01:57:30Z wzou $
 */
public class EditAlterationAction extends PrepareAction {
	private static final long serialVersionUID = 6499384032217505796L;

	private Alteration alteration;
	private DeviceCard tooling;
	private DeviceCard device;
	private String sealedFlag;
	private String toolingDevFlag;

	private final AlterationManager alterationManager;
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	private final CodeValueManager codeValueManager;

	public EditAlterationAction(AlterationManager alterationManager,
			DeviceCardManager deviceCardManager,
			UserManager userManager,
			CodeValueManager codeValueManager) {
		this.alterationManager = alterationManager;
		this.deviceCardManager=deviceCardManager;
		this.userManager = userManager;
		this.codeValueManager = codeValueManager;
	}
	
	

	/**
	 * 根据页面传递的sealedFlag判断是启封还是封存
	 * 根据页面面传递的alteration.id，获取工装封存对象
	 * 根据页面传递的tooling.id，获取工装卡片对象，并将它设置给工装变动对象
	 * 
	 * @param 
	 * @return 
	 */
	public void prepare() throws Exception {
		
		/**
		 * 根据alteration.id的值
		 * 加裁工装变动实体对象
		 */
		if (null == this.alteration) {
			if (this.hasId("alteration.id")) {
				this.alteration = this.alterationManager.loadAlteration(this
						.getId("alteration.id"));
				if(!this.hasId("tooling.id")){
					this.tooling=this.alteration.getAsset();
					
				}
				if(!this.hasId("device.id")){
				this.device=this.alteration.getAsset();
				}
				
			} else {
				this.alteration = new Alteration();
			}
		}
		
		if (this.hasId("toolingDevFlag")) {
			if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
				this.toolingDevFlag = "TOOLING";
				this.alteration.setToolingDevFlag(SysModel.TOOLING);
			} else {
				this.toolingDevFlag = "DEVICE";
				this.alteration.setToolingDevFlag(SysModel.DEVICE);
			}
		}
	}
	
	/**
	 * 判断是新增还是修改
	 * 根据封存标志存储工装变动实体
	 * 
	 * @param 
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		boolean isNew = this.alteration.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
			this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
			if (!StringUtils.isEmpty(request.getParameter("toolingState"))){						// 设置工装状态
				tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("toolingState")));
			}
			alteration.setAsset(tooling);
		}
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			if (!StringUtils.isEmpty(request.getParameter("deviceState"))){							//设置设备状态
				device.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("deviceState")));
			}
			alteration.setAsset(device);
		}
		if (!StringUtils.isEmpty(request.getParameter("sealedUsed.id"))) {
			alteration.setSealedUsed(this.userManager.loadUser(this.getId("sealedUsed.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("sealedUsedApr.id"))) {
			alteration.setSealedUsedApr(this.userManager.loadUser(this.getId("sealedUsedApr.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("unSealedUsedApr.id"))) {
			alteration.setUnSealedUsedApr(this.userManager.loadUser(this.getId("unSealedUsedApr.id")));
		}

		
		if (!StringUtils.isEmpty(request.getParameter("sealedStateFlag"))) {
			alteration.setIsSealed(true);
			alteration.setStatus( UnSealedStatus.SEALED);
		}
		
		if (!StringUtils.isEmpty(request.getParameter("unSealedStateFlag"))){
			alteration.setIsUnSealed(true);
			alteration.setStatus( UnSealedStatus.NORMAL);
		}
		
		if (StringUtils.isEmpty(request.getParameter("sealedStateFlag"))&& 
				StringUtils.isEmpty(request.getParameter("unSealedStateFlag"))){
			if(alteration.getStatus().equals(UnSealedStatus.NORMAL)) {
				alteration.setIsUnSealed(false);
				alteration.setStatus( UnSealedStatus.SEALED);
			}else if (alteration.getStatus().equals(UnSealedStatus.SEALED)){
				alteration.setIsSealed(false);
				alteration.setStatus( UnSealedStatus.REQUEST);
			}
		}
		if (isNew) {
			alteration.setStatus( UnSealedStatus.REQUEST);
		}
		
		this.alterationManager.storeAlteration(alteration);
		
		if (isNew) {
			if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
				this.addActionMessage(this.getText("alterationTooling.add.success", Arrays
						.asList(new Object[] { alteration.getSealedBillNo() })));
			}else {
				this.addActionMessage(this.getText("alterationDevice.add.success", Arrays
						.asList(new Object[] { alteration.getSealedBillNo() })));
			}
			return NEW;
		} else {       
			if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
				this.addActionMessage(this.getText("alterationTooling.edit.success", Arrays
						.asList(new Object[] { alteration.getSealedBillNo() })));
			}else {
				this.addActionMessage(this.getText("alterationDevice.edit.success", Arrays
						.asList(new Object[] { alteration.getSealedBillNo() })));
			}
			return SUCCESS;
		}
	}

	public Alteration getAlteration() {
		return alteration;
	}

	public void setAlteration(Alteration alteration) {
		this.alteration = alteration;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public String getSealedFlag() {
		return sealedFlag;
	}

	public void setSealedFlag(String sealedFlag) {
		this.sealedFlag = sealedFlag;
	}



	public String getToolingDevFlag() {
		return toolingDevFlag;
	}



	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}



	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	
	public User getLoginUser() {
		return userManager.getUser();
	}
}
