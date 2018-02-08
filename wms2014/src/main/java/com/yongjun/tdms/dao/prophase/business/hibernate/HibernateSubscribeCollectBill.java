/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
import com.yongjun.tdms.dao.prophase.business.SubscribeCollectBillDao;
import com.yongjun.tdms.model.prophase.business.SubscribeCollectBill;

/**
 * <p>Title: HibernateSubscribeCollectBill
 * <p>Description: 申购汇总单数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id$
 * @see com.yongjun.tdms.dao.prophase.business.SubscribeCollectBillDao
 */
public class HibernateSubscribeCollectBill extends BaseHibernateDao implements
		SubscribeCollectBillDao {

	public void deleteAllSubscribeCollectBill(Collection<SubscribeCollectBill> SubscribeCollectBillIds) {
		this.deleteAll(SubscribeCollectBillIds);
	}

	public void deleteSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.delete(subscribeCollectBill);
	}

	public SubscribeCollectBill loadSubscribeCollectBill(Long id) {
		return this.load(SubscribeCollectBill.class, id);
	}

	public List<SubscribeCollectBill> loadAllSubscribeCollectBills(Long[] SubscribeCollectBillIds) {
		return this.loadAll(SubscribeCollectBill.class, SubscribeCollectBillIds);
	}

	public void storeSubscribeCollectBill(SubscribeCollectBill subscribeCollectBill) {
		this.store(subscribeCollectBill);
	}

}
