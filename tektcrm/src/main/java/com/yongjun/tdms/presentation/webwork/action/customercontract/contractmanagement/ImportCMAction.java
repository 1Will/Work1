package com.yongjun.tdms.presentation.webwork.action.customercontract.contractmanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.csvreader.CsvReader;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.presentation.webwork.FileTransportAction;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.duty.Duty;
import com.yongjun.tdms.model.base.products.Products;
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
import com.yongjun.tdms.model.project.projectInfoPlan.ProjectInfoPlan;
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
import com.yongjun.tdms.service.productionOperation.productionOperationDetail.ProductionOperationDetailManager;
import com.yongjun.tdms.service.project.ProjectInfoManager;
import com.yongjun.tdms.service.project.projectInfoContract.ProjectInfoContractManager;
import com.yongjun.tdms.service.project.projectInfoCustomer.ProjectInfoCustomerManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
import com.yongjun.tdms.service.project.projectInfoPlan.ProjectInfoPlanManager;

public class ImportCMAction extends FileTransportAction {
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
	private final ProductionOperationDetailManager productionOperationDetailManager;
	private final ProjectInfoPlanManager projectInfoPlanManager;
	private static final long serialVersionUID = -877987215782827292L;
	private StringBuffer message = new StringBuffer();
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public void prepare() throws Exception {

	}

	public ImportCMAction(ProductsManager productsManager, CodeValueManager codeValueManager, AreaManager areaManager,
			UserManager userManager, PersonnelFilesManager personnelFilesManager,
			ProductTypeManager productTypeManager, InstitutionManager institutionManager,
			DepartmentManager departmentManager, DutyManager dutyManager, CustomerInfoManager customerInfoManager,
			ProjectInfoManager projectInfoManager, ContactArchivesManager contactArchivesManager,
			ContractManagementManager contractManagementManager, ProductListManager productListManager,
			ReturnPlanManager returnPlanManager, FinancialManagementManager financialManagementManager,
			BillingRecordManager billingRecordManager, InvoicesManager invoicesManager,
			ProjectInfoCustomerManager projectInfoCustomerManager,ProjectInfoContractManager projectInfoContractManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager, InvoicesListManager invoicesListManager,
			ProductionOperationDetailManager productionOperationDetailManager,ProjectInfoPlanManager projectInfoPlanManager) {
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
		this.invoicesListManager = invoicesListManager;
		this.productionOperationDetailManager = productionOperationDetailManager;
		this.projectInfoPlanManager = projectInfoPlanManager;
	}

	public String save() {
		try {
			File file = getFile();// 获取文件
			InputStream is = new FileInputStream(file);// 转化为流
			CsvReader csvReader = new CsvReader(is, Charset.forName("GBK"));
			
			List<List> data = new ArrayList<List>();
			List<List<String>> obj = new ArrayList<List <String>>();
			int n = 1;
	        while(csvReader.readRecord()){ //逐行读入除表头的数据      
	        	String [] line = csvReader.getValues();
	        	if(line.length == 1){
	        		if (n!=1) {
						data.add(obj);
						obj = new ArrayList<List <String>>();
						obj.add(Arrays.asList(line));
					}else {
						obj.add(Arrays.asList(line));
					}
	        	}else {
					obj.add(Arrays.asList(line));
				}
				n++;
			}
			dealData(data);
		} catch (Exception e) {
			e.printStackTrace();
			message.append("导入有误！");
			return "error";
		}
		message.append("导入成功！");
		return "success";
	}
	
