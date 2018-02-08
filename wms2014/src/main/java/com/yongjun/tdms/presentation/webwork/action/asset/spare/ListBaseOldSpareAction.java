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
package com.yongjun.tdms.presentation.webwork.action.asset.spare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.codevalue.SpareDetailType;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;
import com.yongjun.tdms.service.asset.spare.SpareDetailTypeManager;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;
import com.yongjun.tdms.service.base.codevalue.SpareTypeManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowManager;

/**
 * <p>Title: ListBaseSpareAction
 * <p>Description: 备件页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author weiming@yj-technology.com
 * @version $Id: ListBaseSpareAction.java 11295 2008-03-12 12:27:38Z wzou $
 */
public class ListBaseOldSpareAction extends ValueListAction {
	private static final long serialVersionUID = -6066964310061346050L;

	private final Log log = LogFactory.getLog(getClass());

	private final DepartmentManager departmentManager;

	private final SpareTypeManager spareTypeManager;

	private final SpareManager spareManager;
	
	private final SpareDetailTypeManager spareDetailTypeManager;
	
	private final SpareOutBillManager spareOutBillManager;
	
	private final SpareInBillManager spareInBillManager;
	
	private final SpareInventoryManager spareInventoryManager;
	
	private final SubscribeManager subscribeManager;
	
	private final PurchaseBillManager purchaseBillManager;
	
	private final SpareBorrowManager spareBorrowManager;
	private final AcceptBillManager acceptBillManager;
	private String selectFlag;

	private List<Spare> spares;
	
	private String strValue;

	private String toolingDevFlag;   //资产标识 【设备】|【工装】

	private List<Long> oldSpareIds; // 需要过滤得备件ID集合
	
	private Long spareBillId; //需要过滤备件的出库单Id
	private Long borrowId;

	private String filterSpareIds; // 需要过滤得备件ID字符串
	
	private String sysLogValue;
	
	private String inOutFlag = null;		//标识是出库


	public ListBaseOldSpareAction(DepartmentManager departmentManager,
			SpareTypeManager spareTypeManager, SpareManager spareManager,
			SpareDetailTypeManager spareDetailTypeManager,
			SpareOutBillManager spareOutBillManager,
			SpareInBillManager spareInBillManager,
			SpareInventoryManager spareInventoryManager,
			SubscribeManager subscribeManager,
			PurchaseBillManager purchaseBillManager,
			SpareBorrowManager spareBorrowManager,
			AcceptBillManager acceptBillManager) {
		this.departmentManager = departmentManager;
		this.spareTypeManager = spareTypeManager;
		this.spareManager = spareManager;
		this.spareDetailTypeManager = spareDetailTypeManager;
		this.spareOutBillManager = spareOutBillManager;
		this.spareInBillManager = spareInBillManager;
		this.spareInventoryManager = spareInventoryManager;
		this.subscribeManager = subscribeManager;
		this.purchaseBillManager = purchaseBillManager;
		this.spareBorrowManager=spareBorrowManager;
		this.acceptBillManager=acceptBillManager;
	}
	
	/**
	 * 判断出入库标志
	 * 判断设备还是工装标志
	 * 获取备件集合
	 * 
	 * @param 
	 * @return 
	 */
	public void prepare() throws Exception {
		 
		if (this.hasId("selectFlag")) {
			selectFlag = (String) request.getParameter("selectFlag");
		}
		if (strValue == null) {
			strValue = (String) request.getParameter("strValueIds");
		}
		
		if (sysLogValue == null) {
			sysLogValue = (String) request.getParameter("spareLogValueIds");
		}
		if (null == this.spares && this.hasIds("spareIds")) {
			this.spares = this.spareManager.loadAllSpares(this
					.getIds("spareIds"));
		}
		
		if (this.hasId("toolingDevFlag")) {
			
			if (request.getParameter("toolingDevFlag").equals("TOOLING")) {
				this.toolingDevFlag = "TOOLING";
			} else {
				this.toolingDevFlag = "DEVICE";
			}
		}
		if(this.hasId("spareBillId")){
				spareBillId = new Long(request.getParameter("spareBillId"));
			}
//		if(this.hasId("spareBorrowId")){
//			spareBillId = new Long(request.getParameter("spareBorrowId"));
//			System.out.println("======================="+spareBillId);
//		}
		
		if (null == this.inOutFlag) {
			if (!StringUtils.isEmpty(request.getParameter("inOutFlag"))) {
				this.inOutFlag = request.getParameter("inOutFlag");
			}
		}

	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		if (this.isDisabled()) {
			return disabled();
		}
		if (this.isEnable()) {
			return enabled();
		}
		if (this.isSetStock()) {
			return setSafeStock();
		}
		return NEW;
	}
	
	public String enabled()  throws Exception{
		try{
			this.spareManager.enabledAllSpare(spares);
		}catch(Exception e){
			e.printStackTrace();
			this.addActionMessage(this.getText("spare.disable.failer"));
			return ERROR;
		}
		
		this.addActionMessage(this.getText("enabled.spares.success"));
		return SUCCESS;
	}

