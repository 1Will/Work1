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
package com.yongjun.tdms.workflow.dao.docstate.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.workflow.dao.docstate.DocStateDao;
import com.yongjun.tdms.workflow.model.docstate.DocState;

/**
 * @author qs
 * @version $Id: HibernateDocState.java 7653 2007-09-29 07:20:00Z qsun $
 */
public class HibernateDocState extends BaseHibernateDao implements DocStateDao {

	public List<DocState> loadAllDocStates() {
		return loadAll(DocState.class);
	}

	
	public DocState loadDocStateByCode(final String stateCode) {
		return (DocState) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("LoadDocStateByCode")
								.setParameter("stateCode", stateCode)
								.uniqueResult();
					}
				});
	}
}
