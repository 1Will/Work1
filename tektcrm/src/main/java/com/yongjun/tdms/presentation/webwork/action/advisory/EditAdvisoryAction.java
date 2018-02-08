package com.yongjun.tdms.presentation.webwork.action.advisory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.advisory.Advisory;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.advisory.AdvisoryManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.util.comparator.CodeValueComparator;

public class EditAdvisoryAction extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final AdvisoryManager advisoryManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final AreaManager areaManager;
	private final CustomerInfoManager customerInfoManager;
	private final UserManager userManager;
	private final ContactArchivesManager contactArchivesManager;
	private Advisory advisory;
	private CustomerInfo custormerInfo;
	private PersonnelFiles personnelFiles;
	private CodeValue staue;
	private int isAddCustomer = 0;

	public EditAdvisoryAction(AdvisoryManager advisoryManager, CodeValueManager codeValueManager,
			AreaManager areaManager, PersonnelFilesManager personnelFilesManager,
			CustomerInfoManager customerInfoManager, UserManager userManager,
			ContactArchivesManager contactArchivesManager) {
		this.advisoryManager = advisoryManager;
		this.codeValueManager = codeValueManager;
		this.areaManager = areaManager;
		this.personnelFilesManager = personnelFilesManager;
		this.customerInfoManager = customerInfoManager;
		this.userManager = userManager;
		this.contactArchivesManager = contactArchivesManager;
	}

	public boolean getIsCustomer() {
		if ((null != this.advisory) && (null != this.advisory.getCustomer())) {
			return true;
		}

		return false;
	}

	public void prepare() throws Exception {
		if (this.advisory == null)
			if (hasId("advisory.id")) {
				this.advisory = this.advisoryManager.loadAdvisory(getId("advisory.id"));
			} else {
				this.advisory = new Advisory();
				if ((null != this.userManager.getUser().getCode())
						&& (!"".equals(this.userManager.getUser().getCode()))) {
					this.personnelFiles = ((PersonnelFiles) this.personnelFilesManager
							.loadByKey("code", this.userManager.getUser().getCode()).get(0));
					this.advisory.setCustomerServiceName(this.personnelFiles.getName());
					this.advisory.setParlorDept(this.personnelFiles.getDept().getName());
					this.advisory.setCustomerServicePerson(this.personnelFiles);
				}
			}
	}

	private boolean changToCustomer() {
		return hasKey("changToCustomer");
	}

	public String save() throws Exception {
		boolean isNew = this.advisory.isNew();
		try {
			if (!StringUtils.isEmpty(this.request.getParameter("customerType"))) {
				this.advisory.setCustomerType(
						this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("customerType"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("industry"))) {
				this.advisory.setIndustry(
						this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("industry"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("companyNature"))) {
				this.advisory.setCompanyNature(
						this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("companyNature"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisory.legalPerson"))) {
				this.advisory.setLegalPerson(this.request.getParameter("advisory.legalPerson"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisory.dept"))) {
				this.advisory.setDept(this.request.getParameter("advisory.dept"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisory.personCount"))) {
				this.advisory.setPersonCount(
						Integer.valueOf(Integer.parseInt(this.request.getParameter("advisory.personCount"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisory.registeredCapital"))) {
				this.advisory.setRegisteredCapital(
						Double.valueOf(Double.parseDouble(this.request.getParameter("advisory.registeredCapital"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisory.parlorDept"))) {
				this.advisory.setParlorDept(this.request.getParameter("advisory.parlorDept"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisory.duty"))) {
				this.advisory.setDuty(this.request.getParameter("advisory.duty"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("country.id"))) {
				this.advisory
						.setCountry(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("country.id"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("province.id"))) {
				this.advisory
						.setProvince(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("province.id"))));
			}

			if ((!StringUtils.isEmpty(this.request.getParameter("city.id")))
					&& (!this.request.getParameter("city.id").equals("-1"))) {
				this.advisory.setCity(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("city.id"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("infoSource"))) {
				this.advisory.setInfoSource(
						this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("infoSource"))));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("customerService"))) {
				this.personnelFiles = this.personnelFilesManager
						.loadPersonnel(Long.valueOf(this.request.getParameter("customerService")));
				this.advisory.setCustomerServicePerson(this.personnelFiles);
				this.advisory.setCustomerServiceName(this.personnelFiles.getName());
			}

			if (!StringUtils.isEmpty(this.request.getParameter("enterpriseSynopsis"))) {
				this.advisory.setEnterpriseSynopsis(this.request.getParameter("enterpriseSynopsis"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("advisoryContent"))) {
				this.advisory.setAdvisoryContent(this.request.getParameter("advisoryContent"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("effectDescribe"))) {
				this.advisory.setEffectDescribe(this.request.getParameter("effectDescribe"));
			}

			if (!StringUtils.isEmpty(this.request.getParameter("customerInfoIntegrity"))) {
				this.advisory.setCustomerInfoIntegrity(
						Float.valueOf(Float.parseFloat(this.request.getParameter("customerInfoIntegrity"))));
			}

			if (Integer.parseInt(this.request.getParameter("isNoB")) == 0)
				this.advisory.setIsNoBack(false);
			else {
				this.advisory.setIsNoBack(true);
			}

			if (Integer.parseInt(this.request.getParameter("advisorySex")) == 0)
				this.advisory.setSex(Boolean.valueOf(false).booleanValue());
			else {
				this.advisory.setSex(Boolean.valueOf(true).booleanValue());
			}

			if (hasId("customerId")) {
				this.advisory.setCustomer(this.customerInfoManager.loadCustomerInfo(getId("customerId")));
				CodeValue statue = (CodeValue) this.codeValueManager.loadByKey("parentCV",
						((CodeValue) this.codeValueManager.loadByKey("code", "023").get(0)).getId()).get(1);

				this.advisory.setStatue(statue);
			} else if (!StringUtils.isEmpty(this.request.getParameter("advisory.statue"))) {
				CodeValue statue = this.codeValueManager
						.loadCodeValue(Long.valueOf(this.request.getParameter("advisory.statue")));
				this.advisory.setStatue(statue);
			} else {
				CodeValue statue = (CodeValue) this.codeValueManager.loadByKey("parentCV",
						((CodeValue) this.codeValueManager.loadByKey("code", "023").get(0)).getId()).get(0);

				this.advisory.setStatue(statue);
			}

			if (changToCustomer()) {
				saveCustByAdvisory();
				this.advisory.setCustomerType((CodeValue) getAllTypes().get(1));
				this.advisoryManager.storeAdvisory(this.advisory);
			}
			if (isNew) {
				List li = this.advisoryManager.loadByKey("name", this.advisory.getName());

				if ((li != null) && (li.size() > 0)) {
					addActionMessage(
							getText("advisory.add.exist", Arrays.asList(new Object[] { this.advisory.getName() })));
					return "error";
				}
			}
			this.advisoryManager.storeAdvisory(this.advisory);
		} catch (Exception e) {
			addActionMessage(getText("advisory.add.error", Arrays.asList(new Object[] { this.advisory.getName() })));
			e.printStackTrace();
			return "error";
		}
		if (getIsAddCustomer() == 0) {
			if (isNew) {
				addActionMessage(
						getText("advisory.add.success", Arrays.asList(new Object[] { this.advisory.getName() })));
				return "new";
			}
			addActionMessage(getText("advisory.edit.success", Arrays.asList(new Object[] { this.advisory.getName() })));
			return "success";
		}

		addActionMessage(getText("advisory1.add.success", Arrays.asList(new Object[] { this.advisory.getName() })));
		return "success";
	}

	public void saveCustByAdvisory() {
		this.custormerInfo = new CustomerInfo();
		ContactArchives contactArchives = new ContactArchives();
		Boolean flag = Boolean.valueOf(false);
		this.custormerInfo.setName(this.advisory.getName());
		this.custormerInfo.setAbbreviations(this.advisory.getShortName());
		try {
			this.custormerInfo.setState(codeValueManager.loadByKey("code", "20001").get(0));
			this.custormerInfo.setCustomerType((CodeValue) getAllTypes().get(1));

			this.custormerInfo
					.setStep((CodeValue) this.codeValueManager
							.loadByKey("parentCV",
									((CodeValue) this.codeValueManager.loadByKey("code", "022").get(0)).getId())
							.get(0));
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		this.custormerInfo
				.setIndustry(this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("industry"))));

		this.custormerInfo.setCompanyNature(
				this.codeValueManager.loadCodeValue(Long.valueOf(this.request.getParameter("companyNature"))));

		this.custormerInfo.setCountry(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("country.id"))));

		this.custormerInfo
				.setProvince(this.areaManager.loadArea(Long.valueOf(this.request.getParameter("province.id"))));

		if (this.request.getParameter("advisorySex").equals("0"))
			this.custormerInfo.setIsOrNot("0");
		else {
			this.custormerInfo.setIsOrNot("1");
		}
		this.custormerInfo.setCity(this.advisory.getCity());
		this.custormerInfo.setSalesman(this.personnelFiles);
		this.custormerInfo.setSaleman(this.personnelFiles.getName());
		this.custormerInfo.setKeyContacter(this.advisory.getConnectPeople());
		this.custormerInfo.setMobilePhone(this.advisory.getMobile());
		this.custormerInfo.setFax(this.advisory.getFax());
		this.custormerInfo.setEmail(this.advisory.getEmail());
		this.custormerInfo.setQq(this.advisory.getQq());
		this.custormerInfo.setTelphone(this.advisory.getOfficePhone());
		this.custormerInfo.setDept(this.advisory.getDept());
		this.custormerInfo.setDuty(this.advisory.getDuty());
		this.custormerInfo.setAddress(this.advisory.getAddress());
		this.custormerInfo.setAdvisoryContent(this.advisory.getAdvisoryContent());
		this.custormerInfo.setLegalPerson(this.advisory.getLegalPerson());
		this.custormerInfo.setRegisteredCapital(this.advisory.getRegisteredCapital());
		this.custormerInfo.setPersonCount(this.advisory.getPersonCount());
		this.custormerInfo.setSetupTime(this.advisory.getSetupTime());
		this.custormerInfo.setBusinessScope(this.advisory.getEnterpriseSynopsis());
		this.custormerInfo.setEffectDescribe(this.advisory.getEffectDescribe());
		this.custormerInfo.setParlorDept(this.advisory.getParlorDept());
		try {
			this.custormerInfo
					.setFamiliarityType((CodeValue) this.codeValueManager
							.loadByKey("parentCV",
									((CodeValue) this.codeValueManager.loadByKey("code", "010").get(0)).getId())
							.get(1));
		} catch (DaoException e1) {
			this.logger.info("熟悉程度查找出错！");
		}

		this.custormerInfo.setMobilePhone(this.advisory.getMobile());
		try {
			List cs = this.customerInfoManager.loadByKey("name", this.advisory.getName());

			if (null != cs) {
				if (cs.size() <= 0) {
					setIsAddCustomer(1);
					this.customerInfoManager.storeCustomerInfo(this.custormerInfo);
					try {
						String[] keys = new String[2];
						Object[] values = new Object[2];
						keys[0] = "customerInfoCode";
						keys[1] = "name";
						values[0] = this.custormerInfo.getCode();
						values[1] = this.advisory.getConnectPeople();
						List conList = new ArrayList();
						conList = this.contactArchivesManager.loadByKeyArray(keys, values);
						if ((null == conList) || (conList.isEmpty())) {
							contactArchives.setName(this.custormerInfo.getKeyContacter());
							contactArchives.setCustName(this.advisory.getName());
							contactArchives.setCustomerInfoCode(this.custormerInfo.getCode());
							contactArchives.setCustomerType(this.custormerInfo.getCustomerType());
							contactArchives.setIndustry(this.custormerInfo.getIndustry().getName().toString());
							contactArchives.setPhone(this.custormerInfo.getTelphone());
							contactArchives.setMobilePhone(this.custormerInfo.getMobilePhone());
							contactArchives.setFax(this.custormerInfo.getFax());
							contactArchives.setEmail(this.custormerInfo.getEmail());
							contactArchives.setQq(this.custormerInfo.getQq());
							contactArchives.setDept(this.advisory.getDept());
							contactArchives.setDuty(this.advisory.getDuty());
							CodeValue o = (CodeValue) this.codeValueManager.loadByKey("code", "0102").get(0);
							contactArchives.setType(o);

							if (this.request.getParameter("advisorySex").equals("0"))
								contactArchives.setSex(false);
							else {
								contactArchives.setSex(true);
							}
							contactArchives.setType(this.custormerInfo.getFamiliarityType());
							flag = Boolean.valueOf(true);
						}
					} catch (Exception e) {
						this.logger.info("联系人添加出错");
					}
					if (flag.booleanValue()) {
						contactArchives.setCustomerName(this.custormerInfo);
						this.contactArchivesManager.storeContactArchives(contactArchives);
					}
					this.advisory.setCustomer(this.custormerInfo);
				} else {
					setIsAddCustomer(1);
					this.advisory.setCustomer((CustomerInfo) cs.get(0));
				}
			} else {
				setIsAddCustomer(1);
				this.customerInfoManager.storeCustomerInfo(this.custormerInfo);
				try {
					String[] keys = new String[2];
					Object[] values = new Object[2];
					keys[0] = "customerInfoCode";
					keys[1] = "name";
					values[0] = this.custormerInfo.getCode();
					values[1] = this.advisory.getConnectPeople();
					List conList = new ArrayList();
					conList = this.contactArchivesManager.loadByKeyArray(keys, values);
					if ((null == conList) || (conList.isEmpty())) {
						contactArchives.setName(this.custormerInfo.getKeyContacter());
						contactArchives.setCustName(this.advisory.getName());
						contactArchives.setCustomerInfoCode(this.custormerInfo.getCode());
						contactArchives.setCustomerType(this.custormerInfo.getCustomerType());
						contactArchives.setIndustry(this.custormerInfo.getIndustry().getName().toString());
						contactArchives.setPhone(this.custormerInfo.getTelphone());
						contactArchives.setMobilePhone(this.custormerInfo.getMobilePhone());
						contactArchives.setFax(this.custormerInfo.getFax());
						contactArchives.setEmail(this.custormerInfo.getEmail());
						contactArchives.setQq(this.custormerInfo.getQq());
						contactArchives.setDept(this.advisory.getDept());
						contactArchives.setDuty(this.advisory.getDuty());
						CodeValue o = (CodeValue) this.codeValueManager.loadByKey("code", "0102").get(0);
						contactArchives.setType(o);

						if (this.request.getParameter("advisorySex").equals("0"))
							contactArchives.setSex(false);
						else {
							contactArchives.setSex(true);
						}
						contactArchives.setType(this.custormerInfo.getFamiliarityType());
						flag = Boolean.valueOf(true);
					}
				} catch (Exception e) {
					this.logger.info("联系人添加出错");
				}
				if (flag.booleanValue()) {
					contactArchives.setCustomerName(this.custormerInfo);
					this.contactArchivesManager.storeContactArchives(contactArchives);
				}
				CodeValue statue = (CodeValue) this.codeValueManager.loadByKey("parentCV",
						((CodeValue) this.codeValueManager.loadByKey("code", "023").get(0)).getId()).get(1);

				this.advisory.setStatue(statue);
				this.advisory.setCustomer(this.custormerInfo);
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	public List getAllCustTypes() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "001").get(0);

			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());

			if (list != null) {
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List getAllIndustrys() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "002").get(0);

			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				return list;
			}
			Collections.sort(list, new CodeValueComparator());
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List getAllEnterNature() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "003").get(0);

			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List getAllInfoOriginId() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "006").get(0);

			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List getAllCountry() {
		try {
			List list = this.areaManager.loadByKey("type", "country");
			if (list != null) {
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List getAllProvince() {
		return new ArrayList();
	}

	public List getAllCity() {
		return new ArrayList();
	}

	public List getAllAdvisoryStatues() {
		try {
			CodeValue codeValue = (CodeValue) this.codeValueManager.loadByKey("code", "023").get(0);

			List list = this.codeValueManager.loadByKey("parentCV", codeValue.getId());
			if (list != null) {
				return list;
			}
			return new ArrayList();
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public Advisory getAdvisory() {
		return this.advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}

	public int getIsAddCustomer() {
		return this.isAddCustomer;
	}

	public void setIsAddCustomer(int isAddCustomer) {
		this.isAddCustomer = isAddCustomer;
	}

	public CodeValue getStaue() {
		return this.staue;
	}

	public void setStaue(CodeValue staue) {
		this.staue = staue;
	}

	public List<CodeValue> getAllSteps() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "022");
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(1)).getId());
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

	public List<CodeValue> getAllTypes() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", "001");
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
}
