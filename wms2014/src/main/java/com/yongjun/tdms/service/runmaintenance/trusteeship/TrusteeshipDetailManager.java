package com.yongjun.tdms.service.runmaintenance.trusteeship;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.tdms.model.runmaintenance.trusteeship.TrusteeshipDetail;
@Transactional(readOnly=true)
public interface TrusteeshipDetailManager {
	/**
	 * 根据传入的托管明细ID，获取托管明细
	 * 
	 * @param TrusteeshipDetailId 托管明细ID
	 * @return TrusteeshipDetail 托管明细
	 */
	TrusteeshipDetail loadTrusteeshipDetail(Long TrusteeshipDetailId);
	/**
	 * 根据传入的托管明细ID集合，获取托管明细集合
	 * 
	 * @param TrusteeshipDetailIds 托管明细ID集合
	 * @return List 托管明细集合
	 */
	List<TrusteeshipDetail> loadAllTrusteeshipDetails(Long[] TrusteeshipDetailIds);
	/**
	 * 获取集合中的托管明细
	 * 
	 * @return List  托管明细集合
	 */
	List<TrusteeshipDetail> loadTrusteeshipDetails();
	/**
	 * 保存托管明细
	 * 
	 * @param trusteeshipDetail 托管明细实体
	 * @return
	 */
	void storeTrusteeshipDetail(TrusteeshipDetail trusteeshipDetail);
	/**
	 * 删除托管明细
	 * 
	 * @param trusteeshipDetail 托管明细单实体
	 * @return
	 */
	@Transactional
	void deleteTrusteeshipDetail(TrusteeshipDetail trusteeshipDetail);
	/**
	 * 根据传入的托管明细集合，删除集合中的托管单的明细
	 * 
	 * @param trusteeshipDetails 托管单明细集合
	 * @return
	 */
	@Transactional
	void deleteAllTrusteeshipDetails(Collection<TrusteeshipDetail> trusteeshipDetails);
	
	
	@Transactional
	void storeTrusteeshipDetail(Trusteeship trusteeship, String newDeviceIds);
	
}
