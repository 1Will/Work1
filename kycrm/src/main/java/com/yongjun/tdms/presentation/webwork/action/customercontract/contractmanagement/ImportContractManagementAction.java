/*     */package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.lowagie.tools.split_pdf;
import com.lowagie.tools.plugins.Split;
import com.opensymphony.util.DateUtil;
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
import com.yongjun.tdms.model.base.house.House;
import com.yongjun.tdms.model.base.products.Products;
import com.yongjun.tdms.model.base.produttype.ProductType;
import com.yongjun.tdms.model.customercontract.billingrecord.BillingRecord;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
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
import com.yongjun.tdms.service.base.house.HouseManager;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.base.products.ProductsManager;
import com.yongjun.tdms.service.base.producttype.ProductTypeManager;
import com.yongjun.tdms.service.customercontract.billingrecord.BillingRecordManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.houseList.HouseListManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.productlist.ProductListManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.financialmanagement.FinancialManagementManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;

import net.sf.ehcache.store.Store;
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
	       private final ProjectInfoCustomerManager projectInfoCustomerManager;
	       private final ProjectInfoContractManager projectInfoContractManager;
	       private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	       private final HouseListManager houseListManager;
	       private final HouseManager  houseManager;
	       private DecimalFormat  format  = new DecimalFormat("######0.00");
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
					BillingRecordManager billingRecordManager,ProjectInfoCustomerManager projectInfoCustomerManager,ProjectInfoContractManager projectInfoContractManager,
					ProjectInfoPersonnelsManager projectInfoPersonnelsManager,HouseListManager houseListManager, HouseManager  houseManager) {
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
				this.projectInfoCustomerManager = projectInfoCustomerManager;
				this.projectInfoContractManager = projectInfoContractManager;
				this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
				this.houseListManager=houseListManager;
				this.houseManager=houseManager;
				}
