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
package com.yongjun.tdms.service.asset.spare.inWareHouse.pojo;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.location.LocationDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDao;
import com.yongjun.tdms.dao.asset.spare.outWareHouse.SpareOutBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeCollectBillDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.LocationStatus;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBill;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBill;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetail;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.outWareHouse.SpareOutBillStatus;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.model.base.codevalue.SpareType;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBillTypeStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeStatus;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillDetailManager;
import com.yongjun.tdms.service.asset.spare.inWareHouse.SpareInBillManager;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;

/**
 * <p>Title:DefaultSpareInBillDetailManager
 * <p>Description:备件入库单明细服务控制类</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version Id:
 */
public class DefaultSpareInBillDetailManager extends BaseManager implements
		SpareInBillDetailManager {
	private final SpareInBillDetailDao spareInBillDetailDao;
	private final DepartmentManager departmentManager;
	private final SpareDao spareDao;
	private final PurchaseBillDetailDao purchaseBillDetailDao;
	private final SpareInBillManager spareInBillManager;
	private final PurchaseBillDetailManager purchaseBillDetailManager;
	private final SpareLocationManager spareLocationManager;
	private final SpareOutBillDetailDao spareOutBillDtlDao;
	private final PurchaseBillDao purchaseBillDao;
	private final SubscribeDao subscribeDao;
	private final SpareOutBillDao spareOutBillDao;
	private final SpareWareHouseManager spareWareHouseManager;
  
	private final LocationDao locationDao;
	private final SpareLocationDao spareLocationDao;
	private final SubscribeCollectBillDao subscribeCollectBillDao;

	public DefaultSpareInBillDetailManager(SpareInBillDetailDao spareInBillDetailDao,
										   SpareDao spareDao,
										   PurchaseBillDetailDao purchaseBillDetailDao,
										   SpareInBillManager spareInBillManager,
										   PurchaseBillDetailManager purchaseBillDetailManager,
										   DepartmentManager departmentManager,
										    LocationDao locationDao,
										    SpareLocationDao spareLocationDao,
										    SpareLocationManager spareLocationManager,
										    SpareOutBillDetailDao spareOutBillDtlDao,
										    PurchaseBillDao purchaseBillDao,
										    SubscribeDao subscribeDao,
										    SpareOutBillDao spareOutBillDao,
										    SubscribeCollectBillDao subscribeCollectBillDao,
										    SpareWareHouseManager spareWareHouseManager){

		this.spareInBillDetailDao = spareInBillDetailDao;
		this.spareDao = spareDao;
		this.purchaseBillDetailDao = purchaseBillDetailDao;
		this.spareInBillManager=spareInBillManager;
		this.purchaseBillDetailManager=purchaseBillDetailManager;
		this.departmentManager=departmentManager;
		this.spareLocationManager = spareLocationManager;

		this.locationDao=locationDao;
		this.spareLocationDao=spareLocationDao;
		this.spareOutBillDtlDao = spareOutBillDtlDao;
		this.purchaseBillDao =purchaseBillDao;
		this.subscribeDao = subscribeDao;
		this.spareOutBillDao = spareOutBillDao;
		this.subscribeCollectBillDao= subscribeCollectBillDao;
		this.spareWareHouseManager = spareWareHouseManager;

	}
	public void storeSpareInBillDtl(SpareInBillDetail spareInBillDtl) {
		this.spareInBillDetailDao.storeSpareInBillDtl(spareInBillDtl);
	}

	public SpareInBillDetail loadSpareInBillDtl(Long spareInBillDtlId) {
		return this.spareInBillDetailDao.loadSpareInBillDtl(spareInBillDtlId);
	}

	public List<SpareInBillDetail> loadAllSpareInBillDtl(
			Long[] spareInBillDtlIds) {
		return this.spareInBillDetailDao.loadAllSpareInBillDtl(spareInBillDtlIds);
	}

	public void deleteSpareInBillDtl(SpareInBillDetail spareInBillDtl) {
		this.spareInBillDetailDao.deleteSpareInBillDtl(spareInBillDtl);
	}
	
   /**
    * 判断入库单明细状态 如果是 新建 就可以删除
    * @param lists
    * @throws Exception
    */
   public void deleteSpareInBillDetailStatusEquesInWareHoused(List<SpareInBillDetail> lists)throws Exception{
	   for(SpareInBillDetail dtl:lists){
		   if(SpareInBillDetailStatus.INWAREHOUSED.equals(dtl.getInstatus())){
			   throw new Exception();
		   }
	   }
   }
   /**
    * 删除入库单明细
    */
	public void deleteAllSpateInBillDtl(List<SpareInBillDetail> list ,SpareInBill spareInBill)throws Exception {
		//如果删除入库明细的状态等于“已入库”  则删除失败
		deleteSpareInBillDetailStatusEquesInWareHoused(list);
		this.spareInBillDetailDao.deleteAllSpateInBillDtl(list);
	
		//当删除入库单明细时，同时更改备件台帐的总库存
		for(SpareInBillDetail detail : list){
			
			//将删除的入库单明细从入库单中给移除
			spareInBill.getDetail().remove(detail);
			//更改入库单的提醒状态
			detail.getSpareInBill().setSubmit(true);
			spareInBillManager.storeSpareInBill(detail.getSpareInBill());
			
			//删除时，判断该记录是从采购单过来，还是从备件台帐过来
			PurchaseBillDetail purBillDetail = detail.getPoDetail();
			if(null != purBillDetail && null!= purBillDetail.getId()){
				//updatePurchaseBillDtlStatus(detail);//2010-11-04 zzb 修改采购单明细的状态
				purBillDetail.setStatus(PurchaseBillDtlStatus.NEW);
				 
				this.purchaseBillDetailDao.storePurchaseBillDetail(purBillDetail);
				//同步更新 采购单状态
				this.updatePurchaseBillStatus(purBillDetail.getPurchaseBill());
				//获得 申购单明细 同步更细申购单明细 和申购单 的状态
				SubscribeDetail subscribeDetail = purBillDetail.getSubscribeDetail();
				if(null!=subscribeDetail && null != subscribeDetail.getId()){
					subscribeDetail.setStatus(SubscribeDetailStatus.PURCHASEED);
					
					this.updateSubscribeStatus(subscribeDetail.getSubscribe());
					//同步更新 汇总单 状态
					this.updateSubscribeCollecStatus(subscribeDetail.getSubscribeCollectBill());
					this.subscribeDao.storeSubscribeDetail(subscribeDetail);
				}
				
			}
			//删除时 更新出库单
			this.updateSpareOutBillDetail(detail.getSpareOutBillDtl());
			
			//删除入库单明细的时候  改变出库单的状态
			this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
			
			SpareOutBillDetail outBillDetail = detail.getSpareOutBillDtl();
			if(null !=outBillDetail){
				outBillDetail.setHaveInbill(false);
				this.spareOutBillDtlDao.storeSpareOutBillDetail(outBillDetail);
			}
			
			
			
		}
		//循环删除入库单总金额
		this.updateSpareInBill_totalPrice(spareInBill);
	}
	
	//更新采购单明细的状态
	public void updatePurchaseBillDtlStatus(SpareInBillDetail sparedetail){
		PurchaseBillDetail purdetail = sparedetail.getPoDetail();//获得采购单明细
		
		if(purdetail!=null){
			  if(purdetail.getId()!=null){
				  //在入库单明细中冗余了一个状态的字段,目的是存储从采购单明细copy到入库单明细的中的属于采购单明细的当前状态
				  //确保删除入库单明细时恢复采购单明细的原有状态 
				  if(sparedetail.getStatus().equals("INSPECTED")){
					  purdetail.setStatus(PurchaseBillDtlStatus.INSPECTED);    
					  purchaseBillDetailDao.storePurchaseBillDetail(purdetail);  	
				  }
				  if(sparedetail.getStatus().equals("unSPECT")){
					  purdetail.setStatus(PurchaseBillDtlStatus.NEW);
					  purchaseBillDetailDao.storePurchaseBillDetail(purdetail);  	
				  } 				  
			  }
//			  PurchaseBill purBill = purdetail.getPurchaseBill();//获得采购单
			 
			  purchaseBillDetailManager.accordingSpareInWarehouseDetailChangePurchaseBillStatus(purdetail);
		   }
		 
		
	}
 	
	/**
	 * 删除时 更新出库单明细的状态
	 */
	public void updateSpareOutBillDetail(SpareOutBillDetail detail){
		if(null != detail ){
			 detail.setHaveInbill(false);
			 this.spareOutBillDtlDao.storeSpareOutBillDetail(detail);
			 
		}
		
	}
	
	/**
	 * 从备件库台帐-->入库单明细
	 */
	public void storeSpareInBillDtlFromAccount(SpareInBill spareInBill, String addSpareAccountIds) {
		String[] addSpareAccountId = null;
		if(null != addSpareAccountIds){
			addSpareAccountId = addSpareAccountIds.split(",");
		}
		for(int i=0;i<addSpareAccountId.length;i++){
			SpareInBillDetail detail = new SpareInBillDetail();
			SpareLocation spareLocation = spareLocationDao.loadSpareLocation(Long.valueOf(addSpareAccountId[i]));
			detail.setSpare(spareLocation.getSpare());
			
			//判断选择的备件的仓库是否是入库单的 来源仓库
			if(spareLocation.getWarehouse().getId().equals(spareInBill.getOutWarehouse().getId())){
				if(null != spareLocation.getLocation()){  
					detail.setLocation(spareLocation.getLocation()); //复制库位的id到入库单明细
					detail.setLocationCode(spareLocation.getLocation().getCode());//复制库位的code到入库单明细
				}
			}
			
			if(null != spareLocation.getUnitPrice()){        //复制单价到入库单明细 税前单价
				detail.setTaxPrice(spareLocation.getUnitPrice());
			}else{
				detail.setTaxPrice(Double.valueOf(0.0));
			}
			
			if(null != spareLocation.getDepartment()){  //复制部门到入库单明细
				detail.setDepartment(spareLocation.getDepartment());
				detail.setDeptName(spareLocation.getDepartment().getName());
			}else{
				detail.setDepartment(null);
			}
//			if(null != spareLocation.getPreStocks()){  //复制期初库存到入库单明细
//			   detail.setStocksBeforeInOrOut(spareLocation.getStocks());
//			}else{
//				detail.setStocksBeforeInOrOut(Long.valueOf(0));
//			}
			 
			detail.setSpareInBill(spareInBill);
			detail.getSpareInBill().setSubmit(true);
            //copy备件台帐到备件入库单明细
			copySpareAccountToSpareInBillDtl(spareLocation.getSpare(),detail);				
			this.spareInBillDetailDao.storeSpareInBillDtl(detail);
			this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
		}
	}
	/**
	 * copy备件台帐到备件入库单明细
	 * @param spare
	 * @param detail
	 */
	private void copySpareAccountToSpareInBillDtl(Spare spare, SpareInBillDetail detail) {
		if(spare.getSpareNo()!=null){						//复制编号
			detail.setCode(spare.getSpareNo());
		}else{
			detail.setCode(null);
		}
		if(spare.getName()!=null){
			detail.setName(spare.getName());				//复制名称
		}else{
			detail.setName(null);
		}
	}
	/**
	 * 从采购单明细-->入库单明细
	 */
	public void storeSpareInBillDtlFromPurBillDtl(SpareInBill spareInBill, String addPurchaseBillDetailIds) {
		String[] addPurchaseBillDetailId = null;
		if(null != addPurchaseBillDetailIds){
			addPurchaseBillDetailId = addPurchaseBillDetailIds.split(",");
		}
		for(int i=0;i<addPurchaseBillDetailId.length;i++){
			SpareInBillDetail detail = new SpareInBillDetail();
            //获得采购单明细
			PurchaseBillDetail purBillDtl = purchaseBillDetailDao.loadPurchaseBillDetail(Long.valueOf(addPurchaseBillDetailId[i]));
		    //获得采购单
			PurchaseBill purBill = purBillDtl.getPurchaseBill();
		    if(null != purBill){
		    	//同步到 采购单明细
		    	purBill.setStatus(PurchaseBillStatus.INSPECTING);
		    	/*String keyValue[]=new String[2];
		    	Object arryValue[] = new Object[2];
		    	keyValue[0] = "purchaseBill";
		    	keyValue[1] = "status";
		    	
		    	arryValue[0] = purBill.getId();
		    	arryValue[1] = PurchaseBillStatus.INSPECTED;
		    	try {
					List<PurchaseBillDetail> list = this.purchaseBillDetailDao.loadByKeyArry(keyValue, arryValue);
					List<PurchaseBillDetail> list2 = this.purchaseBillDetailDao.loadByKey("purchaseBill", purBill.getId()); 
					if(null !=list && null !=list2 && list.size() == list2.size()){
						purBill.setStatus(PurchaseBillStatus.INSPECTED);
					}
				} catch (DaoException e) {
					e.printStackTrace();
				}*/
		    	
		    	SubscribeDetail subDetail = purBillDtl.getSubscribeDetail();
                // 同步到 申购单明细
		    	if(null !=subDetail && null !=subDetail.getId()){
		    		subDetail.setStatus(SubscribeDetailStatus.INSPECTING);
		    		this.subscribeDao.storeSubscribeDetail(subDetail);
		    		//同步更新 申购单 状态
		    		this.updateSubscribeStatus(subDetail.getSubscribe());
		    		//同步更新 汇总单状态
		    		this.updateSubscribeCollecStatus(subDetail.getSubscribeCollectBill());
		    	
		    	}
		    	
		    	
		    }
           if(null!=purBillDtl.getDepart()){
        		detail.setDepartment(purBillDtl.getDepart());
        		detail.setDeptName(purBillDtl.getDepart().getName());
           }else{
        	   detail.setDepartment(null);
        	   detail.setDeptName(null);
           }
			detail.setSpare(purBillDtl.getSpare());
			detail.setPoDetail(purBillDtl);
			detail.setSpareInBill(spareInBill);
            //copy采购单明细到备件入库单明细
			copyPurBillDtlToSpareInBillDtl(purBillDtl,detail);
			//将入库单的submit设置为FALSE
			detail.getSpareInBill().setSubmit(true);
			//同步更新采购单的状态
			purBillDtl.setStatus(PurchaseBillDtlStatus.INSPECTING);//验收、入库中
			this.purchaseBillDetailDao.storePurchaseBillDetail(purBillDtl);
			
			this.spareInBillDetailDao.storeSpareInBillDtl(detail);
			this.purchaseBillDao.storePurchaseBill(purBill);
			
			this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
		}
	}
    /**
     * copy采购单明细到备件入库单明细
     * @param purBillDtl
     * @param detail 
     */
	private void copyPurBillDtlToSpareInBillDtl(PurchaseBillDetail purBillDtl, SpareInBillDetail detail) {
		if(purBillDtl.getSpare()!=null){
			if(purBillDtl.getSpare().getSpareNo()!=null){						//复制编号
				detail.setCode(purBillDtl.getSpare().getSpareNo());
			}else{
				detail.setCode(null);
			}
			if(purBillDtl.getSpare().getName()!=null){							//复制名称
				detail.setName(purBillDtl.getSpare().getName());				
			}else{
				detail.setName(null);
			}
			if(purBillDtl.getSpare().getStocks()!=null){										
				detail.setStocksBeforeInOrOut(Long.valueOf(0));//复制总库存
			}else{
				detail.setStocksBeforeInOrOut(Long.valueOf(0));
			}
		}
		if(purBillDtl.getTaxPrice()!=null){									//复制单价
			detail.setTaxPrice(purBillDtl.getTaxPrice());		
		}else{
			detail.setTaxPrice(Double.valueOf(0.0));
		}
		
		if(purBillDtl.getAmount()!=null){										//采购数量
			//默认入库数量
			detail.setNumber(Long.valueOf((purBillDtl.getAmount()-purBillDtl.getArrivalAmount())));
			//当前采购数量
			detail.setPurchaseNum(purBillDtl.getAmount()-purBillDtl.getArrivalAmount());
             //	同时计算出金额
			detail.setTotalPrice((purBillDtl.getAmount()-purBillDtl.getArrivalAmount())*purBillDtl.getTaxPrice());
		}
		
		

      //如果入库明细列表的数据是从采购明细中copy的,那么先记录下当前采购单明细的状态是“未验收”还是“已验收”
	  //目的是在删除的记录是从采购单明细中选择的，方便恢复采购单明细的状态
		detail.setStatus(detail.getPoDetail().getStatus().toString());
		
	}
	
	
	
	/**
	 * 从备件-->入库单明细
	 */
	public void storeSpareInBillFromSpare(SpareInBill spareInBill, String addSpareIds) {
		String[] addSpareId = null;
		if(null != addSpareIds){
			addSpareId = addSpareIds.split(",");
		}
		for(int i=0;i<addSpareId.length;i++){
			SpareInBillDetail detail = new SpareInBillDetail();
			Spare spare = spareDao.loasSpare(Long.valueOf(addSpareId[i]));
			
			detail.setSpare(spare);
			
		
			 //复制单价到入库单明细
			Double untilPrice = spare.getUnitPrice();
			detail.setTaxPrice(null == untilPrice? Double.valueOf(0.0):untilPrice);
            //复制期初库存到入库单明细
		    Long previousStocks = spare.getPreviousStocks();
			detail.setStocksBeforeInOrOut(null == previousStocks ? Long.valueOf(0):previousStocks);
			
		 
			detail.setSpareInBill(spareInBill);
			detail.getSpareInBill().setSubmit(true);
            //copy备件台帐到备件入库单明细
			copySpareToSpareInBillDtl(spare,detail);				
			this.spareInBillDetailDao.storeSpareInBillDtl(detail);
			this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
		}
		
	}
		
		/**
		 * copy备件台帐到备件入库单明细
		 * @param spare
		 * @param detail
		 */
	private void copySpareToSpareInBillDtl(Spare spare, SpareInBillDetail detail) {
			if(spare.getSpareNo()!=null){						//复制编号
				detail.setCode(spare.getSpareNo());
			}else{
				detail.setCode(null);
			}
			if(spare.getName()!=null){
				detail.setName(spare.getName());				//复制名称
			}else{
				detail.setName(null);
			}
		}	
	/**
	 * 从出库单明细 -->入库单明细
	 */
	public void storeSpareInBillFromSpareOutBillDtl(SpareInBill spareInBill, String addSpareOutBillDtlIds) {
		String[] addSpareOutBillDtlId = null;
		 
		if(null != addSpareOutBillDtlIds){
			addSpareOutBillDtlId = addSpareOutBillDtlIds.split(",");
		}
		for(int i=0;i<addSpareOutBillDtlId.length;i++){
			SpareInBillDetail detail = new SpareInBillDetail();
			
			SpareOutBillDetail spareOutBillDtl = this.spareOutBillDtlDao.loadSpareOutBillDetail(Long.valueOf(addSpareOutBillDtlId[i])) ;
	 	
		/*	Department dept = spareOutBillDtl.getDepartment();
			Location location = spareOutBillDtl.getLocation();
			detail.setLocation(null == location ? null : location);
            detail.setLocationCode(null == location ? null : location.getCode());
            detail.setDepartment(null == dept ? null : dept);
          */
			detail.setSpareOutBillDtl(spareOutBillDtl);
			detail.setSpareInBill(spareInBill);
			
			Spare spare = spareOutBillDtl.getSpare();
			if(null!=spare){
    			detail.setSpare(spare);
            }
            //copy出库单单明细到备件入库单明细
			if(null != spare){
				detail.setCode(spare.getSpareNo());//复制编号
				detail.setName(spare.getName());//复制名称
				//detail.setStocksBeforeInOrOut(spare.getStocks());//复制总库存
			}
			Double unitPrice = spareOutBillDtl.getUnitPrice();
			Long number = spareOutBillDtl.getNumber();
			
			//detail.setUnitPrice(unitPrice);//复制单价
			detail.setTaxPrice(unitPrice);
			detail.setNumber(spareOutBillDtl.getNumber()-spareOutBillDtl.getInNumber());   //入库数量
			
            //同时计算出金额
			
			detail.setTotalPrice(unitPrice*number);
			  
			//将入库单的submit设置为FALSE
			detail.getSpareInBill().setSubmit(true);
			
			spareOutBillDtl.setHaveInbill(true);//标识 被二级入库单选择
			this.spareOutBillDtlDao.storeSpareOutBillDetail(spareOutBillDtl);
			//同步更新出库单的状态
			this.updateSpareOutBillStatus(spareOutBillDtl.getSpareOutBill());
			this.spareInBillDetailDao.storeSpareInBillDtl(detail);
			this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
		}
	}
	
	/**
	 * 更新出库单的状态
	 * @param spareOutBill 出库单
	 */
	public void updateSpareOutBillStatus(SpareOutBill spareOutBill){
		int newNum =0;
		int outNum =0;
		Set<SpareOutBillDetail> set = spareOutBill.getDetail();
		if(null != set && set.size()>0){
			int size = set.size();
			for(SpareOutBillDetail detail : set){
				if(SpareOutBillDetailStatus.NEW.equals(detail.getStatus())){
					newNum++;
				}
				if(SpareOutBillDetailStatus.OUTWAREHOUSEED.equals(detail.getStatus())){
					outNum++;
				}
			}
			if(size == newNum){
				spareOutBill.setStatus(SpareOutBillStatus.NEWSTATUS);
			}else if(size == outNum){
				spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEED);
			}else{
				spareOutBill.setStatus(SpareOutBillStatus.OUTWAREHOUSEING);
			}
			this.spareOutBillDao.storeSpareOutBill(spareOutBill);
			
		}
	}
	/** 
	 * 更新 汇总单的状态
	 * @param collBill 汇总单
	 */
	public void updateSubscribeCollecStatus(SubscribeCollectBill collBill){
		if(null !=collBill){
			int inspectingNum=0;//入库中
	    	int inspectedNum=0;//已入库
	    	int purchaseNum=0; //已采购
			   try {
					List<SubscribeDetail> list = this.subscribeDao.loadByKey("subscribeCollectBill", collBill.getId());
				    if(null != list && list.size()>0){
				    	int size = list.size();
				    
				    	for(SubscribeDetail detail : list){
				    		if(SubscribeDetailStatus.INSPECTING.equals(detail.getStatus())){
				    			inspectingNum++;
				    		}
				    		if(SubscribeDetailStatus.INSPECTED.equals(detail.getStatus())){
				    			inspectedNum++;
				    		}if(SubscribeDetailStatus.PURCHASEED.equals(detail.getStatus())){
				    			purchaseNum++;
				    		}
				    	}
				    	if(size == inspectingNum){
				    		collBill.setBillStatus(SubscribeCollectBillTypeStatus.INSPECTING);
				    	}else if(size == inspectedNum){
				    		collBill.setBillStatus(SubscribeCollectBillTypeStatus.INSPECTED);
				    	}else if(inspectingNum>0){
				    		collBill.setBillStatus(SubscribeCollectBillTypeStatus.INSPECTING);
				    	}else if(size ==purchaseNum ){
				    		collBill.setBillStatus(SubscribeCollectBillTypeStatus.PURCHASEED);
				    	}else if(0 ==inspectingNum && 0 == inspectedNum && purchaseNum>0){
				    		collBill.setBillStatus(SubscribeCollectBillTypeStatus.PURCHASING);
				    	}
				    	
				    	this.subscribeCollectBillDao.storeSubscribeCollectBill(collBill);
				    	
				    }
				} catch (DaoException e) {
					e.printStackTrace();
				}
		}

		
	}
	
	
	/**
	 * 保存修改后的入库单明细信息
	 */
	public void storeSpareInBillDtl(long roleWarehouseId,
									String allSpareInBillDtlComment, 
									String allSpareInBillDtlNumber, 
									String allSpareInBillDtlId,
									String allSpareInBillDeparement,
									String allLocationCodeValue,
									String allSpareInBillDtlTaxPriceValue
	                                ) {
		String[] dtlIdArr = null;
		String[] dtlNumberArr = null;
		String[] dtlCommentArr = null;
		String[] dtlDeparementArr=null;
	    String[] dtlLocationCodeValue=null;
        String[] dtlTaxPriceValue=null;
        
		if(null!=allSpareInBillDeparement)
		{
			dtlDeparementArr=allSpareInBillDeparement.split(",");
		}
		if(null!=allSpareInBillDtlId){
			dtlIdArr = allSpareInBillDtlId.split(",");    			//入库单明细ID数组
		}
		if(null!=allSpareInBillDtlTaxPriceValue){
			dtlTaxPriceValue = allSpareInBillDtlTaxPriceValue.split(",");  	//入库单明细ID和入库单价数组
		}
		if(null!=allSpareInBillDtlNumber){
			dtlNumberArr = allSpareInBillDtlNumber.split(",");  	//入库单明细ID和入库数量数组
		}
		if(null!=allSpareInBillDtlComment){
			dtlCommentArr = allSpareInBillDtlComment.split(",");  	//入库单明细ID和备注数组
		}
		
		if(null!=allLocationCodeValue){
			dtlLocationCodeValue = allLocationCodeValue.split(",");  	//入库单明细ID和库位ID数组
		}
		updateSpareInBillDetail(roleWarehouseId,dtlIdArr,dtlNumberArr,dtlCommentArr,dtlDeparementArr,dtlLocationCodeValue,dtlTaxPriceValue);	//更新入库明细的信息

	}
	
	/**
	 * 保存修改后的入库单明细信息
	 */
	public void storeSpareInBillDtlForOldToNew(long roleWarehouseId,
									String allSpareInBillDtlComment, 
									 String allSpareInBillDtlName, String allSpareInBillDtlModel,
									String allSpareInBillDtlNumber, 
									String allSpareInBillDtlId,
									String allSpareInBillDeparement,
									String allLocationCodeValue,
									String allSpareInBillDtlTaxPriceValue,
									String allDisableSpare
	                                ) {
		String[] dtlIdArr = null;
		String[] dtlNumberArr = null;
		String[] dtlCommentArr = null;
		String[] dtlNamerArr = null;
		String[] dtlModelArr = null;
		String[] dtlDeparementArr=null;
	    String[] dtlLocationCodeValue=null;
        String[] dtlTaxPriceValue=null;
        String[] allDisableSpareArr=null;
        
        if(null!=allDisableSpare)
		{
        	allDisableSpareArr=allDisableSpare.split(",");
		}
		if(null!=allSpareInBillDtlName)
		{
			dtlNamerArr=allSpareInBillDtlName.split(",");
		}
		if(null!=allSpareInBillDtlModel)
		{
			dtlModelArr=allSpareInBillDtlModel.split(",");
		}
		if(null!=allSpareInBillDeparement)
		{
			dtlDeparementArr=allSpareInBillDeparement.split(",");
		}
		if(null!=allSpareInBillDtlId){
			dtlIdArr = allSpareInBillDtlId.split(",");    			//入库单明细ID数组
		}
		if(null!=allSpareInBillDtlTaxPriceValue){
			dtlTaxPriceValue = allSpareInBillDtlTaxPriceValue.split(",");  	//入库单明细ID和入库单价数组
		}
		if(null!=allSpareInBillDtlNumber){
			dtlNumberArr = allSpareInBillDtlNumber.split(",");  	//入库单明细ID和入库数量数组
		}
		if(null!=allSpareInBillDtlComment){
			dtlCommentArr = allSpareInBillDtlComment.split(",");  	//入库单明细ID和备注数组
		}
		
		if(null!=allLocationCodeValue){
			dtlLocationCodeValue = allLocationCodeValue.split(",");  	//入库单明细ID和库位ID数组
		}
		updateOldSpareInBillDetailForOldToNew(roleWarehouseId,dtlIdArr,dtlNamerArr,dtlModelArr,dtlNumberArr,dtlCommentArr,dtlDeparementArr,dtlLocationCodeValue,dtlTaxPriceValue,allDisableSpareArr);	//更新入库明细的信息

	}
	
	/**
	 * 保存修改后的入库单明细信息
	 */
	public void storeOldSpareInBillDtl(long roleWarehouseId,
									String allSpareInBillDtlComment, 
									String allSpareInBillDtlNumber, 
									String allSpareInBillDtlId,
									String allSpareInBillDeparement,
									String allLocationCodeValue,
									String allSpareInBillDtlTaxPriceValue,
									String allDisableSpare
	                                ) {
		String[] dtlIdArr = null;
		String[] dtlNumberArr = null;
		String[] dtlCommentArr = null;
		String[] dtlDeparementArr=null;
	    String[] dtlLocationCodeValue=null;
        String[] dtlTaxPriceValue=null;
        String[] allDisableSpareArr=null;
        
        
		if(null!=allDisableSpare)
		{
			allDisableSpareArr=allDisableSpare.split(",");
		}
		if(null!=allSpareInBillDeparement)
		{
			dtlDeparementArr=allSpareInBillDeparement.split(",");
		}
		if(null!=allSpareInBillDtlId){
			dtlIdArr = allSpareInBillDtlId.split(",");    			//入库单明细ID数组
		}
		if(null!=allSpareInBillDtlTaxPriceValue){
			dtlTaxPriceValue = allSpareInBillDtlTaxPriceValue.split(",");  	//入库单明细ID和入库单价数组
		}
		if(null!=allSpareInBillDtlNumber){
			dtlNumberArr = allSpareInBillDtlNumber.split(",");  	//入库单明细ID和入库数量数组
		}
		if(null!=allSpareInBillDtlComment){
			dtlCommentArr = allSpareInBillDtlComment.split(",");  	//入库单明细ID和备注数组
		}
		
		if(null!=allLocationCodeValue){
			dtlLocationCodeValue = allLocationCodeValue.split(",");  	//入库单明细ID和库位ID数组
		}
		updateOldSpareInBillDetail(roleWarehouseId,dtlIdArr,dtlNumberArr,dtlCommentArr,dtlDeparementArr,dtlLocationCodeValue,dtlTaxPriceValue,allDisableSpareArr);	//更新入库明细的信息

	}
	/**
	 * 更新入库明细的信息
	 * @param dtlIdArr 入库单明细ID数组
	 * @param dtlNumberArr 入库单明细ID和入库数量数组
	 * @param dtlCommentArr 入库单明细ID和备注数组
	 * @param dtlDeparementArr 入库单明细ID和部门数组
	 * @param dtlLocationCodeValue 入库单明细ID和库位号数组
	 * @param dtlUnitPriceArr 入库单明细ID和入库单价数组
	 */
	private void updateSpareInBillDetail(long roleWarehouseId,String[] dtlIdArr, String[] dtlNumberArr, String[] dtlCommentArr,
											String[] dtlDeparementArr,String[] dtlLocationCodeValue,String[] dtlTaxPriceValue) {

		SpareInBillDetail detail = new SpareInBillDetail();
		//入库明细循环次数
		int spareInBillDetailNum = 0;
		while(dtlIdArr != null && spareInBillDetailNum<dtlIdArr.length){
			//根据入库单明细ID获取入库单对象
			detail = spareInBillDetailDao.loadSpareInBillDtl(Long.valueOf(dtlIdArr[spareInBillDetailNum]));
		 
			//入库单关联的备件
			Spare spare = detail.getSpare();
			Department dept =null;
			Location location = null;
			if (detail.getInstatus().toString().equals(SpareInBillDetailStatus.INWAREHOUSED.toString())) {
				spareInBillDetailNum++;
				continue;
			}
			
			//获得相应的部门，并set进入detail中，使其成为游离状态，等待保存为持久态
			if (null != dtlDeparementArr) {
				for (int i=0; i<dtlDeparementArr.length; i=i+2) {              //设置计划明细中的责任单位
					if (dtlDeparementArr[i].equals(dtlIdArr[spareInBillDetailNum])) {
						dept = this.departmentManager.loadDepartment(Long.valueOf(dtlDeparementArr[i+1]));
						detail.setDepartment(dept);
						detail.setDeptName(dept.getName());
						
					    break;
					} else {
						detail.setDepartment(null);
					}
				}
			} else {
				detail.setDepartment(null);
			}
			
			//获得库位号，并同样set进入detail，同时进入库位管理中将该库位号更改为已用状态
			if(dtlLocationCodeValue!=null){
				for(int i=0;i<dtlLocationCodeValue.length;i=i+2){
					if(dtlLocationCodeValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						if ((i+1) < dtlLocationCodeValue.length) {
//							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1]);
//							long locationId = Long.parseLong(dtlLocationCodeValue[i+1]);
//							location=locationDao.loadLocation(locationId);
							//location=locationDao.findLocationByWarehouse(dtlLocationCodeValue[i+1], roleWarehouseId);
							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1], roleWarehouseId);
							//System.out.println("=======" + location.getId());
							if (null != location) {
								detail.setLocationCode(location.getCode());
								detail.setLocation(location);
								detail.setRegional(location.getRegional());
								detail.setWarehouse(location.getWarehouse());
								//将库位的状态置为“已用”
								updateLocationStatusToUsed(location);
							}
							else {
								detail.setLocationCode(null);
								detail.setLocation(null);
							}
						} else {
							detail.setLocationCode(null);
							detail.setLocation(null);
						}
						break;
					}else{
						detail.setLocationCode(null);
						detail.setLocation(null);
					}
				}
			}else{
				detail.setLocationCode(null);
				detail.setLocation(null);
			}			
			//保存入库数量
			long amountNumber = 0;
			if(dtlNumberArr!= null){
				for(int i=0;i<dtlNumberArr.length;i=i+2){
					if(dtlNumberArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountNumber = Long.valueOf(dtlNumberArr[i+1]);
						//将入库数量设置为页面传过来的入库数量
						detail.setNumber(amountNumber);
						
						//如果是二级库入库单
						if (detail.getWarehouse().getStorageGrade().getId()==209||(detail.getWarehouse().getStorageGrade().getId()==208&&detail.getSpareOutBillDtl()!=null)){
							SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
							spareOutBillDetail.setInNumber(spareOutBillDetail.getInNumber().longValue()+amountNumber);
							SpareLocation spareLocation = spareOutBillDetail.getSpareLocation();
							spareLocation.setLockedStocks(spareLocation.getLockedStocks()-amountNumber);//减去源仓库的锁定库存
							spareLocation.setStocks(spareLocation.getStocks()-amountNumber);    //减去源仓库的库存
							Long allStocks = this.spareLocationDao.getSumBySpare(spareOutBillDetail.getSpareLocation().getSpare().getId());
							Spare spare1 = spareOutBillDetail.getSpare();
							//更新备件总库存
							spare.setStocks(allStocks);
							this.spareOutBillDtlDao.storeSpareOutBillDetail(spareOutBillDetail);
							//更新出库单已入金额
							Double totalPrice = NumberUtils.DOUBLE_ZERO;
							SpareOutBill spareOutBill = spareOutBillDetail.getSpareOutBill();
							for(SpareOutBillDetail detail1 : spareOutBill.getDetail()){
								if(detail1.getInNumber()!=null){
									totalPrice = totalPrice + detail1.getInNumber()*detail1.getUnitPrice();
									//System.out.println("setInTotalPrice111=" + detail1.getUnitPrice() + "N2222:"+detail1.getInNumber());
								}
							}
							//System.out.println("setInTotalPrice=" + totalPrice);
							spareOutBill.setInTotalPrice(totalPrice);
							this.spareOutBillDao.storeSpareOutBill(spareOutBill);
							
							this.spareDao.storeSpare(spare1);
							this.spareLocationDao.storeSpareLocation(spareLocation);
							//更新备件库总台帐库存
							this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
							
							
						}
						PurchaseBillDetail purDetail = detail.getPoDetail();
						
						//保存到采购单明细的到货数量、到货日期
						if(null != purDetail){
							if(purDetail.getId()!=null){
							  purDetail.setArrivalAmount(purDetail.getArrivalAmount()+(int)amountNumber);
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //如果入库数量 大于等于采购数量 置采购单明细为 已入库
							  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
								  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
									  purDetail.setStatus(PurchaseBillDtlStatus.INSPECTED);
								  }else{
										 purDetail.setStatus(PurchaseBillDtlStatus.INSPECTING);
								 }
							  }
							  //同步更新 采购单的 实际到货日期
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //同步更新 申购单明细的到货数量、到货日期和状态
							  SubscribeDetail subsDetail  = purDetail.getSubscribeDetail();
							  if(null !=subsDetail && null!=subsDetail.getId()){
								  //如果入库数量(到货数量)等于采购数量置 申购单明细 为 已入库
								  if(null != subsDetail.getArrivalAmount()){
									  subsDetail.setArrivalAmount(subsDetail.getArrivalAmount()+(int)amountNumber);
								  }else{
									  subsDetail.setArrivalAmount((int)amountNumber);
								  }
								  subsDetail.setArrivalDate(detail.getSpareInBill().getInDate());
								  if(amountNumber==purDetail.getAmount()){
								    subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
								     
								  } 
								  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
									  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
										  subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
									  }
								  }
								  this.subscribeDao.storeSubscribeDetail(subsDetail);
								  //同步更新 申购单状态
								  this.updateSubscribeStatus(subsDetail.getSubscribe());
								  //同步更新 汇总单
								  this.updateSubscribeCollecStatus(subsDetail.getSubscribeCollectBill());
							  } 
							
							  purchaseBillDetailDao.storePurchaseBillDetail(purDetail);
							  //同步更新 采购单 的状态
							  this.updatePurchaseBillStatus(purDetail.getPurchaseBill());
							}
						} 
						
						break;
					}
				}
			}
			
			//保存入库单价
			Double amountUnitprice =0.00;
			if(dtlTaxPriceValue!= null){
				for(int i=0;i<dtlTaxPriceValue.length;i=i+2){
					if(dtlTaxPriceValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountUnitprice = Double.valueOf(dtlTaxPriceValue[i+1]);
						spare.setUnitPrice(amountUnitprice);
						
						//detail.setUnitPrice(amountUnitprice);
						//税前价格
						detail.setTaxPrice(amountUnitprice);
						 break;
					}
				}
			}
			
			//计算总金额
			detail.setTotalPrice(amountNumber*(detail.getTaxPrice()));
			//保存备注
			if(dtlCommentArr!=null){
				for(int i=0;i<dtlCommentArr.length;i=i+2){
					if(dtlCommentArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						detail.setComment(dtlCommentArr[i+1]);
						break;
					}else{
						detail.setComment(null);
					}
				}
			}else{
				detail.setComment(null);
			}

			
		//入库明细保存库位号之后,根据spare.id,department.id,location.id判断此备件,部门,库位在t_spare_location中是否存在,如果存在则更新
		//t_spare_location中的总库存,
	    this.spareLocationManager.storeOrUpdateSpareLocationByInBillDetail(detail);
	    //更新备件库总台帐
	    this.spareWareHouseManager.storeOrUpdateSpareWareHouse(detail.getSpare(),detail.getSpareInBill().getWarehouse(), detail.getNumber());
	    detail.setStocksAfterInOrOut(detail.getStocksBeforeInOrOut()+detail.getNumber());   //设置入库单明细入库后库存
	    
	    detail.setInstatus(SpareInBillDetailStatus.INWAREHOUSED);
 	    detail.setStatus(SpareInBillDetailStatus.INWAREHOUSED.toString());
	    //获得出库单明细
 	    //SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
 	 
 	    
 	    
		this.spareInBillDetailDao.storeSpareInBillDtl(detail);
		
		//TODO 放在while循环之外     入库单明细保存之后  改变入库单的状态
		this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
 
		//改变入库单的提醒状态
		detail.getSpareInBill().setSubmit(true);
		spareInBillManager.storeSpareInBill(detail.getSpareInBill());
		spareInBillDetailNum++;
		
		}
		//更新入库单总金额
		updateSpareInBill_totalPrice(detail.getSpareInBill());
	}
	
	/**
	 * 更新入库明细的信息
	 * @param dtlIdArr 入库单明细ID数组
	 * @param dtlNumberArr 入库单明细ID和入库数量数组
	 * @param dtlCommentArr 入库单明细ID和备注数组
	 * @param dtlDeparementArr 入库单明细ID和部门数组
	 * @param dtlLocationCodeValue 入库单明细ID和库位号数组
	 * @param dtlUnitPriceArr 入库单明细ID和入库单价数组
	 */
	private void updateSpareInBillDetailForOldToNew(long roleWarehouseId,String[] dtlIdArr, String[] dtlNumberArr, String[] dtlCommentArr,
											String[] dtlDeparementArr,String[] dtlLocationCodeValue,String[] dtlTaxPriceValue) {

		SpareInBillDetail detail = new SpareInBillDetail();
		//入库明细循环次数
		int spareInBillDetailNum = 0;
		while(dtlIdArr != null && spareInBillDetailNum<dtlIdArr.length){
			//根据入库单明细ID获取入库单对象
			detail = spareInBillDetailDao.loadSpareInBillDtl(Long.valueOf(dtlIdArr[spareInBillDetailNum]));
		 
			//入库单关联的备件
			Spare spare = detail.getSpare();
			Department dept =null;
			Location location = null;
			if (detail.getInstatus().toString().equals(SpareInBillDetailStatus.INWAREHOUSED.toString())) {
				spareInBillDetailNum++;
				continue;
			}
			
			//获得相应的部门，并set进入detail中，使其成为游离状态，等待保存为持久态
			if (null != dtlDeparementArr) {
				for (int i=0; i<dtlDeparementArr.length; i=i+2) {              //设置计划明细中的责任单位
					if (dtlDeparementArr[i].equals(dtlIdArr[spareInBillDetailNum])) {
						dept = this.departmentManager.loadDepartment(Long.valueOf(dtlDeparementArr[i+1]));
						detail.setDepartment(dept);
						detail.setDeptName(dept.getName());
						
					    break;
					} else {
						detail.setDepartment(null);
					}
				}
			} else {
				detail.setDepartment(null);
			}
			
			//获得库位号，并同样set进入detail，同时进入库位管理中将该库位号更改为已用状态
			if(dtlLocationCodeValue!=null){
				for(int i=0;i<dtlLocationCodeValue.length;i=i+2){
					if(dtlLocationCodeValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						if ((i+1) < dtlLocationCodeValue.length) {
//							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1]);
//							long locationId = Long.parseLong(dtlLocationCodeValue[i+1]);
//							location=locationDao.loadLocation(locationId);
							//location=locationDao.findLocationByWarehouse(dtlLocationCodeValue[i+1], roleWarehouseId);
							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1], roleWarehouseId);
							//System.out.println("=======" + location.getId());
							if (null != location) {
								detail.setLocationCode(location.getCode());
								detail.setLocation(location);
								detail.setRegional(location.getRegional());
								detail.setWarehouse(location.getWarehouse());
								//将库位的状态置为“已用”
								updateLocationStatusToUsed(location);
							}
							else {
								detail.setLocationCode(null);
								detail.setLocation(null);
							}
						} else {
							detail.setLocationCode(null);
							detail.setLocation(null);
						}
						break;
					}else{
						detail.setLocationCode(null);
						detail.setLocation(null);
					}
				}
			}else{
				detail.setLocationCode(null);
				detail.setLocation(null);
			}			
			//保存入库数量
			long amountNumber = 0;
			if(dtlNumberArr!= null){
				for(int i=0;i<dtlNumberArr.length;i=i+2){
					if(dtlNumberArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountNumber = Long.valueOf(dtlNumberArr[i+1]);
						//将入库数量设置为页面传过来的入库数量
						detail.setNumber(amountNumber);
						
						//如果是二级库入库单
						if (detail.getWarehouse().getStorageGrade().getId()==209||(detail.getWarehouse().getStorageGrade().getId()==208&&detail.getSpareOutBillDtl()!=null)){
							SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
							spareOutBillDetail.setInNumber(spareOutBillDetail.getInNumber().longValue()+amountNumber);
							SpareLocation spareLocation = spareOutBillDetail.getSpareLocation();
							spareLocation.setLockedStocks(spareLocation.getLockedStocks()-amountNumber);//减去源仓库的锁定库存
							spareLocation.setStocks(spareLocation.getStocks()-amountNumber);    //减去源仓库的库存
							Long allStocks = this.spareLocationDao.getSumBySpare(spareOutBillDetail.getSpareLocation().getSpare().getId());
							Spare spare1 = spareOutBillDetail.getSpare();
							//更新备件总库存
							spare.setStocks(allStocks);
							this.spareOutBillDtlDao.storeSpareOutBillDetail(spareOutBillDetail);
							//更新出库单已入金额
							Double totalPrice = NumberUtils.DOUBLE_ZERO;
							SpareOutBill spareOutBill = spareOutBillDetail.getSpareOutBill();
							for(SpareOutBillDetail detail1 : spareOutBill.getDetail()){
								if(detail1.getInNumber()!=null){
									totalPrice = totalPrice + detail1.getInNumber()*detail1.getUnitPrice();
									//System.out.println("setInTotalPrice111=" + detail1.getUnitPrice() + "N2222:"+detail1.getInNumber());
								}
							}
							//System.out.println("setInTotalPrice=" + totalPrice);
							spareOutBill.setInTotalPrice(totalPrice);
							this.spareOutBillDao.storeSpareOutBill(spareOutBill);
							
							this.spareDao.storeSpare(spare1);
							this.spareLocationDao.storeSpareLocation(spareLocation);
							//更新备件库总台帐库存
							this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
							
							
						}
						PurchaseBillDetail purDetail = detail.getPoDetail();
						
						//保存到采购单明细的到货数量、到货日期
						if(null != purDetail){
							if(purDetail.getId()!=null){
							  purDetail.setArrivalAmount(purDetail.getArrivalAmount()+(int)amountNumber);
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //如果入库数量 大于等于采购数量 置采购单明细为 已入库
							  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
								  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
									  purDetail.setStatus(PurchaseBillDtlStatus.INSPECTED);
								  }else{
										 purDetail.setStatus(PurchaseBillDtlStatus.INSPECTING);
								 }
							  }
							  //同步更新 采购单的 实际到货日期
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //同步更新 申购单明细的到货数量、到货日期和状态
							  SubscribeDetail subsDetail  = purDetail.getSubscribeDetail();
							  if(null !=subsDetail && null!=subsDetail.getId()){
								  //如果入库数量(到货数量)等于采购数量置 申购单明细 为 已入库
								  if(null != subsDetail.getArrivalAmount()){
									  subsDetail.setArrivalAmount(subsDetail.getArrivalAmount()+(int)amountNumber);
								  }else{
									  subsDetail.setArrivalAmount((int)amountNumber);
								  }
								  subsDetail.setArrivalDate(detail.getSpareInBill().getInDate());
								  if(amountNumber==purDetail.getAmount()){
								    subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
								     
								  } 
								  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
									  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
										  subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
									  }
								  }
								  this.subscribeDao.storeSubscribeDetail(subsDetail);
								  //同步更新 申购单状态
								  this.updateSubscribeStatus(subsDetail.getSubscribe());
								  //同步更新 汇总单
								  this.updateSubscribeCollecStatus(subsDetail.getSubscribeCollectBill());
							  } 
							
							  purchaseBillDetailDao.storePurchaseBillDetail(purDetail);
							  //同步更新 采购单 的状态
							  this.updatePurchaseBillStatus(purDetail.getPurchaseBill());
							}
						} 
						
						break;
					}
				}
			}
			
			//保存入库单价
			Double amountUnitprice =0.00;
			if(dtlTaxPriceValue!= null){
				for(int i=0;i<dtlTaxPriceValue.length;i=i+2){
					if(dtlTaxPriceValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountUnitprice = Double.valueOf(dtlTaxPriceValue[i+1]);
						spare.setUnitPrice(amountUnitprice);
						
						//detail.setUnitPrice(amountUnitprice);
						//税前价格
						detail.setTaxPrice(amountUnitprice);
						 break;
					}
				}
			}
			
			//计算总金额
			detail.setTotalPrice(amountNumber*(detail.getTaxPrice()));
			//保存备注
			if(dtlCommentArr!=null){
				for(int i=0;i<dtlCommentArr.length;i=i+2){
					if(dtlCommentArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						detail.setComment(dtlCommentArr[i+1]);
						break;
					}else{
						detail.setComment(null);
					}
				}
			}else{
				detail.setComment(null);
			}

			
		//入库明细保存库位号之后,根据spare.id,department.id,location.id判断此备件,部门,库位在t_spare_location中是否存在,如果存在则更新
		//t_spare_location中的总库存,
	    this.spareLocationManager.storeOrUpdateSpareLocationByInBillDetail(detail);
	    //更新备件库总台帐
	    this.spareWareHouseManager.storeOrUpdateSpareWareHouse(detail.getSpare(),detail.getSpareInBill().getWarehouse(), detail.getNumber());
	    detail.setStocksAfterInOrOut(detail.getStocksBeforeInOrOut()+detail.getNumber());   //设置入库单明细入库后库存
	    
	    detail.setInstatus(SpareInBillDetailStatus.INWAREHOUSED);
 	    detail.setStatus(SpareInBillDetailStatus.INWAREHOUSED.toString());
	    //获得出库单明细
 	    //SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
 	 
 	    
 	    
		this.spareInBillDetailDao.storeSpareInBillDtl(detail);
		
		//TODO 放在while循环之外     入库单明细保存之后  改变入库单的状态
		this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
 
		//改变入库单的提醒状态
		detail.getSpareInBill().setSubmit(true);
		spareInBillManager.storeSpareInBill(detail.getSpareInBill());
		spareInBillDetailNum++;
		
		}
		//更新入库单总金额
		updateSpareInBill_totalPrice(detail.getSpareInBill());
	}
	
	
	
	/**
	 * 更新入库明细的信息
	 * @param dtlIdArr 入库单明细ID数组
	 * @param dtlNumberArr 入库单明细ID和入库数量数组
	 * @param dtlCommentArr 入库单明细ID和备注数组
	 * @param dtlDeparementArr 入库单明细ID和部门数组
	 * @param dtlLocationCodeValue 入库单明细ID和库位号数组
	 * @param dtlUnitPriceArr 入库单明细ID和入库单价数组
	 */
	private void updateOldSpareInBillDetail(long roleWarehouseId,String[] dtlIdArr, String[] dtlNumberArr, String[] dtlCommentArr,
											String[] dtlDeparementArr,String[] dtlLocationCodeValue,String[] dtlTaxPriceValue,String[] allDisableSpareArr) {

		SpareInBillDetail detail = new SpareInBillDetail();
		//入库明细循环次数
		int spareInBillDetailNum = 0;
		while(dtlIdArr != null && spareInBillDetailNum<dtlIdArr.length){
			//根据入库单明细ID获取入库单对象
			detail = spareInBillDetailDao.loadSpareInBillDtl(Long.valueOf(dtlIdArr[spareInBillDetailNum]));
		 
			//入库单关联的备件
			Spare spare = detail.getSpare();
			Department dept =null;
			Location location = null;
			if (detail.getInstatus().toString().equals(SpareInBillDetailStatus.INWAREHOUSED.toString())) {
				spareInBillDetailNum++;
				continue;
			}
			
			//获得相应的部门，并set进入detail中，使其成为游离状态，等待保存为持久态
			if (null != dtlDeparementArr) {
				for (int i=0; i<dtlDeparementArr.length; i=i+2) {              //设置计划明细中的责任单位
					if (dtlDeparementArr[i].equals(dtlIdArr[spareInBillDetailNum])) {
						dept = this.departmentManager.loadDepartment(Long.valueOf(dtlDeparementArr[i+1]));
						detail.setDepartment(dept);
						detail.setDeptName(dept.getName());
						
					    break;
					} else {
						detail.setDepartment(null);
					}
				}
			} else {
				detail.setDepartment(null);
			}
			
			//获得库位号，并同样set进入detail，同时进入库位管理中将该库位号更改为已用状态
			if(dtlLocationCodeValue!=null){
				for(int i=0;i<dtlLocationCodeValue.length;i=i+2){
					if(dtlLocationCodeValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						if ((i+1) < dtlLocationCodeValue.length) {
//							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1]);
//							long locationId = Long.parseLong(dtlLocationCodeValue[i+1]);
//							location=locationDao.loadLocation(locationId);
							//location=locationDao.findLocationByWarehouse(dtlLocationCodeValue[i+1], roleWarehouseId);
							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1], roleWarehouseId);
							//System.out.println("=======" + location.getId());
							if (null != location) {
								detail.setLocationCode(location.getCode());
								detail.setLocation(location);
								detail.setRegional(location.getRegional());
								detail.setWarehouse(location.getWarehouse());
								//将库位的状态置为“已用”
								updateLocationStatusToUsed(location);
							}
							else {
								detail.setLocationCode(null);
								detail.setLocation(null);
							}
						} else {
							detail.setLocationCode(null);
							detail.setLocation(null);
						}
						break;
					}else{
						detail.setLocationCode(null);
						detail.setLocation(null);
					}
				}
			}else{
				detail.setLocationCode(null);
				detail.setLocation(null);
			}			
			//保存入库数量
			long amountNumber = 0;
			if(dtlNumberArr!= null){
				for(int i=0;i<dtlNumberArr.length;i=i+2){
					if(dtlNumberArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountNumber = Long.valueOf(dtlNumberArr[i+1]);
						//将入库数量设置为页面传过来的入库数量
						detail.setNumber(amountNumber);
						
						//如果是二级库入库单
//						if (detail.getWarehouse().getStorageGrade().getId()==209||(detail.getWarehouse().getStorageGrade().getId()==208&&detail.getSpareOutBillDtl()!=null)){
//							SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
//							spareOutBillDetail.setInNumber(spareOutBillDetail.getInNumber().longValue()+amountNumber);
//							SpareLocation spareLocation = spareOutBillDetail.getSpareLocation();
//							spareLocation.setLockedStocks(spareLocation.getLockedStocks()-amountNumber);//减去源仓库的锁定库存
//							spareLocation.setStocks(spareLocation.getStocks()-amountNumber);    //减去源仓库的库存
//							Long allStocks = this.spareLocationDao.getSumBySpare(spareOutBillDetail.getSpareLocation().getSpare().getId());
//							Spare spare1 = spareOutBillDetail.getSpare();
							//更新备件总库存
//							spare.setStocks(allStocks);
//							this.spareOutBillDtlDao.storeSpareOutBillDetail(spareOutBillDetail);
							//更新出库单已入金额
//							Double totalPrice = NumberUtils.DOUBLE_ZERO;
//							SpareOutBill spareOutBill = spareOutBillDetail.getSpareOutBill();
//							for(SpareOutBillDetail detail1 : spareOutBill.getDetail()){
//								if(detail1.getInNumber()!=null){
//									totalPrice = totalPrice + detail1.getInNumber()*detail1.getUnitPrice();
//									//System.out.println("setInTotalPrice111=" + detail1.getUnitPrice() + "N2222:"+detail1.getInNumber());
//								}
//							}
							//System.out.println("setInTotalPrice=" + totalPrice);
//							spareOutBill.setInTotalPrice(totalPrice);
//							this.spareOutBillDao.storeSpareOutBill(spareOutBill);
							
//							this.spareDao.storeSpare(spare1);
//							this.spareLocationDao.storeSpareLocation(spareLocation);
//							//更新备件库总台帐库存
//							this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
							
							
//						}
						PurchaseBillDetail purDetail = detail.getPoDetail();
						
						//保存到采购单明细的到货数量、到货日期
						if(null != purDetail){
							if(purDetail.getId()!=null){
							  purDetail.setArrivalAmount(purDetail.getArrivalAmount()+(int)amountNumber);
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //如果入库数量 大于等于采购数量 置采购单明细为 已入库
							  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
								  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
									  purDetail.setStatus(PurchaseBillDtlStatus.INSPECTED);
								  }else{
										 purDetail.setStatus(PurchaseBillDtlStatus.INSPECTING);
								 }
							  }
							  //同步更新 采购单的 实际到货日期
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //同步更新 申购单明细的到货数量、到货日期和状态
							  SubscribeDetail subsDetail  = purDetail.getSubscribeDetail();
							  if(null !=subsDetail && null!=subsDetail.getId()){
								  //如果入库数量(到货数量)等于采购数量置 申购单明细 为 已入库
								  if(null != subsDetail.getArrivalAmount()){
									  subsDetail.setArrivalAmount(subsDetail.getArrivalAmount()+(int)amountNumber);
								  }else{
									  subsDetail.setArrivalAmount((int)amountNumber);
								  }
								  subsDetail.setArrivalDate(detail.getSpareInBill().getInDate());
								  if(amountNumber==purDetail.getAmount()){
								    subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
								     
								  } 
								  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
									  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
										  subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
									  }
								  }
								  this.subscribeDao.storeSubscribeDetail(subsDetail);
								  //同步更新 申购单状态
								  this.updateSubscribeStatus(subsDetail.getSubscribe());
								  //同步更新 汇总单
								  this.updateSubscribeCollecStatus(subsDetail.getSubscribeCollectBill());
							  } 
							
							  purchaseBillDetailDao.storePurchaseBillDetail(purDetail);
							  //同步更新 采购单 的状态
							  this.updatePurchaseBillStatus(purDetail.getPurchaseBill());
							}
						} 
						
						break;
					}
				}
			}
			
			//保存入库单价
			Double amountUnitprice =0.00;
			if(dtlTaxPriceValue!= null){
				for(int i=0;i<dtlTaxPriceValue.length;i=i+2){
					if(dtlTaxPriceValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountUnitprice = Double.valueOf(dtlTaxPriceValue[i+1]);
						spare.setUnitPrice(amountUnitprice);
						
						//detail.setUnitPrice(amountUnitprice);
						//税前价格
						detail.setTaxPrice(amountUnitprice);
						 break;
					}
				}
			}
			
			//计算总金额
			detail.setTotalPrice(amountNumber*(detail.getTaxPrice()));
			//保存备注
			if(dtlCommentArr!=null){
				for(int i=0;i<dtlCommentArr.length;i=i+2){
					if(dtlCommentArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						detail.setComment(dtlCommentArr[i+1]);
						break;
					}else{
						detail.setComment(null);
					}
				}
			}else{
				detail.setComment(null);
			}
			
			
			//是否可用
			if(allDisableSpareArr!=null){
				for(int i=0;i<allDisableSpareArr.length;i=i+2){
					if(allDisableSpareArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						detail.setDisableSpare(allDisableSpareArr[i+1]);
						break;
					}else{
						detail.setDisableSpare(null);
					}
				}
			}else{
				detail.setDisableSpare(null);
			}
			

			
		//入库明细保存库位号之后,根据spare.id,department.id,location.id判断此备件,部门,库位在t_spare_location中是否存在,如果存在则更新
		//t_spare_location中的总库存,
			Location l = new Location();
			Warehouse warehouse= new Warehouse();
			warehouse.setId(roleWarehouseId);
			Regional regional = new Regional();
