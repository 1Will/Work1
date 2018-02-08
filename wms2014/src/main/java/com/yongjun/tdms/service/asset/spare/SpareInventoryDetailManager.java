package com.yongjun.tdms.service.asset.spare;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import com.yongjun.tdms.model.asset.spare.SpareInventoryDetail;

public interface SpareInventoryDetailManager {
	List<SpareInventoryDetail> loadAllSpareInventoryDetails(
			Long[] spareInventoryDetailIds);

	SpareInventoryDetail loadSpareInventoryDetail(Long spareInventoryDetailId);

	@Transactional
	void storeSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);

	@Transactional
	void deleteSpareInventoryDetail(SpareInventoryDetail spareInventoryDetail);

	@Transactional
	void deleteAllSpareInventoryDetail(
			Collection<SpareInventoryDetail> spareInventoryDetails);
}
