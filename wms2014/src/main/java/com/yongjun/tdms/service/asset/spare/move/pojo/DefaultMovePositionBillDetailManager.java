package com.yongjun.tdms.service.asset.spare.move.pojo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.location.LocationDao;
import com.yongjun.tdms.dao.asset.spare.move.MovePositionBillDao;
import com.yongjun.tdms.dao.asset.spare.move.MovePositionBillDetailDao;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.model.asset.spare.Location;
import com.yongjun.tdms.model.asset.spare.SpareLocation;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBill;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBillDetail;
import com.yongjun.tdms.model.asset.spare.move.MovePositionBillDetailStatus;
import com.yongjun.tdms.model.asset.spare.move.MovePositionStatus;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;
import com.yongjun.tdms.service.asset.spare.move.MovePositionBillDetailManager;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;

public class DefaultMovePositionBillDetailManager extends BaseManager implements MovePositionBillDetailManager{
    private final MovePositionBillDetailDao movePositionBillDetailDao;
    private final SpareLocationDao spareLocationDao;
    private final LocationDao locationDao;
    private final MovePositionBillDao movePositionBillDao;
	private final SpareWareHouseManager spareWareHouseManager;
	
    public DefaultMovePositionBillDetailManager(MovePositionBillDetailDao movePositionBillDetailDao,
    											SpareLocationDao spareLocationDao,
    											LocationDao locationDao,
    											MovePositionBillDao movePositionBillDao,
    											SpareWareHouseManager spareWareHouseManager){
    	  this.movePositionBillDetailDao=movePositionBillDetailDao;
    	  this.spareLocationDao = spareLocationDao;
    	  this.locationDao = locationDao;
    	  this.movePositionBillDao = movePositionBillDao;
    	  this.spareWareHouseManager = spareWareHouseManager;
    }
	public List<MovePositionBillDetail> loadAllMovePositionBillDtl(Long[] ids) {
		return 	this.movePositionBillDetailDao.loadAllMovePositionBillDtl(ids);
	}

	public MovePositionBillDetail loadMovePositionBillDtl(Long id) {
		
		return this.movePositionBillDetailDao.loadMovePositionBillDtl(id);
	}
	
