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
package com.yongjun.tdms.dao.year.tooling;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.year.tooling.YearPlanDetailView;

/**
 * <p>Title: EditDailyRepairAction
 * <p>Description: 运维计划的日常维修维护页面访问控制类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public interface YearPlanDetailViewDao {
	List<YearPlanDetailView> loadYearPlanDetail(Long yearPlanId);
//	List<YearPlanDetailView>loadYearPlanDetailYear(String year,String departName );
//	List<YearPlanDetailView>loadYearPlanDetailAndYear(String year);
	public List<YearPlanDetailView> loadYearPlanDetailView(String[] array) throws HibernateException;
}
