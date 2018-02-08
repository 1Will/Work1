package com.yongjun.tdms.dao.customercontract.contractmanagement.additionalInfo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.additionalInfo.ContractAdditionalInfo;

/**
 * <p>Title: ContractAdditionalInfoDao
 * <p>Description: 附加信息数据访问层�接口</p>
 * <p>Copyright: Copyright (c) 2009 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author wliu@yj-technology.com</p>
 * <p>@version $Id: ContractAdditionalInfoDao.java 2009-12-7 14:50:03Z wliu $</p>
 */
public interface ContractAdditionalInfoDao {

	/**
	 * @function 修改/保存附加信息
	 * @param ai 需要修改/保存的附加信息实体类
	 */
	public void storeContractAdditionalInfo(ContractAdditionalInfo ai);
	
	/**
	 * @function 根据附加标识查询指定附加信息
	 * @param aiId 附加信息标识
	 * @return 附加信息实体类
	 */
	public ContractAdditionalInfo loadContractAdditionalInfo(Long aiId);
	
	/**
	 * @function 根据附加标识集合查询指定附加信息集合
	 * @return 附加信息实体类集合
	 */
	public List<ContractAdditionalInfo> loadAllContractAdditionalInfo();
	
	/**
	 * @function 根据附加标识集合查询指定附加信息集合
	 * @param aiIds 附加信息标识集合
	 * @return 附加信息实体类集合
	 */
	public List<ContractAdditionalInfo> loadAllContractAdditionalInfo(Long[] aiIds);
	
	/**
	 * @function 根据标识删除指定的附加信息
	 * @param ai 需要删除的附加信息实体类
	 */
	public void deleteContractAdditionalInfo(ContractAdditionalInfo ai);
	
	/**
	 * @function 根据标识集合删除指定的附加信息集合
	 * @param aiId 附加信息标识集合
	 */
	public void deleteAllContractAdditionalInfo(List<ContractAdditionalInfo> aiIds);
	
	/**
	 * @function 根据非主键列查询附加信息
	 * @param key 列键
	 * @param value 列值
	 * @return 附加信息集合
	 */
	public List<ContractAdditionalInfo> loadByKey(String key, Object value) throws DaoException;
}

