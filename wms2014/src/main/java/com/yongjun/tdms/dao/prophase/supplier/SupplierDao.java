package com.yongjun.tdms.dao.prophase.supplier;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.model.prophase.supplier.Supplier;

/**
 * <p>Title: SupplierDao
 * <p>Description: 供应商据库访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface SupplierDao {
	void storeSupplier(Supplier supplier);

	Supplier loadSupplier(Long supplierIds);

	void deleteSupplier(Supplier supplier);

	void deleteAllSuppliers(Collection<Supplier> supplierIds);

	List<Supplier> loadAllSupplier(Long[] supplierIds);
	
	List<Supplier> loadAllSupplier();
	
	/**
	 * 根据供应商类别获取供应商集合
	 * @param supplierCategoryId 供应商类别
	 * @return List 供应商集合
	 */
	List<Supplier> loadAllExternalHelpSupplier(final Long supplierCategoryId);
	
	/**
	 * 根据供应商编号获取供应商对象
	 * @param code 供应商编号
	 * @return Supplier 供应商对象
	 */
	Supplier getSupplierByCode(String code);
	
	String getMaxSupplierNoBySupplierCode(String code);
	
	List<Supplier> loadSuppliersByName(String name);
	
	Supplier loadSupplierByName(String name);
}
