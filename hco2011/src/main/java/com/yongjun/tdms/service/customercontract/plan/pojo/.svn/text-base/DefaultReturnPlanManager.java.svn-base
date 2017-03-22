/*     */ package com.yongjun.tdms.service.customercontract.plan.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.dao.codevalue.CodeValueDao;
/*     */ import com.yongjun.pluto.exception.DaoException;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.customercontract.contractmanagement.ContractManagementDao;
/*     */ import com.yongjun.tdms.dao.customercontract.plan.ReturnPlanDao;
/*     */ import com.yongjun.tdms.dao.financialmanagement.FinancialManagementDao;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
/*     */ import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
/*     */ import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
/*     */ import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class DefaultReturnPlanManager extends BaseManager
/*     */   implements ReturnPlanManager
/*     */ {
/*     */   private final ReturnPlanDao returnPlanDao;
/*     */   private final CodeValueDao codeValueDao;
/*     */   private final FinancialManagementDao financialManagementDao;
/*     */   private final ContractManagementDao contractManagementDao;
/*     */ 
/*     */   public DefaultReturnPlanManager(ReturnPlanDao returnPlanDao, CodeValueDao codeValueDao, FinancialManagementDao financialManagementDao, ContractManagementDao contractManagementDao)
/*     */   {
/*  51 */     this.returnPlanDao = returnPlanDao;
/*  52 */     this.codeValueDao = codeValueDao;
/*  53 */     this.financialManagementDao = financialManagementDao;
/*  54 */     this.contractManagementDao = contractManagementDao;
/*     */   }
/*     */ 
/*     */   public void storeReturnPlan(ReturnPlan returnPlan)
/*     */   {
/*  62 */     this.returnPlanDao.storeReturnPlan(returnPlan);
/*     */   }
/*     */ 
/*     */   public void deleteReturnPlan(ReturnPlan returnPlan)
/*     */   {
/*  70 */     this.returnPlanDao.deleteReturnPlan(returnPlan);
/*     */   }
/*     */ 
/*     */   public void deleteAllReturnPlans(Collection<ReturnPlan> returnPlans)
/*     */   {
/*  78 */     this.returnPlanDao.deleteAllReturnPlans(returnPlans);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadAllReturnPlans(Long[] returnPlanIds)
/*     */   {
/*  87 */     return this.returnPlanDao.loadAllReturnPlans(returnPlanIds);
/*     */   }
/*     */ 
/*     */   public ReturnPlan loadReturnPlan(Long returnPlanId)
/*     */   {
/*  96 */     return this.returnPlanDao.loadReturnPlan(returnPlanId);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadAllReturnPlans()
/*     */   {
/* 104 */     return this.returnPlanDao.loadAllReturnPlans();
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadByKey(String keyName, Object keyValue)
/*     */     throws DaoException
/*     */   {
/* 115 */     return this.returnPlanDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<ReturnPlan> loadByKeyArray(String[] keyNames, Object[] keyValues)
/*     */     throws DaoException
/*     */   {
/* 126 */     return this.returnPlanDao.loadByKeyArray(keyNames, keyValues);
/*     */   }
/*     */ 
/*     */   public void disabledAllReturnPlan(List<ReturnPlan> returnPlans)
/*     */   {
/* 133 */     for (ReturnPlan r : returnPlans) {
/* 134 */       r.setDisabled(true);
/* 135 */       this.returnPlanDao.storeReturnPlan(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllReturnPlan(List<ReturnPlan> returnPlans)
/*     */   {
/* 144 */     for (ReturnPlan r : returnPlans) {
/* 145 */       r.setDisabled(false);
/* 146 */       this.returnPlanDao.storeReturnPlan(r);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List<Object> contractManagementAndBatch(String contractManagementId, String batchsId, boolean isNew)
/*     */   {
/* 159 */     Long id = Long.valueOf(batchsId);
/* 160 */     List data = new ArrayList();
/* 161 */     CodeValue c = this.codeValueDao.loadCodeValue(id);
/* 162 */     List<ReturnPlan> list = this.returnPlanDao.contractManagementAndBatch(Long.valueOf(contractManagementId), Long.valueOf(batchsId), c.getCode(), isNew);
/*     */ 
/* 164 */     for (ReturnPlan r : list) {
/*     */       try {
/* 166 */         List fmts = this.financialManagementDao.loadByKey("contractManagement", Long.valueOf(contractManagementId));
/*     */ 
/* 168 */         if (null == fmts) {
/* 169 */           Object[] array = new Object[3];
/* 170 */           array[0] = r.getSum();
/* 171 */           array[1] = r.getSum();
/* 172 */           array[2] = Double.valueOf(r.getContractManagement().getContractMoney() - r.getSum().doubleValue());
/*     */ 
/* 174 */           data.add(array);
/*     */         } else {
/* 176 */           Object[] array = new Object[3];
/* 177 */           array[0] = r.getSum();
/* 178 */           array[1] = Double.valueOf(((FinancialManagement)fmts.get(0)).getTotalSum().doubleValue() + r.getSum().doubleValue());
/* 179 */           array[2] = Double.valueOf(r.getContractManagement().getContractMoney() - (((FinancialManagement)fmts.get(0)).getTotalSum().doubleValue() + r.getSum().doubleValue()));
/*     */ 
/* 181 */           data.add(array);
/*     */         }
/*     */       } catch (NumberFormatException e) {
/* 184 */         e.printStackTrace();
/*     */       } catch (DaoException e) {
/* 186 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 189 */     return data;
/*     */   }
/*     */ 
/*     */   public List<CodeValue> contractAndBatch(String contractManagementId, boolean isNew)
/*     */   {
/* 200 */     List list = this.returnPlanDao.contractAndBatch(Long.valueOf(contractManagementId), isNew);
/*     */ 
/* 202 */     return list;
/*     */   }
/*     */ 
/*     */   public List<String> checkSum(String contractManagementId, String sum, boolean isNew)
/*     */   {
/* 213 */     List data = new ArrayList();
/* 214 */     Long id = Long.valueOf(contractManagementId);
/* 215 */     if (null != id) {
/* 216 */       Double i = Double.valueOf(0.0D);
/* 217 */       Double j = Double.valueOf(0.0D);
/* 218 */       ContractManagement cm = this.contractManagementDao.loadContractManagement(id);
/*     */       try {
/* 220 */         List<ReturnPlan> list = this.returnPlanDao.loadByKey("contractManagement", id);
/*     */ 
/* 222 */         if (null != list)
/* 223 */           for (ReturnPlan r : list)
/* 224 */             i = Double.valueOf(i.doubleValue() + r.getSum().doubleValue());
/*     */       }
/*     */       catch (DaoException e)
/*     */       {
/* 228 */         e.printStackTrace();
/*     */       }
/* 230 */       if (null != cm) {
/* 231 */         j = Double.valueOf(cm.getContractMoney());
/*     */       }
/* 233 */       Double a = Double.valueOf(j.doubleValue() - i.doubleValue());
/*     */ 
/* 235 */       Long sums = Long.valueOf(sum);
/* 236 */       if (sums.longValue() > a.doubleValue())
/* 237 */         data.add("unuser");
/*     */       else {
/* 239 */         data.add("user");
/*     */       }
/*     */     }
/* 242 */     return data;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.customercontract.plan.pojo.DefaultReturnPlanManager
 * JD-Core Version:    0.6.2
 */