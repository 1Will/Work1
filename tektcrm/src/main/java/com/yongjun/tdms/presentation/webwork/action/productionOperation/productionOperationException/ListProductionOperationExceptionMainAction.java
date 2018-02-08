/*     */ package com.yongjun.tdms.presentation.webwork.action.productionOperation.productionOperationException;
/*     */ 
/*     */ import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.productionOperation.productionOperationDetail.ProductionOperationDetail;
import com.yongjun.tdms.model.productionOperation.productionOperationException.ProductionOperationException;
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;
import com.yongjun.tdms.service.productionOperation.productionOperationException.ProductionOperationExceptionManager;

import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ 
/*     */ public class ListProductionOperationExceptionMainAction extends ValueListAction
/*     */ {
/*     */   private static final long serialVersionUID = 5616960016127750986L;
/*     */   private final ProductionOperationExceptionManager productionOperationExceptionManager;
			private final ProductionOperationDetailManager productionOperationDetailManager;
/*     */   private List<ProductionOperationException> productionOperationExceptions;
			private ProductionOperationDetail productionOperationDetail;
			private CodeValueManager codeValueManager;
/*     */ 
/*     */   public ListProductionOperationExceptionMainAction(ProductionOperationExceptionManager productionOperationExceptionManager,ProductionOperationDetailManager productionOperationDetailManager,CodeValueManager codeValueManager)
/*     */   {
/*  30 */     this.productionOperationExceptionManager = productionOperationExceptionManager;
              this.productionOperationDetailManager=productionOperationDetailManager;
              this.codeValueManager=codeValueManager;
/*     */   }
/*     */ 
/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
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
			//获取所有单位
			public List<CodeValue> getAllStatu(){
				List<CodeValue> codeValues =null;
				try {
					String [] key={"code","name"};
					String [] value ={"218","异常状态"};
					List <CodeValue>oneList = this.codeValueManager.loadByKeyArray(key, value);
					
					if(oneList!=null&&oneList.size()>0){
						codeValues = this.codeValueManager.loadByKey("parentCV.id", oneList.get(0).getId());
						
						CodeValue cv = new CodeValue();
						cv.setId(Long.valueOf(-1L));
						cv.setName(getText("select.option.all"));
						codeValues.add(0, cv);
						
					}else {
						codeValues =new ArrayList<CodeValue>();
					}
					
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return codeValues;
			}
			public List<CodeValue> getAllProductionPoints() {
				try {
					List codes = new ArrayList();
					String[] keys = { "name", "code" };
					String[] values = { "生产节点", "217" };
					List one = this.codeValueManager.loadByKeyArray(keys, values);// this.codeValueManager.loadByKey("code",
																					// Long.valueOf("211"));
					if ((null != one) && (one.size() > 0)) {
						List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
						if ((null != list) && (list.size() > 0)) {
							
							codes.addAll(list);
							
							CodeValue cv = new CodeValue();
							cv.setId(Long.valueOf(-1L));
							cv.setName(getText("select.option.all"));
							codes.add(0, cv);
						}

					}

					return codes;
				} catch (DaoException e) {
					e.printStackTrace();
					return new ArrayList();
				}
			}
          
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.productionOperation.ListProductionOperationAction
 * JD-Core Version:    0.6.2
 */