package com.yongjun.tdms.service.base.filiale;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.model.security.ProductionLine;

/**
 * <p>Title: FilialeManager
 * <p>Description: 分公司管理业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mfzhang@yj-technology.com
 * @version 
 */
@Transactional(readOnly = true)
public interface FilialeManager {
	/**
	 * 存储Filiale
	 * @param filiale
	 */
	@Transactional
	public void storeFiliale(Filiale filiale);
	/**
	 * 根据ID加载Filiale
	 * @param filialeId
	 * @return
	 */
	public Filiale loadFiliale(Long filialeId);
	/**
	 * 记载所有的Filiale
	 * @return
	 */
	public List<Filiale> loadAllFiliale(Long[] filialeIds);
	/**
	 * 删除Filiale
	 * @param filialeId
	 */
	@Transactional
	public void deleteFiliale(Filiale filiale);
	/**
	 * 删除所有Filiale
	 * @param filiales
	 */
	@Transactional
	public void deleteAllFiliale(java.util.Collection<Filiale> filiales);
	/**
	 * 加载某分公司所有的部门
	 * 
	 * 该方法无用
	 * @return
	 */
	public java.util.List<Department> loadAllDepartment();

	/**
	 * 记载所有的部门
	 * @return
	 */
	public java.util.List<Filiale> loadAllFiliale();

	/**
	 * 分公司失效
	 * @return
	 */
	@Transactional
	public abstract void disableAllFiliales(List<Filiale> filiales);
	/**
	 * 分公司有效
	 * @return
	 */
	@Transactional
	void enabledAllFiliales(Collection<Filiale> filiales);

	/**
	 * 创建可供选择的分公司集合
	 * @param name		第一条记录的名称，如 "所有" 或者 ""
	 * @return	List	可选择的分公司集合
	 */
	public List createSelectFilailes(String name);
}
