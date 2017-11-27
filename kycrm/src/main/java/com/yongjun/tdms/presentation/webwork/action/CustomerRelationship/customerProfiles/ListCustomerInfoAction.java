package com.yongjun.tdms.presentation.webwork.action.CustomerRelationship.customerProfiles;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.service.security.GroupManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.base.area.Area;
import com.yongjun.tdms.model.base.industryType.Industry;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.base.area.AreaManager;
import com.yongjun.tdms.service.base.industoryType.IndustryManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.util.comparator.CodeValueComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ObjectUtils.Null;

@SuppressWarnings({"unchecked","rawtypes"})
public class ListCustomerInfoAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final CustomerInfoManager customerInfoManager;
	private final CodeValueManager codeValueManager;
	private final AreaManager areaManager;
	private final GroupManager groupManager;
	private List<CustomerInfo> customers;
	private UserManager userManager;
	private PersonnelFilesManager personnelFilesManager;

	public ListCustomerInfoAction(CustomerInfoManager customerInfoManager, CodeValueManager codeValueManager,
			AreaManager areaManager, UserManager userManager, PersonnelFilesManager personnelFilesManager,
			GroupManager groupManager) {
		this.customerInfoManager = customerInfoManager;
		this.codeValueManager = codeValueManager;
		this.areaManager = areaManager;
		this.userManager = userManager;
		this.personnelFilesManager = personnelFilesManager;
		this.groupManager = groupManager;
	}

	public void prepare() throws Exception {
		if ((null == this.customers) && (hasIds("customerInfoIds")))
			this.customers = this.customerInfoManager.loadAllCustomerInfo(getIds("customerInfoIds"));
	}

	protected String getAdapterName() {
		return "customerInfoHQL";
	}


	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
//		PersonnelFiles personnelFiles = null;
//		try {
//			if(isCustomerGroup()){
//				List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
//				if (tempList != null && tempList.size() > 0) {
//					personnelFiles = tempList.get(0);
//					if (personnelFiles.getBusinessType() != null) {
//						if (personnelFiles.getBusinessType().getName().equals("军品")
//								|| personnelFiles.getBusinessType().getName().equals("民品")) {
//							map.put("businessType", "%" + personnelFiles.getBusinessType().getName() + "%");
//						}
//					}
//				}
//			}else {
//				List<PersonnelFiles> tempList = this.personnelFilesManager.loadByKey("code", this.userManager.getUser().getCode());
//				if (tempList != null && tempList.size() > 0) {
//					personnelFiles = tempList.get(0);
//					if (personnelFiles.getDept() != null) {
//						map.put("classificationId", personnelFiles.getDept().getId());
//					}
//				}
//			}
//			
//			
//		} catch (DaoException e) {
//			e.printStackTrace();
//		}
		if (hasId("customerInfo.salesmanName")) {
			User user = this.userManager.loadUser(getId("customerInfo.salesmanName"));
			map.put("customerInfo.salesmanName", user.getName());
		}
		if (this.request.getParameter("customerInfo.createdTime") != null) {
			map.put("customerInfo.createdTime", this.request.getParameter("customerInfo.createdTime") + "%");
		}
		if (hasId("customerInfo.salesman")) {
			map.put("customerInfo.salesmanName", this.request.getParameter("customerInfo.salesman"));
		}

		return map;
	}

	public String execute() throws Exception {
		if (isDisabled()) {
			disabled();
		}
		if (isEnable()) {
			enabled();
		}
		if (isDelete()) {
			delete();
		}
		return "success";
	}

	public String delete() {
		try {
			this.customerInfoManager.deleteAllCustomerInfo(this.customers);
			addActionMessage(getText("customerInfo.delete.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("customerInfo.delete.error"));
		}
		return "error";
	}

	private String disabled() {
		try {
			this.customerInfoManager.disabledAllCustomerInfo(this.customers);
			addActionMessage(getText("customerInfo.disabled.success"));
			return "success";
		} catch (RuntimeException e) {
			addActionMessage(getText("customerInfo.disabled.error"));
		}
		return "error";
	}

	private String enabled() {
		try {
			this.customerInfoManager.enabledAllCustomerInfo(this.customers);
			addActionMessage(getText("customerInfo.enabled.success"));
			return "success";
		} catch (RuntimeException e) {
			e.printStackTrace();
			addActionMessage(getText("customerInfo.enabled.error"));
		}
		return "error";
	}

	public List<CodeValue> getAllTypes() {
		try {
			List codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("001"));
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				codes.addAll(list);
			}
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			List codes = new ArrayList();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		}
	}

	public List<Area> getAllCountrys() {
		try {
			List countrys = new ArrayList();
			List list = this.areaManager.loadByKey("type", "country");
			if ((null != list) && (list.size() > 0)) {
				countrys.addAll(list);
			}
			Area area = new Area();
			area.setId(Long.valueOf(-1L));
			area.setName(getText("select.option.all"));
			countrys.add(0, area);
			return countrys;
		} catch (Exception e) {
			e.printStackTrace();
			List countrys = new ArrayList();
			Area area = new Area();
			area.setId(Long.valueOf(-1L));
			area.setName(getText("select.option.all"));
			countrys.add(0, area);
			return countrys;
		}
	}

	public List<Area> getAllProvince() {
		List province = new ArrayList();
		Area area = new Area();
		area.setId(Long.valueOf(-1L));
		area.setName(getText("select.option.all"));
		province.add(0, area);
		return province;
	}

	public List<Area> getAllCity() {
		List city = new ArrayList();
		Area area = new Area();
		area.setId(Long.valueOf(-1L));
		area.setName(getText("select.option.all"));
		city.add(0, area);
		return city;
	}

	public List<CodeValue> getAllIndustrys() {
		try {
			List industrys = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", Long.valueOf("002"));
			if ((null != one) && (one.size() > 0) && (null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				industrys.addAll(list);
			}
			Collections.sort(industrys, new CodeValueComparator());

			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			industrys.add(0, cv);
			return industrys;
		} catch (Exception e) {
			e.printStackTrace();
			List industrys = new ArrayList();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			industrys.add(0, cv);
			return industrys;
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
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			companyNatures.add(0, cv);
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			List companyNatures = new ArrayList();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			companyNatures.add(0, cv);
			return companyNatures;
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
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
			List codes = new ArrayList();
			CodeValue cv = new CodeValue();
			cv.setId(Long.valueOf(-1L));
			cv.setName(getText("select.option.all"));
			codes.add(0, cv);
			return codes;
		}
	}
	/*public List<Industry> getAllNationalEconomy(){
		
		List<Industry> industrieList = new ArrayList<Industry>();
		System.out.println("1111111111");
		return industrieList;
	
	}*/
	public boolean isCustomerGroup(){
		boolean isCustomerGroup =false;
		Set<User> noticePers = groupManager.getGroupByGroupName("客户管理组").getUsers();
		User user = this.userManager.getUser();
		for (User u : noticePers) {
			System.out.println(u.getId()+"=="+user.getId());
			if(user.getId().longValue() == u.getId().longValue()){
				isCustomerGroup = true;
			}
		}
		return isCustomerGroup;
	}
}
