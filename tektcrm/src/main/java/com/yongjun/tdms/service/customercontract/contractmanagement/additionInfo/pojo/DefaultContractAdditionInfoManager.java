package com.yongjun.tdms.service.customercontract.contractmanagement.additionInfo.pojo;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.customercontract.contractmanagement.additionalInfo.ContractAdditionalInfoDao;
import com.yongjun.tdms.model.customercontract.contractmanagement.additionalInfo.ContractAdditionalInfo;
import com.yongjun.tdms.service.customercontract.contractmanagement.additionInfo.ContractAdditionInfoManager;

public class DefaultContractAdditionInfoManager extends BaseManager implements ContractAdditionInfoManager {

	// 附加信息数据访问层接口
	private final ContractAdditionalInfoDao additionalInfoDao;

	/**
	 * @function 附加信息业务接口实现类构造函数
	 * @param additionalInfoDao
	 *            附加信息数据访问层接口
	 */
	public DefaultContractAdditionInfoManager(ContractAdditionalInfoDao additionalInfoDao) {
		this.additionalInfoDao = additionalInfoDao;
	}

	/**
	 * @function 修改/保存附加信息
	 * @param ai
	 *            需要修改/保存的附加信息实体类
	 */
	public void storeContractAdditionalInfo(ContractAdditionalInfo ai) {
		this.additionalInfoDao.storeContractAdditionalInfo(ai);
	}

	/**
	 * @function 根据附加标识查询指定附加信息
	 * @param aiId
	 *            附加信息标识
	 * @return 附加信息实体类
	 */
	public ContractAdditionalInfo loadContractAdditionalInfo(Long aiId) {
		return this.additionalInfoDao.loadContractAdditionalInfo(aiId);
	}

	/**
	 * @function 根据附加标识集合查询指定附加信息集合
	 * @return 附加信息实体类集合
	 */
	public List<ContractAdditionalInfo> loadAllContractAdditionalInfo() {
		return this.additionalInfoDao.loadAllContractAdditionalInfo();
	}

	/**
	 * @function 根据附加标识集合查询指定附加信息集合
	 * @param aiIds
	 *            附加信息标识集合
	 * @return 附加信息实体类集合
	 */
	public List<ContractAdditionalInfo> loadAllContractAdditionalInfo(Long[] aiIds) {
		return this.additionalInfoDao.loadAllContractAdditionalInfo(aiIds);
	}

	/**
	 * @function 根据标识删除指定的附加信息
	 * @param ai
	 *            需要删除的附加信息实体类
	 */
	public void deleteContractAdditionalInfo(ContractAdditionalInfo ai) {
		this.additionalInfoDao.deleteContractAdditionalInfo(ai);
	}

	/**
	 * @function 根据标识集合删除指定的附加信息集合
	 * @param aiId
	 *            附加信息标识集合
	 */
	public void deleteAllContractAdditionalInfo(List<ContractAdditionalInfo> aiIds) {
		this.additionalInfoDao.deleteAllContractAdditionalInfo(aiIds);
	}

	/**
	 * @function 根据非主键列查询附加信息
	 * @param key
	 *            列键
	 * @param value
	 *            列值
	 * @return 附加信息集合
	 */
	public List<ContractAdditionalInfo> loadByKey(String key, Object value) throws DaoException {
		return this.additionalInfoDao.loadByKey(key, value);
	}
	
	public List<String> getBankInfo(Long contractManagementId){
		List<String> bank =new ArrayList<String>();
		try {
			List<ContractAdditionalInfo> contractAdditionalInfos = this.additionalInfoDao.loadByKey("contractManagement.id",contractManagementId);
			if(contractAdditionalInfos!=null){
				bank.add(contractAdditionalInfos.get(0).getBank());
				bank.add(contractAdditionalInfos.get(0).getBankAccount());
			}
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return bank;
	}
}