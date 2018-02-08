/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.service.asset.spare.spareWareHouse.pojo;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.dao.asset.spare.spareWareHouse.SpareWareHouseDao;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.spareWareHouse.SpareWareHouse;
import com.yongjun.tdms.service.asset.spare.spareWareHouse.SpareWareHouseManager;

/**
 * <p>Title: DefaultSpareWareHouseManager
 * <p>Description: 备件库总台帐业务逻辑操作实现类</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class DefaultSpareWareHouseManager implements SpareWareHouseManager {
	
	private final SpareWareHouseDao spareWareHouseDao;
	private final SpareLocationDao spareLocationDao;
	
	public DefaultSpareWareHouseManager(SpareWareHouseDao spareWareHouseDao,SpareLocationDao spareLocationDao) {
		this.spareWareHouseDao = spareWareHouseDao;
		this.spareLocationDao = spareLocationDao;
	}

	public SpareWareHouse loadSpareWareHouse(Long spareWareHouseId) {
		return this.spareWareHouseDao.loadSpareWareHouse(spareWareHouseId);
	}

	public SpareWareHouse loadSpareWareHouse(Long spareId, Long wareHouseId) {
		return this.spareWareHouseDao.loadSpareWareHouse(spareId, wareHouseId);
	}
	@Transactional
	public void storeSpareWareHouse(SpareWareHouse spareWareHouse) {
		this.spareWareHouseDao.storeSpareWareHouse(spareWareHouse);
	}
	@Transactional
	public void storeOrUpdateSpareWareHouse(Spare spare, Warehouse wareHouse, Long stocks) {
		if (spare==null && wareHouse==null) {
			return;
		}
		SpareWareHouse spareWareHouse = this.loadSpareWareHouse(spare.getId(), wareHouse.getId());
		
		if (spareWareHouse!= null) {
			
			if(spareWareHouse.getSpare().getOldSpare()!=null&&spareWareHouse.getSpare().getOldSpare().equals("0")
					&&spareWareHouse.getWareHouse().getOldWarehouse()!=null&&spareWareHouse.getWareHouse().getOldWarehouse().equals("Y")){
				if(spareWareHouse.getStocks()>stocks){
					spareWareHouse.setStocks(spareWareHouse.getStocks()-stocks);
					}else{
						spareWareHouse.setStocks(0l);
					}
			}else{
				Long stocksSum = this.spareLocationDao.getSumStocksBySpareAndWareHouse(spare.getId(), wareHouse.getId());
				spareWareHouse.setStocks(stocksSum);
				
			}
		} else {
			spareWareHouse = new SpareWareHouse();
			spareWareHouse.setSpare(spare);
			spareWareHouse.setWareHouse(wareHouse);
			spareWareHouse.setStocks(stocks);
		}
		this.spareWareHouseDao.storeSpareWareHouse(spareWareHouse);
	}
	@Transactional
	public void storeOrUpdateSpareWareHouseHaveDisabe(Spare spare, Warehouse wareHouse, Long stocks) {
		if (spare==null && wareHouse==null) {
			return;
		}
		SpareWareHouse spareWareHouse = this.loadSpareWareHouse(spare.getId(), wareHouse.getId());
		
		if (spareWareHouse!= null) {
//			Long stocksSum = this.spareLocationDao.getSumStocksBySpareAndWareHouse(spare.getId(), wareHouse.getId());
//			spareWareHouse.setStocks(stocksSum);
			if(spareWareHouse.getStocks()>stocks){
			spareWareHouse.setStocks(spareWareHouse.getStocks()-stocks);
			}else{
				spareWareHouse.setStocks(0l);
			}
			if(spareWareHouse.getDisableStocks()!=null&&spareWareHouse.getDisableStocks()>=stocks){
				spareWareHouse.setDisableStocks(spareWareHouse.getDisableStocks()-stocks);	
			}
		} else {
			spareWareHouse = new SpareWareHouse();
			spareWareHouse.setSpare(spare);
			spareWareHouse.setWareHouse(wareHouse);
			spareWareHouse.setStocks(stocks);
		}
		this.spareWareHouseDao.storeSpareWareHouse(spareWareHouse);
	}
	@Transactional
	public void storeOrUpdateOldSpareWareHouse(Spare spare, Warehouse wareHouse, Long stocks) {
		if (spare==null && wareHouse==null) {
			return;
		}
		SpareWareHouse spareWareHouse = this.loadSpareWareHouse(spare.getId(), wareHouse.getId());
		
		if (spareWareHouse!= null) {
//			Long stocksSum = this.spareLocationDao.getSumStocksBySpareAndWareHouse(spare.getId(), wareHouse.getId());
			Long stocksSum = spareWareHouse.getStocks();
			if(stocksSum==null){
				spareWareHouse.setStocks(stocks);
			}else{
				spareWareHouse.setStocks(stocksSum+stocks);
			}
			
			spareWareHouse.setOldSpare("0");
		} else {
			spareWareHouse = new SpareWareHouse();
			spareWareHouse.setSpare(spare);
			spareWareHouse.setWareHouse(wareHouse);
			spareWareHouse.setStocks(stocks);
			spareWareHouse.setOldSpare("0");
		}
		this.spareWareHouseDao.storeSpareWareHouse(spareWareHouse);
	}
	@Transactional
	public void storeOrUpdateOldSpareWareHouseHaveDisabe(String isDisable,Spare spare, Warehouse wareHouse, Long stocks) {
		if (spare==null && wareHouse==null) {
			return;
		}
		SpareWareHouse spareWareHouse = this.loadSpareWareHouse(spare.getId(), wareHouse.getId());
		
		if (spareWareHouse!= null) {
//			Long stocksSum = this.spareLocationDao.getSumStocksBySpareAndWareHouse(spare.getId(), wareHouse.getId());
			Long stocksSum = spareWareHouse.getStocks();
			if(stocksSum==null){
				spareWareHouse.setStocks(stocks);
			}else{
				spareWareHouse.setStocks(stocksSum+stocks);
			}
			if(spareWareHouse.getDisableStocks()!=null&&isDisable!=null&&isDisable.equals("N")){
				spareWareHouse.setDisableStocks(spareWareHouse.getDisableStocks()+stocks);
			}
			
			spareWareHouse.setOldSpare("0");
		} else {
			spareWareHouse = new SpareWareHouse();
			spareWareHouse.setSpare(spare);
			spareWareHouse.setWareHouse(wareHouse);
			spareWareHouse.setStocks(stocks);
			spareWareHouse.setOldSpare("0");
			if(spareWareHouse.getDisableStocks()!=null&&isDisable!=null&&isDisable.equals("N")){
				spareWareHouse.setDisableStocks(stocks);
			}
		}
		this.spareWareHouseDao.storeSpareWareHouse(spareWareHouse);
	}
	@Transactional
	public void storeSpareWareHouse(String allSpareWareHouseIds, String allSpareWareHouseMinStocks) {
		String [] spareWareHouseIds = null;
		String [] spareWareHouseMinStocks = null;
		//System.out.println("spareWareHouseIds.length allSpareWareHouseIds " + allSpareWareHouseIds);
		if(null!=allSpareWareHouseIds){
			spareWareHouseIds = allSpareWareHouseIds.split(",");    			//备件库总台帐ID
		}
		if(null!=allSpareWareHouseMinStocks){
			spareWareHouseMinStocks = allSpareWareHouseMinStocks.split(",");    			//备件库总台帐匹配的安全库存
		}
		SpareWareHouse spareWareHouse = new SpareWareHouse();
		int loopNum = 0;
		
		System.out.println("spareWareHouseIds.length " + spareWareHouseIds.length);
		while (null!=spareWareHouseIds && loopNum<spareWareHouseIds.length) {
			//根据备件库总台帐ID获取备件库总台帐实体
			spareWareHouse = this.spareWareHouseDao.loadSpareWareHouse(Long.valueOf(spareWareHouseIds[loopNum]));
			//System.out.println("loopNum " + Long.valueOf(spareWareHouseIds[loopNum]));
			//设置安全库存
			if(spareWareHouseMinStocks!= null){
				for(int i=0;i<spareWareHouseMinStocks.length;i=i+2){
					if(spareWareHouseMinStocks[i].equals(spareWareHouseIds[loopNum])){
						Long minStocks = Long.valueOf(spareWareHouseMinStocks[i+1]);
						//System.out.println("minStocks " + minStocks);
						spareWareHouse.setMinStocks(minStocks);
						 break;
					} else {
						spareWareHouse.setMinStocks(null);
					}
				}
			} else {
				spareWareHouse.setMinStocks(null);
			}
			this.spareWareHouseDao.storeSpareWareHouse(spareWareHouse);
			loopNum++;
			
		}
	}
	
}
