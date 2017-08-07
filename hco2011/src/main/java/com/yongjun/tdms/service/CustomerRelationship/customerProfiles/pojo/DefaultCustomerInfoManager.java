package com.yongjun.tdms.service.CustomerRelationship.customerProfiles.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao;
import com.yongjun.tdms.dao.CustomerRelationship.customerProfiles.CustomerInfoDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;

public class DefaultCustomerInfoManager extends BaseManager implements CustomerInfoManager {
	private final CustomerInfoDao customerInfoDao;
	private final SequenceManager ciSequenceManager;
	private final UserManager userManager;
	private ContactArchivesDao contactArchivesDao;

	public DefaultCustomerInfoManager(CustomerInfoDao customerInfoDao, SequenceManager ciSequenceManager,
			UserManager userManager,ContactArchivesDao contactArchivesDao) {
		this.customerInfoDao = customerInfoDao;
		this.ciSequenceManager = ciSequenceManager;
		this.userManager = userManager;
		this.contactArchivesDao = contactArchivesDao;
	}

	public void storeCustomerInfo(CustomerInfo ci) {
		if (ci.isNew()) {
			ci.setCode((String) this.ciSequenceManager.generate("-"));
		}

		setInfoIntegrity(ci);
		this.customerInfoDao.storeCustomerInfo(ci);
	}

	public CustomerInfo loadCustomerInfo(Long ciId) {
		return this.customerInfoDao.loadCustomerInfo(ciId);
	}

	public List<CustomerInfo> loadAllCustomerInfo() {
		return this.customerInfoDao.loadAllCustomerInfo();
	}

	public List<CustomerInfo> loadAllCustomerInfo(Long[] ciIds) {
		return this.customerInfoDao.loadAllCustomerInfo(ciIds);
	}

	public void deleteCustomerInfo(CustomerInfo ci) {
		this.customerInfoDao.deleteCustomerInfo(ci);
	}

	public void deleteAllCustomerInfo(List<CustomerInfo> ciIds) {
		this.customerInfoDao.deleteAllCustomerInfo(ciIds);
	}

	public List<CustomerInfo> loadByKey(String key, Object value) throws DaoException {
		return this.customerInfoDao.loadByKey(key, value);
	}

	public void disabledAllCustomerInfo(List<CustomerInfo> cis) {
		for (CustomerInfo customerInfo : cis) {
			customerInfo.setDisabled(true);
			this.customerInfoDao.storeCustomerInfo(customerInfo);
		}
	}

	public void enabledAllCustomerInfo(List<CustomerInfo> cis) {
		for (CustomerInfo customerInfo : cis) {
			customerInfo.setDisabled(false);
			this.customerInfoDao.storeCustomerInfo(customerInfo);
		}
	}

	public List<CustomerInfo> getCustomerList(String advisoryName) {
		List<CustomerInfo> allAdvisory = this.customerInfoDao.getCustomerByName(advisoryName);

		List list = new ArrayList();
		if (null!= allAdvisory && allAdvisory.size() > 0) {
			for (CustomerInfo info : allAdvisory) {
				CustomerInfo c = new CustomerInfo();
				c.setId(info.getId());
				c.setCode(info.getCode());
				c.setName(info.getName());
				c.setAbbreviations(info.getAbbreviations());
				c.setLegalPerson(info.getLegalPerson());
				c.setKeyContacter(info.getKeyContacter());
				c.setTelphone(info.getTelphone());
				c.setMobilePhone(info.getMobilePhone());
				c.setFax(info.getFax());
				c.setEmail(info.getEmail());
				c.setQq(info.getQq());
				c.setSetupTime(info.getSetupTime());
				c.setRegisteredCapital(info.getRegisteredCapital());
				c.setPersonCount(info.getPersonCount());
				c.setSaleman(info.getSaleman());
				c.setPostCode(info.getPostCode());
				c.setAddress(info.getAddress());
				c.setBusinessScope(info.getBusinessScope());
				c.setStep(info.getStep());
				c.setCustomerType(info.getCustomerType());
				if (info.getLegalPerson() == null) {
					c.setLegalPerson("");
				}
				if (info.getStep() != null) {
					CodeValue ct = new CodeValue();
					ct.setId(info.getStep().getId());
					c.setStep(ct);
				}
				if (info.getCustomerType() != null) {
					CodeValue ct = new CodeValue();
					ct.setId(info.getCustomerType().getId());
					c.setCustomerType(ct);
				}
				if (info.getIndustry() != null) {
					CodeValue ind = new CodeValue();
					ind.setId(info.getIndustry().getId());
					c.setIndustry(ind);
				}
				if (info.getCompanyNature() != null) {
					CodeValue cn = new CodeValue();
					cn.setId(info.getCompanyNature().getId());
					c.setCompanyNature(cn);
				}
				if (info.getCountry() != null) {
					Area cou = new Area();
					cou.setId(info.getCountry().getId());
					c.setCountry(cou);
				}
				if (info.getProvince() != null) {
					Area pro = new Area();
					pro.setId(info.getProvince().getId());
					c.setProvince(pro);
				}
				if (info.getCity() != null) {
					Area city = new Area();
					city.setId(info.getCity().getId());
					c.setCity(city);
				}
				list.add(c);
			}
		}
		return list;
	}

