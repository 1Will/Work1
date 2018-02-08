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
package com.yongjun.tdms.presentation.webwork.action.base.org;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.service.base.filiale.FilialeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.productionline.ProductionLineManager;

/**
 * <p>
 * Title: EditDepartmentAction
 * <p>
 * Description: 部门页面访问控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author qs@yj-technology.com
 * @version $Id: EditDepartmentAction.java 10818 2008-01-31 02:25:29Z qsun $
 */
@Deprecated
public class EditDepartmentAction extends PrepareAction {
	private static final long serialVersionUID = 3269015023775508432L;

	private final DepartmentManager departmentManager;

	private final ProductionLineManager productionLineManager;
	
	private final FilialeManager filialeManager;
	private Department department;
	private Filiale filiale;
	private List<ProductionLine> unJoinedProductionLines;

	private List<ProductionLine> joinedProductionLines;

	private ProductionLine productionLine;
	
	public EditDepartmentAction(DepartmentManager departmentManager,
			ProductionLineManager productionLineManager,FilialeManager filialeManager) {
		this.departmentManager = departmentManager;
		this.productionLineManager = productionLineManager;
		this.filialeManager = filialeManager;
	}

	/**
	 * 初始化，获取参数<b>department.id</b>，如果存在就获取这个部门实体， 如果不存在，就新建一个部门实体
	 */
	public void prepare() throws Exception {
		if (unJoinedProductionLines == null) {
			this.unJoinedProductionLines = this.departmentManager
					.getUnJoinedProductionLines();
		}
		if (null == this.department) {
			if (this.hasId("department.id")) {
				this.department = this.departmentManager.loadDepartment(this
						.getId("department.id"));
			} else {
				this.department = new Department();
			}
		}

		if (this.productionLine == null) {
			if (this.hasId("productionLine.id")) {
				this.productionLine = productionLineManager
						.loadProductionLine(this.getId("productionLine.id"));
			}
		}
		if (this.joinedProductionLines == null) {
			if (this.hasIds("productionLineIds")) {
				this.joinedProductionLines = this.productionLineManager
						.loadAllProductionLines(this
								.getIds("productionLineIds"));
			}
		}
	}

	/**
	 * 删除一个部门
	 * 
	 * @return String SUCCESS
	 */
	public String delete() {
		this.departmentManager.deleteDepartment(this.department);
		this.addActionMessage(this.getText("department.delete.success", Arrays
				.asList(new Object[] { department.getName() })));
		return SUCCESS;
	}

	/**
	 * 保存一个部门信息
	 * 
	 * @return String SUCCESS 或者 NEW
	 */
	public String save() {

		if (this.isDelete()) {
			return this.delete();
		}
		if (this.isJoinProductionLine()) {
			return this.JoinProductionLine();
		}
		if (this.isdeleteProductionLine()) {
			return this.deleteProductionLine();
		}

		boolean isNew = this.department.isNew();
		
		if(isNew){
			/** 
			 * 从数据库中查询是否已存在的部门代码
			 * 若存在，直接返回错误提示
			 * 若不存在，继续执行新增操作
			 */
			//定义保存新增的部门变量
			Department d = null;
			//根据部门标识
			if(this.department.getCode() != null){
				d = departmentManager.loadDepartmentByCode(this.department.getCode());
				if(d != null){
					this.addActionMessage(this.getText("department.code.exists", Arrays
						.asList(new Object[] { department.getCode() })));
					return ERROR;
				}else{
					d = departmentManager.loadDepartmentByName(this.department.getName());
					if(d != null){
						this.addActionMessage(this.getText("department.name.exists", Arrays
								.asList(new Object[] { department.getName() })));
						return ERROR;
					}
				}
			}
			/** 修改结束 */
		}

		if (!StringUtils.isEmpty(request.getParameter("filiale.id"))) {
			this.filiale = filialeManager.loadFiliale(this.getId("filiale.id"));
			department.setFiliale(filiale);
		}
		
		this.departmentManager.storeDepartment(department);
		
//		try {
//			this.departmentManager.storeDepartment(department);
//        } catch (RuntimeException e) {
//	        // TODO Auto-generated catch block
//	        e.printStackTrace();
//	        this.addActionMessage("<font color=red>部门代码"+department.getCode()+"已经存在</font>");
//	        return ERROR;
//        }
		
		if (isNew) {
			this.addActionMessage(this.getText("department.add.success", Arrays
					.asList(new Object[] { department.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("department.edit.success",
					Arrays.asList(new Object[] { department.getName() })));
			return SUCCESS;
		}
	}

	public Department getDepartment() {
		return department;
	}

	private boolean isJoinProductionLine() {
		return this.hasKey("join");
	}

	private boolean isdeleteProductionLine() {
		return this.hasKey("leave");
	}

	private String deleteProductionLine() {
		this.productionLineManager.deleteProductionLine(joinedProductionLines);
		this.addActionMessage(this.getText("department.delete.join.success"));
		return SUCCESS;
	}

	private String JoinProductionLine() {
		this.productionLineManager.joinProductionLine(department,
				productionLine);
		this.addActionMessage(this.getText("department.join.success", Arrays
				.asList(new Object[] { productionLine.getName(),
						department.getName() })));
		return SUCCESS;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<ProductionLine> getUnJoinedProductionLines() {
		return unJoinedProductionLines;
	}

	public void setUnJoinedProductionLines(
			List<ProductionLine> unJoinedProductionLines) {
		this.unJoinedProductionLines = unJoinedProductionLines;
	}

	public ProductionLine getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(ProductionLine productionLine) {
		this.productionLine = productionLine;
	}

	public List<ProductionLine> getJoinedProductionLines() {
		return joinedProductionLines;
	}

	public void setJoinedProductionLines(
			List<ProductionLine> joinedProductionLines) {
		this.joinedProductionLines = joinedProductionLines;
	}
	
	public List<Filiale> getFiliales(){
		return filialeManager.loadAllFiliale();
	}
}
