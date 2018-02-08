package com.yongjun.tdms.service.CustomerRelationship.customerProfiles.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
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
	private final YongJunSequenceManager yongJunSequenceManager;
	private final UserManager userManager;
	private ContactArchivesDao contactArchivesDao;
	private final CodeValueManager codeValueManager;
    private final DepartmentManager departmentManager;
	public DefaultCustomerInfoManager(CustomerInfoDao customerInfoDao, YongJunSequenceManager yongJunSequenceManager,
			UserManager userManager,ContactArchivesDao contactArchivesDao,CodeValueManager codeValueManager,DepartmentManager departmentManager) {
		this.customerInfoDao = customerInfoDao;
		this.yongJunSequenceManager = yongJunSequenceManager;
		this.userManager = userManager;
		this.contactArchivesDao = contactArchivesDao;
		this.codeValueManager=codeValueManager;
		this.departmentManager=departmentManager;
	}

	public void storeCustomerInfo(CustomerInfo ci) {
		if (ci.isNew()) {
			String toCode="MP";
			if(ci.getBusinessType().getCode().equals("21001")){
				 toCode="JP";
			}
			String code1 =(String)this.yongJunSequenceManager.generateeCodeTypeReplacFormtter(YongJunSequenceConstant.CODE_CUSTOMER,toCode);
			ci.setCode(code1);
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
	public void saveCustomerInfoByImp(List<CustomerInfo> customerInfos) throws DaoException {
		
		if(customerInfos!=null&&customerInfos.size()>0){
			for(CustomerInfo cu:customerInfos){
				CustomerInfo cuTmep=null;
				if(cu.getCode()==null||cu.getCode().equals("")){
					String toCode="MP";
					if(cu.getBusinessType().getCode().equals("21001")){
						 toCode="JP";
					}
					String code1 =(String)this.yongJunSequenceManager.generateeCodeTypeReplacFormtter(YongJunSequenceConstant.CODE_CUSTOMER,toCode);
					cu.setCode(code1);
				}
				   List list = this.customerInfoDao.loadByKey("name", cu.getName());
				   if(list!=null&&list.size()>0){
//					   cu=(CustomerInfo) list.get(0);
					   cuTmep =(CustomerInfo) list.get(0);
				   }else {
					   this.customerInfoDao.storeCustomerInfo(cu);//插入客户档案
				 }
				   
					ContactArchives caArchives  = new ContactArchives();
					setInfoIntegrity(cu);
					caArchives.setName(cu.getKeyContacter());
					caArchives.setMobilePhone(cu.getMobilePhone());
					if(list!=null&&list.size()>0){
					caArchives.setCustomerName(cuTmep);
					caArchives.setCustomerInfoCode(cuTmep.getCode());
					caArchives.setCustName(cuTmep.getName());
					}else {
						caArchives.setCustomerName(cu);
						caArchives.setCustomerInfoCode(cu.getCode());
						caArchives.setCustName(cu.getName());
					}
					CodeValue cd = new CodeValue();
					cd.setId(170l);
					caArchives.setType(cd);//熟悉程度默认为一般的
					contactArchivesDao.storeContactArchives(caArchives);//将客户的主要联系人插入联系人表中
					
				
			}
			
		}
		
		
		
	}

	public List<Department> getAllClassification(String Btype) {
		try {
		List<Department>mpList = new ArrayList<Department>();
		Long bt=Long.parseLong(Btype);
		List<CodeValue> vs =this.codeValueManager.loadByKey("id", bt.longValue());
		CodeValue c =vs.get(0);
		if(c.getCode().equals("21001")){
			mpList = departmentManager.loadByKey("parentDept.name","军品销售");
		}
		if(c.getCode().equals("21002")){
			mpList = departmentManager.loadByKey("parentDept.name","民品销售");
		}
		 
		 List<Department> departmentList = new ArrayList<Department>();
		 for(Department d: mpList){
			 Department de = new Department();
			 de.setId(d.getId());
			 de.setName(d.getName());
			 departmentList.add(de);
		 }
		return departmentList;
	} catch (Exception e) {
		e.printStackTrace();
		return new ArrayList();
	}
}
	} 

