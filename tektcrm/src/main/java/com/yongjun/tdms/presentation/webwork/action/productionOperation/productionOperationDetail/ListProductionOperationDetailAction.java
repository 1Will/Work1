/*     */ package com.yongjun.tdms.presentation.webwork.action.productionOperation.productionOperationDetail;
/*     */ 
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.productionOperation.ProductionOperation;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.service.productionOperation.ProductionOperationManager;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;

/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ 
/*     */ public class ListProductionOperationDetailAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final ProductionOperationDetailManager productionOperationDetailManager;
			private final ProductionOperationManager productionOperationManager;
/*     */   private List<ProductionOperationDetail> productionOperationDetails;
			private ProductionOperation productionOperation;
/*     */ 
/*     */   public ListProductionOperationDetailAction(ProductionOperationDetailManager productionOperationDetailManager,ProductionOperationManager productionOperationManager)
/*     */   {
/*  30 */     this.productionOperationDetailManager = productionOperationDetailManager;
              this.productionOperationManager=productionOperationManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
				if (hasId("productionOperation.id")) {
					this.productionOperation = this.productionOperationManager.loadProductionOperation(getId("productionOperation.id"));
				} 
/*  38 */     if ((this.productionOperationDetails == null) && (hasIds("productionOperationDetailIds")))
/*  39 */       this.productionOperationDetails = this.productionOperationDetailManager.loadAllProductionOperationDetail(getIds("productionOperationDetailIds"));
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/*  44 */     return "productionOperationDetailHQL";
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
/* 124 */       this.productionOperationDetailManager.disabledAllProductionOperationDetails(this.productionOperationDetails);
/* 125 */       addActionMessage(getText("productionOperationDetail.disabled.success"));
/* 126 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 128 */       addActionMessage(getText("productionOperationDetail.disabled.failer"));
/* 129 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 137 */       this.productionOperationDetailManager.enabledAllProductionOperationDetails(this.productionOperationDetails);
/* 138 */       addActionMessage(getText("productionOperationDetail.enabled.success"));
/* 139 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 141 */       addActionMessage(getText("productionOperationDetail.enabled.failer"));
/* 142 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String delete()
/*     */   {
/*     */     try
/*     */     {
/* 151 */       this.productionOperationDetailManager.deleteAllProductionOperationDetail(this.productionOperationDetails);
/* 152 */       addActionMessage(getText("productionOperationDetail.delete.success"));
/* 153 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 155 */       addActionMessage(getText("productionOperationDetail.delete.failer"));
/* 156 */     }return "error";
/*     */   }
/*     */ 
/*     */   public List<ProductionOperationDetail> getProductionOperationDetails()
/*     */   {
/* 161 */     return this.productionOperationDetails;
/*     */   }
/*     */ 
/*     */   public void setProductionOperationDetails(List<ProductionOperationDetail> productionOperationDetails) {
/* 165 */     this.productionOperationDetails = productionOperationDetails;
/*     */   }
/*     */
			public ProductionOperation getProductionOperation() {
				return productionOperation;
			}
			public void setProductionOperation(ProductionOperation productionOperation) {
				this.productionOperation = productionOperation;
			} 
          
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.productionOperation.ListProductionOperationAction
 * JD-Core Version:    0.6.2
 */