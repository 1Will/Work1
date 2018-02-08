package com.yongjun.tdms.service.prophase.business.pojo;



import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.prophase.business.PayDetailDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDao;
import com.yongjun.tdms.model.prophase.business.PayDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillPayDetailManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;

public class DefaultPurchaseBillPayDetailManager  extends BaseManager implements PurchaseBillPayDetailManager{
	private final PayDetailDao payDetailDao;
	private final PurchaseBillManager purchaseBillManager;
	private  final PurchaseBillDao purchaseBilldao;
	
	private BudgetDetailManager budgetDetailManager;
	
	public DefaultPurchaseBillPayDetailManager(PayDetailDao payDetailDao,
			PurchaseBillManager purchaseBillManager,
			PurchaseBillDao purchaseBilldao){
		this.payDetailDao=payDetailDao;
		this.purchaseBillManager=purchaseBillManager;
		this.purchaseBilldao = purchaseBilldao;
	}
	public PayDetail loadPayDetail(Long id) {
		
		return payDetailDao.loadPayDetail(id);
	}

	public List<PayDetail> loadPayDetails(Long[] PayDetailIds) {
		
		return payDetailDao.loadPayDetails(PayDetailIds);
	}

	public void storePayDetail(PayDetail payDetail) {
		
		//如果已付金额存在，则从预算中已发生费用除去改费用
		if (null != payDetail.getPurchaseBill().getBudgetNo()) {
			if (null != payDetail.getPurchaseBill().getAlreadyPayOut()) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(payDetail.getPurchaseBill().getBudgetNo(),payDetail.getPurchaseBill().getAlreadyPayOut());
			}
		}
		payDetailDao.storePayDetail(payDetail);
		PurchaseBill purchase=payDetail.getPurchaseBill();
		purchaseBillManager.updatePurchaseBillPayInformation(purchase);  //循环计算已付金额
		this.purchaseBilldao.storePurchaseBill(purchase);
		//purchaseBillManager.storePurchaseBill(purchase);//付款明细保存之后调用采购单的store方法  循环计算已付金额
		
		//如果已付金额存在，则从预算中已发生费用加上改费用
		if (null != payDetail.getPurchaseBill().getBudgetNo()) {
			if (null != payDetail.getPurchaseBill().getAlreadyPayOut()) {
				this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(payDetail.getPurchaseBill().getBudgetNo(),payDetail.getPurchaseBill().getAlreadyPayOut());
			}
		}
		
	}

	public void deletePayDetail(PayDetail payDetail) {
		payDetailDao.deletePayDetail(payDetail);
		
	}

	public void deleteAllPayDetail(Collection<PayDetail> payDetailIds,PurchaseBill purchaseBill) {
		//如果已付金额存在，则从预算中已发生费用除去改费用
		if (null != purchaseBill.getBudgetNo()) {
			if (null != purchaseBill.getAlreadyPayOut()) {
				this.budgetDetailManager.removeOccurFeeFromBudgetDetailByBudgetNo(purchaseBill.getBudgetNo(),purchaseBill.getAlreadyPayOut());
			}
		}
		payDetailDao.deleteAllPayDetail(payDetailIds);
		for(PayDetail dtl:payDetailIds){
			purchaseBill.getPayDetails().remove(dtl);
			purchaseBillManager.storePurchaseBill(purchaseBill);
		}
		//如果已付金额存在，则从预算中已发生费用加上改费用
		if (null != purchaseBill.getBudgetNo()) {
			if (null != purchaseBill.getAlreadyPayOut()) {
				this.budgetDetailManager.addOccurFeeFromBudgetDetailByBudgetNo(purchaseBill.getBudgetNo(),purchaseBill.getAlreadyPayOut());
			}
		}
		
		
	}
	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}
	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}

}
