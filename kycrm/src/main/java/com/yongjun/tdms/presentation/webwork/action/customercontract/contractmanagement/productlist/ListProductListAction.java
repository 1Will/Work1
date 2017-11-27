/*     */ package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.productlist;
/*     */ 
/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
/*     */ import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
/*     */ import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class ListProductListAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private final ProductListManager productListManager;
/*     */   private final ContractManagementManager contractManagementManager;
/*     */   private final CodeValueManager codeValueManager;
/*  59 */   private List<ProductList> productLists = null;
/*     */   private Long contractManagementId;
/*     */ 
/*     */   public ListProductListAction(ProductListManager productListManager, ContractManagementManager contractManagementManager, CodeValueManager codeValueManager)
/*     */   {
/*  73 */     this.productListManager = productListManager;
/*  74 */     this.contractManagementManager = contractManagementManager;
/*  75 */     this.codeValueManager = codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
/*  85 */     if ((null == this.productLists) && 
/*  86 */       (hasIds("productListIds"))) {
/*  87 */       this.productLists = this.productListManager.loadAllProductList(getIds("productListIds"));
/*     */     }
/*     */ 
/*  91 */     if (hasId("contractManagement.id")) {
/*  92 */       this.contractManagementId = getId("contractManagement.id");
/*     */     }
/*  94 */     setFirst(false);
/*     */   }
/*     */ 
/*     */   protected String getAdapterName()
/*     */   {
/* 104 */     return "productListHQL";
/*     */   }
/*     */ 
/*     */   protected Map getRequestParameterMap()
/*     */   {
/* 112 */     Map map = super.getRequestParameterMap();
/* 113 */     if (this.contractManagementId != null) {
/* 114 */       map.put("contractManagementid", this.contractManagementId);
/*     */     }
/* 116 */     return map;
/*     */   }
/*     */ 
/*     */   public String execute()
/*     */     throws Exception
/*     */   {
/* 126 */     if (isDisabled()) {
/* 127 */       disabled();
/*     */     }
/* 129 */     if (isEnable()) {
/* 130 */       enabled();
/*     */     }
/* 132 */     if (isDelete()) {
/* 133 */       delete();
/*     */     }
/* 135 */     return "success";
/*     */   }
/*     */ 
/*     */   public String delete()
/*     */   {
/*     */     try
/*     */     {
/* 144 */       this.productListManager.deleteAllProductList(this.productLists);
/* 145 */       addActionMessage(getText("productList.delete.success"));
/* 146 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 148 */       addActionMessage(getText("productList.delete.error"));
/* 149 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String disabled()
/*     */   {
/*     */     try
/*     */     {
/* 159 */       this.productListManager.disabledAllProductList(this.productLists);
/* 160 */       addActionMessage(getText("productList.disabled.success"));
/* 161 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 163 */       addActionMessage(getText("productList.disabled.error"));
/* 164 */     }return "error";
/*     */   }
/*     */ 
/*     */   private String enabled()
/*     */   {
/*     */     try
/*     */     {
/* 173 */       this.productListManager.enabledAllProductList(this.productLists);
/* 174 */       addActionMessage(getText("productList.enabled.success"));
/* 175 */       return "success";
/*     */     } catch (RuntimeException e) {
/* 177 */       e.printStackTrace();
/* 178 */       addActionMessage(getText("productList.enabled.error"));
/* 179 */     }return "error";
/*     */   }
			public Long getContractManagementId() {
				return contractManagementId;
			}
			public void setContractManagementId(Long contractManagementId) {
				this.contractManagementId = contractManagementId;
			}

/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement.productlist.ListProductListAction
 * JD-Core Version:    0.6.2
 */