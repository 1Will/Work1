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
package com.yongjun.tdms.presentation.webwork.action.asset.spare.outWareHouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailDao;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailStatus;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillDetailManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillDetailManager;
import com.yongjun.tdms.service.asset.spare.outWareHouse.SpareOutBillManager;

/**
 * <p>
 * Title: ListSpareOutBillDetailAction
 * <p>
 * Description: 备件出库明细列表页面控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author smzhang@yj-technology.com
 * @version $Id: ListSpareOutBillDetailAction 9149 2007-12-09 06:29:38Z smzhang $
 */
public class ListOldSpareScrapBillDetailAction extends ValueListAction {
	private static final long serialVersionUID = 1L;

	private SpareOutBill spareOutBill;

	private SpareInBill spareInBill;

	private String addSpareOutBillIds;

	private String allSpareBorrowIds;

	private String saveAllDetail;

	private String valueAry;

	private String roleWarehouseId;
	private String allDetailIds;//出库单明细的IDs
	private String allNumber;//出库数量
	private String allborrowerPeople;//所有领料人
	private String allNewOrOld;//是否是以旧换新
	private String allOutDate;//出库日期
	private String allComment;//备注
	private String allOutPeopleIds;//出库人
	private String allSheBei;//设备
	private String allBanZu;//班组
	

	private List<SpareOutBillDetail> spareOutBillDetails;

	private SpareOutBillDetail spareOutBillDetail;

	private final SpareOutBillManager spareOutBillManager;

	private final SpareOutBillDetailManager spareOutBillDetailManager;

	private final SpareInBillDetailManager spareInBillDetailManager;

	public ListOldSpareScrapBillDetailAction(
			SpareOutBillManager spareOutBillManager,
			SpareOutBillDetailManager spareOutBillDetailManager,
			SpareInBillDetailManager spareInBillDetailManager) {
		this.spareOutBillManager = spareOutBillManager;
		this.spareOutBillDetailManager = spareOutBillDetailManager;
		this.spareInBillDetailManager = spareInBillDetailManager;

	}

	@Override
	public void prepare() {
		// 根据id获得备件出库单
		if (spareOutBill == null) {
			if (this.hasId("spareOutBill.id")) {
				this.spareOutBill = this.spareOutBillManager.loadSpareOutBill(this.getId("spareOutBill.id"));
				if(null!=spareOutBill.getWarehouse()){
					this.roleWarehouseId = this.spareOutBill.getWarehouse().getId().toString();					
				}
			} else {
				this.spareOutBill = new SpareOutBill();
			}
		}
		if (this.hasIds("spareOutBillDetailIds")) {
			if (!StringUtils.isEmpty(request
					.getParameter("spareOutBillDetailIds"))) {
				this.spareOutBillDetails = this.spareOutBillDetailManager
						.loadAllSpareOutBillDetail(getIds("spareOutBillDetailIds"));
			}
		}
		// 获得出库的备件Id集合
		if (null == addSpareOutBillIds) {
			if (!StringUtils
					.isEmpty(request.getParameter("addSpareOutBillIds"))) {
				this.addSpareOutBillIds = request
						.getParameter("addSpareOutBillIds");
			}
		}
		
		// 获得被修改出库明细的信息
		if (null == saveAllDetail) {
			if (!StringUtils.isEmpty(request.getParameter("saveAllDetail"))) {
				this.saveAllDetail = request.getParameter("saveAllDetail");
			}
		}
		if (null == this.allSpareBorrowIds) {
			if (!StringUtils.isEmpty(request
					.getParameter("addSpareBorrowDetailIds"))) {
				this.allSpareBorrowIds = request
						.getParameter("addSpareBorrowDetailIds");
			}
		}
	    //出库数量
		if (!StringUtils.isEmpty(request.getParameter("allNumber"))) {
			this.allNumber = request.getParameter("allNumber");
		}
		
          //领料人
		if (!StringUtils.isEmpty(request.getParameter("allDetailIds"))) {
			this.allDetailIds = request.getParameter("allDetailIds");
		}
		
		//领料人
		if (!StringUtils.isEmpty(request.getParameter("allborrowerPeople"))) {
			this.allborrowerPeople = request.getParameter("allborrowerPeople");
		}//以旧换新
		if (!StringUtils.isEmpty(request.getParameter("allNewOrOld"))) {
			this.allNewOrOld = request.getParameter("allNewOrOld");
		}//出库日期
		if (!StringUtils.isEmpty(request.getParameter("allOutDate"))) {
			this.allOutDate = request.getParameter("allOutDate");
		}//备注
		if (!StringUtils.isEmpty(request.getParameter("allComment"))) {
			this.allComment = request.getParameter("allComment");
		}//出库人
		if (!StringUtils.isEmpty(request.getParameter("allOutPeopleIds"))) {
			this.allOutPeopleIds = request.getParameter("allOutPeopleIds");
		}
        //设备
		if (!StringUtils.isEmpty(request.getParameter("allSheBei"))) {
			this.allSheBei = request.getParameter("allSheBei");
		}
        //班组
		if (!StringUtils.isEmpty(request.getParameter("allBanZu"))) {
			this.allBanZu = request.getParameter("allBanZu");
		}
		
		// if(this.hasId("spareOutBill.id")){
		// this.spareOutBill =
		// spareOutBillManager.loadSpareOutBill(this.getId("spareOutBill.id"));
		// }else{
		// spareOutBill = new SpareOutBill();
		// }
		if (null == valueAry) {
			this.valueAry = request.getParameter("valueAry");
		}
		//查询该报废单有没有明细记录  
				int count = this.spareOutBillDetailManager.loadSpareOutBillDetailCount(spareOutBill.getId()+"");
				
				if(count==0){//没有明细记录则自动添加满足条件的记录 即凡满足在此二级库中的有不可用数量的旧件增加报废处理，
					this.addAutoDetails();
					
				}

		this.setFirst(false);
	}

