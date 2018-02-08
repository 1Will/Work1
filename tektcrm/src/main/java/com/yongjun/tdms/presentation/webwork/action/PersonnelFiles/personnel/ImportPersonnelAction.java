/*     */package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.personnel;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ 
/*     */ public class ImportPersonnelAction extends FileTransportAction{
	       private String message="";
	       private final ProductsManager productsManager;
	       private final CodeValueManager codeValueManager;
	       private final AreaManager areaManager;
	       private final UserManager userManager;
	       private final ProductTypeManager productTypeManager;
	       private final PersonnelFilesManager personnelFilesManager;
	       private final InstitutionManager institutionManager;
	       private final DepartmentManager departmentManager;
	       private final DutyManager dutyManager;
/*     */   private static final long serialVersionUID = -877987215782827292L;
/*     */ 

/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	
/*     */   }
			public ImportPersonnelAction(ProductsManager productsManager, CodeValueManager codeValueManager,
					AreaManager areaManager,UserManager userManager,PersonnelFilesManager personnelFilesManager,ProductTypeManager productTypeManager,InstitutionManager institutionManager,
					DepartmentManager departmentManager,DutyManager dutyManager) {
				this.productsManager = productsManager;
				this.codeValueManager = codeValueManager;
				this.areaManager = areaManager;
				this.userManager = userManager;
				this.personnelFilesManager = personnelFilesManager;
				this.productTypeManager = productTypeManager;
				this.institutionManager = institutionManager;
				this.departmentManager = departmentManager;
				this.dutyManager = dutyManager;
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
//				 insertFile(hssfWorkbook);//导入数据
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
			          HSSFCell fileNo = hssfRow.getCell((short)0);//档案编码
			          if(fileNo == null){
			        	  this.message+="第"+indexNum+"行档案编码不能为空<br/>";
			          }else{
			        	  if(getValue(fileNo).trim().equals("")){
			        	  this.message+="第"+indexNum+"行档案编码不能为空<br/>";
			        	  }else{
			        		  List<PersonnelFiles> fileNos = personnelFilesManager.loadByKey("fileNo", getValue(fileNo).trim());
			        		  if(fileNos!=null&&fileNos.size()>0){
			        			  this.message+="第"+indexNum+"行档案编码在系统中已经存在<br/>";
			        		  }
			        	  }
			          }
			          HSSFCell name = hssfRow.getCell((short)1);//姓名
			          if(name == null){
			        	  this.message+="第"+indexNum+"行姓名不能为空<br/>";
			          }else{
			        	  if(getValue(name).trim().equals("")){
			        	  this.message+="第"+indexNum+"行姓名不能为空<br/>";
			        	  }else{
//			        		  List<PersonnelFiles> names = personnelFilesManager.loadByKey("name", getValue(name).trim());
//			        		  if(names!=null&&names.size()>0){
//			        			  this.message+="第"+indexNum+"行姓名在系统中已经存在<br/>";
//			        		  }
			        	  }
			          }
			          HSSFCell sex = hssfRow.getCell((short)2);//性别
			          if(sex == null){
			        	  this.message+="第"+indexNum+"行性别不能为空<br/>";
			          }else{
			        	  if(getValue(sex).trim().equals("")){
			        	  this.message+="第"+indexNum+"行性别不能为空<br/>";
			        	  }
			          }
			          HSSFCell birthday = hssfRow.getCell((short)3);//出生日期
			          if(birthday == null){
			        	  this.message+="第"+indexNum+"行出生日期不能为空<br/>";
			          }else{
			        	  if(getValue(birthday).trim().equals("")){
			        	  this.message+="第"+indexNum+"行出生日期不能为空<br/>";
			        	  }
			          }
			          HSSFCell birthplace = hssfRow.getCell((short)5);//籍贯
			          if(birthplace == null){
			        	  this.message+="第"+indexNum+"行籍贯不能为空<br/>";
			          }else{
			        	  if(getValue(birthplace).trim().equals("")){
			        	  this.message+="第"+indexNum+"行籍贯不能为空<br/>";
			        	  }else{
			        		  List<Area> Areas = this.areaManager.loadByKey("name", getValue(birthplace).trim());
			        		  if(Areas==null||Areas.size()<1){
			        			  this.message+="第"+indexNum+"行籍贯在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          HSSFCell politice = hssfRow.getCell((short)8);//政治面貌
			          if(politice == null){
			        	  this.message+="第"+indexNum+"行政治面貌为空<br/>";
			          }else{
			        	  if(getValue(politice).trim().equals("")){
			        	  this.message+="第"+indexNum+"行政治面貌为空<br/>";
			        	  }else{
			        		  List<CodeValue> politices = codeValueManager.loadByKey("name", getValue(politice).trim());
			        		  if(politices==null||politices.size()<1){
			        			  this.message+="第"+indexNum+"行政治面貌在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          HSSFCell mobile = hssfRow.getCell((short)10);//手机号码
			          if(mobile == null){
			        	  this.message+="第"+indexNum+"行手机号码为空<br/>";
			          }else{
			        	  if(getValue(mobile).trim().equals("")){
			        	  this.message+="第"+indexNum+"行手机号码为空<br/>";
			        	  }
			          }
			          HSSFCell inst = hssfRow.getCell((short)13);//单位
			          if(inst == null){
			        	  this.message+="第"+indexNum+"行单位为空<br/>";
			          }else{
			        	  if(getValue(inst).trim().equals("")){
			        	  this.message+="第"+indexNum+"行单位为空<br/>";
			        	  }else{
			        		  List<Institution> insts = institutionManager.loadByKey("name", getValue(inst).trim());
			        		  if(insts==null||insts.size()<1){
			        			  this.message+="第"+indexNum+"行单位在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          HSSFCell dept = hssfRow.getCell((short)14);//部门
			          if(dept == null){
			        	  this.message+="第"+indexNum+"行部门为空<br/>";
			          }else{
			        	  if(getValue(dept).trim().equals("")){
			        	  this.message+="第"+indexNum+"行部门为空<br/>";
			        	  }else{
			        		  List<Department> depts = departmentManager.loadByKey("name", getValue(dept).trim());
			        		  if(depts==null||depts.size()<1){
			        			  this.message+="第"+indexNum+"行部门在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          HSSFCell duty = hssfRow.getCell((short)15);//职位
			          if(duty == null){
			        	  this.message+="第"+indexNum+"行职位为空<br/>";
			          }else{
			        	  if(getValue(duty).trim().equals("")){
			        	  this.message+="第"+indexNum+"行职位为空<br/>";
			        	  }else{
			        		  List<Duty> dutys = dutyManager.loadByKey("name", getValue(duty).trim());
			        		  if(dutys==null||dutys.size()<1){
			        			  this.message+="第"+indexNum+"行职位在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          HSSFCell entryDate = hssfRow.getCell((short)18);//入职日期
			          if(entryDate == null){
			        	  this.message+="第"+indexNum+"行入职日期为空<br/>";
			          }else{
			        	  if(getValue(entryDate).trim().equals("")){
			        	  this.message+="第"+indexNum+"行入职日期为空<br/>";
			        	  }
			          }
			          HSSFCell status = hssfRow.getCell((short)20);//状态
			          if(status == null){
			        	  this.message+="第"+indexNum+"行状态为空<br/>";
			          }else{
			        	  if(getValue(status).trim().equals("")){
			        	  this.message+="第"+indexNum+"行状态为空<br/>";
			        	  }
			          }
			          HSSFCell businessType = hssfRow.getCell((short)21);//员工属性（天鹅）
			          if(businessType == null){
			        	  this.message+="第"+indexNum+"行员工属性为空<br/>";
			          }else{
			        	  if(getValue(businessType).trim().equals("")){
			        	  this.message+="第"+indexNum+"行员工属性为空<br/>";
			        	  }
			          }
			          HSSFCell code = hssfRow.getCell((short)22);//工号
			          if(code == null){
			        	  this.message+="第"+indexNum+"行工号为空<br/>";
			          }else{
			        	  if(getValue(code).trim().equals("")){
			        	  this.message+="第"+indexNum+"行工号为空<br/>";
			        	  }else{
			        		  List<PersonnelFiles> codes = personnelFilesManager.loadByKey("code", getValue(code).trim());
			        		  if(codes!=null&&codes.size()>0){
			        			  this.message+="第"+indexNum+"行工号在系统中已经存在<br/>";
			        		  }
			        	  }
			          }
//			          HSSFCell code = hssfRow.getCell((short)0);//产品编码
//			          
//			          if (code == null) {
////			        	  this.message+="第"+indexNum+"行产品编码不能为空<br/>";
//			          }else{
//			        	  if(getValue(code).trim().equals("")){
////			        	  this.message+="第"+indexNum+"行产品编码不能为空<br/>";
//			        	  }else {
//			        		  List<Products> products = productsManager.loadByKey("code", getValue(code).trim());
//			        		  if(products!=null&&products.size()>0){
//					        	  this.message+="第"+indexNum+"行产品编码在系统中已经存在<br/>";
//					        	  }
//			        		  
//						}
//			          }
//			          
//			          HSSFCell name = hssfRow.getCell((short)1);//产品名称
//			          if (name == null) {
//			        	  this.message+="第"+indexNum+"行产品名称不能为空<br/>";
//			          }else{
//			        	  if(getValue(name).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行产品名称不能为空<br/>";
//			        	  }else {
//			        		  List<Products> products = productsManager.loadByKey("name", getValue(name).trim());
//			        		  if(products!=null&&products.size()>0){
//					        	  this.message+="第"+indexNum+"行产品产品名称在系统中已经存在<br/>";
//					        	  }
//			        		  
//						}
//			          }
//			          
//			          HSSFCell khfl = hssfRow.getCell((short)2);//产品型号
//			          if (khfl == null) {
//			        	  this.message+="第"+indexNum+"行产品型号不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(khfl).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行产品型号不能为空<br/>";
//			        	  }else {
//			        		  List<Products> products = productsManager.loadByKey("model", getValue(khfl).trim());
//			        		  if(products!=null&&products.size()>0){
//					        	  this.message+="第"+indexNum+"行产品产品型号在系统中已经存在<br/>";
//					        	  }
//						}
//			          }
			          
			          
//			          HSSFCell qyxz = hssfRow.getCell((short)3);//产品的产品规格
//			          if (qyxz == null) {
//			        	  this.message+="第"+indexNum+"行产品规格不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(qyxz).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行产品规格不能为空<br/>";
//			        	  }
//			          }
			          
			          
//			          HSSFCell sshy = hssfRow.getCell((short)4);//产品的成本价
//			          if (sshy != null) {
//			        	  String ret =getValue(sshy).trim();
//			        	  if(!ret.equals("")){
//			        		  if(!isNumeric(ret)){
//			        			  this.message+="第"+indexNum+"行产品成本价必须是数字<br/>"; 
//			        		  }else{
//			        			  if(Double.parseDouble(ret)<0){
//				        			  this.message+="第"+indexNum+"行产品成本价必须是正数<br/>"; 
//				        			  }
//			        		  }
//						}
//			          }
//			          
//			          
//			          HSSFCell khzt = hssfRow.getCell((short)5);//产品销售价
//			          if (khzt == null) {
////			        	  this.message+="第"+indexNum+"行产品销售价不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(khzt).trim();
//			        	  if(ret.equals("")){
////			        	  this.message+="第"+indexNum+"行产品销售价不能为空<br/>";
//			        	  }else {
//			        		  if(!isNumeric(ret)){
//			        			  this.message+="第"+indexNum+"行产品销售价必须是数字<br/>"; 
//			        		  }else{
//			        			  if(Double.parseDouble(ret)<0){
//				        			  this.message+="第"+indexNum+"行产品销售价必须是正数<br/>"; 
//				        			  }
//			        		  }
//						}
//			          }
//			          
//			          
//			          HSSFCell khdj = hssfRow.getCell((short)6);//产品类别
//			          if (khdj == null) {
//			        	  this.message+="第"+indexNum+"行产品类别不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(khdj).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行产品类别不能为空<br/>";
//			        	  }else {
//			        		  List<ProductType> productTypes = this.productTypeManager.loadByKey("name", ret);
//			        		  if(productTypes==null||productTypes.size()<1){
//			        			  this.message+="第"+indexNum+"行产品类别在系统中不存在<br/>";  
//			        		  }
//						}
//			          }
//			          
//			          
//			          HSSFCell guojia = hssfRow.getCell((short)7);//来源
//			          if (guojia == null) {
//			        	  this.message+="第"+indexNum+"行来源不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(guojia).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行来源不能为空<br/>";
//			        	  }else {
//			        		  List<CodeValue> codes =codeValueManager.loadByKey("name", ret);
//			        		  if(codes==null||codes.size()<1){
//			        			  this.message+="第"+indexNum+"行来源在系统中不存在<br/>";  
//			        		  }				}
//			          }
//			          
//			          HSSFCell sheng = hssfRow.getCell((short)8);//是否主营
//			          if (sheng == null) {
//			        	  this.message+="第"+indexNum+"行是否主营不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(sheng).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行是否主营不能为空<br/>";
//			        	  }else {
//			        		  if(!ret.equals("是")&&!ret.equals("否")){
//			        			  this.message+="第"+indexNum+"行是否主营只能填写是或者否<br/>";    
//			        		  }
//						}
//			          }
//			          
//			          HSSFCell shi = hssfRow.getCell((short)9);//产品分类
//			          if (shi == null) {
//			        	  this.message+="第"+indexNum+"行产品分类不能为空<br/>";
//			          }else{
//			        	  String ret =getValue(shi).trim();
//			        	  if(ret.equals("")){
//			        	  this.message+="第"+indexNum+"行产品分类不能为空<br/>";
//			        	  }else {
//			        		  List<CodeValue> codes =codeValueManager.loadByKey("name", ret);
//			        		  if(codes==null||codes.size()<1){
//			        			  this.message+="第"+indexNum+"行产品分类在系统中不存在<br/>";  
//			        		  }
//						}
//			          }
			          
			          
			       
			          
			      }
			      
			return this.message;
		}
		
		public void insertFile(HSSFWorkbook hssfWorkbook) throws DaoException{
			List<PersonnelFiles> pros= new ArrayList<PersonnelFiles>();
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		    	  System.out.println(rowNum+"==============================");
//		    	  Products cInfo = new Products();
		    	  PersonnelFiles cInfo = new PersonnelFiles();
//		    	  cInfo.setCode(null);
		          HSSFRow hssfRow = hssfSheet.getRow(rowNum);
//		          HSSFCell code = hssfRow.getCell((short)0);//工号
//		          if(code!=null){
//		        	  String ret_code =getValue(code).trim();
//			          cInfo.setCode(ret_code);
//		          }
		          HSSFCell fileNo = hssfRow.getCell((short)0);//档案编码
		          String ret_fileNo =getValue(fileNo).trim();
		          cInfo.setFileNo(ret_fileNo);
		          HSSFCell name = hssfRow.getCell((short)1);//姓名
		          String ret_name =getValue(name).trim();
		          cInfo.setName(ret_name);
		          HSSFCell sex = hssfRow.getCell((short)2);//性别
		          String ret_sex =getValue(sex).trim();
		          if(ret_sex.equals("女")){
		        	  cInfo.setSex(true);
		          }else{
		        	  cInfo.setSex(false);
		          }
		          HSSFCell birthday = hssfRow.getCell((short)3);//出生日期
		          String rec_birthday =getValue(birthday).trim();
		          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		          Date ret_birthday = null;
					try {
						ret_birthday = sdf.parse(rec_birthday);
					} catch (ParseException e) {
						e.printStackTrace();
					}
		          cInfo.setBirthday(ret_birthday);
		          HSSFCell idNumber = hssfRow.getCell((short)4);//身份证号码
		          if(idNumber!=null){
		        	  String ret_idNumber =getValue(idNumber).trim();
			          cInfo.setIdNumber(ret_idNumber);
		          }		          
		          HSSFCell birthplace = hssfRow.getCell((short)5);//籍贯
		          String ret_birthplace =getValue(birthplace).trim();
		          List<Area> Areas = this.areaManager.loadByKey("name", ret_birthplace);
		          if(Areas!=null&&Areas.size()>0){
		        	  cInfo.setBirthplace(Areas.get(0));
		          }		
		          HSSFCell national = hssfRow.getCell((short)6);//民族
		          if(national!=null){
		        	  String ret_national =getValue(national).trim();
			          String [] key={"parentCV.name","name"};
	        		  String [] value={"民族",ret_national};
			          List<CodeValue> code_national =codeValueManager.loadByKeyArray(key, value);
			          if(code_national!=null&&code_national.size()>0){
	        			  cInfo.setNational(code_national.get(0));
	        		  }
		          }		         
		          HSSFCell marriage = hssfRow.getCell((short)7);//婚姻状况
		          if(marriage!=null){
		        	  String ret_marriage =getValue(marriage).trim();
			          List<CodeValue> code_marriage =codeValueManager.loadByKey("name", ret_marriage);
			          if(code_marriage!=null&&code_marriage.size()>0){
	        			  cInfo.setMarriage(code_marriage.get(0));
	        		  }
		          }       
		          HSSFCell politice = hssfRow.getCell((short)8);//政治面貌
		          String ret_politice =getValue(politice).trim();
		          List<CodeValue> code_politice =codeValueManager.loadByKey("name", ret_politice);
		          if(code_politice!=null&&code_politice.size()>0){
        			  cInfo.setPolitice(code_politice.get(0));
        		  }
		          HSSFCell education = hssfRow.getCell((short)9);//学历
		          if(education!=null){
		        	  String ret_education =getValue(education).trim();
			          List<CodeValue> code_education =codeValueManager.loadByKey("name", ret_education);
			          if(code_education!=null&&code_education.size()>0){
	        			  cInfo.setEducation(code_education.get(0));
	        		  } 
		          }    
		          HSSFCell mobile = hssfRow.getCell((short)10);//手机号码
		          String ret_mobile =getValue(mobile).trim();
		          cInfo.setMobile(ret_mobile);
		          HSSFCell homeTel = hssfRow.getCell((short)11);//家庭电话
		          if(homeTel!=null){
		        	  String ret_homeTel =getValue(homeTel).trim();
			          cInfo.setHomeTel(ret_homeTel);
		          }  
		          HSSFCell email = hssfRow.getCell((short)12);//email
		          if(email!=null){
		        	  String ret_email =getValue(email).trim();
			          cInfo.setEmail(ret_email); 
		          } 
		          HSSFCell inst = hssfRow.getCell((short)13);//单位
		          String ret_inst =getValue(inst).trim();
		          List<Institution> code_inst = institutionManager.loadByKey("name", ret_inst);
		          if(code_inst!=null&&code_inst.size()>0){
        			  cInfo.setInst(code_inst.get(0));
        		  }
		          HSSFCell dept = hssfRow.getCell((short)14);//部门
		          String ret_dept =getValue(dept).trim();
		          List<Department> code_dept = departmentManager.loadByKey("name", ret_dept); 
		          if(code_dept!=null&&code_dept.size()>0){
		        	cInfo.setDept(code_dept.get(0));  
		          }
		          HSSFCell duty = hssfRow.getCell((short)15);//职位
		          String ret_duty =getValue(duty).trim();
		          List<Duty> code_duty = dutyManager.loadByKey("name", ret_duty);
		          if(code_duty!=null&&code_duty.size()>0){
		        	  cInfo.setDuty(code_duty.get(0));
		          }
		          HSSFCell superiorLeader = hssfRow.getCell((short)16);//上级领导
		          if(superiorLeader!=null){
		        	  String ret_superiorLeader =getValue(superiorLeader).trim();
			          List<PersonnelFiles> code_superiorLeader = personnelFilesManager.loadByKey("name", ret_superiorLeader);
			          if(code_superiorLeader!=null&&code_superiorLeader.size()>0){
			        	  cInfo.setSuperiorLeader(code_superiorLeader.get(0));
			          }
		          }     
		          HSSFCell telphone = hssfRow.getCell((short)17);//办公电话
		          if(telphone!=null){
		        	  String ret_telphone =getValue(telphone).trim();
			          cInfo.setTelphone(ret_telphone);  
		          }    
		          HSSFCell entryDate = hssfRow.getCell((short)18);//入职日期
		          String rec_entryDate =getValue(entryDate).trim();
		          Date ret_entryDate = null;
					try {
						ret_entryDate = sdf.parse(rec_entryDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
		          cInfo.setEntryDate(ret_entryDate);
		          HSSFCell regularizedDate = hssfRow.getCell((short)19);//转正日期
		          if(regularizedDate!=null){
		        	  String rec_regularizedDate =getValue(regularizedDate).trim();
			          Date ret_regularizedDate = null;
						try {
							ret_regularizedDate = sdf.parse(rec_regularizedDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
			          cInfo.setEntryDate(ret_regularizedDate);
		          }     
		          HSSFCell status = hssfRow.getCell((short)20);//状态
		          String ret_status =getValue(status).trim();
		          List<CodeValue> code_status =codeValueManager.loadByKey("name", ret_status);
		          if(code_status!=null&&code_status.size()>0){
        			  cInfo.setStatus(code_status.get(0));
        		  }
		          HSSFCell businessType = hssfRow.getCell((short)21);//员工属性（天鹅）
		          String ret_businessType =getValue(businessType).trim();
		          List<CodeValue> code_businessType =codeValueManager.loadByKey("name", ret_businessType);
		          if(code_businessType!=null&&code_businessType.size()>0){
        			  cInfo.setBusinessType(code_businessType.get(0));
        		  }
		          HSSFCell code = hssfRow.getCell((short)22);//工号
		          String ret_code =getValue(code).trim();
		          cInfo.setCode(ret_code);
		          
//		          HSSFCell khfl = hssfRow.getCell((short)2);//产品型号
//		          String ret_khfl =getValue(khfl).trim();
//		          cInfo.setModel(ret_khfl);
//		          HSSFCell qyxz = hssfRow.getCell((short)3);//产品的规格
//		          if(qyxz!=null){
//		          String ret_qyxz =getValue(qyxz).trim();
//		          cInfo.setStandard(ret_qyxz);
//		          }
//		          HSSFCell sshy = hssfRow.getCell((short)4);//产品成本价
//		          if(sshy!=null){
//		          String ret_sshy =getValue(sshy).trim();
//		          if(ret_sshy!=null&&!ret_sshy.equals("")){
//		          cInfo.setEtcPrice(Double.parseDouble(ret_sshy));
//		          }
//		          }
//		          HSSFCell khzt = hssfRow.getCell((short)5);//产品销售价
//		          if(khzt!=null){
//		          String ret_khzt =getValue(khzt).trim();
//		          if(ret_khzt!=null&&!ret_khzt.equals("")){
//			          cInfo.setSalePrice(Double.parseDouble(ret_khzt));
//			          }
//		          }
//		          HSSFCell khdj = hssfRow.getCell((short)6);//产品类别
//		          String ret_khdj =getValue(khdj).trim();
//		          List<ProductType> productTypes = this.productTypeManager.loadByKey("name", ret_khdj);
//		          if(productTypes!=null&&productTypes.size()>0){
//		        	  cInfo.setPt(productTypes.get(0));
//		          }
//		          
//		          HSSFCell guojia = hssfRow.getCell((short)7);//来源
//		          String ret_guojia =getValue(guojia).trim();
//		          List<CodeValue> codes =codeValueManager.loadByKey("name", ret_guojia);
//		          if(codes!=null&&codes.size()>0){
//		          cInfo.setProduct_source_ID(codes.get(0));
//        		  cInfo.setProductSource(ret_guojia);
//		          }
//		          
//		          HSSFCell sheng = hssfRow.getCell((short)8);//是否主营
//		          String ret_sheng =getValue(sheng).trim();
//		          
//		          if(ret_sheng!=null){
//		        	  if(ret_sheng.equals("是")){
//		        		  cInfo.setIsNoMain(true);
//		        	  }else if(ret_sheng.equals("否")){
//		        		  cInfo.setIsNoMain(false);
//		        	  }
//		          }
//		          HSSFCell shi = hssfRow.getCell((short)9);//产品分类
//		          String ret_shi =getValue(shi).trim();
//		          List<CodeValue> ret_shi_codes =codeValueManager.loadByKey("name", ret_shi);
//        		  if(ret_shi_codes!=null&&ret_shi_codes.size()>0){
//        			  cInfo.setBusinessType(ret_shi_codes.get(0));
//        		  }
		          pros.add(cInfo);
		      }
//		      this.message =  this.productsManager.saveProductfoByImp(pros);
		      this.message =  this.personnelFilesManager.savePersonnelFilesfoByImp(pros);
		     
			
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