package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
 
public interface PurchaseBillDetailManager {
	@Transactional
	PurchaseBillDetail loadPurchaseBillDetail(Long purchaseBillDetailId);
	List<PurchaseBillDetail> loadPurchaseBillDetails(Long[] PurchaseBillDetailIds);
	@Transactional
	void storePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail);
	@Transactional
	void deletePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail);
	@Transactional
	void deleteAllPurchaseBillDetails(Collection<PurchaseBillDetail> PurchaseBillDetails, PurchaseBill purchaseBill)throws Exception;
	@Transactional
	void storSubscribeDtl(PurchaseBill purchaseBill,String addSubscribeIds);
	@Transactional
	void storPurchaseBillDetail(PurchaseBill purchaseBill,String addSpareAccountIds);
	@Transactional
	void storPurchaseBillDetail(PurchaseBill purchaseBill,String addPurchaseBillDtlIds,String addPurchaseBillDetailAmountNumber,
			String addPurchaseBillDetailUnitPrice,String addPurchaseBillDetailRequestDate,String addPurchaseBillDetailComment,User user);
	List loadAllPurchaseBillDtlBySubDtlId(Long id);
	void accordingSpareInWarehouseDetailChangePurchaseBillStatus(PurchaseBillDetail purchaseBillDetail);
	@Transactional
	void storPurchaseContractDetail(PurchaseBill purchaseBill, String addPurchaseBillDtlIds, 
            String addPurchaseBillDetailAmountNumber, String addPurchaseBillDetailUnitPrice, 
            String addPurchaseBillDetailRequestDate,String addPurchaseContractDetailComment);
	@Transactional
	void storPurchaseBillDtl(PurchaseBill purchaseBill, String addPurchaseBillIds);
	@Transactional
	public void storPurchaseBillContractDetail(PurchaseBill purchaseBill, String addSpareAccountIds);
	@Transactional
	void storeSparePurchaseDtl(PurchaseBill purchaseBill, String addPurchaseBillIds);
	@Transactional
	void storeRepairMantancePurchaseDtl(PurchaseBill purchaseBill, String addPurchaseBillIds);
	@Transactional
	void storeTechAlterDetailsPurchaseConcatDtl(PurchaseBill purchaseBill, String addPurchaseBillIds);
	void updatePurchaseBillStatus(PurchaseBill purchaseBill);
	/**
	 * 出入单价就保存 dwr
	 * @param str 页面字段值
	 */
	@Transactional
	public void updateParameterFromPage(String[] param);
	
	public List<PurchaseBillDetail> loadByKey(String key, Object value) throws DaoException;
	public List<PurchaseBillDetail> loadByKeyArry(String[] keys, Object[] values) throws DaoException;
	
	/**
	 * 更新 采购单的 采购项、入库项
	 * @param purchaseBill
	 */
	public void updateNnmber(PurchaseBill purchaseBill);
	
	/**
	 * 根据采购明细ID集合获取对应的申购明细ID集合
	 * @param purchaseBill
	 */
	public String updSubDetailIds(String PurchaseBillIds);
	
	/**
	 * 根据传入的采购单明细ID集合，更新申购单、汇总单的采购项
	 * 
	 * @param PurchaseBills 采购单明细ID集合
	 * @return
	 */
	public void updStatus(String PurchaseBillIds);
}
