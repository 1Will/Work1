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
package com.yongjun.tdms.dao.runmaintenance.usualcheck;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;

/**
 * <p>Title: CheckDao
 * <p>Description: 日常检查数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: $
 */
public interface CheckDao {
	
	/**
	 * 根据传入的日常检查的ID,获取日常检查对象
	 * @param checkId 日常检查的ID
	 * @return Check 日常检查对象
	 */
	Check loadCheck(Long checkId);
	
	/**
	 * 根据传入的日常检查的ID集合，获取日常检查对象集合
	 * @param checkIds 日常检查的ID集合
	 * @return List 日常检查对象集合
	 */
	List<Check> loadAllChecks(Long [] checkIds);
	
	/**
	 * 获取日常检查对象集合
	 * @return List 日常检查对象集合
	 */
	List<Check> loadAllChecks();
	
	/**
	 * 保存传入的日常检查对象
	 * @param check 日常检查对象
	 */
	void storeCheck(Check check);
	
	/**
	 * s传入的日常检查对象
	 * @param check
	 */
	void deleteCheck(Check check);
	
	/**
	 * 删除传入的日常检查集合
	 * @param checks 日常检查集合
	 */
	void deleteAllChecks(Collection<Check> checks);

}
