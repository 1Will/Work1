/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.asset.spare;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.QueryUnMatchSpare;
import com.yongjun.tdms.model.asset.spare.Spare;

/**
 * @author qs
 * @version $Id: SpareManager.java 11295 2008-03-12 12:27:38Z wzou $
 */
 
public interface SpareManager {
	
	/**
	 * 根据备件的ID数组号，加载所有的备件
	 * 
	 * @param spareIds 备件的ID数组号
	 * @return 备件List集合
	 */
	List<Spare> loadAllSpares(Long [] spareIds);
	
	/**
	 * 根据备件的ID数组号，加载所有的备件
	 * 
	 * @param spareIds 备件的ID数组号
	 * @return 备件List集合
	 */
	Spare loasSpare(Long spareId);
	
	/**
	 * 查询不配备的备件
	 * 
	 * @param 
	 * @return 备件QueryUnMatchSpare集合
	 */
	List<QueryUnMatchSpare> queryUnMatchSpare();
	
	/**
	 * 失效集合中所有的备件
	 * 
	 * @param spare备件的集合，flag是否失效标志
	 * @return 
	 */
	@Transactional
	void  invalidateSpare(List<Spare> spares);
	
	/**
	 * 有效传入的备件检查集合
	 * @param spare备件的集合
	 */
	@Transactional
	void enabledAllSpare(Collection<Spare> spares) throws Exception;
	
	/**
	 * 存储备件
	 * 
	 * @param spare备件
	 * @return 
	 */
	@Transactional
	void storeSpare(Spare spare);
	
	/**
	 * 根据传递的备件值的数组，存储备件
	 * 
	 * @param spare备件，备件值的数组
	 * @return 
	 */
	@Transactional
	void storeSpare(Spare spare,String spareLogValue);
	
	/**
	 * 根据传递的备件值的数组
	 * 
	 * @param strValue备件相关值的数组
	 * @return 
	 */
	@Transactional
	void storeSpare(String strValue);
	
	/**
	 * 根据传递的备件值的数组和相关信息，设置备件的相关值
	 * 
	 * @param strValue备件相关值的数组
	 * @return 
	 */
	@Transactional
	void storeSpare(String strValue,String sysLogValue);
	
	/**
	 * 删除备件
	 * 
	 * @param spare备件实体
	 * @return 
	 */
	@Transactional
	void deleteSpare(Spare spare);
	
	/**
	 * 删除备件
	 * 
	 * @param spare备件实体集合
	 * @return 
	 */
	@Transactional
	void deleteAllSpare(Collection<Spare> spares);
	
	/**
	 * 根据相关参数查询备件
	 * 
	 * @param queryInfo查询条件数组
	 * @return 备件集合
	 */
	@Transactional
	List<Spare> Query(String[] queryInfo)throws HibernateException;
	/**
	 * 根据备件型号获得备件的集合
	 * @param modelSpare
	 * @return
	 */
	@Transactional
	List getSpareCollectionByModelSpares(String modelSpare)throws Exception;
	/**
	 * 根据备件规格获得备件的集合
	 * @param specification
	 * @return
	 */
	@Transactional
	List getSpareCollectionBySpecification(String specification)throws Exception;
	/***
	 * 根据备件型号获得备件的集合
	 * @param modelSpare
	 * @return list
	 */
	@Transactional
	List getSpareCollentionByModel(String modelSpare)throws Exception;
	/**
	 * 根据备件规格获得备件的集合
	 * @param specification
	 * @return
	 */
	@Transactional
	List getSpareCollentionBySpec(String specification)throws Exception;
	
	/**
	 * 根据备件名称和型号获取备件的数量
	 * @param name 备件名称
	 * @param modelSpare  备件型号
	 * @return 匹配备件数量
	 */
	Integer getSpareNumByModelAndName(String name,String modelSpare);
	
	  public List<Spare> loadByKey(String key, Object value) throws DaoException;
	  public Spare loadBySpareNo(String SpareNo);
	  
	  public List<Spare> loadByKeyArry(String[] value) throws DaoException;
		
	  /**
	   * 创建台账
	   * @param id
	   * @return
	   */
	  @Transactional
	  public String createGraphNo(String[] ary);
	  
  /**
	 * 根据备件名称、型号、订货号、生产厂家获取备件
	 * @param name 备件名称
	 * @param modelSpare  备件型号
	 * @param orderNo  订货号
	 * @param factoryName  生产厂家
	 * @return 匹配备件
	 */
	public Spare loadByPamrameter(String name,String modelSpecs,String orderNo,String factoryName);
	
	
	/**
	 * 查找备件 zzb
	 * @param keyName
	 * @param keyValue
	 * @return
	 */
	public Spare listSpareByArry(String[] keyName);
	 
	
}
