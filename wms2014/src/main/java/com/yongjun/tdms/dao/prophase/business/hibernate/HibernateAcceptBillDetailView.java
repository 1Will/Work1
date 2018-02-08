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
package com.yongjun.tdms.dao.prophase.business.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.AcceptBillDetailViewDao;
import com.yongjun.tdms.model.prophase.business.AcceptBillDetailView;

/**
 * <p>Title:HibernateAcceptBillDetailView
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: HibernateAcceptBillDetailView.java 2008-12-15 20:53:02 yli$
 */
public class HibernateAcceptBillDetailView extends BaseHibernateDao implements
		AcceptBillDetailViewDao {
	/**
	 * 根据验收单ID在视图类中查询出属于该验收单的所有验收单明细记录，并打印报表
	 */
	@SuppressWarnings("unchecked")
	public List<AcceptBillDetailView> loadAcceptBillDetailView(final Long id) {
		return (List<AcceptBillDetailView>) this.getHibernateTemplate().execute(
			new HibernateCallback(){
				public Object doInHibernate(Session session)throws HibernateException, SQLException{
					return session.getNamedQuery("GetAcceptBillDetailByAcceptBillId")
					.setParameter("AcceptBillId", id).list();
				}
			});
	}
}
