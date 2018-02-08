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
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.intactness;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.runmaintenance.intactness.IntactnessManager;
/**
 * <p>Title: EditIntactnessAction
 * <p>Description: 设备鉴定维护访问控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 */
public class EditIntactnessAction extends PrepareAction {
	private static final long serialVersionUID = -495239302550468413L;
	
	private final IntactnessManager intactnessManager;
	private final UserManager userManager;
	private final DepartmentManager departmentManager;
	
	private Intactness intactnessBill;
	
	public EditIntactnessAction(IntactnessManager intactnessManager,
			UserManager userManager,
			DepartmentManager departmentManager) {
		this.intactnessManager = intactnessManager;
		this.userManager = userManager;
		this.departmentManager = departmentManager;
	}
	
	/**
	 * 根据页面传来的鉴定单的ID,获取鉴定单对象,如果没有,则创建一个新的鉴定单对象
	 */
	public void prepare() throws Exception {
		if (null == intactnessBill) {
			if (this.hasId("intactnessBill.id")) {
				this.intactnessBill = this.intactnessManager.loadIntactness(this.getId("intactnessBill.id"));
			} else {
				this.intactnessBill = new Intactness();
			}
		}
	}

	/**
	 * 点击保存按钮,保存鉴定单的信息
	 * @return SUCCECC | NEW
	 */
	public String save() {
		boolean isNew = intactnessBill.isNew();
		
		if (!StringUtils.isEmpty(request.getParameter("department.id"))) {
			intactnessBill.setDepartment(this.departmentManager.loadDepartment(this.getId("department.id")));
		}
		if (!StringUtils.isEmpty(request.getParameter("examiner"))) {
			intactnessBill.setExaminer(request.getParameter("examiner"));
		}
		this.intactnessManager.storeIntactness(intactnessBill);
		
		if (isNew) {
			 this.addActionMessage(this.getText("intactnessBill.add.success",
						Arrays.asList(new Object[] { intactnessBill.getName() })));
			 return NEW;
		} else {
			this.addActionMessage(this.getText("intactnessBill.edit.success",
					Arrays.asList(new Object[] { intactnessBill.getName() })));
			return SUCCESS;
		}
	}
	
	/**
	 * 获取所有的部门的值
	 * @return List 部门集合
	 */
	public List<Department> getDepartments() {
		return this.departmentManager.loadAllDepartments();
	}
	
	/**
	 * 获取当前登录的用户
	 * @return User 用户
	 */
	public User getLoginUser() {
		return this.userManager.getUser();
	}
	
	public Intactness getIntactnessBill() {
		return intactnessBill;
	}

	public void setIntactnessBill(Intactness intactnessBill) {
		this.intactnessBill = intactnessBill;
	}

}
