/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.asset.spare.inAndOutAndStoreReport.pojo;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportDao;
import com.yongjun.tdms.dao.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportViewDao;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReport;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportView;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutStoreMonthReportView;
import com.yongjun.tdms.service.asset.spare.inAndOutAndStoreReport.SpareInAndOutAndStoreReportManager;
/**
 * <p>Title: DefaultSpareInAndOutAndStoreReportManager
 * <p>Description: 收发存报表服务控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: DefaultSpareInAndOutAndStoreReportManager.java 9887 2009-03-10 03:11:36Z xschen $
 */
public class DefaultSpareInAndOutAndStoreReportManager extends BaseManager implements SpareInAndOutAndStoreReportManager{
   private final SpareInAndOutAndStoreReportDao spareInAndOutAndStoreReportDao;  //收发存报表数据访问接口类

   private final SpareInAndOutAndStoreReportViewDao spareInAndOutAndStoreReportViewDao;//收发存报表视图数据访问接口类
   public DefaultSpareInAndOutAndStoreReportManager(SpareInAndOutAndStoreReportDao spareInAndOutAndStoreReportDao, 
		   SpareInAndOutAndStoreReportViewDao spareInAndOutAndStoreReportViewDao){
	        this.spareInAndOutAndStoreReportDao=spareInAndOutAndStoreReportDao;
	        this.spareInAndOutAndStoreReportViewDao=spareInAndOutAndStoreReportViewDao;
   }

   /**
    * 获得所有的收发存报表的集合
    */
	public List<SpareInAndOutAndStoreReport> loadAllSpareInAndOut() {
		return spareInAndOutAndStoreReportDao.loadAllSpareInAndOut();
	}

	/**
	 * 通过页面传来的字段获得一个收发存月报表的集合
	 */
	public List<SpareInAndOutAndStoreReportView> getSpareInAndOutAndStoreViewCollention(String[] array) throws HibernateException {
		return spareInAndOutAndStoreReportViewDao.getSpareInAndOutAndStoreViewCollention(array);
	}

	/**
	 * 通过页面传来的字段获得一个收发存月报表的集合
	 */
	public List<SpareInAndOutStoreMonthReportView> getSpareInAndOutAndStoreMonthViewCollention(String[] array) throws HibernateException {
		
		return null;
	}


}
