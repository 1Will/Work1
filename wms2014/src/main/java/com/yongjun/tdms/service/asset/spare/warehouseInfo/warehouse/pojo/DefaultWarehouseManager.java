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
package com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.dao.asset.spare.warehouseInfo.warehouse.WarehouseDao;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.WarehouseManager;

/**
 * <p>
 * Title:DefaultWarehouseManager
 * <p>
 * Description:DefaultWarehouseManager
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version
 */
@Deprecated
public class DefaultWarehouseManager
implements WarehouseManager {
private final WarehouseDao warehouseDao;
private final UserManager  userManager;

	public DefaultWarehouseManager(final WarehouseDao warehouseDao,final UserManager  userManager) {
	this.warehouseDao = warehouseDao;
	this.userManager=userManager;
}

	public void deleteAllWarehouse(Collection<Warehouse> warehouses) {
		this.warehouseDao.deleteAllWarehouse(warehouses);
		
	}

	public void deleteWarehouse(Warehouse warehouse) {
		this.warehouseDao.deleteWarehouse(warehouse);
		
	}

	public List<Warehouse> loadAllWarehouse(Long[] warehouseIds) {
		return this.warehouseDao.loadAllWarehouse(warehouseIds);
	}

	public Warehouse loadWarehouse(Long warehouseId) {
		return this.warehouseDao.loadWarehouse(warehouseId);
	}

	public void storeWarehouse(Warehouse warehouse) {
		this.warehouseDao.storeWarehouse(warehouse);
	}

	public void disabledAllWarehouse(Collection<Warehouse> warehouse) {
		for(Warehouse w : warehouse){
			w.setDisabled(true);
			this.warehouseDao.storeWarehouse(w);
		}
		
	}

	public void enabledAllWarehouse(Collection<Warehouse> warehouse) {
		for(Warehouse w : warehouse){
			w.setDisabled(false);
			this.warehouseDao.storeWarehouse(w);
		}
		
	}

	public List<Warehouse> loadAllValidWarehouse() {
		List<Warehouse> whs ;
		 try {
			whs = this.warehouseDao.loadByKey("disabled", false);
		}
		catch (DaoException e) {
			e.printStackTrace();
			whs = new ArrayList<Warehouse>();
		}
		return whs;
	}

	public List<Warehouse> loadAllWarehouse() {
		List<Warehouse> whs ;
		 try {
			whs = this.warehouseDao.loadAllWarehouse();
		}
		catch (Exception e) {
			e.printStackTrace();
			whs = new ArrayList<Warehouse>();
		}
		return whs;
	}

	public List<Warehouse> loadByKey(String keyName, Object keyValue) throws DaoException {
		return warehouseDao.loadByKey(keyName, keyValue);
	}

	public List<Warehouse> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return warehouseDao.loadByKeyArray(keyNames, keyValues);
	}

	public List loadWareHouseByStorageGradeId(String id) {
		 
		return this.warehouseDao.loadWareHouseByStorageGradeId(id);
	}
	 public List loadWareHouseByUser(String id, String userId) {
		    List<Warehouse> warehouses = this.warehouseDao.loadWareHouseByStorageGradeId(id);
		    List<Warehouse> results=null;
			List<Integer> wid = warehouseDao.loadWareHouseByUserAndNew(Long.parseLong(userId));
			
			User user = this.userManager.loadUser(Long.parseLong(userId));
			if(null != user.getWarehouses()){
				results = new ArrayList<Warehouse>();
				for(Warehouse ws :warehouses){
					for(Integer i : wid){
						if(Long.parseLong(i+"")==ws.getId()){
							results.add(ws);
						}
					}
				}
			}
			return results;
		    
//		    return warehouses;
		  }

	public Warehouse loadJJWareHouse(String code) {
		// TODO Auto-generated method stub
		return this.warehouseDao.loadJJWareHouse(code);
	}

	public List<Warehouse> loadByUser(long userid) throws DaoException {
		// TODO Auto-generated method stub
		List<Warehouse> userbleWarehouses = new ArrayList<Warehouse>();
		List<Integer> wid = warehouseDao.loadWareHouseByUser(userid);
		
		for(Integer i:wid){
			Warehouse wa = warehouseDao.loadWarehouse(Long.parseLong(i+""));
			userbleWarehouses.add(wa);
		}
		
		
		return userbleWarehouses;
	}
	
	public List<Warehouse> loadByUserAndNew(long userid) throws DaoException {
		// TODO Auto-generated method stub
		List<Warehouse> userbleWarehouses = new ArrayList<Warehouse>();
		List<Integer> wid = warehouseDao.loadWareHouseByUserAndNew(userid);
		
		for(Integer i:wid){
			Warehouse wa = warehouseDao.loadWarehouse(Long.parseLong(i+""));
			userbleWarehouses.add(wa);
		}
		
		
		return userbleWarehouses;
	}
	
	public List<Warehouse> loadByAber(String wids) {
		// TODO Auto-generated method stub
		return warehouseDao.loadByAber(wids);
	}
	
}
