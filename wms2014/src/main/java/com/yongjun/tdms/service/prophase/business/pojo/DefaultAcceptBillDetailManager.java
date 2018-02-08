package com.yongjun.tdms.service.prophase.business.pojo;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.device.DeviceCardDao;
import com.yongjun.tdms.dao.asset.spare.SpareDao;
import com.yongjun.tdms.dao.base.org.DepartmentDao;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDao;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDetailDao;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDetailViewDao;
import com.yongjun.tdms.dao.prophase.business.PurchaseBillDetailDao;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.prophase.business.AcceptBill;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetail;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetailView;
import com.yongjun.tdms.model.prophase.business.AcceptEstablishAccount;
import com.yongjun.tdms.model.prophase.business.AcceptResult;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDetail;
import com.yongjun.tdms.model.prophase.business.PurchaseBillDtlStatus;
import com.yongjun.tdms.model.year.tooling.yearPlan.YearPlanDetailCategory;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.prophase.business.AcceptBillDetailManager;
import com.yongjun.tdms.service.prophase.business.PurchaseBillDetailManager;

public class DefaultAcceptBillDetailManager extends BaseManager implements AcceptBillDetailManager{
   private final AcceptBillDetailDao acceptBillDetailDao;
   private final SpareDao spareDao;
   private final PurchaseBillDetailManager purchaseBillDetailManager; 
   private final PurchaseBillDetailDao purchaseBillDetailDao;
   private final AcceptBillDao acceptBillDao;
   private final AcceptBillDetailViewDao acceptBillDetailViewDao;
   private final DeviceCardDao deviceCardDao;
   private final DepartmentManager departmentManager;
   private final CodeValueManager codeValueManager;
   public DefaultAcceptBillDetailManager(AcceptBillDetailDao acceptBillDetailDao,SpareDao spareDao
		   ,PurchaseBillDetailManager purchaseBillDetailManager,PurchaseBillDetailDao purchaseBillDetailDao,
		   AcceptBillDao acceptBillDao,AcceptBillDetailViewDao acceptBillDetailViewDao,DeviceCardDao deviceCardDao,
		   DepartmentManager departmentManager,CodeValueManager codeValueManager){
	   this.acceptBillDetailDao=acceptBillDetailDao;
	   this.spareDao=spareDao;
	   this.purchaseBillDetailManager=purchaseBillDetailManager;
	   this.purchaseBillDetailDao=purchaseBillDetailDao;
	   this.acceptBillDao=acceptBillDao;
	   this.acceptBillDetailViewDao = acceptBillDetailViewDao;
	   this.deviceCardDao=deviceCardDao;
	   this.departmentManager=departmentManager;
	   this.codeValueManager=codeValueManager;
   }
	public AcceptBillDetail loadAcceptBillDetail(Long AcceptBillDetailId) {
		    
		return acceptBillDetailDao.loadAcceptBillDetail(AcceptBillDetailId);
	}

	public List<AcceptBillDetail> loadAcceptBillDetais(Long[] AcceptBillDetaiIds) {
		
		return acceptBillDetailDao.loadAcceptBillDetais(AcceptBillDetaiIds);
	}

	public void storeAcceptBillDetail(AcceptBillDetail acceptBillDetai) {
		acceptBillDetailDao.storeAcceptBillDetail(acceptBillDetai);
		
	}
	public void storeToolingAcceptBillToolingMakeDetail(AcceptBillDetail acceptBillDetai) {
		acceptBillDetailDao.storeToolingAcceptBillToolingMakeDetail(acceptBillDetai);
		
	}
	public void deleteAcceptBillDetail(AcceptBillDetail acceptBillDetail) {
		acceptBillDetailDao.deleteAcceptBillDetail(acceptBillDetail);
		
	}

	public void deleteAllAcceptBillDetails(Collection<AcceptBillDetail> AcceptBillDetails) {
		
		acceptBillDetailDao.deleteAllAcceptBillDetails(AcceptBillDetails);
		
	}

