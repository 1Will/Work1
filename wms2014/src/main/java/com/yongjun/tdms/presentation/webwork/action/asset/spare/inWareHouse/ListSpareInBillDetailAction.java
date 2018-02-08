package com.yongjun.tdms.presentation.webwork.action.asset.spare.inWareHouse;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.WarehouseManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillDetailManager;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.regional.RegionalManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
/**
 * 
 * <p>Title:ListSpareInBillDetailAction保存
 * <p>Description:入库单明细查询</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: ListSpareInBillDetailAction.java 2008-11-13 15:23:48 yli$
 */
public class ListSpareInBillDetailAction extends ValueListAction {
	private static final long serialVersionUID = -2540807550126861965L;
	
	private String roleWarehouseId;
	private SpareInBill spareInBill;
	private String addSpareAccountIds;					//添加新的入库明细时，从备件库台帐选择的ID集合
	private String addPurchaseBillDetailIds;			//添加新的入库明细时，从采购单明细选择的ID集合
	private String allSpareInBillDtlId;					//入库单明细所有ID
	private String allSpareInBillDtlNumber;				//入库单明细所有入库数量
	private String allSpareInBillDtlTaxPriceValue;     //入库单明细所有入库单价
	private String allSpareInBillDtlComment;			//入库单明细所有备注
	private String valueAry;							//获得所有在未保存入库明细中的入库数量的值
	
	private String addSpareIds;     //添加新的入库单明细时，从备件台账选择的ID集合
	private String addSpareOutBillDtlIds;//添加新的入库明细时，从出库单明细选择的ID集合
	
	//private String allLocationCodeValue;
	private String allSpareInBillDeparement;
	private String allSpareInBillRegional;
	private String allSpareBorrowIds;
	private String allLocationCodeValue;               //	库位号 用","分割开的字符串
	private List<SpareInBillDetail> spareInBillDetails;
	private final SpareInBillManager spareInBillManager;
	private final SpareInBillDetailManager spareInBillDetailManager;
	private final SpareManager spareManager;
	private final DepartmentManager departmentManager;
	private final WarehouseManager warehouseManager;
	private final RegionalManager regionalManager;
	private String outWarehouseId = null;
	private boolean notFather= false;//判断是否是一级库存
	private Long[] inBillDtlIds;
	
	public ListSpareInBillDetailAction(SpareInBillManager spareInBillManager,
									   SpareInBillDetailManager spareInBillDetailManager,
									   SpareManager spareManager,
									   DepartmentManager departmentManager,
									   WarehouseManager warehouseManager,
									   RegionalManager regionalManager){
		this.spareInBillManager = spareInBillManager;
		this.spareInBillDetailManager = spareInBillDetailManager;
		this.spareManager = spareManager;
		this.departmentManager= departmentManager;
		this.warehouseManager = warehouseManager;
		this.regionalManager = regionalManager;
	}
	
	/**
	 * 获取所有部门的集合
	 * @return List 部门的集合
	 */
	public List getDepartments() {
		return departmentManager.createSelectDepartments(this
				.getText(""));
	}
	
//	/**
//	 * 获取当前用户权限仓库的库区的集合
//	 * @return 库区的集合
//	 */
//	public List<Regional> getAllRegional() {
//		Long wareId = this.userManager.getUser().getWarehouse().getId();
//		List<Regional> list = new ArrayList<Regional>();
//		if(null != wareId){
//			try {
//				list = this.regionalManager.loadRegionalByWareId(wareId.toString(), "search");
//			} catch (DaoException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				list = new ArrayList<Regional>();
//			}
//		}
//		return list;
//	}
	
