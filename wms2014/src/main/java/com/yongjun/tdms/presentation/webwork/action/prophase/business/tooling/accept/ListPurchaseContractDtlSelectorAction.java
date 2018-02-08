package com.yongjun.tdms.presentation.webwork.action.prophase.business.tooling.accept;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.service.base.codevalue.ToolingTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;

public class ListPurchaseContractDtlSelectorAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private final DepartmentManager departmentManager;
	private final ToolingTypeManager toolingTypeManager;
	private final AcceptBillManager acceptBillManager;
	private String detailType;	
	private Long acceptId;
	private List<Long> oldPurchaseContractIds;
	public ListPurchaseContractDtlSelectorAction(DepartmentManager departmentManager,ToolingTypeManager toolingTypeManager,
			AcceptBillManager acceptBillManager){
		this.departmentManager=departmentManager;
		this.toolingTypeManager=toolingTypeManager;
		this.acceptBillManager=acceptBillManager;
	}
	@Override
	public void prepare() throws Exception {
		//季度计划明细的类别[工装制作|备件采购|维修保养|技术改造]
		if(!StringUtils.isEmpty(request.getParameter("detailType"))){
			detailType = request.getParameter("detailType");
		}
		//传入的采购单id
		if(this.hasId("acceptId")){
			acceptId = new Long(request.getParameter("acceptId"));
		}
	}
	@Override
	protected String getAdapterName() {
		
		return "toolingPurContractSelector";
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
				department.setId(Long.valueOf(-1L));
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
	 * 获取当前登陆的用户
	 * 
	 * @return User 用户实体
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	/**
	 * 获取工装类别集合
	 * @return List 工装类别集合
	 */
	public List getCategorys() {
		return this.toolingTypeManager.createSelectToolingType(this.getText("select.option.all"));
	}
	public String execute() {
			return SUCCESS;
	}
	@Override
	protected Map getRequestParameterMap() {
		
		oldPurchaseContractIds = new ArrayList<Long>();
		if(acceptId!=null){
			AcceptBill  acceptBill = acceptBillManager.loadAcceptBill(acceptId);
			for(AcceptBillDetail detail : acceptBill.getAcceptBillDtl()){
				if(detail.getPurchaseContractDtl()!=null){
					oldPurchaseContractIds.add(detail.getPurchaseContractDtl().getId());
				}
			}
		}
		Map map = super.getRequestParameterMap();
		//根据传入的季度计划明细的类别[工装制作|备件采购|维修保养|技术改造]来查询
        map.put("detailType",detailType);
        if(!oldPurchaseContractIds.isEmpty()){
        	//过滤掉采购单明细中已经存在的记录
        	map.put("acceptId",oldPurchaseContractIds);
        }
		return map;
	}
	public String getDetailType() {
		return detailType;
	}
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}
}