	@Override
	public String execute() throws Exception {
		if (this.isAddDetail()) {// 判断是否为新加出库明细
			this.addDetails();
		}
		if (this.isUpdateDetail()) {// 判断是否为修改出库明细
			this.saveDetails();
		}
		if (this.isAddSpareBorrow()) {
			return saveInBillDtlBySpareBorrowDtl();
		}
		if (this.isDelete()) {
			return delete();
		}
		
		return SUCCESS;
	}

	/**
	 * 从领用单明细copy到入库明细
	 * 
	 */
	public String saveInBillDtlBySpareBorrowDtl() {
		this.spareOutBillDetailManager.storeSpareBorrowToSpareInBillDtl(
				spareOutBill, allSpareBorrowIds);
		return SUCCESS;
	}

	/**
	 * 判断页面传来是不是备件领用选择的
	 * 
	 * @return true | false
	 */
	private boolean isAddSpareBorrow() {
		if (!StringUtils.isEmpty(request.getParameter("spareBorrowSelector"))) {
			if (request.getParameter("spareBorrowSelector").equals(
					"spareBorrow"))
				return true;
		}
		return false;
	}

	/**
	 * 判断页面是否添加一个新的备件出库单明细"
	 * 
	 * @return
	 */
	private boolean isAddDetail() {
		if (!StringUtils.isEmpty(request.getParameter("addSpareOutBill"))) {
			if (request.getParameter("addSpareOutBill").equals(
					"addSpareOutBill"))
				return true;
		}
		return false;
	}

	/**
	 * 判断页面是否保存一个备件出库单明细"
	 * 
	 * @return
	 */
	private boolean isUpdateDetail() {
		if (this.hasKey("save")) {
			return true;
		}
		return false;
	}

	/**
	 * 备件库台账 -> 出库明细
	 * 
	 * @return
	 */
	private String addDetails() {
		System.out.println(this.getLoginUser().getName());
		this.spareOutBillDetailManager.addSpareOutBillDetail(
				addSpareOutBillIds, spareOutBill,this.getLoginUser());
		return SUCCESS;
	}
	
	private void addAutoDetails() {
		List<Integer> list = this.spareOutBillDetailManager.loadSpareLocations(spareOutBill.getWarehouse().getId()+"");
		String adddetailIds ="";
		if(list!=null&&list.size()>0){
			for(Integer i:list){
				if(adddetailIds.equals("")){
					adddetailIds=i+"";
				}else{
					adddetailIds+=","+i;
				}
				
			}
			
		}
		
		if(!adddetailIds.equals("")){
		this.spareOutBillDetailManager.addAutoSpareOutBillDetail(
				adddetailIds, spareOutBill,this.getLoginUser());
		}
//		return SUCCESS;
	}
	

