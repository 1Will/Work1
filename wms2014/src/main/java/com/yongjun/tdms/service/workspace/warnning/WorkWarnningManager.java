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
package com.yongjun.tdms.service.workspace.warnning;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;


/**
 * <p>Title: WorkWarnning
 * <p>Description: 我的提醒业务处理接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface WorkWarnningManager {
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
	@Transactional
	void storeWorkWarnning(WorkWarnning workWarnning);
	
	/**
	 * 删除我的提醒
	 * @param workWarnning 我的提醒
	 */
	@Transactional
	void deleteWorkWarnning(WorkWarnning workWarnning);
	
	/**
	 * 根据传入的我的提醒集合,删除集合我的提醒
	 * @param workWarnnings 我的提醒集合
	 */
	@Transactional
	void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings);
	
	/**
	 * 根据传入的用户ID,获取用户未读提醒的个数
	 * @param userId 用户ID
	 * @return int 未读提醒的个数
	 */
	Integer GetNumberOfUnReadWarnningByUserID(Long userId);
	
	/**
	 * 根据传入的我的提醒集合,置集合中的提醒为已读
	 * @param workWarnnings 我的提醒集合
	 */
	@Transactional
	void readAllWorkWarnnings(Collection<WorkWarnning> workWarnnings);
	
	/**
	 * 根据传入的我的提醒集合,置集合中的提醒为未读
	 * @param workWarnnings 我的提醒集合
	 */
	@Transactional
	void unReadAllWorkWarnnings(Collection<WorkWarnning> workWarnnings);
	
	/**
	 * 获取设备的提醒人
	 * @param device  设备
	 * @param container  容器,存放提醒人
	 * @return  提醒人集合
	 */
	List<User> getWarnningReceiverForDevice(DeviceCard device, List<User> container); 
	
	
	/**
	 * 发送提醒消息
	 * @param warnningPeoples  需要的发送消息的提醒人
	 * @param warnningType      提醒类型
	 * @param warnningContent   提醒内容
	 */
	@Transactional
	void sendWarnningMessage(List<User> warnningPeoples, String warnningType, String warnningContent);
	/**
	 * 根据组id查询组的所有用户  
	 * @param groupid
	 * @return
	 */
	List<Long> loadUsersByGroup(long groupid);

}
