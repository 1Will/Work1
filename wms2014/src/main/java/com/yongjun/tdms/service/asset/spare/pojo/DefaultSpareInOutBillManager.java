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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.asset.spare.SpareInOutBillDao;
import com.yongjun.tdms.dao.asset.spare.SpareInOutHistoryDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareInOutBill;
import com.yongjun.tdms.model.asset.spare.SpareInOutHistory;
import com.yongjun.tdms.service.asset.spare.SpareInOutBillManager;

/**
 * @author qs
 * @version $Id: DefaultSpareInOutBillManager.java 8471 2007-11-21 00:48:53Z
 *          mwei $
 */
public class DefaultSpareInOutBillManager extends BaseManager implements
		SpareInOutBillManager {

	private final SpareInOutBillDao spareInOutBillDao;

	private final SpareDao spareDao;

	private final SpareInOutHistoryDao spareInOutHistoryDao;

	private final SequenceManager spareInsequenceManager;

	private final SequenceManager spareOutsequenceManager;

	public DefaultSpareInOutBillManager(SpareInOutBillDao spareInOutBillDao,
			SpareDao spareDao, SpareInOutHistoryDao spareInOutHistoryDao,
			SequenceManager spareInsequenceManager,
			SequenceManager spareOutsequenceManager) {
		this.spareInOutBillDao = spareInOutBillDao;
		this.spareDao = spareDao;
		this.spareInOutHistoryDao = spareInOutHistoryDao;
		this.spareInsequenceManager = spareInsequenceManager;
		this.spareOutsequenceManager = spareOutsequenceManager;
	}

	public SpareInOutBill loadSpareInOutBill(Long spareInOutBillIds) {
		return spareInOutBillDao.loadSpareInOutBill(spareInOutBillIds);
	}

	public List<SpareInOutBill> loadAllSpareInOutBill(Long[] spareInOutBillIds) {
		return spareInOutBillDao.loadAllSpareInOutBill(spareInOutBillIds);
	}

	private void deleteSpareStocks(SpareInOutBill spareInOutBill) {
		Set<SpareInOutHistory> spareInOutHistory = spareInOutBill
				.getSpareInOutHistory();
		Spare spare;
		Object[] historyArray = spareInOutHistory.toArray();
		for (int i = 0; i < historyArray.length; i++) {
			spare = ((SpareInOutHistory) historyArray[i]).getSpare();
			long stocks = spare.getStocks();
			stocks = stocks - ((SpareInOutHistory) historyArray[i]).getNumber();
			spare.setStocks(stocks);
			spareDao.storeSpare(spare);
		}
	}

	private void addSpareStocks(SpareInOutBill spareInOutBill) {
		Set<SpareInOutHistory> spareInOutHistory = spareInOutBill
				.getSpareInOutHistory();
		Spare spare;
		Object[] historyArray = spareInOutHistory.toArray();
		for (int i = 0; i < historyArray.length; i++) {
			spare = ((SpareInOutHistory) historyArray[i]).getSpare();
			long stocks = spare.getStocks();
			stocks = stocks + ((SpareInOutHistory) historyArray[i]).getNumber();
			spare.setStocks(stocks);
			spareDao.storeSpare(spare);
		}
	}

	public void storeSpareInOutBill(SpareInOutBill spareInOutBill,
			String stringMapValue, boolean input) {
		String[] ary = stringMapValue.split(",");
		Spare spare = new Spare();
		long stocks = 0;
		if (input) {
			deleteSpareStocks(spareInOutBill);
			spareInOutBill.getSpareInOutHistory().clear();
			if (spareInOutBill.isNew()) {
				String spareInBillNo = (String) spareInsequenceManager.generate("-");
				spareInOutBill.setBillNo(spareInBillNo);
			}
			spareInOutBill.setInFlag(true);
			spareInOutBillDao.storeSpareInOutBill(spareInOutBill);
			for (int i = 0; i < ary.length; i = i + 2) {
				SpareInOutHistory spareInOutHistory = new SpareInOutHistory();
				spare = spareDao.loasSpare(Long.valueOf(ary[i]));
				stocks = spare.getStocks();
				stocks = stocks + Long.valueOf(ary[i + 1]);
				spare.setStocks(stocks);
				this.spareDao.storeSpare(spare);
				updateSpareInOutHistoryStatus(spare, true);
				spareInOutHistory.setSpare(spare);
				spareInOutHistory.setBill(spareInOutBill);
				spareInOutHistory.setNumber(Long.valueOf(ary[i + 1]));
				spareInOutHistory.setSender(spareInOutBill.getInoutPeople());
				spareInOutHistory
						.setSendDateTm(spareInOutBill.getInoutDateTm());
				spareInOutHistory.setAuditor(spareInOutBill.getInoutPeople());
				spareInOutHistory.setAuditDateTm(spareInOutBill
						.getInoutDateTm());
				spareInOutHistory.setInFlag(true);
				spareInOutHistory.setReadOnly(false);
				spareInOutHistoryDao.storeSpareInOutHistory(spareInOutHistory);
				stocks = 0;
			}
		} else {
			addSpareStocks(spareInOutBill);
			spareInOutBill.getSpareInOutHistory().clear();
			spareInOutBill.setInFlag(false);
			if (spareInOutBill.isNew()) {
				String spareOutBillNo = (String) spareOutsequenceManager.generate("-");
				spareInOutBill.setBillNo(spareOutBillNo);
			}
			spareInOutBillDao.storeSpareInOutBill(spareInOutBill);
			for (int i = 0; i < ary.length; i = i + 2) {
				SpareInOutHistory spareInOutHistory = new SpareInOutHistory();
				spare = spareDao.loasSpare(Long.valueOf(ary[i]));
				stocks = spare.getStocks();
				stocks = stocks - Long.valueOf(ary[i + 1]);
				spare.setStocks(stocks);
				this.spareDao.storeSpare(spare);
				updateSpareInOutHistoryStatus(spare, false);
				spareInOutHistory.setSpare(spare);
				spareInOutHistory.setBill(spareInOutBill);
				spareInOutHistory.setNumber(Long.valueOf(ary[i + 1]));
				spareInOutHistory.setReceiver(spareInOutBill.getInoutPeople());
				spareInOutHistory.setReceiveDateTm(spareInOutBill
						.getInoutDateTm());
				spareInOutHistory.setAuditor(spareInOutBill.getInoutPeople());
				spareInOutHistory.setAuditDateTm(spareInOutBill
						.getInoutDateTm());
				spareInOutHistory.setInFlag(false);
				spareInOutHistory.setReadOnly(false);
				spareInOutHistoryDao.storeSpareInOutHistory(spareInOutHistory);
				stocks = 0;
			}
		}
	}

	public void storeSpareInOutBill(SpareInOutBill spareInOutBill) {
		if(spareInOutBill.isInFlag()){
			if (spareInOutBill.isNew()) {
				String spareInBillNo = (String) spareInsequenceManager.generate("-");
				spareInOutBill.setBillNo(spareInBillNo);
			}
		}else{
			if (spareInOutBill.isNew()) {
				String spareOutBillNo = (String) spareOutsequenceManager.generate("-");
				spareInOutBill.setBillNo(spareOutBillNo);
			}
		}
		spareInOutBillDao.storeSpareInOutBill(spareInOutBill);
	}

	private void updateSpareInOutHistoryStatus(Spare spare, boolean input) {
		String spareMaxId = spareInOutHistoryDao.getMaxSpareHistoryById(spare
				.getId(), input);
		if (spareMaxId != null) {
			SpareInOutHistory spareInOutHistory = spareInOutHistoryDao
					.loadSpareInOutHistory(Long.valueOf(spareMaxId));
			spareInOutHistory.setReadOnly(true);
			spareInOutHistoryDao.storeSpareInOutHistory(spareInOutHistory);
		}
	}

	public void deleteSpareInOutBill(SpareInOutBill spareInOutBill) {
		spareInOutBillDao.deleteSpareInOutBill(spareInOutBill);
	}

	public void deleteAllSpareInOutBill(
			Collection<SpareInOutBill> spareInOutBill) {
		spareInOutBillDao.deleteAllSpareInOutBill(spareInOutBill);
	}
}
