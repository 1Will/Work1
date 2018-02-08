/*    */ package com.yongjun.tdms.service.base.products.pojo;
/*    */ 
/*    */ import com.yongjun.pluto.exception.DaoException;
/*    */ import com.yongjun.pluto.service.impl.BaseManager;
/*    */ import com.yongjun.tdms.dao.base.products.ProductsDao;
/*    */ import com.yongjun.tdms.model.base.products.Products;
/*    */ import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

/*    */ import java.util.Collection;
/*    */ import java.util.List;
/*    */ 
/*    */ public class DefaultProductsManager extends BaseManager
/*    */   implements ProductsManager
/*    */ {
/*    */   private final ProductsDao productsDao;
           private final YongJunSequenceManager yongJunSequenceManager;
/*    */ 
/*    */   public DefaultProductsManager(ProductsDao productsDao,YongJunSequenceManager yongJunSequenceManager)
/*    */   {
/* 20 */     this.productsDao = productsDao;
             this.yongJunSequenceManager=yongJunSequenceManager;
/*    */   }
/*    */ 
/*    */   public void deleteAllProducts(List<Products> list)
/*    */   {
/* 27 */     this.productsDao.deleteAllProducts(list);
/*    */   }
/*    */ 
/*    */   public void deleteProducts(Products products)
/*    */   {
/* 34 */     this.productsDao.deleteProducts(products);
/*    */   }
/*    */ 
/*    */   public List<Products> loadAllProducts(Long[] ids)
/*    */   {
/* 42 */     return this.productsDao.loadAllProducts(ids);
/*    */   }
/*    */ 
/*    */   public Products loadProducts(Long id)
/*    */   {
/* 50 */     return this.productsDao.loadProductType(id);
/*    */   }
/*    */ 
/*    */   public void storeProducts(Products products)
/*    */   {
			   if(products.isNew()){
				   String code1="MP";
				   if(products.getBusinessType().getCode().equals("21001")){
					   code1="JP";
					}
			    products.setCode((String)this.yongJunSequenceManager.generateeCodeTypeReplacFormtter(YongJunSequenceConstant.CODE_PRODUCT,code1));
			    }
	        
/* 57 */     this.productsDao.storeProducts(products);
/*    */   }
/*    */ 
/*    */   public void disabledAllProducts(Collection<Products> products)
/*    */   {
/* 64 */     for (Products product : products) {
/* 65 */       product.setDisabled(true);
/* 66 */       this.productsDao.storeProducts(product);
/*    */     }
/*    */   }
/*    */ 
/*    */   public void enabledAllProducts(Collection<Products> products)
/*    */   {
/* 74 */     for (Products product : products) {
/* 75 */       product.setDisabled(false);
/* 76 */       this.productsDao.storeProducts(product);
/*    */     }
/*    */   }
/*    */ 
/*    */   public List<Products> getAllProductsByNull(String name)
/*    */   {
/* 84 */     List list = this.productsDao.loadAllProducts();
/* 85 */     Products p = new Products();
/* 86 */     p.setId(Long.valueOf(-1L));
/* 87 */     p.setName(name);
/* 88 */     list.add(0, p);
/* 89 */     return list;
/*    */   }
/*    */ 
/*    */   public List<Products> loadByKey(String keyName, Object keyValue)
/*    */     throws DaoException
/*    */   {
/* 95 */     return this.productsDao.loadByKey(keyName, keyValue);
/*    */   }
/*    */
			public String saveProductfoByImp(List<Products> products) throws DaoException {
				String resultString ="";//返回结果
				
				int num =0;//表示成功更新或者插入的条数
				if(products!=null&&products.size()>0){
					for(Products p:products){
						// 第一步：根据产品型号查询共有多少个。
						int size=0;//用于存放每次根据型号查询的结果数
						List<Products> list = this.productsDao.loadByKey("model",p.getModel());
						if(list!=null){
							size = list.size();
						}
						// 第二步：根据查询得到的个数进行判断，不同结果不同处理
						// 第三步：如果个数大于1，则不处理。						
						if(size>1){
							resultString+="型号为："+p.getModel()+"的产品存在多条记录，无法插入或者更新操作<br>";
							//第四步：如果等于1，则做更新操作，即update	
						}else if(size==1) {
							Products pt = list.get(0);
							// 将 p的所有的属性赋值给pt
							pt.setName(p.getName());
							pt.setCode(p.getCode());
							pt.setStandard(p.getStandard());
							pt.setEtcPrice(p.getEtcPrice());
							pt.setSalePrice(p.getSalePrice());
							pt.setPt(p.getPt());
							pt.setProduct_source_ID(p.getProduct_source_ID());
							pt.setProductSource(p.getProductSource());
							pt.setIsNoMain(p.getIsNoMain());
							pt.setBusinessType(p.getBusinessType());
							this.storeProducts(pt);
							num++;
							//第五步：如果小于1，则做插入操作，即insert
						}else{
							this.storeProducts(p);
							num++;
						}
						
						
					}
				}
				resultString +="已成功导入"+num+"条产品信息";
				return resultString;
			} 
			
			}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.base.products.pojo.DefaultProductsManager
 * JD-Core Version:    0.6.2
 */