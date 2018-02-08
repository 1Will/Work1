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
package com.yongjun.tdms.dao.base.workType;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.base.workType.WorkType;


/**
 * <p>Title: PreRepairRuleDao
 * <p>Description: 工种数据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: PreRepairRuleDao.java 9692 2007-12-25 01:49:03Z zbzhang $
 */
public interface WorkTypeDao {
	/**
	 * 根据传入工种ID,获取工种
	 * @param workTypeId 工种ID
	 * @return WorkType 工种
	 */
	WorkType loadWorkType(Long workTypeId);
	
	/**
	 * 根据传入工种ID集合,获取集合工种
	 * @param preRepairRuleIds 工种ID集合
	 * @return List 工种集合
	 */
	List<WorkType> loadAllWorkTypes(Long [] workTypeIds);
	
	/**
	 * 获取集合工种
	 * @return List 工种集合
	 */
	List<WorkType> loadAllWorkTypes();
	
	/**
	 * 保存工种
	 * @param workType 工种
	 */
	void storeWorkType(WorkType workType);
	
	/**
	 * 删除工种
	 * @param workType 工种
	 */
	void deleteWorkType(WorkType workType);
	
	/**
	 * 根据传入工种集合，删除集合工种
	 * @param preRepairRules 工种集合
	 */
	void deleteAllWorkTypes(Collection<WorkType> workTypes);
}
