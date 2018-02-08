package com.yongjun.tdms.dao.runmaintenance.trusteeship;

import java.util.Collection;
import java.util.List;


import com.yongjun.tdms.model.runmaintenance.trusteeship.*;
public interface TrusteeshipDao {
	
	/**
	 * 根据传入的托管单ID，获取托管单
	 * 
	 * @param TrusteeshipId 托管单ID
	 * @return Trusteeship 托管单
	 */
	Trusteeship loadTrusteeship(Long TrusteeshipId);
	/**
	 * 根据传入的转托管单ID集合，获取托管单集合
	 * 
	 * @param TrusteeshipIds 托管单ID集合
	 * @return List 托管单集合
	 */
	List<Trusteeship> loadTrusteeships(Long[] TrusteeshipIds);
	/**
	 * 获取集合中的托管单
	 * 
	 * @return List  托管单集合
	 */
	List<Trusteeship> loadTrusteeships();
	/**
	 * 保存托管单
	 * 
	 * @param migrate 托管单实体
	 * @return
	 */
	void storeTrusteeship(Trusteeship trusteeship);
	/**
	 * 删除托管单
	 * 
	 * @param trusteeship 托管单实体
	 * @return
	 */
	void deleteTrusteeship(Trusteeship trusteeship);
	/**
	 * 根据传入的托管单集合，删除集合中的托管单
	 * 
	 * @param Trusteeships 托管单集合
	 * @return
	 */
	void deleteAllTrusteeships(Collection<Trusteeship> Trusteeships);
}
