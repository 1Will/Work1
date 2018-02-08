/*     */ package com.yongjun.tdms.presentation.webwork.action.productionOperation.productionOperationException;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;
import com.yongjun.tdms.service.productionOperation.ProductionOperationManager;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;
import com.yongjun.tdms.service.productionOperation.productionOperationException.ProductionOperationExceptionManager;

/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ 
/*     */ public class ListProductionOperationExceptionAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final ProductionOperationExceptionManager productionOperationExceptionManager;
			private final ProductionOperationDetailManager productionOperationDetailManager;
/*     */   private List<ProductionOperationException> productionOperationExceptions;
			private ProductionOperationDetail productionOperationDetail;
/*     */ 
/*     */   public ListProductionOperationExceptionAction(ProductionOperationExceptionManager productionOperationExceptionManager,ProductionOperationDetailManager productionOperationDetailManager)
/*     */   {
/*  30 */     this.productionOperationExceptionManager = productionOperationExceptionManager;
              this.productionOperationDetailManager=productionOperationDetailManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
				if (hasId("productionOperation.id")) {
					this.productionOperationDetail = this.productionOperationDetailManager.loadProductionOperationDetail(getId("productionOperation.id"));
				} 
/*  38 */     if ((this.productionOperationExceptions == null) && (hasIds("productionOperationExceptionIds")))
/*  39 */       this.productionOperationExceptions = this.productionOperationExceptionManager.loadAllProductionOperationException(getIds("productionOperationExceptionIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  44 */     return "productionOperationExceptionHQL";
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
/* 124 */       this.productionOperationExceptionManager.disabledAllProductionOperationExceptions(this.productionOperationExceptions);
/* 125 */       addActionMessage(getText("productionOperationException.disabled.success"));
/* 126 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 128 */       addActionMessage(getText("productionOperationException.disabled.failer"));
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.productionOperationExceptionManager.enabledAllProductionOperationExceptions(this.productionOperationExceptions);
/* 138 */       addActionMessage(getText("productionOperationException.enabled.success"));
/* 139 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 141 */       addActionMessage(getText("productionOperationException.enabled.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       this.productionOperationExceptionManager.deleteAllProductionOperationException(this.productionOperationExceptions);
/* 152 */       addActionMessage(getText("productionOperationException.delete.success"));
/* 153 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 155 */       addActionMessage(getText("productionOperationException.delete.failer"));
/* 156 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationException> getProductionOperationExceptions()
/*     */   {
/* 161 */     return this.productionOperationExceptions;
/*     */   }
/*     */ 
/*     */   public void setProductionOperationExceptions(List<ProductionOperationException> productionOperationExceptions) {
/* 165 */     this.productionOperationExceptions = productionOperationExceptions;
/*     */   }
/*     */
			public ProductionOperationDetail getProductionOperationDetail() {
				return productionOperationDetail;
			}
			public void setProductionOperationDetail(ProductionOperationDetail productionOperationDetail) {
				this.productionOperationDetail = productionOperationDetail;
			}
          
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.productionOperation.ListProductionOperationAction
 * JD-Core Version:    0.6.2
 */