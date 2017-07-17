package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProfiles;

import java.text.SimpleDateFormat;
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
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.util.comparator.CodeValueComparator;

public class EditCustomerInfoAction extends PrepareAction {
	private static final long serialVersionUID = 1L;
	private final CustomerInfoManager customerInfoManager;
	private final CodeValueManager codeValueManager;
	private final AreaManager areaManager;
	private final PersonnelFilesManager personnelFilesManager;
	private ContactArchivesManager contactArchivesManager;
	private final UserManager userManager;
	private PersonnelFiles personnelFiles;
	private CustomerInfo customerInfo;
	private CodeValue customerType;
	private CodeValue industry;
	private CodeValue companyNature;
	private Area country;
	private Area province;
	private Area city;
	private PersonnelFiles salesman;
	private String popWindowFlag;
	private String notNewFlag;

	public EditCustomerInfoAction(CustomerInfoManager customerInfoManager, CodeValueManager codeValueManager,
			AreaManager areaManager, PersonnelFilesManager personnelFilesManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager) {
		this.customerInfoManager = customerInfoManager;
		this.codeValueManager = codeValueManager;
		this.areaManager = areaManager;
		this.personnelFilesManager = personnelFilesManager;
		this.contactArchivesManager = contactArchivesManager;
		this.userManager = userManager;
		this.customerType = new CodeValue();
		this.industry = new CodeValue();
		this.companyNature = new CodeValue();
		this.country = new Area();
		this.province = new Area();
		this.city = new Area();
		this.salesman = new PersonnelFiles();
	}

	public void prepare() throws Exception {
		if (null == this.customerInfo)
			if (hasId("customerInfo.id")) {
				this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
			} else {
				this.customerInfo = new CustomerInfo();
				if ((null != this.userManager.getUser().getCode())
						&& (!"".equals(this.userManager.getUser().getCode()))) {
					this.personnelFiles = ((PersonnelFiles) this.personnelFilesManager.loadByKey("code",
							this.userManager.getUser().getCode()).get(0));
					this.customerInfo.setSaleman(this.personnelFiles.getName());
					this.customerInfo.setParlorDept(this.personnelFiles.getDept().getName());
					this.customerInfo.setSalesman(this.personnelFiles);
				}
			}
		if (request.getParameter("popWindowFlag") != null) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
		if (request.getParameter("notNewFlag") != null) {
			this.notNewFlag = request.getParameter("notNewFlag");
		}
	}

