/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.dao.asset.spare.warehouseInfo.regional.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.asset.spare.warehouseInfo.regional.RegionalDao;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;

/**
 * <p>Title: HibernateRegional</p>
 * <p>Description: 库区信息数据访问层接口实现类</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author wliu@yj-technology.com</p>
 * <p>@version $Id: HibernateRegional.java 2010-03-10 wliu $</p>
 */
public class HibernateRegional 
extends BaseHibernateDao
implements RegionalDao{

	/**
	 * 保存当前库区信息
	 * @param regional 库区信息
	 */
	public void storeRegional(Regional regional){
		
		super.store(regional);
	}
	
	/**
	 * 删除当前选定的库区信息
	 * @param regional 库区信息
	 */
	public void deleteRegional(Regional regional){
		
		super.delete(regional);
	}

	/**
	 * 删除多个选定的库区信息
	 * @param regionals 库区信息集合
	 */
	public void deleteAllRegional(Collection<Regional> regionals){
		
		super.deleteAll(regionals);
	}

	/**
	 * 根据多个标识查询多个库区信息
	 * @param regionalIds 库区信息集合
	 * @return 库区信息集合
	 */
	public List<Regional> loadAllRegional(Long[] regionalIds){
		
		return super.loadAll(Regional.class, regionalIds);
	}

	/**
	 * 根据标识查询指定的库区信息
	 * @param regionalId 库区信息标识
	 * @return 库区信息
	 */
	public Regional loadRegional(Long regionalId){
		
		return super.load(Regional.class, regionalId);
	}

	/**
	 * 查询所有库区信息
	 * @return 库区信息集合
	 */
	public List<Regional> loadAllRegional(){
		
		return super.loadAll(Regional.class);
	}

	/**
	 * 根据非主键查询实体类
	 * @param keyName 非主键标识
	 * @param keyValue 非主键值
	 * @return 实体类集合
	 */
	public List<Regional> loadByKey(String keyName, Object keyValue) throws DaoException{
		
		return super.loadByKey(Regional.class, keyName, keyValue);
	}
	
	/**
	 * 根据非主键集合查询实体类
	 * @param keyNamess 非主键标识集合
	 * @param keyValues 非主键值集合
	 * @return 实体类集合
	 */
	public List<Regional> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException{
		
		return super.loadByKeyArray(Regional.class, keyNames, keyValues);
	}
	
}
