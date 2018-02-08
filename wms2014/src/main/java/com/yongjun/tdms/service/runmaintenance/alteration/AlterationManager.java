package com.yongjun.tdms.service.runmaintenance.alteration;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;

/**
 * @author wzou
 * @version $Id: AlterationManager.java 11045 2008-02-21 01:16:58Z wzou $
 */

@Transactional(readOnly = true)
public interface AlterationManager {
	@Transactional
	
	/**
	 * 根据封存标志，存储工装变动对象
	 * 
	 * @param alteration对象
	 * @return 
	 */
	void storeAlteration(Alteration alteration);

	/**
	 * 根据Id数组的集合加载所有的工装变动实体
	 * 
	 * @param alterationIds数组
	 * @return 工装变动对象集合
	 */
	List<Alteration> loadAllAlteration(Long[] alterationIds);

	/**
	 * 根据Id加载工装变动实体
	 * 
	 * @param id号
	 * @return 工装变动对象
	 */
	Alteration loadAlteration(Long id);

	@Transactional
	
	/**
	 * 删除所有的工装变动实体集合
	 * 
	 * @param list对象集合
	 * @return 
	 */
	void deleteAllAlteration(List<Alteration> list);
	
	@Transactional
	void disabledAllAlterations(List<Alteration> list);
	
	@Transactional
	void enabledAllAlterations(List<Alteration> list);
}
