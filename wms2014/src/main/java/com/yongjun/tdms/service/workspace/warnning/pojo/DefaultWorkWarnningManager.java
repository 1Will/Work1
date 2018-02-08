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
package com.yongjun.tdms.service.workspace.warnning.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;


/**
 * <p>Title: EditDailyRepairAction
 * <p>Description: 我的提醒业务处理实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.service.workspace.WorkWarnningManager
 * @version $
 */
public class DefaultWorkWarnningManager implements WorkWarnningManager {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	private final WorkWarnningDao workWarnningDao;
	
	public DefaultWorkWarnningManager(WorkWarnningDao workWarnningDao) {
		this.workWarnningDao = workWarnningDao;
	}
	
	public WorkWarnning loadWorkWarnning(Long workWarnningId) {
		return this.workWarnningDao.loadWorkWarnning(workWarnningId);
	}

	public List<WorkWarnning> loadAllWorkWarnnings(Long[] workWarnningIds) {
		return this.workWarnningDao.loadAllWorkWarnnings(workWarnningIds);
	}

	public List<WorkWarnning> loadAllWorkWarnnings() {
		return this.workWarnningDao.loadAllWorkWarnnings();
	}

	public void storeWorkWarnning(WorkWarnning workWarnning) {
		this.workWarnningDao.storeWorkWarnning(workWarnning);
	}

	public void deleteWorkWarnning(WorkWarnning workWarnning) {
		this.workWarnningDao.deleteWorkWarnning(workWarnning);
	}

	public void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		this.workWarnningDao.deleteAllWorkWarnnings(workWarnnings);
	}

	public Integer GetNumberOfUnReadWarnningByUserID(Long userId) {
		return this.workWarnningDao.GetNumberOfUnReadWarnningByUserID(userId);
	}

	public void readAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		for (WorkWarnning warnning : workWarnnings) {
			//设置提醒状态为已读
			warnning.setReadFlag(true);
			this.workWarnningDao.storeWorkWarnning(warnning);
		}
	}

	public void unReadAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		for (WorkWarnning warnning : workWarnnings) {
			//设置提醒状态为未读
			warnning.setReadFlag(false);
			this.workWarnningDao.storeWorkWarnning(warnning);
		}
	}

	public List<User> getWarnningReceiverForDevice(DeviceCard device, List <User> container) {
		if (null == container) {        //如果存放提醒人的容器为空，则创建一个容器
			container = new ArrayList <User>();
		}
		if (null != device.getManager() && !container.contains(device.getManager())) {     //如果设备的负责人部位空,则往提醒人容器中添加
			container.add(device.getManager());
		}
		return container;
	}

	public void sendWarnningMessage(List<User> warnningPeoples, String warnningType, String warnningContent) {
		if (null != warnningPeoples) {
			//向每个提醒人发送提醒消息
			for (User warnningPeople : warnningPeoples) {
				if (null != warnningType && null != warnningContent) {
					logger.info("start send warnning message for " + warnningPeople.getName());
					WorkWarnning warnning = new WorkWarnning();
					//设置提醒类型
					warnning.setType(warnningType);
					//设置提醒人
					warnning.setWarnedPeople(warnningPeople);
					//设置提醒内容
					warnning.setContent(warnningContent);
					//设置提醒日期
					warnning.setWarnningDate(DateUtil.getSystemDate());
					warnning.setReadFlag(false);
					try {
						this.workWarnningDao.storeWorkWarnning(warnning);
					} catch (Exception e) {
						logger.info("end send warnning message for " + warnningPeople.getName() + " send failure!");
					}
					logger.info("end send warnning message for " + warnningPeople.getName() + " send successful!");
				}
			}
		}
	}
	public List<Long> loadUsersByGroup(long groupid){
		List <Long> users=this.workWarnningDao.loadUsersByGroup(groupid);
		
		return users;
		
	}

}
