/*     */ package com.yongjun.tdms.model.base.products;
/*     */ 
/*     */ import com.yongjun.pluto.model.BaseInfoEntity;
/*     */ import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.CreatorTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
/*     */ import com.yongjun.pluto.model.tracking.LastOperatorTracking;
/*     */ import com.yongjun.tdms.model.base.produttype.ProductType;
/*     */ import com.yongjun.tdms.model.supplier.Supplier;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Products extends BaseInfoEntity
/*     */   implements CreatedTimeTracking, CreatorTracking, LastOperatorTracking, LastModifiedTimeTracking
/*     */ {
/*     */   private static final long serialVersionUID = 4076912356110674435L;
/*     */   private String code;
/*     */   private String name;
/*     */   private String model;
/*     */   private String standard;
/*     */   private Double etcPrice;
/*     */   private Double salePrice;
/*     */   private String salelimit;
/*     */   private Date launch;
/*     */   private String remark;
/*     */   private ProductType pt;
/*     */   private String productSource;
/*     */   private CodeValue product_source_ID;
/*     */   private Supplier supplier;
/*  47 */   private boolean isNoMain = true;
/*     */ 
/*     */   public boolean equals(Object arg0) {
/*  50 */     if (arg0 == this) {
/*  51 */       return true;
/*     */     }
/*  53 */     if (!(arg0 instanceof Products)) {
/*  54 */       return false;
/*     */     }
/*     */ 
/*  57 */     Products p = (Products)arg0;
/*     */ 
/*  59 */     if (!p.getId().equals(getId())) {
/*  60 */       return false;
/*     */     }
/*  62 */     return true;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/*  69 */     return getId().hashCode();
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/*  74 */     return this.code;
/*     */   }
/*     */ 
/*     */   public Double getEtcPrice()
/*     */   {
/*  79 */     return this.etcPrice;
/*     */   }
/*     */ 
/*     */   public boolean getIsNoMain() {
/*  83 */     return this.isNoMain;
/*     */   }
/*     */ 
/*     */   public String getModel() {
/*  87 */     return this.model;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  92 */     return this.name;
/*     */   }
/*     */ 
/*     */   public String getProductSource()
/*     */   {
/*  97 */     return this.productSource;
/*     */   }
/*     */ 
/*     */   public ProductType getPt()
/*     */   {
/* 102 */     return this.pt;
/*     */   }
/*     */ 
/*     */   public Double getSalePrice()
/*     */   {
/* 107 */     return this.salePrice;
/*     */   }
/*     */ 
/*     */   public String getStandard()
/*     */   {
/* 112 */     return this.standard;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 117 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public void setEtcPrice(Double etcPrice)
/*     */   {
/* 122 */     this.etcPrice = etcPrice;
/*     */   }
/*     */ 
/*     */   public void setIsNoMain(boolean isNoMain)
/*     */   {
/* 127 */     this.isNoMain = isNoMain;
/*     */   }
/*     */ 
/*     */   public void setModel(String model)
/*     */   {
/* 132 */     this.model = model;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 137 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public void setProductSource(String productSource)
/*     */   {
/* 142 */     this.productSource = productSource;
/*     */   }
/*     */ 
/*     */   public void setPt(ProductType pt)
/*     */   {
/* 147 */     this.pt = pt;
/*     */   }
/*     */ 
/*     */   public String getSalelimit()
/*     */   {
/* 152 */     return this.salelimit;
/*     */   }
/*     */ 
/*     */   public void setSalelimit(String salelimit)
/*     */   {
/* 157 */     this.salelimit = salelimit;
/*     */   }
/*     */ 
/*     */   public void setSalePrice(Double salePrice)
/*     */   {
/* 162 */     this.salePrice = salePrice;
/*     */   }
/*     */ 
/*     */   public void setStandard(String standard)
/*     */   {
/* 167 */     this.standard = standard;
/*     */   }
/*     */ 
/*     */   public Supplier getSupplier()
/*     */   {
/* 172 */     return this.supplier;
/*     */   }
/*     */ 
/*     */   public void setSupplier(Supplier supplier)
/*     */   {
/* 177 */     this.supplier = supplier;
/*     */   }
/*     */ 
/*     */   public Date getLaunch()
/*     */   {
/* 182 */     return this.launch;
/*     */   }
/*     */ 
/*     */   public void setLaunch(Date launch)
/*     */   {
/* 187 */     this.launch = launch;
/*     */   }
/*     */ 
/*     */   public String getRemark()
/*     */   {
/* 192 */     return this.remark;
/*     */   }
/*     */ 
/*     */   public void setRemark(String remark)
/*     */   {
/* 197 */     this.remark = remark;
/*     */   }
/*     */ 
/*     */   public CodeValue getProduct_source_ID()
/*     */   {
/* 202 */     return this.product_source_ID;
/*     */   }
/*     */ 
/*     */   public void setProduct_source_ID(CodeValue product_source_ID)
/*     */   {
/* 207 */     this.product_source_ID = product_source_ID;
/*     */   }
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.base.products.Products
 * JD-Core Version:    0.6.2
 */