//			regional.setId(1687l);
//			l.setId(51577l);
			regional.setId(1820l);
			l.setId(58374l);
			l.setWarehouse(warehouse);
			l.setRegional(regional);
			detail.setLocation(l);
	    this.spareLocationManager.storeOrUpdateSpareLocationByInBillDetailHaveDisabe(detail);
	    //更新备件库总台帐
	    this.spareWareHouseManager.storeOrUpdateOldSpareWareHouseHaveDisabe(detail.getDisableSpare(),detail.getSpare(),detail.getSpareInBill().getWarehouse(), detail.getNumber());
	    detail.setStocksAfterInOrOut(detail.getStocksBeforeInOrOut()+detail.getNumber());   //设置入库单明细入库后库存
	    
	    detail.setInstatus(SpareInBillDetailStatus.INWAREHOUSED);
 	    detail.setStatus(SpareInBillDetailStatus.INWAREHOUSED.toString());
	    //获得出库单明细
 	    //SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
 	 
 	    
 	    
		this.spareInBillDetailDao.storeSpareInBillDtl(detail);
		
		//TODO 放在while循环之外     入库单明细保存之后  改变入库单的状态
		this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
 
		//改变入库单的提醒状态
		detail.getSpareInBill().setSubmit(true);
		spareInBillManager.storeSpareInBill(detail.getSpareInBill());
		spareInBillDetailNum++;
		
		}
		//更新入库单总金额
		updateSpareInBill_totalPrice(detail.getSpareInBill());
	}


	/**
	 * 更新入库明细的信息
	 * @param dtlIdArr 入库单明细ID数组
	 * @param dtlNumberArr 入库单明细ID和入库数量数组
	 * @param dtlCommentArr 入库单明细ID和备注数组
	 * @param dtlDeparementArr 入库单明细ID和部门数组
	 * @param dtlLocationCodeValue 入库单明细ID和库位号数组
	 * @param dtlUnitPriceArr 入库单明细ID和入库单价数组
	 */
	private void updateOldSpareInBillDetailForOldToNew(long roleWarehouseId,String[] dtlIdArr,  String[] dtlNameArr, String[] dtlModelArr,String[] dtlNumberArr, String[] dtlCommentArr,
											String[] dtlDeparementArr,String[] dtlLocationCodeValue,String[] dtlTaxPriceValue,String[] allDisableSpareArr) {

		SpareInBillDetail detail = new SpareInBillDetail();
		//入库明细循环次数
		int spareInBillDetailNum = 0;
		while(dtlIdArr != null && spareInBillDetailNum<dtlIdArr.length){
			//根据入库单明细ID获取入库单对象
			detail = spareInBillDetailDao.loadSpareInBillDtl(Long.valueOf(dtlIdArr[spareInBillDetailNum]));
		 
			//入库单关联的备件
			Spare spare = detail.getSpare();
			Department dept =null;
			Location location = null;
			if (detail.getInstatus().toString().equals(SpareInBillDetailStatus.INWAREHOUSED.toString())) {
				spareInBillDetailNum++;
				continue;
			}
			if (null != dtlNameArr&&null!=dtlModelArr) {
				for (int i=0; i<dtlNameArr.length; i=i+2) {              

//设置计划明细中的责任单位
					if (dtlNameArr[i].equals(dtlIdArr[spareInBillDetailNum])&&dtlModelArr[i].equals(dtlIdArr[spareInBillDetailNum])) {
						Spare jjspare = this.spareDao.loadByNameAndModel(dtlNameArr[i+1], dtlModelArr[i+1]);
						if(jjspare!=null){
							detail.setCode(jjspare.getSpareNo());
							detail.setName(jjspare.getName());
							detail.setSpare(jjspare);
						}else{
							Spare fistspare = spareDao.loadByFirst();
							Spare newSpare = new Spare();
							newSpare.setToolingDevFlag(fistspare.getToolingDevFlag());
							newSpare.setModelSpecs(dtlModelArr[i+1]);
							newSpare.setPropertyType(fistspare.getPropertyType());
							newSpare.setProducingAreaType(fistspare.getProducingAreaType());
							newSpare.setDisabled(false);
							newSpare.setTenderPartFlag(true);
							newSpare.setWearingPartFlag(true);
							newSpare.setHeavyRepairPartFlag(true);
							SpareType st= new SpareType();
							st.setId(32l);
							newSpare.setCategory(st);
							newSpare.setSpareDetailType(fistspare.getSpareDetailType());
							newSpare.setUnit(fistspare.getUnit());
							newSpare.setSupplier(fistspare.getSupplier());
							newSpare.setFactory(fistspare.getFactory());
							newSpare.setSafeStock(Long.valueOf(0));
							newSpare.setStocks(Long.valueOf(0));
							newSpare.setOldSpare("0");
							newSpare.setName(dtlNameArr[i+1]);
							String spareNo =generateSpareNo(spare); // 生成备件编码
							newSpare.setSpareNo(spareNo);
							this.spareDao.storeSpare(newSpare);
							detail.setCode(newSpare.getSpareNo());
							detail.setName(newSpare.getName());
							detail.setSpare(newSpare);
						}
						
					  break;
					} 
				}
			}
			
			
			
			//获得相应的部门，并set进入detail中，使其成为游离状态，等待保存为持久态
			if (null != dtlDeparementArr) {
				for (int i=0; i<dtlDeparementArr.length; i=i+2) {              //设置计划明细中的责任单位
					if (dtlDeparementArr[i].equals(dtlIdArr[spareInBillDetailNum])) {
						dept = this.departmentManager.loadDepartment(Long.valueOf(dtlDeparementArr[i+1]));
						detail.setDepartment(dept);
						detail.setDeptName(dept.getName());
						
					    break;
					} else {
						detail.setDepartment(null);
					}
				}
			} else {
				detail.setDepartment(null);
			}
			
			//获得库位号，并同样set进入detail，同时进入库位管理中将该库位号更改为已用状态
			if(dtlLocationCodeValue!=null){
				for(int i=0;i<dtlLocationCodeValue.length;i=i+2){
					if(dtlLocationCodeValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						if ((i+1) < dtlLocationCodeValue.length) {
//							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1]);
//							long locationId = Long.parseLong(dtlLocationCodeValue[i+1]);
//							location=locationDao.loadLocation(locationId);
							//location=locationDao.findLocationByWarehouse(dtlLocationCodeValue[i+1], roleWarehouseId);
							location=locationDao.getLocationByCodeOnlyValid(dtlLocationCodeValue[i+1], roleWarehouseId);
							//System.out.println("=======" + location.getId());
							if (null != location) {
								detail.setLocationCode(location.getCode());
								detail.setLocation(location);
								detail.setRegional(location.getRegional());
								detail.setWarehouse(location.getWarehouse());
								//将库位的状态置为“已用”
								updateLocationStatusToUsed(location);
							}
							else {
								detail.setLocationCode(null);
								detail.setLocation(null);
							}
						} else {
							detail.setLocationCode(null);
							detail.setLocation(null);
						}
						break;
					}else{
						detail.setLocationCode(null);
						detail.setLocation(null);
					}
				}
			}else{
				detail.setLocationCode(null);
				detail.setLocation(null);
			}			
			//保存入库数量
			long amountNumber = 0;
			if(dtlNumberArr!= null){
				for(int i=0;i<dtlNumberArr.length;i=i+2){
					if(dtlNumberArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountNumber = Long.valueOf(dtlNumberArr[i+1]);
						//将入库数量设置为页面传过来的入库数量
						detail.setNumber(amountNumber);
						
						//如果是二级库入库单
//						if (detail.getWarehouse().getStorageGrade().getId()==209||(detail.getWarehouse().getStorageGrade().getId()==208&&detail.getSpareOutBillDtl()!=null)){
//							SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
//							spareOutBillDetail.setInNumber(spareOutBillDetail.getInNumber().longValue()+amountNumber);
//							SpareLocation spareLocation = spareOutBillDetail.getSpareLocation();
//							spareLocation.setLockedStocks(spareLocation.getLockedStocks()-amountNumber);//减去源仓库的锁定库存
//							spareLocation.setStocks(spareLocation.getStocks()-amountNumber);    //减去源仓库的库存
//							Long allStocks = this.spareLocationDao.getSumBySpare(spareOutBillDetail.getSpareLocation().getSpare().getId());
//							Spare spare1 = spareOutBillDetail.getSpare();
							//更新备件总库存
//							spare.setStocks(allStocks);
//							this.spareOutBillDtlDao.storeSpareOutBillDetail(spareOutBillDetail);
							//更新出库单已入金额
//							Double totalPrice = NumberUtils.DOUBLE_ZERO;
//							SpareOutBill spareOutBill = spareOutBillDetail.getSpareOutBill();
//							for(SpareOutBillDetail detail1 : spareOutBill.getDetail()){
//								if(detail1.getInNumber()!=null){
//									totalPrice = totalPrice + detail1.getInNumber()*detail1.getUnitPrice();
//									//System.out.println("setInTotalPrice111=" + detail1.getUnitPrice() + "N2222:"+detail1.getInNumber());
//								}
//							}
							//System.out.println("setInTotalPrice=" + totalPrice);
//							spareOutBill.setInTotalPrice(totalPrice);
//							this.spareOutBillDao.storeSpareOutBill(spareOutBill);
							
//							this.spareDao.storeSpare(spare1);
//							this.spareLocationDao.storeSpareLocation(spareLocation);
//							//更新备件库总台帐库存
//							this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareLocation.getSpare(), spareLocation.getWarehouse(), spareOutBillDetail.getNumber());
							
							
//						}
						PurchaseBillDetail purDetail = detail.getPoDetail();
						
						//保存到采购单明细的到货数量、到货日期
						if(null != purDetail){
							if(purDetail.getId()!=null){
							  purDetail.setArrivalAmount(purDetail.getArrivalAmount()+(int)amountNumber);
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //如果入库数量 大于等于采购数量 置采购单明细为 已入库
							  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
								  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
									  purDetail.setStatus(PurchaseBillDtlStatus.INSPECTED);
								  }else{
										 purDetail.setStatus(PurchaseBillDtlStatus.INSPECTING);
								 }
							  }
							  //同步更新 采购单的 实际到货日期
							  purDetail.setActualDeliveryDate(detail.getSpareInBill().getInDate());
							  
							  //同步更新 申购单明细的到货数量、到货日期和状态
							  SubscribeDetail subsDetail  = purDetail.getSubscribeDetail();
							  if(null !=subsDetail && null!=subsDetail.getId()){
								  //如果入库数量(到货数量)等于采购数量置 申购单明细 为 已入库
								  if(null != subsDetail.getArrivalAmount()){
									  subsDetail.setArrivalAmount(subsDetail.getArrivalAmount()+(int)amountNumber);
								  }else{
									  subsDetail.setArrivalAmount((int)amountNumber);
								  }
								  subsDetail.setArrivalDate(detail.getSpareInBill().getInDate());
								  if(amountNumber==purDetail.getAmount()){
								    subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
								     
								  } 
								  if(null !=purDetail.getAmount() && null !=purDetail.getArrivalAmount()){
									  if(purDetail.getAmount().intValue()<=purDetail.getArrivalAmount().intValue()){
										  subsDetail.setStatus(SubscribeDetailStatus.INSPECTED);
									  }
								  }
								  this.subscribeDao.storeSubscribeDetail(subsDetail);
								  //同步更新 申购单状态
								  this.updateSubscribeStatus(subsDetail.getSubscribe());
								  //同步更新 汇总单
								  this.updateSubscribeCollecStatus(subsDetail.getSubscribeCollectBill());
							  } 
							
							  purchaseBillDetailDao.storePurchaseBillDetail(purDetail);
							  //同步更新 采购单 的状态
							  this.updatePurchaseBillStatus(purDetail.getPurchaseBill());
							}
						} 
						
						break;
					}
				}
			}
			
			//保存入库单价
			Double amountUnitprice =0.00;
			if(dtlTaxPriceValue!= null){
				for(int i=0;i<dtlTaxPriceValue.length;i=i+2){
					if(dtlTaxPriceValue[i].equals(dtlIdArr[spareInBillDetailNum])){
						amountUnitprice = Double.valueOf(dtlTaxPriceValue[i+1]);
						spare.setUnitPrice(amountUnitprice);
						
						//detail.setUnitPrice(amountUnitprice);
						//税前价格
						detail.setTaxPrice(amountUnitprice);
						 break;
					}
				}
			}
			
			//计算总金额
			detail.setTotalPrice(amountNumber*(detail.getTaxPrice()));
			//保存备注
			if(dtlCommentArr!=null){
				for(int i=0;i<dtlCommentArr.length;i=i+2){
					if(dtlCommentArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						detail.setComment(dtlCommentArr[i+1]);
						break;
					}else{
						detail.setComment(null);
					}
				}
			}else{
				detail.setComment(null);
			}
			//是否可用
			if(allDisableSpareArr!=null){
				for(int i=0;i<allDisableSpareArr.length;i=i+2){
					if(allDisableSpareArr[i].equals(dtlIdArr[spareInBillDetailNum])){
						detail.setDisableSpare(allDisableSpareArr[i+1]);
						break;
					}else{
						detail.setDisableSpare(null);
					}
				}
			}else{
				detail.setDisableSpare(null);
			}
			

			
		//入库明细保存库位号之后,根据spare.id,department.id,location.id判断此备件,部门,库位在t_spare_location中是否存在,如果存在则更新
		//t_spare_location中的总库存,
			Location l = new Location();
			Warehouse warehouse= new Warehouse();
			warehouse.setId(roleWarehouseId);
			Regional regional = new Regional();
