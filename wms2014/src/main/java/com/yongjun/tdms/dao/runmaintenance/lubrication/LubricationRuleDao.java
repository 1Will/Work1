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
package com.yongjun.tdms.dao.runmaintenance.lubrication;

import java.util.List;

import com.yongjun.tdms.model.runmaintenance.lubrication.LubricationRule;

/**
 * <p>Title: LubricationRuleDao
 * <p>Description: 润滑标准数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: LubricationRuleDao.java 9886 2007-12-28 03:03:41Z qsun $
 */
public interface LubricationRuleDao {
	/**
	 * 根据传入的润滑标准对象,存储润滑标准对象
	 * 
	 * @param alteration 润滑标准对象
	 * @return 
	 */	
	void storeLubricationRule(LubricationRule lubricationRule);
	
	/**
	 * 根据传入的润滑标准的Id,获取润滑标准对象
	 * 
	 * @param id 润滑标准Id号
	 * @return  润滑标准对象
	 */
	LubricationRule loadLubricationRule(Long id);
	
	/**
	 * 根据传入的润滑标准对象的集合,删除润滑标准对象集合
	 * 
	 * @param list 润滑标准对象集合
	 * @return  
	 */
	void deleteAllLubricationRule(List<LubricationRule> list);
	
	/**
	 * 根据传入的润滑标准对象Id的数组,获取润滑标准对象集合
	 * 
	 * @param lubricationRuleIds 润滑标准对象Id的数组
	 * @return  工装变动对象集合
	 */	
	List<LubricationRule> loadAllLubricationRule(Long[] lubricationRuleIds);
	
	/**
	 * 获取全部的润滑标准对象集合
	 * 
	 * @param 
	 * @return  润滑标准对象集合
	 */
	List<LubricationRule> loadAllLubricationRule();
}
