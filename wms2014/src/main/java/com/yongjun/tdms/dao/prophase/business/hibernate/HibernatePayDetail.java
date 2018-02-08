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
package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.PayDetailDao;
import com.yongjun.tdms.model.prophase.business.PayDetail;

/**
 * @author qs
 * @version $Id: HibernatePayDetail.java 11279 2008-03-12 01:12:13Z mwei $
 */
public class HibernatePayDetail extends BaseHibernateDao implements
		PayDetailDao {

	public PayDetail loadPayDetail(Long id) {
		return load(PayDetail.class, id);
	}

	public void storePayDetail(PayDetail payDetail) {
		store(payDetail);
	}

	public List<PayDetail> loadPayDetails(Long[] PayDetailIds) {
		return this.loadAll(PayDetail.class,PayDetailIds);
	}

	public void deletePayDetail(PayDetail payDetail) {
		this.delete(payDetail);
		
	}

	public void deleteAllPayDetail(Collection<PayDetail> payDetailIds) {
		this.deleteAll(payDetailIds);
		
	}

}