	/**
	 * 根据是否是工装还是设备，获取ValueList文件中的Bean名
	 * 
	 * @param 
	 * @return ValueList文件中的Bean名
	 */
	@Override
	protected String getAdapterName() {
//		if (this.toolingDevFlag.equals("TOOLING")) {
//			return "toolingSpares";
//		} 
//		if (this.toolingDevFlag.equals("DEVICE")) {
//			
//			return "deviceSpares";
//		}
		if (("TOOLING").equals(request.getParameter("toolingDevFlag"))) {
			return "toolingOldSpares";
		}
		if (("DEVICE").equals(request.getParameter("toolingDevFlag"))) {
			return "deviceOldSpares";
		}

			return "oldSpares";
	}

	/**
	 * 判断是否点击了search按钮
	 * 
	 * @param 
	 * @return Boolean对象，是为true,否为false
	 */
	public boolean isSearch() {
		return this.hasKey("search");
	}

	/**
	 * 设置安全库存
	 * 
	 * @param 
	 * @return 是否成功字符串SUCCESS，进行页面重定向
	 */
	private String setSafeStock() {
		try {
			if (!strValue.equals("")) {
				this.spareManager.storeSpare(strValue,sysLogValue);
				this.addActionMessage(this
						.getText("spare.setSafeStock.success"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("spare.setSafeStock.error"));
		}
		return SUCCESS;
	}
	
	

	/**
	 * 删除所选择的备件
	 * 
	 * @param 
	 * @return 是否成功字符串SUCCESS，进行页面重定向
	 */
	private String delete() {
		try {
			this.spareManager.deleteAllSpare(spares);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionMessage(this.getText("spare.delete.error"));
		}
		this.addActionMessage(this.getText("spare.delete.success"));
		return SUCCESS;
	}
	
	
	/**
	 * 如果当前用户只能看本部门数据，则获取该部门数据，否则获取所有部门 
	 * @return List 部门集合
	 */
//	public List getDepartments() {
//		if (!this.userManager.getUser().isViewAll()) {           //如果用户只有看本部门的权限
//			List<Department> list = new ArrayList<Department>();
//			if (null == this.userManager.getUser().getDepartment()) {      //如果用户不属于任何部门,置部门ID为-2
//				Department department = new Department();
//				department.setId(Long.valueOf(-2L));
//				department.setName("");
//				list.add(department);
//			} else {
//				list.add(this.departmentManager.loadDepartment(this.userManager.getUser().getDepartment().getId()));
//			}
//			return list;
//		}
//		return departmentManager.createSelectDepartments(this
//				.getText("select.option.all"));
//	}
 

	/**
	 * 判断是否点击了失效按钮
	 * 
	 * @param 
	 * @return Boolean对象，是为true,否为false
	 */
	private boolean isDisable() {
		return this.hasKey("disabled");
	}

	/**
	 * 判断是否点击了设置安全按钮
	 * 
	 * @param 
	 * @return Boolean对象，是为true,否为false
	 */
	private boolean isSetStock() {
		return this.hasKey("toggleSafeStockButton");
	}
	
	/**
	 *失效所选备件
	 * 
	 * @param 
	 * @return 是否成功字符串SUCCESS，进行页面重定向
	 */
	public String disabled(){
			this.spareManager.invalidateSpare(spares);
		this.addActionMessage(this.getText("spare.disable.success"));
		return SUCCESS;
	}

	public List<Spare> getSpares() {
		return spares;
	}

	public void setSpares(List<Spare> spares) {
		this.spares = spares;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}

	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		oldSpareIds = new ArrayList<Long>();
		
		
		
		if(inOutFlag!=null){
			/*
			 * 以下注销的代码功能是选择不从备件台账中选择不重复的备件
			 * 注销之后用户可以选择相同的备件 
			 * */
		/*if(inOutFlag.equals("out")){//inoutFlag的值为'out'的时候是出库标识
			if(spareBillId!=null){
				SpareOutBill spareOutBill = spareOutBillManager.loadSpareOutBill(spareBillId);
				for(SpareOutBillDetail detail : spareOutBill.getDetail()){
					oldSpareIds.add(detail.getSpare().getId());
				}
			}
		}
		if(inOutFlag.equals("in")){//inoutFlag的值为'in'的时候是入库标识
			if(spareBillId!=null){
			SpareInBill spareInBill = spareInBillManager.loadSpareInBill(spareBillId);
			for(SpareInBillDetail detail : spareInBill.getDetail()){
				oldSpareIds.add(detail.getSpare().getId());
			}
			}
		}*/
		if(inOutFlag.equals("inventory")){//inoutFlag的值为invenetory的时候是盘点标识
			if(spareBillId!=null){
				SpareInventory spareInventory = spareInventoryManager.loadSpareInventory(spareBillId);
				for(SpareInventoryDetail detail : spareInventory.getInventoryDetails()){
					oldSpareIds.add(detail.getSpare().getId());
				}
			}
		}
		if(inOutFlag.equals("borrow")){
			if(spareBillId!=null){		//inoutFlag的值为subscribe的时候是工装中的采购单和设备中的申购单的标识
				SpareBorrow borrow  = spareBorrowManager.loadSpareBorrow(spareBillId);
				for (SpareBorrowDetail detail : borrow.getDetail()){
					if(detail.getSpare()!=null){
					oldSpareIds.add(detail.getSpare().getId());
					}
				}
			}
		}
		if(inOutFlag.equals("subscribe")){
			if(spareBillId!=null){		//inoutFlag的值为subscribe的时候是工装中的采购单和设备中的申购单的标识
				Subscribe subscribe  = subscribeManager.loadSubscribe(spareBillId);
				for (SubscribeDetail detail : subscribe.getSubscribeDetails()){
					if(detail.getSpare()!=null){
					oldSpareIds.add(detail.getSpare().getId());
					}
				}
			}
		}
	
		
		if(inOutFlag.equals("purchase")){		//inoutFlag的值为purchase的时候是工装中的采购合同和设备中的采购单的标识
			if(spareBillId!=null){
				PurchaseBill purchaseBill = purchaseBillManager.loadPurchaseBill(spareBillId);
				for(PurchaseBillDetail detail : purchaseBill.getPurchaseBillDetails()){
					if(detail.getSpare()!=null){
					oldSpareIds.add(detail.getSpare().getId());
					}
				}
			}
		}
		if(inOutFlag.equals("purchaseContract")){	
			if(spareBillId!=null){
				PurchaseBill purchaseBill = purchaseBillManager.loadPurchaseBill(spareBillId);
				for(PurchaseBillDetail detail : purchaseBill.getPurchaseBillDetails()){
					if(detail.getSpare()!=null){
					oldSpareIds.add(detail.getSpare().getId());
					}
				}
			}
		}
		if(inOutFlag.equals("SPARE_PURCHASE")){
			Subscribe subscribe = subscribeManager.loadSubscribe(spareBillId);
			for (SubscribeDetail detail : subscribe.getSubscribeDetails()){
				if(detail.getSpare()!=null){
				oldSpareIds.add(detail.getSpare().getId());
				}
			}
		 }
		if(inOutFlag.equals("ACCEPT_SPARE_PURCHASE")){
			AcceptBill acceptBill = acceptBillManager.loadAcceptBill(spareBillId);
			for (AcceptBillDetail detail : acceptBill.getAcceptBillDtl()){
				if(detail.getSpare()!=null){
				oldSpareIds.add(detail.getSpare().getId());
				}
			}
		 }
		
		}
		this.filterSpareIds = request.getParameter("oldSpareIds");

		Map map = super.getRequestParameterMap();
		if(!oldSpareIds.isEmpty()){
			map.put("filterSpareIds", oldSpareIds);
		}
		return map;
	}

	public String getFilterSpareIds() {
		return filterSpareIds;
	}

	public String getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(String selectFlag) {
		this.selectFlag = selectFlag;
	}

	public String getSysLogValue() {
		return sysLogValue;
	}

	public void setSysLogValue(String sysLogValue) {
		this.sysLogValue = sysLogValue;
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
	 * 获取备件大分类
	 */
	@SuppressWarnings("unchecked")
	public List getSpareType() {
		List allSpareTypeList = new ArrayList();
		List spareTypeList = new ArrayList();
		allSpareTypeList =spareTypeManager.loadAllSpareType();
		for (int i=0;i<allSpareTypeList.size();i++){        //区分是工装[TOOLING]或设备[DEVICE]
			if (((SpareType)allSpareTypeList.get(i)).getToolingDevFlag().toString().equals(this.toolingDevFlag)){
				spareTypeList.add(allSpareTypeList.get(i));
			}
			//获得[工装]和[设备]的备件分类
		if (request.getParameter("toolingDevFlag").equals("TOOLINGDEVICE")) {
				spareTypeList.add(allSpareTypeList.get(i));
			}
		}
		SpareType type = new SpareType();
		type.setId(Long.valueOf(-1L));
		type.setName(this.getText("select.option.all"));
		spareTypeList.add(0,type);
		return spareTypeList;
		
	}
	
	/**
	 * 获取备件明细分类
	 * @return
	 */
	public List getSpareDetailType (){
		
		List<SpareDetailType> list = new ArrayList<SpareDetailType>();
		list = this.spareDetailTypeManager.loadAllSpareDetailTypes();
		SpareDetailType type = new SpareDetailType();
		type.setId(-1l);
		type.setName(this.getText("select.option.all"));
		list.add(0, type);
		
		return list;
	}

	public String getStrValue() {
		return strValue;
	}

	public void setStrValue(String strValue) {
		this.strValue = strValue;
	}

	public Long getSpareBillId() {
		return spareBillId;
	}

	public void setSpareBillId(Long spareBillId) {
		this.spareBillId = spareBillId;
	}

	public Long getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

}

