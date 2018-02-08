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
package com.yongjun.tdms.workflow.dao.approver.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.workflow.dao.approver.DocApproverDao;
import com.yongjun.tdms.workflow.model.approver.DocApprover;
import com.yongjun.tdms.workflow.model.doctype.DocType;

/**
 * @author qs
 * @version $Id: HibernateDocApprover.java 7962 2007-10-23 07:03:35Z qsun $
 */
public class HibernateDocApprover extends BaseHibernateDao implements
		DocApproverDao {

	public DocApprover loadDocApprover(Long id) {
		 return this.load(DocApprover.class, id);
	}
	
	public void storeApprover(DocApprover docApprover) {
		 this.store(docApprover);
	}
	@SuppressWarnings("unchecked")
	public List<User> loadAllApprovers(final DocType docType, final boolean onlyFinalApprover) {
		return (List<User>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadAllApprovers")
								.setParameter("docTypeId", docType.getId())
								.setParameter("onlyFinalApprover", onlyFinalApprover)
								.list();
					}
				});
	}
}
