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
package com.yongjun.tdms.dao.year.tooling;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.tooling.ToolingMakeDetail;


/**
 * 
 * <p>Title: YearPlanDao
 * <p>Description: 工装制作详细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface ToolingMakeDetailDao {
	/**
	 * 根据传入的工装制作详细ID,获取工装制作详细
	 * @param toolingMakeDetailId 工装制作详细ID
	 * @return ToolingMakeDetail 工装制作详细实体
	 */
	ToolingMakeDetail loadToolingMakeDetail(Long toolingMakeDetailId);
	
	/**
	 * 根据传入的工装制作详细ID集合,获取集合工装制作详细
	 * @param toolingMakeDetailIds 工装制作详细ID集合
	 * @return List 集合工装制作详细
	 */
	List<ToolingMakeDetail> loadAllToolingMakeDetails(Long [] toolingMakeDetailIds);
	
	/**
	 * 获取集合工装制作详细
	 * @return List 集合工装制作详细
	 */
	List<ToolingMakeDetail> loadAllToolingMakeDetails();
	
	/**
	 * 保存传入的工装制作详细
	 * @param toolingMakeDetail 工装制作详细实体
	 */
	void storeToolingMakeDetail(ToolingMakeDetail toolingMakeDetail);
	
	/**
	 * 删除传入的工装制作详细
	 * @param toolingMakeDetail 工装制作详细
	 */
	void deleteToolingMakeDetail(ToolingMakeDetail toolingMakeDetail);
	
	/**
	 * 删除传入的集合中的工装制作详细
	 * @param toolingMakeDetails 工装制作详细集合
	 */
	void deleteAllToolingMakeDetail(Collection<ToolingMakeDetail> toolingMakeDetails);
	
}
