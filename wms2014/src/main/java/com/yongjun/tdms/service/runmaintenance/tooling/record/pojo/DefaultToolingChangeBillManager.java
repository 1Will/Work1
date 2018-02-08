/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.runmaintenance.tooling.record.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingChangeBillDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingChangeBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager;

/**
 * <p>Title: DefaultToolingChangeBillManager
 * <p>Description: 工装变更单管理业务实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.service.runmaintenance.tooling.record.ToolingChangeBillManager
 */
public class DefaultToolingChangeBillManager implements
		ToolingChangeBillManager {

	private final ToolingChangeBillDao toolingChangeBillDao;
	private final SequenceManager sequenceManager;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	
	public DefaultToolingChangeBillManager(ToolingChangeBillDao toolingChangeBillDao,
			                   SequenceManager sequenceManager, 
			                   DeviceCardManager deviceCardManager,
			                   CodeValueManager codeValueManager) {
		this.toolingChangeBillDao = toolingChangeBillDao;
		this.sequenceManager = sequenceManager;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
	}
	public ToolingChangeBill loadToolingChangeBill(Long toolingChangeBillId) {
		return this.toolingChangeBillDao.loadToolingChangeBill(toolingChangeBillId);
	}

	public List<ToolingChangeBill> loadAllToolingChangeBills(
			Long[] toolingChangeBillIds) {
		return this.toolingChangeBillDao.loadAllToolingChangeBills(toolingChangeBillIds);
	}

	public List<ToolingChangeBill> loadAllToolingChangeBills() {
		return this.toolingChangeBillDao.loadAllToolingChangeBills();
	}

	public void storeToolingChangeBill(ToolingChangeBill toolingChangeBill, DeviceCard tooling) {
		if (toolingChangeBill.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			toolingChangeBill.setBillNo(billNo);
		}
		this.deviceCardManager.storeTooling(tooling);
		this.toolingChangeBillDao.storeToolingChangeBill(toolingChangeBill);
	}

	public void deleteToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.toolingChangeBillDao.deleteToolingChangeBill(toolingChangeBill);
	}

	public void deleteAllToolingChangeBills(
			Collection<ToolingChangeBill> toolingChangeBills) {
		for (Iterator it = toolingChangeBills.iterator(); it.hasNext();) {
			ToolingChangeBill toolingChangeBill = (ToolingChangeBill)it.next();
			DeviceCard tooling = this.deviceCardManager.loadDevice(toolingChangeBill.getTooling().getId());
			if (null != tooling) {
				tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
			    this.deviceCardManager.storeTooling(tooling);
			}
		}
		this.toolingChangeBillDao.deleteAllToolingChangeBills(toolingChangeBills);
	}
	
	public void storeToolingChangeBill(ToolingChangeBill toolingChangeBill) {
		this.toolingChangeBillDao.storeToolingChangeBill(toolingChangeBill);
	}
	
	public void disabledAllToolingChangeBills(Collection<ToolingChangeBill> toolingChangeBills) {
		for (ToolingChangeBill changeBill : toolingChangeBills) {
			changeBill.setDisabled(true);
			this.toolingChangeBillDao.storeToolingChangeBill(changeBill);
			setNormalToolingStatusOfChangeBill(changeBill);
		}
	}
	
	public void enabledAllToolingChangeBills(Collection<ToolingChangeBill> toolingChangeBills) {
		for (ToolingChangeBill changeBill : toolingChangeBills) {
			changeBill.setDisabled(false);
			this.toolingChangeBillDao.storeToolingChangeBill(changeBill);
			setRepairToolingStatusOfChangeBill(changeBill);
		}
	}
	
	private void setNormalToolingStatusOfChangeBill(ToolingChangeBill changeBill) {
		if (!changeBill.isChangeBillFlag()) {      //如果工装变更单为变更中，则改变工装为待用
			//获取工装变更单关联的工装
			DeviceCard tooling = this.deviceCardManager.loadDevice(changeBill.getTooling().getId());
			//改变工装的状态为待用状态
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
			this.deviceCardManager.storeTooling(tooling);
		}
	}
	
	private void setRepairToolingStatusOfChangeBill(ToolingChangeBill changeBill) {
		if (!changeBill.isChangeBillFlag()) {      //如果工装变更单为变更中，则改变工装为整修
			//获取工装变更单关联的工装
			DeviceCard tooling = this.deviceCardManager.loadDevice(changeBill.getTooling().getId());
			//改变工装的状态为整修状态
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_REPAIR_STATUS));
			this.deviceCardManager.storeTooling(tooling);
		}
	}

}
