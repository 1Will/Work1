package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
/**
 * @author zzb
 * @version 查询汇总单明细

 */
 
public class ListSubscribeCollectBillDetaiLonelylAction extends ValueListAction{
  
	private final SubscribeManager subscribeManager;
	private final SubscribeCollectBillManager subscribeCollectBillManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	
	public ListSubscribeCollectBillDetaiLonelylAction(
			SubscribeManager subscribeManager,
			DepartmentManager departmentManager,
			UserManager userManager,
			SubscribeCollectBillManager subscribeCollectBillManager){
		this.subscribeManager = subscribeManager;
		this.departmentManager =departmentManager;
		this.userManager = userManager;
		this.subscribeCollectBillManager =subscribeCollectBillManager;
		
		
	}
	public void prepare(){
	}
	
	public String execute(){
		return SUCCESS;
	}
	
    /**
     * 获得验收单所有部门
     * @return
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
	 * 状态
	 * @return
	 */
	public List<LabelValue> getStatus(){
		LabelValue[] arrays = this.wrapEnum(SubscribeDetailStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}		
		return tmp;
	}
	
	@Override
	protected String getAdapterName() {
		return "collBillDetailLonely";
	}
	

}
