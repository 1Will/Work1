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
package com.yongjun.tdms.dao.runmaintenance.intactness.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.intactness.IntactnessDetailDao;
import com.yongjun.tdms.model.runmaintenance.intactness.IntactnessDetail;
/**
 * <p>Title: HibernateIntactnessDetail
 * <p>Description: 设备鉴定明细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.runmaintenance.intactness.IntactnessDetailDao
 * @version $Id:  $
 */
public class HibernateIntactnessDetail extends BaseHibernateDao implements IntactnessDetailDao {

	public IntactnessDetail loadIntactnessDetail(Long intactnessDetailId) {
		return this.load(IntactnessDetail.class, intactnessDetailId);
	}

	public List<IntactnessDetail> loadAllIntactnessDetails(
			Long[] intactnessDetailIds) {
		return this.loadAll(IntactnessDetail.class, intactnessDetailIds);
	}

	public List<IntactnessDetail> loadAllIntactnessDetail() {
		return this.loadAll(IntactnessDetail.class);
	}

	public void storeIntactnessDetail(IntactnessDetail intactnessDetail) {
		this.store(intactnessDetail);
	}

	public void deleteIntactnessDetail(IntactnessDetail intactnessDetail) {
		this.delete(intactnessDetail);
	}

	public void deleteAllIntactnessDetail(
			Collection<IntactnessDetail> intactnessDetail) {
		this.deleteAll(intactnessDetail);
	}

}
