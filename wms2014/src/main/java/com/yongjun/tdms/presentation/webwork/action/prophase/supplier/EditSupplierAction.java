package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.country.CountryManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierManager;
import com.yongjun.tdms.workflow.model.job.Job;
import com.yongjun.tdms.workflow.service.approver.WfDocApproverManager;
import com.yongjun.tdms.workflow.service.job.WfJobManager;
import com.yongjun.tdms.model.SysModel;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.base.codevalue.CodeValue;
import com.yongjun.tdms.model.base.country.Country;
import com.yongjun.tdms.model.prophase.supplier.Supplier;
import com.yongjun.tdms.model.prophase.supplier.SupplierLevelHistory;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointProc;

/**
 * @author qs
 * @version $Id: EditSupplierAction.java 11195 2008-03-05 00:50:36Z mwei $
 */
public class EditSupplierAction extends PrepareAction {
	private static final long serialVersionUID = 4536180973555835567L;
	private final Log log = LogFactory.getLog(getClass());
	private final SupplierManager supplierManager;
	private final CodeValueManager codeValueManager;
	private final CountryManager countryManager;
	private final UserManager userManager;
	private final WfDocApproverManager wfDocApproverManager;
	private final WfJobManager wfJobManager;
	private final SequenceManager sequenceManager;
	private Supplier supplier;   //供应商 对象
	private CodeValue companyType;  //公司类别
	private CodeValue supplierType; //供应商类别
	private CodeValue tradeType;    //行业类别
	private Country country;        //国家对象
	private CodeValue level;        //供应商等级
	private String toolingDevFlag;  //[工装] [设备]标示 
	private SupplierLevelHistory supplierLevelHistory;  //供应商等级历史
	public EditSupplierAction(SupplierManager supplierManager,
			WfDocApproverManager wfDocApproverManager,
			WfJobManager wfJobManager, CodeValueManager codeValueManager,
			CountryManager countryManager, UserManager userManager,SequenceManager sequenceManager) {
		this.supplierManager = supplierManager;
		this.wfDocApproverManager = wfDocApproverManager;
		this.wfJobManager = wfJobManager;
		this.codeValueManager = codeValueManager;
		this.countryManager = countryManager;
		this.userManager = userManager;
		this.sequenceManager = sequenceManager;
	}

	public void prepare() throws Exception {
		if (null == this.supplier) {
			if (this.hasId("supplier.id")) {
				this.supplier = this.supplierManager.loadSupplier(this
						.getId("supplier.id"));
				this.companyType=supplier.getCompanyType();
				this.country=supplier.getCountry();
				this.supplierType=supplier.getSupplierType();
				this.tradeType=supplier.getTradeType();
				this.level=supplier.getLevel();
			} else {
				this.supplier = new Supplier();
			}
			if (this.hasId("toolingDevFlag")) {
				this.toolingDevFlag = request.getParameter("toolingDevFlag");
			}
		}

		if (null == this.supplierLevelHistory) {
			this.supplierLevelHistory = new SupplierLevelHistory();
		}
		if (!StringUtils.isEmpty(request.getParameter("companyType.id"))) {//获得公司类别
			this.companyType = this.codeValueManager.loadCodeValue(this
					.getId("companyType.id"));
			supplier.setCompanyType(companyType);
		}
		if (!StringUtils.isEmpty(request.getParameter("supplierType.id"))) {//获得供应商类别
			this.supplierType = this.codeValueManager.loadCodeValue(this
					.getId("supplierType.id"));
			supplier.setSupplierType(supplierType);
		}
		if (!StringUtils.isEmpty(request.getParameter("level.id"))) {//获得供应商等级
			this.level = this.codeValueManager.loadCodeValue(this
					.getId("level.id"));
			supplier.setLevel(level);
		}
		
		if (!StringUtils.isEmpty(request.getParameter("tradeType.id"))) {//获得行业类别
			this.tradeType = this.codeValueManager.loadCodeValue(this
					.getId("tradeType.id"));
			supplier.setTradeType(tradeType);
		}
		if (!StringUtils.isEmpty(request.getParameter("country.id"))) {//获得国家对象
			this.country = this.countryManager.loadCountry(this
					.getId("country.id"));
			supplier.setCountry(country);
		}

	}

