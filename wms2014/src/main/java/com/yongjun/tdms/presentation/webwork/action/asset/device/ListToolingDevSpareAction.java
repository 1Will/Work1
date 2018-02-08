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
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceSpareManager;

/**
 * <p>
 * Title: ListDepartmentAction
 * <p>
 * Description: 工装|设备的panel备件页面访问控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author zbzhang@yj-technology.com
 * @version $Id: ListToolingDevSpareAction.java 9286 2007-12-12 13:15:05Z
 *          zbzhang $
 */
public class ListToolingDevSpareAction extends ValueListAction {
	private static final long serialVersionUID = -3561166568744121919L;

	private final Log log = LogFactory.getLog(getClass());;

	private final DeviceCardManager deviceCardManager;

	private final DeviceSpareManager toolingDevSpareManager;

	private DeviceCard toolingDev;

	private List<DeviceSpare> toolingDevspare;

	private String addSpareIds;

	private static final String ADD_SPARES = "addSpares";

	private String SAVE_SPARES;

	public ListToolingDevSpareAction(DeviceCardManager deviceCardManager,
			DeviceSpareManager toolingDevSpareManager) {
		this.deviceCardManager = deviceCardManager;
		this.toolingDevSpareManager = toolingDevSpareManager;
	}

	public void prepare() {
		if (this.hasId("toolingDev.id")) {
			this.toolingDev = this.deviceCardManager.loadDevice(this
					.getId("toolingDev.id"));
		}

		if (null == this.toolingDevspare && this.hasId("spareIds")) {
			this.toolingDevspare = this.toolingDevSpareManager
					.loadDeviceSpares(this.getIds("spareIds"));
		}
		if (null == this.addSpareIds) {
			if (!StringUtils.isEmpty(request.getParameter("addSpareIds"))) {
				this.addSpareIds = request.getParameter("addSpareIds");
				log.debug("spareIds " + addSpareIds);
			}
		}
		if (null == SAVE_SPARES) {
			if (!StringUtils.isEmpty(request.getParameter("saveSpareIds"))) {
				this.SAVE_SPARES = request.getParameter("saveSpareIds");
			}
		}
		this.setFirst(false);

	}

	public String execute() {
		if (this.isDelete()) {
			this.delete();
		}
		if (this.isAddSpares()) {
			this.saveAddSpares();
		}
		if(this.isSaveSpare()){
			this.saveSpares();
		}
		return SUCCESS;
	}

	/**
	 * 判断页面传来的addSpare变量是否有值，且值是否等于ADD_SPARES
	 * 
	 * @return boolean true 添加备件|false 不添加
	 */
	private boolean isAddSpares() {
		if (!StringUtils.isEmpty(request.getParameter("addSpares"))) {
			if (request.getParameter("addSpares").equals(ADD_SPARES)) {
				return true;
			}
		}
		return false;
	}

	private boolean isSaveSpare() {
		if(this.hasKey("save")){
			if (!StringUtils.isEmpty(request.getParameter("saveSpareIds"))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 保存新添加的备件
	 * 
	 */
	
	private String saveSpares(){
		this.toolingDevSpareManager.storeDeviceSpare(this.SAVE_SPARES);
		this.addActionMessage(this.getText("spare.installPlace.success"));
		return SUCCESS;
	}
	public void saveAddSpares() {
		this.toolingDevSpareManager.storeToolingDevSpare(toolingDev,
				this.addSpareIds);
		this.addActionMessage(this.getText("spare.add.success"));
	}

	public void delete() {
		this.toolingDevSpareManager.deleteAllDeviceSpare(toolingDevspare);
		this.addActionMessage(this.getText("spare.delete.success"));
	}

	public DeviceCard getToolingDev() {
		return toolingDev;
	}

	public String getSAVE_SPARES() {
		return SAVE_SPARES;
	}

	public void setSAVE_SPARES(String save_spares) {
		SAVE_SPARES = save_spares;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("toolingDev.id",this.getId("toolingDev.id"));	
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "toolingDevSpare";
	}

}
