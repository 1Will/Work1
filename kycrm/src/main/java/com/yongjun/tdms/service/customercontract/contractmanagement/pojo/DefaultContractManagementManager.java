package com.yongjun.tdms.service.customercontract.contractmanagement.pojo;

import java.util.HashMap;
import java.util.List;

import org.springframework.jdbc.LobRetrievalFailureException;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.ContractManagementDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.contractmanagement.houseList.HouseList;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

public class DefaultContractManagementManager extends BaseManager implements ContractManagementManager {
	private final ContractManagementDao dao;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultContractManagementManager(ContractManagementDao dao,YongJunSequenceManager yongJunSequenceManager) {
		this.dao = dao;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeContractManagement(ContractManagement t) {
		if(t.isNew()){
			t.setCode(yongJunSequenceManager.generateByCodeType(YongJunSequenceConstant.CODE_CONTRACTMANAGERMENT));
		}
		this.dao.storeContractManagement(t);
	}
	
	public List<ContractManagement> loadContractManagementByBType(String type){
		return this.dao.loadContractManagementByBType(type);
	}

	public ContractManagement loadContractManagement(Long id) {
		return this.dao.loadContractManagement(id);
	}

	public List<ContractManagement> loadContractManagement() {
		return this.dao.loadContractManagement();
	}
	
	public List<ContractManagement> loadContractManagementByPj(String pjIds){
		return this.dao.loadContractManagementByPj(pjIds);
	}

	public List<ContractManagement> loadAllContractManagement(Long[] tIds) {
		return this.dao.loadAllContractManagement(tIds);
	}

	public void deleteContractManagement(ContractManagement t) {
		this.dao.deleteContractManagement(t);
	}

	public void deleteAllContractManagement(List<ContractManagement> ts) {
		this.dao.deleteAllContractManagement(ts);
	}

	public List<ContractManagement> loadByKey(String key, Object value) throws DaoException {
		return this.dao.loadByKey(key, value);
	}

	public List<ContractManagement> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.dao.loadByKeyArray(keyNames, keyValues);
	}

	public String getMaxPFCode(String code) {
		return this.dao.getMaxPFCode(code);
	}

	public void disabledAllContractManagement(List<ContractManagement> ts) {
		for (ContractManagement contractManagement : ts) {
			contractManagement.setDisabled(true);
			this.dao.storeContractManagement(contractManagement);
		}
	}

	public void enabledAllContractManagement(List<ContractManagement> ts) {
		for (ContractManagement contractManagement : ts) {
			contractManagement.setDisabled(false);
			this.dao.storeContractManagement(contractManagement);
		}
	}

	public double getSumReturnPrice(long id) throws DaoException {
		return this.dao.getSumReturnPrice(id);
	}

	public HashMap getDataMap(String staDate, String endDate) {
		// TODO Auto-generated method stub
		return this.dao.getDataMap(staDate, endDate);
	}
	public List <HouseList> loadHouseListByConId(Long id){
		return this.dao.loadHouseListByConId(id);
		
	}
	
}