	public String delete() {
		try {
			this.supplierManager.deleteSupplier(supplier);
		} catch (Exception e) {
			this.addActionMessage(this.getText("checkPointProc.delete.error",
					Arrays.asList(new Object[] { supplier.getName() })));
			return SUCCESS;
		}
		this.addActionMessage(this.getText("supplier.delete.success", Arrays
				.asList(new Object[] { supplier.getName() })));
		return SUCCESS;
	}

	public String execute() throws Exception {

		return SUCCESS;
	}


	public String save() {
		if (this.isDelete()) {
			return this.delete();
		}
		boolean isNew = this.supplier.isNew();
		if (toolingDevFlag.equals(SysModel.DEVICE.toString())) {
			supplier.setToolingDevFlag(SysModel.DEVICE);
		} else {
			supplier.setToolingDevFlag(SysModel.TOOLING);
		}
		supplier.setCategory("SUPPLIER"); //标识类别是“供应商”
		if(isNew){
			String supplierNo = (String) sequenceManager.generate("-");
			supplier.setSupplierNo(supplierNo);
		}
		this.supplierManager.storeSupplier(supplier);

		if (isNew) {
			this.addActionMessage(this.getText("supplier.add.success", Arrays
					.asList(new Object[] { supplier.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("supplier.edit.success", Arrays
					.asList(new Object[] { supplier.getName() })));
			return SUCCESS;
		}
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<User> getDocFinalApprovers() {
		return wfDocApproverManager.loadAllFinalApprovers(CheckPointProc.class);
	}

	public Long getFinalApprover() {
		return wfJobManager.getJobFinalApproverId(supplier);
	}

	public Job getJob() {
		return wfJobManager.getJob(supplier);
	}

	public boolean isDocSubmitted() {
		if (supplier.isNew()) {
			return false;
		}
		return wfJobManager.isJobSubmitted(supplier);
	}

	public String submitDoc() {
		log.debug("begin submitDoc...");
		Long[] ids = null;
		if (this.hasIds("approverIds")) {
			ids = getIds("approverIds");
			for (int i = 0; i < ids.length; i++) {
				log.debug("id is : " + ids[i]);
			}
		}
		Long finalId = null;
		finalId = this.getId("finalApproverId");

		String comment = request.getParameter("approv.comment");
		try {
			Job job = this.wfJobManager.submitJob(supplier, ids, finalId,
					comment, supplier.getSupplierNo(), supplier.getName());
			supplier.setJob(job);
			supplierManager.storeSupplier(supplier);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

//	private boolean isCancelSubmittedDoc() {
//		return hasKey("cancelSubmitDoc");
//	}
//
//	private boolean isSubmitDoc() {
//		return hasKey("submitDoc");
//	}



	public List getSupplierCatory() {
		return codeValueManager
				.LoadAllValuesByCode(CodeConstants.SUPPLIER_TYPE);
	}

	public List getSupplierTrade() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.TRADE_TYPE);
	}
   //获得公司性质
	public List getSupplierComapanyProperty() {
		return codeValueManager.LoadAllValuesByCode(CodeConstants.COMPANY_TYPE);
	}
   //获得供应商国家
	public List getSupplierCountry() {
		return countryManager.loadAllCountries();
	}
   
  //获得供应商等级
   public List getSupplierLevel(){
	   return  codeValueManager.LoadAllValuesByCode(CodeConstants.SUPPLIER_LEVEL);
	   
   }

	public SupplierLevelHistory getSupplierLevelHistory() {
		return supplierLevelHistory;
	}

	public void setSupplierLevelHistory(
			SupplierLevelHistory supplierLevelHistory) {
		this.supplierLevelHistory = supplierLevelHistory;
	}

	public CodeValue getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CodeValue companyType) {
		this.companyType = companyType;
	}

	public CodeValue getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(CodeValue supplierType) {
		this.supplierType = supplierType;
	}

	public CodeValue getTradeType() {
		return tradeType;
	}

	public void setTradeType(CodeValue tradeType) {
		this.tradeType = tradeType;
	}


	public void setCountry(Country country) {
		this.country = country;
	}

	public CodeValue getLevel() {
		return level;
	}

	public void setLevel(CodeValue level) {
		this.level = level;
	}

	public String getToolingDevFlag() {
		return toolingDevFlag;
	}

	public void setToolingDevFlag(String toolingDevFlag) {
		this.toolingDevFlag = toolingDevFlag;
	}


	

}
