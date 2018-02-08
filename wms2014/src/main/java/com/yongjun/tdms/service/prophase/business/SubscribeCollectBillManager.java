package com.yongjun.tdms.service.prophase.business;

import java.util.Collection;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;


public interface SubscribeCollectBillManager {
	/**
	 * 根据id 获得汇总单的对象
	 * @param id
	 * @return
	 */
	@Transactional
	SubscribeCollectBill loadSubscribeCollectBill(Long id);
	/**
	 * 根据ids 获得汇总单的集合
	 * @param SparePartIds
	 * @return
	 */
	
    List<SubscribeCollectBill> loadAllSubscribeCollectBills(Long[] SubscribeCollectBillIds);
    /**
     * 保存汇总单的对象
     * @param sparePart
     */
	@Transactional
	void storeSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill);
	/**
	 * 删除汇总单的对象
	 * @param sparePart
	 */
	@Transactional
	void deleteSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill);
	/**
	 * 删除所有的汇总单
	 * @param SparePartIds
	 */
	@Transactional
	void deleteAllSubscribeCollectBill(Collection<SubscribeCollectBill> SubscribeCollectBillIds);
	
	/**
	 * 获得自动生成的编号
	 * @return
	 */
	@Transactional
	String generateSubscribeCollectBillCode();
	
	List<SubscribeDetailView> loadSubscribeDetailView(List id,String flag);
	/**
	 * 更新汇总单的明细、采购、不采购数量
	 *
	 */
	@Transactional
	public void getDetailMoreNum(SubscribeCollectBill bill)throws Exception;
	

}
