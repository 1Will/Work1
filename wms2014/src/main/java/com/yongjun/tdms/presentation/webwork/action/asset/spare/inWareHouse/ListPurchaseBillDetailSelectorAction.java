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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.inWareHouse;

import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title:ListPurchaseBillDetailSelectorAction
 * <p>Description:采购单明细控制访问类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id:$
 */
public class ListPurchaseBillDetailSelectorAction extends ValueListAction {
	private static final long serialVersionUID = 319946063544888070L;
	private final DepartmentManager departmentManager;
	private String filterPurBillDtlIds; 		// 需要过滤得备件ID字符串
	private List<Long> oldSpareIds; 			// 需要过滤得备件ID集合
	@SuppressWarnings("unused")
	private Long spareBillId; 					//需要过滤备件的入库单Id
	private SpareInBill spareInBill;
	
	@SuppressWarnings("unused")
	private SpareInBillManager spareInBillManager;
	public ListPurchaseBillDetailSelectorAction(SpareInBillManager spareInBillManager,DepartmentManager departmentManager){
		this.spareInBillManager = spareInBillManager;
		this.departmentManager=departmentManager;
	}
	
	@Override
	public void prepare() throws Exception {
		if(this.hasId("spareBillId")){
			spareBillId = new Long(request.getParameter("spareBillId"));
			this.spareInBill = this.spareInBillManager.loadSpareInBill(spareBillId);
		}
		
	}
	
	
	
	/**
	 * 为一级入库单添加 供应商 查询条件
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if(null != this.spareInBill && null !=this.spareInBill.getWarehouse()){
			if(208L == this.spareInBill.getWarehouse().getStorageGrade().getId()){
				if(null != this.spareInBill.getSupplier()){
					map.put("supplier",this.spareInBill.getSupplier().getName());
				}
			}
		}
		
        
 
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "purBillDtls";
	}



	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	/**
	 * 过滤页面已经有的备件记录
	 */
//	@SuppressWarnings("unchecked")
//	protected Map getRequestParameterMap() {
//		oldSpareIds = new ArrayList<Long>();
//		SpareInBill spareInBill = spareInBillManager.loadSpareInBill(spareBillId);
//		for(SpareInBillDetail detail : spareInBill.getDetail()){
//			oldSpareIds.add(detail.getSpare().getId());
//		}
//	    //this.filterPurBillDtlIds = request.getParameter("oldPurBillDtlIds");	
//		Map map = super.getRequestParameterMap();
//		if(!oldSpareIds.isEmpty()){
//			map.put("filterPurBillDtlIds", oldSpareIds);
//		}
//		return map;
//	}
	/**
	 * 获取所有申购部门
	 * */
	@SuppressWarnings("unchecked")
	public List<Department> getDepartments(){
		List<Department> list = departmentManager.createSelectDepartments(this.getText("select.option.all"));
		return list;
	}
	
	public List<Long> getOldSpareIds() {
		return oldSpareIds;
	}
	public String getFilterPurBillDtlIds() {
		return filterPurBillDtlIds;
	}
	public void setFilterPurBillDtlIds(String filterPurBillDtlIds) {
		this.filterPurBillDtlIds = filterPurBillDtlIds;
	}
	public SpareInBill getSpareInBill() {
		return spareInBill;
	}
}
