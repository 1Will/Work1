package com.github.wp.business.dao.custom;


import com.github.wp.business.pojo.custom.Bcontactarchives;
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
public interface ContactDao {

	/**
	 * 查询分页数据
	 * @param Bcontactarchives
	 * @return
	 * @author 杜鹏
	 * @since 08-03-16
	 */
	public Pager<Bcontactarchives> findPage(Bcontactarchives contact, Pagination pagination);
	
	/**
	 * @param id
	 * @return Bcontactarchives 部门
	 * @author dupeng
	 * @since 08-08-16
	 */
	public Bcontactarchives findOne(Long id);
	
	/**
	 * @param Bcontactarchives
	 * @return Bcontactarchives 保存或新增
	 * @author dupeng
	 * @since 08-08-16
	 */
	public void saveOrUpdate(Bcontactarchives contact);
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 08-08-16
	 * 根据id得到Bcontactarchives对象，改变状态为D，保存，假删除
	 */
	public void deleteOne(Long[] ids);
	
	
	
}
