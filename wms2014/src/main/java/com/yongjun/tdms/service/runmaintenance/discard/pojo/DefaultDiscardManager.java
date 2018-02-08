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
package com.yongjun.tdms.service.runmaintenance.discard.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.discard.DiscardDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.discard.DiscardManager;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * <p>Title: DefaultToolingDiscardManager
 * <p>Description: 工装报废业务接口实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultDiscardManager.java 8982 2007-12-04 10:00:30Z wzou $
 * @see com.yongjun.tdms.service.runmaintenance.tooling.discard.ToolingDiscardManager
 */
public class DefaultDiscardManager implements DiscardManager{
	private final DiscardDao discardDao;
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final SequenceManager sequenceManager;
	
	public DefaultDiscardManager(DiscardDao discardDao,
			DeviceCardManager deviceCardManager,
            CodeValueManager codeValueManager,
            SequenceManager sequenceManager
			){
		this.discardDao = discardDao;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.sequenceManager=sequenceManager;
	}
	

	public List<Discard> loadAllDiscards(Long[] discardIds) {
		return this.discardDao.loadAllDiscards(discardIds);
	}

	public void deleteAllDiscard(Collection<Discard> discards) throws WFJobInProcException {
		for (Iterator iter = discards.iterator(); iter.hasNext(); ) {
			Discard discard = (Discard)iter.next();
			DeviceCard tooling =this.deviceCardManager.loadDevice(discard.getTooling().getId());
			if(null!=tooling){
				tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
				this.deviceCardManager.storeTooling(tooling);
			}
		}
		this.discardDao.deleteAllDiscard(discards);
	}


	public Discard loadDiscard(Long discardId) {
		return this.discardDao.loadDiscard(discardId);
	}

	public void storeDiscard(Discard discard) {
		if(discard.isNew()){
			String discardNo = (String)sequenceManager.generate("-");
			discard.setDiscardNo(discardNo);
		}
		this.discardDao.storeDiscard(discard);
	}
	public Discard getToolingDiscardByToolingId(final Long toolingId) {
		return this.discardDao.getToolingDiscardByToolingId(toolingId);
	}


	public Discard getDeviceDiscardByToolingId(final Long deviceId) {
		return this.discardDao.getDeviceDiscardByToolingId(deviceId);
	}


	public void disabledAllDiscards(Collection<Discard> DiscardBills) {
		for(Discard oil:DiscardBills){
			oil.setDisabled(true);
			discardDao.storeDiscard(oil);
			if(oil.getTooling()!=null){
				oil.getTooling().setToolingStatus(codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
				deviceCardManager.storeTooling(oil.getTooling());
			}
			
		}
		
	}


	public void enabledAllDiscards(Collection<Discard> DiscardBills) {
		for(Discard oil:DiscardBills){
			oil.setDisabled(false);
			discardDao.storeDiscard(oil);
		}
		
	}
}
