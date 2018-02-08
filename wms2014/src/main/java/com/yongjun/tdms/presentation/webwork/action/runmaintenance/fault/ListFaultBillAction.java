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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.fault;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.fault.FaultBillManager;


/**
 * <p>Title: ListFaultBillAction
 * <p>Description: 故障单页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ListFaultBillAction.java 11045 2008-02-21 01:16:58Z wzou $
 */
public class ListFaultBillAction extends ValueListAction {
	private static final long serialVersionUID = 4034778946163757828L;
	private final FaultBillManager faultBillManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private final DeviceCardManager deviceCardManager;
	private List<FaultBill> faultBill;
	private String toolingDevFlag;
	private DeviceCard tooling;
	private boolean onlyValid;						//标示为有效
	private boolean onlyInvalid;					//标示为无效
	
	public ListFaultBillAction(FaultBillManager faultBillManager,
			               DepartmentManager departmentManager,
			               CodeValueManager codeValueManager,
			               DeviceCardManager deviceCardManager) {
		this.faultBillManager = faultBillManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
		this.deviceCardManager=deviceCardManager;
	}
	
	/**
	 * 获取页面参数 <b>faultBillIds</b> ,如果得到，根据ID获取故障单
	 * 同时根据传递的"toolingDevFlag"来判断是工装还是设备的报废单
	 */
	public void prepare() {
		if (this.hasIds("faultBillIds")) {
			this.faultBill = this.faultBillManager.loadAllFaultBills(this.getIds("faultBillIds"));
		}
		if(this.hasId("toolingDevFlag")){
			if(request.getParameter("toolingDevFlag").equals("TOOLING")){
				this.toolingDevFlag="TOOLING";
			}
			else{
				this.toolingDevFlag="DEVICE";
			}
		}
		if(this.hasId("tooling.id")){
			this.tooling=this.deviceCardManager.loadDevice(this.getId("tooling.id"));
		}
	}
	
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b> 函数处理
	 * 
	 * @return	String SUCCESS
	 */
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return this.enabled();
		}
		if (this.isDelete()) {
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除选择的故障单
	 * @return	String SUCCESS
	 */
	public String delete() {
		this.faultBillManager.deleteAllFaultBill(faultBill);
		if(toolingDevFlag.equalsIgnoreCase("TOOLING")){
			this.addActionMessage(this.getText("toolingFaultBill.delete.success"));
		}else{
			this.addActionMessage(this.getText("deviceFaultBill.delete.success"));
		}
		return SUCCESS;
	}
	
	public String disabled() {
		this.faultBillManager.disabledAllFaultBills(faultBill);
		if(toolingDevFlag.equalsIgnoreCase("TOOLING")){
			this.addActionMessage(this.getText("faultBillTooling.disabled.success"));
		}else {
			this.addActionMessage(this.getText("faultBillDevice.disabled.success"));
		}
		return SUCCESS;
	}

	public String enabled() {
		this.faultBillManager.enabledAllFaultBills(faultBill);
		if(toolingDevFlag.equalsIgnoreCase("TOOLING")){
			this.addActionMessage(this.getText("faultBillTooling.enabled.success"));
		}else {
			this.addActionMessage(this.getText("faultBillDevice.enabled.success"));
		}
		return SUCCESS;
	}
	
	/**
	 * 得到 <i>valueList</i>中配置的，查询hql的ID
	 * 同时根据"toolingDevFlag"判断是工装还是设备的查询，返回不同的ID
	 */
	@Override
	protected String getAdapterName() {
		if(this.toolingDevFlag.equals("TOOLING")){
			return "toolingFaultBills";
		}else{
			return "deviceFaultBills";
		}
	}

	/**
	 * 如果不是点击查询按钮，则默认是根据当前登陆人的部门来对日常检查结果进行筛选
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (!this.isSearch()) {
			map.put("department.id", this.getLoginUser().getDepartment().getId());
		}
		return map;
	}
	
	public List<FaultBill> getFaultBill() {
		return faultBill;
	}

	public void setFaultBill(List<FaultBill> faultBill) {
		this.faultBill = faultBill;
	}

	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
			List<Department> list = new ArrayList<Department>();
			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
				Department department = new Department();
				department.setId(Long.valueOf(-2L));
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
	
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	
	/**
	 * 获取故障类别集合
	 * @return List 故障类别集合
	 */
	public List<CodeValue> getFaultCatorgys(){
		CodeValue cv = new CodeValue();
		cv.setValue(this.getText("select.option.all"));
		List<CodeValue> list = this.codeValueManager.LoadAllValuesByCode(CodeConstants.FAULT_CATEGORY);
		list.add(0,cv);
		return list;
	}
	
	
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	public DeviceCard getTooling() {
		return tooling;
	}

	public void setTooling(DeviceCard tooling) {
		this.tooling = tooling;
	}

	public boolean isOnlyInvalid() {
		return onlyInvalid;
	}

	public void setOnlyInvalid(boolean onlyInvalid) {
		this.onlyInvalid = onlyInvalid;
	}

	public boolean isOnlyValid() {
		return onlyValid;
	}

	public void setOnlyValid(boolean onlyValid) {
		this.onlyValid = onlyValid;
	}

}
