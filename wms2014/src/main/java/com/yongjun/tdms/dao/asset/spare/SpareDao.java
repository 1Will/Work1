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
package com.yongjun.tdms.dao.asset.spare;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;

/**
 * @author qs
 * @version $Id: SpareDao.java 9315 2007-12-13 11:49:50Z mwei $
 */
public interface SpareDao {
	
	List<Spare> loadAllSpares(Long [] spareIds);
	
	List<Spare> loadAllSpares();
	
	Spare loasSpare(Long spareId);
	
	void storeSpare(Spare spare);
	
	void deleteSpare(Spare spare);
	
	void deleteAllSpare(Collection<Spare> spares);
	
	List Query(String[] queryInfo) throws HibernateException;
	
	/**
	 * 根据code获取最大备件编码
	 * @param code
	 * @return
	 */
	String getMaxSpareNoBySpareCode(String code);
	
	/***
	 * 根据备件编码获取相同编码的备件个数
	 * @param spareNo 备件编码
	 * @return Integer 备件个数
	 */
	Integer getSpareNumBySpareNo(String spareNo);
	/***
	 * 根据备件型号获得备件的集合
	 * @param modelSpare
	 * @return List
	 */
	List getSpareCollectionByModelSpares(String modelSpare);
	/***
	 * 根据备件规格获得备件的集合
	 * @param specification
	 * @return List
	 */
	List getSpareCollectionBySpecification(String specification);
	/***
	 * 根据备件型号获得备件的集合
	 * @param modelSpare
	 * @return List
	 */
	List getSpareCollentionByModel(String modelSpare);
	/***
	 * 根据备件规格获得备件集合
	 * @param specification
	 * @return
	 */
	List getSpareCollentionBySpec(String specification);
	
	/**
	 * 根据备件名称和型号获取备件的数量
	 * @param name 备件名称
	 * @param modelSpare  备件型号
	 * @return 匹配备件数量
	 */
	Integer getSpareNumByModelAndName(String name,String modelSpare);
	
	public List<Spare> loadByKey(String keyName, Object keyValue) throws DaoException;
	
	public List<Spare> loadByKeyArry(String[] value) throws DaoException;
	
	/**
	 * 根据备件名称、型号、订货号、生产厂家获取备件
	 * @param spareNo 备件编码
	 * @return 匹配备件
	 */
	public Spare loadBySpareNo(String spareNo);
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
	 * 根据备件名称、型号、订货号、生产厂家获取备件
	 * @param name 备件名称
	 * @param modelSpare  备件型号
	 * @return 匹配备件
	 */
	public Spare loadByNameAndModel(String name,String modelSpecs);
	/**
	 * 更新备件的总库存
	 * @param spareLocation
	 */
	public void updateSpareStocks(SpareLocation spareLocation);
	public Spare loadByFirst();
}
