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
package com.yongjun.tdms.service.runmaintenance.checkpoint;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReport;
import com.yongjun.tdms.model.runmaintenance.checkpoint.CheckPointReportView;

/**
 * @author wzou
 * @version $Id: CheckPointPlanManager.java 7925 2007-10-22 02:37:55Z qsun $
 */
@Transactional(readOnly = true)
public interface CheckPointReportManager {
	public CheckPointReport loadCheckPointReport(Long checkPointReportID);
	
	@Transactional
	void storeCheckPointReport(CheckPointReport report); 
	
	/**
	 * 生成每月设备点检报告的单
	 */
	@Transactional
	void createReportByScheduler();
	
	/**
	 * 根据传入的字符串数组，查询点检报告视图
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	public List<CheckPointReportView> loadAllCheckPointReportView(String[] array) throws HibernateException;
	
	public List<Long> loadAllIdsByMonth(String month);
}
