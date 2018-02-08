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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.device.ManagementLevel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.workflow.service.docstate.WfDocStateManager;

/**
 * @author qs
 * @version $Id: ListDeviceAction.java 11144 2008-02-27 13:46:36Z zbzhang $
 */
public class ListDeviceAction extends ValueListAction {
	private static final long serialVersionUID = 3087495020938455975L;
	private final Log log = LogFactory.getLog(getClass());
	
	private final DepartmentManager departmentManager;
	private final DeviceTypeManager deviceTypeManager;
	private final WfDocStateManager wfDocStateManager;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final UserManager userManager;
	
	//分公司管理 与 部门级联 添加 04/09/2009
	private final FilialeManager filialeManager;
	
	
	private String flag="";	//用于标示是哪个页面进行的查询
	
	private List<DeviceCard> devices;
    //需要过滤得设备ID集合
	private List<Long> oldDeviceIds; 
    //需要过滤得设备ID字符串
	private String filterDeviceIds;
/* 原来的
	public ListDeviceAction(DepartmentManager departmentManager, 
			DeviceTypeManager deviceTypeManager,
			WfDocStateManager wfDocStateManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager,
			UserManager userManager) {
		this.departmentManager = departmentManager;
		this.deviceTypeManager = deviceTypeManager;
		this.wfDocStateManager = wfDocStateManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.userManager = userManager;
	}
*/
	//分公司与部门级联 后添加的 04/09/2009
	public ListDeviceAction(DepartmentManager departmentManager, 
			DeviceTypeManager deviceTypeManager,
			WfDocStateManager wfDocStateManager,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager,
			UserManager userManager,
			FilialeManager filialeManager) {
		this.departmentManager = departmentManager;
		this.deviceTypeManager = deviceTypeManager;
		this.wfDocStateManager = wfDocStateManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.userManager = userManager;
		this.filialeManager = filialeManager;
	}
	
	public String execute() throws Exception {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return this.enabled();
		}
		return SUCCESS;
	}

	public String disabled() {
		this.deviceCardManager.disabledAllDevices(this.devices);
		this.addActionMessage(this.getText("devices.disable.success"));
		return SUCCESS;
	}
	
	public String enabled() {
		this.deviceCardManager.enabledAllDevices(this.devices);
		this.addActionMessage(this.getText("devices.enabled.success"));
		return SUCCESS;
	}
	
	/**
	 * 判断是否点击了search按钮
	 * 
	 * @param 
	 * @return 是为true,否为false
	 */
	public boolean isSearch() {
		return this.hasKey("search");
	}
	
	public void prepare() {
		if (this.hasId("deviceIds")) {
			this.devices = this.deviceCardManager.loadAllDevices(this.getIds("deviceIds"));
		}
		if (null == oldDeviceIds) {
			if (this.hasId("oldDeviceIds")) {
				if (!this.isFirst() || this.isSearch()) {
					//从请求中获取需要过滤得设备ID字符串
					String[] oldDeviceId = request.getParameter("oldDeviceIds")
							.split(",");
					oldDeviceIds = new ArrayList<Long>();
					for (int i = 0; i < oldDeviceId.length; i++) {
						//将需要过滤得设备ID放入List集合中
						oldDeviceIds.add(Long.valueOf(oldDeviceId[i]));
					}
				}
				this.filterDeviceIds = request.getParameter("oldDeviceIds");
				log.debug("filterDeviceIds value ====" + filterDeviceIds);

			}
		}
		if (this.hasId("flag")) {
			this.flag = request.getParameter("flag");
		}
	}

	public List getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceCard> devices) {
		this.devices = devices;
	}

	@Override
	protected String getAdapterName() {
		return "devices";
	}

	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {
				Department department = new Department();
				department.setId(Long.valueOf(-1L));
				department.setName("");
				list.add(department);
			} else {
				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
			}
			//显示附属部门
			Set<Department> depts =userManager.getUser().getDepartments();
			if(depts!=null){
				list.addAll(depts);
			}
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}

	public List getDeviceTypes() {
		return deviceTypeManager.createSelectDeviceTypes(this
				.getText("select.option.all"));
	}
	
	public List getDocStates() {
		return wfDocStateManager.createSelectDocStates(this.getText("select.option.all"));
	}
	
//	@SuppressWarnings("unchecked")
//	protected Map getRequestParameterMap() {
//		Map map = super.getRequestParameterMap();
////		if (invalid) {
////			map.put("includeInvalid", "placeholder");
////		}
//		return map;
//	}

	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (this.hasId("oldDeviceIds")) {
			map.put("filterDeviceIds", oldDeviceIds);
		}
		return map;
	}
//	private boolean isDisabled() {
//		return this.hasKey("disabled");
//	}
	
	public List<LabelValue> getManagementLevels() {
		LabelValue [] arrays = this.wrapEnum(ManagementLevel.class);
		LabelValue array = new LabelValue("-1",this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(array);
		for (int i=0; i<arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	public List getDeviceStatus() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"), CodeConstants.DEVICE_STATUS,this.flag);
	}
/*	原来的
	public List getFiliales() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"), CodeConstants.FILIALE);
	}
*/
	public List getFiliales(){
		return this.filialeManager.createSelectFilailes(this.getText("select.option.all"));
	}
	
	public List getUseCategory() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"), CodeConstants.PRODUCT_USED_TYPE);
	}
	
	public List getSpecificationProp() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"), CodeConstants.DEVICE_SPECIFICATION_PROF);
	}
	
	public List getExcellentBigSparse() {
		return this.codeValueManager.createSelectCodeValues(this.getText("select.option.all"), CodeConstants.EXCELLENT_BIG_SPARSE);
	}

	public String getFilterDeviceIds() {
		return filterDeviceIds;
	}

	public void setFilterDeviceIds(String filterDeviceIds) {
		this.filterDeviceIds = filterDeviceIds;
	}
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
