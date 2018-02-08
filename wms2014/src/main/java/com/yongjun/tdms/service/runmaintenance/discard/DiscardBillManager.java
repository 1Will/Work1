package com.yongjun.tdms.service.runmaintenance.discard;

import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
@Transactional(readOnly=true)
public interface DiscardBillManager {
	/**
	 * 
	 * @param discardBillId 根据报废单Id,获得报废单对象
	 * @return DiscardBill
	 */
	@Transactional
	public DiscardBill loadDiscardBill(Long discardBillId);
	/**
	 * 
	 * @param discardBillIds 根据报废单Ids,获得报废单集合
	 * @return List
	 */
	List<DiscardBill> loadAllDiscardBills(Long[] discardBillIds);
	/**
	 * 
	 * @param discardBill   保存报废单
	 */
	@Transactional
	void storeDiscardBill(DiscardBill discardBill);
	/**
	 * 
	 * @param discardBill   给据报废单对象 删除报废单
	 */
	@Transactional
	void deleteDiscardBill(DiscardBill discardBill);
	/**
	 * 
	 * @param discardBills  获得所要删除的报废单集合
	 */
	void deleteAllDiscardBill(Collection<DiscardBill> discardBills);
	@Transactional
	void disabledAllDiscardBills(Collection<DiscardBill> DiscardBills);
	
	@Transactional
	void enabledAllDiscardBills(Collection<DiscardBill> DiscardBills);
	
}
