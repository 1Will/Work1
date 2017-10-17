package com.github.wp.business.service.baseinfo;

import com.github.wp.business.pojo.product.Product;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;

public interface ProductService {

	public Pager<Product> findPage(Product T, Pagination pagination);
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
