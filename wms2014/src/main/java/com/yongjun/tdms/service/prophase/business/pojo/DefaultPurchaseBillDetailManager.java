package com.yongjun.tdms.service.prophase.business.pojo;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.NumberUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeCollectBillDao;
import com.yongjun.tdms.dao.prophase.business.SubscribeDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.prophase.business.PurchaseBill;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.model.prophase.business.PurchaseBillStatus;
import com.yongjun.tdms.model.prophase.business.Subscribe;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBillTypeStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeDetail;
import com.yongjun.tdms.model.prophase.business.SubscribeDetailStatus;
import com.yongjun.tdms.model.prophase.business.SubscribeStatus;
import com.yongjun.tdms.model.year.budget.BudgetDetail;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillManager;
import com.yongjun.tdms.service.prophase.business.SubscribeManager;
import com.yongjun.tdms.service.year.budget.BudgetDetailManager;

public class DefaultPurchaseBillDetailManager extends BaseManager implements PurchaseBillDetailManager{
    private final PurchaseBillDetailDao purchaseBillDetailDao;
    private final SubscribeManager subscribeManager;
    private final SubscribeDao subscribeDao;
	private final PurchaseBillManager purchaseBillManager;
	private final PurchaseBillDao purchaseBillDao;
	private final SpareDao spareDao;
	private final SubscribeCollectBillDao subscribeCollectBillDao;
	
	
	private BudgetDetailManager budgetDetailManager;
	
	
    public  DefaultPurchaseBillDetailManager(PurchaseBillDetailDao purchaseBillDetailDao,SubscribeManager subscribeManager,
    		SubscribeDao subscribeDao,PurchaseBillManager purchaseBillManager,
    		PurchaseBillDao purchaseBillDao,SpareDao spareDao,
    		SubscribeCollectBillDao subscribeCollectBillDao){
       this.purchaseBillDetailDao=purchaseBillDetailDao;
       this.subscribeManager=subscribeManager;	
       this.subscribeDao=subscribeDao;
       this.purchaseBillManager=purchaseBillManager;
       this.purchaseBillDao = purchaseBillDao;
       this.spareDao=spareDao;  
       this.subscribeCollectBillDao =subscribeCollectBillDao;
      
    }
    
    
	public PurchaseBillDetail loadPurchaseBillDetail(Long purchaseBillDetailId) {
		return purchaseBillDetailDao.loadPurchaseBillDetail(purchaseBillDetailId);
	}
	public List<PurchaseBillDetail> loadPurchaseBillDetails(Long[] PurchaseBillDetailIds) {
		return purchaseBillDetailDao.loadPurchaseBillDetails(PurchaseBillDetailIds);
	}
	public void storePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail) {
        // 如果采购单的预算编号不为空，则减去该预算编号关联的采购总费用
		if(purchaseBillDetail.getPurchaseBill().getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != purchaseBillDetail.getPurchaseBill().getBudgetNo()) {
				this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBillDetail.getPurchaseBill().getBudgetNo(),purchaseBillDetail.getPurchaseBill().getTotalPrice());
			}
		}
		
		purchaseBillDetailDao.storePurchaseBillDetail(purchaseBillDetail);
		 if(purchaseBillDetail.getPurchaseBill().getBudgetNo()!=null&&purchaseBillDetail.getPurchaseBill().getToolingDevFlag().equals(SysModel.TOOLING)){
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(purchaseBillDetail.getPurchaseBill().getBudgetNo());
				Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(purchaseBillDetail.getPurchaseBill().getId());
				budgetDetail.setPurchaseContractFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
		PurchaseBill purchase=purchaseBillDetail.getPurchaseBill();	// 采购单明细对象保存之后,调用采购单基本对象的
		this.updatePurchaseBillDtlToAcceptBillStauts(purchase);
		purchaseBillDetail.setStatus(PurchaseBillDtlStatus.NEW);
		purchaseBillManager.storePurchaseBill(purchase);
	}
	
	public void deletePurchaseBillDetail(PurchaseBillDetail purchaseBillDetail) {
		purchaseBillDetailDao.deletePurchaseBillDetail(purchaseBillDetail);
	}
	/**
	 * 删除采购单明细
	 */
	public void deleteAllPurchaseBillDetails(Collection<PurchaseBillDetail> PurchaseBillDetails,PurchaseBill purchaseBill)throws Exception {
		//purchaseBill.getPurchaseBillDetails()
		// 如果采购单明细中所关联的采购单对象的状态为已验收或验收中 删除失败
		purchaseBillDtlStatus(PurchaseBillDetails,purchaseBill); 
		// 移除该预算编号关联的采购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		purchaseBillDetailDao.deleteAllPurchaseBillDetails(PurchaseBillDetails);
		
		for(PurchaseBillDetail detail : PurchaseBillDetails) {
		    SubscribeDetail subscribeDetail = detail.getSubscribeDetail();
			 
			if(null != subscribeDetail && null !=subscribeDetail.getId()){
				subscribeDetail.setStatus(SubscribeDetailStatus.NEW);
				subscribeDetail.setPurchaseDate(null);
				subscribeDetail.setBuyeAmount(null);
				subscribeDetail.setRequireDate(null);
				subscribeDetail.setStocked(false);
				
				//同步更新 申购单
				this.updateSubscribeStatus(subscribeDetail.getSubscribe());
				//同步更新 汇总单
				if(null!=subscribeDetail.getSubscribeCollectBill()){
					this.updateSubscribeCollectBill(subscribeDetail.getSubscribeCollectBill());
				}
				this.subscribeDao.storeSubscribeDetail(subscribeDetail);
				//添加 事件 zzb
				this.subscribeManager.storeEventNew_subDetail(1062, subscribeDetail.getId().intValue(), "采购删除");
			}
			purchaseBill.getPurchaseBillDetails().remove(detail);
			updatePurchaseBillDtlToAcceptBillStauts(purchaseBill);// 采购单明细被验收之后;改变采购单的状态
		    this.updatePurchaseBillDtlTotalPrice(purchaseBill);
		    detail.getPurchaseBill().setSubmit(true);
		    purchaseBillManager.storePurchaseBill(detail.getPurchaseBill());
		  
		} 
		
		// 如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
		if(purchaseBill.getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != purchaseBill.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
			}
		}else{
			 //如果工装采购合同单的预算编号不为空，则加上该预算编号的采购合同的总费用
			 if(purchaseBill.getBudgetNo()!=null&&purchaseBill.getToolingDevFlag().equals(SysModel.TOOLING)){
					BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(purchaseBill.getBudgetNo());
					Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(purchaseBill.getId());
					budgetDetail.setPurchaseContractFee(allPrice);
					this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				}
		}
		
		updatePurchaseBillStatus(purchaseBill);
	
	}
	/**
	 * 判断采购单是否能被删除
	 * @param purchaseBillDetails
	 * @param purchaseBill
	 * @throws Exception
	 */
	public void purchaseBillDtlStatus(Collection<PurchaseBillDetail> purchaseBillDetails,PurchaseBill purchaseBill)throws Exception{
		for(PurchaseBillDetail Pdtl: purchaseBillDetails){// 如果采购单明细中所关联的采购单对象的状态为已验收或验收中// 删除失败 抛出异常
			if(PurchaseBillDtlStatus.INSPECTED.equals(Pdtl.getStatus())){
				throw new Exception();
			}
		    purchaseBill.getPurchaseBillDetails().remove(Pdtl);
		}
		
	}
    public void updatePurchaseBillDtlToAcceptBillStauts(PurchaseBill purchaseBill){// 采购到验收状态具体改变的方法
		   int number=0;
		  for(PurchaseBillDetail dtl:purchaseBill.getPurchaseBillDetails()) {
			  if(dtl.getStatus().equals(PurchaseBillDtlStatus.INSPECTED)){
			   number++;
			  }
		  }
	      if(number==0){
		     purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
	     }else if((number<purchaseBill.getPurchaseBillDetails().size()&&number>0)){
	    	 purchaseBill.setStatus(PurchaseBillStatus.INSPECTING);
	     }else if(number==purchaseBill.getPurchaseBillDetails().size()){
	    	 purchaseBill.setStatus(PurchaseBillStatus.INSPECTED);
	     }
	}
	/**
	 * 更新申购单
	 * @param subscribe
	 */
	public void updateSubscribeStatus(Subscribe subscribe){
		int newNum=0;//新建
		int purchaseNum=0;//已采购
		int inspectingNum=0;//入库中
		int inspectedNum=0;//已入库
		int collect=0;
		Set<SubscribeDetail> set = subscribe.getSubscribeDetails();
		if(null != set && set.size()>0){
			int size = set.size();
			for(SubscribeDetail detail : set){
				if(SubscribeDetailStatus.NEW.equals(detail.getStatus())){
					newNum++;
				}
				if(SubscribeDetailStatus.PURCHASEED.equals(detail.getStatus())){
					purchaseNum++;
				}
				if(SubscribeDetailStatus.INSPECTING.equals(detail.getStatus())){
					inspectingNum++;
				}
				if(SubscribeDetailStatus.INSPECTED.equals(detail.getStatus())){
					inspectedNum++;
				}if(null!=detail.getSubscribeCollectBill()){
					collect=1;
				}
			}
			if(size == purchaseNum){
				subscribe.setStatus(SubscribeStatus.PURCHASEED);
			}else if(size == inspectingNum){
				subscribe.setStatus(SubscribeStatus.STORAGING);
			}else if(size == inspectedNum){
				subscribe.setStatus(SubscribeStatus.STORAGED);
			}else if(size ==newNum && collect==0){
				subscribe.setStatus(SubscribeStatus.NEWPURCHASE);
			}else if(size ==newNum && collect==1){
				subscribe.setStatus(SubscribeStatus.SUMMARYED);
			}else if(0==inspectingNum && 0==inspectedNum && purchaseNum>0){
				subscribe.setStatus(SubscribeStatus.PURCHASING);
			}else{
				subscribe.setStatus(SubscribeStatus.STORAGING);
			}
		}
		
	}
	/**
	 * 保存弹出页面 申购单明细 -> 采购单
	 */
	public void storSubscribeDtl(PurchaseBill purchaseBill, String addSubscribeIds) {
		String [] addSubscribeId=null;
		if(addSubscribeIds!=null){
			addSubscribeId=addSubscribeIds.split(",");
		}
		// 移除该预算编号关联的申购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		for(int i=0;i<addSubscribeId.length;i++){
			PurchaseBillDetail detail=new PurchaseBillDetail();
			// 获得申购单明细的对象
			SubscribeDetail  subscribeDetail=subscribeManager.loadSubscribeDetail(Long.valueOf(addSubscribeId[i]));
			subscribeDetail.setStocked(true);
			detail.setSubscribeDetail(subscribeDetail);
			detail.setPurchaseBill(purchaseBill);
			//拷贝 采购单明细中
			copySubscribeDetailToPurchaseDetail(detail,subscribeDetail);// 申购单明细复制到采购单明细中
			detail.getPurchaseBill().setSubmit(true);
			detail.setSubcribe(subscribeDetail.getSubscribe());
			updatePurchaseBillStatus(purchaseBill);
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			subscribeDetail.setStatus(SubscribeDetailStatus.PURCHASEED);// 当前的申购单被采购之后,把此申购单状态设为已采购
			//同步更新 申购单
			updateSubscribeStatus(subscribeDetail.getSubscribe());
			 
			this.subscribeDao.storeSubscribe(subscribeDetail.getSubscribe());
			//添加 事件 zzb
			this.subscribeManager.storeEventNew_subDetail(1062, subscribeDetail.getId().intValue(), "采购");
			
			//同步更新 汇总单
			if(null !=subscribeDetail.getSubscribeCollectBill()){
				this.updateSubscribeCollectBill(subscribeDetail.getSubscribeCollectBill());
			}
			
			purchaseBill.getPurchaseBillDetails().add(detail);
			subscribeDao.storeSubscribeDetail(subscribeDetail);
			updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());  // 计算采购单明细中set集合的总价
		}
		// 如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		//更新采购单状态
		//updatePurchaseBillStatus(purchaseBill);
	}
	
	
	
	public void updatePurchaseBillDtlTotalPrice(PurchaseBill purchaseBill){
		Double totalPrice = NumberUtils.DOUBLE_ZERO;
		 for(PurchaseBillDetail Dtl:purchaseBill.getPurchaseBillDetails()){//通过采购单基本对象获得当前采购单明细的集合
			  if(null != Dtl.getTotalPrice()){
				  totalPrice+=Dtl.getTotalPrice();
			  }
			
		   }
		purchaseBill.setTotalPrice(totalPrice);
		purchaseBill.setTaxTotalPrice(totalPrice/1.17);
		this.purchaseBillDao.storePurchaseBill(purchaseBill);
		
	}
	
	
	
	
	/**	
	 * 采购单明细复制到工装采购合同工装制作明细
	 */
	public void storPurchaseBillDtl(PurchaseBill purchaseBill, String addPurchaseBillIds) {
		String [] addSubscribeId=null;
		if(addPurchaseBillIds!=null){
			addSubscribeId=addPurchaseBillIds.split(",");
		}
		// 移除该预算编号关联的申购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		for(int i=0;i<addSubscribeId.length;i++){
			
			PurchaseBillDetail detail=new PurchaseBillDetail();
			// 获得申购单明细的对象
			SubscribeDetail  subscribeDetail=subscribeManager.loadSubscribeDetail(Long.valueOf(addSubscribeId[i]));
			detail.setSubscribeDetail(subscribeDetail);
			detail.setPurchaseBill(purchaseBill);
			detail.setDetailType(YearPlanDetailCategory.TOOLING_MAKE);
			copySubscribeDetailToPurchaseDetail(detail,subscribeDetail);// 申购单明细复制到采购单明细中
			detail.getPurchaseBill().setSubmit(true);
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			updatePurchaseBillStatus(purchaseBill);
			purchaseBill.getPurchaseBillDetails().add(detail);
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			purchaseBillManager.storePurchaseBill(purchaseBill);
			SubscribeDetailStatus  subscribeDetailStatus =subscribeDetail.getStatus();// 获得申购单的状态
			subscribeDetail.setStatus(SubscribeDetailStatus.PURCHASEED);// 当前的申购单被采购之后,把此申购单状态设为已采购
			subscribeDao.storeSubscribeDetail(subscribeDetail);
			this.updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());
			subscribeManager.updateSubscribeStatus(subscribeDetail.getSubscribe());
		}
		// 如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
		if(purchaseBill.getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != purchaseBill.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
			}
		}else{
			if(purchaseBill.getBudgetNo()!=null){
//				如果工装采购合同单的预算编号不为空，则加上该预算编号的采购合同的总费用
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(purchaseBill.getBudgetNo());
				Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(purchaseBill.getId());
				budgetDetail.setPurchaseContractFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
		}
		
		//更新采购单状态
		//updatePurchaseBillStatus(purchaseBill);
	}
	//采购单明细复制到工装采购合同备件明细
	public void storeSparePurchaseDtl(PurchaseBill purchaseBill, String addPurchaseBillIds) {
		String [] addSubscribeId=null;
		if(addPurchaseBillIds!=null){
			addSubscribeId=addPurchaseBillIds.split(",");
		}
		// 移除该预算编号关联的申购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		for(int i=0;i<addSubscribeId.length;i++){
			PurchaseBillDetail detail=new PurchaseBillDetail();
			// 获得申购单明细的对象
			SubscribeDetail  subscribeDetail=subscribeManager.loadSubscribeDetail(Long.valueOf(addSubscribeId[i]));
			detail.setSubscribeDetail(subscribeDetail);
			detail.setPurchaseBill(purchaseBill);
			detail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
			copySubscribeDetailToPurchaseDetail(detail,subscribeDetail);// 申购单明细复制到采购单明细中
			detail.getPurchaseBill().setSubmit(true);
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			updatePurchaseBillStatus(purchaseBill);
			purchaseBillManager.storePurchaseBill(purchaseBill);
			SubscribeDetailStatus  subscribeDetailStatus =subscribeDetail.getStatus();// 获得申购单的状态
			subscribeDetail.setStatus(SubscribeDetailStatus.PURCHASEED);// 当前的申购单被采购之后,把此申购单状态设为已采购
			subscribeDao.storeSubscribeDetail(subscribeDetail);
			purchaseBill.getPurchaseBillDetails().add(detail);
			updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());  // 计算采购单明细中set集合的总价
			subscribeManager.updateSubscribeStatus(subscribeDetail.getSubscribe());
		}
		// 如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
		if(purchaseBill.getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != purchaseBill.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
			}
		}else{
			if(purchaseBill.getBudgetNo()!=null){
//				如果工装采购合同单的预算编号不为空，则加上该预算编号的采购合同的总费用
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(purchaseBill.getBudgetNo());
				Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(purchaseBill.getId());
				budgetDetail.setPurchaseContractFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
		}
	
		//更新采购单状态
		//updatePurchaseBillStatus(purchaseBill);
	}
	
	//采购单明细复制到工装采购合同保养维护明细
	public void storeRepairMantancePurchaseDtl(PurchaseBill purchaseBill, String addPurchaseBillIds) {
		String [] addSubscribeId=null;
		if(addPurchaseBillIds!=null){
			addSubscribeId=addPurchaseBillIds.split(",");
		}
		// 移除该预算编号关联的申购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		for(int i=0;i<addSubscribeId.length;i++){
			PurchaseBillDetail detail=new PurchaseBillDetail();
			// 获得申购单明细的对象
			SubscribeDetail  subscribeDetail=subscribeManager.loadSubscribeDetail(Long.valueOf(addSubscribeId[i]));
			detail.setSubscribeDetail(subscribeDetail);
			detail.setPurchaseBill(purchaseBill);
			detail.setDetailType(YearPlanDetailCategory.REPAIR_MAINTENANCE);
			copySubscribeDetailToPurchaseDetail(detail,subscribeDetail);// 申购单明细复制到采购单明细中
			detail.getPurchaseBill().setSubmit(true);
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			updatePurchaseBillStatus(purchaseBill);
			purchaseBillManager.storePurchaseBill(purchaseBill);
			purchaseBill.getPurchaseBillDetails().add(detail);
			SubscribeDetailStatus  subscribeDetailStatus =subscribeDetail.getStatus();// 获得申购单的状态
			subscribeDetail.setStatus(subscribeDetailStatus.PURCHASEED);// 当前的申购单被采购之后,把此申购单状态设为已采购
			subscribeDao.storeSubscribeDetail(subscribeDetail);
			updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());  // 计算采购单明细中set集合的总价
			subscribeManager.updateSubscribeStatus(subscribeDetail.getSubscribe());
		}
		// 如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
		if(purchaseBill.getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != purchaseBill.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
			}
		}else{
			if(purchaseBill.getBudgetNo()!=null){
//				如果工装采购合同单的预算编号不为空，则加上该预算编号的采购合同的总费用
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(purchaseBill.getBudgetNo());
				Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(purchaseBill.getId());
				budgetDetail.setPurchaseContractFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
		}
		
		//更新采购单状态
		//updatePurchaseBillStatus(purchaseBill);
	}
	
