/*
 * Copyright (c) 2001-2009 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.service.CustomerRelationship.additionalInfo.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.CustomerRelationship.additionalInfo.AdditionalInfoDao;
import com.yongjun.tdms.model.CustomerRelationship.additionalInfo.AdditionalInfomation;
import com.yongjun.tdms.service.CustomerRelationship.additionalInfo.AdditionalInfoManager;

/**
 * <p>Title: DefaultAdditionalInfoManager</p>
 * <p>Description: 附加信息业务接口实现类</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author wliu@yj-technology.com</p>
 * <p>@version $Id: DefaultAdditionalInfoManager.java 2009-12-8 8:44:03Z wliu $</p>
 */
public class DefaultAdditionalInfoManager
extends BaseManager 
implements AdditionalInfoManager {

	//附加信息数据访问层接口
	private final AdditionalInfoDao additionalInfoDao;
	
	/**
	 * @function 附加信息业务接口实现类构造函数
	 * @param additionalInfoDao 附加信息数据访问层接口
	 */
	public DefaultAdditionalInfoManager(AdditionalInfoDao additionalInfoDao) {
		this.additionalInfoDao = additionalInfoDao;
	}
	
	/**
	 * @function 修改/保存附加信息
	 * @param ai 需要修改/保存的附加信息实体类
	 */
	public void storeAdditionalInfo(AdditionalInfomation ai){
		
		this.additionalInfoDao.storeAdditionalInfo(ai);
	}
	
	/**
	 * @function 根据附加标识查询指定附加信息
	 * @param aiId 附加信息标识
	 * @return 附加信息实体类
	 */
	public AdditionalInfomation loadAdditionalInfo(Long aiId){
		
		return this.additionalInfoDao.loadAdditionalInfo(aiId);
	}
	
	/**
	 * @function 根据附加标识集合查询指定附加信息集合
	 * @return 附加信息实体类集合
	 */
	public List<AdditionalInfomation> loadAllAdditionalInfo(){
		
		return this.additionalInfoDao.loadAllAdditionalInfo();
	}
	
	/**
	 * @function 根据附加标识集合查询指定附加信息集合
	 * @param aiIds 附加信息标识集合
	 * @return 附加信息实体类集合
	 */
	public List<AdditionalInfomation> loadAllAdditionalInfo(Long[] aiIds){
		
		return this.additionalInfoDao.loadAllAdditionalInfo(aiIds);
	}
	
	/**
	 * @function 根据标识删除指定的附加信息
	 * @param ai 需要删除的附加信息实体类
	 */
	public void deleteAdditionalInfo(AdditionalInfomation ai){
		
		this.additionalInfoDao.deleteAdditionalInfo(ai);
	}
	
	/**
	 * @function 根据标识集合删除指定的附加信息集合
	 * @param aiId 附加信息标识集合
	 */
	public void deleteAllAdditionalInfo(List<AdditionalInfomation> aiIds){
		
		this.additionalInfoDao.deleteAllAdditionalInfo(aiIds);
	}
	
	/**
	 * @function 根据非主键列查询附加信息
	 * @param key 列键
	 * @param value 列值
	 * @return 附加信息集合
	 */
	public List<AdditionalInfomation> loadByKey(String key, Object value) throws DaoException{
		
		return this.additionalInfoDao.loadByKey(key, value);
	}
	
	
}
