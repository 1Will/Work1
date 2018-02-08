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

import com.yongjun.tdms.model.year.tooling.TechAlterDetail;

/**
 * 
 * <p>Title: TechAlterDetailDao
 * <p>Description: 技术改造明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface TechAlterDetailDao {
	/**
	 * 根据传入的技术改造明细ID,获取技术改造明细
	 * @param techAlterDetailId 技术改造明细ID
	 * @return TechAlterDetail 技术改造明细实体
	 */
	TechAlterDetail loadTechAlterDetail(Long techAlterDetailId);
	
	/**
	 * 根据传入的技术改造明细ID集合,获取技术改造明细集合
	 * @param techAlterDetailIds 技术改造明细ID集合
	 * @return List 技术改造明细集合
	 */
	List<TechAlterDetail> loadAllTechAlterDetails(Long [] techAlterDetailIds);
	
	/**
	 * 获取技术改造明细集合
	 * @return List 技术改造明细集合
	 */
	List<TechAlterDetail> loadAllTechAlterDetails();
	
	/**
	 * 保存传入的技术改造明细
	 * @param techAlterDetail 技术改造明细实体
	 */
	void storeTechAlterDetail(TechAlterDetail techAlterDetail);
	
	/**
	 * 删除传入的技术改造明细
	 * @param techAlterDetail 技术改造明细实体
	 */
	void deleteTechAlterDetail(TechAlterDetail techAlterDetail);
	
	/**
	 * 删除传入的集合技术改造明细
	 * @param techAlterDetails 集合技术改造明细
	 */
	void deleteAllTechAlterDetail(Collection<TechAlterDetail> techAlterDetails);
}
