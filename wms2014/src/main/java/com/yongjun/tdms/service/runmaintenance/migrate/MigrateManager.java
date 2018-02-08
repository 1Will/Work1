package com.yongjun.tdms.service.runmaintenance.migrate;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
@Transactional(readOnly=true)
public interface MigrateManager {
	/**
	 * 根据传入的转移单ID，获取转移单
	 * 
	 * @param MigrateId 闲置单ID
	 * @return MigrateId 闲置单
	 */
	Migrate loadMigrate(Long MigrateId);
	/**
	 * 根据传入的转移单ID集合，获取转移单集合
	 * 
	 * @param MigrateIds 转移单ID集合
	 * @return List 转移单集合
	 */
	List<Migrate> loadMigrateds(Long[] MigrateIds);
	/**
	 * 获取集合中的转移单
	 * 
	 * @return List  转移单集合
	 */
	List<Migrate> loadMigrates();
	/**
	 * 保存转移单
	 * 
	 * @param migrate 转移单实体
	 * @return
	 */
	@Transactional
	void storeMigrate(Migrate migrate);
	/**
	 * 删除转移单
	 * 
	 * @param migrate 转移单实体
	 * @return
	 */
	@Transactional
	void deleteMigrate(Migrate migrate);
	/**
	 * 根据传入的转移单集合，删除集合中的转移单
	 * 
	 * @param migrates 转移单集合
	 * @return
	 */
	@Transactional
	void deleteAllMigrates(Collection<Migrate> Migrates);
	@Transactional
	void disabledAllMigrates(Collection<Migrate> migrates);
	
	@Transactional
	void enabledAllMigrates(Collection<Migrate> migrates);
}

