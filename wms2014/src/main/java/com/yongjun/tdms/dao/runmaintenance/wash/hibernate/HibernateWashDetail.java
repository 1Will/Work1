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
package com.yongjun.tdms.dao.runmaintenance.wash.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.wash.WashDetailDao;
import com.yongjun.tdms.model.runmaintenance.wash.WashDetail;
/**
 * <p>Title: HibernateWashDetail
 * <p>Description: 清洗明细数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.dao.runmaintenance.wash.WashDetailDao
 */
public class HibernateWashDetail extends BaseHibernateDao implements
		WashDetailDao {

	public WashDetail loadWashDetail(Long detailId) {
		return this.load(WashDetail.class, detailId);
	}

	public List<WashDetail> loadAllWashDetails(Long[] detailIds) {
		return this.loadAll(WashDetail.class, detailIds);
	}

	public List<WashDetail> loadAllWashDetails() {
		return this.loadAll(WashDetail.class);
	}

	public void storeWashDetail(WashDetail detail) {
		this.store(detail);
	}

	public void deleteWashDetail(WashDetail detail) {
		this.delete(detail);
	}

	public void delteAllWashDetail(Collection<WashDetail> details) {
		this.deleteAll(details);
	}

}
