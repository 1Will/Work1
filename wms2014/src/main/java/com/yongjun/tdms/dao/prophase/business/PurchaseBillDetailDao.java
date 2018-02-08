package com.yongjun.tdms.dao.prophase.business;

import java.util.Collection;
import java.util.List;


import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;

public interface PurchaseBillDetailDao {
	/**
	 * 根据传入的采购单ID，获取采购单
	 * 
	 * @param purchaseBillDetailId 采购单ID
	 * @return PurchaseBillDetail 采购单
	 */
	PurchaseBillDetail loadPurchaseBillDetail(Long purchaseBillDetailId);
	/**
	 * 根据传入的采购单明细ID集合，获取采购单明细集合
	 * 
	 * @param PurchaseBillDetailIds 采购单明细ID集合
	 * @return List 采购单明细集合
	 */
	List<PurchaseBillDetail> loadPurchaseBillDetails(Long[] PurchaseBillDetailIds);

	/**
	 * 保存采购单明细实体
	 * 
	 * @param purchaseBillDetail 采购单明细实体
	 * @return
	 */
	void storePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail);
	/**
	 * 删除采购单明细实体
	 * 
	 * @param purchaseBillDetail 采购单明细实体
	 * @return
	 */
	void deletePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail);
	/**
	 * 根据传入的采购单明细集合，删除集合中的采购单明细
	 * 
	 * @param PurchaseBillDetails 采购单明细集合
	 * @return
	 */
	void deleteAllPurchaseBillDetails(Collection<PurchaseBillDetail> PurchaseBillDetails);
	List loadAllPurchaseBillDtlBySubDtlId(Long id);
	/**
	 * 
	 * @param id
	 * @return
	 */
	Double getPurchaseDetailTotalByPurchaseId(Long id);
	

	public List<PurchaseBillDetail> loadByKey(String key, Object value) throws DaoException;
	public List<PurchaseBillDetail> loadByKeyArry(String[] keys, Object[] values) throws DaoException;
	
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
