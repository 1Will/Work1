/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.spareBorrow;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;

import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;

import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowStatus;

import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowManager;
/**
 * <p>Title: ListSpareBorrowAction
 * <p>Description: 备件领用页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: ListSpareBorrowAction.java 9149 2007-12-09 06:29:38Z xschen $
 */
public class ListSpareBorrowAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
    private final SpareBorrowManager spareBorrowManager;
    private final DepartmentManager  departmentManager;
    private final UserManager userManager;
    private List<SpareBorrow> spareBorrowBills;
    private String toolingDevFlag;
    public ListSpareBorrowAction(SpareBorrowManager spareBorrowManager,DepartmentManager  departmentManager
    		,UserManager userManager){
    	this.spareBorrowManager=spareBorrowManager;
    	this.departmentManager=departmentManager;
    	this.userManager=userManager;
    }
    /**
	 * 获取页面参数 <b>accidentBillIds</b> ,如果得到，根据ID获取事故单
	 * 同时根据传递的"toolingDevFlag"来判断是工装还是设备的报废单
	 */
    public void prepare(){
    	if (this.hasId("spareBorrowIds")) {
			this.spareBorrowBills = this.spareBorrowManager.loadAllSpareBorrows(this.getIds("spareBorrowIds"));
		}
		if(this.hasId("toolingDevFlag")){
			if(request.getParameter("toolingDevFlag").equals("TOOLING")){
				this.toolingDevFlag="TOOLING";
			}
			else {
				this.toolingDevFlag="DEVICE";
			}
		
		}
    }
	/**
	 * 页面执行，如果选择了删除就调用 <b>delete</b> 函数处理
	 * 
	 * @return	String SUCCESS
	 */
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
	    try{
		     this.spareBorrowManager.disabledAllSpareBorrows(spareBorrowBills);
	      }catch(Exception e){
		      e.printStackTrace();
		      this.addActionMessage(this.getText("spareBorrow.disabled.farile"));
		     return ERROR;
	      }
		this.addActionMessage(this.getText("spareBorrow.disabled.success"));
		return SUCCESS;
	}

	public String enabled() {
		this.spareBorrowManager.enabledAllSpareBorrows(spareBorrowBills);
		this.addActionMessage(this.getText("spareBorrow.enabled.success"));
		return SUCCESS;
	}
	private boolean isEnabled() {
		return this.hasKey("enabled");
	}
	@Override
	protected String getAdapterName() {
		if(this.toolingDevFlag.equals("TOOLING")){
			return "toolingspareBorrowss";
		}else{		
			return "devicespareBorrowss";
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
	
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
    }
	public List<LabelValue> getBorrowStatus() {  //获得备件领用单状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(SpareBorrowStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
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
	 * 获得选择的部门列表
	 * 
	 * @return List 部门集合
	 */
//	public List getDepartments() {
//		return this.departmentManager.createSelectDepartments(this.getText("select.option.all"));
//	}
//	
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}
	public List<SpareBorrow> getSpareBorrowBills() {
		return spareBorrowBills;
	}
	public void setSpareBorrowBills(List<SpareBorrow> spareBorrowBills) {
		this.spareBorrowBills = spareBorrowBills;
	}

}
