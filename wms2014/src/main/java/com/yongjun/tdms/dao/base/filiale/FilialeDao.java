/**
 * 
 */
package com.yongjun.tdms.dao.base.filiale;

import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;

/**
 *<p>Title:FilialeDao.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public interface FilialeDao {
	/**
	 * 存储Filiale
	 * @param filiale
	 */
	void storeFiliale(Filiale filiale);
	/**
	 * 根据ID加载Filiale
	 * @param filialeId
	 * @return
	 */
	Filiale loadFiliale(Long filialeId);
	/**
	 * 记载所有的Filiale
	 * @return
	 */
	List<Filiale> loadAllFiliale(Long[] filialeIds);
	/**
	 * 删除Filiale
	 * @param filialeId
	 */
	void deleteFiliale(Filiale filiale);
	/**
	 * 删除所有Filiale
	 * @param filiales
	 */
	void deleteAllFiliale(java.util.Collection<Filiale> filiales);
	/**
	 * 加载某分公司所有的部门
	 * 
	 * 该方法无用
	 * @return
	 */
	java.util.List<Department> loadAllDepartment();
	/**
	 * 加载所有分公司
	 * @return
	 */
	java.util.List<Filiale> loadAllFiliale();
}
