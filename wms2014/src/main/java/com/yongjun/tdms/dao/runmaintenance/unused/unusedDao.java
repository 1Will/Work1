
package com.yongjun.tdms.dao.runmaintenance.unused;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.runmaintenance.unused.UnUsed;

public interface unusedDao {
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
	void storeUnUsed(UnUsed unUsed);
	/**
	 * 删除闲置单
	 * 
	 * @param unUsed 闲置单实体
	 * @return
	 */
	void deleteUnUsed(UnUsed unUsed);
	/**
	 * 根据传入的闲置单集合，删除集合中的闲置单
	 * 
	 * @param UnUseds 闲置单集合
	 * @return
	 */

	void deleteAllUnUseds(Collection<UnUsed> UnUseds);
	List Query(String[] queryInfo) throws HibernateException;
}
