package com.yongjun.tdms.dao.prophase.supplier;

import java.util.List;

import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateView;



public interface SupplierEvaluateViewDao {
	List<SupplierEvaluateView> loadSupplierEvaluateView(Long supplierEvaluateId);
}
