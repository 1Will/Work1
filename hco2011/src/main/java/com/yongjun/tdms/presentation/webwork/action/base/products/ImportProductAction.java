/*     */package com.yongjun.tdms.presentation.webwork.action.base.products;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ 
/*     */ public class ImportProductAction extends FileTransportAction{
	       private String message="";
	       private final ProductsManager productsManager;
	       private final CodeValueManager codeValueManager;
	       private final AreaManager areaManager;
	       private final UserManager userManager;
	       private final ProductTypeManager productTypeManager;
	       private final PersonnelFilesManager personnelFilesManager;
/*     */   private static final long serialVersionUID = -877987215782827292L;
/*     */ 

/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	
/*     */   }
			public ImportProductAction(ProductsManager productsManager, CodeValueManager codeValueManager,
					AreaManager areaManager,UserManager userManager,PersonnelFilesManager personnelFilesManager,ProductTypeManager productTypeManager) {
				this.productsManager = productsManager;
				this.codeValueManager = codeValueManager;
				this.areaManager = areaManager;
				this.userManager = userManager;
				this.personnelFilesManager= personnelFilesManager;
				this.productTypeManager=productTypeManager;
				}
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
	       File file   = getFile();//获取文件
	       InputStream is = new FileInputStream(file);//转化为流
			 HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);//
			 CustomerInfo cu =null ;
			 List<CustomerInfo> list = new ArrayList<CustomerInfo>();
			 if(hssfWorkbook!=null){
				 String result = checkFile(hssfWorkbook);//检查表格
				 if(result.equals("")){//检查结果为空。表示数据正常，
					 insertFile(hssfWorkbook);//导入数据
				 }
			 }
			
/*     */ 
/* 106 */     return "success";
/*     */   }
		private String getValue(HSSFCell hssfCell) {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
      // 返回布尔类型的值
				return String.valueOf(hssfCell.getBooleanCellValue());
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
      // 返回数值类型的值
				double cellValue = hssfCell.getNumericCellValue();
				String cdd = new DecimalFormat("#").format(cellValue); 
				return  cdd;
			} else {
      // 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
         }
		public String checkFile(HSSFWorkbook hssfWorkbook) throws DaoException{
				 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			    	  int indexNum=rowNum+1;
			    	  System.out.println(indexNum+"==============================");
			          HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			          if (hssfRow == null) {
			        	  this.message ="第"+indexNum+"行无数据<br/>";
			          }
			          HSSFCell code = hssfRow.getCell((short)0);//产品编码
			          
			          if (code == null) {
//			        	  this.message+="第"+indexNum+"行产品编码不能为空<br/>";
			          }else{
			        	  if(getValue(code).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行产品编码不能为空<br/>";
			        	  }else {
			        		  List<Products> products = productsManager.loadByKey("code", getValue(code).trim());
			        		  if(products!=null&&products.size()>0){
					        	  this.message+="第"+indexNum+"行产品编码在系统中已经存在<br/>";
					        	  }
			        		  
						}
			          }
			          
			          HSSFCell name = hssfRow.getCell((short)1);//产品名称
			          if (name == null) {
			        	  this.message+="第"+indexNum+"行产品名称不能为空<br/>";
			          }else{
			        	  if(getValue(name).trim().equals("")){
			        	  this.message+="第"+indexNum+"行产品名称不能为空<br/>";
			        	  }else {
			        		  List<Products> products = productsManager.loadByKey("name", getValue(name).trim());
			        		  if(products!=null&&products.size()>0){
					        	  this.message+="第"+indexNum+"行产品产品名称在系统中已经存在<br/>";
					        	  }
			        		  
						}
			          }
			          
			          HSSFCell khfl = hssfRow.getCell((short)2);//产品型号
			          if (khfl == null) {
			        	  this.message+="第"+indexNum+"行产品型号不能为空<br/>";
			          }else{
			        	  String ret =getValue(khfl).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行产品型号不能为空<br/>";
			        	  }else {
			        		  List<Products> products = productsManager.loadByKey("model", getValue(khfl).trim());
			        		  if(products!=null&&products.size()>0){
					        	  this.message+="第"+indexNum+"行产品产品型号在系统中已经存在<br/>";
					        	  }
						}
			          }
			          
			          
