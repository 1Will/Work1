package com.yongjun.tdms.service.customercontract.contractmanagement.invoices.invoicesList;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.model.customercontract.contractmanagement.invoice.invoiceList.InvoicesList;

public interface InvoicesListManager {
	/**
	 * @function 修改/保存发货单明细
	 * @param inList 需要修改/保存的发货单明细实体类
	 */
	public void storeInvoicesList(InvoicesList inList);
	/**
	 * 选择产品，自动生成发货单明细
	 * @param productListId
	 */
	public void saveInvoicesList(String productListId,String invoicesId);
	
	/**
	 * @function 根据附加标识查询指定发货单明细
	 * @param inListId 发货单明细标识
	 * @return 发货单明细实体类
	 */
	public InvoicesList loadInvoicesList(Long inListId);
	
	/**
	 * @function 根据附加标识集合查询指定发货单明细集合
	 * @return 发货单明细实体类集合
	 */
	public List<InvoicesList> loadAllInvoicesList();
	
	/**
	 * @function 根据附加标识集合查询指定发货单明细集合
	 * @param inListIds 发货单明细标识集合
	 * @return 发货单明细实体类集合
	 */
	public List<InvoicesList> loadAllInvoicesList(Long[] inListIds);
	
	/**
	 * @function 根据标识删除指定的发货单明细
	 * @param inList 需要删除的发货单明细实体类
	 */
	public void deleteInvoicesList(InvoicesList inList);
	
	/**
	 * @function 根据标识集合删除指定的发货单明细集合
	 * @param inList 发货单明细标识集合
	 */
	public void deleteAllInvoicesList(List<InvoicesList> inList);
	
	/**
	 * @function 根据非主键列查询发货单明细
	 * @param key 列键
	 * @param value 列值
	 * @return 发货单明细集合
	 */
	public List<InvoicesList> loadByKey(String key, Object value) throws DaoException;
}