	public List<CustomerInfo> getOneCustomerByName(String advisoryName) {
		List<CustomerInfo> allAdvisory = this.customerInfoDao.getOneCustomerByName(advisoryName);

		List list = new ArrayList();
		if (allAdvisory.size() > 0) {
			for (CustomerInfo info : allAdvisory) {
				CustomerInfo c = new CustomerInfo();
				c.setId(info.getId());
				c.setCode(info.getCode());
				c.setName(info.getName());
				c.setAbbreviations(info.getAbbreviations());
				c.setLegalPerson(info.getLegalPerson());
				c.setKeyContacter(info.getKeyContacter());
				c.setTelphone(info.getTelphone());
				c.setMobilePhone(info.getMobilePhone());
				c.setFax(info.getFax());
				c.setEmail(info.getEmail());
				c.setQq(info.getQq());
				c.setSetupTime(info.getSetupTime());
				c.setRegisteredCapital(info.getRegisteredCapital());
				c.setPersonCount(info.getPersonCount());
				c.setSaleman(info.getSaleman());
				c.setPostCode(info.getPostCode());
				c.setAddress(info.getAddress());
				c.setBusinessScope(info.getBusinessScope());
				c.setStep(info.getStep());
				c.setCustomerType(info.getCustomerType());
				if (info.getLegalPerson() == null) {
					c.setLegalPerson("");
				}
				if (info.getStep() != null) {
					CodeValue ct = new CodeValue();
					ct.setId(info.getStep().getId());
					c.setStep(ct);
				}
				if (info.getCustomerType() != null) {
					CodeValue ct = new CodeValue();
					ct.setId(info.getCustomerType().getId());
					c.setCustomerType(ct);
				}
				if (info.getIndustry() != null) {
					CodeValue ind = new CodeValue();
					ind.setId(info.getIndustry().getId());
					c.setIndustry(ind);
				}
				if (info.getCompanyNature() != null) {
					CodeValue cn = new CodeValue();
					cn.setId(info.getCompanyNature().getId());
					c.setCompanyNature(cn);
				}
				if (info.getCountry() != null) {
					Area cou = new Area();
					cou.setId(info.getCountry().getId());
					c.setCountry(cou);
				}
				if (info.getProvince() != null) {
					Area pro = new Area();
					pro.setId(info.getProvince().getId());
					c.setProvince(pro);
				}
				if (info.getCity() != null) {
					Area city = new Area();
					city.setId(info.getCity().getId());
					c.setCity(city);
				}
				list.add(c);
			}
		}
		return list;
	}

	public void setInfoIntegrity(CustomerInfo customerInfo) {
		float base = 60.0F;
		if ((null != customerInfo.getAbbreviations()) && (!"".equals(customerInfo.getAbbreviations()))) {
			base += 3.0F;
		}

		if ((null != customerInfo.getLegalPerson()) && (!"".equals(customerInfo.getLegalPerson()))) {
			base += 3.0F;
		}

		if ((null != customerInfo.getRegisteredCapital())
				&& (0.0D != customerInfo.getRegisteredCapital().doubleValue())) {
			base += 5.0F;
		}

		if ((null != customerInfo.getPersonCount()) && (0 != customerInfo.getPersonCount().intValue())) {
			base += 3.0F;
		}
		if (null != customerInfo.getSetupTime()) {
			base += 3.0F;
		}
		if ((null != customerInfo.getPostCode()) && (!"".equals(customerInfo.getPostCode()))) {
			base += 3.0F;
		}
		if ((null != customerInfo.getAddress()) && (!"".equals(customerInfo.getAddress()))) {
			base += 3.0F;
		}
		if ((null != customerInfo.getMobilePhone()) && (!"".equals(customerInfo.getMobilePhone()))) {
			base += 4.0F;
		}
		if ((null != customerInfo.getFax()) && (!"".equals(customerInfo.getFax()))) {
			base += 3.0F;
		}

		if ((null != customerInfo.getEmail()) && (!"".equals(customerInfo.getEmail()))) {
			base += 3.0F;
		}
		if ((null != customerInfo.getQq()) && (!"".equals(customerInfo.getQq()))) {
			base += 3.0F;
		}
		if ((null != customerInfo.getBusinessScope()) && (!"".equals(customerInfo.getBusinessScope()))) {
			base += 4.0F;
		}
		customerInfo.setCustomerInfoIntegrity(Float.valueOf(base));
	}

	public int loadCustomerInfoByDate(String userId, String da) {
		User user = userManager.loadUser(Long.parseLong(userId));
		List<CustomerInfo> list = this.customerInfoDao.getCustomerByCodeAndDate(da, user.getCode());
		int size = list.size();
		return size;
		// List<CustomerInfo> cList =new ArrayList<CustomerInfo>();
		// for(CustomerInfo cInfo :list){
		// CustomerInfo customerInfo =new CustomerInfo();
		// customerInfo.setId(cInfo.getId());
		// cList.add(customerInfo);
		// }
		// return cList;
	}
	public void saveCustomerInfoByImp(List<CustomerInfo> customerInfos) {
		
		if(customerInfos!=null&&customerInfos.size()>0){
			for(CustomerInfo cu:customerInfos){
				if(cu.getCode()==null){
					cu.setCode((String)this.ciSequenceManager.generate("-"));
				}
					this.customerInfoDao.storeCustomerInfo(cu);//插入客户档案
					ContactArchives caArchives  = new ContactArchives();
					setInfoIntegrity(cu);
					caArchives.setName(cu.getKeyContacter());
					caArchives.setCustomerName(cu);
					caArchives.setMobilePhone(cu.getMobilePhone());
					caArchives.setCustomerInfoCode(cu.getCode());
					caArchives.setCustName(cu.getName());
					CodeValue cd = new CodeValue();
					cd.setId(170l);
					caArchives.setType(cd);//熟悉程度默认为一般的
					contactArchivesDao.storeContactArchives(caArchives);//将客户的主要联系人插入联系人表中
					
				
			}
			
		}
		
		
		
	} 
}