//	采购单明细复制到工装采购合同技术改造明细
	public void storeTechAlterDetailsPurchaseConcatDtl(PurchaseBill purchaseBill, String addPurchaseBillIds) {
		String [] addSubscribeId=null;
		if(addPurchaseBillIds!=null){
			addSubscribeId=addPurchaseBillIds.split(",");
		}
		// 移除该预算编号关联的申购总费用
		if (null != purchaseBill.getBudgetNo()) {
			this.budgetDetailManager.removeParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
		}
		for(int i=0;i<addSubscribeId.length;i++){
			PurchaseBillDetail detail=new PurchaseBillDetail();
			// 获得申购单明细的对象
			SubscribeDetail  subscribeDetail=subscribeManager.loadSubscribeDetail(Long.valueOf(addSubscribeId[i]));
			detail.setSubscribeDetail(subscribeDetail);
			detail.setPurchaseBill(purchaseBill);
			detail.setDetailType(YearPlanDetailCategory.TECH_ALTER);
			copySubscribeDetailToPurchaseDetail(detail,subscribeDetail);// 申购单明细复制到采购单明细中
			detail.getPurchaseBill().setSubmit(true);
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			updatePurchaseBillStatus(purchaseBill);

			purchaseBill.getPurchaseBillDetails().add(detail);
			updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());  // 计算采购单明细中set集合的总价
			purchaseBillDetailDao.storePurchaseBillDetail(detail); 

			purchaseBillManager.storePurchaseBill(purchaseBill); 

			SubscribeDetailStatus  subscribeDetailStatus =subscribeDetail.getStatus();// 获得申购单的状态
			subscribeDetail.setStatus(subscribeDetailStatus.PURCHASEED);// 当前的申购单被采购之后,把此申购单状态设为已采购
			subscribeDao.storeSubscribeDetail(subscribeDetail);
			subscribeManager.updateSubscribeStatus(subscribeDetail.getSubscribe());
		}
		// 如果采购单的预算编号不为空，则加上该预算编号关联的采购总费用
		if(purchaseBill.getToolingDevFlag().equals(SysModel.DEVICE)){
			if (null != purchaseBill.getBudgetNo()) {
				this.budgetDetailManager.addParchaseContractFeeFromBudgetDetail(purchaseBill.getBudgetNo(), purchaseBill.getTotalPrice());
			}
		}else{
			if(purchaseBill.getBudgetNo()!=null){
				BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(purchaseBill.getBudgetNo());
				Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(purchaseBill.getId());
				budgetDetail.setPurchaseContractFee(allPrice);
				this.budgetDetailManager.storeBudgetDetail(budgetDetail);
			}
		}
		
		//更新采购单状态
		//updatePurchaseBillStatus(purchaseBill);
	}
	
	
	/**
	 * 根据采购单明细状态来更新采购单状态
	 */
	public void updatePurchaseBillStatus(PurchaseBill purchaseBill){
		int newNum = 0;	//表示新建
		int spectedNum=0;   //表示已验收/已入库 
		int inspectingNum=0; //表入库中
		
		Set<PurchaseBillDetail> set = purchaseBill.getPurchaseBillDetails();
		if(null != set && set.size()>0){
			int size = set.size();
			for(PurchaseBillDetail detail : set){
				if(PurchaseBillDtlStatus.NEW.equals(detail.getStatus())){
					newNum++;
				}
				if(PurchaseBillDtlStatus.INSPECTING.equals(detail.getStatus())){
					inspectingNum++;
				}
				if(PurchaseBillDtlStatus.INSPECTED.equals(detail.getStatus())){
					spectedNum++;
				}
			}
			if(size == newNum){
				purchaseBill.setStatus(PurchaseBillStatus.NEWSTATUS);
			}else if(size == spectedNum){
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTED);
			}else{
				purchaseBill.setStatus(PurchaseBillStatus.INSPECTING);
			}
			this.purchaseBillDao.storePurchaseBill(purchaseBill);
		}
		
	 
		
	}
	
	/**
	 * copy 申购单明细 到采购单
	 * @param detail 采购单
	 * @param subscribeDetail 申购单明细
	 */
	private void copySubscribeDetailToPurchaseDetail(PurchaseBillDetail detail,SubscribeDetail subscribeDetail){
		//如果申购单明细的图号不为空,把申购单明细的图号COPY到采购单明细中来
		if(null != subscribeDetail.getGraphNo()){
			detail.setGraphNo(subscribeDetail.getGraphNo());
		}else{
			detail.setGraphNo(null);
		}
		//把申购单所关联的备件id  COPY到采购单明细中
		if(null != subscribeDetail.getSpare()){
			detail.setSpare(subscribeDetail.getSpare());
		}
		//备件类别
		if(null != subscribeDetail.getCategory()){
			detail.setCategory(subscribeDetail.getCategory());
			detail.setCategoryName(subscribeDetail.getCategory().getName());
		}
        //所属类别明细关联
		if(null != subscribeDetail.getDetailCategory()){
			detail.setDetailCategory(subscribeDetail.getDetailCategory());
			detail.setDetailCategoryName(subscribeDetail.getDetailCategory().getName());
		}
		
		//所属类别关联
		if(null != subscribeDetail.getToolingCategory()){
			detail.setToolingCategory(subscribeDetail.getToolingCategory());
		}
	 
		//申购单明细\采购单明细的型号
		if(null != subscribeDetail.getModel()){
			detail.setModel(subscribeDetail.getModel());
		}else{
			detail.setModel(null);		
		}
		//申购单明细\采购单明细的规格
		if(subscribeDetail.getSpecification()!=null){
			detail.setSpecification(subscribeDetail.getSpecification());		
		}else{
			detail.setSpecification(null);		
		}
		
		
       //生产厂家
		if(null != subscribeDetail.getFactory()){
			detail.setFactory(subscribeDetail.getFactory());
		}//订货号
		if(null != subscribeDetail.getOrderNo()){
			detail.setOrderNo(subscribeDetail.getOrderNo());
		}
		//实际到货日期
		if(null != subscribeDetail.getArrivalDate()){
			detail.setActualDeliveryDate(subscribeDetail.getArrivalDate());
		}
		
		
		
		//申购单明细\采购单明细数量
//		if(subscribeDetail.getAmount()!=null){
//			detail.setAmount(subscribeDetail.getAmount());
//		}else{
//			detail.setAmount(null);
//		}
		//申购单明细\采购单明细价格
		Double unitPrice = subscribeDetail.getUnitPrice();
		if(null!= unitPrice && unitPrice != 0){
	    	 detail.setUnitPrice(unitPrice);
	    	 detail.setTaxPrice(unitPrice/1.17);
	    }else{
			detail.setUnitPrice(null);
		}
		//申购单明细\采购单明细总价
		Double totalPrice = subscribeDetail.getTotalPrice();
		if(null != totalPrice && totalPrice != 0){
			detail.setTotalPrice(totalPrice);
			detail.setTaxTotalPrice(totalPrice/1.17);
			
		}else{
			detail.setTotalPrice(null);
		}
		//需求日期
		if(null != subscribeDetail.getRequireDate()){
			detail.setReqDeliveryDate(subscribeDetail.getRequireDate());
		}
		//备注
		if(null != subscribeDetail.getComment()){
			detail.setComment(subscribeDetail.getComment());
		}
		//关联工装
		if(null != subscribeDetail.getTooling()){
			detail.setTooling(subscribeDetail.getTooling());
		}
		//单位
		if(null != subscribeDetail.getCalUnit()){
			detail.setCalUnit(subscribeDetail.getCalUnit());
		}else{
			detail.setCalUnit(null);
		}
       // 申购单明细中的品名保存到采购单明细中		
		if(null != subscribeDetail.getName()){
			detail.setName(subscribeDetail.getName());	
		}else{
			detail.setName(null);	
		}
       //申购单明细中的部门保存到采购单明细中									
		detail.setDepartment(subscribeDetail.getSubscribe().getDepartment().getName());	
		//申购单的部门id保存到采购单明细中的部门id
		detail.setDepart((subscribeDetail.getSubscribe().getDepartment()));
	    detail.getPurchaseBill().setSubmit(true);
	}

	/**
	 * 从设备管理/工装管理中的备件台帐-->采购单明细
	 */
	public void storPurchaseBillDetail(PurchaseBill purchaseBill, String addSpareAccountIds) {
		String[] addSpareAccountId = null;
		if(addSpareAccountIds != null){
			addSpareAccountId = addSpareAccountIds.split(",");
		}
		for(int i=0;i<addSpareAccountId.length;i++){
			PurchaseBillDetail  detail=new PurchaseBillDetail(); // ;获得设备管理中的备件台帐的明细对象
			Spare spare = spareDao.loasSpare(Long.valueOf(addSpareAccountId[i]));
			detail.setSpare(spare);
			detail.setPurchaseBill(purchaseBill);
			copySpareAccountToSubscribeDetail(detail,spare);// 从备件台帐复制到采购单明细中
			purchaseBill.getPurchaseBillDetails().add(detail);// 将再次添加的采购单明细添加到采购单中
			this.storePurchaseBillDetail(detail);
			purchaseBillManager.storePurchaseBill(purchaseBill);
		}
	}
	
	/**
	 * 工装管理中的备件台帐-->工装采购合同明细
	 */
	public void storPurchaseBillContractDetail(PurchaseBill purchaseBill, String addSpareAccountIds) {
		String[] addSpareAccountId = null;
		if(addSpareAccountIds != null){
			addSpareAccountId = addSpareAccountIds.split(",");
		}
		for(int i=0;i<addSpareAccountId.length;i++){
			PurchaseBillDetail  detail=new PurchaseBillDetail(); // ;获得设备管理中的备件台帐的明细对象
			Spare spare = spareDao.loasSpare(Long.valueOf(addSpareAccountId[i]));
			detail.setSpare(spare);
			
			detail.setCategory(spare.getCategory());
			detail.setCategoryName(spare.getCategory().getName());
			detail.setDetailCategory(spare.getSpareDetailType());
			detail.setDetailCategoryName(spare.getSpareDetailType().getName());
			
			detail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
			detail.setSpecification(spare.getSpecification());
			detail.setModel(spare.getModelSpecs());
			detail.setAmount(spare.getStocks().intValue());
			
//			if(null != spare.getUnitPrice()){
//				detail.setUnitPrice(spare.getUnitPrice());
//			}
			
			
			detail.setComment(spare.getComment());
			detail.setReqDeliveryDate(new Date());
			detail.setPurchaseBill(purchaseBill);
			copySpareAccountToSubscribeDetail(detail,spare);// 从备件台帐复制到采购单明细中
			purchaseBill.getPurchaseBillDetails().add(detail);// 将再次添加的采购单明细添加到采购单中
			this.storePurchaseBillDetail(detail);
			purchaseBillManager.storePurchaseBill(purchaseBill);
		}
	}
	/**
	 * 复制备件到采购单明细
	 * @param detail
	 * @param spare
	 */
	private void copySpareAccountToSubscribeDetail(PurchaseBillDetail detail,Spare spare){
		 if(spare.getSpareNo()!= null){	// 复制编号
				detail.setGraphNo(spare.getSpareNo());
			}
			if(spare.getName()!= null){		// 复制名称
				detail.setName(spare.getName());
			}else{
				detail.setName(null);
			}
			if(spare.getModelSpecs()!= null){		// 复制型号
				detail.setModel(spare.getModelSpecs());
			}else{
				detail.setModel(null);
			}
			if(spare.getSpecification()!= null){		// 复制规格
				detail.setSpecification(spare.getSpecification());
			}else{
				detail.setSpecification(null);
			}
			if(spare.getUnitPrice()!= null && spare.getUnitPrice() != 0){		// 复制价格
				
				detail.setUnitPrice(spare.getUnitPrice());
			}else{
				detail.setUnitPrice(null);
			}
			//复制 种类
			if(spare.getCategory()!=null){
				detail.setCategory(spare.getCategory());
				if(spare.getCategory().getName()!=null){
					detail.setCategoryName(spare.getCategory().getName());
				}
			}
			//复制 明细种类
			if(null != spare.getSpareDetailType()){
				detail.setDetailCategory(spare.getSpareDetailType());
				if(null != spare.getSpareDetailType().getName()){
					detail.setDetailCategoryName(spare.getSpareDetailType().getName());
				}
			}
			
			//复制 厂家
			if(null != spare.getFactory()){
				detail.setFactory(spare.getFactory());
			}
			
			if(spare.getUnit()!=null){
				detail.setCalUnit(spare.getUnit());
			}
			detail.getPurchaseBill().setSubmit(true);
	 }
	/**
	 * 保存[设备]采购单明细
	 */
	public void storPurchaseBillDetail(PurchaseBill purchaseBill, String addPurchaseBillDtlIds, 
			                           String addPurchaseBillDetailAmountNumber, String addPurchaseBillDetailUnitPrice, 
			                           String addPurchaseBillDetailRequestDate, String addPurchaseBillDetailComment,User user) {
            String[] dtlIdArr = null;
            String[] amountNumberArr = null;
            String[] utitPriceArr = null;
            String[] requestDateArr = null;
            String[] commentArr = null;
            // 采购单明细的所有ids
            if(null!=addPurchaseBillDtlIds){
            	dtlIdArr=addPurchaseBillDtlIds.split(",");     
            }
            // 采购单明细的所有数量
            if(null!=addPurchaseBillDetailAmountNumber){
            	amountNumberArr=addPurchaseBillDetailAmountNumber.split(",");
            }
            // 采购单明细的所有单价
           if(null!=addPurchaseBillDetailUnitPrice){
        	   utitPriceArr=addPurchaseBillDetailUnitPrice.split(",");
           }	
           // 采购单明细的所有期望到货日期
           if(null!=addPurchaseBillDetailRequestDate){
        	   requestDateArr=addPurchaseBillDetailRequestDate.split(","); 
           }
           // 采购单明细中所有的备注
           if(null!=addPurchaseBillDetailComment){
        	   commentArr= addPurchaseBillDetailComment.split(",");
           }
                  
           updatePurchaseBillDetail(dtlIdArr,amountNumberArr,
        			  				utitPriceArr,requestDateArr,
        			  				commentArr, user,purchaseBill);
           updatePurchaseBillStatus(purchaseBill);
    }
	private void updatePurchaseBillDetail(String[] dtlIdArr,
		   								  String[] amountNumberArr,
		   								  String[] utitPriceArr,
		   								  String[] requestDateArr, 
		   								  String[] commentArr,User user,PurchaseBill purchaseBill){
	   // 采购明细循环次数
	   int purchaseBillDtlIdNum = 0;
	   while(dtlIdArr!=null&&purchaseBillDtlIdNum<dtlIdArr.length){
		   PurchaseBillDetail detail = purchaseBillDetailDao.loadPurchaseBillDetail(Long.valueOf(dtlIdArr[purchaseBillDtlIdNum]));
		   
		   SubscribeDetail subscribeDetail = detail.getSubscribeDetail();//申购单明细;
		   if(null != subscribeDetail){
			   subscribeDetail.setSupplierName(purchaseBill.getSupplier().getName());//保存申购明细的供应商
			   //跟新汇总单的金额
			   SubscribeCollectBill subscribeCollectBill = subscribeDetail.getSubscribeCollectBill();
			   if(null != subscribeCollectBill){
				   subscribeCollectBill.setTotalMoney(this.subscribeDao.getSumPrice(subscribeCollectBill));
				   this.subscribeCollectBillDao.storeSubscribeCollectBill(subscribeCollectBill);
			   }
			   
		   }
        //保存数量
		   int amountNumber = 0;
		   if(amountNumberArr!=null){
			   for(int i=0;i<amountNumberArr.length;i=i+2){
				   if(amountNumberArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])) { 
					  amountNumber = Integer.parseInt(amountNumberArr[i+1]);
					   detail.setAmount(amountNumber);
					   if(null != subscribeDetail){
						   subscribeDetail.setBuyer(user);
						   //如果采购数量 等于 申购数量
						   if(amountNumber == subscribeDetail.getAmount().intValue()){
							   subscribeDetail.setStatus(SubscribeDetailStatus.PURCHASEED);
						   }
						   subscribeDetail.setBuyeAmount(amountNumber);//同步到申购单明细的采购数量 
						   subscribeDetail.setPurchaseDate(new Date());
						    
					   }
					  
					   break;
				   }else{
					   detail.setAmount(null);
				   }
			   }
		   }
		   //保存单价
		   Double utitPrice = 0.0;
		   if(utitPriceArr!=null){
			   for(int i=0;i<utitPriceArr.length;i=i+2){
				   if(utitPriceArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
					   utitPrice = Double.parseDouble(utitPriceArr[i+1]);
					   detail.setUnitPrice(utitPrice);
					   detail.setTaxPrice(utitPrice/1.17);
					   if(null !=subscribeDetail){
						   subscribeDetail.setUnitPrice(utitPrice);//同步到申购单明细的单价
					   }
					 
					   break;
				   }else{
					   detail.setUnitPrice(null);
				   }
			   }
		   }
		   //计算出总价
			Double totalPrice = 0.0;
			if(amountNumber!=0&&utitPrice!=0.0){
				totalPrice = amountNumber*utitPrice;
				detail.setTotalPrice(totalPrice);
				detail.setTaxTotalPrice(totalPrice/1.17);
				 if(null != subscribeDetail){
					 subscribeDetail.setTotalPrice(totalPrice);//同步到申购单明细的总价
				 }
				
			}else{
				detail.setTotalPrice(totalPrice);
			}
			
		   //保存期望到货日期/交货日期
		   if(requestDateArr!=null){
			   for(int i=0;i<requestDateArr.length;i=i+2){
				   if(requestDateArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
					   detail.setReqDeliveryDate(DateUtil.toDate(requestDateArr[i+1],"yyyy-MM-dd"));
					   if(null != subscribeDetail){
						   subscribeDetail.setRequireDate(DateUtil.toDate(requestDateArr[i+1],"yyyy-MM-dd"));
					   }
					   break;
				   }else{
					   detail.setReqDeliveryDate(null);
				   }
			   }
		   }
		  //保存备注
		  if(commentArr!=null){
			  for(int i=0;i<commentArr.length;i=i+2){
				  if(commentArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
					  detail.setComment(commentArr[i+1]);
					  if(null != subscribeDetail){
						  subscribeDetail.setComment(commentArr[i+1]);//同步到申购单明细的备注
					  }
					  
					  break;
				  }else{
					  detail.setComment(null); 
				  }
			  }
		  }else{
			  detail.setComment(null);
		  }
		  purchaseBillDetailDao.storePurchaseBillDetail(detail);
		  updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());
		  detail.getPurchaseBill().setSubmit(true);
		  purchaseBillDetailDao.storePurchaseBillDetail(detail); 
		  if(null != subscribeDetail){
			  this.subscribeDao.storeSubscribeDetail(subscribeDetail);
			  //同步更新 申购单
			  this.updateSubscribeStatus(subscribeDetail.getSubscribe());
			  //同步 更新 汇总单
			  
			  this.updateSubscribeCollectBill(subscribeDetail.getSubscribeCollectBill());
			  //添加 事件 zzb
			  this.subscribeManager.storeEventNew_subDetail(1062, subscribeDetail.getId().intValue(), "更新采购");
		  }
 
		 
		  purchaseBillDtlIdNum++;
	   }
	}
	
	/**
	 * 更新 汇总单 状态
	 * @param collBill
	 */
	public void updateSubscribeCollectBill(SubscribeCollectBill collBill){
		List<SubscribeDetail> list;
		int newNum=0;//新建
		int purchaseNum=0;//已采购
		int inspectingNum=0;//入库中
		int inspectedNum=0;//已入库
		if(null !=collBill){
			try {
				list = this.subscribeDao.loadByKey("subscribeCollectBill", collBill.getId());
				if(null!=list && list.size()>0){
					int size = list.size();
					for(SubscribeDetail detail : list){
						if(SubscribeDetailStatus.NEW.equals(detail.getStatus())){
							newNum++;
						}if(SubscribeDetailStatus.PURCHASEED.equals(detail.getStatus())){
							purchaseNum++;
						}if(SubscribeDetailStatus.INSPECTING.equals(detail.getStatus())){
							inspectingNum++;
						}if(SubscribeDetailStatus.INSPECTED.equals(detail.getStatus())){
							inspectedNum++;
						}
					}
					if(size == newNum){
						collBill.setBillStatus(SubscribeCollectBillTypeStatus.NEW);
					}else if(size == purchaseNum){
						collBill.setBillStatus(SubscribeCollectBillTypeStatus.PURCHASEED);
					}else if(size == inspectingNum){
						collBill.setBillStatus(SubscribeCollectBillTypeStatus.INSPECTING);
					}else if(size == inspectedNum){
						collBill.setBillStatus(SubscribeCollectBillTypeStatus.INSPECTED);
					}else if(0==inspectingNum && 0==inspectedNum && purchaseNum>0){
						collBill.setBillStatus(SubscribeCollectBillTypeStatus.PURCHASING);
					}else{
						collBill.setBillStatus(SubscribeCollectBillTypeStatus.INSPECTING);
					}
					
					 this.subscribeCollectBillDao.storeSubscribeCollectBill(collBill);
					
				}
			} catch (DaoException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	/**
	 * 保存[工装]采购合同明细
	 */
	public void storPurchaseContractDetail(PurchaseBill purchaseBill, String addPurchaseBillDtlIds, 
			                           String addPurchaseBillDetailAmountNumber, String addPurchaseBillDetailUnitPrice, 
			                           String addPurchaseBillDetailRequestDate,String addPurchaseContractDetailComment) {
            String[] dtlIdArr = null;
            String[] amountNumberArr = null;
            String[] utitPriceArr = null;
            String[] requestDateArr = null;
            String[] commentArr=null;
            // 采购单明细的所有ids
            if(null!=addPurchaseBillDtlIds){
            	dtlIdArr=addPurchaseBillDtlIds.split(",");     
            }
            // 采购单明细的所有数量
            if(null!=addPurchaseBillDetailAmountNumber){
            	amountNumberArr=addPurchaseBillDetailAmountNumber.split(",");
            }
            // 采购单明细的所有单价
           if(null!=addPurchaseBillDetailUnitPrice){
        	   utitPriceArr=addPurchaseBillDetailUnitPrice.split(",");
           }	
           // 采购单明细的所有期望到货日期
           if(null!=addPurchaseBillDetailRequestDate){
        	   requestDateArr=addPurchaseBillDetailRequestDate.split(","); 
           }
           if(null!=addPurchaseContractDetailComment){
        	   commentArr =addPurchaseContractDetailComment.split(",");
           }
           updatePurchaseContractDetail(dtlIdArr,amountNumberArr,
        			  				utitPriceArr,requestDateArr,commentArr);
           updatePurchaseBillStatus(purchaseBill);
    }
	private void updatePurchaseContractDetail(String[] dtlIdArr,
				  String[] amountNumberArr,
				  String[] utitPriceArr,
				  String[] requestDateArr, String[] commentArr){
		 // 采购明细循环次数
		   int purchaseBillDtlIdNum = 0;
		   while(dtlIdArr!=null&&purchaseBillDtlIdNum<dtlIdArr.length){
			   PurchaseBillDetail detail = purchaseBillDetailDao.loadPurchaseBillDetail(Long.valueOf(dtlIdArr[purchaseBillDtlIdNum]));
			   //保存数量
			   int amountNumber = 0;
			   if(amountNumberArr!=null){
				   for(int i=0;i<amountNumberArr.length;i=i+2){
					   if(amountNumberArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])) { 
						  amountNumber = Integer.parseInt(amountNumberArr[i+1]);
						   detail.setAmount(amountNumber);
						   break;
					   }else{
						   detail.setAmount(null);
					   }
				   }
			   }
			   //保存单价
			   Double utitPrice = 0.0;
			   if(utitPriceArr!=null){
				   for(int i=0;i<utitPriceArr.length;i=i+2){
					   if(utitPriceArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
						   utitPrice = Double.parseDouble(utitPriceArr[i+1]);
						   detail.setUnitPrice(utitPrice);
						   break;
					   }else{
						   detail.setUnitPrice(null);
					   }
				   }
			   }
			   //计算出总价
				Double totalPrice = 0.0;
				if(amountNumber!=0&&utitPrice!=0.0){
					totalPrice = amountNumber*utitPrice;
					detail.setTotalPrice(totalPrice);
				}else{
					detail.setTotalPrice(totalPrice);
				}
				
			   //保存期望到货日期 交货日期
			   if(requestDateArr!=null){
				   for(int i=0;i<requestDateArr.length;i=i+2){
					   if(requestDateArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
						   detail.setReqDeliveryDate(DateUtil.toDate(requestDateArr[i+1],"yyyy-MM-dd"));
						   break;
					   }else{
						   detail.setReqDeliveryDate(null);
					   }
				   }
			   }
			   if(detail.getDetailType().equals(YearPlanDetailCategory.SPARE_PURCHASE)){
				   if(commentArr!=null){
					   for(int i=0;i<commentArr.length;i=i+2){
						   if(commentArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
							   detail.setComment(commentArr[i+1]);
							   break;
						   }else{
							   detail.setComment(null);
						   }
					   }
				   }else{
					   detail.setComment(null);
				   }
			   }else{
				   if(commentArr!=null){
					   for(int i=0;i<commentArr.length;i=i+2){
						   if(commentArr[i].equals(dtlIdArr[purchaseBillDtlIdNum])){
							   detail.setComment(commentArr[i+1]);
							   break;
						   }else{
							   detail.setComment(null);
						   }
					   }
				   }
			   }
			  purchaseBillDetailDao.storePurchaseBillDetail(detail);
			  updatePurchaseBillDtlTotalPrice(detail.getPurchaseBill());
			  detail.getPurchaseBill().setSubmit(true);
			  purchaseBillDetailDao.storePurchaseBillDetail(detail); 
			  purchaseBillDtlIdNum++;
			  if(detail.getPurchaseBill().getBudgetNo()!=null){
//				如果工装采购合同单的预算编号不为空，则加上该预算编号的采购合同的总费用
					BudgetDetail budgetDetail=budgetDetailManager.getYearBudgetDetailByBudgetNo(detail.getPurchaseBill().getBudgetNo());
					Double allPrice=purchaseBillDetailDao.getPurchaseDetailTotalByPurchaseId(detail.getPurchaseBill().getId());
					budgetDetail.setPurchaseContractFee(allPrice);
					this.budgetDetailManager.storeBudgetDetail(budgetDetail);
				}
		   }
	}
	public BudgetDetailManager getBudgetDetailManager() {
		return budgetDetailManager;
	}
	public void setBudgetDetailManager(BudgetDetailManager budgetDetailManager) {
		this.budgetDetailManager = budgetDetailManager;
	}
	public List loadAllPurchaseBillDtlBySubDtlId(Long id) {
		return purchaseBillDetailDao.loadAllPurchaseBillDtlBySubDtlId(id);
	}
	public void accordingSpareInWarehouseDetailChangePurchaseBillStatus(PurchaseBillDetail purchaseBillDetail) {
		this.updatePurchaseBillStatus(purchaseBillDetail.getPurchaseBill());
	}

	/**
	 * 出入单价就保存 dwr
	 * @param str 页面字段值
	 */
	public void updateParameterFromPage(String[] param) {
		
		if(null != param){
			 String tag = param[0];
			 Long pId = Long.valueOf(param[1]);
			 PurchaseBillDetail detail =  this.purchaseBillDetailDao.loadPurchaseBillDetail(pId);
			 //'N' 代表采购数量
			 if("N".equals(tag)){
				 int num = Integer.parseInt(param[2]);
				 detail.setAmount(num);
				 if(null!=param[3]){
					 Double unitPrice = Double.parseDouble(param[3]);
					 detail.setTaxPrice(unitPrice/1.17);
					 detail.setTotalPrice(unitPrice*num);
					 detail.setTaxTotalPrice((unitPrice/1.17)*(num));
				 }
				 if(null == param[3] && null!=param[4]){
					 Double taxPrice = Double.parseDouble(param[2]);
					 detail.setUnitPrice(taxPrice*1.17);
					 detail.setTaxPrice(taxPrice);
					 detail.setTotalPrice((taxPrice*1.17)*num);
					 detail.setTaxTotalPrice(taxPrice*(num));
				 }
				 if(null == param[3] && null==param[4]){
					  
					 detail.setTotalPrice(0.0);
					 detail.setTaxTotalPrice(0.0);
				 }
			 }
			 //'P' 代表税后单价
			 if("P".equals(tag)){
				 Double unitPrice = Double.parseDouble(param[2]);
				 detail.setUnitPrice(unitPrice);
				 detail.setTaxPrice(unitPrice/1.17);
				 if(null != param[3] && !"".equals(param[3])){
					 Integer number = Integer.parseInt(param[3]);
					 detail.setTotalPrice(unitPrice*number);
					 detail.setTaxTotalPrice((unitPrice/1.17)*(number));
				 }else{
					 detail.setTotalPrice(0.0);
					 detail.setTaxTotalPrice(0.0);
				 }
				 
			 }
			 //'T'代表税前单价
			 if("T".equals(tag)){
				 Double taxPrice = Double.parseDouble(param[2]);
				 detail.setTaxPrice(taxPrice);
				 detail.setUnitPrice(taxPrice*1.17);
				 if(null != param[3] && !"".equals(param[3])){
					 Integer number = Integer.parseInt(param[3]);
					 detail.setTotalPrice((taxPrice*1.17)*number);
					 detail.setTaxTotalPrice(taxPrice*(number));
				 }else{
					 detail.setTotalPrice(0.0);
					 detail.setTaxTotalPrice(0.0);
				 }
			 }
			 //'D' 代表交货日期
			 if("D".equals(tag)){
				 detail.setReqDeliveryDate(DateUtil.toDate(param[2],"yyyy-MM-dd"));
			 }
			
			 this.purchaseBillDetailDao.storePurchaseBillDetail(detail);
		}
	}
	
	
	public List<PurchaseBillDetail> loadByKey(String key, Object value) throws DaoException{
		return this.purchaseBillDetailDao.loadByKey(key, value);
	}
	
	public List<PurchaseBillDetail> loadByKeyArry(String[] keys, Object[] values) throws DaoException{
		return this.purchaseBillDetailDao.loadByKeyArry(keys, values);
	}
	
	/**
	 * 更新 采购单的 采购项、入库项
	 * @param purchaseBill
	 */
	public void updateNnmber(PurchaseBill purchaseBill){
		try {
			
			String[] keys= {"status","purchaseBill"};
			Object[] values = {"INSPECTED",purchaseBill.getId()};
			List<PurchaseBillDetail> list = this.purchaseBillDetailDao.loadByKeyArry(keys, values);
			purchaseBill.setInsNum(list.size());
			purchaseBill.setSumDetail(purchaseBill.getPurchaseBillDetails().size());
			this.purchaseBillManager.storePurchaseBill(purchaseBill);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 根据采购明细ID集合获取对应的申购明细ID集合
	 * @param purchaseBill
	 */
	public String updSubDetailIds(String PurchaseBillIds){
		return purchaseBillDetailDao.updSubDetailIds(PurchaseBillIds);
	}

	/**
	 * 根据传入的采购单明细ID集合，更新申购单、汇总单的采购项
	 * 
	 * @param PurchaseBills 采购单明细ID集合
	 * @return
	 */
	public void updStatus(String PurchaseBillIds) {
		this.purchaseBillDetailDao.updStatus(PurchaseBillIds);
	}
}