	@Override
	public void prepare(){
		outWarehouseId = this.request.getParameter("outWarehouseId");
		notFather = Boolean.parseBoolean(this.request.getParameter("notFather"));
		if(this.hasId("spareInBill.id")){
			this.spareInBill = spareInBillManager.loadSpareInBill(this.getId("spareInBill.id"));
			this.roleWarehouseId =this.spareInBill.getWarehouse().getId().toString();
		}
		if(spareInBillDetails==null&& this.hasIds("spareInBillDtlIds")){
			this.spareInBillDetails = spareInBillDetailManager.loadAllSpareInBillDtl(this.getIds("spareInBillDtlIds"));
			this.inBillDtlIds = this.getIds("spareInBillDtlIds");
		}
		//获得所有从备件库台帐选择的备件ids
		if(null==this.addSpareAccountIds){
			if(!StringUtils.isEmpty(request.getParameter("addSpareDetailIds"))){
				this.addSpareAccountIds = request.getParameter("addSpareDetailIds");
			}
		}
		
		//获得所有从备件台账选择的备件ids
		if(null == this.addSpareIds){
			if(!StringUtils.isEmpty(this.request.getParameter("addSpareIds"))){
				this.addSpareIds = this.request.getParameter("addSpareIds");
			}
		}
		
		//获取从出库单明细ids
		if(null == addSpareOutBillDtlIds){
			if(!StringUtils.isEmpty(this.request.getParameter("addSpareOutBillDtlIds"))){
				this.addSpareOutBillDtlIds = this.request.getParameter("addSpareOutBillDtlIds");
			}
		}
        //获得所有从采购单明细选择的采购单明细ids
		if(null==this.addPurchaseBillDetailIds){
			if(!StringUtils.isEmpty(request.getParameter("addPurBillDetailIds"))){
				this.addPurchaseBillDetailIds = request.getParameter("addPurBillDetailIds");
			}
		}
		if(null==this.allSpareBorrowIds){
			if(!StringUtils.isEmpty(request.getParameter("addSpareBorrowDetailIds"))){
				this.allSpareBorrowIds = request.getParameter("addSpareBorrowDetailIds");
			}
		}
		//页面信息的回传
		if(valueAry==null){
			this.valueAry = request.getParameter("valueAry");
		}
		this.setFirst(false);
	}
	
	@Override
	public String execute() throws Exception {
		if(this.isAddSpareAcount()){					//判断页面的信息是否从备件台帐而来
			return saveAddSpareToSpareInBillDetail();
		}
		if(this.isAddPurBillDtl()){						//判断页面的信息是否从采购单明细而来
			return saveAddPurBillDtlToSpareInBillDetail();
		}
		if(this.isSaveDetail()){						//保存入库单明细信息
			if(request.getParameter("isOld")!=null&&request.getParameter("isOld").equals("true")){
				return saveOldSpareInBillDetail();
			}
			return saveSpareInBillDetail();
		}
		if(this.isAddSpareBorrow()){
			return saveInBillDtlBySpareBorrowDtl();
		}
		if(this.isAddSpare()){
			return this.saveAddSpareInBillDetail();//保存备件信息
		}
		if(this.isAddSpareOutBill()){
			return this.saveAddSpareOutBillInBillDetail();//保存出库单明细信息
		}
		if(this.isDelete()){
			return delete();
		}
		
		if(request.getParameter("addFalse")!=null&&request.getParameter("addFalse").equals("yes")){
			return this.savefalseSpareInBillDetail();
		}
		return SUCCESS;
	}
	/**
	 * 从领用单明细copy到入库明细
	 * 
	 */
	public String saveInBillDtlBySpareBorrowDtl(){
		//this.spareInBillDetailManager.storeSpareBorrowToSpareInBillDtl(spareInBill,allSpareBorrowIds);
		return SUCCESS;
	}
	/**
	 * 从备件库台帐copy到入库单明细
	 * @return SUCCESS
	 */
	public String saveAddSpareToSpareInBillDetail(){
		this.spareInBillDetailManager.storeSpareInBillDtlFromAccount(spareInBill,addSpareAccountIds);
		return SUCCESS;
	}
	/**
	 * 从采购单明细copy到入库单明细
	 * @return SUCCESS
	 */
	public String saveAddPurBillDtlToSpareInBillDetail(){
		this.spareInBillDetailManager.storeSpareInBillDtlFromPurBillDtl(spareInBill,addPurchaseBillDetailIds);
		return SUCCESS;
	}
	/**
	 * 从出库单明细copy到入库单明细
	 * @return
	 */
	public String saveAddSpareOutBillInBillDetail(){
		this.spareInBillDetailManager.storeSpareInBillFromSpareOutBillDtl(spareInBill, addSpareOutBillDtlIds);
		return SUCCESS;
	}
	/**
	 *从备件库 copy到入库单明细
	 * @return
	 */
	public String saveAddSpareInBillDetail(){
		this.spareInBillDetailManager.storeSpareInBillFromSpare(spareInBill, addSpareIds);
		
		if(request.getParameter("isOld")!=null&&request.getParameter("isOld").equals("true")){
			return "oldSuccess";
		}
		return SUCCESS;
	}
	/**
	 * 当页面上输入新的信息以后，调用此方法
	 * @return SUCCESS
	 */
	public String saveSpareInBillDetail(){
		//获得所有入库单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlIds"))){
			this.allSpareInBillDtlId = request.getParameter("allSpareInBillDtlIds");
		}
		//获得所有部门 
        if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDetailDepartment")))
        {
        	this.allSpareInBillDeparement=request.getParameter("allSpareInBillDetailDepartment");
        }
//        //获得所有库区 
//        if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDetailRegional")))
//        {
//        	this.allSpareInBillRegional=request.getParameter("allSpareInBillDetailRegional");
//        }
		//获得所有入库单明细的入库数量
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlNumberValue"))){
			this.allSpareInBillDtlNumber = request.getParameter("allSpareInBillDtlNumberValue");
		}
		//获得所有入库单明细的入库单价
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlTaxPriceValue"))){
			this.allSpareInBillDtlTaxPriceValue = request.getParameter("allSpareInBillDtlTaxPriceValue");
		}
		//获得所有入库单明细的备注
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlCommentValue"))){
			this.allSpareInBillDtlComment = request.getParameter("allSpareInBillDtlCommentValue");
		}

		  //获取页面传来的库位ID 用","分割开的字符串
		if(!StringUtils.isEmpty(request.getParameter("allLocationCodeValue"))){
			this.allLocationCodeValue = request.getParameter("allLocationCodeValue");
		}
		if(null!=roleWarehouseId || null!=allSpareInBillDtlComment||null!=allSpareInBillDtlNumber||null!=allSpareInBillDtlId||null!=allSpareInBillDeparement||null!=allLocationCodeValue){
			this.spareInBillDetailManager.storeSpareInBillDtl(Long.parseLong(roleWarehouseId),allSpareInBillDtlComment,allSpareInBillDtlNumber,allSpareInBillDtlId,allSpareInBillDeparement,allLocationCodeValue,allSpareInBillDtlTaxPriceValue);
		}
