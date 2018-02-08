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
package com.yongjun.tdms.dao.runmaintenance.wash;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.wash.Wash;

/**
 * <p>Title: WashDao
 * <p>Description: 清洗数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public interface WashDao {
	/**
	 * 根据传入的清洗计划或实施ID，获取清洗对象
	 * @param washId 清洗计划或实施ID
	 * @return Wash 清洗对象
	 */
	Wash loadWash(Long washId);
	
	/**
	 * 根据传入的清洗计划或实施ID集合，获取集合清洗对象
	 * @param washIds 清洗计划或实施ID集合
	 * @return List 清洗对象集合
	 */
	List<Wash> loadAllWashs(Long [] washIds);
	
	/**
	 * 获取集合清洗对象
	 * @return  List 清洗对象集合
	 */
	List<Wash> loadAllWashs();
	
	/**
	 * 保存传入的清洗对象
	 * @param wash 清洗对象
	 */
	void storeWash(Wash wash);
	
	/**
	 * 删除传入的清洗对象
	 * @param wash 清洗对象
	 */
	void deleteWash(Wash wash);
	
	/**
	 * 根据传入的清洗对象集合，删除集合中的清洗对象
	 * @param washs 清洗对象集合
	 */
	void deleteAllWashs(Collection<Wash> washs);
}
