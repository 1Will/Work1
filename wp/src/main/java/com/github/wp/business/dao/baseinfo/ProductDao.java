package com.github.wp.business.dao.baseinfo;

import com.github.wp.business.pojo.product.Product;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

public interface ProductDao {

	Pager<Product> findPage(Product t, Pagination pagination);
	public void saveOrUpdate(Product p);

	/**
	 * 根据id查询产品
	 * @param id
	 * @return
	 */
	public Product getProductByid(long id );
	/**
	 * 删除产品
	 * @param ids
	 */
	public void deleteProduct(Long [] ids);
}
