package com.yongjun.tdms.presentation.webwork.action.runmaintenance.migrate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateManager;
public class ListMigrateAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final MigrateManager migrateManager;  
	private final DepartmentManager departmentManager;
	private String  toolingDevFlag;//标识[工装]或[设备]
	private List<Migrate> migrates;
	public ListMigrateAction(MigrateManager migrateManager,DepartmentManager departmentManager){
		this.migrateManager=migrateManager;
		this.departmentManager=departmentManager;
	}
	@Override
	protected String getAdapterName() {
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			
			return "devicemMigrates";
		} else {
			return "toolingMigrates";
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
	
	public void prepare() throws Exception {
		if(this.hasId("toolingDevFlag")){
			if (request.getParameter("toolingDevFlag").equals("DEVICE")) {
				this.toolingDevFlag = "DEVICE";
			} else {
				this.toolingDevFlag = "TOOLING";
			}
		}
		if (this.migrates == null && this.hasIds("migrateIds")) {
			this.migrates = this.migrateManager.loadMigrateds(this
					.getIds("migrateIds"));
			
		}
	}
	public String execute() {
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnabled()) {
			return this.enabled();
		}
		return SUCCESS;
	}
//	获得转移单所有部门
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
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	public String disabled() {
		this.migrateManager.disabledAllMigrates(migrates);
		this.addActionMessage(this.getText("migrates.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.migrateManager.enabledAllMigrates(migrates);
		this.addActionMessage(this.getText("migrates.enabled.success"));
		return SUCCESS;
	}

	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	public List<Migrate> getMigrates() {
		return migrates;
	}

	public void setMigrates(List<Migrate> migrates) {
		this.migrates = migrates;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

}
