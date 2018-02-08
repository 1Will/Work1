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
package com.yongjun.tdms.dao.asset.spare.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.SpareInOutBillDao;
import com.yongjun.tdms.model.asset.spare.SpareInOutBill;

/**
 * @author qs
 * @version $Id: HibernateSpareInOutBill.java 8448 2007-11-20 06:22:00Z qsun $
 */
public class HibernateSpareInOutBill extends BaseHibernateDao implements
		SpareInOutBillDao {
	public List<SpareInOutBill> loadAllSpareInOutBill(Long[] spareInOutBillIds) {
		return this.loadAll(SpareInOutBill.class, spareInOutBillIds);
	}

	public SpareInOutBill loadSpareInOutBill(Long spareInOutBillIds) {
		return this.load(SpareInOutBill.class, spareInOutBillIds);
	}

	public void storeSpareInOutBill(SpareInOutBill spareInOutBill) {
		this.store(spareInOutBill);
	}

	public void deleteSpareInOutBill(SpareInOutBill spareInOutBill) {
		this.delete(spareInOutBill);
	}

	public void deleteAllSpareInOutBill(
			Collection<SpareInOutBill> spareInOutBill) {
		this.deleteAll(spareInOutBill);
	}
}
