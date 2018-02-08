/*     */package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;
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
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList.InvoicesList;
import com.yongjun.tdms.model.customercontract.contractmanagement.productlist.ProductList;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.financialmanagement.FinancialManagement;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.project.ProjectInfo;
import com.yongjun.tdms.model.project.projectInfoContract.ProjectInfoContract;
import com.yongjun.tdms.model.project.projectInfoCustomer.ProjectInfoCustomer;
import com.yongjun.tdms.model.project.projectInfoPersonnels.ProjectInfoPersonnels;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.duty.DutyManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.InvoicesManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.invoices.invoicesList.InvoicesListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
/*     */ 
/*     */ public class ImportContractManagementAction extends FileTransportAction{
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
	       private final CustomerInfoManager customerInfoManager;
	       private final ProjectInfoManager projectInfoManager;
	       private final ContactArchivesManager contactArchivesManager;
	       private final ContractManagementManager contractManagementManager;
	       private final ProductListManager productListManager;
	       private final ReturnPlanManager returnPlanManager;
	       private final FinancialManagementManager financialManagementManager;
	       private final BillingRecordManager billingRecordManager;
	       private final InvoicesManager invoicesManager;
	       private final ProjectInfoCustomerManager projectInfoCustomerManager;
	       private final ProjectInfoContractManager projectInfoContractManager;
	       private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	       private final InvoicesListManager invoicesListManager;
/*     */   private static final long serialVersionUID = -877987215782827292L;
/*     */ 

/*     */   public void prepare()
/*     */     throws Exception
/*     */   {
	
/*     */   }
			public ImportContractManagementAction(ProductsManager productsManager, CodeValueManager codeValueManager,
					AreaManager areaManager,UserManager userManager,PersonnelFilesManager personnelFilesManager,ProductTypeManager productTypeManager,InstitutionManager institutionManager,
					DepartmentManager departmentManager,DutyManager dutyManager,CustomerInfoManager customerInfoManager,ProjectInfoManager projectInfoManager,ContactArchivesManager contactArchivesManager,
					ContractManagementManager contractManagementManager,ProductListManager productListManager,ReturnPlanManager returnPlanManager,FinancialManagementManager financialManagementManager,
					BillingRecordManager billingRecordManager,InvoicesManager invoicesManager,ProjectInfoCustomerManager projectInfoCustomerManager,ProjectInfoContractManager projectInfoContractManager,
					ProjectInfoPersonnelsManager projectInfoPersonnelsManager,InvoicesListManager invoicesListManager) {
				this.productsManager = productsManager;
				this.codeValueManager = codeValueManager;
				this.areaManager = areaManager;
				this.userManager = userManager;
				this.personnelFilesManager = personnelFilesManager;
				this.productTypeManager = productTypeManager;
				this.institutionManager = institutionManager;
				this.departmentManager = departmentManager;
				this.dutyManager = dutyManager;
				this.customerInfoManager = customerInfoManager;
				this.projectInfoManager = projectInfoManager;
				this.contactArchivesManager = contactArchivesManager;
				this.contractManagementManager = contractManagementManager;
				this.productListManager = productListManager;
				this.returnPlanManager = returnPlanManager;
				this.financialManagementManager = financialManagementManager;
				this.billingRecordManager = billingRecordManager;
				this.invoicesManager = invoicesManager;
				this.projectInfoCustomerManager = projectInfoCustomerManager;
				this.projectInfoContractManager = projectInfoContractManager;
				this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
				this.invoicesListManager=invoicesListManager;
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
//				 insertDeliveyFile(hssfWorkbook);
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
			} else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA){//计算带公式的值
				String  cellValue=hssfCell.getNumericCellValue()+"";
				return cellValue;			
			}{
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
			          HSSFCell code = hssfRow.getCell((short)3);//合同编码
			          if(code == null){
			        	  this.message+="第"+indexNum+"行合同编码不能为空<br/>";
			          }else{
			        	  if(getValue(code).trim().equals("")){
			        	  this.message+="第"+indexNum+"行合同编码不能为空<br/>";
			        	  }else{
			        	  }
			          }
			          
			          HSSFCell pcontractName = hssfRow.getCell((short)4);//合同名称
			          if(pcontractName == null){
			        	  this.message+="第"+indexNum+"行合同名称不能为空<br/>";
			          }else{
			        	  if(getValue(pcontractName).trim().equals("")){
			        	  this.message+="第"+indexNum+"行合同名称不能为空<br/>";
			        	  }else{
			        		  
			        	  }
			          }
			          
			          HSSFCell pcustomerInfo = hssfRow.getCell((short)5);//客户名称
			          if(pcustomerInfo == null){
			        	  this.message+="第"+indexNum+"行客户名称不能为空<br/>";
			          }else{
			        	  if(getValue(pcustomerInfo).trim().equals("")){
			        	  this.message+="第"+indexNum+"行客户名称不能为空<br/>";
			        	  }else{
			        		  String rec_pcustomerInfo = getValue(pcustomerInfo).trim();
					          List<CustomerInfo> ret_pcustomerInfo = customerInfoManager.loadByKey("name", rec_pcustomerInfo);
					          if(ret_pcustomerInfo==null||ret_pcustomerInfo.size()<1){
					        	  this.message+="第"+indexNum+"行--"+rec_pcustomerInfo+"--客户名称在系统中不存在<br/>";  
			        		  } 
			        	  }
			          }
			          
			          HSSFCell deparment = hssfRow.getCell((short)10);//部门
			          if(deparment == null){
			        	  this.message+="第"+indexNum+"行部门为空<br/>";
			          }else{
			        	  if(getValue(deparment).trim().equals("")){
			        	  this.message+="第"+indexNum+"行部门为空<br/>";
			        	  }else{
			        		  List<Department> depts = departmentManager.loadByKey("name", getValue(deparment).trim());
			        		  if(depts==null||depts.size()<1){
			        			  this.message+="第"+indexNum+"行部门在系统中不存在<br/>";  
			        		  }else{
			        			  String leaderX = depts.get(0).getLeader();
						        	 List<PersonnelFiles> ret_psaleman = personnelFilesManager.loadByKey("name", leaderX);
						        	 if(ret_psaleman==null||ret_psaleman.size()<1){
						        		 this.message+="第"+indexNum+"行负责人在系统中不存在<br/>";  
					        		  } 	
			        		  }
			        	  }
			          }
			          
			          HSSFCell contractType = hssfRow.getCell((short)14);//合同类型
			          if(contractType == null){
			        	  this.message+="第"+indexNum+"行合同类型为空<br/>";
			          }else{
			        	  if(getValue(contractType).trim().equals("")){
			        	  this.message+="第"+indexNum+"行合同类型为空<br/>";
			        	  }else{
			        		  List<CodeValue> code_contractType = codeValueManager.loadByKey("name", getValue(contractType).trim());
			        		  if(code_contractType==null||code_contractType.size()<1){
			        			  this.message+="第"+indexNum+"行合同类型在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          
			          HSSFCell pModel = hssfRow.getCell((short)22);//产品型号
			          if(pModel == null){
			        	  this.message+="第"+indexNum+"行产品型号为空<br/>";
			          }else{
			        	  if(getValue(pModel).trim().equals("")){
			        	  this.message+="第"+indexNum+"行产品型号为空<br/>";
			        	  }else{	  
			        		  List<Products> pds = productsManager.loadByKey("model", getValue(pModel).trim());//根据产品型号获取对应产品信息
			        		  if(pds==null||pds.size()<1){
			        			  this.message+="第"+indexNum+"行--"+pModel+"--产品型号在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          
			          HSSFCell count = hssfRow.getCell((short)23);//数量
			          if(count == null){
			        	  this.message+="第"+indexNum+"行数量为空<br/>";
			          }else{
			        	  if(getValue(count).trim().equals("")){
			        	  this.message+="第"+indexNum+"行数量为空<br/>";
			        	  }else{	  
			        		  String ret =getValue(count).trim();
			        		  if(!isNumeric(ret)){
			        			  this.message+="第"+indexNum+"行数量必须是数字<br/>";
			        		  }else{
			        			  if(Double.parseDouble(ret)<0){
				        			  this.message+="第"+indexNum+"行数量必须是正数<br/>"; 
				        			  }else if(Double.parseDouble(ret)==0){
				        				  this.message+="第"+indexNum+"行数量不能为0<br/>"; 
				        			  }
			        		  }
			        	  }
			          }
			          
			          HSSFCell unit = hssfRow.getCell((short)24);//单位
			          if(unit == null){
			        	  this.message+="第"+indexNum+"行单位为空<br/>";
			          }else{
			        	  if(getValue(unit).trim().equals("")){
			        	  this.message+="第"+indexNum+"行单位为空<br/>";
			        	  }else{
			        		  List<CodeValue> code_unit = codeValueManager.loadByKey("name", getValue(unit).trim());
			        		  if(code_unit==null||code_unit.size()<1){
			        			  this.message+="第"+indexNum+"行单位在系统中不存在<br/>";  
			        		  }
			        	  }
			          }
			          
			          
			          HSSFCell unitPrice = hssfRow.getCell((short)25);//单价
			          if(unitPrice == null){
			        	  this.message+="第"+indexNum+"行单价为空<br/>";
			          }else{
			        	  if(getValue(unitPrice).trim().equals("")){
			        	  this.message+="第"+indexNum+"行单价为空<br/>";
			        	  }else{	  
			        		  String ret =getValue(unitPrice).trim();
			        		  if(!isNumeric(ret)){
			        			  this.message+="第"+indexNum+"行单价必须是数字<br/>";
			        		  }else{
			        			  if(Double.parseDouble(ret)<0){
				        			  this.message+="第"+indexNum+"行单价必须是正数<br/>"; 
				        			  }else if(Double.parseDouble(ret)==0){
				        				  this.message+="第"+indexNum+"行单价不能为0<br/>"; 
				        			  }
			        		  }
			        	  }
			          }
			          
			          HSSFCell totalPrice = hssfRow.getCell((short)26);//总价
			          if(totalPrice == null){
			        	  this.message+="第"+indexNum+"行总价为空<br/>";
			          }else{
			        	  if(getValue(totalPrice).trim().equals("")){
			        	  this.message+="第"+indexNum+"行总价为空<br/>";
			        	  }else{	  
			        		  String ret =getValue(totalPrice).trim();
//			        		  if(!isNumeric(ret)){
//			        			  this.message+="第"+indexNum+"行总价必须是数字<br/>";
//			        		  }else{
//			        			  if(Double.parseDouble(ret)<0){
//				        			  this.message+="第"+indexNum+"行总价必须是正数<br/>"; 
//				        			  }
//			        		  }
			        	  }
			          }
			          
			          
			          HSSFCell plannedDeliveryDate = hssfRow.getCell((short)27);//计划交付日期
			          if(plannedDeliveryDate == null){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			          }else{
			        	  if(getValue(plannedDeliveryDate).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			        	  }else{
			        		  String ret =getValue(plannedDeliveryDate).trim();
			        		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			        		  String data = ret.substring(0,4)+"-"+ret.substring(4,6)+"-"+ret.substring(6,8);
					          Date ret_plannedDeliveryDate = null;
								try {
									ret_plannedDeliveryDate = sdf.parse(data);
								} catch (ParseException e) {
									e.printStackTrace();
									this.message+="第"+indexNum+"行计划交付日期存在格式错误<br/>";
								}
			        		  
			        	  }
			          }
			          
			          HSSFCell deliveryNum = hssfRow.getCell((short)31);//出库单号
			          if(deliveryNum == null){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			          }else{
			        	  if(getValue(deliveryNum).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			        	  }else{
			        		  HSSFCell deliveryDate = hssfRow.getCell((short)32);//交付日期
			        		  if(deliveryDate == null||getValue(deliveryDate).trim().equals("") ){
			        			  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				        		  String ret =getValue(deliveryNum).trim().replace(" ", "").replace("；", ";");
						          if(ret.indexOf(";")==-1){
						        	  if(ret.substring(0,2).equals("20")){
						        		  String data = ret.substring(0,4)+"-"+ret.substring(4,6)+"-"+ret.substring(6,8);
								          Date ret_data = null;
											try {
												ret_data = sdf.parse(data);
											} catch (ParseException e) {
												e.printStackTrace();
												this.message+="第"+indexNum+"行出库单号存在格式错误-日期获取<br/>";
											}
						        	  }else{
						        		  String data = ret.substring(2,6)+"-"+ret.substring(6,8)+"-"+ret.substring(8,10);
								          Date ret_data = null;
											try {
												ret_data = sdf.parse(data);
											} catch (ParseException e) {
												e.printStackTrace();
												this.message+="第"+indexNum+"行出库单号存在格式错误-日期获取<br/>";
											} 
						        	  }
						          }else{
						        	  String [] delis = ret.split(";");
						        	  for(int i=0;i<delis.length;i++){ 
							        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
							        	  String datas = delisx[0].trim();//获取日期
							        	  if(datas.substring(0,2).equals("20")){//判断军民品合同，如果是20开头则为军品
							        		  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
									          Date ret_data = null;
												try {
													ret_data = sdf.parse(data);
												} catch (ParseException e) {
													e.printStackTrace();
													this.message+="第"+indexNum+"行出库单号存在格式错误-日期获取<br/>";
												}
							        	  }else{
							        			  String data = datas.substring(2,6)+"-"+datas.substring(6,8)+"-"+datas.substring(8,10);
										          Date ret_data = null;
													try {
														ret_data = sdf.parse(data);
													} catch (ParseException e) {
														e.printStackTrace();
														this.message+="第"+indexNum+"行出库单号存在格式错误-日期获取<br/>";
													}     		 
							        		  
							        	  }
											
							          }
						          }
				        	  
			        		  }
			        		  
			        	  }
			          }
			          
			          
			          HSSFCell deliveryDate = hssfRow.getCell((short)32);//交付日期
			          if(deliveryDate == null){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			          }else{
			        	  if(getValue(deliveryDate).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			        	  }else{
			        		  String ret =getValue(deliveryDate).trim().replace(" ", "").replace("；", ";");
			        		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			        		  if(ret.indexOf(";")==-1){
			        			  if(ret.substring(0,2).equals("20")){
			        				  String data = ret.substring(0,4)+"-"+ret.substring(4,6)+"-"+ret.substring(6,8);
							          Date ret_plannedDeliveryDate = null;
										try {
											ret_plannedDeliveryDate = sdf.parse(data);
										} catch (ParseException e) {
											e.printStackTrace();
											this.message+="第"+indexNum+"行交付日期存在格式错误<br/>";
										}
			        			  }else{
			        				  String data = ret.substring(2,6)+"-"+ret.substring(6,8)+"-"+ret.substring(8,10);
							          Date ret_data = null;
										try {
											ret_data = sdf.parse(data);
										} catch (ParseException e) {
											e.printStackTrace();
											this.message+="第"+indexNum+"行交付日期存在格式错误<br/>";
										}  
			        			  }
			        		  }else{
			        			  String [] delis = ret.split(";");
					        	  for(int i=0;i<delis.length;i++){  
						        	  String datas = delis[i].trim();//获取日期
						        	  if(datas.substring(0,2).equals("20")){//判断军民品合同，如果是20开头则为军品
						        		  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
								          Date ret_data = null;
											try {
												ret_data = sdf.parse(data);
											} catch (ParseException e) {
												e.printStackTrace();
												this.message+="第"+indexNum+"行交付日期存在格式错误<br/>";
											}
						        	  }else{
						        			  String data = datas.substring(2,6)+"-"+datas.substring(6,8)+"-"+datas.substring(8,10);
									          Date ret_data = null;
												try {
													ret_data = sdf.parse(data);
												} catch (ParseException e) {
													e.printStackTrace();
													this.message+="第"+indexNum+"行交付日期存在格式错误<br/>";
												}     		 
						        		  
						        	  }
									
					        	  } 
			        		  }
			        		  
			        		  
			        	  }
			          }
			          
			          
			          HSSFCell fInvoiceCode = hssfRow.getCell((short)34);//(收款日期)发票号
			          if(fInvoiceCode == null){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			          }else{
			        	  if(getValue(fInvoiceCode).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行计划交付日期为空<br/>";
			        	  }else{
			        		  String ret =getValue(fInvoiceCode).trim().replace(" ", "").replace("；", ";");
			        		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			        		  if(ret.indexOf(";")==-1){
			        			  
			        				  String data = ret.substring(0,4)+"-"+ret.substring(4,6)+"-"+ret.substring(6,8);
							          Date ret_plannedDeliveryDate = null;
										try {
											ret_plannedDeliveryDate = sdf.parse(data);
										} catch (ParseException e) {
											e.printStackTrace();
											this.message+="第"+indexNum+"行发票号存在格式错误-日期<br/>";
										}
			        			  
			        		  }else{
			        			  String [] delis = ret.split(";");
					        	  for(int i=0;i<delis.length;i++){
					        		  String [] datez = delis[i].split("-");
						        	  String datas = datez[0].trim();//获取日期
						        	  
						        		  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
								          Date ret_data = null;
											try {
												ret_data = sdf.parse(data);
											} catch (ParseException e) {
												e.printStackTrace();
												this.message+="第"+indexNum+"行发票号存在格式错误-日期<br/>";
											}
						        	 
									
					        	  } 
			        		  }
			        		  
			        		  
			        	  }
			          }
			          
			          
			          
//			          HSSFCell qualityControl = hssfRow.getCell((short)28);//质检
//			          if(qualityControl == null){
//			        	  this.message+="第"+indexNum+"行质检为空<br/>";
//			          }else{
//			        	  if(getValue(qualityControl).trim().equals("")){
//			        	  this.message+="第"+indexNum+"行质检为空<br/>";
//			        	  }else{
//			        		  List<CodeValue> code_qualityControl = codeValueManager.loadByKey("name", getValue(qualityControl).trim());
//			        		  if(code_qualityControl==null||code_qualityControl.size()<1){
//			        			  this.message+="第"+indexNum+"行质检在系统中不存在<br/>";  
//			        		  }
//			        	  }
//			          }
			          
			          
			      }
			      
			return this.message;
		}
		
		public void insertFile(HSSFWorkbook hssfWorkbook) throws DaoException{
			List<ContractManagement> pros= new ArrayList<ContractManagement>();
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		    	  System.out.println(rowNum+"==============================");
//		    	  Products cInfo = new Products();
		    	  ContractManagement cInfo = new ContractManagement();//合同
		    	  ProjectInfo pInfo = new ProjectInfo();//项目
		    	  ReturnPlan rInfo = new ReturnPlan();//收款计划
		    	  ProjectInfoCustomer pjcuInfo = new ProjectInfoCustomer();//项目中的项目客户
		    	  ProjectInfoContract pjcoInfo = new ProjectInfoContract();//项目中的联系人列表
		    	  
		    	  
		          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		    	  cInfo.setCode(null);
		          HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		          
		          HSSFCell code = hssfRow.getCell((short)3);//合同编码
		          String ret_code = getValue(code).trim();		              
		          List<ContractManagement> tempList = this.contractManagementManager.loadByKey("code", ret_code);
		        if(tempList!=null&&tempList.size()>0){//判断系统中是否已经存在该合同
		        	  cInfo =tempList.get(0);
		          }else{
		          cInfo.setCode(ret_code);//设置合同编码
		          
		          //以下为设置项目部分
//		          pInfo.setCode(ret_code);//项目编码
		          String pjName = null;//设置项目名称为空
		          HSSFCell pcustomerInfo = hssfRow.getCell((short)5);//客户名称
		          String rec_pcustomerInfo = getValue(pcustomerInfo).trim();
		          List<CustomerInfo> ret_pcustomerInfo = customerInfoManager.loadByKey("name", rec_pcustomerInfo);
		          if(ret_pcustomerInfo!=null&&ret_pcustomerInfo.size()>0){
		        	  List<ProjectInfo> pjInfo = projectInfoManager.loadByKey("customer", ret_pcustomerInfo.get(0).getId());
		        	  if(pjInfo !=null&&pjInfo.size()>0){
		        		  pjName = pjInfo.get(0).getName();//如果通过客户名称可以查询到项目名称则赋值
		        	  }
        		  }
		          if(pjName!=null){
		        	  //判断项目名称是否存在，如果存在则直接把名称给予合同
		        	  List<ProjectInfo> ret_project = projectInfoManager.loadByKey("name", pjName);
			          if(ret_project!=null&&ret_project.size()>0){
	        			  cInfo.setProject(ret_project.get(0));
	        		  }
		          }else{
		        	  HSSFCell pcontractName = hssfRow.getCell((short)4);//合同名称
				      String ret_pcontractName = getValue(pcontractName).trim();
				      String rec_project = rec_pcustomerInfo+"-"+ret_pcontractName+"项目";//项目名称（客户名+合同名称+项目）
			          pInfo.setName(rec_project);//项目名称
			          
			          //客户名称
			          if(ret_pcustomerInfo!=null&&ret_pcustomerInfo.size()>0){
	        			  pInfo.setCustomer(ret_pcustomerInfo.get(0));//项目中的客户名称
//	        			  rInfo.setCustomerInfo(ret_pcustomerInfo.get(0));//收款计划中的客户信息
	        		  }
			          
			          HSSFCell plinkman = hssfRow.getCell((short)6);//联系人
			          if(plinkman == null||"".equals(getValue(plinkman).trim())){
			        	  //如果表格里面没有联系人，则从客户信息内获取
			        	  List<ContactArchives> ret_plinkman = contactArchivesManager.loadByKey("name", ret_pcustomerInfo.get(0).getKeyContacter());
			        	  if(ret_plinkman!=null&&ret_plinkman.size()>0){
		        			  pInfo.setContact(ret_plinkman.get(0));//项目联系人
		        			  pjcoInfo.setContactArchives(ret_plinkman.get(0));//设置项目联系人列表
		        		  }
			          }else{
			        	  String rec_plinkman = getValue(plinkman).trim();
				          List<ContactArchives> ret_plinkman = contactArchivesManager.loadByKey("name", rec_plinkman);
				          if(ret_plinkman!=null&&ret_plinkman.size()>0){
		        			  pInfo.setContact(ret_plinkman.get(0));//项目联系人
		        			  pjcoInfo.setContactArchives(ret_plinkman.get(0));//设置项目联系人列表
		        		  } 
			          }
			          
			          
			          HSSFCell psaleman = hssfRow.getCell((short)9);//负责人
			          if(psaleman == null||"".equals(getValue(psaleman).trim())){//如果负责人为空，则从部门中获取第一位get(0)
			        	  HSSFCell deparment = hssfRow.getCell((short)10);//部门
				          String rec_department = getValue(deparment).trim();
				          List<Department> ret_department = departmentManager.loadByKey("name", rec_department);
				          if(ret_department!=null&&ret_department.size()>0){
				        	      pInfo.setDepartment(ret_department.get(0));
				        	      String leaderX = ret_department.get(0).getLeader();
				        	 List<PersonnelFiles> ret_psaleman = personnelFilesManager.loadByKey("name", leaderX);
				        	 if(ret_psaleman!=null&&ret_psaleman.size()>0){
			        			  pInfo.setController(ret_psaleman.get(0));//项目负责人
			        		  } 	  
		        		  }
			          }else{
			        	  
			        	  HSSFCell deparment = hssfRow.getCell((short)10);//部门
				          String rec_department = getValue(deparment).trim();
				          List<Department> ret_department = departmentManager.loadByKey("name", rec_department);
				          if(ret_department!=null&&ret_department.size()>0){
				        	      pInfo.setDepartment(ret_department.get(0));
				          }
			        	  
			        	  String rec_psaleman = getValue(psaleman).trim();
				          List<PersonnelFiles> ret_psaleman = personnelFilesManager.loadByKey("name", rec_psaleman);
				          if(ret_psaleman!=null&&ret_psaleman.size()>0){
		        			  pInfo.setController(ret_psaleman.get(0));//项目负责人
		        		  } 
			          }
			          
			          
			          
			          

			          String [] key={"parentCV.name","name"};
            		  String [] value={"项目状态","合同"};
			          List<CodeValue> code_pstate =codeValueManager.loadByKeyArray(key, value);
			          if(code_pstate!=null&&code_pstate.size()>0){
			              pInfo.setState(code_pstate.get(0));//项目状态
			          }
			          
			          String pbusinessType = "小甜甜";
			          List<CodeValue> code_pbusinessType =codeValueManager.loadByKey("name", pbusinessType);
			          if(code_pbusinessType!=null&&code_pbusinessType.size()>0){
			        	  pInfo.setBusinessType(code_pbusinessType.get(0));//项目联系人业务属性
			        	  pjcoInfo.setBusinessType(code_pbusinessType.get(0));//项目联系人列表业务属性
	        		  }	
			          
			          //项目创建者
			          String createUser = "管理员";
			          List<User> ret_createUser = userManager.loadByKey("name", createUser);
			          if(ret_createUser!=null&&ret_createUser.size()>0){
			        	  pInfo.setCreateUser(ret_createUser.get(0));
			          }

                      //设置提交
			          pInfo.setIsSaved("1");
			          
			          this.projectInfoManager.storeProjectInfo(pInfo);//新增项目
			          
			          //以下为项目细节部分
			          //设置项目客户（tab页）
			          if(ret_pcustomerInfo!=null&&ret_pcustomerInfo.size()>0){
	        			  pjcuInfo.setCustomerInfo(ret_pcustomerInfo.get(0));
	        		  }
			          //主要说明
			          String outline = "无";
			          pjcuInfo.setOutline(outline);
			              
			          
			          //设置联系人列表			          
			          pjcoInfo.setOutline(outline);//项目联系人列表主要说明
			          
			          
			          
			          
			          
			        //使用新增项目中的项目名给予合同;赋予tab页项目客户
			          List<ProjectInfo> ret_project = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_project!=null&&ret_project.size()>0){
	        			  cInfo.setProject(ret_project.get(0));
	        			  pjcuInfo.setProjectInfo(ret_project.get(0));
	        			  pjcoInfo.setProjectInfo(ret_project.get(0));
	        		  }
			          
			          //设置项目客户
			          this.projectInfoCustomerManager.storeProjectInfoCustomer(pjcuInfo);
			          //设置项目联系人列表
			          this.projectInfoContractManager.storeProjectInfoContract(pjcoInfo);
			          
			          
			          
			          ProjectInfoPersonnels pjpeInfo1 = new ProjectInfoPersonnels();//项目中的项目组成员1
//			          ProjectInfoPersonnels pjpeInfo2 = new ProjectInfoPersonnels();//项目中的项目组成员2销售部领导
			          ProjectInfoPersonnels pjpeInfol3 = new ProjectInfoPersonnels();//项目中的项目组成员3事业部部长
			          ProjectInfoPersonnels pjpeInfoz3 = new ProjectInfoPersonnels();//项目中的项目组成员3事业部部长
			          ProjectInfoPersonnels pjpeInfo4 = new ProjectInfoPersonnels();//项目中的项目组成员4汪总
//			          ProjectInfoPersonnels pjpeInfo5 = new ProjectInfoPersonnels();//企划部
			          ProjectInfoPersonnels pjpeInfol6 = new ProjectInfoPersonnels();//综合部
			          ProjectInfoPersonnels pjpeInfoz6 = new ProjectInfoPersonnels();//综合部
			          ProjectInfoPersonnels pjpeInfo7 = new ProjectInfoPersonnels();//生产部
			          //判断是否军品，军品编码为20开头
			          if(ret_code.substring(0,2).equals("20")){
			        	  //销售部领导
//			        	  List<Department> pjp_department2 = departmentManager.loadByKey("name", "军品销售");
//				          if(pjp_department2!=null&&pjp_department2.size()>0){
//				        	 List<PersonnelFiles> pjp_psaleman2 = personnelFilesManager.loadByKey("name", pjp_department2.get(0).getLeader());
//				        	 if(pjp_psaleman2!=null&&pjp_psaleman2.size()>0){
//				        		 pjpeInfo2.setProPerson(pjp_psaleman2.get(0));
//			        		  } 	  
//		        		  }
				          //事业部部长
//				          List<Department> pjp_department3 = departmentManager.loadByKey("name", "军品事业部");
//				          if(pjp_department3!=null&&pjp_department3.size()>0){
//				        	 List<PersonnelFiles> pjp_psaleman3 = personnelFilesManager.loadByKey("name", pjp_department3.get(0).getLeader());
//				        	 if(pjp_psaleman3!=null&&pjp_psaleman3.size()>0){
//				        		 pjpeInfo3.setProPerson(pjp_psaleman3.get(0));
//			        		  } 	  
//		        		  }
				          List<PersonnelFiles> pjp_psalemanl3 = personnelFilesManager.loadByKey("name", "丁元生");
				        	 if(pjp_psalemanl3!=null&&pjp_psalemanl3.size()>0){
				        		 pjpeInfol3.setProPerson(pjp_psalemanl3.get(0));
			        		  }
				          List<PersonnelFiles> pjp_psalemanz3 = personnelFilesManager.loadByKey("name", "姚平");
				        	 if(pjp_psalemanz3!=null&&pjp_psalemanz3.size()>0){
				        		 pjpeInfoz3.setProPerson(pjp_psalemanz3.get(0));
			        		  } 
				           
				          //军品综合部
//				          List<Department> pjp_department6 = departmentManager.loadByKey("name", "军品综合处");
//				          if(pjp_department6!=null&&pjp_department6.size()>0){
//				        	 List<PersonnelFiles> pjp_psaleman6 = personnelFilesManager.loadByKey("name", pjp_department6.get(0).getLeader());
//				        	 if(pjp_psaleman6!=null&&pjp_psaleman6.size()>0){
//				        		 pjpeInfo6.setProPerson(pjp_psaleman6.get(0));
//			        		  } 	  
//		        		  }
			        	 List<PersonnelFiles> pjp_psalemanl6 = personnelFilesManager.loadByKey("name", "冯素丽");
				        	 if(pjp_psalemanl6!=null&&pjp_psalemanl6.size()>0){
				        		 pjpeInfol6.setProPerson(pjp_psalemanl6.get(0));
			        		  }
			             List<PersonnelFiles> pjp_psalemanz6 = personnelFilesManager.loadByKey("name", "丁翠");
				        	 if(pjp_psalemanz6!=null&&pjp_psalemanz6.size()>0){
				        		 pjpeInfoz6.setProPerson(pjp_psalemanz6.get(0));
			        		  }
				        	 
	        		  }else{
	        			  //销售部领导
//	        			  List<Department> pjp_department2 = departmentManager.loadByKey("name", "民品销售");
//				          if(pjp_department2!=null&&pjp_department2.size()>0){
//				        	 List<PersonnelFiles> pjp_psaleman2 = personnelFilesManager.loadByKey("name", pjp_department2.get(0).getLeader());
//				        	 if(pjp_psaleman2!=null&&pjp_psaleman2.size()>0){
//				        		 pjpeInfo2.setProPerson(pjp_psaleman2.get(0));
//			        		  } 	  
//		        		  }
				          //事业部部长
//				          List<Department> pjp_department3 = departmentManager.loadByKey("name", "民品事业部");
//				          if(pjp_department3!=null&&pjp_department3.size()>0){
//				        	 List<PersonnelFiles> pjp_psaleman3 = personnelFilesManager.loadByKey("name", pjp_department3.get(0).getLeader());
//				        	 if(pjp_psaleman3!=null&&pjp_psaleman3.size()>0){
//				        		 pjpeInfo3.setProPerson(pjp_psaleman3.get(0));
//			        		  } 	  
//		        		  }
				          List<PersonnelFiles> pjp_psalemanl3 = personnelFilesManager.loadByKey("name", "张俊");
				        	 if(pjp_psalemanl3!=null&&pjp_psalemanl3.size()>0){
				        		 pjpeInfol3.setProPerson(pjp_psalemanl3.get(0));
			        		  }
				          List<PersonnelFiles> pjp_psalemanz3 = personnelFilesManager.loadByKey("name", "王西成");
				        	 if(pjp_psalemanz3!=null&&pjp_psalemanz3.size()>0){
				        		 pjpeInfoz3.setProPerson(pjp_psalemanz3.get(0));
			        		  }
				          //民品综合部
//				          List<Department> pjp_department6 = departmentManager.loadByKey("name", "民品综合处");
//				          if(pjp_department6!=null&&pjp_department6.size()>0){
//				        	 List<PersonnelFiles> pjp_psaleman6 = personnelFilesManager.loadByKey("name", pjp_department6.get(0).getLeader());
//				        	 if(pjp_psaleman6!=null&&pjp_psaleman6.size()>0){
//				        		 pjpeInfo6.setProPerson(pjp_psaleman6.get(0));
//			        		  } 	  
//		        		  }
			        	 List<PersonnelFiles> pjp_psalemanl6 = personnelFilesManager.loadByKey("name", "何慧玲");
				        	 if(pjp_psalemanl6!=null&&pjp_psalemanl6.size()>0){
				        		 pjpeInfol6.setProPerson(pjp_psalemanl6.get(0));
			        		  }
			             List<PersonnelFiles> pjp_psalemanz6 = personnelFilesManager.loadByKey("name", "胡承燕");
				        	 if(pjp_psalemanz6!=null&&pjp_psalemanz6.size()>0){
				        		 pjpeInfoz6.setProPerson(pjp_psalemanz6.get(0));
			        		  }
				        	 
	        		  }		 
			        //设置项目组成员
			          //1.大区经理-项目负责人
			         
			          List<ProjectInfo> ret_project1 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_project1!=null&&ret_project1.size()>0){
			        	  pjpeInfo1.setProjectInfo(ret_project1.get(0));
			        	  pjpeInfo1.setProPerson(ret_project1.get(0).getController());//取项目负责人
	        		  }
			          //主要说明
			          pjpeInfo1.setOutline(outline);
			          //业务属性
			          String pjf_businessType1 = "项目负责人";
			          List<CodeValue> code_pjfbusinessType1 =codeValueManager.loadByKey("name", pjf_businessType1);
			          if(code_pjfbusinessType1!=null&&code_pjfbusinessType1.size()>0){
			        	  pjpeInfo1.setBusinessType(code_pjfbusinessType1.get(0));//1设置为项目负责人
	        		  }			          			     
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfo1);
			          //2.销售部领导  客户关系维护
			          
			          if(ret_code.substring(0,2).equals("20")){//军民品区分
			        	  HSSFCell deparment = hssfRow.getCell((short)10);//部门
				          String rec_department = getValue(deparment).trim();
			        	  List<Department> pjp_department2 = departmentManager.loadByKey("name", rec_department);
//				          List<Duty> pjp_duty2 = dutyManager.loadByKey("name", "项目经理");
			        	  List<Duty> pjp_duty2 = dutyManager.loadByDutyId("项目经理", pjp_department2.get(0).getId());
			        	  
				          if(pjp_duty2!=null&&pjp_duty2.size()>0){			          
				          List<PersonnelFiles> pjp_psalemanj2 = personnelFilesManager.loadByName(pjp_department2.get(0).getId(), pjp_duty2.get(0).getId());
				          if(pjp_psalemanj2!=null&&pjp_psalemanj2.size()>0){
				        	 for(int i=0;i<pjp_psalemanj2.size();i++){
				        		 ProjectInfoPersonnels pjpeInfo2 = new ProjectInfoPersonnels();//项目中的项目组成员2销售部领导
				        		 pjpeInfo2.setProPerson(pjp_psalemanj2.get(i));//循环赋予角色属性
				        		 
				        		 List<ProjectInfo> ret_project2 = projectInfoManager.loadByKey("name", rec_project);
						          if(ret_project2!=null&&ret_project2.size()>0){
						        	  pjpeInfo2.setProjectInfo(ret_project2.get(0));
				        		  }
						          //主要说明
						          pjpeInfo2.setOutline(outline);
						          //业务属性
						          String pjf_businessType2 = "客户关系维护";
						          List<CodeValue> code_pjfbusinessType2 =codeValueManager.loadByKey("name", pjf_businessType2);
						          if(code_pjfbusinessType2!=null&&code_pjfbusinessType2.size()>0){
						        	  pjpeInfo2.setBusinessType(code_pjfbusinessType2.get(0));//1设置为项目负责人
				        		  }				          
						          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfo2);
				        	 }
		        		  }
				          }
			          }else{
			        	  HSSFCell deparment = hssfRow.getCell((short)10);//部门
				          String rec_department = getValue(deparment).trim();
			        	  List<Department> pjp_department2 = departmentManager.loadByKey("name", rec_department);
//				          List<Duty> pjp_duty2 = dutyManager.loadByKey("name", "项目经理");
			        	  List<Duty> pjp_duty2 = dutyManager.loadByDutyId("项目经理", pjp_department2.get(0).getId());
				          if(pjp_duty2!=null&&pjp_duty2.size()>0){        
				          List<PersonnelFiles> pjp_psalemanj2 = personnelFilesManager.loadByName(pjp_department2.get(0).getId(), pjp_duty2.get(0).getId());
				          if(pjp_psalemanj2!=null&&pjp_psalemanj2.size()>0){
				        	 for(int i=0;i<pjp_psalemanj2.size();i++){
				        		 ProjectInfoPersonnels pjpeInfo2 = new ProjectInfoPersonnels();//项目中的项目组成员2销售部领导
				        		 pjpeInfo2.setProPerson(pjp_psalemanj2.get(i));//循环赋予角色属性
				        		 
				        		 List<ProjectInfo> ret_project2 = projectInfoManager.loadByKey("name", rec_project);
						          if(ret_project2!=null&&ret_project2.size()>0){
						        	  pjpeInfo2.setProjectInfo(ret_project2.get(0));
				        		  }
						          //主要说明
						          pjpeInfo2.setOutline(outline);
						          //业务属性
						          String pjf_businessType2 = "客户关系维护";
						          List<CodeValue> code_pjfbusinessType2 =codeValueManager.loadByKey("name", pjf_businessType2);
						          if(code_pjfbusinessType2!=null&&code_pjfbusinessType2.size()>0){
						        	  pjpeInfo2.setBusinessType(code_pjfbusinessType2.get(0));//1设置为项目负责人
				        		  }				          
						          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfo2);
				        	 }
		        		  }
				          
			          }
			          }
			          
			          
			          
			          
                      //3.事业部部长
			          
			          List<ProjectInfo> ret_projectl3 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_projectl3!=null&&ret_projectl3.size()>0){
			        	  pjpeInfol3.setProjectInfo(ret_projectl3.get(0));
	        		  }
			          //主要说明
			          pjpeInfol3.setOutline(outline);
			          //业务属性
			          String pjf_businessType3 = "项目监事";
			          List<CodeValue> code_pjfbusinessTypel3 =codeValueManager.loadByKey("name", pjf_businessType3);
			          if(code_pjfbusinessTypel3!=null&&code_pjfbusinessTypel3.size()>0){
			        	  pjpeInfol3.setBusinessType(code_pjfbusinessTypel3.get(0));//1设置为项目负责人
	        		  }	
			          
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfol3);
			          
			          List<ProjectInfo> ret_projectz3 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_projectz3!=null&&ret_projectz3.size()>0){
			        	  pjpeInfoz3.setProjectInfo(ret_projectz3.get(0));
	        		  }
			          //主要说明
			          pjpeInfoz3.setOutline(outline);
			          //业务属性
			          
			          List<CodeValue> code_pjfbusinessTypez3 =codeValueManager.loadByKey("name", pjf_businessType3);
			          if(code_pjfbusinessTypez3!=null&&code_pjfbusinessTypez3.size()>0){
			        	  pjpeInfoz3.setBusinessType(code_pjfbusinessTypez3.get(0));//1设置为项目负责人
	        		  }	
			          
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfoz3);
			          
                      //4.汪总
			          
			          List<ProjectInfo> ret_project4 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_project4!=null&&ret_project4.size()>0){
			        	  pjpeInfo4.setProjectInfo(ret_project4.get(0));
	        		  }
			          //主要说明
			          pjpeInfo4.setOutline(outline);
			          //业务属性
			          String pjf_businessType4 = "其他";
			          List<CodeValue> code_pjfbusinessType4 =codeValueManager.loadByKey("name", pjf_businessType4);
			          if(code_pjfbusinessType4!=null&&code_pjfbusinessType4.size()>0){
			        	  pjpeInfo4.setBusinessType(code_pjfbusinessType4.get(0));//1设置为项目负责人
	        		  }
			          
			          List<PersonnelFiles> pjp_psaleman4 = personnelFilesManager.loadByKey("name", "汪长江");
			        	 if(pjp_psaleman4!=null&&pjp_psaleman4.size()>0){
			        		 pjpeInfo4.setProPerson(pjp_psaleman4.get(0));
		        		  } 
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfo4);
			          
                      //5.企划部 项目流程品质
			          
