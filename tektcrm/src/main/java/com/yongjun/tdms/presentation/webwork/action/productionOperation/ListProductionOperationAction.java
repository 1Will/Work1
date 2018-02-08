/*     */ package com.yongjun.tdms.presentation.webwork.action.productionOperation;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;
import com.yongjun.tdms.service.productionOperation.ProductionOperationManager;

/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ 
/*     */ public class ListProductionOperationAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final ProductionOperationManager productionOperationManager;
/*     */   private List<ProductionOperation> productionOperations;
            private String managerType ;
            private String pageTitle ;
/*     */ 
/*     */   public ListProductionOperationAction(ProductionOperationManager productionOperationManager)
/*     */   {
/*  30 */     this.productionOperationManager = productionOperationManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  38 */     if ((this.productionOperations == null) && (hasIds("productionOperationIds")))
/*  39 */       this.productionOperations = this.productionOperationManager.loadAllProductionOperation(getIds("productionOperationIds"));
              if(hasId("managerType")){
            	  this.managerType = request.getParameter("managerType");
            	  if(this.managerType.equals("design")&&!"".equals(this.managerType)){
                 		pageTitle  = "设计通知单";
                 	  }else {
                 		pageTitle  = "生产经营";
          		}
              }
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  44 */     return "productionOperationHQL";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/*  50 */     Map map = super.getRequestParameterMap();
/*  54 */     return map;
/*     */   }
/*     */ 
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 108 */     if (isDelete()) {
/* 109 */       delete();
/*     */     }
/* 111 */     if (isDisabled()) {
/* 112 */       disabled();
/*     */     }
/* 114 */     if (isEnable()) {
/* 115 */       enabled();
/*     */     }
/* 117 */     return "success";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 124 */       this.productionOperationManager.disabledAllProductionOperations(this.productionOperations);
/* 125 */       addActionMessage(pageTitle+getText("失效成功"));
/* 126 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 128 */       addActionMessage(pageTitle+getText("失效失败"));
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.productionOperationManager.enabledAllProductionOperations(this.productionOperations);
/* 138 */       addActionMessage(getText("productionOperation.enabled.success"));
/* 139 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 141 */       addActionMessage(getText("productionOperation.enabled.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       this.productionOperationManager.deleteAllProductionOperation(this.productionOperations);
/* 152 */       addActionMessage(getText("productionOperation.delete.success"));
/* 153 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 155 */       addActionMessage(getText("productionOperation.delete.failer"));
/* 156 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<ProductionOperation> getProductionOperations()
/*     */   {
/* 161 */     return this.productionOperations;
/*     */   }
/*     */ 
/*     */   public void setProductionOperations(List<ProductionOperation> productionOperations) {
/* 165 */     this.productionOperations = productionOperations;
/*     */   }
/*     */ 
/*     */ 
/*     */
			public String getManagerType() {
				return managerType;
			}
			public void setManagerType(String managerType) {
				this.managerType = managerType;
			}
			public String getPageTitle() {
				return pageTitle;
			}
			public void setPageTitle(String pageTitle) {
				this.pageTitle = pageTitle;
			}
			
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.productionOperation.ListProductionOperationAction
 * JD-Core Version:    0.6.2
 */