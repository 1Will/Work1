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
package com.yongjun.tdms.dao.runmaintenance.alteration;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;

/**
 * <p>Title: AlterationDao
 * <p>Description: 资产变动数据访问接口定义类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author mwei@yj-technology.com
 * @version $Id: AlterationDao.java 9514 2007-12-19 10:12:05Z mwei $
 */
public interface AlterationDao {
	
	/**
	 * 根据传入的工装变动对象,存储工装变动对象
	 * 
	 * @param alteration 工装变动对象
	 * @return 
	 */	
	void storeAlteration(Alteration alteration);
	
	/**
	 * 根据传入的工装变动的Id,获取工装变动对象
	 * 
	 * @param id 工装变动Id号
	 * @return  工装变动对象
	 */
	Alteration loadAlteration(Long id);
	
	/**
	 * 根据传入的工装变动对象的集合,删除工装变动对象集合
	 * 
	 * @param list 工装变动对象集合
	 * @return  
	 */
	void deleteAllAlteration(List<Alteration> list);
	
	/**
	 * 根据传入的工装变动对象Id的数组,获取工装变动对象集合
	 * 
	 * @param AlterationIds 工装变动对象Id的数组
	 * @return  工装变动对象集合
	 */	
	List<Alteration> loadAllAlteration(Long[] AlterationIds);
	
	/**
	 * 获取全部的工装变动对象集合
	 * 
	 * @param 
	 * @return  工装变动对象集合
	 */
	List<Alteration> loadAllAlteration();
}