//			          List<ProjectInfo> ret_project5 = projectInfoManager.loadByKey("name", rec_project);
//			          if(ret_project5!=null&&ret_project5.size()>0){
//			        	  pjpeInfo5.setProjectInfo(ret_project5.get(0));
//	        		  }
//			          //主要说明
//			          pjpeInfo5.setOutline(outline);
//			          //业务属性
//			          String pjf_businessType5 = "项目流程品质";
//			          List<CodeValue> code_pjfbusinessType5 =codeValueManager.loadByKey("name", pjf_businessType5);
//			          if(code_pjfbusinessType5!=null&&code_pjfbusinessType5.size()>0){
//			        	  pjpeInfo5.setBusinessType(code_pjfbusinessType5.get(0));//1设置为项目负责人
//	        		  }
//			          
//			          List<Department> pjp_department5 = departmentManager.loadByKey("name", "销管处");
//			          if(pjp_department5!=null&&pjp_department5.size()>0){
//			        	 List<PersonnelFiles> pjp_psaleman5 = personnelFilesManager.loadByKey("name", "许静");
//			        	 if(pjp_psaleman5!=null&&pjp_psaleman5.size()>0){
//			        		 pjpeInfo5.setProPerson(pjp_psaleman5.get(0));
//		        		  } 	  
//	        		  } 
//			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfo5);
			          
			          
                      //6.综合部 项目后台管理
			          
			          List<ProjectInfo> ret_projectl6 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_projectl6!=null&&ret_projectl6.size()>0){
			        	  pjpeInfol6.setProjectInfo(ret_projectl6.get(0));
	        		  }
			          //主要说明
			          pjpeInfol6.setOutline(outline);
			          //业务属性
			          String pjf_businessType6 = "项目后台管理";
			          List<CodeValue> code_pjfbusinessTypel6 =codeValueManager.loadByKey("name", pjf_businessType6);
			          if(code_pjfbusinessTypel6!=null&&code_pjfbusinessTypel6.size()>0){
			        	  pjpeInfol6.setBusinessType(code_pjfbusinessTypel6.get(0));//1设置为项目负责人
	        		  }          
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfol6);
			          
			          List<ProjectInfo> ret_projectz6 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_projectz6!=null&&ret_projectz6.size()>0){
			        	  pjpeInfoz6.setProjectInfo(ret_projectz6.get(0));
	        		  }
			          //主要说明
			          pjpeInfoz6.setOutline(outline);
			          //业务属性
			          
			          List<CodeValue> code_pjfbusinessTypez6 =codeValueManager.loadByKey("name", pjf_businessType6);
			          if(code_pjfbusinessTypez6!=null&&code_pjfbusinessTypez6.size()>0){
			        	  pjpeInfoz6.setBusinessType(code_pjfbusinessTypez6.get(0));//1设置为项目负责人
	        		  }          
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfoz6);
			          
                      //7.生产部 生产管理负责人
			          
			          List<ProjectInfo> ret_project7 = projectInfoManager.loadByKey("name", rec_project);
			          if(ret_project7!=null&&ret_project7.size()>0){
			        	  pjpeInfo7.setProjectInfo(ret_project7.get(0));
	        		  }
			          //主要说明
			          pjpeInfo7.setOutline(outline);
			          //业务属性
			          String pjf_businessType7 = "生产管理负责人";
			          List<CodeValue> code_pjfbusinessType7 =codeValueManager.loadByKey("name", pjf_businessType7);
			          if(code_pjfbusinessType7!=null&&code_pjfbusinessType7.size()>0){
			        	  pjpeInfo7.setBusinessType(code_pjfbusinessType7.get(0));//1设置为项目负责人
	        		  }     
			          List<PersonnelFiles> pjp_psaleman7 = personnelFilesManager.loadByKey("name", "陈晨");
			        	 if(pjp_psaleman7!=null&&pjp_psaleman7.size()>0){
			        		 pjpeInfo7.setProPerson(pjp_psaleman7.get(0));
		        		  } 
			          
			          this.projectInfoPersonnelsManager.storeProjectInfoPersonnels(pjpeInfo7);
			          
		          }
		          
		          
		          
		          
		          
		          
		          
		          
		          
		          //以下为合同
		          HSSFCell contractName = hssfRow.getCell((short)4);//合同名称
			      String ret_contractName = getValue(contractName).trim();
		          cInfo.setContractName(ret_contractName);
		          
		          HSSFCell customerInfo = hssfRow.getCell((short)5);//客户名称
		          String rec_customerInfo = getValue(customerInfo).trim();
		          List<CustomerInfo> ret_customerInfo = customerInfoManager.loadByKey("name", rec_customerInfo);
		          if(ret_customerInfo!=null&&ret_customerInfo.size()>0){
        			  cInfo.setCustomerInfo(ret_customerInfo.get(0));
        			  //变更客户状态
        			  CustomerInfo cusInfo = ret_customerInfo.get(0);
        			  String [] key={"parentCV.name","name"};
            		  String [] value={"客户状态","合同"};
        			  List<CodeValue> code_customerType =codeValueManager.loadByKeyArray(key, value);
			          if(code_customerType!=null&&code_customerType.size()>0){
			        	  cusInfo.setCustomerType(code_customerType.get(0));
	        		  }
			          List<CodeValue> code_step =codeValueManager.loadByKey("name", "5星");
			          if(code_step!=null&&code_step.size()>0){
			        	  cusInfo.setStep(code_step.get(0));
	        		  }
        			  this.customerInfoManager.storeCustomerInfo(cusInfo);
        			  
        		  }
		          
