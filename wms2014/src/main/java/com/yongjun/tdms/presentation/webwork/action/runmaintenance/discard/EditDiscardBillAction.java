package com.yongjun.tdms.presentation.webwork.action.runmaintenance.discard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardBillManager;

public class EditDiscardBillAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
    private final DiscardBillManager  discardBillManager;
    private final DepartmentManager  departmentManager;
    private final CodeValueManager  codeValueManager;
    private final UserManager userManager;
    private String isTouch;
    private CodeValue discardCategory;   //报废类别
    private DiscardBill discardBill;   //设备报废单对象
    public EditDiscardBillAction(DiscardBillManager  discardBillManager,DepartmentManager  departmentManager,
    		CodeValueManager  codeValueManager,UserManager userManager){
    	this.discardBillManager=discardBillManager;
    	this.departmentManager=departmentManager;
    	this.codeValueManager=codeValueManager;
    	this.userManager=userManager;
    }
	public void prepare() throws Exception {
		if(this.discardBill==null){            //通过从页面传来的值 获得报废单的对象
			if(this.hasId("discardBill.id")){
				this.discardBill=discardBillManager.loadDiscardBill(this.getId("discardBill.id"));
			}else{
				discardBill=new DiscardBill();
			}
		}
		
			//给据页面传来的隐藏的isTouch,判断是否点击确认报废,
		if (!StringUtils.isEmpty(request.getParameter("isTouch"))) {
			this.isTouch = request.getParameter("isTouch");
			
			}
			
		
		
	}
	public String save() {
		boolean isNew = this.discardBill.isNew();
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {//获取部门
			discardBill.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("reportUser.id"))) {//获取申购人
			discardBill.setReportUser(this.userManager.loadUser(this.getId("reportUser.id")));
		}
       if (!StringUtils.isEmpty(request.getParameter("discardCategory"))) {   //获取报废类别
        	 discardBill.setDiscardCategory(this.codeValueManager.loadCodeValue(this.getId("discardCategory")));
		} else {
			discardBill.setDiscardCategory(null);
		}
       if (!StringUtils.isEmpty(request.getParameter("discardAgree"))) {   //获取报废类别
    	   discardBill.setDiscardAgree(true);
		}else{
			  discardBill.setDiscardAgree(false);
		}
		 discardBillManager.storeDiscardBill(discardBill);
	    
		 if (isNew) {
			addActionMessage(getText("discardBill.add.success", Arrays
					.asList(new Object[] { discardBill.getBillNo() })));
			return NEW;
		} else {
			addActionMessage(getText("discardBill.edit.success", Arrays
					.asList(new Object[] { discardBill.getBillNo() })));
			return SUCCESS;
		}
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
		return departmentManager.loadAllDepartments();
	}
	/**
	 * 获得报废类别
	 * @return
	 */
	public List getDiscardCategorys() {
		//return this.codeValueManager.createSelectCodeValues(this.getText(""), CodeConstants.DISCARD_CATEGORY);
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.DISCARD_CATEGORY);
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	public DiscardBill getDiscardBill() {
		return discardBill;
	}
	public void setDiscardBill(DiscardBill discardBill) {
		this.discardBill = discardBill;
	}
	public CodeValue getDiscardCategory() {
		return discardCategory;
	}
	public void setDiscardCategory(CodeValue discardCategory) {
		this.discardCategory = discardCategory;
	}
	public String getIsTouch() {
		return isTouch;
	}
	public void setIsTouch(String isTouch) {
		this.isTouch = isTouch;
	}

}
