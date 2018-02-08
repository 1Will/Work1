package com.yongjun.tdms.service.asset.spare.warehouseInfo.warehouse;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface BYTWarhouseManager {
	@Transactional
	 public abstract List loadWareHouseByStorageGradeId(String paramString);
	@Transactional
	  public abstract List loadWareHouseByUser(String paramString1, String paramString2);

}