	public String save() {
		boolean isNew = this.customerInfo.isNew();
		try {
			if (hasId("type.id")) {
				this.customerType = this.codeValueManager.loadCodeValue(getId("type.id"));
				this.customerInfo.setCustomerType(this.customerType);
			}
			if (isNew) {
				this.customerType = ((CodeValue) getAllTypes().get(1));
				this.customerInfo.setCustomerType(this.customerType);
				CodeValue step = (CodeValue) getAllSteps().get(0);
				CodeValue state = (CodeValue) getAllStates().get(0);
				this.customerInfo.setStep(step);
				this.customerInfo.setState(state);// 客户默认告警状态为正常状态
			}

			if (hasId("industry.id")) {
				this.industry = this.codeValueManager.loadCodeValue(getId("industry.id"));
				this.customerInfo.setIndustry(this.industry);
			}

			if (hasId("resource.id")) {
				CodeValue resource = this.codeValueManager.loadCodeValue(getId("resource.id"));
				this.customerInfo.setResource(resource);
			}

			if (hasId("familiarityType.id")) {
				CodeValue familiarityType = this.codeValueManager.loadCodeValue(getId("familiarityType.id"));
				this.customerInfo.setFamiliarityType(familiarityType);
			}

			if (hasId("companyNature.id")) {
				this.companyNature = this.codeValueManager.loadCodeValue(getId("companyNature.id"));
				this.customerInfo.setCompanyNature(this.companyNature);
			}

			if (hasId("country.id")) {
				this.country = this.areaManager.loadArea(getId("country.id"));
				this.customerInfo.setCountry(this.country);
			}

			if (hasId("province.id")) {
				this.province = this.areaManager.loadArea(getId("province.id"));
				this.customerInfo.setProvince(this.province);
			}

			if ((hasId("city.id")) && (!this.request.getParameter("city.id").equals("-1"))) {
				this.city = this.areaManager.loadArea(getId("city.id"));
				this.customerInfo.setCity(this.city);
			}

			// if (hasId("customerInfo.effectDescribe")) {
			// this.customerInfo.setEffectDescribe(this.request.getParameter("customerInfo.effectDescribe"));
			// }

			if (hasId("customerInfo.advisoryContent")) {
				this.customerInfo.setAdvisoryContent(this.request.getParameter("customerInfo.advisoryContent"));
			}

			if (hasId("customerInfo.parlorDept")) {
				this.customerInfo.setParlorDept(this.request.getParameter("customerInfo.parlorDept"));
			}

			if (hasId("customerInfo.archiveTime")) {
				this.customerInfo.setArchiveTime(new SimpleDateFormat("yyyy-MM-dd").parse(this.request
						.getParameter("customerInfo.archiveTime")));
			}

			if (hasId("customerInfo.customerInfoIntegrity")) {
				this.customerInfo.setCustomerInfoIntegrity(Float.valueOf(Float.parseFloat(this.request
						.getParameter("customerInfo.customerInfoIntegrity"))));
			}

			if (hasId("salesman.id")) {
				this.salesman = this.personnelFilesManager.loadPersonnel(getId("salesman.id"));
				this.customerInfo.setSaleman(this.salesman.getName());
				this.customerInfo.setSalesman(this.salesman);
			}

			if (hasId("isOrNot")) {
				String isOrNot = this.request.getParameter("isOrNot");
				this.customerInfo.setIsOrNot(isOrNot);
			}
			if (isNew) {
				List li = this.customerInfoManager.loadByKey("name", this.customerInfo.getName());

				if ((li != null) && (li.size() > 0)) {
					addActionMessage(getText("customerInfo.add.exist",
							Arrays.asList(new Object[] { this.customerInfo.getName() })));
					return "error";
				}
			}
			this.customerInfoManager.storeCustomerInfo(this.customerInfo);

			String[] keys = new String[2];
			Object[] values = new Object[2];
			keys[0] = "customerInfoCode";
			keys[1] = "name";
			values[0] = this.customerInfo.getCode();
			values[1] = this.customerInfo.getKeyContacter();
			List conList = new ArrayList();
			conList = this.contactArchivesManager.loadByKeyArray(keys, values);
			if ((null == conList) || (conList.isEmpty())) {
				ContactArchives contactArchives = new ContactArchives();
				contactArchives.setName(this.customerInfo.getKeyContacter());

				if (hasId("customerInfo.effectDescribe")) {
					contactArchives.setEnterpriseSynopsis(this.request.getParameter("customerInfo.effectDescribe"));
				}
				contactArchives.setCustName(this.customerInfo.getName());
				contactArchives.setCustomerName(this.customerInfo);
				contactArchives.setCreator(salesman.getName());
				contactArchives.setCustomerInfoCode(this.customerInfo.getCode());
				contactArchives.setCustType(this.customerInfo.getCustomerType().getName().toString());
				contactArchives.setCustomerType(this.customerInfo.getCustomerType());
				contactArchives.setIndustry(this.customerInfo.getIndustry().getName().toString());
				contactArchives.setPhone(this.customerInfo.getTelphone());
				contactArchives.setMobilePhone(this.customerInfo.getMobilePhone());
				contactArchives.setChuanzhen(this.customerInfo.getChuanzhen());
				contactArchives.setFax(this.customerInfo.getFax());
				contactArchives.setEmail(this.customerInfo.getEmail());
				contactArchives.setQq(this.customerInfo.getQq());
				CodeValue o = (CodeValue) this.codeValueManager.loadByKey("code", "0102").get(0);
				contactArchives.setType(o);
				if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.dept"))) {
					contactArchives.setDept(this.request.getParameter("customerInfo.dept"));
				}
				if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.duty"))) {
					contactArchives.setDuty(this.request.getParameter("customerInfo.duty"));
				}

				if (hasId("isOrNot")) {
					String isOrNot = this.request.getParameter("isOrNot");
					if (isOrNot.equals("0"))
						contactArchives.setSex(false);
					else {
						contactArchives.setSex(true);
					}
				}
				this.contactArchivesManager.storeContactArchives(contactArchives);
			} else {
				ContactArchives contactArchives = (ContactArchives) conList.get(0);
				contactArchives.setName(this.customerInfo.getKeyContacter());
				contactArchives.setCustName(this.customerInfo.getName());
				contactArchives.setCustomerName(this.customerInfo);
				contactArchives.setCustType(this.customerInfo.getCustomerType().getName().toString());
				contactArchives.setCustomerType(this.customerInfo.getCustomerType());
				contactArchives.setIndustry(this.customerInfo.getIndustry().getName().toString());
				contactArchives.setPhone(this.customerInfo.getTelphone());
				contactArchives.setMobilePhone(this.customerInfo.getMobilePhone());
				contactArchives.setChuanzhen(this.customerInfo.getChuanzhen());
				contactArchives.setFax(this.customerInfo.getFax());
				contactArchives.setEmail(this.customerInfo.getEmail());
				contactArchives.setQq(this.customerInfo.getQq());
				if (hasId("customerInfo.effectDescribe")) {
					contactArchives.setEnterpriseSynopsis(this.request.getParameter("customerInfo.effectDescribe"));
				}
				CodeValue o = null;
				if ((null != this.customerInfo.getFamiliarityType())
						&& (null != this.customerInfo.getFamiliarityType().getId())) {
					o = this.codeValueManager.loadCodeValue(this.customerInfo.getFamiliarityType().getId());
					contactArchives.setType(o);
				}

				if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.dept"))) {
					contactArchives.setDept(this.request.getParameter("customerInfo.dept"));
				}
				if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.duty"))) {
					contactArchives.setDuty(this.request.getParameter("customerInfo.duty"));
				}

				if (hasId("isOrNot")) {
					String isOrNot = this.request.getParameter("isOrNot");
					if (isOrNot.equals("0"))
						contactArchives.setSex(false);
					else {
						contactArchives.setSex(true);
					}
				}
				this.contactArchivesManager.storeContactArchives(contactArchives);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isNew) {
				addActionMessage(getText("customerInfo.add.error",
						Arrays.asList(new Object[] { this.customerInfo.getCode() })));
			} else {
				addActionMessage(getText("customerInfo.edit.error",
						Arrays.asList(new Object[] { this.customerInfo.getCode() })));

				return "error";
			}
		}
		if (isNew) {
			addActionMessage(getText("customerInfo.add.success",
					Arrays.asList(new Object[] { this.customerInfo.getCode() })));

			return "new";
		}
		addActionMessage(getText("customerInfo.edit.success",
				Arrays.asList(new Object[] { this.customerInfo.getCode() })));

		return "success";
	}

	public List<CodeValue> getAllTypes() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
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

	public List<CodeValue> getAllTypess() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
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

	public List<CodeValue> getAllSteps() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
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

	public List<CodeValue> getAllStates() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("200"));
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

	public List<CodeValue> getAllResources() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("006"));

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

	public List<CodeValue> getAllFamiliarityTypes() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("010"));

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

	public List<CodeValue> getAllIndustrys() {
		try {
			List industrys = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("002"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					industrys.addAll(list);
				}
			}
			Collections.sort(industrys, new CodeValueComparator());
			return industrys;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllCompanyNatures() {
		try {
			List companyNatures = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("003"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<Area> getAllCountrys() {
		try {
			List countrys = new ArrayList();
			List list = this.areaManager.loadByKey("type", "country");
			if ((null != list) && (list.size() > 0)) {
				countrys.addAll(list);
			}
			return countrys;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<Area> getAllProvince() {
		List province = new ArrayList();
		return province;
	}

	public List<Area> getAllCity() {
		List city = new ArrayList();
		return city;
	}

	public Area getCity() {
		return this.city;
	}

	public void setCity(Area city) {
		this.city = city;
	}

	public CodeValue getCompanyNature() {
		return this.companyNature;
	}

	public void setCompanyNature(CodeValue companyNature) {
		this.companyNature = companyNature;
	}

	public Area getCountry() {
		return this.country;
	}

	public void setCountry(Area country) {
		this.country = country;
	}

	public CustomerInfo getCustomerInfo() {
		return this.customerInfo;
	}

	public void setCustomerInfo(CustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public CodeValue getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(CodeValue customerType) {
		this.customerType = customerType;
	}

	public PersonnelFiles getSalesman() {
		return this.salesman;
	}

	public void setSalesman(PersonnelFiles salesman) {
		this.salesman = salesman;
	}

	public CodeValue getIndustry() {
		return this.industry;
	}

	public void setIndustry(CodeValue industry) {
		this.industry = industry;
	}

	public Area getProvince() {
		return this.province;
	}

	public void setProvince(Area province) {
		this.province = province;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

	public String getNotNewFlag() {
		return notNewFlag;
	}

	public void setNotNewFlag(String notNewFlag) {
		this.notNewFlag = notNewFlag;
	}
	//
	// class CodeValueComparator implements Comparator {
	//
	// public int compare(Object o1, Object o2) {
	// CodeValue t1 = (CodeValue) o1;
	// CodeValue t2 = (CodeValue) o2;
	// String name1 = converterToSpell(t1.getName());
	// String name2 = converterToSpell(t2.getName());
	//
	// int result = name1.compareTo(name2);
	// return result;
	// }
	//
	// }

	//
	// public static String converterToSpell(String chines) {
	// String pinyinName = "";
	// char[] nameChar = chines.toCharArray();
	// HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
	// defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	// defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	// for (int i = 0; i < nameChar.length; i++) {
	// if (nameChar[i] > 128) {
	// try {
	// pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i],
	// defaultFormat)[0];
	// } catch (BadHanyuPinyinOutputFormatCombination e) {
	// e.printStackTrace();
	// }
	// } else {
	// pinyinName += nameChar[i];
	// }
	// }
	// return pinyinName;
	// }

}
