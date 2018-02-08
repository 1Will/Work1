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
package com.yongjun.tdms.service.asset.spare;

import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.tdms.model.asset.spare.SpareDetailView;

public interface SpareDetailViewManager {
	/**
	 * 根据页面传入的参数,从备件明细视图中获得备件台帐明细的集合 
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	List<SpareDetailView> loadSpareDetailAccount(String[] array)throws HibernateException;
	/**
	 * 根据页面传入的参数,从备件明细视图中获得备件台帐明细的集合 zzb
	 * @param array
	 * @return
	 * @throws HibernateException
	 */
	List<SpareDetailView> loadSpareLocation(String[] array)throws HibernateException;
}
