package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;

public class ListPurchaseBillDetailChooseAction extends ValueListAction {

	private static final long serialVersionUID = 1L;
    private final SubscribeManager subscribeManager;
	
	private List<SubscribeDetail> subscribeDetails;
	private SubscribeDetail subscribeDetail;
	private Subscribe subscribe;
	private Department department;
	private String detailType;//标识采购合同明细类型
	private final DepartmentManager departmentManager;
	private final ToolingTypeManager toolingTypeManager;
	public ListPurchaseBillDetailChooseAction(SubscribeManager subscribeManager,
					DepartmentManager departmentManager,ToolingTypeManager toolingTypeManager){
		this.subscribeManager=subscribeManager;
		this.departmentManager=departmentManager;
		this.toolingTypeManager = toolingTypeManager;
	}
	public void prepare() {
		if(!StringUtils.isEmpty(request.getParameter("detailType"))){
			detailType = request.getParameter("detailType");
		}
		if (null == this.subscribe) {
			if (this.hasId("subscribe.id")) {
				this.subscribe = this.subscribeManager.loadSubscribe(getId("subscribe.id"));
			} 
		}
		if (this.subscribeDetails == null && this.hasIds("subscribeDtlIds")) {
            this.subscribeDetails = this.subscribeManager.loadAllSubscribeDetails(this.getIds("subscribeDtlIds"));
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
			return list;
		}
		return departmentManager.createSelectDepartments(this
				.getText("select.option.all"));
	}
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
	public List getCategorys() {
		return this.toolingTypeManager.createSelectToolingType(this
				.getText("select.option.all"));
	}
	@Override
	protected String getAdapterName() {
		return "toolingchoosePurchases";
	}
	public User getLoginUser(){
    	return userManager.getUser();
    }
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public SubscribeDetail getSubscribeDetail() {
		return subscribeDetail;
	}
	public void setSubscribeDetail(SubscribeDetail subscribeDetail) {
		this.subscribeDetail = subscribeDetail;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("detailType",detailType);
		return map;
	}
}
