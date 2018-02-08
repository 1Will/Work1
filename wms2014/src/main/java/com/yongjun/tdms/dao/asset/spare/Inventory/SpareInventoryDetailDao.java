package com.yongjun.tdms.dao.asset.spare.Inventory;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.asset.spare.Inventory.SpareInventoryDetail;
import com.yongjun.tdms.model.asset.spare.inWareHouse.SpareInBillDetail;

public interface SpareInventoryDetailDao {
	List<SpareInventoryDetail> loadAllSpareInventoryDetails(
			Long[] spareInventoryDetailIds);

	SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId);

	void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);

	void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);

	void deleteAllSpareInventoryDetail(
			Collection<SpareInventoryDetail> spareInventoryDetails);
	/**
	 * 给据备件明细的Id,获得顶层明细的对象
	 * @param detailId
	 * @return
	 */
	SpareInventoryDetail getTop1SpareInventoryDetailByDetailId(Long detailId);
}
