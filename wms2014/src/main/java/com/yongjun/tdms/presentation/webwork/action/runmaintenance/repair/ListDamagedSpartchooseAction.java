package com.yongjun.tdms.presentation.webwork.action.runmaintenance.repair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceSpare;
import com.yongjun.tdms.model.asset.device.ManagementLevel;

import com.yongjun.tdms.service.asset.device.DeviceSpareManager;
import com.yongjun.tdms.service.asset.device.DeviceTypeManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
public class ListDamagedSpartchooseAction extends ValueListAction{
   private static final long serialVersionUID = 1L;
   private final DeviceSpareManager toolingDevSpareManager;
   private final DepartmentManager departmentManager;
   private final CodeValueManager codeValueManager;
	private final DeviceTypeManager deviceTypeManager;
   private List<DeviceSpare> deviceSpares;
   public ListDamagedSpartchooseAction(DeviceSpareManager toolingDevSpareManager,DepartmentManager departmentManager,
		   CodeValueManager codeValueManager, DeviceTypeManager deviceTypeManager){
	   this.toolingDevSpareManager=toolingDevSpareManager;
	   this.departmentManager=departmentManager;
	   this.codeValueManager=codeValueManager;
	   this.deviceTypeManager= deviceTypeManager;
   }
   /**
	 * 根据页面传来的日常检查的ID集合,获取日常检查的集合对象
	 */
	public void prepare() {
		if (this.hasIds("damagedSpartIds")) {
			this.deviceSpares = this.toolingDevSpareManager.loadDeviceSpares(this.getIds("damagedSpartIds"));
		}
	}
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
	 * 获取设备类别所有值
	 * @return
	 */
	public List getDeviceTypes() {
		return deviceTypeManager.createSelectDeviceTypes(this
				.getText("select.option.all"));
	}
	/**
	 * 获取管理级别所有值
	 * @return
	 */
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
	/**
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}

	@Override
	protected String getAdapterName() {
	  return "damagedSparts";
	}
	public List<DeviceSpare> getDeviceSpares() {
		return deviceSpares;
	}
	public void setDeviceSpares(List<DeviceSpare> deviceSpares) {
		this.deviceSpares = deviceSpares;
	}

}