	//改变验收单发送提醒的状态
	public void chanageSubmitStatus(AcceptBill acceptBill){
		acceptBill.setSubmit(true);
		acceptBillDao.storeAcceptBill(acceptBill);
	}
	public void deleteToolingAllAcceptBillMakeDetails(Collection<AcceptBillDetail> AcceptBillDetails)throws Exception {
		//deleteAcceptBillDetailfaileShootException(AcceptBillDetails);
		acceptBillDetailDao.deleteToolingAllAcceptBillMakeDetails(AcceptBillDetails);
		//删除验收单明细中的记录属于采购合同的 把采购单明细中的记录置为“未验收”
		for(AcceptBillDetail detail:AcceptBillDetails){
			if(detail.getPurchaseContractDtl()!=null){
				detail.getPurchaseContractDtl().setStatus(PurchaseBillDtlStatus.NEW);
				this.purchaseBillDetailManager.storePurchaseBillDetail(detail.getPurchaseContractDtl());
			}
			//改变发送提醒状态
			this.chanageSubmitStatus(detail.getAcceptBill());
		}
	}
	//当验收单明细已经创建台帐之后 不能删除
	public void deleteAcceptBillDetailfaileShootException(Collection<AcceptBillDetail> AcceptBillDetails ,AcceptBill accept)throws Exception{
		for(AcceptBillDetail detail:AcceptBillDetails){
			if(detail.getEstablish().equals(AcceptEstablishAccount.ESTABLISHED)){
				throw new Exception();
			}
			accept.getAcceptBillDtl().remove(detail);
		}
	}
	//保存工装制作明细
	public void storeToolingMakeDetail(String allToolingMakeDtlId, String allToolingMakeDtlGroupNo,
			String allToolingMakeDtlUnitPrice, String allToolingMakeDtlAccount, 
			String allToolingMakeDtlExecResult,String allToolingMakeDtlEstablish,String allToolingMakeDtlDepartment) {
			
		String [] toolingMakeDtlId=null;
		String [] toolingMakeDtlGroupNo=null;
		String []toolingMakeDtlUnitPrice=null;
		String []toolingMakeDtlAccount=null;
		String []toolingMakeDtlExecResult=null;
		String []toolingMakeDtlEstablish=null;
		String []toolingMakeDtlDepartment=null;
		if(null!=allToolingMakeDtlId){
			toolingMakeDtlId=allToolingMakeDtlId.split(",");
		}
		if(null!=allToolingMakeDtlGroupNo){
			toolingMakeDtlGroupNo=allToolingMakeDtlGroupNo.split(",");
		}
		if(null!=allToolingMakeDtlUnitPrice){
			toolingMakeDtlUnitPrice=allToolingMakeDtlUnitPrice.split(",");
		}
		if(null!=allToolingMakeDtlAccount){
			toolingMakeDtlAccount=allToolingMakeDtlAccount.split(",");
		}
		if(null!=allToolingMakeDtlExecResult){
			toolingMakeDtlExecResult=allToolingMakeDtlExecResult.split(",");
		}
		if(null!=allToolingMakeDtlEstablish){
			toolingMakeDtlEstablish=allToolingMakeDtlEstablish.split(",");
		}
		if(null!=allToolingMakeDtlDepartment){
			toolingMakeDtlDepartment=allToolingMakeDtlDepartment.split(",");
		}
		updateStoreToolingMakeDetail(toolingMakeDtlId,toolingMakeDtlGroupNo,
				toolingMakeDtlUnitPrice,toolingMakeDtlAccount,
				toolingMakeDtlExecResult,toolingMakeDtlEstablish,toolingMakeDtlDepartment);
	}
  public void updateStoreToolingMakeDetail(String [] toolingMakeDtlId,String [] toolingMakeDtlGroupNo,String []toolingMakeDtlUnitPrice,
		  String [] toolingMakeDtlAccount,String [] toolingMakeDtlExecResult,String [] toolingMakeDtlEstablish,String [] toolingMakeDtlDepartment){
	     //工装制作明细执行的次数
	     int toolingMakeDetailNum=0;
	     while((toolingMakeDtlId!=null)&&(toolingMakeDetailNum<toolingMakeDtlId.length)){
	    	 AcceptBillDetail acceptBillDetail=acceptBillDetailDao.loadAcceptBillDetail(Long.valueOf(toolingMakeDtlId[toolingMakeDetailNum]));
	    	 acceptBillDetail.setJudageSave(true);
	    	 //保存图号
	    	 String groupNo=null;
	    	 if(null!=toolingMakeDtlGroupNo){
	    		 for(int i=0;i<toolingMakeDtlGroupNo.length;i=i+2){
	    			 if(toolingMakeDtlGroupNo[i].equals(toolingMakeDtlId[toolingMakeDetailNum])){
	    				 groupNo=toolingMakeDtlGroupNo[i+1].toString();
	    				 acceptBillDetail.setGraphNo(groupNo);
	    				 break;
	    			 }else{
	    				 acceptBillDetail.setGraphNo(groupNo);
	    			 }
			      }
	    	 }
//	    	 //保存单价
//	    		Double utitPrice = 0.0;
//	    	 if(null!=toolingMakeDtlUnitPrice){
//	    		 for(int i=0;i<toolingMakeDtlUnitPrice.length;i=i+2){
//	    			 if(toolingMakeDtlUnitPrice[i].equals(toolingMakeDtlId[toolingMakeDetailNum])){
//	    				 utitPrice=Double.valueOf(toolingMakeDtlUnitPrice[i+1]);
//	    				 acceptBillDetail.setUnitPrice(utitPrice);
//	    				 break;
//	    			 }else{
//	    				 acceptBillDetail.setUnitPrice(utitPrice);
//	    			 }
//			      }
//	    	 }
//	    		//保存数量
//				int amountNumber = 0;
//				if(toolingMakeDtlAccount!= null){
//					for(int i=0;i<toolingMakeDtlAccount.length;i=i+2){
//						if(toolingMakeDtlAccount[i].equals(toolingMakeDtlId[toolingMakeDetailNum])){
//							amountNumber = Integer.valueOf(toolingMakeDtlAccount[i+1]);
//							acceptBillDetail.setAmount(amountNumber);
//							break;
//						}else{
//							acceptBillDetail.setAmount(amountNumber);
//						}
//					}
//				}else{
//					acceptBillDetail.setAmount(amountNumber);
//				}
//            //计算出总价
//				Double totalPrice = 0.0;
//				if(amountNumber!=0&&utitPrice!=0.0){
//					totalPrice = amountNumber*utitPrice;
//					acceptBillDetail.setTotalPrice(totalPrice);
//					break;
//				}else{
//					acceptBillDetail.setTotalPrice(totalPrice);
//				}
	     //保存执行结果
	    	 if (null != toolingMakeDtlExecResult) {
					for (int i=0; i<toolingMakeDtlExecResult.length; i++) {     //设置明细中的执行结果
						if (toolingMakeDtlExecResult[i].equals(toolingMakeDtlId[toolingMakeDetailNum])) {
							if (toolingMakeDtlExecResult[i+1].equals(AcceptResult.QUALIFIED.toString())) {
								acceptBillDetail.setResult(AcceptResult.QUALIFIED);
							} else {
								acceptBillDetail.setResult(AcceptResult.DISQUALIFICATION);
							}
						}
					}
				}
//	     //保存创建台帐的值
//	    	 if (null != toolingMakeDtlEstablish) {
//					for (int i=0; i<toolingMakeDtlEstablish.length; i++) {     //设置明细中的创建台帐的值
//						if (toolingMakeDtlEstablish[i].equals(toolingMakeDtlId[toolingMakeDetailNum])) {
//							if (toolingMakeDtlEstablish[i+1].equals(AcceptEstablishAccount.UNESTABLISH.toString())) {
//								acceptBillDetail.setEstablish(AcceptEstablishAccount.UNESTABLISH);
//							} else {
//								acceptBillDetail.setEstablish(AcceptEstablishAccount.ESTABLISHED);
//							}
//						}
//					}
//				}
	    	 //保存部门的值
	    		if (null != toolingMakeDtlDepartment) {
					for (int i=0; i<toolingMakeDtlDepartment.length; i=i+2) {              //设置计划明细中的责任单位
						if (toolingMakeDtlDepartment[i].equals(toolingMakeDtlId[toolingMakeDetailNum])) {
							Department dept = this.departmentManager.loadDepartment(Long.valueOf(toolingMakeDtlDepartment[i+1]));
							acceptBillDetail.setDepartment(dept);
							acceptBillDetail.setDepartmentName(dept.getName());
						    break;
						} else {
							acceptBillDetail.setDepartment(null);
						}
					}
				} else {
					acceptBillDetail.setDepartment(null);
				}
	    	this.acceptBillDetailDao.storeToolingAcceptBillToolingMakeDetail(acceptBillDetail) ;
	    	toolingMakeDetailNum++;
	    	//改变发送提醒状态
			this.chanageSubmitStatus(acceptBillDetail.getAcceptBill());
	     }
      }
  //保存备件台帐到备件采购明细
public void storeSpareAccountToAcceptSparePurchaseDetail(AcceptBill accept, String spareAccountIds) {
	String [] allSpareAccountIds=null;
	if(spareAccountIds!=null){
		allSpareAccountIds=spareAccountIds.split(",");
	}
	for(int i=0;i<allSpareAccountIds.length;i++){
		AcceptBillDetail detail=new AcceptBillDetail();
		Spare spare=spareDao.loasSpare(Long.valueOf(allSpareAccountIds[i]));
		detail.setSpare(spare);
		detail.setAcceptBill(accept);
		detail.setDetailType(YearPlanDetailCategory.SPARE_PURCHASE);
        //从备件台帐复制到验收单明细中
		copySpareAccountToAcceptSparePurchaseDetail(detail,spare);
		accept.getAcceptBillDtl().add(detail);
        //改变发送提醒状态
		this.chanageSubmitStatus(detail.getAcceptBill());
		acceptBillDetailDao.storeToolingAcceptBillToolingMakeDetail(detail);
	}
}
   public void copySpareAccountToAcceptSparePurchaseDetail(AcceptBillDetail detail,Spare spare){
		if(spare.getSpareNo()!= null){	//复制编号
			detail.setGraphNo(spare.getSpareNo());
		}
		if(spare.getName()!= null){		//复制名称
			detail.setName(spare.getName());
		}else{
			detail.setName(null);
		}
		if(spare.getModelSpecs()!= null){		//复制型号
			detail.setModel(spare.getModelSpecs());
		}else{
			detail.setModel(null);
		}
		if(spare.getSpecification()!= null){		//复制规格
			detail.setSpecification(spare.getSpecification());
		}else{
			detail.setSpecification(null);
		}
		if(spare.getUnitPrice()!= null){		//复制价格
			detail.setUnitPrice(spare.getUnitPrice());
		}else{
			detail.setUnitPrice(null);
		}
	    if(spare.getComment()!=null){
		    detail.setMemo(spare.getComment());
	     }else{
	    	 detail.setMemo(null);
	    }
	    if(spare.getUnit()!=null){
	    	detail.setCalUnit(spare.getUnit());
	    }
		if(spare.getCategory()!=null){
			detail.setCategory(spare.getCategory());
			if(spare.getCategory().getName()!=null){
				detail.setCategoryName(spare.getCategory().getName());
			}
		}
   }
   //保存备件采购明细
public void storeAcceptSparePurchaseDetail(AcceptBill accept, String allSparePurchaseDtlId, 
		String allSparePurchaseDtlUnitPrice, 
		String allSparePurchaseDtlAmount, 
		String allPreRepairProcExecResult, 
		String allSparePurchaseDtlMemo) {
	String [] addSparePurchaseDtlId=null;
	String [] addSparePurchaseDtlUnitPrice=null;
	String [] addSparePurchaseDtlAmount=null;
	String [] addPreRepairProcExecResult=null;
	String [] addSparePurchaseDtlMemo=null;
	if(allSparePurchaseDtlId!=null){
		addSparePurchaseDtlId=allSparePurchaseDtlId.split(",");
	}
	if(allSparePurchaseDtlUnitPrice!=null){
		addSparePurchaseDtlUnitPrice=allSparePurchaseDtlUnitPrice.split(",");
	}
	if(allSparePurchaseDtlAmount!=null){
		addSparePurchaseDtlAmount=allSparePurchaseDtlAmount.split(",");
	}
	if(allPreRepairProcExecResult!=null){
		addPreRepairProcExecResult=allPreRepairProcExecResult.split(",");
	}
	if(allSparePurchaseDtlMemo!=null){
		addSparePurchaseDtlMemo=allSparePurchaseDtlMemo.split(",");
	}
	updateStoreAcceptSparePurchaseDetail(accept,addSparePurchaseDtlId,addSparePurchaseDtlUnitPrice,
			addSparePurchaseDtlAmount,addPreRepairProcExecResult,
			addSparePurchaseDtlMemo);
}
public void updateStoreAcceptSparePurchaseDetail(AcceptBill accept,String []addSparePurchaseDtlId, 
		String [] addSparePurchaseDtlUnitPrice,String [] addSparePurchaseDtlAmount,String [] addPreRepairProcExecResult,
		String [] addSparePurchaseDtlMemo){
	  int sparePurchaseDtlNum=0; //标识备件采购明细的循环次数
	  while(sparePurchaseDtlNum<addSparePurchaseDtlId.length&&addSparePurchaseDtlId!=null){
		   	 AcceptBillDetail acceptBillDetail=acceptBillDetailDao.loadAcceptBillDetail(Long.valueOf(addSparePurchaseDtlId[sparePurchaseDtlNum]));
//	    	 //保存单价
//	    		Double utitPrice = 0.0;
//	    	 if(null!=addSparePurchaseDtlUnitPrice){
//	    		 for(int i=0;i<addSparePurchaseDtlUnitPrice.length;i=i+2){
//	    			 if(addSparePurchaseDtlUnitPrice[i].equals(addSparePurchaseDtlId[sparePurchaseDtlNum])){
//	    				 utitPrice=Double.valueOf(addSparePurchaseDtlUnitPrice[i+1]);
//	    				 acceptBillDetail.setUnitPrice(utitPrice);
//	    				 break;
//	    			 }else{
//	    				 acceptBillDetail.setUnitPrice(utitPrice);
//	    			 }
//			      }
//	    	 }
//	    		//保存数量
//				int amountNumber = 0;
//				if(addSparePurchaseDtlAmount!= null){
//					for(int i=0;i<addSparePurchaseDtlAmount.length;i=i+2){
//						if(addSparePurchaseDtlAmount[i].equals(addSparePurchaseDtlId[sparePurchaseDtlNum])){
//							amountNumber = Integer.valueOf(addSparePurchaseDtlAmount[i+1]);
//							acceptBillDetail.setAmount(amountNumber);
//							break;
//						}else{
//							acceptBillDetail.setAmount(null);
//						}
//					}
//				}else{
//					acceptBillDetail.setAmount(amountNumber);
//				}
////				计算出总价
//				Double totalPrice = 0.0;
//				if(amountNumber!=0&&utitPrice!=0.0){
//					totalPrice = amountNumber*utitPrice;
//					acceptBillDetail.setTotalPrice(totalPrice);
//					break;
//				}else{
//					acceptBillDetail.setTotalPrice(totalPrice);
//				}
	    //保存执行结果
	    	 if (null != addPreRepairProcExecResult) {
					for (int i=0; i<addPreRepairProcExecResult.length; i++) {     //设置明细中的执行结果
						if (addPreRepairProcExecResult[i].equals(addSparePurchaseDtlId[sparePurchaseDtlNum])) {
							if (addPreRepairProcExecResult[i+1].equals(AcceptResult.QUALIFIED.toString())) {
								acceptBillDetail.setResult(AcceptResult.QUALIFIED);
							} else {
								acceptBillDetail.setResult(AcceptResult.DISQUALIFICATION);
							}
						}
					}
				}
	    	  //保存备注
	    	 if(null!=addSparePurchaseDtlMemo){
	    		 for(int i=0;i<addSparePurchaseDtlMemo.length;i=i+2){
	    			 if(addSparePurchaseDtlMemo[i].equals(addSparePurchaseDtlId[sparePurchaseDtlNum])){
	    				 acceptBillDetail.setMemo(addSparePurchaseDtlMemo[i+1]);
	    				 break;
	    			 }else{
	    				 acceptBillDetail.setMemo(null);
	    			 }
			      }
	    	 }else{
	    		 acceptBillDetail.setMemo(null);
	    	 }
	    	this.acceptBillDetailDao.storeToolingAcceptBillToolingMakeDetail(acceptBillDetail) ;
	    	sparePurchaseDtlNum++;
	    	this.chanageSubmitStatus(acceptBillDetail.getAcceptBill());
	  }
  }
//保存维修保养明细
public void storeAcceptRepairMaintenanceDetail(String repairMaintenanceDetailIds, String allPreRepairProcExecResult, String allRepairMaintenanceDtlMemo) {
	String [] allrepairMaintenanceDetailId=null;
	String [] allpreRepairProcExecResult=null;
	String [] allrepairMaintenanceDtlMemo=null;
	if(repairMaintenanceDetailIds!=null){
		allrepairMaintenanceDetailId=repairMaintenanceDetailIds.split(",");
	}
	if(allPreRepairProcExecResult!=null){
		allpreRepairProcExecResult=allPreRepairProcExecResult.split(",");
	}
	if(allRepairMaintenanceDtlMemo!=null){
		allrepairMaintenanceDtlMemo=allRepairMaintenanceDtlMemo.split(",");
	}
	updateAcceptRepairMaintenanceDetail(allrepairMaintenanceDetailId,allpreRepairProcExecResult,allrepairMaintenanceDtlMemo);
}
public void updateAcceptRepairMaintenanceDetail(String [] allrepairMaintenanceDetailId,String  [] allpreRepairProcExecResult,
		String [] allrepairMaintenanceDtlMemo){
	  int spareRepairMaintenanceNum=0; //标识备件采购明细的循环次数
	  while(allrepairMaintenanceDetailId!=null&&spareRepairMaintenanceNum<allrepairMaintenanceDetailId.length){
		  AcceptBillDetail acceptBillDetail=acceptBillDetailDao.loadAcceptBillDetail(Long.valueOf(allrepairMaintenanceDetailId[spareRepairMaintenanceNum]));
		  //保存执行结果
	    	 if (null != allpreRepairProcExecResult) {
					for (int i=0; i<allpreRepairProcExecResult.length; i++) {     //设置明细中的执行结果
						if (allpreRepairProcExecResult[i].equals(allrepairMaintenanceDetailId[spareRepairMaintenanceNum])) {
							if (allpreRepairProcExecResult[i+1].equals(AcceptResult.QUALIFIED.toString())) {
								acceptBillDetail.setResult(AcceptResult.QUALIFIED);
							} else {
								acceptBillDetail.setResult(AcceptResult.DISQUALIFICATION);
							}
						}
					}
				}
//	    	  //保存备注
//	    	 if(null!=allrepairMaintenanceDtlMemo){
//	    		 for(int i=0;i<allrepairMaintenanceDtlMemo.length;i=i+2){
//	    			 if(allrepairMaintenanceDtlMemo[i].equals(allrepairMaintenanceDetailId[spareRepairMaintenanceNum])){
//	    				 acceptBillDetail.setMemo(allrepairMaintenanceDtlMemo[i+1]);
//	    				 break;
//	    			 }else{
//	    				 acceptBillDetail.setMemo(null);
//	    			 }
//			      }
//	    	 }
	    	this.acceptBillDetailDao.storeToolingAcceptBillToolingMakeDetail(acceptBillDetail) ;
	    	spareRepairMaintenanceNum++;
	    	this.chanageSubmitStatus(acceptBillDetail.getAcceptBill());
	  }
  }
//保存技术改造明细
public void storeTechAlterAcceptDetail(String techAlterId, String techAlterResult, String techAlterMemo) {
	String [] alltechAlterId=null;
	String [] alltechAlterResult=null;
	String [] alltechAlterMemo=null;
	if(techAlterId!=null){
		alltechAlterId=techAlterId.split(",");
	}
	if(techAlterResult!=null){
		alltechAlterResult=techAlterResult.split(",");
	}
	if(techAlterMemo!=null){
		alltechAlterMemo=techAlterMemo.split(",");
	}
	updateTechAlterAcceptDetail(alltechAlterId,alltechAlterResult,alltechAlterMemo);
  }
public void updateTechAlterAcceptDetail(String []alltechAlterId,String [] alltechAlterResult,String [] alltechAlterMemo){
	 int techAlterNum=0;//技术改造明细的循环次数
	 while(alltechAlterId!=null&&techAlterNum<alltechAlterId.length){
		 AcceptBillDetail acceptBillDetail=acceptBillDetailDao.loadAcceptBillDetail(Long.valueOf(alltechAlterId[techAlterNum]));
		  //保存执行结果
    	 if (null != alltechAlterResult) {
				for (int i=0; i<alltechAlterResult.length; i++) {     //设置明细中的执行结果
					if (alltechAlterResult[i].equals(alltechAlterId[techAlterNum])) {
						if (alltechAlterResult[i+1].equals(AcceptResult.QUALIFIED.toString())) {
							acceptBillDetail.setResult(AcceptResult.QUALIFIED);
						} else {
							acceptBillDetail.setResult(AcceptResult.DISQUALIFICATION);
						}
					}
				}
			}
//    	  //保存备注
//    	 if(null!=alltechAlterMemo){
//    		 for(int i=0;i<alltechAlterMemo.length;i=i+2){
//    			 if(alltechAlterMemo[i].equals(alltechAlterId[techAlterNum])){
//    				 acceptBillDetail.setMemo(alltechAlterMemo[i+1]);
//    				 break;
//    			 }else{
//    				 acceptBillDetail.setMemo(null);
//    			 }
//		      }
//    	 }
    	 this.acceptBillDetailDao.storeToolingAcceptBillToolingMakeDetail(acceptBillDetail) ;
    	 techAlterNum++; 
    		this.chanageSubmitStatus(acceptBillDetail.getAcceptBill());
	 }
   }
   //保存从采购合同明细选择的【工装制作明细】【备件采购明细】【维修保养明细】【技术改造明细】
public void storePurchaseContractDtl(AcceptBill acceptBill, String toolingMakeDetailIds) {
	String [] alltoolingMakeDetailId=null;
	if(toolingMakeDetailIds!=null){
		alltoolingMakeDetailId=toolingMakeDetailIds.split(",");
	}
	PurchaseBillDetail detail = new PurchaseBillDetail();
	for(int i=0;i<alltoolingMakeDetailId.length;i++){
		AcceptBillDetail acceptBillDetail =new AcceptBillDetail();
		detail=purchaseBillDetailManager.loadPurchaseBillDetail(Long.valueOf(alltoolingMakeDetailId[i]));
		//如果从采购合同明细选择的记录是工装制作明细并且采购合同明细的图号不能为空
		if(detail.getDetailType().equals(YearPlanDetailCategory.TOOLING_MAKE)&&detail.getGraphNo()!=null){
			acceptBillDetail.setJudageSave(true);
		}
		//复制采购合同的部门置验收单明细的采购部门字段中
		acceptBillDetail.setDepartmentName(detail.getDepartment());
		acceptBillDetail.setDepartment(detail.getDepart());
		acceptBillDetail.setPurchaseContractDtl(detail);
		acceptBillDetail.setAcceptBill(acceptBill);
		acceptBillDetail.setDetailType(detail.getDetailType());
		updatePurchaseContractDtlToAcceptBillDetail(acceptBillDetail,detail);
		acceptBillDetail.setDetailType(detail.getDetailType());
		acceptBillDetail.setTooling(detail.getTooling());
		//采购合同明细的记录拷贝到验收单明细中改变采购合同明细的状态为“已采购”
		detail.setStatus(PurchaseBillDtlStatus.INSPECTED);
		acceptBillDetail.setToolingCategory(detail.getToolingCategory());    //设置工装类别id
		//改变发送提醒状态
		this.chanageSubmitStatus(acceptBillDetail.getAcceptBill());
		this.acceptBillDetailDao.storeAcceptBillDetail(acceptBillDetail);
		this.purchaseBillDetailDao.storePurchaseBillDetail(detail);
	}
	purchaseBillDetailManager.updatePurchaseBillStatus(detail.getPurchaseBill());
}
public void updatePurchaseContractDtlToAcceptBillDetail(AcceptBillDetail acceptBillDetail,PurchaseBillDetail detail){
	if(detail.getGraphNo()!=null){
		acceptBillDetail.setGraphNo(detail.getGraphNo());
	}else{
		acceptBillDetail.setGraphNo(null);
	}
	if(detail.getName()!=null){
		acceptBillDetail.setName(detail.getName());
	}else{
		acceptBillDetail.setName(null);
	}
	if(detail.getCategoryName()!=null){
		acceptBillDetail.setCategoryName(detail.getCategoryName());
	}else{
		acceptBillDetail.setCategoryName(null);
	}
	if(detail.getDetailCategoryName()!=null){
		acceptBillDetail.setDetailCategoryName(detail.getDetailCategoryName());
	}else{
		acceptBillDetail.setDetailCategoryName(null);
	}
	if(detail.getSpecification()!=null){
		acceptBillDetail.setSpecification(detail.getSpecification());
	}else{
		acceptBillDetail.setSpecification(null);
	}
	if(detail.getModel()!=null){
		acceptBillDetail.setModel(detail.getModel());
	}else{
		acceptBillDetail.setModel(null);
	}
	if(detail.getDetailCategory()!=null){
		acceptBillDetail.setDetailCategory(detail.getDetailCategory());
	}
	if(detail.getDetailCategoryName()!=null){
		acceptBillDetail.setDetailCategoryName(detail.getDetailCategoryName());
	}
	if(detail.getUnitPrice()!=null){
		acceptBillDetail.setUnitPrice(detail.getUnitPrice());
	}else{
		acceptBillDetail.setUnitPrice(0.0);
	}
	if(detail.getAmount()!=null){
		acceptBillDetail.setAmount(detail.getAmount());
	}else{
		acceptBillDetail.setAmount(0);
	}
	if(detail.getTotalPrice()!=null){
		acceptBillDetail.setTotalPrice(detail.getTotalPrice());
	}else{
		acceptBillDetail.setTotalPrice(0.0);
	}
	if(detail.getComment()!=null){
		acceptBillDetail.setMemo(detail.getComment());
	}else{
		acceptBillDetail.setMemo(null);
	}
	if(detail.getCalUnit()!=null){
		acceptBillDetail.setCalUnit(detail.getCalUnit());
	}else{
		acceptBillDetail.setCalUnit(null);
	}
  }
	/**
	 * 打印报表所用
	 */
	public List<AcceptBillDetailView> loadAcceptBillDetailView(Long id) {
		return acceptBillDetailViewDao.loadAcceptBillDetailView(id);
	}
	public void storeAcceptBillDetailToDeviceCard(DeviceCard deviceCard, AcceptBillDetail detail) {
		//copy验收单明细的图号到工装台帐中的工装图号
		
		if(detail.getGraphNo()!=null){
			  deviceCard.setDeviceNo(detail.getGraphNo());
		}else{
			 deviceCard.setDeviceNo(null);
		}
		//copy验收单明细的名称到工装台帐中的工装名称
		if(detail.getName()!=null){
			deviceCard.setName(detail.getName());
		}else{
			deviceCard.setName(null);
		}
		//copy验收单明细的规格到工装台帐中的规格
		if(detail.getSpecification()!=null){
			deviceCard.setSpecification(detail.getSpecification());
		}else{
			deviceCard.setSpecification(null);
		}
		//copy验收单明细的型号到工装台帐中的规格
		if(detail.getModel()!=null){
			deviceCard.setModel(detail.getModel());
		}else{
			deviceCard.setModel(null);
		}
		//copy验收单明细的数量到工装台帐中的数量
		if(detail.getAmount()!=null){
			deviceCard.setNumber(Long.valueOf(detail.getAmount()));
		}else{
			deviceCard.setNumber(null);
		}
		//copy验收单明细的计量单位到工装台帐中的计量单位
		if(detail.getCalUnit()!=null){
			deviceCard.setCalUnit(detail.getCalUnit());
		}else{
			deviceCard.setCalUnit(null);
		}
		//copy验收单明细的工装类别到工装台帐中的工装类别
		if(detail.getToolingCategory()!=null){
			deviceCard.setToolingType(detail.getToolingCategory());
		}else{
			deviceCard.setToolingType(null);
		}
		deviceCard.setAcceptBillDetail(detail);
		deviceCard.setToolingDevFlag(SysModel.TOOLING);
		//从codeValue表中获取工装状态为“待用”的值
		CodeValue waitUse=codeValueManager.loadCodeTypeByCode("0151");
		deviceCard.setToolingStatus(waitUse);
		deviceCard.setDepartment(detail.getDepartment());
		deviceCardDao.storeDevice(deviceCard);
		detail.setEstablish(AcceptEstablishAccount.ESTABLISHED);
		this.chanageSubmitStatus(detail.getAcceptBill());
	
		
		//this.acceptBillDetailDao.storeAcceptBillDetail(detail);
	}
	public void deleteToolingAllAcceptBillMakeDetails(Collection<AcceptBillDetail> AcceptBillDetails, AcceptBill acceptBill) throws Exception {
		deleteAcceptBillDetailfaileShootException(AcceptBillDetails,acceptBill);
		acceptBillDetailDao.deleteToolingAllAcceptBillMakeDetails(AcceptBillDetails);
	}

	
}
