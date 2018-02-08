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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.presentation.webwork.action.TdmsActionSupport;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardManager;

/**
 * <p>Title: EditDiscardAction
 * <p>Description: 报废单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditDiscardAction.java 10818 2008-01-31 02:25:29Z qsun $
 */
public class EditDiscardAction extends PrepareAction {
	private static final long serialVersionUID = -7066875640491027102L;
	private final DeviceCardManager deviceCardManager;
	private final DiscardManager discardManager;
    private final CodeValueManager codeValueManager;
    private final UserManager userManager;
    private final DepartmentManager departmentManager;
    private Discard discard;
    private Discard searchToolingDiscard;
    private Discard searchDeviceDiscard;
    private DeviceCard tooling;
    private DeviceCard device;
    private Department department;
    private User manager;
    private boolean bTooling; 
    
	public EditDiscardAction(DiscardManager discardManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager,
			DepartmentManager departmentManager
			,UserManager userManager){
		this.discardManager=discardManager;
		this.deviceCardManager=deviceCardManager;
		this.codeValueManager=codeValueManager;
		this.departmentManager=departmentManager;
		this.userManager=userManager;
	}
    
	/**
	 * 初始化，获取"toolingDevFlag",以判断该事故单是工装还是设备
	 * 获取参数<b>discard.id</b>，如果存在就获取这个报废单实体和工装（或设备）实体，
	 * 如果不存在，就新建一个报废单实体，同时根据先前的判断将该报废单标示为工装或设备
	 */
	public void prepare() throws Exception {
		if (null == this.discard) {
			if (this.hasId("discard.id")) {
				this.discard = this.discardManager.loadDiscard(this.getId("discard.id"));
				if(!this.hasId("tooling.id")){
					
				  this.tooling = discard.getTooling();
				}
				if(!this.hasId("tooling.id")){
				         this.device = discard.getDevice();
				}
			} else {
				this.discard = new  Discard();
			}
		}
		String s = request.getParameter("toolingDevFlag");
		if (StringUtils.isNotEmpty(s) && s.equals("true")) {
			bTooling = true;
			this.discard.setToolingDevFlag(true);
		} else {
			bTooling = false;
			this.discard.setToolingDevFlag(false);
		}
	}
	
	/**
	 * 保存一个报废单信息
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {
		if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {
			this.tooling = this.deviceCardManager.loadDevice(this.getId("tooling.id"));
			discard.setTooling(tooling);
		}
		if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			discard.setTooling(device);
		}
		if (!StringUtils.isEmpty(request.getParameter("manager.id"))) {
			discard.setManager(this.userManager.loadUser(this.getId("manager.id")));
		}
		if(this.hasId("tooling.id")){
			searchToolingDiscard=this.discardManager.getToolingDiscardByToolingId(this.getId("tooling.id"));
					  if(searchToolingDiscard!=null&&searchToolingDiscard.getId()!=discard.getId()){
							this.addActionMessage(this.getText("toolingDiscard.add.error", Arrays
									.asList(new Object[] { discard.getName() ,discard.getTooling().getName()})));
							return TdmsActionSupport.ERROR_TOOLING;
						}	  
				  }
			
			
		
		
		if(this.hasId("device.id")){
			searchDeviceDiscard=this.discardManager.getDeviceDiscardByToolingId(this.getId("device.id"));
			if(searchDeviceDiscard!=null&&searchDeviceDiscard.getId()!=discard.getId()){
				this.addActionMessage(this.getText("deviceDiscard.add.error", Arrays
						.asList(new Object[] { discard.getName()})));
				return TdmsActionSupport.ERROR_DEVICE;
			}
		}
		
		if (!StringUtils.isEmpty(request.getParameter("discardAgree"))) {
			discard.setDiscardFlag(true);
		} else {
			discard.setDiscardFlag(false);
		}
		
		if (!StringUtils.isEmpty(request.getParameter("toolingState"))){
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("toolingState")));
		}
		if (!StringUtils.isEmpty(request.getParameter("deviceState"))){
			device.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(request.getParameter("deviceState")));
		}
		
		boolean isNew = this.discard.isNew();
		if(this.discard.isToolingDevFlag()){
			discard.setTooling(tooling);
			if (!StringUtils.isEmpty(request.getParameter("discard.descardDatetime"))){
				Date date = null;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("discard.descardDatetime"));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
				tooling.setDisabledTime(date);
			}else{
				tooling.setDisabledTime(null);
			}
		}else{
			discard.setDevice(device);
		}
		discard.setDepartment(department);
		this.discardManager.storeDiscard(discard);
		if (isNew) {
			this.addActionMessage(this.getText("discard.add.success",
					Arrays.asList(new Object[] { discard.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("discard.edit.success",
					Arrays.asList(new Object[] { discard.getName() })));
			return SUCCESS;
		}
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public List getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public DiscardManager getDiscardManager() {
		return discardManager;
	}

	public DeviceCard getDevice() {
		return device;
	}

	public void setDevice(DeviceCard device) {
		this.device = device;
	}

	public Discard getDiscard() {
		return discard;
	}

	public void setDiscard(Discard discard) {
		this.discard = discard;
	}

	public Discard getSearchToolingDiscard() {
		return searchToolingDiscard;
	}

	public void setSearchToolingDiscard(Discard searchToolingDiscard) {
		this.searchToolingDiscard = searchToolingDiscard;
	}

	public boolean isBTooling() {
		return bTooling;
	}

	public void setBTooling(boolean tooling) {
		bTooling = tooling;
	}
}
