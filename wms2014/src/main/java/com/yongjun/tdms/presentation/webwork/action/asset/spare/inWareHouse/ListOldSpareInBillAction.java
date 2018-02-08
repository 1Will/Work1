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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillStatus;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * <p>Title:ListSpareInBillAction
 * <p>Description:入库单访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class ListOldSpareInBillAction extends ValueListAction {
	private static final long serialVersionUID = -3347771591059187305L;
	
	private List<SpareInBill> spareInBills;
	private final SpareInBillManager spareInBillManager;
	private final WarehouseManager warehouseManager;
	private final com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
    newwarehouseManager;
	private final CodeValueManager codeValueManager;
	
	//显示查询出来的总金额
	private Double showTotalPrice = 0.0;
	
	private String isOpen ;
	
	//登陆用户所属权限仓库ID值集合
	private List<Long> warehouseIds= new ArrayList<Long>();
	//登陆用户
	private User user;
	
	public ListOldSpareInBillAction(SpareInBillManager spareInBillManager, 
			WarehouseManager warehouseManager,
			CodeValueManager codeValueManager, com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager
		    newwarehouseManager){
		
		this.spareInBillManager = spareInBillManager;
		this.warehouseManager = warehouseManager;
		this.codeValueManager = codeValueManager;
		this.newwarehouseManager = newwarehouseManager;
	}
	@Override
	public void prepare() throws Exception {
		if(null ==spareInBills && this.hasIds("spareInBillIds") ){
			this.spareInBills = spareInBillManager.loadAllSpareInBill(this.getIds("spareInBillIds"));
		}
		//加默认权限仓库，解决权限仓库空值sql出错问题
		warehouseIds.add(-1L);
		//获取登陆用户所属权限仓库ID值集合
		if(null != getAllWarehouse()){
			for(Warehouse warehouse:getAllWarehouse()){
				warehouseIds.add(warehouse.getId());
			}
		}
		if(this.showTotalPrice()!=null){
			showTotalPrice=this.showTotalPrice();
		}
		isOpen = request.getParameter("isOpen");
		if(isOpen == null){
			isOpen = "false";
		}
	}
	
	@Override
	public String execute() throws Exception {
		if(this.isDisabled()){
			return disabled();
		}
		if(this.isEnabled()){
			return enabled();
		}
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除入库单
	 * 
	 */
	public String delete(){
		this.spareInBillManager.deleteAllSpareInBill(spareInBills);
		this.addActionMessage(this.getText("spareInBill.delete.success"));
		return SUCCESS;
	}
	
	/**
	 * 失效所以的入库单
	 * 
	 */
	public String disabled(){
		try {
			this.spareInBillManager.disabledAllSpareInBill(spareInBills);
			this.addActionMessage(this.getText("spareInBill.disasbled.success"));
			return SUCCESS;
		} catch (Exception e) {
			this.addActionError(this.getText("spareInBill.disasbled.farile"));
		}
		return SUCCESS;
	}
	/**
	 * 有效所有的入库单
	 * 
	 */
	public String enabled(){
		try {
			this.spareInBillManager.enabledAllSpareInBill(spareInBills);
			this.addActionMessage(this.getText("spareInBill.enabled.success"));
			return SUCCESS;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	public List<LabelValue> getInStatus() {  //获得备件领用单状态为枚举类型的值
		LabelValue[] arrays = this.wrapEnum(SpareInBillStatus.class);
		LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(),this.getText("select.option.all"));
		List<LabelValue> tmp = new ArrayList<LabelValue>();
		tmp.add(labelValue);
		for (int i = 0; i < arrays.length; i++) {
			tmp.add(arrays[i]);
		}
		return tmp;
	}
	
	/**
	 * 获得库存级别
	 * @return
	 */
	public List getAllStorageGrade(){
		return this.codeValueManager.LoadAllValuesByCode(CodeConstants.STORAGE_GRADE);
	}
	
	
	
	/**
	 * 系统中的所属仓库全部清除，只有权限仓库存在。故将用户的所属仓库从下拉列表中去除。
	 * 通过session中的User获得user的Id，在通过User从数据库中从新查询一次，以保证数据的实时更新。
	 * hjia 2010-5-21
	 * 
	 * @function 获得用户的所有仓库
	 * @return
	 */
	public List<Warehouse> getAllWarehouse(){
		
		List<Warehouse> wareList = new ArrayList<Warehouse>();
		User user = this.userManager.getUser();
		List<Warehouse> allWa = null;
		try {
			allWa = newwarehouseManager.loadByUser(user.getId());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Warehouse  ws :allWa){
			if(ws.getOldWarehouse()!=null&&ws.getOldWarehouse().equals("Y")){
				wareList.add(ws);
			}
			
		}
		return wareList;
	}
	
	/**
	 * 根据上下文，获得按钮名称为enabled的按钮
	 * @return
	 */
	private boolean isEnabled(){
		return this.hasKey("enabled");
	}
	@Override
	protected String getAdapterName() {
		if(request.getParameter("oldWhouseCode")!=null&&!request.getParameter("oldWhouseCode").equals("")){
			return "oldSpareInBillstoOut";
		}
		return "oldSpareInBills";
	}
	
	/**
	 * 收集页面的查询条件，返回总金额
	 * 
	 * 将仓库加入查询条件
	 * hjia 2010-5-10
	 * @return
	 * @author mfzhang 2009/12/11
	 */
	private Double showTotalPrice() {
		String spareInBillNo = "";
		String spareInBillName = "";
		String inPeople = "";
		String checkPeople = "";
		String supplierName = "";
		String inDate_start = "";
		String inDate_end = "";
		String warehouseId = "";
		String onlyInvalid = "";
		String onlyValid = "";
		String inStatus = "";
		String storageGradeId = "";
		if (!StringUtils.isEmpty(request.getParameter("spareInBillNo"))) {
//			System.out.println(request.getParameter("spareInBillNo"));
			spareInBillNo = request.getParameter("spareInBillNo");
		}
		if (!StringUtils.isEmpty(request.getParameter("spareInBillName"))) {
//			System.out.println(request.getParameter("spareInBillName"));
			spareInBillName = request.getParameter("spareInBillName");
		}
		if (!StringUtils.isEmpty(request.getParameter("inPeople"))) {
//			System.out.println(request.getParameter("inPeople"));
			inPeople = request.getParameter("inPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("checkPeople"))) {
//			System.out.println(request.getParameter("checkPeople"));
			checkPeople = request.getParameter("checkPeople");
		}
		if (!StringUtils.isEmpty(request.getParameter("supplierName"))) {
//			System.out.println(request.getParameter("supplierName"));
			supplierName = request.getParameter("supplierName");
		}
		if (!StringUtils.isEmpty(request.getParameter("inDate_start"))) {
//			System.out.println(request.getParameter("inDate_start"));
			inDate_start = request.getParameter("inDate_start");
		}
		if (!StringUtils.isEmpty(request.getParameter("inDate_end"))) {
//			System.out.println(request.getParameter("inDate_end"));
			inDate_end = request.getParameter("inDate_end");
		}
		if (!StringUtils.isEmpty(request.getParameter("warehouse.id"))) {
//			System.out.println(request.getParameter("warehouse.id"));
			warehouseId = request.getParameter("warehouse.id");
		} else if(warehouseIds != null && warehouseIds.size() > 0) {
			warehouseId = "(";
			for(Long warehouse : warehouseIds) {
				warehouseId += warehouse + ",";
			}
			warehouseId += "null)";
		}
		if (!StringUtils.isEmpty(request.getParameter("onlyInvalid"))) {
//			System.out.println(request.getParameter("onlyInvalid"));
			onlyInvalid = request.getParameter("onlyInvalid");
		}
		if (!StringUtils.isEmpty(request.getParameter("onlyValid"))) {
//			System.out.println(request.getParameter("onlyValid"));
			onlyValid = request.getParameter("onlyValid");
		}
		if (!StringUtils.isEmpty(request.getParameter("inStatus"))) {
//			System.out.println("inStatus: " + request.getParameter("inStatus"));
			inStatus = request.getParameter("inStatus");
		}
		if (!StringUtils.isEmpty(request.getParameter("storageGrade.id"))) {
			storageGradeId = request.getParameter("storageGrade.id");
		}
		String[] info = new String[] { spareInBillNo, spareInBillName,
				inPeople, checkPeople, supplierName, inDate_start, inDate_end,
				warehouseId,onlyInvalid, onlyValid, inStatus,storageGradeId };
		return this.spareInBillManager.showOldTotalPrice(info);
//		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		if (null != warehouseIds) {
			map.put("warehouseIds", warehouseIds);
		}
		String  instatus = (String)map.get("inStatus");
		if(instatus!=null&&instatus.equals("-1")){
			map.remove("inStatus");
		}
		return map;
	}
	
	/**
	 * @return the showTotalPrice
	 */
	public Double getShowTotalPrice() {
		return showTotalPrice;
	}
	/**
	 * @param showTotalPrice the showTotalPrice to set
	 */
	public void setShowTotalPrice(Double showTotalPrice) {
		this.showTotalPrice = showTotalPrice;
	}
	public User getUser() {
		return this.userManager.getUser();
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(String isOpen) {
		this.isOpen = isOpen;
	}
	

}
