package com.yongjun.tdms.service.runmaintenance.fault;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.fault.FaultBill;

@Transactional(readOnly = true)
public interface FaultBillManager {
	FaultBill loadFaultBill(Long faultBillId);
	
	List<FaultBill> loadAllFaultBills(Long [] faultBillIds);
	
	List<FaultBill> loadAllFaultBills();
	
	@Transactional
	void storeFaultBill(FaultBill faultBill);
	
	@Transactional
	void deleteFaultBill(FaultBill faultBill);
	
	@Transactional
	void deleteAllFaultBill(Collection<FaultBill> faultBills);
	
	@Transactional
	void storeFaultBill(FaultBill faultBill, DeviceCard tooling);
	
	@Transactional
	void disabledAllFaultBills(Collection<FaultBill> faultBill);
	
	@Transactional
	void enabledAllFaultBills(Collection<FaultBill> faultBill);

}
