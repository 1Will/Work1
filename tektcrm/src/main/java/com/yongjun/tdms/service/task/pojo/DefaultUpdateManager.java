/*     */ package com.yongjun.tdms.service.task.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.model.workspace.leaveBill.LeaveBill;
/*     */ import com.yongjun.tdms.model.workspace.ontheroadBill.OnTheRoadBill;
/*     */ import com.yongjun.tdms.model.workspace.overTimeBill.OverTimeBill;
/*     */ import com.yongjun.tdms.service.task.UpdateManager;
/*     */ import com.yongjun.tdms.service.workspace.leaveBill.LeaveBillManager;
/*     */ import com.yongjun.tdms.service.workspace.ontheroadBill.OnTheRoadBillManager;
/*     */ import com.yongjun.tdms.service.workspace.overTimeBill.OverTimeBillManager;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ 
/*     */ public class DefaultUpdateManager extends BaseManager
/*     */   implements UpdateManager
/*     */ {
/*     */   private static final String ERROR = "error";
/*     */   private static final String SUCCESS = "success";
/*     */   private CodeValueManager codeValueManager;
/*     */   private LeaveBillManager leaveBillManager;
/*     */   private OnTheRoadBillManager onTheRoadBillManager;
/*     */   private OverTimeBillManager overTimeBillManager;
/*     */ 
/*     */   public DefaultUpdateManager(CodeValueManager codeValueManager, LeaveBillManager leaveBillManager, OnTheRoadBillManager onTheRoadBillManager, OverTimeBillManager overTimeBillManager)
/*     */   {
/*  79 */     this.codeValueManager = codeValueManager;
/*  80 */     this.leaveBillManager = leaveBillManager;
/*  81 */     this.onTheRoadBillManager = onTheRoadBillManager;
/*  82 */     this.overTimeBillManager = overTimeBillManager;
/*     */   }
/*     */ 
/*     */   public String updateObject(String string, Long id, String value, String notAgreeReason)
/*     */   {
/*  95 */     switch (Long.valueOf(string).intValue())
/*     */     {
/*     */     case 2101:
/*  98 */       updateLeaveBillStatue(id, value, notAgreeReason);
/*  99 */       return "success";
/*     */     case 2104:
/* 101 */       updateOverTimeBillStatue(id, value, notAgreeReason);
/* 102 */       return "success";
/*     */     case 2103:
/* 104 */       updateOnTheRoadBillStatue(id, value, notAgreeReason);
/* 105 */       return "success";
/*     */     case 2102:
/* 107 */       updateLeaveBillStatue(id, value, notAgreeReason);
/* 108 */       return "success";
/*     */     }
/* 110 */     return "error";
/*     */   }
/*     */ 
/*     */   private String updateLeaveBillStatue(Long id, String value, String notAgreeReason)
/*     */   {
/*     */     try
/*     */     {
/* 125 */       Long valueId = ((CodeValue)this.codeValueManager.loadByKey("code", value.trim()).get(0)).getId();
/* 126 */       LeaveBill leaveBill = this.leaveBillManager.loadLeaveBill(id);
/* 127 */       leaveBill.setStatus(this.codeValueManager.loadCodeValue(valueId));
/* 128 */       if (null != notAgreeReason) {
/* 129 */         leaveBill.setFailReason(notAgreeReason);
/*     */       }
/* 131 */       this.leaveBillManager.storeLeaveBill(leaveBill);
/* 132 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 136 */       this.logger.info("数据操作出错，请检查实体字段！");
/* 137 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String updateOnTheRoadBillStatue(Long id, String value, String notAgreeReason)
/*     */   {
/*     */     try
/*     */     {
/* 151 */       Long valueId = ((CodeValue)this.codeValueManager.loadByKey("code", value.trim()).get(0)).getId();
/* 152 */       OnTheRoadBill onTheRoadBill = this.onTheRoadBillManager.loadOnTheRoadBill(id);
/* 153 */       onTheRoadBill.setStatus(this.codeValueManager.loadCodeValue(valueId));
/* 154 */       if (null != notAgreeReason) {
/* 155 */         onTheRoadBill.setFailReason(notAgreeReason);
/*     */       }
/* 157 */       this.onTheRoadBillManager.storeOnTheRoadBill(onTheRoadBill);
/* 158 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 162 */       this.logger.info("数据操作出错，请检查实体字段！");
/* 163 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String updateOverTimeBillStatue(Long id, String value, String notAgreeReason)
/*     */   {
/*     */     try
/*     */     {
/* 177 */       Long valueId = ((CodeValue)this.codeValueManager.loadByKey("code", value.trim()).get(0)).getId();
/* 178 */       OverTimeBill overTimeBill = this.overTimeBillManager.loadOverTimeBill(id);
/* 179 */       overTimeBill.setStatus(this.codeValueManager.loadCodeValue(valueId));
/* 180 */       if (null != notAgreeReason) {
/* 181 */         overTimeBill.setFailReason(notAgreeReason);
/*     */       }
/* 183 */       this.overTimeBillManager.storeOverTimeBill(overTimeBill);
/* 184 */       return "success";
/*     */     }
/*     */     catch (Exception e)
/*     */     {
/* 188 */       this.logger.info("数据操作出错，请检查实体字段！");
/* 189 */     }return "error";
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.task.pojo.DefaultUpdateManager
 * JD-Core Version:    0.6.2
 */