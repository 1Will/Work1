/*     */package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProfiles;
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
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
/*     */ 
/*     */ public class ImportCustomerInfoAction extends FileTransportAction{
	       private String message="";
	       private final CustomerInfoManager customerInfoManager;
	       private final CodeValueManager codeValueManager;
	       private final AreaManager areaManager;
	       private final UserManager userManager;
	       private final DepartmentManager departmentManager;
	       private final PersonnelFilesManager personnelFilesManager;
/*     */   private static final long serialVersionUID = -877987215782827292L;
/*     */ 

/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	
/*     */   }
			public ImportCustomerInfoAction(CustomerInfoManager customerInfoManager, CodeValueManager codeValueManager,
					AreaManager areaManager,UserManager userManager,PersonnelFilesManager personnelFilesManager,DepartmentManager departmentManager) {
				this.customerInfoManager = customerInfoManager;
				this.codeValueManager = codeValueManager;
				this.areaManager = areaManager;
				this.userManager = userManager;
				this.personnelFilesManager= personnelFilesManager;
				this.departmentManager = departmentManager;
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
			    	  System.out.println(indexNum+"===============================");
			          HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			          if (hssfRow == null) {
			        	  this.message ="第"+indexNum+"行无数据<br/>";
			          }
			          HSSFCell code = hssfRow.getCell((short)0);//客户编码
			          if (code!= null&&!getValue(code).trim().equals("")) {
			        	  List<CustomerInfo> customerInfos = customerInfoManager.loadByKey("code", getValue(code).trim());
			        	  if(customerInfos!=null&&customerInfos.size()>0){
			        	  this.message+="第"+indexNum+"行客户编码在系统中已经存在<br/>";
			        	  }
			          }
			          
			          HSSFCell name = hssfRow.getCell((short)1);//客户名称
			          if (name == null) {
			        	  this.message+="第"+indexNum+"行客户名称不能为空<br/>";
			          }else{
			        	  if(getValue(name).trim().equals("")){
			        	  this.message+="第"+indexNum+"行客户名称不能为空<br/>";
			        	  }else {
			        		  List<CustomerInfo> customerInfos = customerInfoManager.loadByKey("name", getValue(name).trim());
			        		  if(customerInfos!=null&&customerInfos.size()>0){
					        	  this.message+="第"+indexNum+"行客户客户名称在系统中已经存在<br/>";
					        	  }
			        		  
						}
			          }
			          
			          HSSFCell khfl = hssfRow.getCell((short)2);//客户分类
			          if (khfl == null) {
			        	  this.message+="第"+indexNum+"行客户分类不能为空<br/>";
			          }else{
			        	  String ret =getValue(khfl).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行客户分类不能为空<br/>";
			        	  }else {
			        		  List<CodeValue> codeValues =codeValueManager.loadByKey("name", ret);
			        		  if(codeValues==null||codeValues.size()<1){
			        			  this.message+="第"+indexNum+"行客户分类在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			          HSSFCell qyxz = hssfRow.getCell((short)3);//客户的企业性质
			          if (qyxz == null) {
			        	  this.message+="第"+indexNum+"行企业性质不能为空<br/>";
			          }else{
			        	  String ret =getValue(qyxz).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行企业性质不能为空<br/>";
			        	  }else {
			        		  List<CodeValue> codeValues =codeValueManager.loadByKey("name", ret);
			        		  if(codeValues==null||codeValues.size()<1){
			        			  this.message+="第"+indexNum+"行企业性质在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			          HSSFCell sshy = hssfRow.getCell((short)4);//客户的所属行业
			          if (sshy == null) {
			        	  this.message+="第"+indexNum+"行所属行业不能为空<br/>";
			          }else{
			        	  String ret =getValue(sshy).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行所属行业不能为空<br/>";
			        	  }else {
			        		  String [] key={"parentCV.name","name"};
			        		  String [] value={"行业",ret};
			        		  List<CodeValue> codeValues =codeValueManager.loadByKeyArray(key, value);
			        		  if(codeValues==null||codeValues.size()<1){
			        			  this.message+="第"+indexNum+"行所属行业在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          HSSFCell khzt = hssfRow.getCell((short)5);//客户状态
			          if (khzt == null) {
			        	  this.message+="第"+indexNum+"行客户状态不能为空<br/>";
			          }else{
			        	  String ret =getValue(khzt).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行客户状态不能为空<br/>";
			        	  }else {
			        		  String [] key={"parentCV.name","name"};
			        		  String [] value={"客户状态",ret};
			        		  List<CodeValue> codeValues =codeValueManager.loadByKeyArray(key, value);
			        		  if(codeValues==null||codeValues.size()<1){
			        			  this.message+="第"+indexNum+"行客户状态在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			          HSSFCell khdj = hssfRow.getCell((short)6);//客户等级
			          if (khdj == null) {
			        	  this.message+="第"+indexNum+"行客户等级不能为空<br/>";
			          }else{
			        	  String ret =getValue(khdj).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行客户等级不能为空<br/>";
			        	  }else {
			        		  List<CodeValue> codeValues =codeValueManager.loadByKey("name", ret);
			        		  if(codeValues==null||codeValues.size()<1){
			        			  this.message+="第"+indexNum+"行客户等级在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			          HSSFCell guojia = hssfRow.getCell((short)7);//国家
			          if (guojia == null) {
			        	  this.message+="第"+indexNum+"行国家不能为空<br/>";
			          }else{
			        	  String ret =getValue(guojia).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行国家不能为空<br/>";
			        	  }else {
			        		  List<Area> areas =areaManager.loadByKey("name", ret);
			        		  if(areas==null||areas.size()<1){
			        			  this.message+="第"+indexNum+"行国家在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          HSSFCell sheng = hssfRow.getCell((short)8);//省
			          if (sheng == null) {
			        	  this.message+="第"+indexNum+"行省不能为空<br/>";
			          }else{
			        	  String ret =getValue(sheng).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行省不能为空<br/>";
			        	  }else {
			        		  List<Area> areas =areaManager.loadByKey("name", ret);
			        		  if(areas==null||areas.size()<1){
			        			  this.message+="第"+indexNum+"行省在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          HSSFCell shi = hssfRow.getCell((short)9);//市
			          if (shi == null) {
			        	  this.message+="第"+indexNum+"行市不能为空<br/>";
			          }else{
			        	  String ret =getValue(shi).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行市不能为空<br/>";
			        	  }else {
			        		  List<Area> areas =areaManager.loadByKey("name", ret);
			        		  if(areas==null||areas.size()<1){
			        			  this.message+="第"+indexNum+"行市在系统中不存在<br/>";  
			        		  }
						}
			          }
			          
			          
			          
			          HSSFCell zylxr = hssfRow.getCell((short)10);//主要联系人
			          if (zylxr == null) {
			        	  this.message+="第"+indexNum+"行主要联系人不能为空<br/>";
			          }else{
			        	  String ret =getValue(zylxr).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行主要联系人不能为空<br/>";
			        	  }
			          }
			          
			          HSSFCell lxfs = hssfRow.getCell((short)11);//主要联系人联系方式
			          if (lxfs == null) {
			        	  this.message+="第"+indexNum+"行主要联系人手机号不能为空<br/>";
			          }else{
			        	  String ret =getValue(lxfs).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行主要联系人手机号不能为空<br/>";
			        	  }
			          }
			          
			          HSSFCell ywy = hssfRow.getCell((short)12);//业务员
			          if (ywy == null) {
			        	  this.message+="第"+indexNum+"行业务员不能为空<br/>";
			          }else{
			        	  String ret =getValue(ywy).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行业务员不能为空<br/>";
			        	  }else {
			        		  List<PersonnelFiles> personnelFiles =personnelFilesManager.loadByKey("name", ret);
			        		  if(personnelFiles==null||personnelFiles.size()<1){
			        			  this.message+="第"+indexNum+"行业务员在系统中不存在<br/>";  
			        		  }
						}
			          }
			          HSSFCell pqfl = hssfRow.getCell((short)13);//业务员
			          if (pqfl == null) {
			        	  this.message+="第"+indexNum+"行片区分类不能为空<br/>";
			          }else{
			        	  String ret =getValue(pqfl).trim();
			        	  if(ret.equals("")){
			        	  this.message+="第"+indexNum+"行片区分类不能为空<br/>";
			        	  }else {
			        		  List<Department> departments = this.departmentManager.loadByKey("name", ret);
			        		  if(departments==null||departments.size()<1){
			        			  this.message+="第"+indexNum+"行片区分类在系统中不存在<br/>";  
			        		  }
						}
			          }
			      }
			      
			return this.message;
		}
		
		public void insertFile(HSSFWorkbook hssfWorkbook) throws DaoException{
			List<CustomerInfo> customerInfos= new ArrayList<CustomerInfo>();
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		    	  CustomerInfo cInfo = new CustomerInfo();
		    	  cInfo.setCode(null);
		          HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		          HSSFCell code = hssfRow.getCell((short)0);//客户编码
		          if(code!=null){
		        	  String ret_code =getValue(code).trim();
			          cInfo.setCode(ret_code);
		          }
		          HSSFCell name = hssfRow.getCell((short)1);//客户名称
		          String ret_name =getValue(name).trim();
		          cInfo.setName(ret_name);
		          HSSFCell khfl = hssfRow.getCell((short)2);//客户分类
		          String ret_khfl =getValue(khfl).trim();
		          List<CodeValue> code_khfl =codeValueManager.loadByKey("name", ret_khfl);
		          if(code_khfl!=null&&code_khfl.size()>0){
		        	  cInfo.setBusinessType(code_khfl.get(0)); 
		          }
		          HSSFCell qyxz = hssfRow.getCell((short)3);//客户的企业性质
		          String ret_qyxz =getValue(qyxz).trim();
		          List<CodeValue> code_qyxz =codeValueManager.loadByKey("name", ret_qyxz);
		          if(code_qyxz!=null&&code_qyxz.size()>0){
		        	  cInfo.setCompanyNature(code_qyxz.get(0));
		          }
		          HSSFCell sshy = hssfRow.getCell((short)4);//客户的所属行业
		          String ret_sshy =getValue(sshy).trim();
		          String [] key={"parentCV.name","name"};
        		  String [] value={"行业",ret_sshy};
		          List<CodeValue> code_sshy =codeValueManager.loadByKeyArray(key, value);
		          if(code_sshy!=null&&code_sshy.size()>0){
		        	  cInfo.setIndustry(code_sshy.get(0));
		          }
		          HSSFCell khzt = hssfRow.getCell((short)5);//客户状态
		          String ret_khzt =getValue(khzt).trim();
//		          List<CodeValue> code_khzt =codeValueManager.loadByKey("name", ret_khzt);
		          String [] key1={"parentCV.name","name"};
        		  String [] value1={"客户状态",ret_khzt};
        		  List<CodeValue> code_khzt =codeValueManager.loadByKeyArray(key1, value1);
		          if(code_khzt!=null&&code_khzt.size()>0){
		        	  cInfo.setCustomerType(code_khzt.get(0));
		          }
		          HSSFCell khdj = hssfRow.getCell((short)6);//客户等级
		          String ret_khdj =getValue(khdj).trim();
		          List<CodeValue> code_khdj =codeValueManager.loadByKey("name", ret_khdj);
		          if(code_khdj!=null&&code_khdj.size()>0){
		        	  cInfo.setStep(code_khdj.get(0));
		          }
		          
		          HSSFCell guojia = hssfRow.getCell((short)7);//国家
		          String ret_guojia =getValue(guojia).trim();
		          List<Area> country =areaManager.loadByKey("name", ret_guojia);
        		  if(country!=null&&country.size()>0){
        			  cInfo.setCountry(country.get(0));
        		  }
		          
		          HSSFCell sheng = hssfRow.getCell((short)8);//省
		          String ret_sheng =getValue(sheng).trim();
		          
		          List<Area> province =areaManager.loadByKey("name", ret_sheng);
        		  if(province!=null&&province.size()>0){
        			  cInfo.setProvince(province.get(0));
        		  }
		          HSSFCell shi = hssfRow.getCell((short)9);//市
		          String ret_shi =getValue(shi).trim();
		          List<Area> city =areaManager.loadByKey("name", ret_shi);
        		  if(city!=null&&city.size()>0){
        			  cInfo.setCity(city.get(0));
        		  }
		          HSSFCell zylxr = hssfRow.getCell((short)10);//主要联系人
		          String ret_zylxr =getValue(zylxr).trim();
		          cInfo.setKeyContacter(ret_zylxr);
		          HSSFCell lxfs = hssfRow.getCell((short)11);//主要联系人联系方式
		          String ret_lxfs =getValue(lxfs).trim();
		          cInfo.setMobilePhone(ret_lxfs);
		          HSSFCell ywy = hssfRow.getCell((short)12);//业务员
		          String ret_ywy =getValue(ywy).trim();
		          List<PersonnelFiles> personnelFiles =personnelFilesManager.loadByKey("name", ret_ywy);
		          if(personnelFiles!=null&&personnelFiles.size()>0){
		        	  cInfo.setSaleman(personnelFiles.get(0).getName());
		        	  cInfo.setSalesman(personnelFiles.get(0));
		          }
		          HSSFCell pqfl = hssfRow.getCell((short)13);//业务员
		          String ret_pqfl =getValue(pqfl).trim();
		          List<Department> departments =this.departmentManager.loadByKey("name", ret_pqfl);
		          if(departments!=null&&departments.size()>0){
		        	  cInfo.setClassification(departments.get(0));
		          }
		          customerInfos.add(cInfo);
		          
		      }
		      this.customerInfoManager.saveCustomerInfoByImp(customerInfos);
		      this.message="已经成功导入"+customerInfos.size()+"条客户信息";
			
		}
		
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
/*     */ 
/*     */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.presentation.webwork.action.base.helpManual.EditManualAction
 * JD-Core Version:    0.6.2
 */