	public void dealData(List<List> data) throws DaoException, ParseException {
		if(data != null){
			for(int i =0;i<data.size();i++){
				if(data.get(i) != null && data.get(i).size()>2){
//					for (int j = 0; j < data.get(i).size(); j++) {
//						System.out.println("数据："+(data.get(i).get(j).toString()));
//					}
					insertData(data.get(i));
				}
			}
		}
	}
	/**
	 * 选择插入数据
	 * @param arr 单个表的数据组
	 * @throws DaoException
	 * @throws ParseException
	 */
	public void insertData(List<List <String>> arr) throws DaoException, ParseException{
		if("ContractManagement".equals(arr.get(0).get(0))){
			for (int i = 2; i < arr.size(); i++) {
				List<ContractManagement> cms = this.contractManagementManager.loadByKey("code", arr.get(i).get(0));
				ContractManagement  cm = null;
				if (cms != null && cms.size() >0) {
					cm = cms.get(0);
					setCMproperty(cm,arr.get(i));
				}else {
					cm = new ContractManagement();
					setCMproperty(cm,arr.get(i));
				}
				this.contractManagementManager.storeContractManagement(cm);
			}
		}
		
		if("ProductList".equals(arr.get(0).get(0))){
			for (int i = 2; i < arr.size(); i++) {
				ProductList pl = null;
				if("1".equals(arr.get(i).get(14))){
					List<ProductList> pls = this.productListManager.loadByKeyArray(new String[]{"contractManagement.code","createdTime"},new Object[]{arr.get(i).get(0) , format.parse(arr.get(i).get(9)) });
					if (pls != null) {
						pl = pls.get(0);
						setPLproperty(pl, arr.get(i));
					}else {
						message.append("ProductList存在不存在的修改项<br/>");
					}
				}else if("2".equals(arr.get(i).get(14))){
					pl = new ProductList();
					setPLproperty(pl, arr.get(i));
				}
				if (pl != null) {
					this.productListManager.storeProductList(pl);
				}
			}
		}
		
		if("ReturnPlan".equals(arr.get(0).get(0))){
			for (int i = 2; i < arr.size(); i++) {
				ReturnPlan rp = null;
				if("1".equals(arr.get(i).get(23))){
					List<ReturnPlan> rps = this.returnPlanManager.loadByKeyArray(new String[]{"contractManagement.code","createdTime"},new Object[]{arr.get(i).get(3) , format.parse(arr.get(i).get(14)) });
					if (rps  != null) {
						rp = rps.get(0);
						setRPproperty(rp, arr.get(i));
					}else {
						message.append("ReturnPlan存在不存在的修改项<br/>");
					}
				}else if("2".equals(arr.get(i).get(23))){
					rp = new ReturnPlan();
					setRPproperty(rp, arr.get(i));
				}
				if (rp != null) {
					this.returnPlanManager.storeReturnPlan(rp);
				}
			}
		}
		
		if("BillingRecord".equals(arr.get(0).get(0))){
			for (int i = 2; i < arr.size(); i++) {
				BillingRecord br = null;
				if("1".equals(arr.get(i).get(22))){
					List<BillingRecord> brs = this.billingRecordManager.loadByKeyArray(new String[]{"contractManagement.code","createdTime"},new Object[]{arr.get(i).get(2) , format.parse(arr.get(i).get(9)) });
					if (brs != null) {
						br = brs.get(0);
						setBRproperty(br, arr.get(i));
					}else {
						message.append("BillingRecord存在不存在的修改项<br/>");
					}
				}else if("2".equals(arr.get(i).get(22))){
					br = new BillingRecord();
					setBRproperty(br, arr.get(i));
				}
				if (br != null) {
					this.billingRecordManager.storeBillingRecord(br);
				}
			}
		}
		
		if("FinancialManagement".equals(arr.get(0).get(0))){
			for (int i = 2; i < arr.size(); i++) {
				FinancialManagement fm = null;
				if("1".equals(arr.get(i).get(26))){
					List<FinancialManagement> fms = this.financialManagementManager.loadByKeyArray(new String[]{"contractManagement.code","createdTime"},new Object[]{arr.get(i).get(1) , format.parse(arr.get(i).get(19)) });
					if (fms != null) {
						fm = fms.get(0);
						setFMproperty(fm, arr.get(i));
					}else {
						message.append("FinancialManagement有不存在的修改项<br/>");
					}
				}else if("2".equals(arr.get(i).get(26))){
					fm = new FinancialManagement();
					setFMproperty(fm, arr.get(i));
				}
				if (fm != null) {
					this.financialManagementManager.storeFinancialManagement(fm);
				}
			}
		}
		
		if("ProjectInfoPlan".equals(arr.get(0).get(0))){
			for (int i = 2; i < arr.size(); i++) {
				ProjectInfoPlan pp = null;
				if("1".equals(arr.get(i).get(21))){
					List<ProjectInfoPlan> pps = this.projectInfoPlanManager.loadByKey("createdTime",format.parse(arr.get(i).get(7)) );
					if (pps != null) {
						pp = pps.get(0);
						setPPproperty(pp, arr.get(i));
					}else {
						message.append("ProjectInfoPlan存在不存在的修改项<br/>");
					}
				}else if("2".equals(arr.get(i).get(21))){
					pp = new ProjectInfoPlan();
					setPPproperty(pp, arr.get(i));
				}
				if (pp != null) {
					this.projectInfoPlanManager.storeProjectInfoPlan(pp);
				}
			}
		}
	}
	
