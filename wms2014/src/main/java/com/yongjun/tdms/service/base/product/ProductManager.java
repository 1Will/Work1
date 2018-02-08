package com.yongjun.tdms.service.base.product;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.product.Product;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;

@Transactional(readOnly = true)
public interface ProductManager {
	Product loadProduct(Long productId);
	
	List<Product> loadAllProducts(Long [] productIds);

	List loadAllProducts();
	
	@Transactional
	void storeProduct(Product product);
	
	@Transactional
	void deleteProduct(Product product);
	
	@Transactional
	void deleteAllProducts(Collection<Product> products);
	
	/**
	 * 根据传入的产品集合，失效集合中的产品
	 * @param products　产品集合
	 */
	@Transactional
	void disabledAllProducts(Collection<Product> products);
	
	/**
	 * 有效传入的产品集合
	 * @param checks 产品集合
	 */
	@Transactional
	void enabledAllProducts(Collection<Product> Products);
	
	/**
	 * 根据传入的"所有"名称，获取带有"所有"字段的公司产品集合
	 * @param name "所有"名称
	 * @return List 带有"所有"字段的公司产品集合
	 */
	public List createSelectProducts(String name);
	
	//根据productNo获取产品编码
	Product getProductByProductNo(String productNo);
	//根据name获取产品名称
	Product getProductByName(String name);
}
