package com.yongjun.tdms.presentation.webwork.action.runmaintenance.unused;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsed;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsedStatus;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.unused.UnusedManager;

public class listunusedAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final UnusedManager unusedManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private List<UnUsed> unuseds;         //闲置单集合
	private String toolingDevFlag;       //标示是工装或是设备
	public listunusedAction(UnusedManager unusedManager,UserManager userManager,DepartmentManager departmentManager){
		this.unusedManager=unusedManager;
		this.userManager=userManager;
		this.departmentManager=departmentManager;
	}
	public void prepare() throws Exception {
		if (this.unuseds == null && this.hasIds("unusedIds")) {
			this.unuseds = this.unusedManager.loadUnUseds(this.getIds("unusedIds"));//根据页面传来的unusedIds 获得闲置单的集合
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag=request.getParameter("toolingDevFlag");        //根据页面传来的toolingDevFlag值  判断是工装或是设备
		}
	}
	public String execute() {
		if (this.isDisabled()) {     //判断isDisabled()的值是否为空 如果不为空  调用失效的函数处理
			return disabled();
		}
		if (this.isEnabled()) {      //判断isEnabled()的值是否为空  如果不为空   调用有效的处理函数
			return this.enabled();
		}
		return SUCCESS;
	}
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	public String disabled() {
		this.unusedManager.disabledAllUnUseds(unuseds);
		this.addActionMessage(this.getText("unused.disabled.success"));
		return SUCCESS;
	}
	public String enabled() {
		this.unusedManager.enabledAllUnUseds(unuseds);
		this.addActionMessage(this.getText("unused.enabled.success"));
		return SUCCESS;
	}
	
	@Override
	protected String getAdapterName() {          //getAdapterName()是覆盖了valueListAction中的方法  可以说是重写了此方法  它根据页面includeParemert包含的参数到
      if(toolingDevFlag.equals(SysModel.DEVICE.toString())){//到valueList 配置文件中填充这些参数进行查询
    	  return  "deviceunuseds";
      }else{
		return "toolingunuseds";
      }
	}
	public List<LabelValue> getUnusedStatus() {  //获得闲置状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(UnUsedStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
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
}
