package com.yongjun.tdms.service.expensemanagement.electricFee.pojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.electricFee.ElectricFeeDao;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFee;
import com.yongjun.tdms.model.expensemanagement.electricFee.ElectricFloorFee;
import com.yongjun.tdms.service.expensemanagement.electricFee.ElectricFeeManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultElectricFeeManager extends BaseManager implements ElectricFeeManager {
	private final ElectricFeeDao dao;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultElectricFeeManager(ElectricFeeDao dao,YongJunSequenceManager yongJunSequenceManager) {
		this.dao = dao;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeElectricFee(ElectricFee t) {
		if(t.isNew()){
			t.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_ELECTRICFEE));
		}
		this.dao.storeElectricFee(t);
	}

	public ElectricFee loadElectricFee(Long id) {
		return this.dao.loadElectricFee(id);
	}

	public List<ElectricFee> loadElectricFee() {
		return this.dao.loadElectricFee();
	}

	public List<ElectricFee> loadAllElectricFee(Long[] tIds) {
		return this.dao.loadAllElectricFee(tIds);
	}

	public void deleteElectricFee(ElectricFee t) {
		this.dao.deleteElectricFee(t);
	}

	public void deleteAllElectricFee(List<ElectricFee> ts) {
		this.dao.deleteAllElectricFee(ts);
	}

	public List<ElectricFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ElectricFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}
	public ElectricFee loadByStartTime(Date date) {
		return this.dao.loadByStartTime(date);
	}
}
