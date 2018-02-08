package com.yongjun.tdms.presentation.webwork.action.runmaintenance.spareBorrow;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.prophase.business.SubscribeStatus;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowStatus;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowManager;

public class EditSpareBorrowAction extends PrepareAction{
	private static final long serialVersionUID = 1L;
    private final SpareBorrowManager spareBorrowManager;
    private final DepartmentManager departmentManager;
    private final UserManager  userManager;
    private SpareBorrow  spareBorrow;
    private User borrowUser;
    private User appUser;
    private String toolingDevFlag; //标示[工装][设备]
    private Department department;
    public EditSpareBorrowAction(SpareBorrowManager spareBorrowManager,DepartmentManager departmentManager,
    		UserManager  userManager){
    	this.spareBorrowManager=spareBorrowManager;
    	this.departmentManager=departmentManager;
    	this.userManager=userManager;
    }
	public void prepare() throws Exception {
		if(null==spareBorrow){
			   if(this.hasId("spareBorrow.id")){
				   spareBorrow=this.spareBorrowManager.loadSpareBorrow(this.getId("spareBorrow.id"));
			   }else{
				   this.spareBorrow=new SpareBorrow();
				   spareBorrow.setBorrowUser(userManager.getUser());
				   spareBorrow.setStatus(SpareBorrowStatus.NEWSTATUS);
			   }
		}
		if(this.hasId("toolingDevFlag")){
			this.toolingDevFlag=request.getParameter("toolingDevFlag");
		}
	}
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String save(){
		boolean isNew = this.spareBorrow.isNew();
		//获得领用人
		if (!StringUtils.isEmpty(request.getParameter("spareBorrow1.borrowUser.id"))) {
			this.borrowUser = userManager.loadUser(this.getId("spareBorrow1.borrowUser.id"));
		
			spareBorrow.setBorrowUser(borrowUser);
		}
		if (!StringUtils.isEmpty(request.getParameter("spareBorrow1.approvalUser.id"))) {
			this.appUser = userManager.loadUser(this.getId("spareBorrow1.approvalUser.id"));
		
			spareBorrow.setApprovalUser(appUser);
		}
		//获得部门
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			this.department = departmentManager.loadDepartment(this.getId("department.id"));
			//将部门保存到出库单中
			spareBorrow.setDepartment(department);
		}
		if(!StringUtils.isEmpty(request.getParameter("status"))){	
			
			if (request.getParameter("status").equals("NEWSTATUS")) {
				spareBorrow.setStatus(SpareBorrowStatus.NEWSTATUS);
			}
			if(request.getParameter("status").equals("BORROWING")){
				spareBorrow.setStatus(SpareBorrowStatus.BORROWING);
			}
			if(request.getParameter("status").equals("BORROWED")){
				spareBorrow.setStatus(SpareBorrowStatus.BORROWED);
			}
		}
		if(toolingDevFlag.equals(SysModel.DEVICE.toString())){
			spareBorrow.setToolingDevFlag(SysModel.DEVICE);
		}
		else{
			spareBorrow.setToolingDevFlag(SysModel.TOOLING);
		}
		spareBorrowManager.storeSpareBorrow(spareBorrow);
		//日志
		String logContent = "";
		logContent = spareBorrow.getBillNo();
		this.getLogger().logStore(spareBorrow, logContent, isNew);
		
		if (isNew) {
			this.addActionMessage(this.getText("spareBorrow.add.success",
					Arrays.asList(new Object[] { spareBorrow.getBillNo() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("spareBorrow.edit.success",
					Arrays.asList(new Object[] { spareBorrow.getBillNo() })));
			return SUCCESS;
		}
	}
	public List<Department> getDepartments(){
		List<Department> list = departmentManager.loadAllDepartments();
		return list;
	}
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	public SpareBorrow getSpareBorrow() {
		return spareBorrow;
	}
	public void setSpareBorrow(SpareBorrow spareBorrow) {
		this.spareBorrow = spareBorrow;
	}
	public User getBorrowUser() {
		return borrowUser;
	}
	public void setBorrowUser(User borrowUser) {
		this.borrowUser = borrowUser;
	}
	public User getAppUser() {
		return appUser;
	}
	public void setAppUser(User appUser) {
		this.appUser = appUser;
	}
	public String getToolingDevFlag() {
		return toolingDevFlag;
	}
	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}


}
