package com.yongjun.tdms.dao.asset.spare.spareInAndOut;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutStoreMonthReportView;

/**
 * <p>Title: SpareInAndOutDao
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SpareInAndOutDao.java 2009-3-1 下午03:09:01Z yli$
 * @see com.yongjun.tdms.dao.asset.spare.spareInAndOut
 */
public interface SpareInAndOutAndStoreReportMonthViewDao {
	/**
	 * 根据页面传来的参数,获得备件收发存报表的月报表的集合
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
 List<SpareInAndOutStoreMonthReportView> getSpareInAndOutAndStoreMonthViewCollention(String []array)throws HibernateException;
}
