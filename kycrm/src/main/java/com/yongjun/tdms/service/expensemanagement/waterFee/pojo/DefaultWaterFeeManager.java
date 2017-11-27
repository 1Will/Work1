package com.yongjun.tdms.service.expensemanagement.waterFee.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.waterFee.WaterFeeDao;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFee;
import com.yongjun.tdms.model.expensemanagement.waterFee.WaterFloorFee;
import com.yongjun.tdms.service.expensemanagement.waterFee.WaterFeeManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultWaterFeeManager extends BaseManager implements WaterFeeManager {
	private final WaterFeeDao dao;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultWaterFeeManager(WaterFeeDao dao,YongJunSequenceManager yongJunSequenceManager) {
		this.dao = dao;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeWaterFee(WaterFee t) {
		if(t.isNew()){
			t.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_WATERFEE));
		}
		this.dao.storeWaterFee(t);
	}

	public WaterFee loadWaterFee(Long id) {
		return this.dao.loadWaterFee(id);
	}

	public List<WaterFee> loadWaterFee() {
		return this.dao.loadWaterFee();
	}

	public List<WaterFee> loadAllWaterFee(Long[] tIds) {
		return this.dao.loadAllWaterFee(tIds);
	}

	public void deleteWaterFee(WaterFee t) {
		this.dao.deleteWaterFee(t);
	}

	public void deleteAllWaterFee(List<WaterFee> ts) {
		this.dao.deleteAllWaterFee(ts);
	}

	public List<WaterFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<WaterFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public WaterFee loadByStartTime(Date date) {
		return this.dao.loadByStartTime(date);
	}
}
