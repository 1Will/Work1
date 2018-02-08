package com.yongjun.tdms.service.runmaintenance.spareBorrow;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;
@Transactional(readOnly=true)
public interface SpareBorrowDetailManager {
	/**
	 * 根据备件领用单明细的id获得备件领用单明细的对象 
	 * @param id
	 * @return
	 */
	SpareBorrowDetail loadSpareBorrowDetail(Long id);
	/**
	 * 保存备件领用单对象
	 * @param spareBorrowDetail
	 */
	@Transactional
	void storeSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail);
	/**
	 * 根据备件领用单的ids获得年备件领用单明细的集合
	 * @param ids
	 * @return
	 */
	List<SpareBorrowDetail> loadAllSpareBorrowDetails(Long[] ids);
   /**
    * 删除备件领用单明细
    * @param spareBorrowDetail
    */
	@Transactional
	void deleteSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail);
	/**
	 * 删除备件领用单明细的集合
	 * @param spareBorrowDetails
	 */
	@Transactional
	void deleteAllSpareBorrowDetails(List<SpareBorrowDetail> spareBorrowDetails);
	/**
	 * 把备件台帐的信息copy到备件领用单明细中
	 */
	@Transactional
	void spareAccountCopyToSpareBorrow(SpareBorrow spareBorrow,String spareAccountids);
	/**
	 * 保存备件台帐copy到备件领用单明细
	 * @param spareBorrow
	 * @param allSpareBorrowNumber
	 * @param allSpareBorrowBillDtlId
	 */
	@Transactional
	void storespareAccountCopyToSpareBorrow(SpareBorrow spareBorrow,String allSpareBorrowNumber,String allSpareBorrowBillDtlId);
	/**
	 * 根据备件领用单明细的状态改变备件领用单的状态
	 */
	void updateSpareBorrowStatusBySpareBorrowDtlStatus(SpareBorrowDetail spareBorrowDetail);
	/**
	 * 
	 * 如果备件领用单明细的状态为已领用 则不能删除 抛出异常
	 * @param dtl
	 * @param spareBorrow
	 * @throws Exception
	 */
	@Transactional
	void deleteSpareBorrowDtl(List<SpareBorrowDetail> dtl,SpareBorrow spareBorrow)throws Exception;
}
