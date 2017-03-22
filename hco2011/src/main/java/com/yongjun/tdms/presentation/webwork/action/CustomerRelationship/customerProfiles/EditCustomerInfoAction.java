/*     */package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProfiles;

/*     */
/*     */import com.yongjun.pluto.exception.DaoException;
/*     */
import com.yongjun.pluto.model.codevalue.CodeValue;
/*     */
import com.yongjun.pluto.model.security.Department;
/*     */
import com.yongjun.pluto.model.security.User;
/*     */
import com.yongjun.pluto.service.codevalue.CodeValueManager;
/*     */
import com.yongjun.pluto.service.security.UserManager;
/*     */
import com.yongjun.pluto.webwork.action.PrepareAction;
/*     */
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
/*     */
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
/*     */
import com.yongjun.tdms.model.base.area.Area;
/*     */
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
/*     */
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
/*     */
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
/*     */
import com.yongjun.tdms.service.base.area.AreaManager;
/*     */
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

/*     */
import java.text.SimpleDateFormat;
/*     */
import java.util.ArrayList;
/*     */
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
/*     */
import java.util.List;

/*     */
import javax.servlet.http.HttpServletRequest;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;

import com.yongjun.tdms.util.comparator.CodeValueComparator;

/*     */
/*     */public class EditCustomerInfoAction extends PrepareAction
/*     */{
	/*     */private static final long serialVersionUID = 1L;
	/*     */private final CustomerInfoManager customerInfoManager;
	/*     */private final CodeValueManager codeValueManager;
	/*     */private final AreaManager areaManager;
	/*     */private final PersonnelFilesManager personnelFilesManager;
	/*     */private ContactArchivesManager contactArchivesManager;
	/*     */private final UserManager userManager;
	/*     */private PersonnelFiles personnelFiles;
	/*     */private CustomerInfo customerInfo;
	/*     */private CodeValue customerType;
	/*     */private CodeValue industry;
	/*     */private CodeValue companyNature;
	/*     */private Area country;
	/*     */private Area province;
	/*     */private Area city;
	/*     */private PersonnelFiles salesman;

	/*     */
	/*     */public EditCustomerInfoAction(CustomerInfoManager customerInfoManager, CodeValueManager codeValueManager,
			AreaManager areaManager, PersonnelFilesManager personnelFilesManager,
			ContactArchivesManager contactArchivesManager, UserManager userManager)
	/*     */{
		/* 104 */this.customerInfoManager = customerInfoManager;
		/* 105 */this.codeValueManager = codeValueManager;
		/* 106 */this.areaManager = areaManager;
		/* 107 */this.personnelFilesManager = personnelFilesManager;
		/* 108 */this.contactArchivesManager = contactArchivesManager;
		/* 109 */this.userManager = userManager;
		/* 110 */this.customerType = new CodeValue();
		/* 111 */this.industry = new CodeValue();
		/* 112 */this.companyNature = new CodeValue();
		/* 113 */this.country = new Area();
		/* 114 */this.province = new Area();
		/* 115 */this.city = new Area();
		/* 116 */this.salesman = new PersonnelFiles();
		/*     */}

	/*     */
	/*     */public void prepare()
	/*     */throws Exception
	/*     */{
		/* 125 */if (null == this.customerInfo)
			/* 126 */if (hasId("customerInfo.id")) {
				/* 127 */this.customerInfo = this.customerInfoManager.loadCustomerInfo(getId("customerInfo.id"));
				/*     */} else {
				/* 129 */this.customerInfo = new CustomerInfo();
				/* 130 */if ((null != this.userManager.getUser().getCode())
						&& (!"".equals(this.userManager.getUser().getCode()))) {
					/* 131 */this.personnelFiles = ((PersonnelFiles) this.personnelFilesManager.loadByKey("code",
							this.userManager.getUser().getCode()).get(0));
					/* 132 */this.customerInfo.setSaleman(this.personnelFiles.getName());
					/* 133 */this.customerInfo.setParlorDept(this.personnelFiles.getDept().getName());
					/* 134 */this.customerInfo.setSalesman(this.personnelFiles);
					/*     */}
				/*     */}
		/*     */}

	/*     */
	/*     */public String save()
	/*     */{
		/* 145 */boolean isNew = this.customerInfo.isNew();
		/*     */try
		/*     */{
			/* 148 */if (hasId("type.id")) {
				/* 149 */this.customerType = this.codeValueManager.loadCodeValue(getId("type.id"));
				/* 150 */this.customerInfo.setCustomerType(this.customerType);
				/*     */}
			/* 152 */if (isNew) {
				/* 153 */this.customerType = ((CodeValue) getAllTypes().get(1));
				/* 154 */this.customerInfo.setCustomerType(this.customerType);
				/* 155 */CodeValue step = (CodeValue) getAllSteps().get(0);
						 CodeValue state = (CodeValue) getAllStates().get(0);
				/* 156 */this.customerInfo.setStep(step);
				         this.customerInfo.setState(state);//客户默认告警状态为正常状态
				/*     */}
			/*     */
			/* 159 */if (hasId("industry.id")) {
				/* 160 */this.industry = this.codeValueManager.loadCodeValue(getId("industry.id"));
				/* 161 */this.customerInfo.setIndustry(this.industry);
				/*     */}
			/*     */
			/* 164 */if (hasId("resource.id")) {
				/* 165 */CodeValue resource = this.codeValueManager.loadCodeValue(getId("resource.id"));
				/* 166 */this.customerInfo.setResource(resource);
				/*     */}
			/*     */
			/* 169 */if (hasId("familiarityType.id")) {
				/* 170 */CodeValue familiarityType = this.codeValueManager.loadCodeValue(getId("familiarityType.id"));
				/* 171 */this.customerInfo.setFamiliarityType(familiarityType);
				/*     */}
			/*     */
			/* 175 */if (hasId("companyNature.id")) {
				/* 176 */this.companyNature = this.codeValueManager.loadCodeValue(getId("companyNature.id"));
				/* 177 */this.customerInfo.setCompanyNature(this.companyNature);
				/*     */}
			/*     */
			/* 185 */if (hasId("country.id")) {
				/* 186 */this.country = this.areaManager.loadArea(getId("country.id"));
				/* 187 */this.customerInfo.setCountry(this.country);
				/*     */}
			/*     */
			/* 190 */if (hasId("province.id")) {
				/* 191 */this.province = this.areaManager.loadArea(getId("province.id"));
				/* 192 */this.customerInfo.setProvince(this.province);
				/*     */}
			/*     */
			/* 195 */if ((hasId("city.id")) && (!this.request.getParameter("city.id").equals("-1"))) {
				/* 196 */this.city = this.areaManager.loadArea(getId("city.id"));
				/* 197 */this.customerInfo.setCity(this.city);
				/*     */}
			/*     */
//			/* 200 */if (hasId("customerInfo.effectDescribe")) {
//				/* 201 */this.customerInfo.setEffectDescribe(this.request.getParameter("customerInfo.effectDescribe"));
//				/*     */}
			/*     */
			/* 204 */if (hasId("customerInfo.advisoryContent")) {
				/* 205 */this.customerInfo
						.setAdvisoryContent(this.request.getParameter("customerInfo.advisoryContent"));
				/*     */}
			/*     */
			/* 208 */if (hasId("customerInfo.parlorDept")) {
				/* 209 */this.customerInfo.setParlorDept(this.request.getParameter("customerInfo.parlorDept"));
				/*     */}
			/*     */
			/* 212 */if (hasId("customerInfo.archiveTime")) {
				/* 213 */this.customerInfo.setArchiveTime(new SimpleDateFormat("yyyy-MM-dd").parse(this.request
						.getParameter("customerInfo.archiveTime")));
				/*     */}
			/*     */
			/* 216 */if (hasId("customerInfo.customerInfoIntegrity")) {
				/* 217 */this.customerInfo.setCustomerInfoIntegrity(Float.valueOf(Float.parseFloat(this.request
						.getParameter("customerInfo.customerInfoIntegrity"))));
				/*     */}
			/*     */
			/* 220 */if (hasId("salesman.id")) {
				/* 221 */this.salesman = this.personnelFilesManager.loadPersonnel(getId("salesman.id"));
				/* 222 */this.customerInfo.setSaleman(this.salesman.getName());
				/* 223 */this.customerInfo.setSalesman(this.salesman);
				/*     */}
			/*     */
			/* 226 */if (hasId("isOrNot")) {
				/* 227 */String isOrNot = this.request.getParameter("isOrNot");
				/* 228 */this.customerInfo.setIsOrNot(isOrNot);
				/*     */}
			/* 230 */if (isNew) {
				/* 231 */List li = this.customerInfoManager.loadByKey("name", this.customerInfo.getName());
				/*     */
				/* 233 */if ((li != null) &&
				/* 234 */(li.size() > 0)) {
					/* 235 */addActionMessage(getText("customerInfo.add.exist",
							Arrays.asList(new Object[] { this.customerInfo.getName() })));
					/* 236 */return "error";
					/*     */}
				/*     */}
			/* 239 */this.customerInfoManager.storeCustomerInfo(this.customerInfo);
			/*     */
			/* 242 */String[] keys = new String[2];
			/* 243 */Object[] values = new Object[2];
			/* 244 */keys[0] = "customerInfoCode";
			/* 245 */keys[1] = "name";
			/* 246 */values[0] = this.customerInfo.getCode();
			/* 247 */values[1] = this.customerInfo.getKeyContacter();
			/* 248 */List conList = new ArrayList();
			/* 249 */conList = this.contactArchivesManager.loadByKeyArray(keys, values);
			/* 250 */if ((null == conList) || (conList.isEmpty())) {
				/* 251 */ContactArchives contactArchives = new ContactArchives();
				/* 252 */contactArchives.setName(this.customerInfo.getKeyContacter());
						
						if (hasId("customerInfo.effectDescribe")) {
							contactArchives.setEnterpriseSynopsis(this.request.getParameter("customerInfo.effectDescribe"));
						}
				/* 253 */contactArchives.setCustName(this.customerInfo.getName());
				/* 254 */contactArchives.setCustomerName(this.customerInfo);
				/* 255 */contactArchives.setCustomerInfoCode(this.customerInfo.getCode());
				/* 256 */contactArchives.setCustType(this.customerInfo.getCustomerType().getName().toString());
				/* 257 */contactArchives.setCustomerType(this.customerInfo.getCustomerType());
				/* 258 */contactArchives.setIndustry(this.customerInfo.getIndustry().getName().toString());
				/* 259 */contactArchives.setPhone(this.customerInfo.getTelphone());
				/* 260 */contactArchives.setMobilePhone(this.customerInfo.getMobilePhone());
				contactArchives.setChuanzhen(this.customerInfo.getChuanzhen());
				/* 261 */contactArchives.setFax(this.customerInfo.getFax());
				/* 262 */contactArchives.setEmail(this.customerInfo.getEmail());
				/* 263 */contactArchives.setQq(this.customerInfo.getQq());
				/* 264 */CodeValue o = (CodeValue) this.codeValueManager.loadByKey("code", "0102").get(0);
				/* 265 */contactArchives.setType(o);
				/* 266 */if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.dept"))) {
					/* 267 */contactArchives.setDept(this.request.getParameter("customerInfo.dept"));
					/*     */}
				/* 269 */if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.duty"))) {
					/* 270 */contactArchives.setDuty(this.request.getParameter("customerInfo.duty"));
					/*     */}
				/*     */
				/* 273 */if (hasId("isOrNot")) {
					/* 274 */String isOrNot = this.request.getParameter("isOrNot");
					/* 275 */if (isOrNot.equals("0"))
						/* 276 */contactArchives.setSex(false);
					/*     */else {
						/* 278 */contactArchives.setSex(true);
						/*     */}
					/*     */}
				/* 281 */this.contactArchivesManager.storeContactArchives(contactArchives);
				/*     */}
			/*     */else
			/*     */{
				/* 285 */ContactArchives contactArchives = (ContactArchives) conList.get(0);
				/* 286 */contactArchives.setName(this.customerInfo.getKeyContacter());
				/* 287 */contactArchives.setCustName(this.customerInfo.getName());
				/* 288 */contactArchives.setCustomerName(this.customerInfo);
				/* 289 */contactArchives.setCustType(this.customerInfo.getCustomerType().getName().toString());
				/* 290 */contactArchives.setCustomerType(this.customerInfo.getCustomerType());
				/* 291 */contactArchives.setIndustry(this.customerInfo.getIndustry().getName().toString());
				/* 292 */contactArchives.setPhone(this.customerInfo.getTelphone());
				/* 293 */contactArchives.setMobilePhone(this.customerInfo.getMobilePhone());
				         contactArchives.setChuanzhen(this.customerInfo.getChuanzhen());
				/* 294 */contactArchives.setFax(this.customerInfo.getFax());
				/* 295 */contactArchives.setEmail(this.customerInfo.getEmail());
				/* 296 */contactArchives.setQq(this.customerInfo.getQq());
				if (hasId("customerInfo.effectDescribe")) {
					contactArchives.setEnterpriseSynopsis(this.request.getParameter("customerInfo.effectDescribe"));
				}
				/* 297 */CodeValue o = null;
				/* 298 */if ((null != this.customerInfo.getFamiliarityType())
						&& (null != this.customerInfo.getFamiliarityType().getId())) {
					/* 299 */o = this.codeValueManager.loadCodeValue(this.customerInfo.getFamiliarityType().getId());
					/* 300 */contactArchives.setType(o);
					/*     */}
				/*     */
				/* 303 */if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.dept"))) {
					/* 304 */contactArchives.setDept(this.request.getParameter("customerInfo.dept"));
					/*     */}
				/* 306 */if (!StringUtils.isEmpty(this.request.getParameter("customerInfo.duty"))) {
					/* 307 */contactArchives.setDuty(this.request.getParameter("customerInfo.duty"));
					/*     */}
				/*     */
				/* 310 */if (hasId("isOrNot")) {
					/* 311 */String isOrNot = this.request.getParameter("isOrNot");
					/* 312 */if (isOrNot.equals("0"))
						/* 313 */contactArchives.setSex(false);
					/*     */else {
						/* 315 */contactArchives.setSex(true);
						/*     */}
					/*     */}
				/* 318 */this.contactArchivesManager.storeContactArchives(contactArchives);
				/*     */}
			/*     */} catch (Exception e) {
			/* 321 */e.printStackTrace();
			/* 322 */if (isNew) {
				/* 323 */addActionMessage(getText("customerInfo.add.error",
						Arrays.asList(new Object[] { this.customerInfo.getCode() })));
				/*     */}
			/*     */else {
				/* 326 */addActionMessage(getText("customerInfo.edit.error",
						Arrays.asList(new Object[] { this.customerInfo.getCode() })));
				/*     */
				/* 328 */return "error";
				/*     */}
			/*     */}
		/* 331 */if (isNew) {
			/* 332 */addActionMessage(getText("customerInfo.add.success",
					Arrays.asList(new Object[] { this.customerInfo.getCode() })));
			/*     */
			/* 334 */return "new";
			/*     */}
		/* 336 */addActionMessage(getText("customerInfo.edit.success",
				Arrays.asList(new Object[] { this.customerInfo.getCode() })));
		/*     */
		/* 338 */return "success";
		/*     */}

	/*     */
	/*     */public List<CodeValue> getAllTypes()
	/*     */{
		/*     */try
		/*     */{
			/* 347 */List codes = new ArrayList();
			/* 348 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
			/* 349 */if ((null != one) && (one.size() > 0)) {
				/* 350 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				/* 351 */if ((null != list) && (list.size() > 0)) {
					/* 352 */codes.addAll(list);
					/*     */}
				/*     */}
			/* 355 */return codes;
			/*     */} catch (DaoException e) {
			/* 357 */e.printStackTrace();
			/* 358 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<CodeValue> getAllTypess()
	/*     */{
		/*     */try
		/*     */{
			/* 369 */List codes = new ArrayList();
			/* 370 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
			/* 371 */if ((null != one) && (one.size() > 0)) {
				/* 372 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				/* 373 */if ((null != list) && (list.size() > 0)) {
					/* 374 */codes.addAll(list);
					/*     */}
				/*     */}
			/* 377 */return codes;
			/*     */} catch (DaoException e) {
			/* 379 */e.printStackTrace();
			/* 380 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<CodeValue> getAllSteps()
	/*     */{
		/*     */try
		/*     */{
			/* 391 */List codes = new ArrayList();
			/* 392 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("022"));
			/* 393 */if ((null != one) && (one.size() > 0)) {
				/* 394 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(1)).getId());
				/* 395 */if ((null != list) && (list.size() > 0)) {
					/* 396 */codes.addAll(list);
					/*     */}
				/*     */}
			/* 399 */return codes;
			/*     */} catch (DaoException e) {
			/* 401 */e.printStackTrace();
			/* 402 */return new ArrayList();
			/*     */}
		/*     */}

			public List<CodeValue> getAllStates()
	/*     */   {
	/*     */     try
	/*     */     {
	/* 211 */       List codes = new ArrayList();
	/* 212 */       List one = this.codeValueManager.loadByKey("code", Long.valueOf("200"));
	/* 213 */       if ((null != one) && (one.size() > 0)) {
	/* 214 */         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
	/* 215 */         if ((null != list) && (list.size() > 0)) {
	/* 216 */           codes.addAll(list);
	/*     */         }
	/*     */ 
	/*     */       }
	/*     */ 
	/* 223 */       return codes;
	/*     */     } catch (DaoException e) {
	/* 225 */       e.printStackTrace();
	/* 226 */       return new ArrayList();
	/*     */     }
	/*     */   }
	
	/*     */
	/*     */public List<CodeValue> getAllResources()
	/*     */{
		/*     */try
		/*     */{
			/* 413 */List codes = new ArrayList();
			/* 414 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("006"));
			/*     */
			/* 416 */if ((null != one) && (one.size() > 0)) {
				/* 417 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				/* 418 */if ((null != list) && (list.size() > 0)) {
					/* 419 */codes.addAll(list);
					/*     */}
				/*     */}
			/* 422 */return codes;
			/*     */} catch (DaoException e) {
			/* 424 */e.printStackTrace();
			/* 425 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<CodeValue> getAllFamiliarityTypes()
	/*     */{
		/*     */try
		/*     */{
			/* 436 */List codes = new ArrayList();
			/* 437 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("010"));
			/*     */
			/* 439 */if ((null != one) && (one.size() > 0)) {
				/* 440 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				/*     */
				/* 442 */if ((null != list) && (list.size() > 0)) {
					/* 443 */codes.addAll(list);
					/*     */}
				/*     */}
			/* 446 */return codes;
			/*     */} catch (DaoException e) {
			/* 448 */e.printStackTrace();
			/* 449 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<CodeValue> getAllIndustrys()
	/*     */{
		/*     */try
		/*     */{
			/* 460 */List industrys = new ArrayList();
			/* 461 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("002"));
			/* 462 */if ((null != one) && (one.size() > 0)) {
				/* 463 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				/* 464 */if ((null != list) && (list.size() > 0)) {
					/* 465 */industrys.addAll(list);
					/*     */}
				/*     */}
			Collections.sort(industrys, new CodeValueComparator());
			/* 468 */return industrys;
			/*     */} catch (Exception e) {
			/* 470 */e.printStackTrace();
			/* 471 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<CodeValue> getAllCompanyNatures()
	/*     */{
		/*     */try
		/*     */{
			/* 482 */List companyNatures = new ArrayList();
			/* 483 */List one = this.codeValueManager.loadByKey("code", Long.valueOf("003"));
			/* 484 */if ((null != one) && (one.size() > 0)) {
				/* 485 */List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				/* 486 */if ((null != list) && (list.size() > 0)) {
					/* 487 */companyNatures.addAll(list);
					/*     */}
				/*     */}
			/* 490 */return companyNatures;
			/*     */} catch (Exception e) {
			/* 492 */e.printStackTrace();
			/* 493 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<Area> getAllCountrys()
	/*     */{
		/*     */try
		/*     */{
			/* 504 */List countrys = new ArrayList();
			/* 505 */List list = this.areaManager.loadByKey("type", "country");
			/* 506 */if ((null != list) && (list.size() > 0)) {
				/* 507 */countrys.addAll(list);
				/*     */}
			/* 509 */return countrys;
			/*     */} catch (Exception e) {
			/* 511 */e.printStackTrace();
			/* 512 */return new ArrayList();
			/*     */}
		/*     */}

	/*     */
	/*     */public List<Area> getAllProvince()
	/*     */{
		/* 522 */List province = new ArrayList();
		/* 523 */return province;
		/*     */}

	/*     */
	/*     */public List<Area> getAllCity()
	/*     */{
		/* 531 */List city = new ArrayList();
		/* 532 */return city;
		/*     */}

	/*     */
	/*     */public Area getCity()
	/*     */{
		/* 539 */return this.city;
		/*     */}

	/*     */
	/*     */public void setCity(Area city)
	/*     */{
		/* 546 */this.city = city;
		/*     */}

	/*     */
	/*     */public CodeValue getCompanyNature()
	/*     */{
		/* 553 */return this.companyNature;
		/*     */}

	/*     */
	/*     */public void setCompanyNature(CodeValue companyNature)
	/*     */{
		/* 560 */this.companyNature = companyNature;
		/*     */}

	/*     */
	/*     */public Area getCountry()
	/*     */{
		/* 567 */return this.country;
		/*     */}

	/*     */
	/*     */public void setCountry(Area country)
	/*     */{
		/* 574 */this.country = country;
		/*     */}

	/*     */
	/*     */public CustomerInfo getCustomerInfo()
	/*     */{
		/* 581 */return this.customerInfo;
		/*     */}

	/*     */
	/*     */public void setCustomerInfo(CustomerInfo customerInfo)
	/*     */{
		/* 588 */this.customerInfo = customerInfo;
		/*     */}

	/*     */
	/*     */public CodeValue getCustomerType()
	/*     */{
		/* 595 */return this.customerType;
		/*     */}

	/*     */
	/*     */public void setCustomerType(CodeValue customerType)
	/*     */{
		/* 602 */this.customerType = customerType;
		/*     */}

	/*     */
	/*     */public PersonnelFiles getSalesman()
	/*     */{
		/* 609 */return this.salesman;
		/*     */}

	/*     */
	/*     */public void setSalesman(PersonnelFiles salesman)
	/*     */{
		/* 616 */this.salesman = salesman;
		/*     */}

	/*     */
	/*     */public CodeValue getIndustry()
	/*     */{
		/* 623 */return this.industry;
		/*     */}

	/*     */
	/*     */public void setIndustry(CodeValue industry)
	/*     */{
		/* 630 */this.industry = industry;
		/*     */}

	/*     */
	/*     */public Area getProvince()
	/*     */{
		/* 637 */return this.province;
		/*     */}

	/*     */
	/*     */public void setProvince(Area province)
	/*     */{
		/* 644 */this.province = province;
		/*     */}
//
//	class CodeValueComparator implements Comparator {
//
//		public int compare(Object o1, Object o2) {
//			CodeValue t1 = (CodeValue) o1;
//			CodeValue t2 = (CodeValue) o2;
//			String name1 = converterToSpell(t1.getName());
//			String name2 = converterToSpell(t2.getName());
//
//			int result = name1.compareTo(name2);
//			return result;
//		}
//
//	}

//	/**
//	 * 汉字转换位汉语拼音，英文字符不变
//	 * 
//	 * @param chines
//	 *            汉字
//	 * @return 拼音
//	 */
//	public static String converterToSpell(String chines) {
//		String pinyinName = "";
//		char[] nameChar = chines.toCharArray();
//		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
//		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		for (int i = 0; i < nameChar.length; i++) {
//			if (nameChar[i] > 128) {
//				try {
//					pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
//				} catch (BadHanyuPinyinOutputFormatCombination e) {
//					e.printStackTrace();
//				}
//			} else {
//				pinyinName += nameChar[i];
//			}
//		}
//		return pinyinName;
//	}
	/*     */
}

/*
 * Location: E:\crm2010\110\crm2009\WEB-INF\classes\ Qualified Name:
 * com.yongjun.
 * tdms.presentation.webwork.action.CustomerRelationship.customerProfiles
 * .EditCustomerInfoAction JD-Core Version: 0.6.2
 */