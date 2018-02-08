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
package com.yongjun.tdms.dao.runmaintenance.discard.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.discard.DiscardBillDao;
import com.yongjun.tdms.model.runmaintenance.discard.DiscardBill;
/**
 * <p>Title: HibernateDiscardBill
 * <p>Description: 报废单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateDiscardBill.java 8911 2007-12-02 09:30:05Z wzou $
 * @see com.yongjun.tdms.dao.runmaintenance.tooling.discard.DiscardBillDao
 */
public class HibernateDiscardBill extends BaseHibernateDao implements DiscardBillDao{

	public DiscardBill loadDiscardBill(Long discardBillId) {
		
		return this.load(DiscardBill.class,discardBillId);
	}

	public List<DiscardBill> loadAllDiscardBills(Long[] discardBillIds) {
		
		return this.loadAll(DiscardBill.class,discardBillIds);
	}

	public void storeDiscardBill(DiscardBill discardBill) {
		this.store(discardBill);
		
	}

	public void deleteDiscardBill(DiscardBill discardBill) {
		this.delete(discardBill);
	}

	public void deleteAllDiscardBill(Collection<DiscardBill> discardBills) {
		this.deleteAll(discardBills);
		
	}

}
