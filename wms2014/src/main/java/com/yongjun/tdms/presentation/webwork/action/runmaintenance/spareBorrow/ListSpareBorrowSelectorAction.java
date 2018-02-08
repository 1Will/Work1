package com.yongjun.tdms.presentation.webwork.action.runmaintenance.spareBorrow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;

/**
 * <p>Title:ListSpareBorrowSelectorAction
 * <p>Description:备件领用明细控制访问类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author xschen@yj-technology.com
 * @version $Id:$
 */
public class ListSpareBorrowSelectorAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private String filterPurBillDtlIds; 		// 需要过滤得备件ID字符串
	private List<Long> oldSpareIds; 			// 需要过滤得备件ID集合
	private Long spareBillId; 					//需要过滤备件的入库单Id
	private SpareInBillManager spareInBillManager;
	private final DepartmentManager departmentManager;
	private final UserManager userManager;
	public ListSpareBorrowSelectorAction(SpareInBillManager spareInBillManager,DepartmentManager departmentManager,
			UserManager userManager){
		this.spareInBillManager = spareInBillManager;
		this.departmentManager=departmentManager;
		this.userManager=userManager;
	}
	@Override
	public void prepare() throws Exception {
		if(this.hasId("spareBillId")){
			spareBillId = new Long(request.getParameter("spareBillId"));
		}
	}
	/**
	 * 获得选择的部门列表
	 * 
	 * @return List 部门集合
	 */
	public List getDepartments() {
		return this.departmentManager.createSelectDepartments(this.getText("select.option.all"));
	}
	/**
	 * 
	 * 获取当前所有登陆用户
	 * @return
	 */
    public User getLoginUser(){
    	return userManager.getUser();
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
////		this.filterPurBillDtlIds = request.getParameter("oldPurBillDtlIds");	
//		Map map = super.getRequestParameterMap();
//		if(!oldSpareIds.isEmpty()){
//			map.put("filterPurBillDtlIds", oldSpareIds);
//		}
//		return map;
//	}
	@Override
	protected String getAdapterName() {
		return "spareBorrowSelectors";
	}
	public String getFilterPurBillDtlIds() {
		return filterPurBillDtlIds;
	}
	public void setFilterPurBillDtlIds(String filterPurBillDtlIds) {
		this.filterPurBillDtlIds = filterPurBillDtlIds;
	}
	public List<Long> getOldSpareIds() {
		return oldSpareIds;
	}
	public void setOldSpareIds(List<Long> oldSpareIds) {
		this.oldSpareIds = oldSpareIds;
	}
	public Long getSpareBillId() {
		return spareBillId;
	}
	public void setSpareBillId(Long spareBillId) {
		this.spareBillId = spareBillId;
	}

}
