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
package com.yongjun.tdms.service.runmaintenance.intactness.pojo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.intactness.IntactnessDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.intactness.IntactnessManager;
/**
 * <p>Title: DefaultIntactnessManager
 * <p>Description: 设备鉴定业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.runmaintenance.intactness.IntactnessManager
 */

public class DefaultIntactnessManager  implements IntactnessManager {
	private final IntactnessDao intactnessDao;	
	private final DeviceCardManager deviceCardManager;
	private final CodeValueManager codeValueManager;
	private final SequenceManager sequenceManager;
	
	public DefaultIntactnessManager(IntactnessDao intactnessDao,
			DeviceCardManager deviceCardManager,
			CodeValueManager codeValueManager,
			SequenceManager sequenceManager) {
		this.intactnessDao = intactnessDao;
		this.deviceCardManager = deviceCardManager;
		this.codeValueManager = codeValueManager;
		this.sequenceManager = sequenceManager;
	}
	
	public Intactness loadIntactness(Long intactnessId) {
		return this.intactnessDao.loadIntactness(intactnessId);
	}
	
	public List<Intactness> loadAllIntactnesss(Long[] intactnessIds) {
		return this.intactnessDao.loadAllIntactnesss(intactnessIds);
	}
	
	public List<Intactness> loadAllIntactnesss() {
		return this.intactnessDao.loadAllIntactnesss();
	}

	public void storeIntactness(Intactness intactness) {
		if (intactness.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			intactness.setCode(billNo);
			intactness.setDisabled(false);
		}
		this.intactnessDao.storeIntactness(intactness);
	}

	public void deleteIntactness(Intactness intactness) {
		this.intactnessDao.deleteIntactness(intactness);
	}

	public void deleteAllIntactness(Collection<Intactness> intactnesses) {
		this.intactnessDao.deleteAllIntactness(intactnesses);
	}

	public void disabledAllIntactness(Collection<Intactness> intactnesses) {
		for (Intactness intactness : intactnesses) {
			intactness.setDisabled(true);
			this.intactnessDao.storeIntactness(intactness);
		}
	}

	public void enabledAllIntactness(Collection<Intactness> intactnesses) {
		for (Intactness intactness : intactnesses) {
			intactness.setDisabled(false);
			this.intactnessDao.storeIntactness(intactness);
		}
	}

	

}
