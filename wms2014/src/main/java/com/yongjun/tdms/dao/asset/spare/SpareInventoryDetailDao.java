package com.yongjun.tdms.dao.asset.spare;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.asset.spare.SpareInventoryDetail;

public interface SpareInventoryDetailDao {
	List<SpareInventoryDetail> loadAllSpareInventoryDetails(
			Long[] spareInventoryDetailIds);

	SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId);

	void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);

	void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);

	void deleteAllSpareInventoryDetail(
			Collection<SpareInventoryDetail> spareInventoryDetails);
}
