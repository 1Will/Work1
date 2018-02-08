/**
 * 
 */
package com.yongjun.tdms.dao.asset.spare.spareInAndOut.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;

import com.yongjun.tdms.dao.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportDao;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReport;

/**
 * <p>Title: HibernateSpareInAndOutAndStoreReport
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: HibernateSpareInAndOutAndStoreReport.java 2009-3-1 下午03:15:01Z yli$
 * @see com.yongjun.tdms.dao.asset.spare.HibernateSpareInAndOutAndStoreReport.hibernate
 */
public class HibernateSpareInAndOutAndStoreReport extends BaseHibernateDao implements
		SpareInAndOutAndStoreReportDao {
	public List<SpareInAndOutAndStoreReport> loadAllSpareInAndOut() {
		return this.loadAll(SpareInAndOutAndStoreReport.class);
	}

}
