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
package com.yongjun.tdms.service.asset.spare.pojo;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.SpareInOutHistoryDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareInOutHistory;
import com.yongjun.tdms.service.asset.spare.SpareInOutHistoryManager;

/**
 * @author qs
 * @version $Id: DefaultSpareInOutHistoryManager.java 8631 2007-11-25 09:15:59Z
 *          mwei $
 */
public class DefaultSpareInOutHistoryManager extends BaseManager implements
		SpareInOutHistoryManager {
	private final SpareInOutHistoryDao spareInOutHistoryDao;

	private final SpareDao spareDao;

	public DefaultSpareInOutHistoryManager(
			SpareInOutHistoryDao spareInOutHistoryDao, SpareDao spareDao) {
		this.spareInOutHistoryDao = spareInOutHistoryDao;
		this.spareDao = spareDao;
	}

	public String getMaxSpareHistoryById(Long spareId, boolean Flag) {
		return spareInOutHistoryDao.getMaxSpareHistoryById(spareId, Flag);
	}

	public SpareInOutHistory loadSpareInOutHistory(Long spareInOutHistoryIds) {
		return spareInOutHistoryDao.loadSpareInOutHistory(spareInOutHistoryIds);
	}

	public void storeSpareInOutHistory(SpareInOutHistory spareInOutHistory,
			Long number) {
		if (spareInOutHistory.isInFlag()) {
			Spare spare = spareInOutHistory.getSpare();
			long stocks = spare.getStocks() - number;
			spare.setStocks(stocks);
			this.spareDao.storeSpare(spare);
			spareInOutHistoryDao.storeSpareInOutHistory(spareInOutHistory);
			stocks = spare.getStocks() + spareInOutHistory.getNumber();
			spare.setStocks(stocks);
			this.spareDao.storeSpare(spare);
		} else {
			Spare spare = spareInOutHistory.getSpare();
			long stocks = spare.getStocks() + number;
			spare.setStocks(stocks);
			this.spareDao.storeSpare(spare);
			spareInOutHistoryDao.storeSpareInOutHistory(spareInOutHistory);
			stocks = spare.getStocks() - spareInOutHistory.getNumber();
			spare.setStocks(stocks);
			this.spareDao.storeSpare(spare);

		}
	}
}
