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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.unused;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
/**
 * <p>Title: EditUnusedAction
 * <p>Description: 闲置单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: EditUnusedAction 10972 2008-02-17 03:05:50Z xschen $
 */
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsed;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.unused.UnusedManager;

public class EditUnusedAction extends PrepareAction {

	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final DeviceCardManager deviceCardManager;
	private final UserManager userManager;
	private final UnusedManager unusedManger;
	private UnUsed unused;                       //闲置单对象
	private DeviceCard device;                   //闲置单所关联的设备对象
	private DeviceCard asset;
	private String toolingDevFlag;               //标示工装或设备
	public EditUnusedAction(UnusedManager unusedManger,
			UserManager userManager, DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager) {
		this.unusedManger = unusedManger;
		this.userManager = userManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
	}
	public void prepare() throws Exception {
		if (null == unused) {
			if (this.hasId("unused.id")) {this.unused = this.unusedManger.loadUnUsed(this.getId("unused.id"));
				if(!this.hasId("tooling.id") && !this.hasId("device.id")){
					this.asset=this.unused.getAsset();
				}
				if(!this.hasId("device.id")){
				this.device = this.unused.getAsset();
				}
			} else {
				this.unused = new UnUsed();
			}
		}
       if(this.hasId("toolingDevFlag")){
    	   this.toolingDevFlag=request.getParameter("toolingDevFlag");
       }
	}

	/**
	 * 获取系统当前登录的人
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	public String save() {
		boolean isNew = unused.isNew();
		if (!StringUtils.isEmpty(request.getParameter("applicant.id"))) {
			unused.setApplicant(this.userManager.loadUser(this.getId("applicant.id")));//获得闲置申请人
		}
			if (!StringUtils.isEmpty(request.getParameter("device.id"))) {
				unused.setAsset(this.deviceCardManager.loadDevice(this.getId("device.id")));//根据设备ID获得设备的对象
			}
			if (!StringUtils.isEmpty(request.getParameter("tooling.id"))) {                 //根据工装ID获得工装的对象
				unused.setAsset(this.deviceCardManager.loadDevice(this.getId("tooling.id")));
			}
			if (isNew) {
				unused.setStatus(UnUsedStatus.REQUEST);   //如果是新建一个闲置单  至闲置单的状态为闲置申请中..............
			} else if ((!StringUtils.isEmpty(request.getParameter("onlyDisapprove")))||!StringUtils.isEmpty(request.getParameter("onlyisUsed"))) {//如果“是否闲置”的checkbox不为空
				if (!StringUtils.isEmpty(request.getParameter("onlyDisapprove"))) {
					if (request.getParameter("onlyDisapprove").equals(UnUsedStatus.UNUSED.toString())) {//如果是否闲置的状态值等于UNUSESD 那么至闲置单状态为已闲置
						unused.setStatus(UnUsedStatus.UNUSED);
						unused.setIsUnUsedAduit(true);
					}
				}
				if (!StringUtils.isEmpty(request.getParameter("onlyisUsed"))) { //如果checkbox值等于启用     那么至闲置单的状态为已启用
					if (request.getParameter("onlyisUsed").equals(UnUsedStatus.NORMAL.toString())) {
						unused.setStatus(UnUsedStatus.NORMAL);
						unused.setIsUsedAduit(true); // 设置为启用状态
					}
				}
			} else {
				if (unused.getStatus().equals(UnUsedStatus.NORMAL)) {
					unused.setStatus(UnUsedStatus.UNUSED);
					unused.setIsUsedAduit(false);
				} else {
					unused.setStatus(UnUsedStatus.REQUEST);
					unused.setIsUnUsedAduit(false);
				}
			}
			if (!StringUtils.isEmpty(request.getParameter("unUsedApr.id"))) {
				unused.setUnUsedApr(this.userManager.loadUser(this.getId("unUsedApr.id")));
			}
			if (!StringUtils.isEmpty(request.getParameter("usedApr.id"))) {
				unused.setUsedApr(this.userManager.loadUser(this.getId("usedApr.id")));
			}
			if(toolingDevFlag.equals(SysModel.DEVICE.toString())){        //如果toolingDevFlag值为'DEVICE' 那么此闲置单为设备闲置单
				unused.setToolingDevFlag(SysModel.DEVICE);
			}else{
				  unused.setToolingDevFlag(SysModel.TOOLING);             //如果toolingDevFlag值为'TOOLING' 那么此闲置单为工装闲置单 
			}
		this.unusedManger.storeUnUsed(unused);
		if (isNew) {
			this.addActionMessage(this.getText("unused.add.success", Arrays.asList(new Object[] { unused.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("unused.edit.success", Arrays.asList(new Object[] { unused.getName() })));
			return SUCCESS;
		}
	}
	public UnUsed getUnused() {
		return unused;
	}

	public void setUnused(UnUsed unused) {
		this.unused = unused;
	}
	public DeviceCard getAsset() {
		return asset;
	}
	public void setAsset(DeviceCard asset) {
		this.asset = asset;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public DeviceCard getDevice() {
		return asset;
	}
	public DeviceCard getTooling(){
		return asset;
	}
}
