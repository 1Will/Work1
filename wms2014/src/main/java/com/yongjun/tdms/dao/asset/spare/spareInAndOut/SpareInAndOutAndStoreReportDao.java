/**
 * 
 */
package com.yongjun.tdms.dao.asset.spare.spareInAndOut;


import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReport;

/**
 * <p>Title: SpareInAndOutDao
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SpareInAndOutDao.java 2009-3-1 下午03:09:01Z yli$
 * @see com.yongjun.tdms.dao.asset.spare.spareInAndOut
 */
public interface SpareInAndOutAndStoreReportDao {
	
	List<SpareInAndOutAndStoreReport> loadAllSpareInAndOut();
	
}