//			          HSSFCell qyxz = hssfRow.getCell((short)3);//产品的产品规格
//			          if (qyxz == null) {
//			        	  this.message+="第"+indexNum+"行产品规格不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(qyxz).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行产品规格不能为空<br/>";
//			        	  }
//			          }
			          
			          
			          HSSFCell sshy = hssfRow.getCell((short)4);//产品的成本价
			          if (sshy != null) {
			        	  String ret =getValue(sshy).trim();
			        	  if(!ret.equals("")){
			        		  if(!isNumeric(ret)){
			        			  this.message+="第"+indexNum+"行产品成本价必须是数字<br/>"; 
			        		  }else{
			        			  if(Double.parseDouble(ret)<0){
				        			  this.message+="第"+indexNum+"行产品成本价必须是正数<br/>"; 
				        			  }
			        		  }
						}
			          }
			          
			          
			          HSSFCell khzt = hssfRow.getCell((short)5);//产品销售价
			          if (khzt == null) {
//			        	  this.message+="第"+indexNum+"行产品销售价不能为空<br/>";
			          }else{
			        	  String ret =getValue(khzt).trim();
			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行产品销售价不能为空<br/>";
			        	  }else {
			        		  if(!isNumeric(ret)){
			        			  this.message+="第"+indexNum+"行产品销售价必须是数字<br/>"; 
			        		  }else{
			        			  if(Double.parseDouble(ret)<0){
				        			  this.message+="第"+indexNum+"行产品销售价必须是正数<br/>"; 
				        			  }
			        		  }
						}
			          }
			          
			          
			          HSSFCell khdj = hssfRow.getCell((short)6);//产品类别
			          if (khdj == null) {
			        	  this.message+="第"+indexNum+"行产品类别不能为空<br/>";
			          }else{
			        	  String ret =getValue(khdj).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行产品类别不能为空<br/>";
			        	  }else {
			        		  List<ProductType> productTypes = this.productTypeManager.loadByKey("name", ret);
			        		  if(productTypes==null||productTypes.size()<1){
			        			  this.message+="第"+indexNum+"行产品类别在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			          HSSFCell guojia = hssfRow.getCell((short)7);//来源
			          if (guojia == null) {
			        	  this.message+="第"+indexNum+"行来源不能为空<br/>";
			          }else{
			        	  String ret =getValue(guojia).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行来源不能为空<br/>";
			        	  }else {
			        		  List<CodeValue> codes =codeValueManager.loadByKey("name", ret);
			        		  if(codes==null||codes.size()<1){
			        			  this.message+="第"+indexNum+"行来源在系统中不存在<br/>";  
			        		  }				}
			          }
			          
			          HSSFCell sheng = hssfRow.getCell((short)8);//是否主营
			          if (sheng == null) {
			        	  this.message+="第"+indexNum+"行是否主营不能为空<br/>";
			          }else{
			        	  String ret =getValue(sheng).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行是否主营不能为空<br/>";
			        	  }else {
			        		  if(!ret.equals("是")&&!ret.equals("否")){
			        			  this.message+="第"+indexNum+"行是否主营只能填写是或者否<br/>";    
			        		  }
						}
			          }
			          
			          HSSFCell shi = hssfRow.getCell((short)9);//产品分类
			          if (shi == null) {
			        	  this.message+="第"+indexNum+"行产品分类不能为空<br/>";
			          }else{
			        	  String ret =getValue(shi).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行产品分类不能为空<br/>";
			        	  }else {
			        		  List<CodeValue> codes =codeValueManager.loadByKey("name", ret);
			        		  if(codes==null||codes.size()<1){
			        			  this.message+="第"+indexNum+"行产品分类在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			       
			          
			      }
			      
			return this.message;
		}
		
		public void insertFile(HSSFWorkbook hssfWorkbook) throws DaoException{
			List<Products> pros= new ArrayList<Products>();
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		    	  System.out.println(rowNum+"==============================");
		    	  Products cInfo = new Products();
		    	  cInfo.setCode(null);
		          HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		          HSSFCell code = hssfRow.getCell((short)0);//产品编码
		          if(code!=null){
		        	  String ret_code =getValue(code).trim();
			          cInfo.setCode(ret_code);
		          }
		          HSSFCell name = hssfRow.getCell((short)1);//产品名称
		          String ret_name =getValue(name).trim();
		          cInfo.setName(ret_name);
		          HSSFCell khfl = hssfRow.getCell((short)2);//产品型号
		          String ret_khfl =getValue(khfl).trim();
		          cInfo.setModel(ret_khfl);
		          HSSFCell qyxz = hssfRow.getCell((short)3);//产品的规格
		          if(qyxz!=null){
		          String ret_qyxz =getValue(qyxz).trim();
		          cInfo.setStandard(ret_qyxz);
		          }
		          HSSFCell sshy = hssfRow.getCell((short)4);//产品成本价
		          if(sshy!=null){
		          String ret_sshy =getValue(sshy).trim();
		          if(ret_sshy!=null&&!ret_sshy.equals("")){
		          cInfo.setEtcPrice(Double.parseDouble(ret_sshy));
		          }
		          }
		          HSSFCell khzt = hssfRow.getCell((short)5);//产品销售价
		          if(khzt!=null){
		          String ret_khzt =getValue(khzt).trim();
		          if(ret_khzt!=null&&!ret_khzt.equals("")){
			          cInfo.setSalePrice(Double.parseDouble(ret_khzt));
			          }
		          }
		          HSSFCell khdj = hssfRow.getCell((short)6);//产品类别
		          String ret_khdj =getValue(khdj).trim();
		          List<ProductType> productTypes = this.productTypeManager.loadByKey("name", ret_khdj);
		          if(productTypes!=null&&productTypes.size()>0){
		        	  cInfo.setPt(productTypes.get(0));
		          }
		          
		          HSSFCell guojia = hssfRow.getCell((short)7);//来源
		          String ret_guojia =getValue(guojia).trim();
		          List<CodeValue> codes =codeValueManager.loadByKey("name", ret_guojia);
		          if(codes!=null&&codes.size()>0){
		          cInfo.setProduct_source_ID(codes.get(0));
        		  cInfo.setProductSource(ret_guojia);
		          }
		          
		          HSSFCell sheng = hssfRow.getCell((short)8);//是否主营
		          String ret_sheng =getValue(sheng).trim();
		          
		          if(ret_sheng!=null){
		        	  if(ret_sheng.equals("是")){
		        		  cInfo.setIsNoMain(true);
		        	  }else if(ret_sheng.equals("否")){
		        		  cInfo.setIsNoMain(false);
		        	  }
		          }
		          HSSFCell shi = hssfRow.getCell((short)9);//产品分类
		          String ret_shi =getValue(shi).trim();
		          List<CodeValue> ret_shi_codes =codeValueManager.loadByKey("name", ret_shi);
        		  if(ret_shi_codes!=null&&ret_shi_codes.size()>0){
        			  cInfo.setBusinessType(ret_shi_codes.get(0));
        		  }
		          pros.add(cInfo);
		      }
		      this.message =  this.productsManager.saveProductfoByImp(pros);
		     
			
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public static boolean isNumeric(String str){
			  for (int i = 0; i < str.length(); i++){
			   if (!Character.isDigit(str.charAt(i))){
			    return false;
			   }
			  }
			  return true;
			 }
		
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.helpManual.EditManualAction
 * JD-Core Version:    0.6.2
 */