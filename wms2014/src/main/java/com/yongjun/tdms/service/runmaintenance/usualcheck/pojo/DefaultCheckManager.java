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
package com.yongjun.tdms.service.runmaintenance.usualcheck.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.usualcheck.CheckDao;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
import com.yongjun.tdms.model.runmaintenance.usualcheck.CheckStatus;
import com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager;
/**
 * <p>Title: DefaultCheckManager
 * <p>Description: 日常检查业务访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.service.runmaintenance.usualcheck.CheckManager
 */
public class DefaultCheckManager implements CheckManager {

	private final CheckDao checkDao;
	private final SequenceManager sequenceManager;
	
	public DefaultCheckManager(CheckDao checkDao, SequenceManager sequenceManager) {
		this.checkDao = checkDao;
		this.sequenceManager = sequenceManager;
	}
	public Check loadCheck(Long checkId) {
		return this.checkDao.loadCheck(checkId);
	}

	public List<Check> loadAllChecks(Long[] checkIds) {
		return this.checkDao.loadAllChecks(checkIds);
	}

	public List<Check> loadAllChecks() {
		return this.checkDao.loadAllChecks();
	}

	public void storeCheck(Check check) {
		if (check.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			check.setBillNo(billNo);
		}
		check.setDisabled(false);
		checkDao.storeCheck(check);
	}

	public void deleteCheck(Check check) {
		this.checkDao.deleteCheck(check);
	}

	public void deleteAllChecks(Collection<Check> checks) {
		this.checkDao.deleteAllChecks(checks);
	}

	public void disabledAllChecks(Collection<Check> checks)throws Exception {
		for (Check check : checks) {
			if((null!= check.getStatus()) && (check.getStatus().equals(CheckStatus.Enrol))){//如果日常检查单的状态为未列入或者状态为空时 可以失效，否则将抛出异常
				throw new Exception();
			}
		}
		for (Check check : checks) {
			check.setDisabled(true);
			checkDao.storeCheck(check);
		}
		
	}
	public void enabledAllChecks(Collection<Check> checks) {
		for (Check check : checks) {
			check.setDisabled(false);
			checkDao.storeCheck(check);
		}
	}
	public void chooseUausalCheckStatusEnrol(Check check) {
		check.setStatus(CheckStatus.Enrol);
		checkDao.storeCheck(check);
	}
	public void chooseUausalCheckStatusunEnrol(Check check) {
		check.setStatus(CheckStatus.unEnrol);
		checkDao.storeCheck(check);
	}

}
