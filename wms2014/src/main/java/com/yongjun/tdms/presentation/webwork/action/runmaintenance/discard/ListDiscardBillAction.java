package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillManager;

public class ListDiscardBillAction extends ValueListAction{

	private static final long serialVersionUID = 1L;
    private final DiscardBillManager discardBillManager;  
    private final DepartmentManager departmentManager;
    private List<DiscardBill> discardBills;       //获得报废单集合
    private DiscardBill discardBill;
    public ListDiscardBillAction(DiscardBillManager discardBillManager,
    		DepartmentManager departmentManager){
    	  this.discardBillManager=discardBillManager;
    	  this.departmentManager=departmentManager;
    }
    public void prepare() throws Exception {
		
		if (this.discardBills == null && this.hasIds("discardBillIds")) {  //给据页面传来的Ids获得报废单集合
			this.discardBills = this.discardBillManager.loadAllDiscardBills(this
					.getIds("discardBillIds"));
			
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
    public String disabled() {
		this.discardBillManager.disabledAllDiscardBills(discardBills);
		this.addActionMessage(this.getText("discard.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.discardBillManager.enabledAllDiscardBills(discardBills);
		this.addActionMessage(this.getText("discard.enabled.success"));
		return SUCCESS;
	}
	
	//获得使用部门
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
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	@Override
	protected String getAdapterName() {
		
		return "discardBillIds";
	}
	public List<DiscardBill> getDiscardBills() {
		return discardBills;
	}
	public void setDiscardBills(List<DiscardBill> discardBills) {
		this.discardBills = discardBills;
	}
	public DiscardBill getDiscardBill() {
		return discardBill;
	}
	public void setDiscardBill(DiscardBill discardBill) {
		this.discardBill = discardBill;
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