	public void storeMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl) {
		this.movePositionBillDetailDao.storeMovePositionBillDtl(movePositionBillDtl);
		
	}
	
	public void deleteMovePositionBillDtl(MovePositionBillDetail movePositionBillDtl) {
		
		this.movePositionBillDetailDao.deleteMovePositionBillDtl(movePositionBillDtl);
	}
	
	public void deleteAllMovePositionBillDtl(Collection<MovePositionBillDetail> movePositionBillDtls,
			MovePositionBill movePostionBill) throws Exception {
		//Is or deleted movePostionBillDetail according this movePostionBillDetail status
		deleteMovePostionBillDetailDependingOnStatus(movePositionBillDtls);
		this.movePositionBillDetailDao.deleteAllMovePositionBillDtl(movePositionBillDtls);
	
		//Remove the movePostionBill the cache
		for(MovePositionBillDetail detail : movePositionBillDtls){
			movePostionBill.getMovePositionBillDtls().remove(detail);
		}
		//update movePostionBill status
		accordingMovePostionBillDetailStatusUpdateMovePostionBillStatus(movePostionBill);
	}
	
	private void deleteMovePostionBillDetailDependingOnStatus(Collection<MovePositionBillDetail> movePositionBillDtls) throws Exception {
		// TODO Auto-generated method stub
		for(MovePositionBillDetail detail : movePositionBillDtls){
			if(detail.getMovePositionDtlStatus().equals(MovePositionBillDetailStatus.movePositionedDtl)){
				throw new Exception("The Runtime Exception");
			}
		}
	}
	/**
	 * Data from the t_spare_location copied to t_move_postion_detail
	 */
	public void storeSpareLocationToMovePostionDtl(MovePositionBill movePostionBill, String addSpareLocationAccountIds) {
		String [] addSLAId = null;
		if(addSpareLocationAccountIds != null){
			addSLAId = addSpareLocationAccountIds.split(",");
		}
		
		for(int i=0;i<addSLAId.length;i++){
			MovePositionBillDetail movePBDtl = new MovePositionBillDetail();
			SpareLocation spareLocation = spareLocationDao.loadSpareLocation(Long.valueOf(addSLAId[i]));
			movePBDtl.setSpareLocation(spareLocation);
			if(spareLocation.getLocation()!=null){
				//movePBDtl.setLocation(spareLocation.getLocation());
				movePBDtl.setBeforeMoveLocationCode(spareLocation.getLocation().getCode());
				movePBDtl.setBeforeMoveWarehouse(spareLocation.getWarehouse().getName());
				movePBDtl.setBeforeMoveRegional(spareLocation.getRegional().getName());
			}
			if(spareLocation.getSpare()!=null){
				movePBDtl.setSpareNo(spareLocation.getSpare().getSpareNo());
				movePBDtl.setSpareName(spareLocation.getSpare().getName());
				movePBDtl.setModelSpec(spareLocation.getSpare().getModelSpecs());
				movePBDtl.setSpareId(spareLocation.getSpare().getId());
			}
			if(spareLocation.getStocks()!=null){
				movePBDtl.setBeforeMoveStocks(spareLocation.getStocks());
				movePBDtl.setNumber(spareLocation.getStocks());
			}
			if(spareLocation.getDepartment()!=null){
				movePBDtl.setDepartment(spareLocation.getDepartment());
			}
			movePBDtl.setMovePositionBill(movePostionBill);
			
			this.movePositionBillDetailDao.storeMovePositionBillDtl(movePBDtl);
		}
		//update movePostionBill status
		accordingMovePostionBillDetailStatusUpdateMovePostionBillStatus(movePostionBill);
	}
	/**
	 * Save movePostionBillDetail
	 */
	public void storeMovePositionBillDtl(String allMovePostionBillDtlIds, 
			String allLocationCodeValue, String allMovePostionDetailNumber) {
		String [] allMPBId = null;
		String [] allLCValue = null;
		String [] allMPDNumber = null;
		if(allMovePostionBillDtlIds!=null){
			allMPBId = allMovePostionBillDtlIds.split("##");
		}
		if(allLocationCodeValue!=null){
			allLCValue = allLocationCodeValue.split(",");
		}
		if(allMovePostionDetailNumber!=null){
			allMPDNumber = allMovePostionDetailNumber.split("##");
		}
		
		MovePositionBillDetail movePBDtl=null;
		int i=0;
		while(allMPBId!=null&&i<allMPBId.length){
			movePBDtl= new MovePositionBillDetail();
			movePBDtl = movePositionBillDetailDao.loadMovePositionBillDtl(Long.valueOf(allMPBId[i]));
			if(allLCValue!=null){
				Location location;
				for(int j=0;i<allMPBId.length&&j<allLCValue.length;j=j+2){
					if(allLCValue[j].equals(allMPBId[i])){
						if ((j+1) < allLCValue.length) {
//							location=locationDao.getLocationByCodeOnlyValid(allLCValue[j+1]);
							long locationId = Long.parseLong(allLCValue[j+1]);
							location=locationDao.loadLocation(locationId);
							if (null != location) {
								Warehouse warehouse = location.getWarehouse();
								Regional regional = location.getRegional();
								movePBDtl.setLocation(location);
								movePBDtl.setAfterMoveLocationCode(location.getCode());
								movePBDtl.setWarehouse(warehouse);
								movePBDtl.setAfterMoveWarehouse(warehouse.getName());
								movePBDtl.setRegional(regional);
								movePBDtl.setAfterMoveRegional(regional.getName());
							}
							else {
								movePBDtl.setLocation(null);
								movePBDtl.setAfterMoveLocationCode(null);
								movePBDtl.setWarehouse(null);
								movePBDtl.setAfterMoveWarehouse(null);
								movePBDtl.setRegional(null);
								movePBDtl.setAfterMoveRegional(null);
							}
						} else {
							movePBDtl.setLocation(null);
							movePBDtl.setAfterMoveLocationCode(null);
							movePBDtl.setWarehouse(null);
							movePBDtl.setAfterMoveWarehouse(null);
							movePBDtl.setRegional(null);
							movePBDtl.setAfterMoveRegional(null);
						}
						break;
					}else{
						movePBDtl.setLocation(null);
						movePBDtl.setAfterMoveLocationCode(null);
						movePBDtl.setWarehouse(null);
						movePBDtl.setAfterMoveWarehouse(null);
						movePBDtl.setRegional(null);
						movePBDtl.setAfterMoveRegional(null);
					}
				}
			}else{
				movePBDtl.setLocation(null);
				movePBDtl.setAfterMoveLocationCode(null);
				movePBDtl.setWarehouse(null);
				movePBDtl.setAfterMoveWarehouse(null);
				movePBDtl.setRegional(null);
				movePBDtl.setAfterMoveRegional(null);
			}
			
			if(allMPDNumber!=null){
				for(int j=0;i<allMPDNumber.length;j=j+2){
					if(allMPDNumber[j].equals(allMPBId[i])){
						movePBDtl.setNumber(Long.valueOf(allMPDNumber[j+1]));
						break;
					}else{
						movePBDtl.setNumber(null);
					}
				}
			}else{
				movePBDtl.setNumber(null);
			}
			//movePBDtl.setMovePositionDtlStatus(MovePositionBillDetailStatus.movePositionedDtl);
			movePositionBillDetailDao.storeMovePositionBillDtl(movePBDtl);
			i++;
			/*
			 * After save movePostionBillDetail,according spare.id/department.id/location.id to do judge
			 * If spare.id/department.id/location.id already exists in t_spare_location Then update this t_spare_location
			 * Else new t_spare_location 
			 */
			storeOrUpdateSpareLocationByMovePositionBillDetail(movePBDtl);
			
			movePBDtl.setMovePositionDtlStatus(MovePositionBillDetailStatus.movePositionedDtl);
			movePositionBillDetailDao.storeMovePositionBillDtl(movePBDtl);
		}
		//update movePostionBill status
		accordingMovePostionBillDetailStatusUpdateMovePostionBillStatus(movePBDtl.getMovePositionBill());
	}
	/**
	 * 
	 * @param movePBDtl
	 */
	private void storeOrUpdateSpareLocationByMovePositionBillDetail(MovePositionBillDetail movePBDtl) {
		//移位前的spareLocation对象
		SpareLocation beforeMoveSpareLocation = movePBDtl.getSpareLocation();
		//移位前的spareLocation上的库存数量 
		Long beforeNumber = beforeMoveSpareLocation.getStocks();
		if(movePBDtl.getDepartment()!=null&&movePBDtl.getSpareId()!=null&&movePBDtl.getLocation()!=null){
			SpareLocation afterMoveSpareLocation = spareLocationDao
																	.getTop1SpareLocationByDeptAndLocationAndSpare(movePBDtl.getDepartment().getId(),
																	movePBDtl.getLocation().getId(), movePBDtl.getSpareId(),false);
			if(afterMoveSpareLocation!=null){
				updateSpareLocationStocksByMovePositionBillDetail(movePBDtl,afterMoveSpareLocation);
				//更新源库存上的库存数量
				if(movePBDtl.getMovePositionDtlStatus().equals(MovePositionBillDetailStatus.unMovePositionDtl)){
					beforeMoveSpareLocation.setStocks(beforeNumber-movePBDtl.getNumber());
					spareLocationDao.storeSpareLocation(beforeMoveSpareLocation);
				}
				return;
			}
		}else if(movePBDtl.getSpareId()!=null&&movePBDtl.getLocation()!=null){
			//需移往的t_spare_location对象
			SpareLocation afterMoveSpareLocation = spareLocationDao
																	.getTop1SpareLocationByLocationAndSpare(movePBDtl.getLocation().getId(),movePBDtl.getSpareId());
			if(afterMoveSpareLocation!=null){
				updateSpareLocationStocksByMovePositionBillDetail(movePBDtl,afterMoveSpareLocation);
				//更新源库存上的库存数量
				if(movePBDtl.getMovePositionDtlStatus().equals(MovePositionBillDetailStatus.unMovePositionDtl)){
					beforeMoveSpareLocation.setStocks(beforeNumber-movePBDtl.getNumber());
					spareLocationDao.storeSpareLocation(beforeMoveSpareLocation);
				}
				return;
			}
		}else if(movePBDtl.getSpareId()==null||movePBDtl.getLocation()==null){
			return;
		}
		
		if(movePBDtl.getMovePositionDtlStatus().equals(MovePositionBillDetailStatus.unMovePositionDtl)){
			beforeMoveSpareLocation.setStocks(beforeNumber-movePBDtl.getNumber());
			spareLocationDao.storeSpareLocation(beforeMoveSpareLocation);
			//新增明细台帐
			storeSpareLocationByMovePostionDetail(movePBDtl);
		}
		//更新备件库总台帐库存
		this.spareWareHouseManager.storeOrUpdateSpareWareHouse(movePBDtl.getSpareLocation().getSpare(), 
				movePBDtl.getSpareLocation().getWarehouse(), movePBDtl.getNumber());
		//更新备件库总台帐库存
		this.spareWareHouseManager.storeOrUpdateSpareWareHouse(movePBDtl.getSpareLocation().getSpare(), 
				movePBDtl.getWarehouse(), movePBDtl.getNumber());
	}
	/**
	 * 新增明细台帐
	 * @param movePBDtl
	 */
	private void storeSpareLocationByMovePostionDetail(MovePositionBillDetail movePBDtl) {
		// TODO Auto-generated method stub
		SpareLocation spareLocation=new SpareLocation();
		spareLocation.setSpare(movePBDtl.getSpareLocation().getSpare());
		spareLocation.setLocation(movePBDtl.getLocation());
		spareLocation.setWarehouse(movePBDtl.getLocation().getWarehouse());
		spareLocation.setRegional(movePBDtl.getLocation().getRegional());
		spareLocation.setDepartment(movePBDtl.getDepartment());
		spareLocation.setLocationCode(movePBDtl.getAfterMoveLocationCode());
		spareLocation.setUnitPrice(movePBDtl.getSpareLocation().getUnitPrice());
		spareLocation.setStocks(movePBDtl.getNumber());
		spareLocationDao.storeSpareLocation(spareLocation);		
	}
	/**
	 * 根据移位明细更新明细台帐库存
	 */
	private void updateSpareLocationStocksByMovePositionBillDetail(MovePositionBillDetail movePBDtl,SpareLocation spareLocation) {
		Long yuanStocks = spareLocation.getStocks();
		if(movePBDtl.getMovePositionDtlStatus().toString().equals(MovePositionBillDetailStatus.unMovePositionDtl.toString())){
			spareLocation.setStocks(movePBDtl.getNumber() + yuanStocks);    //更新明细台帐中库存
		}else{
			spareLocation.setStocks(yuanStocks);
		}
		
		spareLocationDao.storeSpareLocation(spareLocation);
	}
	
	/**
	 * update movePostionBill status
	 * @param movePostionBill
	 */
	public void  accordingMovePostionBillDetailStatusUpdateMovePostionBillStatus(MovePositionBill movePostionBill){
		  //Mark has shifted cycle times
		  int 	inedLoop=0;
		  //Mark not shift cycle times
		  int unInLoop=0;
		  for(MovePositionBillDetail dtl:movePostionBill.getMovePositionBillDtls()){
			   if(dtl.getMovePositionDtlStatus().equals(MovePositionBillDetailStatus.unMovePositionDtl)){
				   unInLoop++;
				   continue;
			   }else{
				   inedLoop++;
				   continue;
			   }
		  }
		  if(unInLoop==movePostionBill.getMovePositionBillDtls().size()){
			  movePostionBill.setMoveStatus(MovePositionStatus.unMovePosition);
		  }else if(inedLoop==movePostionBill.getMovePositionBillDtls().size()){
			  movePostionBill.setMoveStatus(MovePositionStatus.movePositioned);
		  }else{
			  movePostionBill.setMoveStatus(MovePositionStatus.movePositioning);
		  }
		  
		  movePositionBillDao.storeMovePositionBill(movePostionBill);
	  }
	public void storeMovePositionBillDtl(String allMovePostionBillDtlIds, String allLocationCodeValue, 
			String allMovePostionDetailNumber, Long warehouseId) {
		String [] allMPBId = null;
		String [] allLCValue = null;
		String [] allMPDNumber = null;
		if(allMovePostionBillDtlIds!=null){
			allMPBId = allMovePostionBillDtlIds.split("##");
		}
		if(allLocationCodeValue!=null){
			allLCValue = allLocationCodeValue.split(",");
		}
		if(allMovePostionDetailNumber!=null){
			allMPDNumber = allMovePostionDetailNumber.split("##");
		}
		
		MovePositionBillDetail movePBDtl=null;
		int i=0;
		while(allMPBId!=null&&i<allMPBId.length){
			movePBDtl= new MovePositionBillDetail();
			movePBDtl = movePositionBillDetailDao.loadMovePositionBillDtl(Long.valueOf(allMPBId[i]));
			if(allLCValue!=null){
				Location location;
				for(int j=0;i<allMPBId.length&&j<allLCValue.length;j=j+2){
					if(allLCValue[j].equals(allMPBId[i])){
						if ((j+1) < allLCValue.length) {
//							location=locationDao.getLocationByCodeOnlyValid(allLCValue[j+1]);
							location = locationDao.getLocationByCodeOnlyValid(allLCValue[j+1], warehouseId);
//							long locationId = Long.parseLong(allLCValue[j+1]);
//							location=locationDao.loadLocation(locationId);
							if (null != location) {
								Warehouse warehouse = location.getWarehouse();
								Regional regional = location.getRegional();
								movePBDtl.setLocation(location);
								movePBDtl.setAfterMoveLocationCode(location.getCode());
								movePBDtl.setWarehouse(warehouse);
								movePBDtl.setAfterMoveWarehouse(warehouse.getName());
								movePBDtl.setRegional(regional);
								movePBDtl.setAfterMoveRegional(regional.getName());
							}
							else {
								movePBDtl.setLocation(null);
								movePBDtl.setAfterMoveLocationCode(null);
								movePBDtl.setWarehouse(null);
								movePBDtl.setAfterMoveWarehouse(null);
								movePBDtl.setRegional(null);
								movePBDtl.setAfterMoveRegional(null);
							}
						} else {
							movePBDtl.setLocation(null);
							movePBDtl.setAfterMoveLocationCode(null);
							movePBDtl.setWarehouse(null);
							movePBDtl.setAfterMoveWarehouse(null);
							movePBDtl.setRegional(null);
							movePBDtl.setAfterMoveRegional(null);
						}
						break;
					}else{
						movePBDtl.setLocation(null);
						movePBDtl.setAfterMoveLocationCode(null);
						movePBDtl.setWarehouse(null);
						movePBDtl.setAfterMoveWarehouse(null);
						movePBDtl.setRegional(null);
						movePBDtl.setAfterMoveRegional(null);
					}
				}
			}else{
				movePBDtl.setLocation(null);
				movePBDtl.setAfterMoveLocationCode(null);
				movePBDtl.setWarehouse(null);
				movePBDtl.setAfterMoveWarehouse(null);
				movePBDtl.setRegional(null);
				movePBDtl.setAfterMoveRegional(null);
			}
			
			if(allMPDNumber!=null){
				for(int j=0;i<allMPDNumber.length;j=j+2){
					if(allMPDNumber[j].equals(allMPBId[i])){
						movePBDtl.setNumber(Long.valueOf(allMPDNumber[j+1]));
						break;
					}else{
						movePBDtl.setNumber(null);
					}
				}
			}else{
				movePBDtl.setNumber(null);
			}
			//movePBDtl.setMovePositionDtlStatus(MovePositionBillDetailStatus.movePositionedDtl);
			movePositionBillDetailDao.storeMovePositionBillDtl(movePBDtl);
			i++;
			/*
			 * After save movePostionBillDetail,according spare.id/department.id/location.id to do judge
			 * If spare.id/department.id/location.id already exists in t_spare_location Then update this t_spare_location
			 * Else new t_spare_location 
			 */
			storeOrUpdateSpareLocationByMovePositionBillDetail(movePBDtl);
			
			movePBDtl.setMovePositionDtlStatus(MovePositionBillDetailStatus.movePositionedDtl);
			movePositionBillDetailDao.storeMovePositionBillDtl(movePBDtl);
		}
		//update movePostionBill status
		accordingMovePostionBillDetailStatusUpdateMovePostionBillStatus(movePBDtl.getMovePositionBill());
		
	}
	
}
