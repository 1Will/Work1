package com.yongjun.tdms.service.asset.spare.Inventory.pojo;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.Inventory.SpareInventoryDao;
import com.yongjun.tdms.dao.asset.spare.Inventory.SpareInventoryDetailDao;
import com.yongjun.tdms.dao.asset.spare.inWareHouse.SpareInBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.location.LocationDao;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventory;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.service.asset.spare.SpareManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryDetailManager;
import com.yongjun.tdms.service.asset.spare.Inventory.SpareInventoryManager;
import com.yongjun.tdms.service.asset.spare.spareLocation.SpareLocationManager;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;

public class DefaultSpareInventoryDetailManager extends BaseManager implements
		SpareInventoryDetailManager {
	private final SpareInventoryDetailDao spareInventoryDetailDao;
	private final SpareDao  spareDao;
	private final SpareManager spareManger;
	private final SpareInBillDetailDao spareInBillDetailDao;
	private final SpareInventoryDao spareInventoryDao;
	private final SpareLocationDao  spareLocationDao;
	private final LocationDao locationDao;
	private final SpareLocationManager spareLocationManager;
	private final SpareWareHouseManager spareWareHouseManager;
	
	public DefaultSpareInventoryDetailManager(SpareInventoryDetailDao spareInventoryDetailDao,
			SpareDao  spareDao,SpareManager spareManger,SpareInBillDetailDao spareInBillDetailDao,SpareInventoryDao spareInventoryDao,
			SpareLocationDao  spareLocationDao,LocationDao locationDao,
			SpareLocationManager spareLocationManager,
			SpareWareHouseManager spareWareHouseManager){
		this.spareInventoryDetailDao=spareInventoryDetailDao;
		this.spareDao=spareDao;
		this.spareManger=spareManger;
		this.spareInBillDetailDao = spareInBillDetailDao;
		this.spareInventoryDao=spareInventoryDao;
		this.spareLocationDao=spareLocationDao;
		this.locationDao=locationDao;
		this.spareLocationManager=spareLocationManager;
		this.spareWareHouseManager = spareWareHouseManager;
	}
	public List<SpareInventoryDetail> loadAllSpareInventoryDetails(
			Long[] spareInventoryDetailIds){
		return spareInventoryDetailDao.loadAllSpareInventoryDetails(spareInventoryDetailIds);
	}

	public SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId){
		return spareInventoryDetailDao.loadSpareInventoryDetail(spareInventoryDetailId);
	}
    /**
     * 保存盘点单明细
     */
	public void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail){
		spareInventoryDetailDao.storeSpareInventoryDetail(spareInventoryDetail);
		//同步更新备件库以及备件
		this.updateSpareLocation(spareInventoryDetail);
		//更新备件库总台帐库存
		this.spareWareHouseManager.storeOrUpdateSpareWareHouse(spareInventoryDetail.getSpare(), 
				spareInventoryDetail.getWarehouse(), spareInventoryDetail.getActualNumber());
		
	}
    /**
     * 更新备件库台账
     * @param spareInventoryDetail
     */
	public void updateSpareLocation(SpareInventoryDetail spareInventoryDetail){
		SpareLocation spareLocation = spareInventoryDetail.getSpareLocation();
		//把备件库台账的总库存 跟新为 盘点数量
		spareLocation.setStocks(spareInventoryDetail.getInventoryNum());
		this.spareLocationDao.storeSpareLocation(spareLocation);
		this.updateSpareStocks(spareLocation);
	}
	/**
	 * 更新备件的库存
	 * @param spareLocation
	 */
	public void updateSpareStocks(SpareLocation spareLocation){
		spareLocation = this.spareLocationDao.loadSpareLocation(spareLocation.getId());
		this.spareDao.updateSpareStocks(spareLocation);
		
	}
	
	public void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail){
		spareInventoryDetailDao.deleteSpareInventoryDetail(spareInventoryDetail);
	}

	public void deleteAllSpareInventoryDetail(Collection<SpareInventoryDetail> spareInventoryDetails ,SpareInventory spareInventory){
		spareInventoryDetailDao.deleteAllSpareInventoryDetail(spareInventoryDetails);
		for(SpareInventoryDetail detail : spareInventoryDetails){
			spareInventory.getInventoryDetails().remove(detail);
		}
		//更改盘点单总金额
		this.updateSpareInventory_totalPrice(spareInventory);
		//盘点单明细删除之后改变此盘点单明细所关联的备件台帐明细的总库存
		updateSpareAccountDetailStocks(spareInventoryDetails);
		updateSpareAccountStocks(spareInventoryDetails);
		
	}
	public void updateSpareAccountDetailStocks(Collection<SpareInventoryDetail> spareInventoryDetails){
		for(SpareInventoryDetail spareDtl:spareInventoryDetails){
			if(spareDtl.getLocation()!=null){
				for(SpareLocation spareLocation:spareDtl.getLocation().getSpareLoc()){
					if(spareDtl.getActualNumber()!=null){
						spareLocation.setStocks(spareDtl.getActualNumber());
					}else{
						spareLocation.setStocks(Long.valueOf(0));
					}
					this.spareLocationDao.storeSpareLocation(spareLocation);
					spareDtl.getInventory().setSubmit(true);
					this.updateInventoryAfterSpareStocks(spareDtl.getSpare().getId());
				}
			}
			
		}
	}
	public void updateSpareAccountStocks(Collection<SpareInventoryDetail> spareInventoryDetails){
		for(SpareInventoryDetail spareDtl:spareInventoryDetails){
			Spare spare=this.spareManger.loasSpare(spareDtl.getSpare().getId());
			spare.setStocks(spareDtl.getActualNumber());
			this.spareDao.storeSpare(spare);
			spareDtl.getInventory().setSubmit(true);
			spareInventoryDao.storeSpareInventory(spareDtl.getInventory());
		}
	}
	//copy备件台帐到盘点单明细
	public void storeSpareInventoryBillDtlFromAccount(SpareInventory spareInventoryBill, String addSpareAccountIds) {
		String[] addSpareAccountId = null;
		if(null != addSpareAccountIds){
			addSpareAccountId = addSpareAccountIds.split(",");
		}
		for(int i=0;i<addSpareAccountId.length;i++){
			SpareInventoryDetail detail = new SpareInventoryDetail();

			SpareLocation spareLocation = spareLocationDao.loadSpareLocation(Long.valueOf(addSpareAccountId[i]));
			detail.setSpare(spareLocation.getSpare());
			if(spareLocation.getLocation()!=null){  
				detail.setLocation(spareLocation.getLocation()); //复制库位的id到入库单明细
				detail.setLocationCode(spareLocation.getLocation().getCode());//复制库位的code到入库单明细
			}
			if(spareLocation.getUnitPrice()!=null){        //复制单价到入库单明细
				detail.setUnitPrice(spareLocation.getUnitPrice());
			}else{
				detail.setUnitPrice(Double.valueOf(0.0));
			}
			if(spareLocation.getStocks()!=null&&spareLocation.getUnitPrice()!=null){
			      detail.setActualTotalPrice(spareLocation.getUnitPrice()*spareLocation.getStocks());	//保存盘点前金额
			}
			if(spareLocation.getStocks()!=null){               //复制当前库存数量
				detail.setCurrentSysStocks(spareLocation.getStocks());
			}else{
				detail.setCurrentSysStocks(null);
			}
			if(spareLocation.getStocks()!=null){               //复制实际数量
				detail.setActualNumber(spareLocation.getStocks());
			}else{
				detail.setActualNumber(null);
			}
		
			detail.setSpareLocation(spareLocation);
			detail.setInventory(spareInventoryBill);
			detail.getInventory().setSubmit(true);
        
			copySpareAccountToSpareInventoryBillDtl(spareLocation.getSpare(),detail);	
			this.spareInventoryDetailDao.storeSpareInventoryDetail(detail);
		}
	}
	public void copySpareAccountToSpareInventoryBillDtl(Spare spare,SpareInventoryDetail detail){
		
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
		if(spare.getModelSpecs()!=null){
			detail.setModel(spare.getModelSpecs());        //复制型号
		}else{
			detail.setModel(null);
		}
		if(spare.getSpecification()!=null){               //复制规格
			detail.setSpecification(spare.getSpecification());
		}else{
			detail.setSpecification(null);
		}
		if(spare.getCategory()!=null){
			if(spare.getCategory().getName()!=null){          //复制种类
				detail.setCategory(spare.getCategory());
				//detail.setCategoryName(spare.getCategory().getName());
			}else{
				detail.setCategory(null);
			}
		}
		if(spare.getUnit()!=null){            //复制单位
			detail.setUnit(spare.getUnit());
		}else{
			detail.setUnit(null);
		}

	}
	/**
	 * 保存盘点单明细
	 */
	public void storeSpareInventoryBillDtl(SpareInventory spareInventoryBill, String allSpareInventoryBillDtlId, 
			String allSpareInventoryBillDtlNumber,String allSpareInventoryBillDtlComment) {
		String[] dtlIdArr = null;
		String[] dtlInventoryNumberArr = null;
		String[] dtlInventoryCommentArr = null;
	
        //盘点单明细ID数组
		if(null!=allSpareInventoryBillDtlId){
			dtlIdArr = allSpareInventoryBillDtlId.split(",");    			
		}
		if(null!=allSpareInventoryBillDtlNumber){
			dtlInventoryNumberArr = allSpareInventoryBillDtlNumber.split(",");  	//入库单明细ID和入库数量数组
		}
		if(null!=allSpareInventoryBillDtlComment){
			dtlInventoryCommentArr = allSpareInventoryBillDtlComment.split(",");
		}
		
		updateSpareInventoryBillDetail(dtlIdArr,dtlInventoryNumberArr,dtlInventoryCommentArr);	//更新入库明细的信息
	}
	public void updateSpareInventoryBillDetail(String [] dtlIdArr,
			String [] dtlInventoryNumberArr,
			String [] dtlInventoryCommentArr){
		SpareInventoryDetail detail = new SpareInventoryDetail();
		//盘点单明细的循环数量
		int inventoryBillDtlNum=0;
		while(dtlIdArr!=null&&inventoryBillDtlNum<dtlIdArr.length){
			detail=spareInventoryDetailDao.loadSpareInventoryDetail(Long.valueOf(dtlIdArr[inventoryBillDtlNum]));
			Spare spare=this.spareManger.loasSpare(detail.getSpare().getId());
			//盘点数量
			long inventoryNum = 0;
			if(dtlInventoryNumberArr!=null){
				   for(int i=0;i<dtlInventoryNumberArr.length;i=i+2){
					   if(dtlInventoryNumberArr[i].equals(dtlIdArr[inventoryBillDtlNum])){
						   inventoryNum = Long.valueOf(dtlInventoryNumberArr[i+1]);
						   detail.setInventoryNum(inventoryNum);
						   break;
					   }else{
						   detail.setInventoryNum(null);
					   }
				   }
			 }

			//保存盘点后金额
			detail.setInventoryTotalPrice(detail.getUnitPrice()*inventoryNum);
			//保存备注
			if(dtlInventoryCommentArr!=null){
				for(int i=0;i<dtlInventoryCommentArr.length;i=i+2){
					if(dtlInventoryCommentArr[i].equals(dtlIdArr[inventoryBillDtlNum])){
						detail.setComment(dtlInventoryCommentArr[i+1]);
						break;
					}else{
						detail.setComment(null);
					}
				}
			}else{
				detail.setComment(null);
			}
			this.spareInventoryDetailDao.storeSpareInventoryDetail(detail);
			
//			//更新t_spare_Location的总库存
//			if(detail.getLocation()!=null){
//			    if(dtlInventoryNumberArr!=null){
//				   for(int i=0;i<dtlInventoryNumberArr.length;i=i+2){
//				        if(dtlInventoryNumberArr[i].equals(dtlIdArr[inventoryBillDtlNum])){
//				        	for(SpareLocation spareLocation:detail.getLocation().getSpareLoc()){
//				        		//更新t_spare_location盘点后的数量
//					            updateInventoryAfterSpareLocationStocks(spareLocation,detail.getInventoryNum(),detail);
//					            updateInventoryAfterSpareStocks(detail.getSpare().getId());
//					            return;
//				        	  }
//				            }
//				        }
//				  }
//			}
			
			//EAM1.1
//			spare.setStocks(detail.getInventoryNum());
//			this.spareDao.storeSpare(spare); 
//			detail.getInventory().setSubmit(true);
//			spareInventoryDao.storeSpareInventory(detail.getInventory());
//			//更新盘点后的t_spare总库存
//			updateInventoryAfterSpareStocks(detail.getSpare().getId());
//			inventoryBillDtlNum++;
			
			//EAM1.0
			SpareLocation spareLocation = detail.getSpareLocation();
    		//更新t_spare_location盘点后的数量
			updateInventoryAfterSpareLocationStocks(spareLocation,detail.getInventoryNum(),detail);
			spare.setStocks(detail.getInventoryNum());
			this.spareDao.storeSpare(spare); 
			detail.getInventory().setSubmit(true);
			spareInventoryDao.storeSpareInventory(detail.getInventory());
			//更新盘点后的t_spare总库存
			updateInventoryAfterSpareStocks(detail.getSpare().getId());
			inventoryBillDtlNum++;
		}
		
		//更新盘点单的总金额
		updateSpareInventory_totalPrice(detail.getInventory());
	}
     //更新t_spare_location盘点后的数量
	public void updateInventoryAfterSpareLocationStocks(SpareLocation spareLocation,Long inventoryNum,SpareInventoryDetail detail){
		//EAM1.1
		SpareInventoryDetail dtl=spareInventoryDetailDao.getTop1SpareInventoryDetailByDetailId(detail.getId());
		spareLocation.setStocks(dtl.getInventoryNum());
		//EAM1.0
//		spareLocation.setStocks(inventoryNum);
		this.spareLocationDao.storeSpareLocation(spareLocation);
	}
	public void updateInventoryAfterSpareStocks(Long spareId){
		 Long spareSum= this.spareLocationDao.getSumBySpare(spareId);
		 Spare spare=spareDao.loasSpare(spareId);
		 spare.setStocks(spareSum);
		 this.spareDao.storeSpare(spare);
	}
	private void updateSpareInventory_totalPrice(SpareInventory inventory) {
		Double totalPrice = NumberUtils.DOUBLE_ZERO;
		for(SpareInventoryDetail detail : inventory.getInventoryDetails()){
			if(detail.getInventoryTotalPrice()!=null){
				totalPrice += detail.getInventoryTotalPrice();
			}
		}
		inventory.setTotalPrice(totalPrice);
		spareInventoryDao.storeSpareInventory(inventory);
	}
}
