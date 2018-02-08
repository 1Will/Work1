package com.yongjun.tdms.service.prophase.business.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDao;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.service.prophase.business.AcceptBillManager;

public class DefaultAcceptBillManager extends BaseManager implements AcceptBillManager{
    private final AcceptBillDao acceptBillDao;  //验收单业务接口所用到的验收单数据接口
    private final SequenceManager sequenceManager;
    public  DefaultAcceptBillManager(AcceptBillDao acceptBillDao,SequenceManager sequenceManager){
    	this.acceptBillDao=acceptBillDao;
    	this.sequenceManager=sequenceManager;
    }
	public AcceptBill loadAcceptBill(Long id) {
		
		return acceptBillDao.loadAcceptBill(id);
	}

	public List<AcceptBill> loadAcceptBills(Long[] AcceptBillIds) {
		
		return acceptBillDao.loadAcceptBills(AcceptBillIds);
	}

	public void storeAcceptBill(AcceptBill acceptBill) {
		if (acceptBill.isNew()) {
			String billNo = (String) sequenceManager.generate("-");
			acceptBill.setAcceptBillNo(billNo);
		}
		this.acceptBillDao.storeAcceptBill(acceptBill);
		if (null != acceptBill.getPurchaseBill()) {
			updatePurchaseBillDtlToAcceptBillStauts(acceptBill);// 采购单明细被验收之后;改变采购单的状态
			updatePurchaseBillDtlStatus(acceptBill);            //如果验收单所保存的是从采购单明细中选择的，那么改变采购单明细的状态
		}
	}
	public void updatePurchaseBillDtlStatus(AcceptBill acceptBill){
		if(acceptBill.getPurchaseBill()!=null){
			acceptBill.getPurchaseBillDtl().setStatus(PurchaseBillDtlStatus.INSPECTED);//如果采购单明细被验收之后 至采购单明细中的隐藏状态为已验收
		}else{
			acceptBill.getPurchaseBillDtl().setStatus(PurchaseBillDtlStatus.NEW);//如果采购单明细被验收之后 至采购单明细中的隐藏状态为未验收
		}
	}
   public void updatePurchaseBillDtlToAcceptBillStauts(AcceptBill acceptBill){//采购到验收状态具体改变的方法
	   if(acceptBill.getPurchaseBill().getPurchaseBillDetails().size()!=acceptBill.getPurchaseBill().getAcceptBill().size()){
		   acceptBill.getPurchaseBill().setStatus(PurchaseBillStatus.INSPECTING);  //如果采购单明细的大小不等于验收明细的大小,把采购单状态侄为验收中       
	   }else{                                                           
		   acceptBill.getPurchaseBill().setStatus(PurchaseBillStatus.INSPECTED);//如果采购单明细的大小等于验收明细的大小,把采购单状态侄为以验收 
	   }
   }
	public void deleteAcceptBill(AcceptBill acceptBill) {
		this.acceptBillDao.deleteAcceptBill(acceptBill);
		
	}

	public void deleteAllAcceptBill(Collection<AcceptBill> acceptBillIds) {
	      this.acceptBillDao.deleteAllAcceptBill(acceptBillIds);
		
	}
	//失效验收单的业务方法
	public void disabledAllAcceptBills(Collection<AcceptBill> acceptBills) {
		  for(AcceptBill oil:acceptBills){
			  oil.setDisabled(true);
			  acceptBillDao.storeAcceptBill(oil); 
		  }
	}
    //有效验收单的业务方法
	public void enabledAllAcceptBills(Collection<AcceptBill> acceptBills) {
		  for(AcceptBill oil:acceptBills){
			  oil.setDisabled(false);
			acceptBillDao.storeAcceptBill(oil); 
		  }
		
	}

}
