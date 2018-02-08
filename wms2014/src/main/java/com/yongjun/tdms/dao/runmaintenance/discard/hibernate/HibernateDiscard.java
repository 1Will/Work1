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

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.discard.DiscardDao;
import com.yongjun.tdms.model.runmaintenance.discard.Discard;
import com.yongjun.tdms.workflow.service.job.WFJobInProcException;

/**
 * <p>Title: HibernateDiscard
 * <p>Description: 报废单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernateDiscard.java 8911 2007-12-02 09:30:05Z wzou $
 * @see com.yongjun.tdms.dao.runmaintenance.tooling.discard.DiscardDao
 */
public class HibernateDiscard extends BaseHibernateDao implements DiscardDao {

	public List<Discard> loadAllDiscards(Long[] discardIds) {
		return this.loadAll(Discard.class,discardIds);
	}

	public void deleteAllDiscard(Collection<Discard> discards) throws WFJobInProcException {
		this.deleteAll(discards);
	}

	public Discard loadDiscard(Long discardId) {
		return this.load(Discard.class,discardId);
	}

	public void storeDiscard(Discard discard) {
		this.store(discard);
	}

	@SuppressWarnings("unchecked")
	public Discard getToolingDiscardByToolingId(final Long toolingId) {
		return (Discard) this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
					  throws HibernateException, SQLException {
						return session.getNamedQuery("GetToolingDiscardByToolingId")
						.setParameter("toolingId", toolingId)
						.uniqueResult();
					}
				});
	}

	public Discard getDeviceDiscardByToolingId(final Long deviceId) {
		return (Discard) this.getHibernateTemplate().execute(
				new HibernateCallback(){
					public Object doInHibernate(Session session)
					  throws HibernateException, SQLException {
						return session.getNamedQuery("GetDeviceDiscardByToolingId")
						.setParameter("deviceId", deviceId)
						.uniqueResult();
					}
				});
	}


}
