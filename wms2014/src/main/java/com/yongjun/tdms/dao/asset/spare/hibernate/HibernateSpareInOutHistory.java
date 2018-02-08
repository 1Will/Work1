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

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.SpareInOutHistoryDao;
import com.yongjun.tdms.model.asset.spare.SpareInOutHistory;

/**
 * @author qs
 * @version $Id: HibernateSpareInOutHistory.java 9315 2007-12-13 11:49:50Z mwei $
 */
public class HibernateSpareInOutHistory extends BaseHibernateDao implements
		SpareInOutHistoryDao {
	public List<SpareInOutHistory> loadAllSpareInOutHistory(
			Long[] spareInOutHistoryIds) {
		return this.loadAll(SpareInOutHistory.class, spareInOutHistoryIds);
	}

	public SpareInOutHistory loadSpareInOutHistory(Long spareInOutHistoryId) {
		return this.load(SpareInOutHistory.class, spareInOutHistoryId);
	}

	public void storeSpareInOutHistory(SpareInOutHistory spareInOutHistory) {
		this.store(spareInOutHistory);
	}

	public void deleteSpareInOutHistory(SpareInOutHistory spareInOutHistory) {
		this.delete(spareInOutHistory);
	}

	public void deleteAllSpareInOutHistory(
			Collection<SpareInOutHistory> spareInOutHistory) {
		this.deleteAll(spareInOutHistory);
	}

	public String getTotalSpareNumberBySpare(Long sapreId, boolean flag) {
		String result=null;
		try {
			Object object = getSession()
					.createQuery(
							"select sum(spareInOutHistory.number) from SpareInOutHistory as spareInOutHistory where spareInOutHistory.spare.id=:id and spareInOutHistory.inFlag=:inOutFlag group by spareInOutHistory.spare.id")
					.setParameter("id", sapreId)
					.setParameter("inOutFlag", flag).uniqueResult();
			if (object != null) {
				result=object.toString();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getMaxSpareHistoryById(final Long spareId, final boolean Flag) {
		return (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Object object = session.getNamedQuery(
								"GetMaxSpareHistoryById").setParameter("id",
								spareId).setParameter("inOutFlag", Flag)
								.uniqueResult();
						if (object == null) {
							return null;
						} else {
							return object.toString();
						}
					}
				});
	}

}
