package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetailView;

@Transactional(readOnly=true)
public interface AcceptBillDetailManager {
	/**
	 * 根据传入的验收单明细ID，获取验收单明细
	 * 
	 * @param AcceptBillDetailId 验收单明细ID
	 * @return AcceptBillDetail 验收单明细
	 */
	@Transactional
	AcceptBillDetail loadAcceptBillDetail(Long AcceptBillDetailId);
	/**
	 * 根据传入的验收单明细ID集合，获取验收单集合
	 * 
	 * @param AcceptBillDetaiIds 验收单明细ID集合
	 * @return List 采购单集合
	 */
	List<AcceptBillDetail> loadAcceptBillDetais(Long[] AcceptBillDetaiIds);

	/**
	 * 保存验收明细单实体
	 * 
	 * @param AcceptBillDetail 验收单明细
	 * @return
	 */
	@Transactional
	void storeAcceptBillDetail(AcceptBillDetail acceptBillDetai);
	/**
	 * 删除验收明细实体
	 * 
	 * @param acceptBillDetai 验收明细单实体
	 * @return
	 */
	@Transactional
	void deleteAcceptBillDetail(AcceptBillDetail acceptBillDetail);
	/**
	 * 根据传入的采购单集合，删除集合中的采购单
	 * 
	 * @param AcceptBillDetails 验收单集合
	 * @return
	 */
	@Transactional
	void deleteAllAcceptBillDetails(Collection<AcceptBillDetail> AcceptBillDetails);
	/**
	 * 
	 * @param acceptBill
	 * @param allToolingMakeDtlId
	 * @param allToolingMakeDtlUnitPrice
	 * @param allToolingMakeDtlAccount
	 * @param allToolingMakeDtlExecResult
	 */
	@Transactional
	void storeToolingMakeDetail(String allToolingMakeDtlId,String llToolingMakeDtlGroupNo,String allToolingMakeDtlUnitPrice,
    		String allToolingMakeDtlAccount,String allToolingMakeDtlExecResult,String allToolingMakeDtlEstablish,String allToolingMakeDtlDepartment);
/**
 * 
 * @param acceptBillDetai
 */
	@Transactional
	void storeToolingAcceptBillToolingMakeDetail(AcceptBillDetail acceptBillDetai);
	/**
	 * 删除所有的工装制作明细
	 * @param AcceptBillDetails
	 */
	@Transactional
	void deleteToolingAllAcceptBillMakeDetails(Collection<AcceptBillDetail> AcceptBillDetails)throws Exception;
	@Transactional
	void deleteToolingAllAcceptBillMakeDetails(Collection<AcceptBillDetail> AcceptBillDetails,AcceptBill acceptBill)throws Exception;
	/**
	 * 
	 * @param accept
	 * @param spareAccountIds
	 */
	@Transactional
	void storeSpareAccountToAcceptSparePurchaseDetail(AcceptBill accept,String spareAccountIds);
	/**
	 * 
	 * @param accept
	 * @param allSparePurchaseDtlId
	 * @param allSparePurchaseDtlUnitPrice
	 * @param allSparePurchaseDtlAmount
	 * @param allPreRepairProcExecResult
	 * @param allSparePurchaseDtlMemo
	 */
	@Transactional
	void storeAcceptSparePurchaseDetail(AcceptBill accept,String allSparePurchaseDtlId,String allSparePurchaseDtlUnitPrice,
			String allSparePurchaseDtlAmount,String allPreRepairProcExecResult,String allSparePurchaseDtlMemo);
	/**
	 * 
	 * @param repairMaintenanceDetailIds
	 * @param allPreRepairProcExecResult
	 * @param allRepairMaintenanceDtlMemo
	 */
	@Transactional
	void storeAcceptRepairMaintenanceDetail(String repairMaintenanceDetailIds,String allPreRepairProcExecResult,String allRepairMaintenanceDtlMemo);
	/**
	 * 
	 * @param techAlterId
	 * @param techAlterResult
	 * @param techAlterMemo
	 */
	@Transactional
	void storeTechAlterAcceptDetail(String techAlterId,String techAlterResult,String techAlterMemo);
	/**
	 * 
	 * @param acceptBill
	 * @param toolingMakeDetailIds
	 */
	@Transactional
	void storePurchaseContractDtl(AcceptBill acceptBill,String toolingMakeDetailIds);
	
	List<AcceptBillDetailView> loadAcceptBillDetailView(Long id);
	/**
	 * 保存验收单明细到工装台帐
	 * @param deviceCard
	 * @param detail
	 */
	@Transactional
	public void storeAcceptBillDetailToDeviceCard(DeviceCard deviceCard,AcceptBillDetail detail);
//	@Transactional
//	public void updateEstalishValue(AcceptBillDetail detail);
//	public void parvalue(String value);
}
