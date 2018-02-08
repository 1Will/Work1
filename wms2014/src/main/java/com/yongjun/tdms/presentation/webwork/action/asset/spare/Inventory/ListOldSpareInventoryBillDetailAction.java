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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.Inventory;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryDetailManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;

/**
 * <p>Title:ListSpareInventoryBillDetailAction
 * <p>Description:盘点单明细页面访问控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class ListOldSpareInventoryBillDetailAction extends ValueListAction{
	private static final long serialVersionUID = 1L;
	private SpareInventory spareInventoryBill;
	private List<SpareInventoryDetail> spareDtls;
	private String addSpareAccountIds;                 			//备件台帐所以的ids 
	private String allSpareInventoryBillDtlId;					//盘点单明细所有ID
	private String allSpareInventoryBillDtlNumber;				//盘点单明细所有盘点数量
	private String allSpareInventoryBillDtlComment;
	private String allLocationCodeValue;               //	库位号 用","分割开的字符串
	private String valueAry;									//页面信息的回传
	private final SpareInventoryDetailManager spareInventoryDetailManager;
	private final SpareInventoryManager spareInventoryManager;
	
	public ListOldSpareInventoryBillDetailAction(SpareInventoryDetailManager spareInventoryDetailManager,SpareInventoryManager spareInventoryManager){
		this.spareInventoryDetailManager=spareInventoryDetailManager;
		this.spareInventoryManager=spareInventoryManager;
	}
	@Override
	public void prepare(){
		if(this.hasId("spareInventoryBill.id")){
			this.spareInventoryBill = spareInventoryManager.loadSpareInventory(this.getId("spareInventoryBill.id"));
		}
		if(this.hasIds("spareInventoryBillDtlIds")){
			this.spareDtls=spareInventoryDetailManager.loadAllSpareInventoryDetails(this.getIds("spareInventoryBillDtlIds"));
		}
		//获得所有从备件台帐选择的备件ids
		if(null==this.addSpareAccountIds){
			if(!StringUtils.isEmpty(request.getParameter("addSpareDetailIds"))){
				this.addSpareAccountIds = request.getParameter("addSpareDetailIds");
			}
		}
		if(valueAry==null){
			this.valueAry = request.getParameter("valueAry");
		}
		this.setFirst(false);
	}
	@Override
	public String execute() throws Exception {
       //判断页面的信息是否从备件台帐而来
		if(this.isAddSpareAcount()){					
			return saveAddSpareToSpareInventoryBillDetail();
		}
    	//保存入库单明细信息
		if(this.isSaveDetail()){						
			return saveSpareInventoryBillDetail();
		}
		if(this.isDelete()){
			return delete();
		}
		return SUCCESS;
	}
	/**
	 * 删除盘点单 明细
	 * @return
	 */
	private String delete(){
		this.spareInventoryDetailManager.deleteAllSpareInventoryDetail(spareDtls,spareInventoryBill);
		this.updateFatherTotalPrice(spareInventoryBill);
		return SUCCESS;
	}
	
	/**
	 * 更新单头的盘点总金额
	 * @param spareInventoryBill
	 */
	public void updateFatherTotalPrice(SpareInventory spareInventoryBill){
		Double totalPrice = 0.0;
		Set<SpareInventoryDetail> set = spareInventoryBill.getInventoryDetails();
		for(SpareInventoryDetail detail : set){
			totalPrice = totalPrice + detail.getInventoryTotalPrice();
		}
		spareInventoryBill.setTotalPrice(totalPrice);
		this.spareInventoryManager.storeSpareInventory(spareInventoryBill);
	}

	
	/**
	 * 判断页面是否保存入库单明细"
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
	public String saveSpareInventoryBillDetail(){
		//获得所有盘点单明细的ID
		if(!StringUtils.isEmpty(request.getParameter("allSpareInventoryBillDtlIds"))){
			this.allSpareInventoryBillDtlId = request.getParameter("allSpareInventoryBillDtlIds");
		}
		//获得所有盘点单明细的盘点数量
		if(!StringUtils.isEmpty(request.getParameter("allSpareInventoryBillDtlNumberValue"))){
			this.allSpareInventoryBillDtlNumber = request.getParameter("allSpareInventoryBillDtlNumberValue");
		}
		//获得所有盘点单明细的备注
		if(!StringUtils.isEmpty(request.getParameter("allSpareInventoryBillDtlCommentValue"))){
			this.allSpareInventoryBillDtlComment = request.getParameter("allSpareInventoryBillDtlCommentValue");
		}
		
		if(null!=allSpareInventoryBillDtlNumber||null!=allSpareInventoryBillDtlId||null!=allSpareInventoryBillDtlComment||null!=allLocationCodeValue){
			this.spareInventoryDetailManager.storeSpareInventoryBillDtl(spareInventoryBill,allSpareInventoryBillDtlId,allSpareInventoryBillDtlNumber,allSpareInventoryBillDtlComment);	
		}
		return SUCCESS;
	}
	/**
	 * 从备件台帐copy到入库单明细
	 * @return SUCCESS
	 */
	public String saveAddSpareToSpareInventoryBillDetail(){
		this.spareInventoryDetailManager.storeSpareInventoryBillDtlFromAccount(spareInventoryBill,addSpareAccountIds);
		return SUCCESS;
	}
	/**
	 * 判断页面是不是备件台帐选择而来
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
	 * 将spareInBill.id的值放入到spareInBillDtl的valuelist中去
	 */
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        map.put("spareInventoryBill.id",this.getId("spareInventoryBill.id"));
        map.put("spareInventoryBill.storageGrade.id",spareInventoryBill.getStorageGrade().getId());
        map.put("spareInventoryBill.warehouse.id",spareInventoryBill.getWarehouse().getId());
		return map;
	}
	@Override
	protected String getAdapterName() {
		return "inventoryBillDtls";
	}
	public SpareInventory getSpareInventoryBill() {
		return spareInventoryBill;
	}
	public void setSpareInventoryBill(SpareInventory spareInventoryBill) {
		this.spareInventoryBill = spareInventoryBill;
	}
	public String getValueAry() {
		return valueAry;
	}
	public void setValueAry(String valueAry) {
		this.valueAry = valueAry;
	}

}
