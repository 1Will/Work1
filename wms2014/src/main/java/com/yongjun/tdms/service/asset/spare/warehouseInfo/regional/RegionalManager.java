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
package com.yongjun.tdms.service.asset.spare.warehouseInfo.regional;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.asset.spare.warehouseInfo.regional.Regional;

/**
 * <p>
 * Title:RegionalManager
 * <p>
 * Description:RegionalManager
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author bxchen@yj-technology.com
 * @version 1.0 
 */
@Transactional(readOnly=true)
public interface RegionalManager {
	/**
	 * 加载所有库区集合
	 * @return
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	List<Regional> loadAllRegionals();
	/**
	 * 根据库区的ID集合，获得库区的集合
	 * @param regionalIds
	 * @return
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	List<Regional> loadAllRegionals(Long [] regionalIds);
	
	/**
	 * 根据库区的ID，获得库区
	 * @param regionalId
	 * @return
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	Regional loadRegionals(Long regionalId);

	/**
	 * 保存库区实体
	 * @param regional
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void storeRegional(Regional regional);

	/**
	 * 删除库区实体
	 * @param regional
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void deleteRegional(Regional regional);

	/**
	 * 删除库区实体集合
	 * @param regionalIds
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void deleteAllRegional(Collection<Regional> regionals);
	/**
	 * 失效库区实体集合
	 * @param regional
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void disabledAllRegional(Collection<Regional> regional);
	
	/**
	 * 有效库区实体集合
	 * @param regional
	 * @author bxchen@yj-technology.com
	 * @since 1.0
	 */
	@Transactional
	void enabledAllRegional(Collection<Regional> regional);
	
	/**
	 * @function 根据仓库标识查询其包含的库区
	 * @author wliu
	 * @param warehouseId 仓库标识
	 * @param flag 标识查询/维护
	 * @return
	 */
	public List<Regional> loadRegionalByWareId(String warehouseId,String flag) throws DaoException;

	/**
	 * 根据非主键查询实体类
	 * @param keyName 非主键标识
	 * @param keyValue 非主键值
	 * @return 实体类集合
	 */
	public List<Regional> loadByKey(String keyName, Object keyValue) throws DaoException;
	
	/**
	 * 根据非主键集合查询实体类
	 * @param keyNamess 非主键标识集合
	 * @param keyValues 非主键值集合
	 * @return 实体类集合
	 */
	public List<Regional> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException;
}
