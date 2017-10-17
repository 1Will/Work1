package com.github.wp.business.dao.contract;


import com.github.wp.business.pojo.qiandan.Hcontractmanagement;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;



/**
 * <p>
 * 合同管理的dao层：杜鹏
 * <p>
 * Date: 08-11-16
 * <p>
 * Version: 1.0
 */
public interface ContractDao {

	/**
	 * 查询分页数据
	 * @param Bcustomerinfo
	 * @return
	 * @author 杜鹏
	 * @since 08-09-16
	 */
	public Pager<Hcontractmanagement> findPage(Hcontractmanagement contract, Pagination pagination);
	
	/**
	 * @param id
	 * @return Hcontractmanagement 客户信息
	 * @author dupeng
	 * @since 08-09-16
	 */
	public Hcontractmanagement findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(Hcontractmanagement contract);
	
}