//		          HSSFCell project = hssfRow.getCell((short)3);//项目名称
		          
//		          List<ProjectInfo> ret_project = projectInfoManager.loadByKey("name", rec_project);
//		          if(ret_project!=null&&ret_project.size()>0){
//        			  cInfo.setProject(ret_project.get(0));
//        		  }
		          
		          HSSFCell linkman = hssfRow.getCell((short)6);//联系人
		          if(linkman == null||"".equals(getValue(linkman).trim())){
		        	  //如果表格里面没有联系人，则从客户信息内获取
		        	  List<ContactArchives> ret_linkman = contactArchivesManager.loadByKey("name", ret_customerInfo.get(0).getKeyContacter());
		        	  if(ret_linkman!=null&&ret_linkman.size()>0){
		        		  cInfo.setLinkman(ret_linkman.get(0));
	        		  }
		          }else{
		        	  String rec_linkman = getValue(linkman).trim();
			          List<ContactArchives> ret_linkman = contactArchivesManager.loadByKey("name", rec_linkman);
			          if(ret_linkman!=null&&ret_linkman.size()>0){
	        			  cInfo.setLinkman(ret_linkman.get(0));
	        		  }
		          }
		          
		          HSSFCell telephone = hssfRow.getCell((short)7);//联系电话
		          if(telephone == null||"".equals(getValue(telephone).trim())){//如果表格内没联系电话就从客户信息里取
		        	  cInfo.setTelephone(ret_customerInfo.get(0).getMobilePhone());
		          }else{
		        	  String ret_telephone = getValue(telephone).trim();
			          cInfo.setTelephone(ret_telephone); 
		          }
		          
		          
		          HSSFCell address = hssfRow.getCell((short)8);//联系地址
		          if(address == null||"".equals(getValue(address).trim())){//如果表格内没联系地址
		        	  cInfo.setAddress("无");
		          }else{
		        	  String ret_address = getValue(address).trim();
			          cInfo.setAddress(ret_address);  
		          }
		          
		          HSSFCell saleman = hssfRow.getCell((short)9);//负责人
		          if(saleman == null||"".equals(getValue(saleman).trim())){//如果表格内没有负责人
		        	  HSSFCell deparmentz = hssfRow.getCell((short)10);//部门
			          String rec_departmentz = getValue(deparmentz).trim();
			          List<Department> ret_departmentz = departmentManager.loadByKey("name", rec_departmentz);
			          if(ret_departmentz!=null&&ret_departmentz.size()>0){
			        	      String leaderZ = ret_departmentz.get(0).getLeader();
		        	  List<PersonnelFiles> ret_psaleman = personnelFilesManager.loadByKey("name", leaderZ);
			        	 if(ret_psaleman!=null&&ret_psaleman.size()>0){
		        			  cInfo.setSaleman(ret_psaleman.get(0));//项目负责人
		        		  } 
			          }
		          }else{
		        	  String rec_saleman = getValue(saleman).trim();
			          List<PersonnelFiles> ret_saleman = personnelFilesManager.loadByKey("name", rec_saleman);
			          if(ret_saleman!=null&&ret_saleman.size()>0){
	        			  cInfo.setSaleman(ret_saleman.get(0));
	        		  }
		          }
		          
		          HSSFCell deparmentC = hssfRow.getCell((short)10);//部门
		          String rec_deparmentC = getValue(deparmentC).trim();
		          List<Department> ret_deparmentC = departmentManager.loadByKey("name", rec_deparmentC);
		          if(ret_deparmentC!=null&&ret_deparmentC.size()>0){
        			  cInfo.setDeparment(ret_deparmentC.get(0));
        		  }
		          
		          HSSFCell ciemdinghTime = hssfRow.getCell((short)11);//签订日期
		          if(ciemdinghTime == null||"".equals(getValue(ciemdinghTime).trim())){//如果签订日期为空  ,那么以合同编码截取前8个字段当做日期
		        	  if(ret_code.substring(0,2).equals("20")){
		        		  String cData = ret_code.substring(0,4)+"-"+ret_code.substring(4,6)+"-"+ret_code.substring(6,8);
			        	  Date ret_ciemdinghTime = null;
							try {
								ret_ciemdinghTime = sdf.parse(cData);
							} catch (ParseException e) {
								e.printStackTrace();
							}
				          cInfo.setCiemdinghTime(ret_ciemdinghTime); 
		        	  }else{
		        		  String cData = ret_code.substring(2,6)+"-"+ret_code.substring(6,8)+"-"+ret_code.substring(8,10);
			        	  Date ret_ciemdinghTime = null;
							try {
								ret_ciemdinghTime = sdf.parse(cData);
							} catch (ParseException e) {
								e.printStackTrace();
							}
				          cInfo.setCiemdinghTime(ret_ciemdinghTime); 
		        	  }
		        	  
		        	 
		          }else{
		        	  String rec_ciemdinghTime =getValue(ciemdinghTime).trim();
			          Date ret_ciemdinghTime = null;
						try {
							ret_ciemdinghTime = sdf.parse(rec_ciemdinghTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
			          cInfo.setCiemdinghTime(ret_ciemdinghTime); 
		          }
		          
		          
		          HSSFCell startTime = hssfRow.getCell((short)12);//开始日期
		          if(startTime == null||"".equals(getValue(startTime).trim())){//日期为空  ,那么以合同编码截取前8个字段当做日期
		        	  if(ret_code.substring(0,2).equals("20")){
		        		  String cData = ret_code.substring(0,4)+"-"+ret_code.substring(4,6)+"-"+ret_code.substring(6,8);
			        	  Date ret_ciemdinghTime = null;
							try {
								ret_ciemdinghTime = sdf.parse(cData);
							} catch (ParseException e) {
								e.printStackTrace();
							}
				          cInfo.setStartTime(ret_ciemdinghTime); 
		        	  }else{
		        		  String cData = ret_code.substring(2,6)+"-"+ret_code.substring(6,8)+"-"+ret_code.substring(8,10);
			        	  Date ret_ciemdinghTime = null;
							try {
								ret_ciemdinghTime = sdf.parse(cData);
							} catch (ParseException e) {
								e.printStackTrace();
							}
				          cInfo.setStartTime(ret_ciemdinghTime); 
		        	  }
		        	  
		          }else{
		        	  String rec_startTime =getValue(startTime).trim();
			          Date ret_startTime = null;
						try {
							ret_startTime = sdf.parse(rec_startTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
			          cInfo.setStartTime(ret_startTime);
		          }
		          
		          HSSFCell endTime = hssfRow.getCell((short)13);//终止日期
		          if(endTime == null||"".equals(getValue(endTime).trim())){
		        	  cInfo.setEndTime(null); 
		          }else{
		        	  String rec_endTime =getValue(endTime).trim();
			          Date ret_endTime = null;
						try {
							ret_endTime = sdf.parse(rec_endTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
			          cInfo.setEndTime(ret_endTime); 
		          }
		          
		          
		          HSSFCell contractType = hssfRow.getCell((short)14);//合同类型
		          if(contractType!=null){
		        	  String ret_contractType =getValue(contractType).trim();
			          List<CodeValue> code_contractType =codeValueManager.loadByKey("name", ret_contractType);
			          if(code_contractType!=null&&code_contractType.size()>0){
	        			  cInfo.setContractType(code_contractType.get(0));
	        		  }
		          }
		          
		          HSSFCell contractMoney = hssfRow.getCell((short)15);//合同金额
		          if(contractMoney == null||"".equals(getValue(contractMoney).trim())){
		        	  cInfo.setContractMoney(0);
		          }else{
		        	  String rec_contractMoney = getValue(contractMoney).trim();
			          Double ret_contractMoney = Double.parseDouble(rec_contractMoney);
			          cInfo.setContractMoney(ret_contractMoney);
		          }
		          
		          
		          HSSFCell paidMoney = hssfRow.getCell((short)16);//已收金额
		          if(paidMoney == null||"".equals(getValue(paidMoney).trim())){
		        	  cInfo.setPaidMoney(0);
		          }else{
		        	  String rec_paidMoney = getValue(paidMoney).trim();
			          Double ret_paidMoney = Double.parseDouble(rec_paidMoney);
			          cInfo.setPaidMoney(ret_paidMoney);
		          }
		          
		          HSSFCell moneyType = hssfRow.getCell((short)17);//币种
		          if(moneyType == null||"".equals(getValue(moneyType).trim())){
		        	  List<CodeValue> code_moneyType =codeValueManager.loadByKey("name", "人民币");
			          if(code_moneyType!=null&&code_moneyType.size()>0){
	        			  cInfo.setMoneyType(code_moneyType.get(0));
	        		  }
		          }else{
		        	  String ret_moneyType =getValue(moneyType).trim();
			          List<CodeValue> code_moneyType =codeValueManager.loadByKey("name", ret_moneyType);
			          if(code_moneyType!=null&&code_moneyType.size()>0){
	        			  cInfo.setMoneyType(code_moneyType.get(0));
	        		  }
		          }
		          
		          HSSFCell payType = hssfRow.getCell((short)18);//收款类型
		          if(payType == null||"".equals(getValue(payType).trim())){
		        	  List<CodeValue> code_payType =codeValueManager.loadByKey("name", "多次支付");
			          if(code_payType!=null&&code_payType.size()>0){
	        			  cInfo.setPayType(code_payType.get(0));
	        		  }
		          }else{
		        	  String ret_payType =getValue(payType).trim();
			          List<CodeValue> code_payType =codeValueManager.loadByKey("name", ret_payType);
			          if(code_payType!=null&&code_payType.size()>0){
	        			  cInfo.setPayType(code_payType.get(0));
	        		  }
		          }
		          
//		          HSSFCell state = hssfRow.getCell((short)16);//状态
		          
		        	  String ret_state ="执行中";
			          List<CodeValue> code_state =codeValueManager.loadByKey("name", ret_state);
			          if(code_state!=null&&code_state.size()>0){
	        			  cInfo.setState(code_state.get(0));
	        		  }
		          
		          
		          HSSFCell contractContent = hssfRow.getCell((short)19);//合同内容
		          if(contractContent == null||"".equals(getValue(contractContent).trim())){
		        	  cInfo.setContractContent("无"); 
		          }else{
		        	  String rec_contractContent = getValue(contractContent).trim();
			          cInfo.setContractContent(rec_contractContent); 
		          }
		          
		          HSSFCell remark = hssfRow.getCell((short)20);//备注
		          if(remark == null||"".equals(getValue(remark).trim())){
		        	  cInfo.setRemark("无");
		          }else{
		        	  String rec_remark = getValue(remark).trim();
			          cInfo.setRemark(rec_remark);
		          }
		          
		          HSSFCell twoCFourF = hssfRow.getCell((short)0);//两厂四方
		          if(twoCFourF == null||"".equals(getValue(twoCFourF).trim())){
//		        	  cInfo.setTwoCFourF("无");
		          }else{
		        	  String rec_twoCFourF = getValue(twoCFourF).trim();
			          cInfo.setTwoCFourF(rec_twoCFourF);
		          }
		          
		          HSSFCell receipt = hssfRow.getCell((short)1);//原件
		          if(receipt == null||"".equals(getValue(receipt).trim())){
//		        	  cInfo.setReceipt("无");
		          }else{
		        	  String rec_receipt = getValue(receipt).trim();
			          cInfo.setReceipt(rec_receipt);
		          }
		          
		          HSSFCell back = hssfRow.getCell((short)2);//返回
		          if(back == null||"".equals(getValue(back).trim())){
		        	  cInfo.setBack("无");
		          }else{
		        	  String rec_back = getValue(back).trim();
			          cInfo.setBack(rec_back);
		          }
		          
		          String isSaved ="1";  //提交 1是0否
		          cInfo.setIsSaved(isSaved);
		          
		          
		                   		          
		          this.contractManagementManager.storeContractManagement(cInfo);
		          
		          }
		          		             
		          
		              //产品明细
		        	  ProductList pl =new ProductList();
		        	  		        	  
		        	  List<ContractManagement> cM = contractManagementManager.loadByKey("code", cInfo.getCode());//根据合同编码获取相应的合同
		        	  pl.setContractManagement(cM.get(0));
		        	  
		        	  HSSFCell pModel = hssfRow.getCell((short)22);//产品型号
			          String rec_pModel = getValue(pModel).trim();
			          List<Products> pds = productsManager.loadByKey("model", rec_pModel);//根据产品型号获取对应产品信息
			          pl.setProduct(pds.get(0));
//		        	  Long pdId = productsManager.loadByKey("model", rec_pModel).get(0).getId();//获取产品ID
		        	  
		        	  HSSFCell count = hssfRow.getCell((short)23);//数量
			          String ret_count = getValue(count).trim();
			          int rec_count = Integer.parseInt(ret_count);
			          pl.setCount(rec_count);
			          
			          HSSFCell unit = hssfRow.getCell((short)24);//单位
			          String ret_unit = getValue(unit).trim();
			          List<CodeValue> code_unit =codeValueManager.loadByKey("name", ret_unit);
			          if(code_unit!=null&&code_unit.size()>0){
			        	  pl.setUnit(code_unit.get(0));
	        		  }
			          
			          HSSFCell unitPrice = hssfRow.getCell((short)25);//单价
			          String rec_unitPrice = getValue(unitPrice).trim();
			          Double ret_unitPrice = Double.parseDouble(rec_unitPrice);
			          pl.setUnitPrice(ret_unitPrice);
			          
			          HSSFCell totalPrice = hssfRow.getCell((short)26);//总价
			          String rec_totalPrice = getValue(totalPrice).trim();
			          Double totalPricex = Double.parseDouble(rec_totalPrice);
			          DecimalFormat format = new DecimalFormat("0.00");//保留小数点两位小数
			          Double ret_totalPrice = Double.parseDouble(format.format(totalPricex));
			          pl.setTotalPrice(ret_totalPrice);
			          Double discount = (ret_totalPrice/(double)rec_count)/ret_unitPrice*100;
			          Double discountx = Double.parseDouble(format.format(discount));
			          pl.setDiscount(discountx);//产品明细的百分比
			          
			          HSSFCell plannedDeliveryDate = hssfRow.getCell((short)27);//计划交付日期
			          if(plannedDeliveryDate == null||"".equals(getValue(plannedDeliveryDate).trim())){
			        	  //如果为空，则取合同签订日期加上两个月
			        	  Date cMDate = cM.get(0).getCiemdinghTime();//合同日期
			        	  System.out.println(cMDate);
			        	  System.out.println(new Date(cMDate.getTime()+(long)60*24*60*60*1000));
			        	  pl.setPlannedDeliveryDate(new Date(cMDate.getTime()+(long)60*24*60*60*1000));//产品明细的计划交付日期
			        	  rInfo.setPlanDate(new Date(cMDate.getTime()+(long)60*24*60*60*1000));//收款计划交付日期  
			          }else{
			        	  String rec_plannedDeliveryDate =getValue(plannedDeliveryDate).trim().replace(" ", "");
			        	  String data = rec_plannedDeliveryDate.substring(0,4)+"-"+rec_plannedDeliveryDate.substring(4,6)+"-"+rec_plannedDeliveryDate.substring(6,8);
				          Date ret_plannedDeliveryDate = null;
							try {
								ret_plannedDeliveryDate = sdf.parse(data);
							} catch (ParseException e) {
								e.printStackTrace();
							}
				          pl.setPlannedDeliveryDate(ret_plannedDeliveryDate);//产品明细的计划交付日期
				          rInfo.setPlanDate(ret_plannedDeliveryDate);//收款计划交付日期  
			          }
			          
			          HSSFCell qualityControl = hssfRow.getCell((short)28);//质检
			          if(qualityControl == null||"".equals(getValue(qualityControl).trim())){
			        	  List<CodeValue> code_qualityControl =codeValueManager.loadByKey("name", "待定");
				          if(code_qualityControl!=null&&code_qualityControl.size()>0){
				        	  pl.setQualityControl(code_qualityControl.get(0)); 
		        		  }
			          }else{
			        	  String ret_qualityControl = getValue(qualityControl).trim();
			        	  List<CodeValue> code_qualityControl =codeValueManager.loadByKey("name", ret_qualityControl);
				          if(code_qualityControl!=null&&code_qualityControl.size()>0){
				        	  pl.setQualityControl(code_qualityControl.get(0)); 
		        		  }
				          
			          }
			          
			          HSSFCell remarkPl = hssfRow.getCell((short)39);//备注
			          String rec_remarkPl = getValue(remarkPl).trim();
			          pl.setRemark(rec_remarkPl);
			          
			          //查询到产品型号对相应的价格进行更新
			          List<Products> lProducts = productsManager.loadByKey("model", rec_pModel);
			          if(lProducts!=null&&lProducts.size()>0){
			        	  lProducts.get(0).setSalePrice(ret_unitPrice);//修改产品价格为合同内部价格
			        	  
			          }
			          this.productsManager.storeProducts(lProducts.get(0));
			          //下面为收款计划
//			          HSSFCell chargMan = hssfRow.getCell((short)6);//收款计划负责人			          
//			          String rec_chargMan = getValue(chargMan).trim();
//			          List<PersonnelFiles> ret_chargMan = personnelFilesManager.loadByKey("name", rec_chargMan);
	        		  //取合同负责人  
			          rInfo.setChargMan(cM.get(0).getSaleman());//收款计划负责人
	        		  rInfo.setPayee(cM.get(0).getSaleman());//收款计划收款人
	        		  rInfo.setCustomerInfo(cM.get(0).getCustomerInfo());//从合同中获取客户信息给收款计划
	        		  
			          
			          HSSFCell deliveryedCount = hssfRow.getCell((short)30);//已交付数量
			          if(deliveryedCount == null||"".equals(getValue(deliveryedCount).trim())){
			        	  HSSFCell deliveryNum = hssfRow.getCell((short)31);//出库单号         
				          if(deliveryNum == null||"".equals(getValue(deliveryNum).trim())){
				        	  pl.setDeliveryedCount(0); 
				          }else{
				        	  String rei_deliveryNum = getValue(deliveryNum).trim().replace(" ", "");
				        	 String rec_deliveryNum = rei_deliveryNum.replace("；", ";").replace("—", "-");
				        	    
					          if(rec_deliveryNum.indexOf(";")==-1){
					        	  if(rec_deliveryNum.indexOf("-")==-1){
					        		  pl.setDeliveryedCount(rec_count);
					        	  }else{
					        		  String [] delisx = rec_deliveryNum.split("-");
						        	  int n = Integer.parseInt(delisx[1]);//获取数量
						        	  pl.setDeliveryedCount(n); 
					        	  }
					        	  
					          }else{
					        	  String [] delis = rec_deliveryNum.split(";");//[20150429132-1;,20150506145-1;...]
					        	  int num = 0;//发货数量
						        	  for(int i=0;i<delis.length;i++){
							        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
							        	  String n = delisx[1];//获取数量1+2+1+4
							        	  num+= Integer.parseInt(n);								          
							          } 
						        	  pl.setDeliveryedCount(num); 
					          }
				          }
			          }else{
			        	  String rec_deliveryedCount = getValue(deliveryedCount).trim();
				          int ret_deliveryedCount = Integer.parseInt(rec_deliveryedCount);
				          pl.setDeliveryedCount(ret_deliveryedCount);  
			          }
			                   
			          this.productListManager.storeProductList(pl);
			          
			          //维护更新合同内部总价
			          //1.取出当前循环合同的总价格
			          ContractManagement cMhInfo = cM.get(0);
			          //把取出的合同价格加上当前循环的产品明细价格为现阶段的总价
			          cMhInfo.setContractMoney(cMhInfo.getContractMoney()+ret_totalPrice);  
			          //更新
			          this.contractManagementManager.storeContractManagement(cMhInfo);
			          
			          //收款计划
			          Long cmId = contractManagementManager.loadByKey("code", cInfo.getCode()).get(0).getId();//根据合同编码获取合同ID
			          List<ReturnPlan> rPlanList = this.returnPlanManager.loadByKey("contractManagement", cmId);//根据合同ID获取收款计划列表
			          if(rPlanList!=null&&rPlanList.size()>0){//如果列表存在
			        	  ReturnPlan rxInfo = rPlanList.get(0);//取出列表里面的总价
			        	  rxInfo.setSum(rxInfo.getSum()+ret_totalPrice);//收款计划的总价=使用列表中的总价+现在表格里面的总价  
			        	  this.returnPlanManager.storeReturnPlan(rxInfo);
			          }else{
			        	  List<ContractManagement> rCM = this.contractManagementManager.loadByKey("code", cInfo.getCode());
			        	  if(rCM!=null&&rCM.size()>0){
			        		  rInfo.setContractManagement(rCM.get(0));//相关合同
			        	  }
			        	  HSSFCell rcustomerInfo = hssfRow.getCell((short)5);//相关客户名称
				          String rec_rcustomerInfo = getValue(rcustomerInfo).trim();
				          List<CustomerInfo> ret_rcustomerInfo = customerInfoManager.loadByKey("name", rec_rcustomerInfo);
				          if(ret_rcustomerInfo!=null&&ret_rcustomerInfo.size()>0){
		        			  rInfo.setCustomerInfo(ret_rcustomerInfo.get(0));//收款计划中的客户信息
		        			  System.out.println(ret_rcustomerInfo.get(0));
		        		  }
				          HSSFCell rlinkman = hssfRow.getCell((short)6);//相关联系人
				          if(rlinkman == null||"".equals(getValue(rlinkman).trim())){
				        	  List<ContactArchives> ret_rlinkman = contactArchivesManager.loadByKey("name", ret_rcustomerInfo.get(0).getKeyContacter());
					          if(ret_rlinkman!=null&&ret_rlinkman.size()>0){
			        			  rInfo.setContactArchives(ret_rlinkman.get(0));
			        			  if(ret_rlinkman.get(0).getPhone()!=null){
			        				  rInfo.setPhone(ret_rlinkman.get(0).getPhone());//设置联系电话 
			        			  }else{
			        				 rInfo.setPhone("000000");//如果没有联系电话设定000000 
			        			  }
			        			  
			        		  }
				          }else{
				        	  String rec_rlinkman = getValue(rlinkman).trim();
					          List<ContactArchives> ret_rlinkman = contactArchivesManager.loadByKey("name", rec_rlinkman);
					          if(ret_rlinkman!=null&&ret_rlinkman.size()>0){
			        			  rInfo.setContactArchives(ret_rlinkman.get(0));
			        			  if(ret_rlinkman.get(0).getPhone()!=null){
			        				  rInfo.setPhone(ret_rlinkman.get(0).getPhone());//设置联系电话 
			        			  }else{
			        				 rInfo.setPhone("000000");//如果没有联系电话设定000000 
			        			  }
			        			  
			        		  }
				          }
				         
			        	  rInfo.setSum(ret_totalPrice);//收款计划的总价,来自产品明细里面的产品总价
			        	  String batch = "1";//收款批次
				          List<CodeValue> code_batch =codeValueManager.loadByKey("name", batch);
				          if(code_batch!=null&&code_batch.size()>0){
				        	  rInfo.setBatch(code_batch.get(0));
		        		  }
				          String payment = "转账";//收款批次
				          List<CodeValue> code_payment =codeValueManager.loadByKey("name", payment);
				          if(code_payment!=null&&code_payment.size()>0){
				        	  rInfo.setPayment(code_payment.get(0));
		        		  }
				          String notOrIs = "0";//是否提醒
				          rInfo.setNotOrIs(notOrIs);
//				          Double factSum = ;//已收金额

//				          Date paytime = ;//收款日期
//				          int percentt = ;//完成百分比
				          String isBill = "0";//是否开票?是否到款
				          rInfo.setIsBill(isBill);
				          String billingOrNot = "0";//是否开票
				          rInfo.setBillingOrNot(billingOrNot);
				          String isOrNot = "1";//是否到款
				          rInfo.setIsOrNot(isOrNot);
				          String planState = "待收款";//计划状态
				          List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
				          if(code_planState!=null&&code_planState.size()>0){
				        	  rInfo.setPlanState(code_planState.get(0));
		        		  }
				          this.returnPlanManager.storeReturnPlan(rInfo);
			          }
			          
			          
			          //发货单
			          /*
			           *    20150429132-1；
							20150506145-2；
							20150725257-1；
							20160106002-4；
							20160715282-1；
							20170426130-1；
			           */
			          HSSFCell deliveryNum = hssfRow.getCell((short)31);//出库单号
			          HSSFCell deliveryDate = hssfRow.getCell((short)32);//交付日期
//			          int num = 0;//发货数量
			          if(deliveryNum == null||"".equals(getValue(deliveryNum).trim())){
//			        	  ivInfo.setDeliveryNum("无");//判断出库单号如果没有则设置无
			          }else{
			        	  String rei_deliveryNum = getValue(deliveryNum).trim().replace(" ", "");
			        	  String rec_deliveryNum = rei_deliveryNum.replace("；", ";").replace("—", "-");
				          if(rec_deliveryNum.indexOf(";")==-1){
				        	  Invoices ivInfo = new Invoices();//发货单
				        	  if(rec_deliveryNum.indexOf("-")==-1){
				        		  ivInfo.setDeliveryCount(rec_count);
				        	  }else{
				        		  ivInfo.setDeliveryCount(Integer.parseInt(rec_deliveryNum.split("-")[1]));
				        	  }
				        	  
				        	  ivInfo.setDeliveryNum(rec_deliveryNum);//判断出库单号如果只有一条则设置
				        	  
				        	  if(rec_deliveryNum.substring(0,2).equals("20")){//判断军民品合同，如果是20开头则为军品
				        		  String datas = rec_deliveryNum.split("-")[0];
					        	  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
						          Date ret_data = null;
									try {
										ret_data = sdf.parse(data);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									ivInfo.setDeliveryDate(ret_data);
				        	  }else{
				        		  if(deliveryDate == null||"".equals(getValue(deliveryDate).trim())){//民品判断是否存在交付日期
				        			  String datas = rec_deliveryNum.split("-")[0];
						        	  String data = datas.substring(2,6)+"-"+datas.substring(6,8)+"-"+datas.substring(8,10);
							          Date ret_data = null;
										try {
											ret_data = sdf.parse(data);
										} catch (ParseException e) {
											e.printStackTrace();
										}
										ivInfo.setDeliveryDate(ret_data);
				        		  }else{
				        			  String data = getValue(deliveryDate).trim();
							          Date ret_data = null;
										try {
											ret_data = sdf.parse(data);
										} catch (ParseException e) {
											e.printStackTrace();
										}
										ivInfo.setDeliveryDate(ret_data);
				        			  
				        		  }
				        		  
				        	  }
	
								ivInfo.setDeliveryPerson(cM.get(0).getSaleman());
								ivInfo.setContractManagement(cM.get(0));
								ivInfo.setProductList(pl);
								this.invoicesManager.storeInvoices(ivInfo);
				          }else{
				        	  String [] delis = rec_deliveryNum.split(";");//[20150429132-1,20150506145-1...]
				        	  String [] datads = null;
				        	  if(deliveryDate == null||"".equals(getValue(deliveryDate).trim())){
				        		  
				        	  }else{
				        		  datads = getValue(deliveryDate).trim().replace(" ", "").replace("；", ";").split(";");
				        	  }
				        	  
				        	      int ivNum = 0;//累计总数量
					        	  for(int i=0;i<delis.length;i++){
					        		  Invoices ivInfo = new Invoices();//发货单
						        	  String j = delis[i].trim();//获取发货单号，多条发货单，循环设置
						        	  ivInfo.setDeliveryNum(j);	  
						        	  ivInfo.setDeliveryCount(Integer.parseInt(j.split("-")[1]));
						        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
						        	  String datas = delisx[0].trim();//获取日期
						        	  int n = Integer.parseInt(j.split("-")[1]);//获取数量1+2+1+4
						        	  ivNum+= n;
						        	  
						        	  if(datas.substring(0,2).equals("20")){//判断军民品合同，如果是20开头则为军品
						        		  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
								          Date ret_data = null;
											try {
												ret_data = sdf.parse(data);
											} catch (ParseException e) {
												e.printStackTrace();
											}
											ivInfo.setDeliveryDate(ret_data);
						        	  }else{
						        		  if(deliveryDate == null||"".equals(getValue(deliveryDate).trim())){
						        			  String data = datas.substring(2,6)+"-"+datas.substring(6,8)+"-"+datas.substring(8,10);
									          Date ret_data = null;
												try {
													ret_data = sdf.parse(data);
												} catch (ParseException e) {
													e.printStackTrace();
												}
												ivInfo.setDeliveryDate(ret_data);
						        		  }else{
						        			  String data =  datads[i].trim();
						        			  Date ret_data = null;
												try {
													ret_data = sdf.parse(data);
												} catch (ParseException e) {
													e.printStackTrace();
												}
												ivInfo.setDeliveryDate(ret_data);
						        		  }
						        		  
						        	  }
						        	  
										
										
										ivInfo.setDeliveryPerson(cM.get(0).getSaleman());
										ivInfo.setContractManagement(cM.get(0));
										ivInfo.setProductList(pl);
										this.invoicesManager.storeInvoices(ivInfo);
										
						          } 
				          }
			          }
			          
			          //收款单
			          
			          
			          List<ReturnPlan> fPlanList = this.returnPlanManager.loadByKey("contractManagement", cmId);//根据合同ID获取收款计划列表
			          HSSFCell fInvoiceCode = hssfRow.getCell((short)34);//(收款日期)发票号
			          
			          if(fInvoiceCode == null||"".equals(getValue(fInvoiceCode).trim())){
			        	  ContractManagement cMlInfo = cM.get(0);//获取当前合同信息
			        	  ReturnPlan rfInfo = fPlanList.get(0);//收款计划
			        	  double contractMoney = cMlInfo.getContractMoney();//合同金额
			        	  double paidMoney = cMlInfo.getPaidMoney();//已收金额
			        	  
			        	  //判断发货数量与要数量是否相等，则是合同属性执行中
			        	  List<ProductList>  productListHl = productListManager.loadByKey("contractManagement.id", cmId);
			        	  
			        	  boolean deliveryed = true;
			        	  if(productListHl!=null&&productListHl.size()>0){
			        		  for(int i=0;i<productListHl.size();i++){
			        			  int h = productListHl.get(i).getDeliveryedCount();
			        			  int l = productListHl.get(i).getCount();
			        			  if(l!=h){
			        				  deliveryed = false;
			        			  }
			        		  }
			        	  }

			        	  
			        	  if(deliveryed&&contractMoney<=paidMoney){
			        		//设置合同状态
//					          String state = "执行中";
//					          List<CodeValue> code_state =codeValueManager.loadByKey("name", state);
//					          if(code_state!=null&&code_state.size()>0){
//					        	  cMlInfo.setState(code_state.get(0));
//			        		  }
//					          String planState = "执行中";//完成状态100则收款计划设置已完成
//				        	  List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
//					          if(code_planState!=null&&code_planState.size()>0){
//					        	  rfInfo.setPlanState(code_planState.get(0));
//			        		  }
//					          String islSaved ="1";  //提交 1是0否 设置合同中的提交
//					          cMlInfo.setIsSaved(islSaved);
//					          
//					          int percentt = (int) ((paidMoney/contractMoney)*100);
//					          rfInfo.setPercentt(percentt);
//       		          
//					          this.contractManagementManager.storeContractManagement(cMlInfo);//更新合同数据
//					          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
			        	  }else{
			        		//设置合同状态
					          String state = "执行中";
					          List<CodeValue> code_state =codeValueManager.loadByKey("name", state);
					          if(code_state!=null&&code_state.size()>0){
					        	  cMlInfo.setState(code_state.get(0));
			        		  }
					          String planState = "执行中";//完成状态100则收款计划设置已完成
				        	  List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
					          if(code_planState!=null&&code_planState.size()>0){
					        	  rfInfo.setPlanState(code_planState.get(0));
			        		  }
					          String islSaved ="1";  //提交 1是0否 设置合同中的提交
					          cMlInfo.setIsSaved(islSaved);
					          
					          int percentt = (int) ((paidMoney/contractMoney)*100);
					          rfInfo.setPercentt(percentt);
       		          
					          this.contractManagementManager.storeContractManagement(cMlInfo);//更新合同数据
					          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
			        	  }
			        	  //更新收款单中的计划金额和未收款金额
			        	  List<FinancialManagement> fcMlist = this.financialManagementManager.loadByKey("contractManagement.id", cMhInfo.getId());
				          if(fcMlist!=null&&fcMlist.size()>0){
				        	  for(int i=0;i<fcMlist.size();i++){
				        		  //计划应收金额更新
				        		  fcMlist.get(i).setSumReceivable(contractMoney);//更新为合同金额
				        		  //取出当前循环中的实收金额
				        		  Double trueSumHl = fcMlist.get(i).getTrueSum();
				        		  //取出当前循环中的已收金额
				        		  Double totalSumHl = fcMlist.get(i).getTotalSum();
				        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
				        		  fcMlist.get(i).setWithoutGotSum(contractMoney-(trueSumHl+totalSumHl));
				        		  //更新当前信息
				        		  this.financialManagementManager.storeFinancialManagement(fcMlist.get(i));
				        	  }
				          }
			        	  //更新开票单中的计划开票金额和未开票金额
				          List<BillingRecord> billinglist = this.billingRecordManager.loadByKey("contractManagement.id", cMhInfo.getId());
				          if(billinglist!=null&&billinglist.size()>0){
				        	  for(int i=0;i<billinglist.size();i++){
				        		  //计划应收金额更新
				        		  billinglist.get(i).setPlanSum(contractMoney);//更新为合同金额
				        		  //取出当前循环中的实收金额
				        		  Double sumHl = billinglist.get(i).getSum();
				        		  //取出当前循环中的已收金额
				        		  Double hasBillSumHl = billinglist.get(i).getHasBillSum();
				        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
				        		  billinglist.get(i).setRestSum(contractMoney-(sumHl+hasBillSumHl));
				        		  //更新当前信息
				        		  this.billingRecordManager.storeBillingRecord(billinglist.get(i));
				        	  }
				          }
			        	  
			          }else{
			        	  String rei_fInvoiceCode = getValue(fInvoiceCode).trim().replace(" ", "");
			        	  String rec_fInvoiceCode = rei_fInvoiceCode.replace("；", ";").replace("—", "-");
			        	  
			        	  
			        	  
				          if(rec_fInvoiceCode.indexOf(";")==-1){
				        	  //收款单
				        	  FinancialManagement fInfo = new FinancialManagement();//收款单
				        	  fInfo.setInvoiceCode(rec_fInvoiceCode);//判断发票编码如果只有一条则设置
				        	  String datas = rec_fInvoiceCode.split("-")[0];
				        	  
				        		  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
						          Date ret_data = null;
									try {
										ret_data = sdf.parse(data);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									fInfo.setCollectionDate(ret_data);		
								
								 String codeF = autoCompleteCode();//收款单编码
						          fInfo.setCode(codeF);
						          
						          fInfo.setContractManagement(cMhInfo);//合同
						          
						          fInfo.setIsSaved("1");//是否提交
						          
						          fInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
						          
						          String collectionType = "收款";//收款类型
						          List<CodeValue> code_collectionType =codeValueManager.loadByKey("name", collectionType);
						          if(code_collectionType!=null&&code_collectionType.size()>0){
						        	  fInfo.setCollectionType(code_collectionType.get(0));
				        		  }
						          
						          String fPayment = "转账";//付款方式
						          List<CodeValue> code_fPayment =codeValueManager.loadByKey("name", fPayment);
						          if(code_fPayment!=null&&code_fPayment.size()>0){
						        	  fInfo.setPayment(code_fPayment.get(0));
				        		  }
						          
						          String fBatch = "1";//收款批次
						          List<CodeValue> code_fBatch =codeValueManager.loadByKey("name", fBatch);
						          if(code_fBatch!=null&&code_fBatch.size()>0){
						        	  fInfo.setBatch(code_fBatch.get(0));
				        		  }
						          Double sumReceivable = fPlanList.get(0).getSum();//应收金额等于收款计划总金额
						          fInfo.setSumReceivable(sumReceivable);
						          
						          Double totalSum = fPlanList.get(0).getFactSum();//已收金额等于收款计划中的付款金额
						          fInfo.setTotalSum(totalSum);
						          
						          Double trueSum =0.0D;//实收金额
						          if(rec_fInvoiceCode.split("-").length!=3){
						        	  trueSum = ret_totalPrice;
						          }else{  
						        	  trueSum = Double.parseDouble(rec_fInvoiceCode.split("-")[2].trim());
						          }
						          fInfo.setTrueSum(trueSum);
						          //计划总金额-前期已收-本次实收=累积未收金额
						          Double withoutGotSum = sumReceivable-(totalSum+trueSum);//未收金额
//						          if(withoutGotSum>0){
						        	  fInfo.setWithoutGotSum(withoutGotSum);
//						          }else{
//						        	  fInfo.setWithoutGotSum(0.0);
//						          }
						          
						          
						          //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
//						          ReturnPlan rfInfo = fPlanList.get(0);
//						          rfInfo.setFactSum(totalSum+trueSum);
//						          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
						          
						          fInfo.setPayee(cMhInfo.getSaleman());//收款人取合同负责人
						          
						          fInfo.setSaleman(cMhInfo.getSaleman());//收款单业务员
						          
						          fInfo.setInvoice("0");//发票状态
						          
						          fInfo.setDept(cMhInfo.getDeparment());//部门，获取合同内的部门
						          this.financialManagementManager.storeFinancialManagement(fInfo);
						          
						          List<FinancialManagement> fcMlist = this.financialManagementManager.loadByKey("contractManagement.id", cMhInfo.getId());
						          if(fcMlist!=null&&fcMlist.size()>0){
						        	  for(int i=0;i<fcMlist.size();i++){
						        		  //计划应收金额更新
						        		  fcMlist.get(i).setSumReceivable(sumReceivable);
						        		  //取出当前循环中的实收金额
						        		  Double trueSumHl = fcMlist.get(i).getTrueSum();
						        		  //取出当前循环中的已收金额
						        		  Double totalSumHl = fcMlist.get(i).getTotalSum();
						        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
						        		  fcMlist.get(i).setWithoutGotSum(sumReceivable-(trueSumHl+totalSumHl));
						        		  //更新当前信息
						        		  this.financialManagementManager.storeFinancialManagement(fcMlist.get(i));
						        	  }
						          }
						          
						          
						          //开票记录
						          BillingRecord bInfo = new BillingRecord();//开票记录
					        	  bInfo.setCode(rec_fInvoiceCode);//发票编码
					        	  bInfo.setIsSaved("1");//是否提交1提交0否
					        	  String dataf = rec_fInvoiceCode.split("-")[0];
					        	  String datax = dataf.substring(0,4)+"-"+dataf.substring(4,6)+"-"+dataf.substring(6,8);
						          Date ret_datx = null;
									try {
										ret_datx = sdf.parse(datax);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									bInfo.setBillingTime(ret_datx);//开票日期
									
									String myCode = autoCompleteCodeb();//开票单编码
									bInfo.setMyCode(myCode);
									
									bInfo.setContractManagement(cMhInfo);//合同
									
									bInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
									
									bInfo.setContactArchives(cMhInfo.getLinkman());//联系人
									
									String bBatch = "1";//收款批次
							          List<CodeValue> code_bBatch =codeValueManager.loadByKey("name", bBatch);
							          if(code_bBatch!=null&&code_bBatch.size()>0){
							        	  bInfo.setBatch(code_bBatch.get(0));
					        		  }
							        
							        Double planSum = fPlanList.get(0).getSum();//计划开票金额
							        bInfo.setPlanSum(planSum);
							        
							        Double hasBillSum = fPlanList.get(0).getFactSum();//已开票金额等于计划中的实际付款金额
							        bInfo.setHasBillSum(hasBillSum);
							        
							        Double sum =0.0D;//本次开票金额
							          if(rec_fInvoiceCode.split("-").length!=3){
							        	  sum = ret_totalPrice;//为本条记录产品总价
							          }else{  
							        	  sum = Double.parseDouble(rec_fInvoiceCode.split("-")[2].trim());
							          }
							          
							          bInfo.setSum(sum);//本次开票金额
							          
							        //计划总金额-前期已收-本次实收=剩下未开开票金额
							          Double restSumz = planSum-(hasBillSum+sum);//未收金额
//							          Double restSum = 0.0D;
//							          if(restSumz>0){
//							        	  restSum = restSumz;
							        	  bInfo.setRestSum(restSumz);
//							          }else{
//							        	  bInfo.setRestSum(0.0);
//							          }
							          
							          
							          
							          ContractManagement cMlInfo = cM.get(0);//获取当前合同信息
							          double paidlMoney =totalSum+trueSum;//合同的已收金额
//							          if(restSum<0){
//							        	  cMlInfo.setPaidMoney(planSum);
//							          }else{
							        	  cMlInfo.setPaidMoney(paidlMoney); 
//							          }
							          
							          
							          //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
							          ReturnPlan rfInfo = fPlanList.get(0);
							          rfInfo.setFactSum(totalSum+trueSum);
							          //收款计划中的完成百分比
							          int percentt = (int) (((totalSum+trueSum)/planSum)*100);
							          rfInfo.setPercentt(percentt);
							        //判断发货数量与要数量是否相等，则是合同属性执行中
						        	  List<ProductList>  productListHl = productListManager.loadByKey("contractManagement.id", cmId);
						        	  
						        	  boolean deliveryedl = true;
						        	  if(productListHl!=null&&productListHl.size()>0){
						        		  for(int i=0;i<productListHl.size();i++){
						        			  int h = productListHl.get(i).getDeliveryedCount();
						        			  int l = productListHl.get(i).getCount();
						        			  if(l!=h){
						        				  deliveryedl = false;
						        			  }
						        		  }
						        	  }
							          //收款计划中的计划状态
							          if(percentt>=100&&deliveryedl){
							        	  //设置收款计划状态
							        	  String planState = "已完成";//完成状态100则收款计划设置已完成
							        	  List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
								          if(code_planState!=null&&code_planState.size()>0){
								        	  rfInfo.setPlanState(code_planState.get(0));
						        		  }
								          String isBill = "1";//是否开票?是否到款
								          rfInfo.setIsBill(isBill);
								          String billingOrNot = "1";//是否开票
								          rfInfo.setBillingOrNot(billingOrNot);
								          String isOrNot = "0";//是否到款
								          rfInfo.setIsOrNot(isOrNot);
								          
								          //设置合同状态
								          String state = "已结束";
								          List<CodeValue> code_state =codeValueManager.loadByKey("name", state);
								          if(code_state!=null&&code_state.size()>0){
								        	  cMlInfo.setState(code_state.get(0));
						        		  }
								          
							          }
							          String islSaved ="1";  //提交 1是0否 设置合同中的提交
							          cMlInfo.setIsSaved(islSaved);
							          
							          
							          
							          List<ProjectInfo> ret_projectl = projectInfoManager.loadByKey("name", cMlInfo.getProject().getName());
							          if(ret_projectl!=null&&ret_projectl.size()>0){
							        	  String [] key={"parentCV.name","name"};
					            		  String [] value={"项目状态","付费"};
								          List<CodeValue> code_statepl =codeValueManager.loadByKeyArray(key, value);
								          if(code_statepl!=null&&code_statepl.size()>0){
								        	  ret_projectl.get(0).setState(code_statepl.get(0));//项目状态
								        	  ret_projectl.get(0).setIsSaved("1");//设置提交状态
								          }
							        	  
					        		  }
							          
							          this.projectInfoManager.storeProjectInfo(ret_projectl.get(0));//更新项目
							          this.contractManagementManager.storeContractManagement(cMlInfo);//更新合同数据
							          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
							          
							         
							          
							        
							         bInfo.setPayee(cMhInfo.getSaleman());//开票人
							         
							         bInfo.setIsPay("0");//是否付款0是1否
							         
							         this.billingRecordManager.storeBillingRecord(bInfo);
							         
							         
							         List<BillingRecord> billinglist = this.billingRecordManager.loadByKey("contractManagement.id", cMhInfo.getId());
							          if(billinglist!=null&&billinglist.size()>0){
							        	  for(int i=0;i<billinglist.size();i++){
							        		  //计划应收金额更新
							        		  billinglist.get(i).setPlanSum(planSum);
							        		  //取出当前循环中的实收金额
							        		  Double sumHl = billinglist.get(i).getSum();
							        		  //取出当前循环中的已收金额
							        		  Double hasBillSumHl = billinglist.get(i).getHasBillSum();
							        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
							        		  billinglist.get(i).setRestSum(planSum-(sumHl+hasBillSumHl));
							        		  //更新当前信息
							        		  this.billingRecordManager.storeBillingRecord(billinglist.get(i));
							        	  }
							          }
									
						          
				          }else{
				        	  String [] delis = rec_fInvoiceCode.split(";");//[20150429132-1,20150506145-1...]
				        	  for(int i=0;i<delis.length;i++){
				        		  //收款单
				        		  FinancialManagement fInfo = new FinancialManagement();//收款单
					        	  String j = delis[i].trim();//获取发货单号，多条发货单，循环设置
					        	  fInfo.setInvoiceCode(j);//设置发票编码
					        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
					        	  String datas = delisx[0].trim();//获取日期
//					        	  String n = delisx[1];//获取数量1+2+1+4
//					        	  num+= Integer.parseInt(n);
					        	  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
						          Date ret_data = null;
									try {
										ret_data = sdf.parse(data);
									} catch (ParseException e) {
										e.printStackTrace();
									}
									  fInfo.setCollectionDate(ret_data);
									
									  String codeF = autoCompleteCode();//收款单编码
							          fInfo.setCode(codeF);
							          
							          fInfo.setIsSaved("1");//是否提交
							          
							          fInfo.setContractManagement(cMhInfo);//合同
							          
							          fInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
							          
							          String collectionType = "收款";//收款类型
							          List<CodeValue> code_collectionType =codeValueManager.loadByKey("name", collectionType);
							          if(code_collectionType!=null&&code_collectionType.size()>0){
							        	  fInfo.setCollectionType(code_collectionType.get(0));
					        		  }
							          
							          String fPayment = "转账";//付款方式
							          List<CodeValue> code_fPayment =codeValueManager.loadByKey("name", fPayment);
							          if(code_fPayment!=null&&code_fPayment.size()>0){
							        	  fInfo.setPayment(code_fPayment.get(0));
					        		  }
							          
							          String fBatch = "1";//收款批次
							          List<CodeValue> code_fBatch =codeValueManager.loadByKey("name", fBatch);
							          if(code_fBatch!=null&&code_fBatch.size()>0){
							        	  fInfo.setBatch(code_fBatch.get(0));
					        		  }
							          
							          Double sumReceivable = fPlanList.get(0).getSum();//应收金额等于收款计划总金额
							          fInfo.setSumReceivable(sumReceivable);
							          
							          Double totalSum = fPlanList.get(0).getFactSum();//已收金额等于收款计划中的付款金额
							          fInfo.setTotalSum(totalSum);
							          
							          Double trueSum =0.0D;//实收金额
							          if(delisx.length!=3){
							        	  trueSum = ret_totalPrice;
							          }else{  
							        	  trueSum = Double.parseDouble(delisx[2].trim());
							          }
							          fInfo.setTrueSum(trueSum);
							          
							        //计划总金额-前期已收-本次实收=累积未收金额
							          Double withoutGotSum = sumReceivable-(totalSum+trueSum);//未收金额
//							          if(withoutGotSum>0){
							        	  fInfo.setWithoutGotSum(withoutGotSum);
//							          }else{
//							        	  fInfo.setWithoutGotSum(0.0);
//							          }
							          
							          
							          //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
//							          ReturnPlan rfInfo = fPlanList.get(0);
//							          rfInfo.setFactSum(totalSum+trueSum);
//							          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
							          
							          fInfo.setPayee(cMhInfo.getSaleman());//收款人取合同负责人
							          
							          fInfo.setSaleman(cMhInfo.getSaleman());//收款单业务员
							          
							          fInfo.setInvoice("0");//发票状态
							          
							          fInfo.setDept(cMhInfo.getDeparment());//部门，获取合同内的部门
									
							          this.financialManagementManager.storeFinancialManagement(fInfo);
							          
							          List<FinancialManagement> fcMlist = this.financialManagementManager.loadByKey("contractManagement.id", cMhInfo.getId());
							          if(fcMlist!=null&&fcMlist.size()>0){
							        	  for(int k=0;k<fcMlist.size();k++){
							        		  //计划应收金额更新
							        		  fcMlist.get(k).setSumReceivable(sumReceivable);
							        		  //取出当前循环中的实收金额
							        		  Double trueSumHl = fcMlist.get(k).getTrueSum();
							        		  //取出当前循环中的已收金额
							        		  Double totalSumHl = fcMlist.get(k).getTotalSum();
							        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
							        		  fcMlist.get(k).setWithoutGotSum(sumReceivable-(trueSumHl+totalSumHl));
							        		  //更新当前信息
							        		  this.financialManagementManager.storeFinancialManagement(fcMlist.get(k));
							        	  }
							          }
							          
							          //开票记录
							          BillingRecord bInfo = new BillingRecord();//开票记录
					        		  String f = delis[i].trim();//获取发货单号，多条发货单，循环设置
						        	  bInfo.setCode(f);//设置发票编码
						        	  bInfo.setIsSaved("1");//是否提交1提交0否
						        	  String [] delisf = delis[i].split("-");//[20150429132,1],分开日期与发货价数
						        	  String dataf = delisf[0].trim();//获取日期
						        	  
						        	  String datafz = dataf.substring(0,4)+"-"+dataf.substring(4,6)+"-"+dataf.substring(6,8);
							          Date ret_dataf = null;
										try {
											ret_dataf = sdf.parse(datafz);
										} catch (ParseException e) {
											e.printStackTrace();
										}
										bInfo.setBillingTime(ret_dataf);//开票日期
										
										String myCode = autoCompleteCodeb();//开票单编码
										bInfo.setMyCode(myCode);
										
										bInfo.setContractManagement(cMhInfo);//合同
										
										bInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
										
										bInfo.setContactArchives(cMhInfo.getLinkman());//联系人
										
										String bBatch = "1";//收款批次
								          List<CodeValue> code_bBatch =codeValueManager.loadByKey("name", bBatch);
								          if(code_bBatch!=null&&code_bBatch.size()>0){
								        	  bInfo.setBatch(code_bBatch.get(0));
						        		  }
								        
								        Double planSum = fPlanList.get(0).getSum();//计划开票金额
								        bInfo.setPlanSum(planSum);
								        
								        Double hasBillSum = fPlanList.get(0).getFactSum();//已开票金额等于计划中的实际付款金额
								        bInfo.setHasBillSum(hasBillSum);
								        
								        Double sum =0.0D;//本次开票金额
								          if(j.split("-").length!=3){
								        	  sum = ret_totalPrice;
								          }else{  
								        	  sum = Double.parseDouble(j.split("-")[2].trim());
								          }
								          bInfo.setSum(sum);//本次开票金额
								          
								        //计划总金额-前期已收-本次实收=剩下未开开票金额
								          Double restSum = planSum-(hasBillSum+sum);//未收金额
//								          if(restSum>0){
								        	  bInfo.setRestSum(restSum);
//								          }else{
//								        	  bInfo.setRestSum(0.0);
//								          }
								          
								         
								          ContractManagement cMlInfo = cM.get(0);//获取当前合同信息
								          double paidlMoney =totalSum+trueSum;//合同的已收金额
//								          if(restSum<0){
//								        	  cMlInfo.setPaidMoney(planSum);
//								          }else{
								        	  cMlInfo.setPaidMoney(paidlMoney);
//								          }
								          
								          
								        //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
								          ReturnPlan rfInfo = fPlanList.get(0);
								          rfInfo.setFactSum(totalSum+trueSum);
								        //收款计划中的完成百分比
								          int percentt = (int) (((totalSum+trueSum)/planSum)*100);
								          rfInfo.setPercentt(percentt);
								          
								        //判断发货数量与要数量是否相等，则是合同属性执行中
							        	  List<ProductList>  productListHl = productListManager.loadByKey("contractManagement.id", cmId);
							        	  
							        	  boolean deliveryedl = true;
							        	  if(productListHl!=null&&productListHl.size()>0){
							        		  for(int u=0;u<productListHl.size();u++){
							        			  int h = productListHl.get(u).getDeliveryedCount();
							        			  int l = productListHl.get(u).getCount();
							        			  if(l!=h){
							        				  deliveryedl = false;
							        			  }
							        		  }
							        	  }
								          
								          
								          //收款计划中的计划状态
								          if(percentt>=100&&deliveryedl){
								        	  String planState = "已完成";//完成状态100则收款计划设置已完成
								        	  List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
									          if(code_planState!=null&&code_planState.size()>0){
									        	  rfInfo.setPlanState(code_planState.get(0));
							        		  }
									          String isBill = "1";//是否开票?是否到款
									          rfInfo.setIsBill(isBill);
									          String billingOrNot = "1";//是否开票
									          rfInfo.setBillingOrNot(billingOrNot);
									          String isOrNot = "0";//是否到款
									          rfInfo.setIsOrNot(isOrNot);
									          
									        //设置合同状态
									          String state = "已结束";
									          List<CodeValue> code_state =codeValueManager.loadByKey("name", state);
									          if(code_state!=null&&code_state.size()>0){
									        	  cMlInfo.setState(code_state.get(0));
							        		  }
								          }
								          
								          String islSaved ="1";  //提交 1是0否 设置合同中的提交
								          cMlInfo.setIsSaved(islSaved);
								          
	               		          
								          //更新项目中的信息
								          List<ProjectInfo> ret_projectl = projectInfoManager.loadByKey("name", cMlInfo.getProject().getName());
								          if(ret_projectl!=null&&ret_projectl.size()>0){
								        	  String [] key={"parentCV.name","name"};
						            		  String [] value={"项目状态","付费"};
									          List<CodeValue> code_statepl =codeValueManager.loadByKeyArray(key, value);
									          if(code_statepl!=null&&code_statepl.size()>0){
									        	  ret_projectl.get(0).setState(code_statepl.get(0));//项目状态
									        	  ret_projectl.get(0).setIsSaved("1");//设置提交状态
									          }
								        	  
						        		  }
								          
								          this.projectInfoManager.storeProjectInfo(ret_projectl.get(0));//更新项目
								          this.contractManagementManager.storeContractManagement(cMlInfo);//更新合同数据
								          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
								          
								         bInfo.setPayee(cMhInfo.getSaleman()); //开票人
								         
								         bInfo.setIsPay("0");//是否付款0是1否
								         
								         this.billingRecordManager.storeBillingRecord(bInfo);
								         
								         List<BillingRecord> billinglist = this.billingRecordManager.loadByKey("contractManagement.id", cMhInfo.getId());
								          if(billinglist!=null&&billinglist.size()>0){
								        	  for(int k=0;k<billinglist.size();k++){
								        		  //计划应收金额更新
								        		  billinglist.get(k).setPlanSum(planSum);
								        		  //取出当前循环中的实收金额
								        		  Double sumHl = billinglist.get(k).getSum();
								        		  //取出当前循环中的已收金额
								        		  Double hasBillSumHl = billinglist.get(k).getHasBillSum();
								        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
								        		  billinglist.get(k).setRestSum(planSum-(sumHl+hasBillSumHl));
								        		  //更新当前信息
								        		  this.billingRecordManager.storeBillingRecord(billinglist.get(k));
								        	  }
								          }
							          
					          }
				          }
				        	  
								
				          
			          }
			          
			          
			          //开票记录
//			          HSSFCell billingRecordCode = hssfRow.getCell((short)34);//(收款日期)发票号
//			          if(billingRecordCode == null||"".equals(getValue(billingRecordCode).trim())){
//			        	  
//			          }else{
//			        	  String rei_billingRecordCode = getValue(billingRecordCode).trim();
//			        	  String rec_billingRecordCode = rei_billingRecordCode.replace("；", ";");
//				          if(rec_billingRecordCode.indexOf(";")==-1){
//				        	  BillingRecord bInfo = new BillingRecord();//开票记录
//				        	  bInfo.setCode(rec_billingRecordCode);//发票编码
//				        	  bInfo.setIsSaved("1");//是否提交1提交0否
//				        	  String datas = rec_billingRecordCode.split("-")[0];
//				        	  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
//					          Date ret_data = null;
//								try {
//									ret_data = sdf.parse(data);
//								} catch (ParseException e) {
//									e.printStackTrace();
//								}
//								bInfo.setBillingTime(ret_data);//开票日期
//								
//								String myCode = autoCompleteCodeb();//开票单编码
//								bInfo.setMyCode(myCode);
//								
//								bInfo.setContractManagement(cMhInfo);//合同
//								
//								bInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
//								
//								bInfo.setContactArchives(cMhInfo.getLinkman());//联系人
//								
//								String bBatch = "1";//收款批次
//						          List<CodeValue> code_bBatch =codeValueManager.loadByKey("name", bBatch);
//						          if(code_bBatch!=null&&code_bBatch.size()>0){
//						        	  bInfo.setBatch(code_bBatch.get(0));
//				        		  }
//						        
//						        Double planSum = fPlanList.get(0).getSum();//计划开票金额
//						        bInfo.setPlanSum(planSum);
//						        
//						        Double sum =0.0D;//本次开票金额
//						          if(rec_billingRecordCode.split("-").length!=3){
//						        	  sum = planSum;
//						          }else{  
//						        	  sum = Double.parseDouble(rec_billingRecordCode.split("-")[2].trim());
//						          }
//						        
//						         bInfo.setPayee(cMhInfo.getSaleman());//开票人
//						         
//						         bInfo.setIsPay("0");//是否付款0是1否
//						         
//						         this.billingRecordManager.storeBillingRecord(bInfo);
//								
//				          }else{
//				        	  String [] delis = rec_billingRecordCode.split(";");
//				        	  for(int i=0;i<delis.length;i++){
//				        		  BillingRecord bInfo = new BillingRecord();//开票记录
//				        		  String j = delis[i].trim();//获取发货单号，多条发货单，循环设置
//					        	  bInfo.setCode(j);//设置发票编码
//					        	  bInfo.setIsSaved("1");//是否提交1提交0否
//					        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
//					        	  String datas = delisx[0].trim();//获取日期
//					        	  
//					        	  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
//						          Date ret_data = null;
//									try {
//										ret_data = sdf.parse(data);
//									} catch (ParseException e) {
//										e.printStackTrace();
//									}
//									bInfo.setBillingTime(ret_data);//开票日期
//									
//									String myCode = autoCompleteCodeb();//开票单编码
//									bInfo.setMyCode(myCode);
//									
//									bInfo.setContractManagement(cMhInfo);//合同
//									
//									bInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
//									
//									bInfo.setContactArchives(cMhInfo.getLinkman());//联系人
//									
//									String bBatch = "1";//收款批次
//							          List<CodeValue> code_bBatch =codeValueManager.loadByKey("name", bBatch);
//							          if(code_bBatch!=null&&code_bBatch.size()>0){
//							        	  bInfo.setBatch(code_bBatch.get(0));
//					        		  }
//							        
//							        Double planSum = fPlanList.get(0).getSum();//计划开票金额
//							        bInfo.setPlanSum(planSum);
//							        
//							        Double sum =0.0D;//本次开票金额
//							          if(rec_billingRecordCode.split("-").length!=3){
//							        	  sum = planSum;
//							          }else{  
//							        	  sum = Double.parseDouble(rec_billingRecordCode.split("-")[2].trim());
//							          }
//							        
//							         bInfo.setPayee(cMhInfo.getSaleman()); //开票人
//							         
//							         bInfo.setIsPay("0");//是否付款0是1否
//							         
//							         this.billingRecordManager.storeBillingRecord(bInfo);
//				        	  }
//				        	  
//				        	  
//				          }
//			          }
			          
			          
			          
			          
			          
			         
			          
			          
			          
			          
			          

		        	  //第一步 根据产品名称和产品型号查询产品id
		        	  //获取合同id
		        	  //设置相关属性  负责人 交货日期
		        	  //保存产品明细
//		        	  pro.save(pl);
//		        	  String [] aa =rec_remark.split(";");
		        	  
		        	 
//		        	  for(int j=0;j<10;j++){
//		        		 send.save(); 
//		        	  }
		        	  
		         
		          
		      }
		      this.message =  "成功导入"+hssfSheet.getLastRowNum()+"条数据";
//		      this.message =  this.contractManagementManager.saveContractManagementfoByImp(pros);
//		      this.message =  this.productListManager.saveProductListManagerfoByImp(prol);
		     
			
		}
		
		//收款单编码
		public String autoCompleteCode() {
			String maxCode = this.financialManagementManager.getMaxPFCode("SK");
			if (null != maxCode) {
				int num = Integer.parseInt(maxCode) + 1;
				if (num < 10)
					return "SK-00000" + num;
				if (num < 100)
					return "SK-0000" + num;
				if (num < 1000)
					return "SK-000" + num;
				if (num < 10000)
					return "SK-00" + num;
				if (num < 100000) {
					return "SK-0" + num;
				}
				return "SK-" + num;
			}

			return "SK-000001";
		}
		//开票记录
		public String autoCompleteCodeb() {
			String maxCode = this.billingRecordManager.getMaxCode("KP");
			if (null != maxCode) {
				int num = Integer.parseInt(maxCode) + 1;
				if (num < 10)
					return "KP-00000" + num;
				if (num < 100)
					return "KP-0000" + num;
				if (num < 1000)
					return "KP-000" + num;
				if (num < 10000)
					return "KP-00" + num;
				if (num < 100000) {
					return "KP-0" + num;
				}
				return "KP-" + num;
			}

			return "KP-000001";
		}
		public void insertDeliveyFile(HSSFWorkbook hssfWorkbook) throws DaoException{
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		    	  HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		          

		          HSSFCell deliveryDate = hssfRow.getCell((short)0);//发货日期
		          String ret_deliveryDate = getValue(deliveryDate).trim();
		          
		          HSSFCell deliveryPersion = hssfRow.getCell((short)1);//负责人id
		          String ret_deliveryPersion = getValue(deliveryPersion).trim();
		          
		          HSSFCell contractManagetment = hssfRow.getCell((short)2);//合同id
		          String ret_contractManagetment = getValue(contractManagetment).trim();
		          
		          HSSFCell productList = hssfRow.getCell((short)3);//产品明细id
		          String ret_productList = getValue(productList).trim();
		          
		          
		          HSSFCell deliveryCount = hssfRow.getCell((short)4);//发货数量
		          String ret_deliveryCount = getValue(deliveryCount).trim();
		          
		          
		          HSSFCell customer = hssfRow.getCell((short)5);//客户
		          String ret_customer = getValue(customer).trim();
		          
		          HSSFCell contractor = hssfRow.getCell((short)6);//联系人
		          String ret_contractor = getValue(contractor).trim();
		          
		          
		          HSSFCell telephone = hssfRow.getCell((short)7);//联系人方式
		          String ret_telephone ="";
		          if(telephone!=null){
		        	  ret_telephone= getValue(telephone).trim();
		          }
		           
		          
		          
		          HSSFCell department = hssfRow.getCell((short)8);//负责人部门
		          String ret_department = getValue(department).trim();
		          
		          
		          HSSFCell  contractWay= hssfRow.getCell((short)9);//负责人联系方式
		          String ret_contractWay = getValue(contractWay).trim();
		          
		          HSSFCell  deliveryNum= hssfRow.getCell((short)10);//出库单编码
		          String ret_deliveryNum = getValue(deliveryNum).trim();
		    	  
		    	  
		    	  InvoicesList invoicesList = new InvoicesList();
		    	  Invoices invoices =null;
		    	  
		          List<Invoices> listInvoice = this.invoicesManager.loadByKey("customerInfo.id", ret_customer);
		          
		          if(listInvoice!=null&&listInvoice.size()>0){
		        	  invoices =  listInvoice.get(0);//
		        	  invoicesList.setInvoices(invoices);
		        	  invoicesList.setCurrentSum(Long.parseLong(ret_deliveryCount));
		        	  invoicesList.setIsSaved("1");
		        	  ProductList proList = productListManager.loadProductList(Long.parseLong(ret_productList));
		        	  invoicesList.setProductList(proList);
		        	  invoicesList.setDeliveryPrice(Long.parseLong(ret_deliveryCount)*proList.getUnitPrice());
		        	  long finishSum  = proList.getDeliveryedCount();
		        	  System.out.println("============================="+finishSum);
		        	  invoicesList.setFinishedSum(finishSum);
		        	  invoicesList.setRestSum(proList.getCount()-proList.getDeliveryedCount()-Long.parseLong(ret_deliveryCount));
		        	  invoicesListManager.storeInvoicesList(invoicesList);
		        	  if(invoices.getDeliveryCount()!=null){
		        		  invoices.setDeliveryCount(invoices.getDeliveryCount()+Integer.parseInt(ret_deliveryCount));
		        	  }else {
		        		  invoices.setDeliveryCount(Integer.parseInt(ret_deliveryCount));
					  }
		        	  invoices.setDeliveryPrice(invoices.getDeliveryPrice()+invoicesList.getDeliveryPrice());
		        	  this.invoicesManager.storeInvoices(invoices);
		        	  proList.setDeliveryedCount(proList.getDeliveryedCount()+Integer.parseInt(ret_deliveryCount));
		        	  productListManager.storeProductList(proList);
		          }else {
		        	  
		        	  
		        	  try {
		        		  invoices = new Invoices();
			        	  invoices.setDeliveryNum(ret_deliveryNum);
			        	  invoices.setDeliveryDate(sf.parse(ret_deliveryDate));
			        	  PersonnelFiles personnelFiles = new PersonnelFiles();
			        	  personnelFiles.setId(Long.parseLong(ret_deliveryPersion));
			        	  invoices.setDeliveryPerson(personnelFiles);
			        	  CustomerInfo customerInfo = new CustomerInfo();
			        	  customerInfo.setId(Long.parseLong(ret_customer));
			        	  invoices.setCustomerInfo(customerInfo);
			        	  invoices.setContacter(ret_contractor);
			        	  invoices.setTelephone(ret_telephone);
			        	  Department dept = new Department();
			        	  dept.setId(Long.parseLong(ret_department));
			        	  invoices.setDepartment(dept);
			        	  invoices.setContactWay(ret_contractWay);
			        	  invoices.setDeliveryPrice(0);
			        	  invoices.setDeliveryCount(0);
			        	  this.invoicesManager.storeInvoices(invoices);
			        	  
						  invoicesList.setInvoices(invoices);
			        	  invoicesList.setCurrentSum(Long.parseLong(ret_deliveryCount));
			        	  invoicesList.setIsSaved("1");
			        	  
			        	  ProductList proList = productListManager.loadProductList(Long.parseLong(ret_productList));
			        	  long finishSum  = proList.getDeliveryedCount();
			        	  System.out.println("============================="+finishSum);
			        	  invoicesList.setProductList(proList);
			        	  invoicesList.setDeliveryPrice(Long.parseLong(ret_deliveryCount)*proList.getUnitPrice());
			        	  invoicesList.setFinishedSum(finishSum);
			        	  invoicesList.setRestSum(proList.getCount()-proList.getDeliveryedCount()-Long.parseLong(ret_deliveryCount));
			        	  invoicesListManager.storeInvoicesList(invoicesList);
			        	  
			        	  if(invoices.getDeliveryCount()!=null){
			        		  invoices.setDeliveryCount(invoices.getDeliveryCount()+Integer.parseInt(ret_deliveryCount));
			        	  }else {
			        		  invoices.setDeliveryCount(Integer.parseInt(ret_deliveryCount));
						  }
			        	  invoices.setDeliveryPrice(invoices.getDeliveryPrice()+invoicesList.getDeliveryPrice());
			        	  this.invoicesManager.storeInvoices(invoices);
			        	  proList.setDeliveryedCount(proList.getDeliveryedCount()+Integer.parseInt(ret_deliveryCount));
			        	  productListManager.storeProductList(proList);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
				  }
		          
		    	  
		          
		          
		      }
		      this.message =  "成功导入"+hssfSheet.getLastRowNum()+"条数据";
			
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