/*     */ 
/*     */   public String save()
/*     */     throws Exception
/*     */   {
	       File file   = getFile();//获取文件
	       InputStream is = new FileInputStream(file);//转化为流
			 HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			 if(hssfWorkbook!=null){
				 String result = checkFile(hssfWorkbook);// 检查表格
				if (result.equals("")) {// 检查结果为空。表示数据正常，
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
			} else if(hssfCell.getCellType() == hssfCell.CELL_TYPE_FORMULA){//计算带公式的值
				String  cellValue=hssfCell.getNumericCellValue()+"";
				return cellValue;			
			}{
      // 返回字符串类型的值
				return String.valueOf(hssfCell.getStringCellValue());
			}
         }
		public String getValue_(HSSFCell hssfCell){
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			      // 返回数值类型的值
					double cellValue = hssfCell.getNumericCellValue();
					String cString=cellValue+"";
					return  cString;
		}
			return String.valueOf(hssfCell.getStringCellValue());
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
			          
			          HSSFCell hSSFCell = hssfRow.getCell((short)1);//合同名称
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行合同名称不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行合同名称不能为空<br/>";
			        	  }else{
			        		  //判断合同是否已存在
					          List namesCons=contractManagementManager.loadByKey("contractName", getValue(hssfRow.getCell((short)1)).trim());
					          if(namesCons!=null&& namesCons.size()>0){
					        	  this.message +="第"+indexNum+"行合同名称重复<br/>";
					          }
			        	  }
			          }
			          
			           hSSFCell = hssfRow.getCell((short)2);//客户名称
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行客户名称不能为空<br/>";
			          }else{
			        	  if("".equals(getValue(hSSFCell).trim())){
			        	  this.message+="第"+indexNum+"行客户名称不能为空<br/>";
			        	  }else{
			        		  String rec_pcustomerInfo = getValue(hSSFCell).trim();
					          List<CustomerInfo> ret_pcustomerInfo = customerInfoManager.loadByKey("name", rec_pcustomerInfo);
					          if(ret_pcustomerInfo==null||ret_pcustomerInfo.size()<1){
					        	  this.message+="第"+indexNum+"行--"+rec_pcustomerInfo+"--客户名称在系统中不存在<br/>";  
			        		  } 
			        	  }
			          }
			          
			          hSSFCell = hssfRow.getCell((short)3);//联系人
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行联系人不能为空<br/>";
			          }else if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行联系人不能为空<br/>";
			          }else{
			        	  List<ContactArchives> ret_linkman = contactArchivesManager.loadByKey("name", getValue(hSSFCell).trim()); 
			        	  if(!(ret_linkman!=null&&ret_linkman.size()>0)){
			        		  this.message+="第"+indexNum+"行联系人不存在<br/>";
			        	  }
			          }
			          
			          hSSFCell = hssfRow.getCell((short)4);//联系电话
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行联系人不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        		  this.message+="第"+indexNum+"行联系人不能为空<br/>";
			        	  }
			          }
			          hSSFCell = hssfRow.getCell((short)5);//联系地址
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行联系地址不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行联系地址不能为空<br/>";
			        	  }else{
			        		  String[] fCode = getValue(hssfRow.getCell((short)5)).trim().split("#");
			        		  for(int i=0;i<fCode.length;i++){
			        			  List <House>house=houseManager.loadByKey("code",fCode[i].trim());
			        			  if(!(house!=null && house.size()>0)){
			        				  this.message+="第"+indexNum+"的行"+fCode[i].trim()+"的联系地址不存在<br/>";
			        			  }else {
			        				  List <HouseList>houseList=houseListManager.loadByKey("house", house.get(0));
			        				  if(houseList !=null && houseList.size()>0){
			        					  if(houseList.get(0).getIsuse()){
			        						  this.message+="第"+indexNum+"的行"+fCode[i].trim()+"的地址已在用<br/>";
			        					  }
			        				  }
			        			  }
			        		  }
			        	  }
			          } 
			          
			          hSSFCell = hssfRow.getCell((short)6);//业务员
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行业务员不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行业务员不能为空<br/>";
			        	  }else{
			        		  List pList=personnelFilesManager.loadByKey("name",getValue(hSSFCell).trim());
			        		  if(!(pList!=null && pList.size()>0)){
			        			  this.message+="第"+indexNum+"行业务员不存在<br/>";
			        		  }
			        	  }
			          }
			          
			          hSSFCell = hssfRow.getCell((short)7);// 签订日期
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行签订日期不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行签订日期不能为空<br/>";
			        	  }
			          }
			          
			          hSSFCell = hssfRow.getCell((short)8);// 单价
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行单价不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行单价不能为空<br/>";
			        	  }else{
			        		  String[] fprice = getValue(hssfRow.getCell((short)8)).trim().split("\\+");
			        		  for(String s:fprice){
			        			  if (!isNumeric(s)) {
										this.message += "第" + indexNum + "行单价必须是数字<br/>";
									} else {
										if (Double.parseDouble(s) < 0) {
											this.message += "第" + indexNum + "行单价必须是正数<br/>";
										}
									}
			        		  }
			        		  
			        	  }
			          }
			        
			          hSSFCell = hssfRow.getCell((short)9);//开始日期
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行开始日期不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行开始日期不能为空<br/>";
			        	  }
			          }
			          
			          hSSFCell = hssfRow.getCell((short)10);//终止日期
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行终止日期不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行终止日期不能为空<br/>";
			        	  }
			          } 
			          
			          hSSFCell = hssfRow.getCell((short)12);//合同金额
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行合同金额不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行合同金额不能为空<br/>";
			        	  }else{
			        		  if (!isNumeric(getValue(hSSFCell).trim())) {
									this.message += "第" + indexNum + "行合同金额必须是数字<br/>";
								} else {
									if (Double.parseDouble(getValue(hSSFCell).trim()) < 0) {
										this.message += "第" + indexNum + "行合同金额必须是正数<br/>";
									}
								}
			        	  }
			          } 
			          hSSFCell = hssfRow.getCell((short)13);//已收金额
			        if(!getValue(hSSFCell).trim().equals("")){
	        		  if (!isNumeric(getValue(hSSFCell).trim())) {
							this.message += "第" + indexNum + "行已收金额必须是数字<br/>";
						} else {
							if (Double.parseDouble(getValue(hSSFCell).trim()) < 0) {
								this.message += "第" + indexNum + "行已收金额必须是正数<br/>";
							}
						}
			        }
			          
			          hSSFCell = hssfRow.getCell((short)14);//币种
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行币种不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行币种不能为空<br/>";
			        	  }
			          } 
			          
			          hSSFCell = hssfRow.getCell((short)14);//收款类型
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行收款类型不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行收款类型不能为空<br/>";
			        	  }
			          } 
			          hSSFCell = hssfRow.getCell((short)15);//支付方式
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行支付方式不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行支付方式不能为空<br/>";
			        	  }
			          } 
			          hSSFCell = hssfRow.getCell((short)18);// 月数
			          if(hSSFCell == null){
			        	  this.message+="第"+indexNum+"行月数不能为空<br/>";
			          }else{
			        	  if(getValue(hSSFCell).trim().equals("")){
			        	  this.message+="第"+indexNum+"行月数不能为空<br/>";
			        	  }else{
			        			  if (!isNumeric(getValue(hSSFCell).trim())) {
										this.message += "第" + indexNum + "行月数必须是数字<br/>";
									} else {
										if (Double.parseDouble(getValue(hSSFCell).trim()) < 0) {
											this.message += "第" + indexNum + "行月数必须是正数<br/>";
										}
									}
			        		  }
			        		  
			          }
			      }
			          
			      
			      return this.message;
		}
		
		public void insertFile(HSSFWorkbook hssfWorkbook) throws DaoException, ParseException{
			List<ContractManagement> pros= new ArrayList<ContractManagement>();
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			 long size=0L;
		      for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
		    	  System.out.println(rowNum+"==============================");
		    	  ContractManagement cInfo = new ContractManagement();//合同
		    	  ProjectInfo pInfo = new ProjectInfo();//项目
		    	  ReturnPlan rInfo = new ReturnPlan();//收款计划
		    	  ProjectInfoContract pjcoInfo = new ProjectInfoContract();//项目中的联系人列表
		    	  HSSFRow hssfRow = hssfSheet.getRow(rowNum);
		    	  
		          SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd");
		         //判断合同是否已存在
		          List namesCons=contractManagementManager.loadByKey("contractName", getValue(hssfRow.getCell((short)1)).trim());
		          if(namesCons!=null&& namesCons.size()>0){
		        	  this.message +="第"+rowNum+"条合同名称重复";
		        	  size++;
		        	  continue;
		          }
		          //设置合同名称
		          cInfo.setContractName(getValue(hssfRow.getCell((short)1)).trim());
		          //以下为设置项目部分
		          String pjName = null;//设置项目名称为空
		          HSSFCell pcustomerInfo = hssfRow.getCell((short)2);
		          String rec_pcustomerInfo = getValue(pcustomerInfo).trim();//客户名称
		          List<CustomerInfo> ret_pcustomerInfo = customerInfoManager.loadByKey("name", rec_pcustomerInfo);
		          if(ret_pcustomerInfo!=null&&ret_pcustomerInfo.size()>0){
		        	  cInfo.setCustomerInfo(ret_pcustomerInfo.get(0));
		        	  List<ProjectInfo> pjInfo = projectInfoManager.loadByKey("customer", ret_pcustomerInfo.get(0).getId());//根据客户查询对应的多个项目
		        	  if(pjInfo !=null&&pjInfo.size()>0){
		        		  pjName = pjInfo.get(0).getName();//如果通过客户名称可以查询到项目名称则赋值
		        		  cInfo.setProject(pjInfo.get(0));
		        	  }
        		  }
		          
		          if(pjName == null){
		        	  HSSFCell pcontractName = hssfRow.getCell((short)1);//合同名称
				      String ret_pcontractName = getValue(pcontractName).trim();
				      String rec_project = rec_pcustomerInfo+"项目";//项目名称（客户名+合同名称+项目）
			          pInfo.setName(rec_project);//项目名称
			          
			          //客户名称
			          if(ret_pcustomerInfo!=null&&ret_pcustomerInfo.size()>0){
	        			  pInfo.setCustomer(ret_pcustomerInfo.get(0));//项目中的客户名称
	        		  }
			          
			          HSSFCell plinkman = hssfRow.getCell((short)3);//联系人
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
			          //获取项目负责人
		        		 List<PersonnelFiles>personnelFiles= personnelFilesManager.loadByKey("name", getValue(hssfRow.getCell((short)6)).trim());
		        		  if(personnelFiles!=null&&personnelFiles.size()>0){
		        			  pInfo.setController(personnelFiles.get(0));
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
			          
			          cInfo.setProject(projectInfoManager.loadByKey("name", rec_project).get(0));
			          
//			          //以下为项目细节部分
//			          //设置项目客户（tab页）
//			          if(ret_pcustomerInfo!=null&&ret_pcustomerInfo.size()>0){
//	        			  pjcuInfo.setCustomerInfo(ret_pcustomerInfo.get(0));
//	        		  }
//			          //主要说明
//			          String outline = "无";
//			          pjcuInfo.setOutline(outline);
//			              
//			          
//			          //设置联系人列表			          
//			          pjcoInfo.setOutline(outline);//项目联系人列表主要说明
//			          
//			        //使用新增项目中的项目名给予合同;赋予tab页项目客户
//			          List<ProjectInfo> ret_project = projectInfoManager.loadByKey("name", rec_project);
//			          if(ret_project!=null&&ret_project.size()>0){
//	        			  cInfo.setProject(ret_project.get(0));
//	        			  pjcuInfo.setProjectInfo(ret_project.get(0));
//	        			  pjcoInfo.setProjectInfo(ret_project.get(0));
//	        		  }
//			          
//			          //设置项目客户
//			          this.projectInfoCustomerManager.storeProjectInfoCustomer(pjcuInfo);
//			          //设置项目联系人列表
//			          this.projectInfoContractManager.storeProjectInfoContract(pjcoInfo);
//			          
//			          
//			          
			          }
			          
		          //以下为合同
		          HSSFCell customerInfo = hssfRow.getCell((short)2);//客户名称
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
		          
		          HSSFCell linkman = hssfRow.getCell((short)3);//联系人
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
		          
		          HSSFCell telephone = hssfRow.getCell((short)4);//联系电话
		          if(telephone == null||"".equals(getValue(telephone).trim())){//如果表格内没联系电话就从客户信息里取
		        	  cInfo.setTelephone(ret_customerInfo.get(0).getMobilePhone());
		          }else{
		        	  String ret_telephone = getValue(telephone).trim();
			          cInfo.setTelephone(ret_telephone); 
		          }
		          
		          
		          HSSFCell address = hssfRow.getCell((short)5);//联系地址
		          if(address == null||"".equals(getValue(address).trim())){//如果表格内没联系地址
		        	  cInfo.setAddress("无");
		          }else{
		        	  String ret_address = getValue(address).trim();
			          cInfo.setAddress(ret_address);  
		          }
		          
		          HSSFCell saleman = hssfRow.getCell((short)6);//负责人
		        	  String rec_saleman = getValue(saleman).trim();
		        	  if(rec_saleman!=null&&rec_saleman.length()>0){
			          List<PersonnelFiles> ret_saleman = personnelFilesManager.loadByKey("name", rec_saleman);
			          if(ret_saleman!=null&&ret_saleman.size()>0){
	        			  cInfo.setSaleman(ret_saleman.get(0));
	        			  cInfo.setDeparment(ret_saleman.get(0).getDept());//部门
		          }
		        	  }
		          
		          HSSFCell ciemdinghTime = hssfRow.getCell((short)7);//签订日期
		        	  String rec_ciemdinghTime =getValue(ciemdinghTime).trim();
		        	  if(rec_ciemdinghTime!=null&&rec_ciemdinghTime.length()>0){
			          Date ret_ciemdinghTime = null;
						try {
							ret_ciemdinghTime = sdf.parse(rec_ciemdinghTime);
						} catch (ParseException e) {
							e.printStackTrace();
						}
			          cInfo.setCiemdinghTime(ret_ciemdinghTime); 
		        	  }
		          
		          HSSFCell startTime = hssfRow.getCell((short)9);//开始日期
		        	  String rec_startTime =getValue(startTime).trim();
		        	  if(rec_startTime!=null&&rec_startTime.length()>0){
		        		  Date ret_startTime = null;
							try {
								ret_startTime = sdf.parse(rec_startTime);
							} catch (ParseException e) {
								e.printStackTrace();
							}
				          cInfo.setStartTime(ret_startTime);
		        	  }
			          
		          
		          HSSFCell endTime = hssfRow.getCell((short)10);//终止日期
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
		          
		          
		          HSSFCell contractType = hssfRow.getCell((short)11);//合同类型
		          if(contractType!=null){
		        	  String ret_contractType =getValue(contractType).trim();
			          List<CodeValue> code_contractType =codeValueManager.loadByKey("name", ret_contractType);
			          if(code_contractType!=null&&code_contractType.size()>0){
	        			  cInfo.setContractType(code_contractType.get(0));
	        		  }
		          }
		          
		          HSSFCell contractMoney = hssfRow.getCell((short)12);//合同金额
		          if(contractMoney == null||"".equals(contractMoney)){
		        	  cInfo.setContractMoney(0.00);
		          }else{
		        	  String rec_contractMoney =getValue_(contractMoney).trim();
			          Double ret_contractMoney =Double.parseDouble(format.format(Double.parseDouble(rec_contractMoney)));
			          System.out.println(ret_contractMoney);
			          cInfo.setContractMoney(ret_contractMoney);
		          }
		          
		          
		          HSSFCell paidMoney = hssfRow.getCell((short)13);//已收金额
		          if(paidMoney == null||"".equals(getValue(paidMoney).trim())){
		        	  cInfo.setPaidMoney(0.00);
		          }else{
		        	  String rec_paidMoney = getValue_(paidMoney);
		        	  System.out.println(rec_paidMoney);
			          Double ret_paidMoney = Double.parseDouble(format.format(Double.parseDouble(rec_paidMoney)));
			          System.out.println(ret_paidMoney);
			          cInfo.setPaidMoney(ret_paidMoney);
		          }
		          
		          HSSFCell moneyType = hssfRow.getCell((short)14);//币种
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
		          
		          HSSFCell payType = hssfRow.getCell((short)15);//收款类型
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
		          
		          
		        	  String ret_state ="执行中";
			          List<CodeValue> code_state =codeValueManager.loadByKey("name", ret_state);
			          if(code_state!=null&&code_state.size()>0){
	        			  cInfo.setState(code_state.get(0));
	        		  }
		          
		          
		          HSSFCell contractContent = hssfRow.getCell((short)16);//合同内容
		          if(contractContent == null||"".equals(getValue(contractContent).trim())){
		        	  cInfo.setContractContent("无"); 
		          }else{
		        	  String rec_contractContent = getValue(contractContent).trim();
			          cInfo.setContractContent(rec_contractContent); 
		          }
		          
		          HSSFCell remark = hssfRow.getCell((short)17);//备注
		          if(remark == null||"".equals(getValue(remark).trim())){
		        	  cInfo.setRemark("无");
		          }else{
		        	  String rec_remark = getValue(remark).trim();
			          cInfo.setRemark(rec_remark);
		          }
		          String isSaved ="1";  //提交 1是0否
		          cInfo.setIsSaved(isSaved);
		          this.contractManagementManager.storeContractManagement(cInfo);
		        //房源明细
		        String[] fCode = getValue(hssfRow.getCell((short)5)).trim().split("#");
		        String[] fprice = getValue(hssfRow.getCell((short)8)).trim().split("\\+");
                int mons=Integer.parseInt(getValue(hssfRow.getCell((short)18)).trim());
		        double square=0;
		        for(int i=0;i<fCode.length;i++){
		        	String code="'"+fCode[i].trim()+"'";
		        	House house=houseManager.loadByKey("code",fCode[i].trim()).get(0);
		        	HouseList houselist=new HouseList();
		        	houselist.setContractManagement(cInfo);
		        	houselist.setHouse(house); 
		        	houselist.setRent(Double.parseDouble(format.format(Double.parseDouble(fprice[0].trim()))));
		        	houselist.setService(Double.parseDouble(format.format(Double.parseDouble(fprice[1].trim()))));
		        	Double price=Double.parseDouble(format.format((Double.parseDouble(fprice[0].trim())+Double.parseDouble(fprice[1].trim()))*house.getSquare()));
		        	houselist.setPrice(price);
		        	houselist.setMonth(mons);
		        	Calendar cal = Calendar.getInstance();
		        	cal.setTime(sdf.parse(getValue(hssfRow.getCell((short)9)).trim()));
		        	//下面的就是把当前日期加一个月
		        	cal.add(Calendar.MONTH, mons);
		        	//当前日期减一天
		        	cal.add(Calendar.DAY_OF_MONTH, -1);
		        	houselist.setEndTime(cal.getTime());
		        	houselist.setStartTime(sdf.parse(getValue(hssfRow.getCell((short)9)).trim()));
                    if(new Date().before(cal.getTime())&& sdf.parse(getValue(hssfRow.getCell((short)9)).trim()).before(new Date())){
                    	houselist.setIsuse(true);
		        	}else{
		        		houselist.setIsuse(false);
		        	}
		        	houselist.setSum(Double.parseDouble(format.format(mons*price)));
		        	houseListManager.storeHouseList(houselist);
        			//修改房子状态为在用
        			house.setState(codeValueManager.loadByKey("code", "23102").get(0));
        			square+=house.getSquare();
        			houseManager.storeHouse(house);
                  }
		        List<ContractManagement> c=contractManagementManager.loadByKey("contractName", getValue(hssfRow.getCell((short)1)).trim());
		        if(c!=null &&c.size()>0){
		        	c.get(0).setSquare(Double.parseDouble(format.format(square)));
		        	this.contractManagementManager.storeContractManagement(c.get(0));
		        }
		        
		      }  
		          this.message += "成功导入"+(hssfSheet.getLastRowNum()-size)+"条数据";
		          }
		          
		          
