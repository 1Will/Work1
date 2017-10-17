package com.github.wp.business.dao.custom;


import com.github.wp.business.pojo.custom.Bcustomerinfo;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;



/**
 * <p>
 * 联系人管理的dao层：杜鹏
 * <p>
 * Date: 08-03-16
 * <p>
 * Version: 1.0
 */
public interface CustomDao {

	/**
	 * 查询分页数据
	 * @param Bcustomerinfo
	 * @return
	 * @author 杜鹏
	 * @since 08-09-16
	 */
	public Pager<Bcustomerinfo> findPage(Bcustomerinfo custom, Pagination pagination);
	
	/**
	 * @param id
	 * @return Bcustomerinfo 客户信息
	 * @author dupeng
	 * @since 08-09-16
	 */
	public Bcustomerinfo findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(Bcustomerinfo custom);
	
}