//		获得入库明细ID集合对应的采购明细ID集合
		String pruchDetailIds = this.spareInBillDetailManager.updPurchaseBillIds(allSpareInBillDtlId);
//		更新申购单、汇总单、采购单的入库项
		this.spareInBillDetailManager.updStatus(pruchDetailIds);
		return SUCCESS;
	}
	
	public String savefalseSpareInBillDetail(){
		SpareInBillDetail inDetail = new SpareInBillDetail();
		inDetail.setSpareInBill(spareInBill);
		inDetail.setCreator(spareInBill.getCreator());
		inDetail.setCreatedTime(new Date());
		inDetail.setInstatus(SpareInBillDetailStatus.NEW);
		inDetail.setNumber(0l);
		inDetail.setTaxPrice(0.0d);
		inDetail.setUnitPrice(0.0);
		inDetail.setLastModifiedTime(new Date());
		inDetail.setLastOperator(spareInBill.getCreator());
		inDetail.setTotalPrice(0.0);
		spareInBillManager.storeFalseSpareInBillDetail(inDetail);
		
		return SUCCESS;
		
	}
	
	public String saveOldSpareInBillDetail(){
		String allDisableSpare ="";
		//获得所有入库单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlIds"))){
			this.allSpareInBillDtlId = request.getParameter("allSpareInBillDtlIds");
		}
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlDisableValue"))){
			allDisableSpare = request.getParameter("allSpareInBillDtlDisableValue");
		}
		//获得所有部门 
        if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDetailDepartment")))
        {
        	this.allSpareInBillDeparement=request.getParameter("allSpareInBillDetailDepartment");
        }
//        //获得所有库区 
//        if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDetailRegional")))
//        {
//        	this.allSpareInBillRegional=request.getParameter("allSpareInBillDetailRegional");
//        }
		//获得所有入库单明细的入库数量
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlNumberValue"))){
			this.allSpareInBillDtlNumber = request.getParameter("allSpareInBillDtlNumberValue");
		}
		//获得所有入库单明细的入库单价
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlTaxPriceValue"))){
			this.allSpareInBillDtlTaxPriceValue = request.getParameter("allSpareInBillDtlTaxPriceValue");
		}
		//获得所有入库单明细的备注
		if(!StringUtils.isEmpty(request.getParameter("allSpareInBillDtlCommentValue"))){
			this.allSpareInBillDtlComment = request.getParameter("allSpareInBillDtlCommentValue");
		}

		  //获取页面传来的库位ID 用","分割开的字符串
		if(!StringUtils.isEmpty(request.getParameter("allLocationCodeValue"))){
			this.allLocationCodeValue = request.getParameter("allLocationCodeValue");
		}
		if(null!=roleWarehouseId || null!=allSpareInBillDtlComment||null!=allSpareInBillDtlNumber||null!=allSpareInBillDtlId||null!=allSpareInBillDeparement||null!=allLocationCodeValue){
			this.spareInBillDetailManager.storeOldSpareInBillDtl(Long.parseLong(roleWarehouseId),allSpareInBillDtlComment,allSpareInBillDtlNumber,allSpareInBillDtlId,allSpareInBillDeparement,allLocationCodeValue,allSpareInBillDtlTaxPriceValue,allDisableSpare);
		}
