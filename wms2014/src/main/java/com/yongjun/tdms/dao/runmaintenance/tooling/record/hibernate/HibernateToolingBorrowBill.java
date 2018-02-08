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
package com.yongjun.tdms.dao.runmaintenance.tooling.record.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.tooling.record.ToolingBorrowBillDao;
import com.yongjun.tdms.model.runmaintenance.tooling.record.ToolingBorrowBill;

/**
 * @author qs
 * @version $Id: HibernateToolingBorrowBill.java 8500 2007-11-21 06:28:06Z zbzhang $
 */
public class HibernateToolingBorrowBill extends BaseHibernateDao implements
		ToolingBorrowBillDao {

	public ToolingBorrowBill loadToolingBorrowBill(Long toolingBorrowBillId) {
		return this.load(ToolingBorrowBill.class, toolingBorrowBillId);
	}

	public List<ToolingBorrowBill> loadAllToolingBorrowBills(Long[] toolingBorrowBillIds) {
		return this.loadAll(ToolingBorrowBill.class, toolingBorrowBillIds);
	}

	public List<ToolingBorrowBill> loadAllToolingBorrowBills() {
		return this.loadAll(ToolingBorrowBill.class);
	}

	public void storeToolingBorrowBill(ToolingBorrowBill toolingBorrowBill) {
		this.store(toolingBorrowBill);
	}

	public void deleteToolingBorrowBill(ToolingBorrowBill toolingBorrowBill) {
		this.delete(toolingBorrowBill);
	}

	public void deleteAllToolingBorrowBills(Collection<ToolingBorrowBill> toolingBorrowBills) {
		this.deleteAll(toolingBorrowBills);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getOldDeviceIds(final String toolingDev_Flag,final Boolean returnFlag) {
		return (List)this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session) 
						throws HibernateException, SQLException {
						return session.getNamedQuery("GetOldDeviceIds")
							.setString("toolingDev_Flag", toolingDev_Flag)
							.setBoolean("returnFlag", returnFlag).list();
					}
				}
		);
	}

}
