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
package com.yongjun.tdms.dao.year.tooling.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.year.tooling.TechAlterDetailDao;
import com.yongjun.tdms.model.year.tooling.TechAlterDetail;

/**
 * 
 * <p>Title: HibernateTechAlterDetail
 * <p>Description: 技术改造明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.TechAlterDetailDao
 * @version $Id:$
 */
public class HibernateTechAlterDetail extends BaseHibernateDao implements
		TechAlterDetailDao {

	public TechAlterDetail loadTechAlterDetail(Long techAlterDetailId) {
		return this.load(TechAlterDetail.class, techAlterDetailId);
	}

	public List<TechAlterDetail> loadAllTechAlterDetails(Long[] techAlterDetailIds) {
		return this.loadAll(TechAlterDetail.class, techAlterDetailIds);
	}

	public List<TechAlterDetail> loadAllTechAlterDetails() {
		return this.loadAll(TechAlterDetail.class);
	}

	public void storeTechAlterDetail(TechAlterDetail techAlterDetail) {
		this.store(techAlterDetail);
	}

	public void deleteTechAlterDetail(TechAlterDetail techAlterDetail) {
		this.delete(techAlterDetail);
	}

	public void deleteAllTechAlterDetail(Collection<TechAlterDetail> techAlterDetails) {
		this.deleteAll(techAlterDetails);
	}

}