//		              //产品明细
//		        	  ProductList pl =new ProductList();
//		        	  		        	  
//		        	  List<ContractManagement> cM = contractManagementManager.loadByKey("code", cInfo.getCode());//根据合同编码获取相应的合同
//		        	  pl.setContractManagement(cM.get(0));
//		        	  
//		        	  HSSFCell pModel = hssfRow.getCell((short)22);//产品型号
//			          String rec_pModel = getValue(pModel).trim();
//			          List<Products> pds = productsManager.loadByKey("model", rec_pModel);//根据产品型号获取对应产品信息
//			          pl.setProduct(pds.get(0));
////		        	  Long pdId = productsManager.loadByKey("model", rec_pModel).get(0).getId();//获取产品ID
//		        	  
//		        	  HSSFCell count = hssfRow.getCell((short)23);//数量
//			          String ret_count = getValue(count).trim();
//			          int rec_count = Integer.parseInt(ret_count);
//			          pl.setCount(rec_count);
//			          
//			          HSSFCell unit = hssfRow.getCell((short)24);//单位
//			          String ret_unit = getValue(unit).trim();
//			          List<CodeValue> code_unit =codeValueManager.loadByKey("name", ret_unit);
//			          if(code_unit!=null&&code_unit.size()>0){
//			        	  pl.setUnit(code_unit.get(0));
//	        		  }
//			          
//			          HSSFCell unitPrice = hssfRow.getCell((short)25);//单价
//			          String rec_unitPrice = getValue(unitPrice).trim();
//			          Double ret_unitPrice = Double.parseDouble(rec_unitPrice);
//			          pl.setUnitPrice(ret_unitPrice);
//			          
//			          HSSFCell totalPrice = hssfRow.getCell((short)26);//总价
//			          String rec_totalPrice = getValue(totalPrice).trim();
//			          Double totalPricex = Double.parseDouble(rec_totalPrice);
//			          DecimalFormat format = new DecimalFormat("0.00");//保留小数点两位小数
//			          Double ret_totalPrice = Double.parseDouble(format.format(totalPricex));
//			          pl.setTotalPrice(ret_totalPrice);
//			          Double discount = (ret_totalPrice/(double)rec_count)/ret_unitPrice*100;
//			          Double discountx = Double.parseDouble(format.format(discount));
//			          pl.setDiscount(discountx);//产品明细的百分比
//			          
//			          HSSFCell plannedDeliveryDate = hssfRow.getCell((short)27);//计划交付日期
//			          if(plannedDeliveryDate == null||"".equals(getValue(plannedDeliveryDate).trim())){
//			        	  //如果为空，则取合同签订日期加上两个月
//			        	  Date cMDate = cM.get(0).getCiemdinghTime();//合同日期
//			        	  System.out.println(cMDate);
//			        	  System.out.println(new Date(cMDate.getTime()+(long)60*24*60*60*1000));
//			        	  pl.setPlannedDeliveryDate(new Date(cMDate.getTime()+(long)60*24*60*60*1000));//产品明细的计划交付日期
//			        	  rInfo.setPlanDate(new Date(cMDate.getTime()+(long)60*24*60*60*1000));//收款计划交付日期  
//			          }else{
//			        	  String rec_plannedDeliveryDate =getValue(plannedDeliveryDate).trim().replace(" ", "");
//			        	  String data = rec_plannedDeliveryDate.substring(0,4)+"-"+rec_plannedDeliveryDate.substring(4,6)+"-"+rec_plannedDeliveryDate.substring(6,8);
//				          Date ret_plannedDeliveryDate = null;
//							try {
//								ret_plannedDeliveryDate = sdf.parse(data);
//							} catch (ParseException e) {
//								e.printStackTrace();
//							}
//				          pl.setPlannedDeliveryDate(ret_plannedDeliveryDate);//产品明细的计划交付日期
//				          rInfo.setPlanDate(ret_plannedDeliveryDate);//收款计划交付日期  
//			          }
//			          
//			          HSSFCell qualityControl = hssfRow.getCell((short)28);//质检
//			          if(qualityControl == null||"".equals(getValue(qualityControl).trim())){
//			        	  List<CodeValue> code_qualityControl =codeValueManager.loadByKey("name", "待定");
//				          if(code_qualityControl!=null&&code_qualityControl.size()>0){
//				        	  pl.setQualityControl(code_qualityControl.get(0)); 
//		        		  }
//			          }else{
//			        	  String ret_qualityControl = getValue(qualityControl).trim();
//			        	  List<CodeValue> code_qualityControl =codeValueManager.loadByKey("name", ret_qualityControl);
//				          if(code_qualityControl!=null&&code_qualityControl.size()>0){
//				        	  pl.setQualityControl(code_qualityControl.get(0)); 
//		        		  }
//				          
//			          }
//			          
//			          HSSFCell remarkPl = hssfRow.getCell((short)39);//备注
//			          String rec_remarkPl = getValue(remarkPl).trim();
//			          pl.setRemark(rec_remarkPl);
//			          
//			          //查询到产品型号对相应的价格进行更新
//			          List<Products> lProducts = productsManager.loadByKey("model", rec_pModel);
//			          if(lProducts!=null&&lProducts.size()>0){
//			        	  lProducts.get(0).setSalePrice(ret_unitPrice);//修改产品价格为合同内部价格
//			        	  
//			          }
//			          this.productsManager.storeProducts(lProducts.get(0));
//			          //下面为收款计划
//	        		  //取合同负责人  
//			          rInfo.setChargMan(cM.get(0).getSaleman());//收款计划负责人
//	        		  rInfo.setPayee(cM.get(0).getSaleman());//收款计划收款人
//	        		  rInfo.setCustomerInfo(cM.get(0).getCustomerInfo());//从合同中获取客户信息给收款计划
//	        		  
//			          
//			          HSSFCell deliveryedCount = hssfRow.getCell((short)30);//已交付数量
//			          if(deliveryedCount == null||"".equals(getValue(deliveryedCount).trim())){
//			        	  HSSFCell deliveryNum = hssfRow.getCell((short)31);//出库单号         
//				          if(deliveryNum == null||"".equals(getValue(deliveryNum).trim())){
//				        	  pl.setDeliveryedCount(0); 
//				          }else{
//				        	  String rei_deliveryNum = getValue(deliveryNum).trim().replace(" ", "");
//				        	 String rec_deliveryNum = rei_deliveryNum.replace("；", ";");
//				        	    
//					          if(rec_deliveryNum.indexOf(";")==-1){
//					        	  if(rec_deliveryNum.indexOf("-")==-1){
//					        		  pl.setDeliveryedCount(rec_count);
//					        	  }else{
//					        		  String [] delisx = rec_deliveryNum.split("-");
//						        	  int n = Integer.parseInt(delisx[1]);//获取数量
//						        	  pl.setDeliveryedCount(n); 
//					        	  }
//					        	  
//					          }else{
//					        	  String [] delis = rec_deliveryNum.split(";");//[20150429132-1;,20150506145-1;...]
//					        	  int num = 0;//发货数量
//						        	  for(int i=0;i<delis.length;i++){
//							        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
//							        	  String n = delisx[1];//获取数量1+2+1+4
//							        	  num+= Integer.parseInt(n);								          
//							          } 
//						        	  pl.setDeliveryedCount(num); 
//					          }
//				          }
//			          }else{
//			        	  String rec_deliveryedCount = getValue(deliveryedCount).trim();
//				          int ret_deliveryedCount = Integer.parseInt(rec_deliveryedCount);
//				          pl.setDeliveryedCount(ret_deliveryedCount);  
//			          }
//			                   
//			          this.productListManager.storeProductList(pl);
//			          
//			          //维护更新合同内部总价
//			          //1.取出当前循环合同的总价格
//			          ContractManagement cMhInfo = cM.get(0);
//			          //把取出的合同价格加上当前循环的产品明细价格为现阶段的总价
//			          cMhInfo.setContractMoney(cMhInfo.getContractMoney()+ret_totalPrice);  
//			          //更新
//			          this.contractManagementManager.storeContractManagement(cMhInfo);
//			          
//			          //收款计划
//			          Long cmId = contractManagementManager.loadByKey("code", cInfo.getCode()).get(0).getId();//根据合同编码获取合同ID
//			          List<ReturnPlan> rPlanList = this.returnPlanManager.loadByKey("contractManagement", cmId);//根据合同ID获取收款计划列表
//			          if(rPlanList!=null&&rPlanList.size()>0){//如果列表存在
//			        	  ReturnPlan rxInfo = rPlanList.get(0);//取出列表里面的总价
//			        	  rxInfo.setSum(rxInfo.getSum()+ret_totalPrice);//收款计划的总价=使用列表中的总价+现在表格里面的总价  
//			        	  this.returnPlanManager.storeReturnPlan(rxInfo);
//			          }else{
//			        	  List<ContractManagement> rCM = this.contractManagementManager.loadByKey("code", cInfo.getCode());
//			        	  if(rCM!=null&&rCM.size()>0){
//			        		  rInfo.setContractManagement(rCM.get(0));//相关合同
//			        	  }
//			        	  HSSFCell rcustomerInfo = hssfRow.getCell((short)5);//相关客户名称
//				          String rec_rcustomerInfo = getValue(rcustomerInfo).trim();
//				          List<CustomerInfo> ret_rcustomerInfo = customerInfoManager.loadByKey("name", rec_rcustomerInfo);
//				          if(ret_rcustomerInfo!=null&&ret_rcustomerInfo.size()>0){
//		        			  rInfo.setCustomerInfo(ret_rcustomerInfo.get(0));//收款计划中的客户信息
//		        			  System.out.println(ret_rcustomerInfo.get(0));
//		        		  }
//				          HSSFCell rlinkman = hssfRow.getCell((short)6);//相关联系人
//				          if(rlinkman == null||"".equals(getValue(rlinkman).trim())){
//				        	  List<ContactArchives> ret_rlinkman = contactArchivesManager.loadByKey("name", ret_rcustomerInfo.get(0).getKeyContacter());
//					          if(ret_rlinkman!=null&&ret_rlinkman.size()>0){
//			        			  rInfo.setContactArchives(ret_rlinkman.get(0));
//			        			  if(ret_rlinkman.get(0).getPhone()!=null){
//			        				  rInfo.setPhone(ret_rlinkman.get(0).getPhone());//设置联系电话 
//			        			  }else{
//			        				 rInfo.setPhone("000000");//如果没有联系电话设定000000 
//			        			  }
//			        			  
//			        		  }
//				          }else{
//				        	  String rec_rlinkman = getValue(rlinkman).trim();
//					          List<ContactArchives> ret_rlinkman = contactArchivesManager.loadByKey("name", rec_rlinkman);
//					          if(ret_rlinkman!=null&&ret_rlinkman.size()>0){
//			        			  rInfo.setContactArchives(ret_rlinkman.get(0));
//			        			  if(ret_rlinkman.get(0).getPhone()!=null){
//			        				  rInfo.setPhone(ret_rlinkman.get(0).getPhone());//设置联系电话 
//			        			  }else{
//			        				 rInfo.setPhone("000000");//如果没有联系电话设定000000 
//			        			  }
//			        			  
//			        		  }
//				          }
//			        	  rInfo.setSum(ret_totalPrice);//收款计划的总价,来自产品明细里面的产品总价
//			        	  String batch = "1";//收款批次
//				          List<CodeValue> code_batch =codeValueManager.loadByKey("name", batch);
//				          if(code_batch!=null&&code_batch.size()>0){
//				        	  rInfo.setBatch(code_batch.get(0));
//		        		  }
//				          String payment = "转账";//收款批次
//				          List<CodeValue> code_payment =codeValueManager.loadByKey("name", payment);
//				          if(code_payment!=null&&code_payment.size()>0){
//				        	  rInfo.setPayment(code_payment.get(0));
//		        		  }
//				          String notOrIs = "0";//是否提醒
//				          rInfo.setNotOrIs(notOrIs);
//				          String isBill = "0";//是否开票?是否到款
//				          rInfo.setIsBill(isBill);
//				          String billingOrNot = "0";//是否开票
//				          rInfo.setBillingOrNot(billingOrNot);
//				          String isOrNot = "1";//是否到款
//				          rInfo.setIsOrNot(isOrNot);
//				          String planState = "待收款";//计划状态
//				          List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
//				          if(code_planState!=null&&code_planState.size()>0){
//				        	  rInfo.setPlanState(code_planState.get(0));
//		        		  }
//				          this.returnPlanManager.storeReturnPlan(rInfo);
//			          }
//			          
//			          
//			          //收款单
//			          
//			          List<ReturnPlan> fPlanList = this.returnPlanManager.loadByKey("contractManagement", cmId);//根据合同ID获取收款计划列表
//			          HSSFCell fInvoiceCode = hssfRow.getCell((short)34);//(收款日期)发票号
//			          
//			          if(fInvoiceCode == null||"".equals(getValue(fInvoiceCode).trim())){
//			        	  ContractManagement cMlInfo = cM.get(0);//获取当前合同信息
//			        	  ReturnPlan rfInfo = fPlanList.get(0);//收款计划
//			        	  double contractMoney = cMlInfo.getContractMoney();//合同金额
//			        	  double paidMoney = cMlInfo.getPaidMoney();//已收金额
//			        	  if(contractMoney > paidMoney){
//			        		//设置合同状态
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
//			        	  }
//			        	  //更新收款单中的计划金额和未收款金额
//			        	  List<FinancialManagement> fcMlist = this.financialManagementManager.loadByKey("contractManagement.id", cMhInfo.getId());
//				          if(fcMlist!=null&&fcMlist.size()>0){
//				        	  for(int i=0;i<fcMlist.size();i++){
//				        		  //计划应收金额更新
//				        		  fcMlist.get(i).setSumReceivable(contractMoney);//更新为合同金额
//				        		  //取出当前循环中的实收金额
//				        		  Double trueSumHl = fcMlist.get(i).getTrueSum();
//				        		  //取出当前循环中的已收金额
//				        		  Double totalSumHl = fcMlist.get(i).getTotalSum();
//				        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
//				        		  fcMlist.get(i).setWithoutGotSum(contractMoney-(trueSumHl+totalSumHl));
//				        		  //更新当前信息
//				        		  this.financialManagementManager.storeFinancialManagement(fcMlist.get(i));
//				        	  }
//				          }
//			        	  //更新开票单中的计划开票金额和未开票金额
//				          List<BillingRecord> billinglist = this.billingRecordManager.loadByKey("contractManagement.id", cMhInfo.getId());
//				          if(billinglist!=null&&billinglist.size()>0){
//				        	  for(int i=0;i<billinglist.size();i++){
//				        		  //计划应收金额更新
//				        		  billinglist.get(i).setPlanSum(contractMoney);//更新为合同金额
//				        		  //取出当前循环中的实收金额
//				        		  Double sumHl = billinglist.get(i).getSum();
//				        		  //取出当前循环中的已收金额
//				        		  Double hasBillSumHl = billinglist.get(i).getHasBillSum();
//				        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
//				        		  billinglist.get(i).setRestSum(contractMoney-(sumHl+hasBillSumHl));
//				        		  //更新当前信息
//				        		  this.billingRecordManager.storeBillingRecord(billinglist.get(i));
//				        	  }
//				          }
//			        	  
//			          }else{
//			        	  String rei_fInvoiceCode = getValue(fInvoiceCode).trim().replace(" ", "");
//			        	  String rec_fInvoiceCode = rei_fInvoiceCode.replace("；", ";");
//			        	  
//			        	  
//			        	  
//				          if(rec_fInvoiceCode.indexOf(";")==-1){
//				        	  //收款单
//				        	  FinancialManagement fInfo = new FinancialManagement();//收款单
//				        	  fInfo.setInvoiceCode(rec_fInvoiceCode);//判断发票编码如果只有一条则设置
//				        	  String datas = rec_fInvoiceCode.split("-")[0];
//				        	  
//				        		  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
//						          Date ret_data = null;
//									try {
//										ret_data = sdf.parse(data);
//									} catch (ParseException e) {
//										e.printStackTrace();
//									}
//									fInfo.setCollectionDate(ret_data);		
//								
//								 String codeF = autoCompleteCode();//收款单编码
//						          fInfo.setCode(codeF);
//						          
//						          fInfo.setContractManagement(cMhInfo);//合同
//						          
//						          fInfo.setIsSaved("1");//是否提交
//						          
//						          fInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
//						          
//						          String collectionType = "收款";//收款类型
//						          List<CodeValue> code_collectionType =codeValueManager.loadByKey("name", collectionType);
//						          if(code_collectionType!=null&&code_collectionType.size()>0){
//						        	  fInfo.setCollectionType(code_collectionType.get(0));
//				        		  }
//						          
//						          String fPayment = "转账";//付款方式
//						          List<CodeValue> code_fPayment =codeValueManager.loadByKey("name", fPayment);
//						          if(code_fPayment!=null&&code_fPayment.size()>0){
//						        	  fInfo.setPayment(code_fPayment.get(0));
//				        		  }
//						          
//						          String fBatch = "1";//收款批次
//						          List<CodeValue> code_fBatch =codeValueManager.loadByKey("name", fBatch);
//						          if(code_fBatch!=null&&code_fBatch.size()>0){
//						        	  fInfo.setBatch(code_fBatch.get(0));
//				        		  }
//						          Double sumReceivable = fPlanList.get(0).getSum();//应收金额等于收款计划总金额
//						          fInfo.setSumReceivable(sumReceivable);
//						          
//						          Double totalSum = fPlanList.get(0).getFactSum();//已收金额等于收款计划中的付款金额
//						          fInfo.setTotalSum(totalSum);
//						          
//						          Double trueSum =0.0D;//实收金额
//						          if(rec_fInvoiceCode.split("-").length!=3){
//						        	  trueSum = sumReceivable;
//						          }else{  
//						        	  trueSum = Double.parseDouble(rec_fInvoiceCode.split("-")[2].trim());
//						          }
//						          fInfo.setTrueSum(trueSum);
//						          //计划总金额-前期已收-本次实收=累积未收金额
//						          Double withoutGotSum = sumReceivable-(totalSum+trueSum);//未收金额
//						        	  fInfo.setWithoutGotSum(withoutGotSum);
//						          
//						          
//						          //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
//						          
//						          fInfo.setPayee(cMhInfo.getSaleman());//收款人取合同负责人
//						          
//						          fInfo.setSaleman(cMhInfo.getSaleman());//收款单业务员
//						          
//						          fInfo.setInvoice("0");//发票状态
//						          
//						          fInfo.setDept(cMhInfo.getDeparment());//部门，获取合同内的部门
//						          this.financialManagementManager.storeFinancialManagement(fInfo);
//						          
//						          List<FinancialManagement> fcMlist = this.financialManagementManager.loadByKey("contractManagement.id", cMhInfo.getId());
//						          if(fcMlist!=null&&fcMlist.size()>0){
//						        	  for(int i=0;i<fcMlist.size();i++){
//						        		  //计划应收金额更新
//						        		  fcMlist.get(i).setSumReceivable(sumReceivable);
//						        		  //取出当前循环中的实收金额
//						        		  Double trueSumHl = fcMlist.get(i).getTrueSum();
//						        		  //取出当前循环中的已收金额
//						        		  Double totalSumHl = fcMlist.get(i).getTotalSum();
//						        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
//						        		  fcMlist.get(i).setWithoutGotSum(sumReceivable-(trueSumHl+totalSumHl));
//						        		  //更新当前信息
//						        		  this.financialManagementManager.storeFinancialManagement(fcMlist.get(i));
//						        	  }
//						          }
//						          
//						          
//						          //开票记录
//						          BillingRecord bInfo = new BillingRecord();//开票记录
//					        	  bInfo.setCode(rec_fInvoiceCode);//发票编码
//					        	  bInfo.setIsSaved("1");//是否提交1提交0否
//					        	  String dataf = rec_fInvoiceCode.split("-")[0];
//					        	  String datax = dataf.substring(0,4)+"-"+dataf.substring(4,6)+"-"+dataf.substring(6,8);
//						          Date ret_datx = null;
//									try {
//										ret_datx = sdf.parse(datax);
//									} catch (ParseException e) {
//										e.printStackTrace();
//									}
//									bInfo.setBillingTime(ret_datx);//开票日期
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
//							        Double hasBillSum = fPlanList.get(0).getFactSum();//已开票金额等于计划中的实际付款金额
//							        bInfo.setHasBillSum(hasBillSum);
//							        
//							        Double sum =0.0D;//本次开票金额
//							          if(rec_fInvoiceCode.split("-").length!=3){
//							        	  sum = planSum;
//							          }else{  
//							        	  sum = Double.parseDouble(rec_fInvoiceCode.split("-")[2].trim());
//							          }
//							          
//							          bInfo.setSum(sum);//本次开票金额
//							          
//							        //计划总金额-前期已收-本次实收=剩下未开开票金额
//							          Double restSumz = planSum-(hasBillSum+sum);//未收金额
////							          Double restSum = 0.0D;
////							          if(restSumz>0){
////							        	  restSum = restSumz;
//							        	  bInfo.setRestSum(restSumz);
////							          }else{
////							        	  bInfo.setRestSum(0.0);
////							          }
//							          
//							          
//							          
//							          ContractManagement cMlInfo = cM.get(0);//获取当前合同信息
//							          double paidlMoney =totalSum+trueSum;//合同的已收金额
////							          if(restSum<0){
////							        	  cMlInfo.setPaidMoney(planSum);
////							          }else{
//							        	  cMlInfo.setPaidMoney(paidlMoney); 
////							          }
//							          
//							          
//							          //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
//							          ReturnPlan rfInfo = fPlanList.get(0);
//							          rfInfo.setFactSum(totalSum+trueSum);
//							          //收款计划中的完成百分比
//							          int percentt = (int) (((totalSum+trueSum)/planSum)*100);
//							          rfInfo.setPercentt(percentt);
//							          //收款计划中的计划状态
//							          if(percentt>=100){
//							        	  //设置收款计划状态
//							        	  String planState = "已完成";//完成状态100则收款计划设置已完成
//							        	  List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
//								          if(code_planState!=null&&code_planState.size()>0){
//								        	  rfInfo.setPlanState(code_planState.get(0));
//						        		  }
//								          String isBill = "1";//是否开票?是否到款
//								          rfInfo.setIsBill(isBill);
//								          String billingOrNot = "1";//是否开票
//								          rfInfo.setBillingOrNot(billingOrNot);
//								          String isOrNot = "0";//是否到款
//								          rfInfo.setIsOrNot(isOrNot);
//								          
//								          //设置合同状态
//								          String state = "已结束";
//								          List<CodeValue> code_state =codeValueManager.loadByKey("name", state);
//								          if(code_state!=null&&code_state.size()>0){
//								        	  cMlInfo.setState(code_state.get(0));
//						        		  }
//								          
//							          }
//							          String islSaved ="1";  //提交 1是0否 设置合同中的提交
//							          cMlInfo.setIsSaved(islSaved);
//							          
//							          List<ProjectInfo> ret_projectl = projectInfoManager.loadByKey("name", cMlInfo.getProject().getName());
//							          if(ret_projectl!=null&&ret_projectl.size()>0){
//							        	  String [] key={"parentCV.name","name"};
//					            		  String [] value={"项目状态","付费"};
//								          List<CodeValue> code_statepl =codeValueManager.loadByKeyArray(key, value);
//								          if(code_statepl!=null&&code_statepl.size()>0){
//								        	  ret_projectl.get(0).setState(code_statepl.get(0));//项目状态
//								        	  ret_projectl.get(0).setIsSaved("1");//设置提交状态
//								          }
//							        	  
//					        		  }
//							          
//							          this.projectInfoManager.storeProjectInfo(ret_projectl.get(0));//更新项目
//							          this.contractManagementManager.storeContractManagement(cMlInfo);//更新合同数据
//							          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
//							        
//							         bInfo.setPayee(cMhInfo.getSaleman());//开票人
//							         
//							         bInfo.setIsPay("0");//是否付款0是1否
//							         
//							         this.billingRecordManager.storeBillingRecord(bInfo);
//							         
//							         
//							         List<BillingRecord> billinglist = this.billingRecordManager.loadByKey("contractManagement.id", cMhInfo.getId());
//							          if(billinglist!=null&&billinglist.size()>0){
//							        	  for(int i=0;i<billinglist.size();i++){
//							        		  //计划应收金额更新
//							        		  billinglist.get(i).setPlanSum(planSum);
//							        		  //取出当前循环中的实收金额
//							        		  Double sumHl = billinglist.get(i).getSum();
//							        		  //取出当前循环中的已收金额
//							        		  Double hasBillSumHl = billinglist.get(i).getHasBillSum();
//							        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
//							        		  billinglist.get(i).setRestSum(planSum-(sumHl+hasBillSumHl));
//							        		  //更新当前信息
//							        		  this.billingRecordManager.storeBillingRecord(billinglist.get(i));
//							        	  }
//							          }
//									
//						          
//				          }else{
//				        	  String [] delis = rec_fInvoiceCode.split(";");//[20150429132-1,20150506145-1...]
//				        	  for(int i=0;i<delis.length;i++){
//				        		  //收款单
//				        		  FinancialManagement fInfo = new FinancialManagement();//收款单
//					        	  String j = delis[i].trim();//获取发货单号，多条发货单，循环设置
//					        	  fInfo.setInvoiceCode(j);//设置发票编码
//					        	  String [] delisx = delis[i].split("-");//[20150429132,1],分开日期与发货价数
//					        	  String datas = delisx[0].trim();//获取日期
//					        	  String data = datas.substring(0,4)+"-"+datas.substring(4,6)+"-"+datas.substring(6,8);
//						          Date ret_data = null;
//									try {
//										ret_data = sdf.parse(data);
//									} catch (ParseException e) {
//										e.printStackTrace();
//									}
//									  fInfo.setCollectionDate(ret_data);
//									
//									  String codeF = autoCompleteCode();//收款单编码
//							          fInfo.setCode(codeF);
//							          
//							          fInfo.setIsSaved("1");//是否提交
//							          
//							          fInfo.setContractManagement(cMhInfo);//合同
//							          
//							          fInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
//							          
//							          String collectionType = "收款";//收款类型
//							          List<CodeValue> code_collectionType =codeValueManager.loadByKey("name", collectionType);
//							          if(code_collectionType!=null&&code_collectionType.size()>0){
//							        	  fInfo.setCollectionType(code_collectionType.get(0));
//					        		  }
//							          
//							          String fPayment = "转账";//付款方式
//							          List<CodeValue> code_fPayment =codeValueManager.loadByKey("name", fPayment);
//							          if(code_fPayment!=null&&code_fPayment.size()>0){
//							        	  fInfo.setPayment(code_fPayment.get(0));
//					        		  }
//							          
//							          String fBatch = "1";//收款批次
//							          List<CodeValue> code_fBatch =codeValueManager.loadByKey("name", fBatch);
//							          if(code_fBatch!=null&&code_fBatch.size()>0){
//							        	  fInfo.setBatch(code_fBatch.get(0));
//					        		  }
//							          
//							          Double sumReceivable = fPlanList.get(0).getSum();//应收金额等于收款计划总金额
//							          fInfo.setSumReceivable(sumReceivable);
//							          
//							          Double totalSum = fPlanList.get(0).getFactSum();//已收金额等于收款计划中的付款金额
//							          fInfo.setTotalSum(totalSum);
//							          
//							          Double trueSum =0.0D;//实收金额
//							          if(delisx.length!=3){
//							        	  trueSum = sumReceivable;
//							          }else{  
//							        	  trueSum = Double.parseDouble(delisx[2].trim());
//							          }
//							          fInfo.setTrueSum(trueSum);
//							          
//							        //计划总金额-前期已收-本次实收=累积未收金额
//							          Double withoutGotSum = sumReceivable-(totalSum+trueSum);//未收金额
//							        	  fInfo.setWithoutGotSum(withoutGotSum);
//							          //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
//							          
//							          fInfo.setPayee(cMhInfo.getSaleman());//收款人取合同负责人
//							          
//							          fInfo.setSaleman(cMhInfo.getSaleman());//收款单业务员
//							          
//							          fInfo.setInvoice("0");//发票状态
//							          
//							          fInfo.setDept(cMhInfo.getDeparment());//部门，获取合同内的部门
//									
//							          this.financialManagementManager.storeFinancialManagement(fInfo);
//							          
//							          List<FinancialManagement> fcMlist = this.financialManagementManager.loadByKey("contractManagement.id", cMhInfo.getId());
//							          if(fcMlist!=null&&fcMlist.size()>0){
//							        	  for(int k=0;k<fcMlist.size();k++){
//							        		  //计划应收金额更新
//							        		  fcMlist.get(k).setSumReceivable(sumReceivable);
//							        		  //取出当前循环中的实收金额
//							        		  Double trueSumHl = fcMlist.get(k).getTrueSum();
//							        		  //取出当前循环中的已收金额
//							        		  Double totalSumHl = fcMlist.get(k).getTotalSum();
//							        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
//							        		  fcMlist.get(k).setWithoutGotSum(sumReceivable-(trueSumHl+totalSumHl));
//							        		  //更新当前信息
//							        		  this.financialManagementManager.storeFinancialManagement(fcMlist.get(k));
//							        	  }
//							          }
//							          
//							          //开票记录
//							          BillingRecord bInfo = new BillingRecord();//开票记录
//					        		  String f = delis[i].trim();//获取发货单号，多条发货单，循环设置
//						        	  bInfo.setCode(f);//设置发票编码
//						        	  bInfo.setIsSaved("1");//是否提交1提交0否
//						        	  String [] delisf = delis[i].split("-");//[20150429132,1],分开日期与发货价数
//						        	  String dataf = delisf[0].trim();//获取日期
//						        	  
//						        	  String datafz = dataf.substring(0,4)+"-"+dataf.substring(4,6)+"-"+dataf.substring(6,8);
//							          Date ret_dataf = null;
//										try {
//											ret_dataf = sdf.parse(datafz);
//										} catch (ParseException e) {
//											e.printStackTrace();
//										}
//										bInfo.setBillingTime(ret_dataf);//开票日期
//										
//										String myCode = autoCompleteCodeb();//开票单编码
//										bInfo.setMyCode(myCode);
//										
//										bInfo.setContractManagement(cMhInfo);//合同
//										
//										bInfo.setCustomerInfo(cMhInfo.getCustomerInfo());//客户信息
//										
//										bInfo.setContactArchives(cMhInfo.getLinkman());//联系人
//										
//										String bBatch = "1";//收款批次
//								          List<CodeValue> code_bBatch =codeValueManager.loadByKey("name", bBatch);
//								          if(code_bBatch!=null&&code_bBatch.size()>0){
//								        	  bInfo.setBatch(code_bBatch.get(0));
//						        		  }
//								        
//								        Double planSum = fPlanList.get(0).getSum();//计划开票金额
//								        bInfo.setPlanSum(planSum);
//								        
//								        Double hasBillSum = fPlanList.get(0).getFactSum();//已开票金额等于计划中的实际付款金额
//								        bInfo.setHasBillSum(hasBillSum);
//								        
//								        Double sum =0.0D;//本次开票金额
//								          if(j.split("-").length!=3){
//								        	  sum = planSum;
//								          }else{  
//								        	  sum = Double.parseDouble(j.split("-")[2].trim());
//								          }
//								          bInfo.setSum(sum);//本次开票金额
//								          
//								        //计划总金额-前期已收-本次实收=剩下未开开票金额
//								          Double restSum = planSum-(hasBillSum+sum);//未收金额
////								          if(restSum>0){
//								        	  bInfo.setRestSum(restSum);
////								          }else{
////								        	  bInfo.setRestSum(0.0);
////								          }
//								          
//								         
//								          ContractManagement cMlInfo = cM.get(0);//获取当前合同信息
//								          double paidlMoney =totalSum+trueSum;//合同的已收金额
////								          if(restSum<0){
////								        	  cMlInfo.setPaidMoney(planSum);
////								          }else{
//								        	  cMlInfo.setPaidMoney(paidlMoney);
////								          }
//								          
//								          
//								        //收款计划中的付款金额=收款单中的实收金额+收款计划之前的已收金额
//								          ReturnPlan rfInfo = fPlanList.get(0);
//								          rfInfo.setFactSum(totalSum+trueSum);
//								        //收款计划中的完成百分比
//								          int percentt = (int) (((totalSum+trueSum)/planSum)*100);
//								          rfInfo.setPercentt(percentt);
//								          //收款计划中的计划状态
//								          if(percentt>=100){
//								        	  String planState = "已完成";//完成状态100则收款计划设置已完成
//								        	  List<CodeValue> code_planState =codeValueManager.loadByKey("name", planState);
//									          if(code_planState!=null&&code_planState.size()>0){
//									        	  rfInfo.setPlanState(code_planState.get(0));
//							        		  }
//									          String isBill = "1";//是否开票?是否到款
//									          rfInfo.setIsBill(isBill);
//									          String billingOrNot = "1";//是否开票
//									          rfInfo.setBillingOrNot(billingOrNot);
//									          String isOrNot = "0";//是否到款
//									          rfInfo.setIsOrNot(isOrNot);
//									          
//									        //设置合同状态
//									          String state = "已结束";
//									          List<CodeValue> code_state =codeValueManager.loadByKey("name", state);
//									          if(code_state!=null&&code_state.size()>0){
//									        	  cMlInfo.setState(code_state.get(0));
//							        		  }
//								          }
//								          
//								          String islSaved ="1";  //提交 1是0否 设置合同中的提交
//								          cMlInfo.setIsSaved(islSaved);
//								          
//	               		          
//								          //更新项目中的信息
//								          List<ProjectInfo> ret_projectl = projectInfoManager.loadByKey("name", cMlInfo.getProject().getName());
//								          if(ret_projectl!=null&&ret_projectl.size()>0){
//								        	  String [] key={"parentCV.name","name"};
//						            		  String [] value={"项目状态","付费"};
//									          List<CodeValue> code_statepl =codeValueManager.loadByKeyArray(key, value);
//									          if(code_statepl!=null&&code_statepl.size()>0){
//									        	  ret_projectl.get(0).setState(code_statepl.get(0));//项目状态
//									        	  ret_projectl.get(0).setIsSaved("1");//设置提交状态
//									          }
//								        	  
//						        		  }
//								          
//								          this.projectInfoManager.storeProjectInfo(ret_projectl.get(0));//更新项目
//								          this.contractManagementManager.storeContractManagement(cMlInfo);//更新合同数据
//								          this.returnPlanManager.storeReturnPlan(rfInfo);//更新收款计划中的已收金额
//								          
//								         bInfo.setPayee(cMhInfo.getSaleman()); //开票人
//								         
//								         bInfo.setIsPay("0");//是否付款0是1否
//								         
//								         this.billingRecordManager.storeBillingRecord(bInfo);
//								         
//								         List<BillingRecord> billinglist = this.billingRecordManager.loadByKey("contractManagement.id", cMhInfo.getId());
//								          if(billinglist!=null&&billinglist.size()>0){
//								        	  for(int k=0;k<billinglist.size();k++){
//								        		  //计划应收金额更新
//								        		  billinglist.get(k).setPlanSum(planSum);
//								        		  //取出当前循环中的实收金额
//								        		  Double sumHl = billinglist.get(k).getSum();
//								        		  //取出当前循环中的已收金额
//								        		  Double hasBillSumHl = billinglist.get(k).getHasBillSum();
//								        		  //当前的计划应收金额-循环当中的实收金额 -循环中的已收金额=循环中的未收金额
//								        		  billinglist.get(k).setRestSum(planSum-(sumHl+hasBillSumHl));
//								        		  //更新当前信息
//								        		  this.billingRecordManager.storeBillingRecord(billinglist.get(k));
//								        	  }
//								          }
//					          }
//				          }
//			          }
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
		}
		
		
		
/*     */ 

