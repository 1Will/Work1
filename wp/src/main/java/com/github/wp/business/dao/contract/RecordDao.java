package com.github.wp.business.dao.contract;


import com.github.wp.business.pojo.qiandan.HBillingrecord;
import com.github.wp.business.vo.RecordCustom;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;



/**
 * 开票记录管理的dao层：杜鹏
 * Date: 08-23-16
 * Version: 1.0
 */
public interface RecordDao {

	/**
	 * 查询开票记录
	 * @param HBillingrecord
	 * @return
	 * @author 杜鹏
	 * @since 08-17-16
	 */
	
	public Pager<RecordCustom> findPage1(String code,String contractname,String customCode,String name, Pagination pagination);
	
	public HBillingrecord findOne(Long id);
	
	public void deleteOne(Long[] ids);
	
	public void saveOrUpdate(HBillingrecord record);
}
