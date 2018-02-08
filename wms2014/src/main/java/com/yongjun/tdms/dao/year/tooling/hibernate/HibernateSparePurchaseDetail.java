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
import com.yongjun.tdms.dao.year.tooling.SparePurchaseDetailDao;
import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;

/**
 * 
 * <p>Title: HibernateSparePurchaseDetail
 * <p>Description: 备件采购明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.year.tooling.SparePurchaseDetailDao
 * @version $Id:$
 */
public class HibernateSparePurchaseDetail extends BaseHibernateDao implements
		SparePurchaseDetailDao {

	public SparePurchaseDetail loadSparePurchaseDetail(Long sparePurchaseDetailId) {
		return this.load(SparePurchaseDetail.class, sparePurchaseDetailId);
	}

	public List<SparePurchaseDetail> loadAllSparePurchaseDetails(Long[] sparePurchaseDetailIds) {
		return this.loadAll(SparePurchaseDetail.class, sparePurchaseDetailIds);
	}

	public List<SparePurchaseDetail> loadAllSparePurchaseDetails() {
		return this.loadAll(SparePurchaseDetail.class);
	}

	public void storeSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail) {
		this.store(sparePurchaseDetail);
	}

	public void deleteSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail) {
		this.delete(sparePurchaseDetail);
	}

	public void deleteAllSparePurchaseDetail(Collection<SparePurchaseDetail> sparePurchaseDetails) {
		this.deleteAll(sparePurchaseDetails);
	}

}
