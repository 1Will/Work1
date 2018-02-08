package com.yongjun.tdms.service.runmaintenance.accident.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.accident.AccidentBillDao;
import com.yongjun.tdms.model.runmaintenance.accident.AccidentBill;
import com.yongjun.tdms.service.runmaintenance.accident.AccidentBillManager;

public class DefaultAccidentBillManager implements AccidentBillManager {

	private final AccidentBillDao accidentBillDao;
	private final SequenceManager sequenceManager;
	
	public DefaultAccidentBillManager(AccidentBillDao accidentBillDao,
			           SequenceManager sequenceManager) {
		this.accidentBillDao = accidentBillDao;
		this.sequenceManager = sequenceManager;
	}
	public AccidentBill loadAccidentBill(Long accidentId) {
		return this.accidentBillDao.loadAccidentBill(accidentId);
	}

	public List<AccidentBill> loadAllAccidentBills(Long[] accidentIds) {
		return this.accidentBillDao.loadAllAccidentBills(accidentIds);
	}

	public List<AccidentBill> loadAllAccidentBills() {
		return this.accidentBillDao.loadAllAccidentBills();
	}

	public void storeAccidentBill(AccidentBill accidentBill) {
		if (accidentBill.isNew()) {
			String billNo = (String)sequenceManager.generate("-");
			accidentBill.setBillNo(billNo);
		}
		this.accidentBillDao.storeAccidentBill(accidentBill);
	}

	public void deleteAccidentBill(AccidentBill accidentBill) {
		this.accidentBillDao.deleteAccidentBill(accidentBill);
	}

	public void deleteAllAccidentBils(Collection<AccidentBill> accidentBill) {
		this.accidentBillDao.deleteAllAccidentBils(accidentBill);
	}

}