//		获得入库明细ID集合对应的采购明细ID集合
		String pruchDetailIds = this.spareInBillDetailManager.updPurchaseBillIds(allSpareInBillDtlId);
//		更新申购单、汇总单、采购单的入库项
		this.spareInBillDetailManager.updStatus(pruchDetailIds);
		return "oldSuccess";
	}
	/**
	 * 判断页面是不是备件库台帐选择而来
	 * @return true | false
	 */
	private boolean isAddSpareAcount(){
		if (!StringUtils.isEmpty(request.getParameter("spareAccountSelector"))) {
			if (request.getParameter("spareAccountSelector").equals("spareAccount"))
				return true;
		}
		return false;
	}
	/**
	 * 判断页面是不是从备件台账选择来的
	 * @return
	 */
	private boolean isAddSpare(){
		String spareSelector = this.request.getParameter("spareSelector");
		if(null != spareSelector){
			if("fromSpare".equals(spareSelector)){
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断页面时不是从出库单明细选择来的
	 * @return
	 */
	private boolean isAddSpareOutBill(){
		String spareOutBillDtlSelector = this.request.getParameter("spareOutBillDtlSelector");
		if(null != spareOutBillDtlSelector){
			if("fromSpareOutBillDtl".equals(spareOutBillDtlSelector)){
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * 判断页面传来是不是备件领用选择的
	 * @return true | false
	 */
	private boolean isAddSpareBorrow(){
		if (!StringUtils.isEmpty(request.getParameter("spareBorrowSelector"))) {
			if (request.getParameter("spareBorrowSelector").equals("spareBorrow"))
				return true;
		}
		return false;
	}
	/**
	 * 判断页面是不是采购单明细选择而来
	 * @return true | false
	 */
	private boolean isAddPurBillDtl(){
		if (!StringUtils.isEmpty(request.getParameter("purBillDetailSelector"))) {
			if (request.getParameter("purBillDetailSelector").equals("PurBillDetail"))
				return true;
		}
		return false;
	}
	/**
	 * 判断页面是否保存入库单明细
	 * @return true | false
	 */
	private boolean isSaveDetail() {
		if (!StringUtils.isEmpty(request.getParameter("save"))){
		   if(this.hasKey("save")){
			  return true;
		   }
		}
		return false;
	}
	/**
	 * 删除
	 * @return SUCCESS | ERROR
	 */
	public String delete(){
		try {
//		     根据入库明细ID集合获取对应的采购明细ID集合
			String subDetailIds = this.spareInBillDetailManager.updPurchaseBillIds(StringUtils.join(inBillDtlIds, ","));
			this.spareInBillDetailManager.deleteAllSpateInBillDtl(spareInBillDetails,spareInBill);
//			 根据采购明细ID集合更新申购单、汇总单的采购项
			 this.spareInBillDetailManager.updStatus(subDetailIds+",");
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	@Override
	protected String getAdapterName() {
		 
		return "spareInBillDtls";
	}
	
	/**
	 * 将spareInBill.id的值放入到spareInBillDtl的valuelist中去
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("spareInBill.id",this.getId("spareInBill.id"));
		return map;
	}
	
	public SpareInBill getSpareInBill() {
		return spareInBill;
	}
	public void setSpareInBill(SpareInBill spareInBill) {
		this.spareInBill = spareInBill;
	}

	public String getAddSpareAccountIds() {
		return addSpareAccountIds;
	}

	public void setAddSpareAccountIds(String addSpareAccountIds) {
		this.addSpareAccountIds = addSpareAccountIds;
	}

	public String getValueAry() {
		return valueAry;
	}

	public void setValueAry(String valueAry) {
		this.valueAry = valueAry;
	}

	public boolean isNotFather() {
		return notFather;
	}

	public void setNotFather(boolean notFather) {
		this.notFather = notFather;
	}

	public String getOutWarehouseId() {
		return outWarehouseId;
	}

	public void setOutWarehouseId(String outWarehouseId) {
		this.outWarehouseId = outWarehouseId;
	}

	 

 
}
