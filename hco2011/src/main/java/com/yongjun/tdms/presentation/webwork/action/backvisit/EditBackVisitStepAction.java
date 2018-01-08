package com.yongjun.tdms.presentation.webwork.action.backvisit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class EditBackVisitStepAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CodeValueManager codeValueManager;
	private final BackVisitManager backVisitManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final CustomerInfoManager customerInfoManager;
	private final ContactArchivesManager contactArchivesManager;
	private final UserManager userManager;
	private BackVisit backVisit;
	private StepInfo stepInfo;
	private PersonnelFiles personnelFiles;
	private CodeValue tempSteped;
	private CodeValue tempSteping;

	public CodeValue getTempSteped() {
		return tempSteped;
	}

	public void setTempSteped(CodeValue tempSteped) {
		this.tempSteped = tempSteped;
	}

	public EditBackVisitStepAction(CodeValueManager codeValueManager, BackVisitManager backVisitManager,
			PersonnelFilesManager personnelFilesManager, CustomerInfoManager customerInfoManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager) {
		this.codeValueManager = codeValueManager;
		this.backVisitManager = backVisitManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.contactArchivesManager = contactArchivesManager;
		this.userManager = userManager;
	}

	public BackVisit getBackVisit() {
		return this.backVisit;
	}

	public void setBackVisit(BackVisit backVisit) {
		this.backVisit = backVisit;
	}

	public void prepare() throws Exception {
		if (this.backVisit == null)
			if (hasId("backVisit.id")) {
				this.backVisit = this.backVisitManager.loadBackVisit(getId("backVisit.id"));
			} else {
				this.backVisit = new BackVisit();
				if (this.userManager.getUser().getCode() != null) {
					List pfs = this.personnelFilesManager.loadByKey("name", this.userManager.getUser().getName());
					if ((null != pfs) && (pfs.size() > 0)) {
						this.personnelFiles = ((PersonnelFiles) pfs.get(0));
						this.backVisit.setEmployee(this.personnelFiles);

						this.backVisit.setBackVisiter(this.personnelFiles.getName());
					}
				}
			}
	}

	public String save() {
		boolean isNew = this.backVisit.isNew();
		tempSteping = this.backVisit.getCustomerSteping();
		tempSteped = this.backVisit.getCustomerSteped();
		// 如果变更后等级为空， 则属于第一次变更，不需要保存变更前等级，
		if (backVisit.getCustomerSteped() == null) {
			// if ("" != this.request.getParameter("customerStepingId")) {
			// CodeValue cv =
			// this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId"))));
			// this.backVisit.setCustomerSteping(cv);
			// }
		} else {

			this.backVisit.setCustomerSteping(backVisit.getCustomerSteped());

		}

		if (hasId("customerSteped.id")) {
			CodeValue cv = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
			this.backVisit.setCustomerSteped(cv);
		}
		if ("" != this.request.getParameter("changeReason")) {
			this.backVisit.setChangReason(this.request.getParameter("changeReason"));
		}

		if (hasId("customerSteped.id")) {
			CodeValue cve = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
			// CodeValue cvState =
			// this.codeValueManager.loadCodeValue(getId("customerStated.id"));
			CustomerInfo cif = this.backVisit.getCustomerInfo();

			cif.setStep(cve);

			this.backVisit.setCustomerInfo(cif);
		}

		if (isNew) {
			this.backVisitManager.storeBackVisit(this.backVisit);

			CodeValue cve = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
			CustomerInfo cif = this.backVisit.getCustomerInfo();
			this.stepInfo = new StepInfo();
			this.stepInfo.setBackVisitId(this.backVisit);
			this.stepInfo.setCustomerId(cif);
			// b保存之前判断有没有变更记录 如没有， 则子新增一条变更记录，变更之前等级为回访等级变更前等级，如有，
			// 则子新增一条变更记录，变更之前等级为回访等级变更后等级
			if (tempSteped == null) {

				// this.stepInfo.setCustomerSteping(this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId")))));
				this.stepInfo.setCustomerSteping(tempSteping);
			} else {
				this.stepInfo.setCustomerSteping(tempSteped);

			}
			this.stepInfo.setCustomerSteped(cve);
			this.stepInfo.setChangeDate(new Date());
			this.stepInfo.setUser(this.userManager.getUser());
			this.stepInfo.setChangeReason(this.request.getParameter("changeReason"));
			this.backVisitManager.storeStepInfo(this.stepInfo);
		} else {
			this.backVisitManager.storeBackVisit(this.backVisit);
			CodeValue cve = this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
			CustomerInfo cif = this.backVisit.getCustomerInfo();
			this.stepInfo = new StepInfo();
			this.stepInfo.setBackVisitId(this.backVisit);
			this.stepInfo.setCustomerId(cif);
			if (tempSteped == null) {

				// this.stepInfo.setCustomerSteping(this.codeValueManager.loadCodeValue(Long.valueOf(Long.parseLong(this.request.getParameter("customerStepingId")))));
				// 保存之前判断有没有变更记录 如没有， 则子新增一条变更记录，变更之前等级为回访等级变更前等级，如有，
				// 则子新增一条变更记录，变更之前等级为回访等级变更后等级
				this.stepInfo.setCustomerSteping(tempSteping);
			} else {
				this.stepInfo.setCustomerSteping(tempSteped);

			}
			this.stepInfo.setCustomerSteped(cve);
			this.stepInfo.setChangeDate(new Date());
			this.stepInfo.setUser(this.userManager.getUser());
			this.stepInfo.setChangeReason(this.request.getParameter("changeReason"));
			this.backVisitManager.storeStepInfo(this.stepInfo);
		}

		if (isNew) {
			addActionMessage(getText("backvisit.step.success",
					Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));

			return "success";
		}
		addActionMessage(getText("backvisit.step.success",
				Arrays.asList(new Object[] { this.backVisit.getBackVisitType().getName() })));
		// if (hasId("from")) {
		// String s=request.getParameter("from");
		// if(s.equals("h")||s=="h"){
		// return "success1";
		// }
		//
		// }
		return "success";
	}

	public List<CodeValue> getAllBackVisitWay() {
		List cust_types = new ArrayList();
		try {
			CodeValue custType = (CodeValue) this.codeValueManager.loadByKey("code", "007").get(0);
			cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
			if (cust_types != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText(""));
				cust_types.add(0, cv);
				return cust_types;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public UserManager getUserManager() {
		return this.userManager;
	}

	public List<CodeValue> getAllSteps() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "022");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "200");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}

			}

			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public StepInfo getStepInfo() {
		return this.stepInfo;
	}

	public void setStepInfo(StepInfo stepInfo) {
		this.stepInfo = stepInfo;
	}

	public List<CodeValue> getAllBackVisitType() {
		List cust_types = new ArrayList();
		try {
			CodeValue custType = (CodeValue) this.codeValueManager.loadByKey("code", "888").get(0);
			cust_types = this.codeValueManager.loadByKey("parentCV.id", custType.getId());
			if (cust_types != null) {
				CodeValue cv = new CodeValue();
				cv.setId(Long.valueOf(-1L));
				cv.setName(getText(""));
				cust_types.add(0, cv);
				return cust_types;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return new ArrayList();
	}

	public CodeValue getTempSteping() {
		return tempSteping;
	}

	public void setTempSteping(CodeValue tempSteping) {
		this.tempSteping = tempSteping;
	}

	public CodeValue getCustomerSteped() {
		return this.codeValueManager.loadCodeValue(getId("customerSteped.id"));
	}
}
