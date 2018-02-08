package com.yongjun.tdms.presentation.webwork.action.prophase.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBillTypeStatus;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;

public class EditSubscribeCollectBillAction extends PrepareAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3588089784350541783L;

	private final SubscribeCollectBillManager subscribeCollectBillManager;
	
	private SubscribeCollectBill subscribeCollectBill;
	
	private final UserManager userManager;
	
	private final DepartmentManager departmentManager;
	
	private User collectPerson=new User();
	

	public EditSubscribeCollectBillAction(SubscribeCollectBillManager subscribeCollectBillManager,
			UserManager userManager,
			 DepartmentManager departmentManager) {
		this.subscribeCollectBillManager = subscribeCollectBillManager;
		this.userManager=userManager;
		this.departmentManager=departmentManager;
	}


	public void prepare() throws Exception {
		if(this.hasId("subscribeCollectBill.id")){
			this.subscribeCollectBill = this.subscribeCollectBillManager.loadSubscribeCollectBill(this.getId("subscribeCollectBill.id"));
	    }else{
	    	this.subscribeCollectBill = new SubscribeCollectBill();
	    }			
	}
	

	public String save() {
		boolean isNew = subscribeCollectBill.isNew();
		
		
		if(!StringUtils.isEmpty(request.getParameter("billStatus"))){		 //获得申购单的状态
			if (request.getParameter("billStatus").equals("NEW")) {
				subscribeCollectBill.setBillStatus(SubscribeCollectBillTypeStatus.NEW);
			}
			if(request.getParameter("billStatus").equals("PURCHASING")){
				subscribeCollectBill.setBillStatus(SubscribeCollectBillTypeStatus.PURCHASING);
			}
			if(request.getParameter("billStatus").equals("PURCHASEED")){
				subscribeCollectBill.setBillStatus(SubscribeCollectBillTypeStatus.PURCHASEED);
			}
		}
		if (!StringUtils.isEmpty(request.getParameter("collectDept.id"))) {//获得部门 
			subscribeCollectBill.setCollectDept(this.departmentManager.loadDepartment(this.getId("collectDept.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("collectPerson.id"))) { //获得申购汇总人
			this.collectPerson = userManager.loadUser(this.getId("collectPerson.id"));
			subscribeCollectBill.setCollectPerson(collectPerson);
		}
		
		
		if(isNew){
			subscribeCollectBill.setCode(subscribeCollectBillManager.generateSubscribeCollectBillCode());
			subscribeCollectBillManager.storeSubscribeCollectBill(subscribeCollectBill);
		
			this.addActionMessage(this.getText("subscribeCollectBill.add.success",
	                Arrays.asList(new Object[]{subscribeCollectBill.getName()})));
           return NEW;
		}else{
			subscribeCollectBillManager.storeSubscribeCollectBill(subscribeCollectBill);
			this.addActionMessage(this.getText("subscribeCollectBill.edit.success",
					Arrays.asList(new Object[]{subscribeCollectBill.getName()})));
			return SUCCESS;
		}
		 
	}
	
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
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
			return list;
		}
		return this.departmentManager.loadAllDepartments();
	}
	
	/**
	 * 类型选择
	 * @return
	 */
	public List<LabelValue> getStatus() {  
		LabelValue[] arrays = this.wrapEnum(SubscribeCollectBillTypeStatus.class);
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	


	public SubscribeCollectBill getSubscribeCollectBill() {
		return subscribeCollectBill;
	}


	public void setSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.subscribeCollectBill = subscribeCollectBill;
	}


	public User getCollectPerson() {
		return collectPerson;
	}


	public void setCollectPerson(User collectPerson) {
		this.collectPerson = collectPerson;
	}

}
