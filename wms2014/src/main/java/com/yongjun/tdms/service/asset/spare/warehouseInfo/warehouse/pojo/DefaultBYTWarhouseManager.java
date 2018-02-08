package com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.dao.asset.spare.warehouseInfo.warehouse.WarehouseDao;
import com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse.BYTWarhouseManager;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;

public class DefaultBYTWarhouseManager implements BYTWarhouseManager {
	private WarehouseDao warehouseDao;

	public List loadWareHouseByStorageGradeId(String paramString) {
		// TODO Auto-generated method stub
		return this.warehouseDao.loadWareHouseByStorageGradeId(paramString);
	  
	}

	public List loadWareHouseByUser(String paramString1, String paramString2) {
		 List warehouses = this.warehouseDao.loadWareHouseByStorageGradeId(paramString1);
//		    List results = null;
//		    User user = this.userManager.loadUser(Long.valueOf(Long.parseLong(paramString2)));
//		    if (user.getWarehouses() != null) {
//		      results = new ArrayList();
//		      for (Warehouse ws : warehouses) {
//		        for (Warehouse w : user.getWarehouses()) {
//		          if (w.getId().equals(ws.getId())) {
//		            results.add(ws);
//		          }
//		        }
//		      }
//		    }
		    return warehouses;
		  }

	public WarehouseDao getBytwarehouseDao() {
		return warehouseDao;
	}

	public void setBytwarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	
	

}
