package main.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import main.dao.CodeValueDao;
import main.dao.ContractManagementDao;
import main.dao.CustomerInfoDao;
import main.dao.FinancialManagementDao;
import main.dao.ProjectInfoDao;
import main.pojo.CodeValue;
import main.pojo.ContractManagement;
import main.pojo.CustomerInfo;
import main.pojo.EventNew;
import main.pojo.FinancialManagement;
import main.pojo.ProjectInfo;
import main.service.ChangStateService;
import net.sf.json.JSONObject;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ChangStateServiceImpl implements ChangStateService {
	private ProjectInfoDao projectInfoDao;
	private CustomerInfoDao customerInfoDao;
	private CodeValueDao codeValueDao;
	private ContractManagementDao contractManagementDao;
	private FinancialManagementDao financialManagementDao;
	Logger logger = Logger.getRootLogger();
	public Map getEventMap(EventNew event) {
		String moreInfo = event.getMoreinfo();// 得到传递的参数，解析成map
		Map<String, Object> map = new HashMap<String, Object>();
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		return map;
	}

	@Override
	public void setStateByProject(EventNew event) {
		Map<String, Object> map = getEventMap(event);
		Long projectInfoId = Long.parseLong((String) map.get("projectInfoId"));
		if (projectInfoId != null) {
			ProjectInfo projectInfo = projectInfoDao.getById(projectInfoId);
			CustomerInfo customerInfo = projectInfo.getCustomerId();
			CodeValue code = customerInfo.getCustomerType();
			CodeValue newccode = codeValueDao.getCodeValueByCode("0013");
			if ("0011".equals(code.getCode()) || "0012".equals(code.getCode())) {
				customerInfo.setCustomerType(newccode);
				logger.info("修改客户状态为："+newccode.getName());
			}
			this.customerInfoDao.updateCustomerInfo(customerInfo);
		}
	}

	@Override
	public void setStateByContract(EventNew event) {
		Map<String, Object> map = getEventMap(event);
		Long contractManagementId = Long.parseLong((String) map.get("contractManagementId"));
		if (contractManagementId != null) {
			ContractManagement contractManagement = contractManagementDao
					.getContractManagementById(contractManagementId);
			ProjectInfo projectInfo = contractManagement.getProject();
			CustomerInfo customerInfo = contractManagement.getCustomerInfo();
			CodeValue pcode = codeValueDao.getCodeValueById(projectInfo.getState());
			CodeValue newpcode = codeValueDao.getCodeValueByCode("20102");
			CodeValue ccode = customerInfo.getCustomerType();
			CodeValue newccode = codeValueDao.getCodeValueByCode("0014");
			if ("20101".equals(pcode.getCode())) {
				projectInfo.setState(newpcode.getId());
				logger.info("修改项目状态为："+newpcode.getName());
			}
			if ("0013".equals(ccode.getCode())) {
				customerInfo.setCustomerType(newccode);
				logger.info("修改客户状态为："+newccode.getName());
			}
			this.projectInfoDao.updateProjectInfo(projectInfo);
			this.customerInfoDao.updateCustomerInfo(customerInfo);
		}
	}

	@Override
	public void setStateByFinancial(EventNew event) {
		Map<String, Object> map = getEventMap(event);
		Long financialManagementId = Long.parseLong((String) map.get("financialManagementId"));
		if (financialManagementId != null) {
			FinancialManagement financialManagement = financialManagementDao
					.getFinancialManagementById(financialManagementId);
			ContractManagement contractManagement = financialManagement.getContractManagement();
			ProjectInfo projectInfo = contractManagement.getProject();
			CustomerInfo customerInfo = contractManagement.getCustomerInfo();
			CodeValue pcode = codeValueDao.getCodeValueById(projectInfo.getState());
			CodeValue newpcode = codeValueDao.getCodeValueByCode("20103");
			CodeValue ccode = customerInfo.getCustomerType();
			CodeValue newccode = codeValueDao.getCodeValueByCode("0015");
			if ("20102".equals(pcode.getCode())) {
				projectInfo.setState(newpcode.getId());
				logger.info("修改项目状态为："+newpcode.getName());
			}
			if ("0014".equals(ccode.getCode())) {
				customerInfo.setCustomerType(newccode);
				logger.info("修改客户状态为："+newccode.getName());
			}
			this.projectInfoDao.updateProjectInfo(projectInfo);
			this.customerInfoDao.updateCustomerInfo(customerInfo);
		}
	}

	public ProjectInfoDao getProjectInfoDao() {
		return projectInfoDao;
	}

	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}

	public CustomerInfoDao getCustomerInfoDao() {
		return customerInfoDao;
	}

	public void setCustomerInfoDao(CustomerInfoDao customerInfoDao) {
		this.customerInfoDao = customerInfoDao;
	}

	public CodeValueDao getCodeValueDao() {
		return codeValueDao;
	}

	public void setCodeValueDao(CodeValueDao codeValueDao) {
		this.codeValueDao = codeValueDao;
	}

	public ContractManagementDao getContractManagementDao() {
		return contractManagementDao;
	}

	public void setContractManagementDao(ContractManagementDao contractManagementDao) {
		this.contractManagementDao = contractManagementDao;
	}

	public FinancialManagementDao getFinancialManagementDao() {
		return financialManagementDao;
	}

	public void setFinancialManagementDao(FinancialManagementDao financialManagementDao) {
		this.financialManagementDao = financialManagementDao;
	}
}
