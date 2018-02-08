package com.yongjun.tdms.service.runmaintenance.accident;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.accident.AccidentBill;


@Transactional(readOnly = true)
public interface AccidentBillManager {
	AccidentBill loadAccidentBill(Long accidentId);
	
	List<AccidentBill> loadAllAccidentBills(Long[] accidentIds);
	
	List<AccidentBill> loadAllAccidentBills();
	
	@Transactional
	void storeAccidentBill(AccidentBill accidentBill);
	
	@Transactional
	void deleteAccidentBill(AccidentBill accidentBill);
	
	@Transactional
	void deleteAllAccidentBils(Collection<AccidentBill> accidentBill);

}
