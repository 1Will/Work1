package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.prophase.business.PayDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
@Transactional(readOnly=true)
public interface PurchaseBillPayDetailManager {
	
	
		@Transactional
		PayDetail loadPayDetail(Long id);
	    List<PayDetail> loadPayDetails(Long[] PayDetailIds);
		@Transactional
		void storePayDetail(PayDetail payDetail);
		@Transactional
		void deletePayDetail(PayDetail payDetail);
		@Transactional
		void deleteAllPayDetail(Collection<PayDetail> payDetailIds,PurchaseBill purchaseBill);
	
}
