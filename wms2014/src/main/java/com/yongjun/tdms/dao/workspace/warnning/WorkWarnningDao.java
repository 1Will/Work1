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
package com.yongjun.tdms.dao.workspace.warnning;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;


/**
 * <p>Title: WorkWarnningDao
 * <p>Description: 我的提醒数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface WorkWarnningDao {
	/**
	 * 根据传入的我的提醒ID,获取我的提醒
	 * @param budgetId 我的提醒ID
	 * @return Budget 我的提醒实体
	 */
	WorkWarnning loadWorkWarnning(Long workWarnningId);
	
	/**
	 * 根据传入的我的提醒ID集合,获取集合我的提醒
	 * @param workWarnningIds 我的提醒ID集合
	 * @return List 集合我的提醒
	 */
	List<WorkWarnning> loadAllWorkWarnnings(Long [] workWarnningIds);
	
	/**
	 * 获取集合我的提醒
	 * @return List 集合我的提醒
	 */
	List<WorkWarnning> loadAllWorkWarnnings();
	
	/**
	 * 保存我的提醒
	 * @param workWarnning 我的提醒
	 */
	void storeWorkWarnning(WorkWarnning workWarnning);
	
	/**
	 * 删除我的提醒
	 * @param workWarnning 我的提醒
	 */
	void deleteWorkWarnning(WorkWarnning workWarnning);
	
	/**
	 * 根据传入的我的提醒集合,删除集合我的提醒
	 * @param workWarnnings 我的提醒集合
	 */
	void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings);
	
	/**
	 * 根据传入的用户ID,获取用户未读提醒的个数
	 * @param userId 用户ID
	 * @return int 未读提醒的个数
	 */
	Integer GetNumberOfUnReadWarnningByUserID(Long userId);
	List<Long> loadUsersByGroup(long groupid);
}
