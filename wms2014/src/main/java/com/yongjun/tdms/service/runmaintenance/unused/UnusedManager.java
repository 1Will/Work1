package com.yongjun.tdms.service.runmaintenance.unused;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.unused.UnUsed;
@Transactional(readOnly=true)
public interface UnusedManager{

	/**
	 * 根据传入的闲置单ID，获取闲置单
	 * 
	 * @param UnUsedId 闲置单IID
	 * @return UnUsed 闲置单
	 */
	UnUsed loadUnUsed(Long UnUsedId);
	/**
	 * 根据传入的闲置单ID集合，获取闲置单集合
	 * 
	 * @param UnUsedIds 闲置单ID集合
	 * @return List 闲置单集合
	 */

	List<UnUsed> loadUnUseds(Long[] UnUsedIds);
	/**
	 * 获取集合中的闲置单
	 * 
	 * @return List  闲置单集合
	 */
	List<UnUsed> loadUnUseds();
	/**
	 * 保存闲置单
	 * 
	 * @param unUsed 闲置单实体
	 * @return
	 */
	@Transactional
	void storeUnUsed(UnUsed unUsed);
	/**
	 * 删除闲置单
	 * 
	 * @param unUsed 闲置单实体
	 * @return
	 */
	@Transactional
	void deleteUnUsed(UnUsed unUsed);
	/**
	 * 根据传入的闲置单集合，删除集合中的闲置单
	 * 
	 * @param UnUseds 闲置单集合
	 * @return
	 */
	@Transactional
	void deleteAllUnUseds(Collection<UnUsed> UnUseds);
	@Transactional
	void disabledAllUnUseds(Collection<UnUsed> unUseds);
	
	@Transactional
	void enabledAllUnUseds(Collection<UnUsed> unUseds);
	List<UnUsed> Query(String[] queryInfo)throws HibernateException;
	
}