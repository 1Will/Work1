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
package com.yongjun.tdms.service.asset.spare.inAndOutAndStoreReport;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReport;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutAndStoreReportView;
import com.yongjun.tdms.model.asset.spare.spareInAndOut.SpareInAndOutStoreMonthReportView;

public interface SpareInAndOutAndStoreReportManager {
	/**
	 * 获得收发存报表的集合
	 * @return List
	 */
	@Transactional
	List<SpareInAndOutAndStoreReport> loadAllSpareInAndOut();
    /**
     * 根据页面传来的参数,获得备件收发存日报表的集合
     * @param array
     * @return
     * @throws HibernateException
     */  
	List<SpareInAndOutAndStoreReportView> getSpareInAndOutAndStoreViewCollention(String[] array) throws HibernateException;
	/**
	 * 根据页面传来的参数,获得备件收发存月报表的集合
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	List<SpareInAndOutStoreMonthReportView> getSpareInAndOutAndStoreMonthViewCollention(String[] array) throws HibernateException;
}
