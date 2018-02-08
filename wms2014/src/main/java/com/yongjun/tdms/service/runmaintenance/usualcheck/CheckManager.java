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
package com.yongjun.tdms.service.runmaintenance.usualcheck;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;

/**
 * <p>Title: CheckManager
 * <p>Description: 日常检查业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 */
@Transactional(readOnly=true)
public interface CheckManager {
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
	@Transactional
	void storeCheck(Check check);
	
	/**
	 * s传入的日常检查对象
	 * @param check
	 */
	@Transactional
	void deleteCheck(Check check);
	
	/**
	 * 删除传入的日常检查集合
	 * @param checks 日常检查集合
	 */
	@Transactional
	void deleteAllChecks(Collection<Check> checks);
	
	/**
	 * 失效传入的日常检查集合
	 * @param checks 日常检查集合
	 */
	@Transactional
	void disabledAllChecks(Collection<Check> checks)throws Exception;
	
	/**
	 * 有效传入的日常检查集合
	 * @param checks 日常检查集合
	 */
	@Transactional
	void enabledAllChecks(Collection<Check> checks);
	/**
	 * 改变日常检查单的对象
	 * 
	 */
	void chooseUausalCheckStatusEnrol(Check check);
	
	void chooseUausalCheckStatusunEnrol(Check check);
	
	
}
