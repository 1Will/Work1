/*     */ package com.yongjun.tdms.service.base.producttype.pojo;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */ import com.yongjun.pluto.service.impl.BaseManager;
/*     */ import com.yongjun.tdms.dao.base.producttype.ProductTypeDao;
/*     */ import com.yongjun.tdms.model.base.produttype.ProductType;
/*     */ import com.yongjun.tdms.service.base.producttype.ProductTypeManager;

import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;

/*     */ import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */ public class DefaultProductTypeManager extends BaseManager
/*     */   implements ProductTypeManager
/*     */ {
/*     */   private static final long serialVersionUID = -8985256230588219583L;
/*     */   private final ProductTypeDao productTypeDao;
/*     */   private final CodeValueManager codeValueManager;
/*     */   public DefaultProductTypeManager(ProductTypeDao productTypeDao,CodeValueManager codeValueManager)
/*     */   {
/*  24 */     this.productTypeDao = productTypeDao;
              this.codeValueManager=codeValueManager;
/*     */   }
/*     */ 
/*     */   public void deleteAllProductType(Collection<ProductType> productTypes) {
/*  28 */     this.productTypeDao.deleteAllProductType(productTypes);
/*     */   }
/*     */ 
/*     */   public void deleteProductType(ProductType productType) {
/*  32 */     this.productTypeDao.deleteProductType(productType);
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadAllProductType(Long[] productTypeIds) {
/*  36 */     return this.productTypeDao.loadAllProductType(productTypeIds);
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadAllProductType() {
/*  40 */     return this.productTypeDao.loadAllProductType();
/*     */   }
/*     */ 
/*     */   public ProductType loadProductType(Long productTypeId) {
/*  44 */     return this.productTypeDao.loadProductType(productTypeId);
/*     */   }
/*     */ 
/*     */   public void storeProductType(ProductType productType) {
/*  48 */     if (productType.getChildPT().size() > 0)
/*     */     {
/*  50 */       storeProductTypeEditSTEP(productType);
/*     */     }
/*  52 */     this.productTypeDao.storeProductType(productType);
/*     */   }
/*     */ 
/*     */   public void disabledAllProductType(Collection<ProductType> productTypes) {
/*  56 */     for (ProductType pt : productTypes) {
/*  57 */       pt.setDisabled(true);
/*  58 */       this.productTypeDao.storeProductType(pt);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void enabledAllProductType(Collection<ProductType> productTypes) {
/*  63 */     for (ProductType pt : productTypes) {
/*  64 */       pt.setDisabled(false);
/*  65 */       this.productTypeDao.storeProductType(pt);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void storeProductTypeEditSTEP(ProductType pt)
/*     */   {
/*  73 */     for (ProductType pt1 : pt.getChildPT()) {
/*  74 */       pt1.setStep(Integer.valueOf(pt1.getParentPT().getStep().intValue() + 1));
/*  75 */       this.productTypeDao.storeProductType(pt1);
/*  76 */       if (pt1.getChildPT().size() > 0)
/*  77 */         storeProductTypeEditSTEP(pt1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public List createSelectProductTypes(String name)
/*     */   {
/*  88 */     List list = this.productTypeDao.loadAllProductType();
/*  89 */     ProductType cc = new ProductType();
/*  90 */     cc.setId(Long.valueOf(0L));
/*  91 */     cc.setName(name);
/*  92 */     list.add(0, cc);
/*  93 */     return list;
/*     */   }
/*     */ 
/*     */   public List<ProductType> loadByKey(String keyName, Object keyValue) throws DaoException {
/*  97 */     return this.productTypeDao.loadByKey(keyName, keyValue);
/*     */   }
/*     */ 
/*     */   public List<Integer> getStepAfterGroupingByStep() {
/* 101 */     return this.productTypeDao.getStepAfterGroupingByStep();
/*     */   }
/*     */ 
/*     */   public int getMaxPTId() {
/* 105 */     return this.productTypeDao.getMaxPTId();
/*     */   }
/*     */ 
/*     */   public List<ProductType> getPT(ProductType productType, List<ProductType> list, String mark) {
/*     */     try {
/* 110 */       List listpt = loadByKey("parentPT", productType.getId());
/*     */ 
/* 112 */       if (null != productType.getParentPT()) {
/* 113 */         if (null != listpt) {
/* 114 */           list.remove(productType);
/* 115 */           for (int i = 0; i < listpt.size(); i++) {
/* 116 */             productType = (ProductType)listpt.get(i);
/* 117 */             getPT(productType, list, mark);
/*     */           }
/*     */         } else {
/* 120 */           list.remove(productType);
/*     */         }
/*     */       }
/*     */       else {
/* 124 */         List<ProductType> listParent = loadByKey("step", Integer.valueOf(0));
/*     */ 
/* 126 */         list.clear();
/* 127 */         for (ProductType pt : listParent) {
/* 128 */           list.add(pt);
/*     */         }
/*     */ 
/* 131 */         list.remove(productType);
/*     */ 
/* 133 */         ProductType pt = new ProductType();
/* 134 */         pt.setId(Long.valueOf(0L));
/* 135 */         pt.setName(mark);
/* 136 */         list.add(0, pt);
/*     */       }
/*     */     } catch (DaoException e) {
/* 139 */       e.printStackTrace();
/*     */     }
/* 141 */     return list;
/*     */   }
/*     */   public List<ProductType> getAllProductTypeByNull(String name) {
/* 144 */     List list = this.productTypeDao.loadAllProductTypes();
/* 145 */     ProductType pt = new ProductType();
/* 146 */     pt.setId(Long.valueOf(0L));
/* 147 */     pt.setName(name);
/* 148 */     list.add(0, pt);
/* 149 */     return list;
/*     */   }
            public List<ProductType> loadByBtype(String btype) throws DaoException {
            	long b=Integer.parseInt(btype);
            	List<ProductType> productTypes=new ArrayList<ProductType>();
            	CodeValue cv= codeValueManager.loadCodeValue(b);
            	ProductType prarentPT=productTypeDao.loadByKey("name",cv.getName()).get(0);
            	if(prarentPT!=null){
            		ProductType p=new ProductType();
            		p.setId(prarentPT.getId());
            		p.setName(prarentPT.getName());
            		productTypes.add(p);
            	}
            	List<ProductType>childPTs=productTypeDao.loadByKey("parentPT.name",cv.getName());
            	if(childPTs!=null && childPTs.size()>0){
            		for(int i=0;i<childPTs.size();i++){
            			ProductType pt=new ProductType();
            				pt.setId(childPTs.get(i).getId());
            				pt.setName(childPTs.get(i).getName());
            			    productTypes.add(pt);
            		}
            	}
			    return productTypes;
			  }
			}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.producttype.pojo.DefaultProductTypeManager
 * JD-Core Version:    0.6.2
 */