	/**
	 * 设置合同数据
	 * @param cm 要修改的合同
	 * @param properties 需要装入的数据
	 * @return
	 * @throws ParseException
	 */
	public ContractManagement setCMproperty(ContractManagement cm, List <String> properties) throws ParseException{
		int i = 0;
		cm.setCode(properties.get(i++));
		cm.setContractName(properties.get(i++));
		if(!"".equals(properties.get(i))){
			cm.setCustomerInfo(customerInfoManager.loadCustomerInfo(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setLinkman(contactArchivesManager.loadContactArchives(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		cm.setAddress(properties.get(i++));
		cm.setTelephone(properties.get(i++));
		if(!"".equals(properties.get(i))){
			cm.setSaleman(personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setDeparment(departmentManager.loadDepartment(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setCiemdinghTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setStartTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setEndTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setContractType(codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setContractMoney(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setPaidMoney(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setMoneyType(codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setPayType(codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setState(codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		cm.setContractContent(properties.get(i++));
		cm.setRemark(properties.get(i++));
		if(!"".equals(properties.get(i))){
			cm.setDisabled(Boolean.parseBoolean(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setCreatedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		cm.setOverGet(properties.get(i++));
		cm.setOverReturnPlan(properties.get(i++));
		if(!"".equals(properties.get(i))){
			cm.setProject(projectInfoManager.loadProjectInfo(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		cm.setIsSaved(properties.get(i++));
		if(!"".equals(properties.get(i))){
			cm.setSubmitNum(Long.parseLong(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			cm.setLastSubmitMoney(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		cm.setTwoCFourF(properties.get(i++));
		cm.setReceipt(properties.get(i++));
		cm.setBack(properties.get(i++));
		if(!"".equals(properties.get(i))){
			cm.setHasBillSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		return cm;
	}
	/**
	 * 设置合同产品列表数据
	 * @param pl  要修改的ProductList
	 * @param properties 需要装入的数据
	 * @return
	 * @throws ParseException
	 */
	public ProductList setPLproperty(ProductList pl, List <String> properties) throws ParseException, DaoException{
		int i = 0;
		if(!"".equals(properties.get(i))){
			pl.setContractManagement(this.contractManagementManager.loadByKey("code", properties.get(i++)).get(0));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setProduct(productsManager.loadProducts(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setUnit(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setCount(Integer.parseInt(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setUnitPrice(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setDiscount(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setTotalPrice(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		pl.setRemark(properties.get(i++));
		if(!"".equals(properties.get(i))){
			pl.setDisabled(Boolean.parseBoolean(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setCreatedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setLastModifiedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setPlannedDeliveryDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setQualityControl(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pl.setDeliveryedCount(Integer.parseInt(properties.get(i++)));
		}else {
			i++;
		}
		pl.setIsExport(properties.get(i++));
		return pl;
	}

	/**
	 * 收款计划属性设置
	 * @param rp 要设置属性的收款计划
	 * @param properties 属性值
	 * @return
	 * @throws ParseException
	 * @throws DaoException
	 */
	public ReturnPlan setRPproperty(ReturnPlan rp, List <String> properties) throws ParseException, DaoException{
		int i = 0;
		if(!"".equals(properties.get(i))){
			rp.setPayee(this.personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setCustomerInfo(this.customerInfoManager.loadCustomerInfo(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setContactArchives(this.contactArchivesManager.loadContactArchives(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setContractManagement(this.contractManagementManager.loadByKey("code", properties.get(i++)).get(0));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setPaytime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setPlanDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setPayment(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setBatch(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setFactSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		rp.setIsOrNot(properties.get(i++));
		rp.setNotOrIs(properties.get(i++));
		rp.setBillingOrNot(properties.get(i++));
		if(!"".equals(properties.get(i))){
			rp.setDisabled(Boolean.parseBoolean(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setCreatedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setLastModifiedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		rp.setRemark(properties.get(i++));
		if(!"".equals(properties.get(i))){
			rp.setChargMan(this.personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		rp.setIsBill(properties.get(i++));
		if(!"".equals(properties.get(i))){
			rp.setPercentt(Integer.parseInt(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setPlanState(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setBillMoney(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			rp.setBillDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		rp.setIsExport(properties.get(i++));
		return rp;
	}
	
	/**
	 * 开票记录属性设置
	 * @param br 要设置属性的开票记录
	 * @param properties 属性值
	 * @return
	 * @throws ParseException
	 * @throws DaoException
	 */
	public BillingRecord setBRproperty(BillingRecord br, List <String> properties) throws ParseException, DaoException{
		int i = 0;
		if(!"".equals(properties.get(i))){
			br.setPayee(this.personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setCustomerInfo(this.customerInfoManager.loadCustomerInfo(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setContractManagement(this.contractManagementManager.loadByKey("code", properties.get(i++)).get(0));
		}else {
			i++;
		}
		br.setCode(properties.get(i++));
		br.setInvoiceTitle(properties.get(i++));
		if(!"".equals(properties.get(i))){
			br.setBillingTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		br.setContent(properties.get(i++));
		if(!"".equals(properties.get(i))){
			br.setDisabled(Boolean.parseBoolean(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setCreatedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setLastModifiedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setContactArchives(this.contactArchivesManager.loadContactArchives(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setHasBillSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setPlanSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setRestSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setBatch(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		br.setIsSaved(properties.get(i++));
		if(!"".equals(properties.get(i))){
			br.setSubmitNum(Long.parseLong(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			br.setLastSubmitMoney(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		br.setIsPay(properties.get(i++));
		br.setPayCode(properties.get(i++));
		br.setMyCode(properties.get(i++));
		br.setIsExport(properties.get(i++));
		return br;
	}
	
	/**
	 * 开票记录属性设置
	 * @param br 要设置属性的开票记录
	 * @param properties 属性值
	 * @return
	 * @throws ParseException
	 * @throws DaoException
	 */
	public FinancialManagement setFMproperty(FinancialManagement fm, List <String> properties) throws ParseException, DaoException{
		int i = 0;
		fm.setCode(properties.get(i++));
		if(!"".equals(properties.get(i))){
			fm.setContractManagement(this.contractManagementManager.loadByKey("code", properties.get(i++)).get(0));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setCustomerInfo(this.customerInfoManager.loadCustomerInfo(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setSaleman(this.personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setCollectionType(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setPayment(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		fm.setAccountNumber(properties.get(i++));
		if(!"".equals(properties.get(i))){
			fm.setBatch(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setSumReceivable(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setTrueSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setTotalSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setWithoutGotSum(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		fm.setInvoice(properties.get(i++));
		fm.setInvoiceCode(properties.get(i++));
		if(!"".equals(properties.get(i))){
			fm.setCollectionDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setPayee(this.personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setDept(this.departmentManager.loadDepartment(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		fm.setRemark(properties.get(i++));
		if(!"".equals(properties.get(i))){
			fm.setDisabled(Boolean.parseBoolean(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setCreatedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			fm.setLastModifiedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		fm.setAccountName(properties.get(i++));
		fm.setIsSaved(properties.get(i++));
		fm.setPayNumber(properties.get(i++));
		fm.setSubmitNum(Long.parseLong(properties.get(i++)));        
		if(!"".equals(properties.get(i))){
			fm.setLastSubmitMoney(Double.parseDouble(properties.get(i++)));
		}else {
			i++;
		}
		fm.setIsExport(properties.get(i++));
		return fm;
	}
	
	
	/**
	 * 开票记录属性设置
	 * @param br 要设置属性的开票记录
	 * @param properties 属性值
	 * @return
	 * @throws ParseException
	 * @throws DaoException
	 */
	public ProjectInfoPlan setPPproperty(ProjectInfoPlan pp, List <String> properties) throws ParseException, DaoException{
		int i = 0;
		pp.setOutline(properties.get(i++));
		pp.setName(properties.get(i++));
		if(!"".equals(properties.get(i))){
			pp.setPersonnelFiles(this.personnelFilesManager.loadPersonnel(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		pp.setAssist(properties.get(i++));
		if(!"".equals(properties.get(i))){
			pp.setStartDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setEndDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setDisabled(Boolean.parseBoolean(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setCreatedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setLastModifiedTime(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setPercentt(Integer.parseInt(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setPriority(Integer.parseInt(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setContractManagement(this.contractManagementManager.loadByKey("code", properties.get(i++)).get(0));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setPlanState(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		pp.setIsSaved(properties.get(i++));
		if(!"".equals(properties.get(i))){
			pp.setStartFactDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setEndFactDate(format.parse(properties.get(i++)));
		}else {
			i++;
		}
		pp.setAssistIds(properties.get(i++));
		
		if(!"".equals(properties.get(i))){
			List<ProjectInfoPlan> pps = this.projectInfoPlanManager.loadByKey("createdTime", format.parse(properties.get(i++)));
			if (pps!=null) {
				pp.setProjectInfoPlan_2(pps.get(0));
			}
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setOrderNumber(Integer.parseInt(properties.get(i++)));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setProductionOperationDetail(productionOperationDetailManager.loadProductionOperationDetail(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		if(!"".equals(properties.get(i))){
			pp.setProductionPoint(this.codeValueManager.loadCodeValue(Long.parseLong(properties.get(i++))));
		}else {
			i++;
		}
		pp.setIsExport(properties.get(i++));
		return pp;
	}
	
	public String getMessage() {
		return message.toString();
	}

	public void setMessage(StringBuffer message) {
		this.message = message;
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
