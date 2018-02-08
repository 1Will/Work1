package com.yongjun.tdms.service.prophase.business.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.SubscribeCollectBillDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDetailViewDao;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;
import com.yongjun.tdms.service.prophase.business.SubscribeCollectBillManager;

public class DefaultSubscribeCollectBillManager  extends BaseManager implements SubscribeCollectBillManager {
	
	private SubscribeCollectBillDao subscribeCollectBillDao;
	private final SequenceManager sequenceManager;
	private SubscribeDetailViewDao subscribeDetailViewDao;
	private SubscribeDao subscribeDao;
	
	public DefaultSubscribeCollectBillManager(
			SubscribeCollectBillDao subscribeCollectBillDao,
			SequenceManager sequenceManager,
			SubscribeDetailViewDao subscribeDetailViewDao,
			SubscribeDao subscribeDao) {
		this.subscribeCollectBillDao = subscribeCollectBillDao;
		this.sequenceManager=sequenceManager;
		this.subscribeDetailViewDao=subscribeDetailViewDao;
		this.subscribeDao = subscribeDao;
	}

	public void deleteAllSubscribeCollectBill(Collection<SubscribeCollectBill> SubscribeCollectBillIds) {
		this.subscribeCollectBillDao.deleteAllSubscribeCollectBill(SubscribeCollectBillIds);
	}

	public void deleteSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.subscribeCollectBillDao.deleteSubscribeCollectBill(subscribeCollectBill);
	}

	public SubscribeCollectBill loadSubscribeCollectBill(Long id) {
		return this.subscribeCollectBillDao.loadSubscribeCollectBill(id);
	}

	public List<SubscribeCollectBill> loadAllSubscribeCollectBills(Long[] SubscribeCollectBillIds) {
		return this.subscribeCollectBillDao.loadAllSubscribeCollectBills(SubscribeCollectBillIds);
	}

	public void storeSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.subscribeCollectBillDao.storeSubscribeCollectBill(subscribeCollectBill);
	}

	public String generateSubscribeCollectBillCode() {
		return sequenceManager.generate("-").toString();
	}

	public List<SubscribeDetailView> loadSubscribeDetailView(List id,String flag) {
		return subscribeDetailViewDao.loadSubscribeDetailView(id,flag);
	}

	/**
	 * 更新汇总单的明细、采购、不采购数量
	 *
	 */
	public void getDetailMoreNum(SubscribeCollectBill bill) throws Exception{
		List<SubscribeDetail> list = new ArrayList<SubscribeDetail>();
		String[] arrys={"status","subscribeCollectBill"};
		Object[] values={"NOTPURCHASEED",bill.getId()};
	 
		//不采购数
		int calNum = 0;
		list = this.subscribeDao.loadDetailBykeyArry(arrys, values);
		calNum = list.size();
		bill.setCalNum(calNum);
		
		//入库数
		Object[] values2 = {"INSPECTED",bill.getId()};
		list = this.subscribeDao.loadDetailBykeyArry(arrys,values2);
		bill.setInsNum(list.size());
		
		//采购数（包括入库）
		bill.setPurNum(this.subscribeDao.getPurNum(bill));
	
		//金额
		Double dou = subscribeDao.getSumPrice(bill);
		bill.setTotalMoney(dou);
		
		//明细数量
	    list = this.subscribeDao.loadByKey("subscribeCollectBill", bill.getId());
		bill.setSumDetail(list.size());
		
		//待确认数 总数-不采购-已采购（包括入库）
		int conNum = bill.getSubscribeDetails().size()-calNum-this.subscribeDao.getPurNum(bill);
		bill.setConNum(conNum);
		
		this.subscribeCollectBillDao.storeSubscribeCollectBill(bill);
			
	}
	
	


}
