package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBillTypeStatus;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;

public class ListSubscribeCollectBillAction extends ValueListAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7732206378681030650L;
	
	private final SubscribeCollectBillManager subscribeCollectBillManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	
	private List<SubscribeCollectBill> subscribeCollectBills;
	
	public ListSubscribeCollectBillAction(SubscribeCollectBillManager subscribeCollectBillManager,
			UserManager userManager,
			DepartmentManager departmentManager,
			CodeValueManager codeValueManager) {
		this.subscribeCollectBillManager = subscribeCollectBillManager;
		this.userManager=userManager;
		this.departmentManager=departmentManager;
		this.codeValueManager = codeValueManager;
	}


	@Override
	public void prepare() throws Exception {
		if (this.hasIds("subscribeCollectBillIds")) {
			this.subscribeCollectBills = this.subscribeCollectBillManager
					.loadAllSubscribeCollectBills(this.getIds("subscribeCollectBillIds"));
		} else {
			subscribeCollectBills = new ArrayList<SubscribeCollectBill>();
		}
	}
	
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
 
	public String disabled() {
		return null;
	}

	public String enabled() {
		return null;	
	
	}
	
	
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	
//	获得验收单所有部门
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
	
	public List<LabelValue> getStatus(){
		LabelValue[] arrays = this.wrapEnum(SubscribeCollectBillTypeStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}		
		return tmp;
	}
	
	
	 /**
	 * 获得单据类型
	 * @return
	 */
	
	public List getAllDetailKind() {
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.DETAIL_KIND);
	}
	

	@Override
	protected String getAdapterName() {	
		return "SubscribeCollectBillHQL";  
	}

	
	
	public List<SubscribeCollectBill> getSubscribeCollectBills() {
		return subscribeCollectBills;
	}


	public void setSubscribeCollectBills(
			List<SubscribeCollectBill> subscribeCollectBills) {
		this.subscribeCollectBills = subscribeCollectBills;
	}

}
