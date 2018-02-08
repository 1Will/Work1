package com.yongjun.tdms.dao.prophase.business.tooling.reportmanager;

import java.util.List;

import com.yongjun.tdms.model.prophase.business.report.PurchaseCountBySupplierReport;

public interface PurchaseCountBySupplierReportDao {
	public List<PurchaseCountBySupplierReport> loadDetailViewNumberSupplier(String[] array);
}
