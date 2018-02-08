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
package com.yongjun.tdms.dao.workspace.warnning.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.User;
import com.yongjun.tdms.dao.workspace.warnning.WorkWarnningDao;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;

/**
 * <p>Title: HibernateWorkWarnning
 * <p>Description: 我的提醒数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.workspace.WorkWarnningDao
 * @version $Id:$
 */
public class HibernateWorkWarnning extends BaseHibernateDao implements
		WorkWarnningDao {

	public WorkWarnning loadWorkWarnning(Long workWarnningId) {
		return this.load(WorkWarnning.class, workWarnningId);
	}

	public List<WorkWarnning> loadAllWorkWarnnings(Long[] workWarnningIds) {
		return this.loadAll(WorkWarnning.class, workWarnningIds);
	}

	public List<WorkWarnning> loadAllWorkWarnnings() {
		return this.loadAll(WorkWarnning.class);
	}

	public void storeWorkWarnning(WorkWarnning workWarnning) {
		this.store(workWarnning);
	}

	public void deleteWorkWarnning(WorkWarnning workWarnning) {
		this.delete(workWarnning);
	}

	public void deleteAllWorkWarnnings(Collection<WorkWarnning> workWarnnings) {
		this.deleteAll(workWarnnings);
	}

	@SuppressWarnings("unchecked")
	public Integer GetNumberOfUnReadWarnningByUserID(final Long userId) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetNumberOfUnReadWarnningByUserID")
								.setParameter("userId", userId).uniqueResult();
					}
				});
	}
	public List<Long> loadUsersByGroup(final long groupid){
		
		return this.getHibernateTemplate().executeFind( new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql = "SELECT USER_ID userid FROM t_group_user  WHERE GROUP_ID =  "+groupid;
				return session.createSQLQuery(sql).addScalar("userid", Hibernate.LONG).list();
			}
		});
		
	}

}