	private String delete() {
		try {
			this.spareOutBillDetailManager.deleteAllSpareOutBillDetail(
					spareOutBillDetails, spareOutBill);
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		this.addActionMessage(this.getText("spareBorrowDetails.delete.success"));
		return SUCCESS;
	}

	/**
	 * 修改保存出库明细
	 * 
	 * @return
	 */
	private String saveDetails() {
		spareOutBillDetailManager.saveSpareOutBillDetailHaveDisable(allDetailIds,allNumber,allborrowerPeople,
				allNewOrOld,allOutDate,allComment,allOutPeopleIds,allSheBei,allBanZu);

		
		/**
		 * 若出库是一级库 所入库是二级库 则在生成一个二级库的入库单
		 * 2010-10-27 13:59:52 qcshen
		 * 2014-3-18,如果进入仓库不为空，则生成进入仓库的对应的入库单
		 */
		//if (null!=spareOutBill.getStorageGrade()) {
			//if (208==spareOutBill.getStorageGrade().getId()) {
		 if(null!=spareOutBill.getInWarehouse()){
				String[] allSpareOutBillIds = null;
				if (null != allDetailIds) {
					allSpareOutBillIds = allDetailIds.split(",");
					for (int i = 0; i < allSpareOutBillIds.length; i++) {
						//预设空的进库单明细对象
						SpareInBillDetail inDtl = new SpareInBillDetail();
						//根据ids得到出库单明细对象
						SpareOutBillDetail outDtl = spareOutBillDetailManager.loadSpareOutBillDetail(Long.valueOf(allSpareOutBillIds[i]));
						if(!outDtl.isHaveInbill()){
							inDtl.setCode(outDtl.getCode());// 编码
							inDtl.setName(outDtl.getName());// 名子
							inDtl.setCreator(outDtl.getCreator());// 种类
							inDtl.setNumber(outDtl.getNumber());// 数量
							//inDtl.setUnitPrice(outDtl.getUnitPrice());// 单价
							inDtl.setTaxPrice(outDtl.getUnitPrice());//单价为税前单价
							inDtl.setTotalPrice(outDtl.getTotalPrice());// 总价
							//inDtl.setStocksAfterInOrOut(outDtl.getStocksBeforeInOrOut());// 库存
							inDtl.setSpare(outDtl.getSpare());// 备件
							inDtl.setSpareInBill(spareOutBill.getSpareInBill());
							inDtl.setSpareOutBillDtl(outDtl);
							inDtl.setDepartment(spareOutBill.getDepartment());
							outDtl.setHaveInbill(true);
							this.spareInBillDetailManager.storeSpareInBillDtl(inDtl);
							this.spareOutBillDetailManager.storeSpareOutBillDetail(outDtl);
						}
					
					}
				}
				
			//}
		}
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		map.put("spareOutBill.id", this.getId("spareOutBill.id"));
		return map;
	}

	@Override
	protected String getAdapterName() {
		return "spareOutBillDetails";
	}
    public User getLoginUser(){
    	return this.userManager.getUser();
    }
	public SpareOutBill getSpareOutBill() {
		return spareOutBill;
	}

	public void setSpareOutBill(SpareOutBill spareOutBill) {
		this.spareOutBill = spareOutBill;
	}

	public SpareOutBillDetail getSpareOutBillDetail() {
		return spareOutBillDetail;
	}

	public void setSpareOutBillDetail(SpareOutBillDetail spareOutBillDetail) {
		this.spareOutBillDetail = spareOutBillDetail;
	}

	public String getValueAry() {
		return valueAry;
	}

	public void setValueAry(String valueAry) {
		this.valueAry = valueAry;
	}

	public String getRoleWarehouseId() {
		return roleWarehouseId;
	}

	public void setRoleWarehouseId(String roleWarehouseId) {
		this.roleWarehouseId = roleWarehouseId;
	}
}
