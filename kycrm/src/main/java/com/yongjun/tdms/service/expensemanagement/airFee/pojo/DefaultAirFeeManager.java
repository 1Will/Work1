package com.yongjun.tdms.service.expensemanagement.airFee.pojo;

import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.expensemanagement.airFee.AirFeeDao;
import com.yongjun.tdms.model.expensemanagement.airFee.AirFee;
import com.yongjun.tdms.service.expensemanagement.airFee.AirFeeManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultAirFeeManager extends BaseManager implements AirFeeManager {
	private final AirFeeDao dao;
    private final YongJunSequenceManager yongJunSequenceManager;
	public DefaultAirFeeManager(AirFeeDao dao,YongJunSequenceManager yongJunSequenceManager) {
		this.dao = dao;
		this.yongJunSequenceManager=yongJunSequenceManager;
	}

	public void storeAirFee(AirFee t) {
		if(t.isNew()){
			t.setCode((String)this.yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_AIRFEE));
		    }
		this.dao.storeAirFee(t);
	}

	public AirFee loadAirFee(Long id) {
		return this.dao.loadAirFee(id);
	}

	public List<AirFee> loadAirFee() {
		return this.dao.loadAirFee();
	}

	public List<AirFee> loadAllAirFee(Long[] tIds) {
		return this.dao.loadAllAirFee(tIds);
	}

	public void deleteAirFee(AirFee t) {
		this.dao.deleteAirFee(t);
	}

	public void deleteAllAirFee(List<AirFee> ts) {
		this.dao.deleteAllAirFee(ts);
	}

	public List<AirFee> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<AirFee> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}


}
