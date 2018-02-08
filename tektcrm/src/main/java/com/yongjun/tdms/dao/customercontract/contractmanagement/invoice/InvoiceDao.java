package com.yongjun.tdms.dao.customercontract.contractmanagement.invoice;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.Invoices;

public interface InvoiceDao {
	/**
	 * @function 修改/保存发货单
	 * @param in 需要修改/保存的发货单实体类
	 */
	public void storeInvoices(Invoices in);
	
	/**
	 * @function 根据附加标识查询指定发货单
	 * @param inId 发货单标识
	 * @return 发货单实体类
	 */
	public Invoices loadInvoices(Long inId);
	
	/**
	 * @function 根据附加标识集合查询指定发货单集合
	 * @return 发货单实体类集合
	 */
	public List<Invoices> loadAllInvoices();
	
	/**
	 * @function 根据附加标识集合查询指定发货单集合
	 * @param inIds 发货单标识集合
	 * @return 发货单实体类集合
	 */
	public List<Invoices> loadAllInvoices(Long[] inIds);
	
	/**
	 * @function 根据标识删除指定的发货单
	 * @param in 需要删除的发货单实体类
	 */
	public void deleteInvoices(Invoices in);
	
	/**
	 * @function 根据标识集合删除指定的发货单集合
	 * @param inId 发货单标识集合
	 */
	public void deleteAllInvoices(List<Invoices> inIds);
	
	/**
	 * @function 根据非主键列查询发货单
	 * @param key 列键
	 * @param value 列值
	 * @return 发货单集合
	 */
	public List<Invoices> loadByKey(String key, Object value) throws DaoException;
}