//			regional.setId(1687l);
//			l.setId(51577l);
			regional.setId(1820l);
			l.setId(58374l);
			l.setWarehouse(warehouse);
			l.setRegional(regional);
			detail.setLocation(l);
	    this.spareLocationManager.storeOrUpdateSpareLocationByInBillDetailHaveDisabe(detail);
	    //更新备件库总台帐
//	    this.spareWareHouseManager.storeOrUpdateOldSpareWareHouse(detail.getSpare(),detail.getSpareInBill().getWarehouse(), detail.getNumber());
	    this.spareWareHouseManager.storeOrUpdateOldSpareWareHouseHaveDisabe(detail.getDisableSpare(),detail.getSpare(),detail.getSpareInBill().getWarehouse(), detail.getNumber());
	    detail.setStocksAfterInOrOut(detail.getStocksBeforeInOrOut()+detail.getNumber());   //设置入库单明细入库后库存
	    
	    detail.setInstatus(SpareInBillDetailStatus.INWAREHOUSED);
 	    detail.setStatus(SpareInBillDetailStatus.INWAREHOUSED.toString());
	    //获得出库单明细
 	    //SpareOutBillDetail spareOutBillDetail = detail.getSpareOutBillDtl();
 	 
 	    
 	    
		this.spareInBillDetailDao.storeSpareInBillDtl(detail);
		
		//TODO 放在while循环之外     入库单明细保存之后  改变入库单的状态
		this.spareInBillManager.accordingSpareInBillDetailStatusUpdateSpareInStatus(detail.getSpareInBill());
 
		//改变入库单的提醒状态
		detail.getSpareInBill().setSubmit(true);
		spareInBillManager.storeSpareInBill(detail.getSpareInBill());
		spareInBillDetailNum++;
		
		}
		//更新入库单总金额
		updateSpareInBill_totalPrice(detail.getSpareInBill());
	}
	
	/**
	 * 修改库位状态 未用|已用
	 */
	public void updateLocationStatusToUsed(Location location){
		location.setStatus(LocationStatus.USED);
	}
	/**
	 * 更新采购单的状态
	 * @param purchaseBill 采购单
	 */
	public void updatePurchaseBillStatus(PurchaseBill purchaseBill){
		//新建的数量
		int newNum=0;
		//入库中的数量
//		int inspectingNum =0;
		//已入库的数量
		int inspectedNum =0;
		Set<PurchaseBillDetail> set = purchaseBill.getPurchaseBillDetails();
		if(null != set && set.size()>0){
			int size = set.size();
			for(PurchaseBillDetail detail :set ){
				if(PurchaseBillDtlStatus.NEW.equals(detail.getStatus())){
					newNum++;
				}
//				if(PurchaseBillDtlStatus.INSPECTING.equals(detail.getStatus())){
//					inspectingNum++;
//				}
				if(PurchaseBillDtlStatus.INSPECTED.equals(detail.getStatus())){
					inspectedNum++;
				}
			}
			if(size == newNum){
				purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
			}else if(size ==inspectedNum ){
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTED);
			}else{
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTING);
			}
			
		}
		this.purchaseBillDao.storePurchaseBill(purchaseBill);
		
	}
	
	/**
	 * 更新 申购单 状态
	 * @param subscribe 申购单
	 */
	public void updateSubscribeStatus(Subscribe subscribe){
		int inspectingNum=0;//入库中
		int inspectedNum =0;//已入库
		int purchasedNum=0;//已采购
		
		
		Set<SubscribeDetail> set = subscribe.getSubscribeDetails();
		if(null != set && set.size()>0){
			int size = set.size();
			for(SubscribeDetail detail : set){
				if(SubscribeDetailStatus.INSPECTING.equals(detail.getStatus())){
					inspectingNum++;
				}
				if(SubscribeDetailStatus.INSPECTED.equals(detail.getStatus())){
					inspectedNum++;
				}
				if(SubscribeDetailStatus.PURCHASEED.equals(detail.getStatus())){
					purchasedNum++;
				}
				 
			}
			
			if(size == inspectingNum){
				subscribe.setStatus(SubscribeStatus.STORAGING);
			}else if(size==inspectedNum){
				subscribe.setStatus(SubscribeStatus.STORAGED);
			}else if(size == purchasedNum){
				subscribe.setStatus(SubscribeStatus.PURCHASEED);
			}else if(inspectingNum>0){
				subscribe.setStatus(SubscribeStatus.STORAGING);
			}else if(0==inspectedNum && 0==inspectingNum && purchasedNum>0){
				subscribe.setStatus(SubscribeStatus.PURCHASING);
			}
			 
		}
		this.subscribeDao.storeSubscribe(subscribe);
	}

	//保存入库单明细之后更新t_spare_location的库存
	public void updateSpareLocationInformation(Long departId,Long locationId,Long spareId,SpareInBillDetail detail){
	    Location location=locationDao.loadLocation(locationId);
	    Long warehouseId=location.getWarehouse().getId();
	    Long regionalId=location.getRegional().getId();
		Integer num =this.spareLocationDao.getNumByDeptAndLocationAndSpare(departId,locationId,spareId);
		//如果根据部门id,备件id,和库位id在t_spare_location表中获得的记录条数大于0，则说明在入库单明细中新增的备件在t_spare_location
		//表中存在,此时获取此备件的总库存,累加到t_spare_location表中相同备件的中库存
		if(num>0){
			SpareInBillDetail dtl=spareInBillDetailDao.getTop1SpareInDetailByDeptAndLocationAndSpare(departId,locationId,spareId);
			
			SpareLocation spareLocation=spareLocationDao.getTop1SpareLocationByDeptAndLocationAndSpare(departId,locationId,spareId,false);
			Long total=(dtl.getNumber())+(spareLocation.getStocks());
			spareLocation.setStocks(total);
			spareLocationDao.storeSpareLocation(spareLocation);
			//更新spareLocation的总库存之后,同时要更新t_spare
			Long spareid=spareLocation.getSpare().getId();
			updateSpareLocationStocks(spareid);
		}else{
			storeSpareLocationByInBillDetail(detail);
		}
	}
	
	private void storeSpareLocationByInBillDetail(SpareInBillDetail detail) {
		Location location=detail.getLocation();
		Warehouse warehouse=location.getWarehouse();
		Regional regional=location.getRegional();
		SpareLocation spareLocation=new SpareLocation();
		spareLocation.setSpare(detail.getSpare());      //获取备件
		spareLocation.setUnitPrice(detail.getUnitPrice());  //获取单价
		spareLocation.setStocks(detail.getNumber());         //获取库存
		spareLocation.setDepartment(detail.getDepartment()); //获取部门
		spareLocation.setLocation(detail.getLocation());     //获取库位
		spareLocation.setWarehouse(warehouse);				//插入仓库
		spareLocation.setRegional(regional);				//插入库区
		spareLocation.setLocationCode(detail.getLocationCode()); //获取库位号
		spareLocation.setPreStocks(detail.getNumber());           //获取期初库存
		spareLocationDao.storeSpareLocation(spareLocation);
		//更新t_spare的总库存
		updateSpareLocationStocks(spareLocation.getSpare().getId());
	}
    //更新t_spare的总库存
	public void updateSpareLocationStocks(Long spareId){
		Long sum=spareLocationDao.getSumBySpare(spareId);
		Spare spare=spareDao.loasSpare(spareId);
		spare.setStocks(sum);
		spareDao.storeSpare(spare);
	}

	//根据入库单明细的金额来更新入库单的总金额
	private void updateSpareInBill_totalPrice(SpareInBill spareInBill) {
		Double totalPrice = NumberUtils.DOUBLE_ZERO;
		for(SpareInBillDetail detail : spareInBill.getDetail()){
			if(detail.getTotalPrice()!=null){
				totalPrice += detail.getTotalPrice();
			}
		}
		spareInBill.setTotalPrice(totalPrice);
		this.spareInBillManager.storeSpareInBill(spareInBill);
	}
	public void storeOutBillDtlToInBillDtl(SpareInBill spareInBill, String addSpareOutBillIds) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 根据入库明细ID集合获取对应的采购明细ID集合
	 * @param SpareInBillDtlIds
	 */
	public String updPurchaseBillIds(String SpareInBillDtlIds){
		return this.spareInBillDetailDao.updPurchaseBillIds(SpareInBillDtlIds);
	}
	
	/**
	 * 根据传入的入库单明细ID集合，更新申购单、汇总单、采购单的入库项
	 * 
	 * @param SpareInBillDtlIds 入库单明细ID集合
	 * @return
	 */
	public void updStatus(String SpareInBillDtlIds) {
		this.spareInBillDetailDao.updStatus(SpareInBillDtlIds);
	}
	/**
	 * 生成备件编码：按照规则生成，例如：SBD00-00021 SBD：表示备件类别 00：表示型号类别，即明细分类
	 * 00021：顺序码，从00001~99999顺序增加
	 * 
	 * @param spare
	 * @return String 备件编码
	 */
	private String generateSpareNo(Spare spare) {
		String categoryCode = "", // 类别代码
		detailCategoryCode = ""; // 明细类别代码
		if (null != spare.getCategory()) {
			categoryCode = "JJ";//spare.getCategory().getCode();
		}
//		if (null != spare.getSpareDetailType()) {
//			detailCategoryCode = spare.getSpareDetailType().getCode();
//		}
		/*
		 * 如果已存在备件编码的备件，更改了备件类别或分细分类，则修改备件编码：
		 * 1）如果新备件类别、新明细分类加上老的顺序码在备件中不存在，就使用改编码
		 * 2）如果新备件类别、新明细分类加上老的顺序码在备件中已存在，则重新生成
		 */
		if (null != spare.getSpareNo()) {
			String[] spareNoBySeparated = spare.getSpareNo().split("-");
			//if (spareNoBySeparated[0].equals(categoryCode + detailCategoryCode)) {
			if (spareNoBySeparated[0].equals(categoryCode)) {
				return spare.getSpareNo();
			} 
			
//			else {
//				//spareNoBySeparated[0] = categoryCode + detailCategoryCode;
//				spareNoBySeparated[0] = categoryCode;
//				String newSpareNo = (new StringBuffer()).append(
//						spareNoBySeparated[0]).append("-").append(
//						spareNoBySeparated[1]).toString();
//				if (null == this.spareDao.getSpareNumBySpareNo(newSpareNo)) {
//					return newSpareNo;
//				}
//
//			}
		}
//		String maxSpareNo = this.spareDao.getMaxSpareNoBySpareCode(categoryCode
//				+ detailCategoryCode + "-_____");
		String maxSpareNo = this.spareDao.getMaxSpareNoBySpareCode(categoryCode
		 + "-______");
		return parseAndCalculateSpareNo(categoryCode, detailCategoryCode,
				maxSpareNo);

	}

	/**
	 * 生产备件编码
	 * 
	 * @param categoryCode
	 *            类别代码
	 * @param detailCategoryCode
	 *            明细类别代码
	 * @param maxSpareNo
	 *            最大备件代码
	 * @return String 最大备件编码
	 */
	private String parseAndCalculateSpareNo(String categoryCode,
			String detailCategoryCode, String maxSpareNo) {
		String pattern = "000000";
		Format formatter = NumberFormat.getIntegerInstance();
		formatter = new DecimalFormat(pattern);
		if (null == maxSpareNo) {
			Long n = NumberUtils.createLong(pattern) + 1L;
//			return (new StringBuffer()).append(categoryCode).append(
//					detailCategoryCode).append("-").append(formatter.format(n))
//					.toString();
			return (new StringBuffer()).append(categoryCode).append("-").append(formatter.format(n))
					.toString();
		}
		logger.debug("spare category code is : [" + categoryCode + "]");
		String spareNoSequenceCode = maxSpareNo.substring((maxSpareNo
				.indexOf("-") + 1));
		logger.debug("spare no sequence code is : [" + spareNoSequenceCode
				+ "]");
		Long n = NumberUtils.createLong(spareNoSequenceCode) + 1L;
//		return (new StringBuffer()).append(categoryCode).append(
//				detailCategoryCode).append("-").append(formatter.format(n))
//				.toString();
		return (new StringBuffer()).append(categoryCode).append("-").append(formatter.format(n))
				.toString();
	}
	
}


