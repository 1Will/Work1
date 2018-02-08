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
package com.yongjun.tdms.dao.year.device.runmaintainPlan.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.device.runmaintainPlan.PricisionCheckDao;
import com.yongjun.tdms.model.year.device.runmaintainPlan.PricisionCheck;

/**
 * 
 * <p>Title: HibernatePricisionCheck
 * <p>Description: 运维计划的精度检查数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.device.runmaintainPlan.PricisionCheckDao
 * @version $Id:$
 */
public class HibernatePricisionCheck extends BaseHibernateDao implements
		PricisionCheckDao {

	public PricisionCheck loadPricisionCheck(Long pricisionCheckId) {
		return this.load(PricisionCheck.class, pricisionCheckId);
	}

	public List<PricisionCheck> loadAllPricisionChecks(Long[] pricisionCheckIds) {
		return this.loadAll(PricisionCheck.class, pricisionCheckIds);
	}

	public List<PricisionCheck> loadAllPricisionChecks() {
		return this.loadAll(PricisionCheck.class);
	}

	public void storePricisionCheck(PricisionCheck pricisionCheck) {
		this.store(pricisionCheck);
	}

	public void deletePricisionCheck(PricisionCheck pricisionCheck) {
		this.delete(pricisionCheck);
	}

	public void deleteAllPricisionChecks(Collection<PricisionCheck> pricisionChecks) {
		this.deleteAll(pricisionChecks);
	}

}
