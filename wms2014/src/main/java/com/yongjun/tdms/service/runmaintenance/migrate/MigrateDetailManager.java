package com.yongjun.tdms.service.runmaintenance.migrate;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;
@Transactional(readOnly=true)
public interface MigrateDetailManager {
	/**
	 * 根据传入的转移明细单ID，获取转移明细单
	 * 
	 * @param MigrateDetailId 转移明细单ID
	 * @return MigrateId 明细单
	 */
	MigrateDetail loadMigrateDetail(Long MigrateDetailId);
	/**
	 * 根据传入的转移明细单ID集合，获取转移明细单集合
	 * 
	 * @param MigrateDetailIds 转移单ID集合
	 * @return List 转移明细单集合
	 */
	List<MigrateDetail> loadAllMigrateDetails(Long[] MigrateDetailIds);
	/**
	 * 获取集合中的转移明细单
	 * 
	 * @return List  转移明细单集合
	 */
	List<MigrateDetail> loadAllMigrateDetail();
	/**
	 * 保存转移明细单
	 * 
	 * @param migrateDetail 转移明细单实体
	 * @return
	 */
	void storeMigrateDetail(MigrateDetail migrateDetail);
	/**
	 * 删除转移明细单
	 * 
	 * @param migrateDetail 转移明细单实体
	 * @return
	 */
	
	@Transactional
	void deleteMigrateDetail(MigrateDetail migrateDetail);
	/**
	 * 根据传入的转移明细单集合，删除集合中的转移明细单
	 * 
	 * @param MigrateDetails 转移明细单集合
	 * @return
	 */
	@Transactional
	void deleteAllMigrateDetails(Collection<MigrateDetail> MigrateDetails);
	/**
	 * 根据传入的新添加的设备的id,保存到转移明细中,作为相应转移单的明细
	 * @param intactness 转移单
	 * @param newDeviceIds 设备ID
	 */
	@Transactional
	void storemigrateDetail(Migrate migrate, String newDeviceIds);
	@Transactional
	void storeMigrateDetail(String allMigrateDeviceId,String allDeviceMigratenewManager, 
			String migrateDeviceNewPlace
			);
}
