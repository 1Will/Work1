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
package com.yongjun.tdms.service.runmaintenance.spareBorrow.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDao;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowStatus;
import com.yongjun.tdms.service.runmaintenance.spareBorrow.SpareBorrowManager;
/**
 * <p>Title: DefaultSpareBorrowManager
 * <p>Description: 备件领用单业务访问定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:$
 */
public class DefaultSpareBorrowManager implements SpareBorrowManager{
    private final SpareBorrowDao spareBorrowDao;
    private final SequenceManager sequenceManager;
    public DefaultSpareBorrowManager( SpareBorrowDao spareBorrowDao,
    		SequenceManager sequenceManager){
    	this.spareBorrowDao=spareBorrowDao;
    	this.sequenceManager=sequenceManager;
    }
	public SpareBorrow loadSpareBorrow(Long id) {
		return this.spareBorrowDao.loadSpareBorrow(id);
	}

	public void storeSpareBorrow(SpareBorrow spareBorrow) {
		if(spareBorrow.isNew()){
			String billNo=(String)sequenceManager.generate("-");
			spareBorrow.setBillNo(billNo);
		}
		this.spareBorrowDao.storeSpareBorrow(spareBorrow);
		
	}

	public List<SpareBorrow> loadAllSpareBorrows(Long[] ids) {
		
		return this.spareBorrowDao.loadAllSpareBorrows(ids);
	}

	public void deleteSpareBorrow(SpareBorrow sparePart) {
		this.spareBorrowDao.deleteSpareBorrow(sparePart);
		
	}

	public void deleteAllSpareBorrows(List<SpareBorrow> spareBorrows) {
		this.spareBorrowDao.deleteAllSpareBorrows(spareBorrows);
		
	}
	//失效所有的备件领用单 如果此备件领用单的状态为“已领用”或者为“领用中”则失效失败
	public void disabledAllSpareBorrows(Collection<SpareBorrow> spareBorrows)throws Exception {
		for (SpareBorrow spareBor : spareBorrows) {
			if(spareBor.getStatus().equals(SpareBorrowStatus.NEWSTATUS)){
				spareBor.setDisabled(true);
			}else{
				throw new Exception();
			}
			this.spareBorrowDao.storeSpareBorrow(spareBor);
		}
		
	}
	//有效所有的备件领用单
	public void enabledAllSpareBorrows(Collection<SpareBorrow> spareBorrows) {
		for (SpareBorrow spareBor : spareBorrows) {
			spareBor.setDisabled(false);
			this.spareBorrowDao.storeSpareBorrow(spareBor);
		}
	}

}
