/*     */ package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salarydetail;
/*     */ 
/*     */ import com.opensymphony.webwork.ServletActionContext;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.personnelFiles.salarymanager.salarydetail.SalaryDetail;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salarydetail.SalaryDetailManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryitems.SalaryItemsManager;
/*     */ import com.yongjun.tdms.service.personnelFiles.salarymanager.salarymanager.SalaryStandardManager;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ 
/*     */ public class ListSalaryDetailAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final SalaryDetailManager salaryDetailManager;
/*     */   private final SalaryStandardManager salaryStandardManager;
/*     */   private final SalaryItemsManager salaryItemsManager;
/*  59 */   private List<SalaryDetail> salaryDetails = null;
/*     */   private String itemIds;
/*     */ 
/*     */   public ListSalaryDetailAction(SalaryDetailManager salaryDetailManager, SalaryStandardManager salaryStandardManager, SalaryItemsManager salaryItemsManager)
/*     */   {
/*  73 */     this.salaryDetailManager = salaryDetailManager;
/*  74 */     this.salaryStandardManager = salaryStandardManager;
/*  75 */     this.salaryItemsManager = salaryItemsManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  85 */     if ((null == this.salaryDetails) && 
/*  86 */       (hasIds("salaryDetailIds"))) {
/*  87 */       this.salaryDetails = this.salaryDetailManager.loadAllSalaryDetail(getIds("salaryDetailIds"));
/*     */     }
/*     */ 
/*  91 */     this.itemIds = ServletActionContext.getRequest().getParameter("itemIds");
/*  92 */     if ((null != this.itemIds) && (!"".equals(this.itemIds.trim()))) {
/*  93 */       String[] sa = this.itemIds.trim().split(" ");
/*  94 */       for (String s : sa) {
/*  95 */         String[] keyNames = { "salaryStandard.id", "salaryItems.id" };
/*  96 */         Long[] keyValues = { Long.valueOf(this.request.getParameter("salaryDetail.salaryStandard")), Long.valueOf(s) };
/*  97 */         List listsd = this.salaryDetailManager.loadByKeyArray(keyNames, keyValues);
/*     */ 
/*  99 */         if ((null == listsd) || (listsd.size() < 1)) {
/* 100 */           SalaryDetail sd = new SalaryDetail();
/* 101 */           sd.setSalaryItems(this.salaryItemsManager.loadSalaryItems(Long.valueOf(s)));
/* 102 */           sd.setSalaryStandard(this.salaryStandardManager.loadSalaryStandard(Long.valueOf(this.request.getParameter("salaryDetail.salaryStandard"))));
/* 103 */           this.salaryDetailManager.storeSalaryDetail(sd);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 108 */     String[] ids = this.request.getParameterValues("id");
/* 109 */     if ((null != ids) && (ids.length > 0))
/* 110 */       for (String id : ids) {
/* 111 */         double basicMoney = Double.valueOf(this.request.getParameter("basicMoney_" + id)).doubleValue();
/* 112 */         SalaryDetail d = this.salaryDetailManager.loadSalaryDetail(Long.valueOf(id));
/* 113 */         d.setBasicMoney(basicMoney);
/* 114 */         this.salaryDetailManager.storeSalaryDetail(d);
/*     */       }
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 127 */     return "salaryDetailHQL";
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 138 */     if (isDisabled()) {
/* 139 */       disabled();
/*     */     }
/* 141 */     if (isEnable()) {
/* 142 */       enabled();
/*     */     }
/* 144 */     if (isDelete()) {
/* 145 */       delete();
/*     */     }
/* 147 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 157 */       this.salaryDetailManager.deleteAllSalaryDetail(this.salaryDetails);
/* 158 */       addActionMessage(getText("salaryDetail.delete.success"));
/* 159 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 161 */       addActionMessage(getText("salaryDetail.delete.error"));
/* 162 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 173 */       this.salaryDetailManager.disabledAllSalaryDetail(this.salaryDetails);
/* 174 */       addActionMessage(getText("salaryDetail.disabled.success"));
/* 175 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 177 */       addActionMessage(getText("salaryDetail.disabled.error"));
/* 178 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 188 */       this.salaryDetailManager.enabledAllSalaryDetail(this.salaryDetails);
/* 189 */       addActionMessage(getText("salaryDetail.enabled.success"));
/* 190 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 192 */       e.printStackTrace();
/* 193 */       addActionMessage(getText("salaryDetail.enabled.error"));
/* 194 */     }return "error";
/*     */   }
/*     */ 
/*     */   public String getItemIds()
/*     */   {
/* 204 */     return this.itemIds;
/*     */   }
/*     */ 
/*     */   public void setItemIds(String itemIds)
/*     */   {
/* 212 */     this.itemIds = itemIds;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salarydetail.ListSalaryDetailAction
 * JD-Core Version:    0.6.2
 */