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
package com.yongjun.tdms.dao.runmaintenance.repair.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.repair.PreRepairPlanDetailDao;
import com.yongjun.tdms.model.runmaintenance.repair.PreRepairPlanDetail;

/**
 * <p>Title: HibernatePreRepairPlanDetail
 * <p>Description: 预防性维修计划详细数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author wzou@yj-technology.com
 * @version $Id: HibernatePreRepairPlanDetail.java 10303 2008-01-10 06:37:07Z wzou $
 */
public class HibernatePreRepairPlanDetail extends BaseHibernateDao implements PreRepairPlanDetailDao{

	public PreRepairPlanDetail loadPreRepairPlanDetail(Long preRepairPlanDetailId) {
		return this.load(PreRepairPlanDetail.class, preRepairPlanDetailId);
	}

	public List<PreRepairPlanDetail> loadAllPreRepairPlanDetails(Long[] preRepairPlanDetailIds) {
		return this.loadAll(PreRepairPlanDetail.class, preRepairPlanDetailIds);
	}

	public List<PreRepairPlanDetail> loadAllPreRepairPlanDetails() {
		return this.loadAll(PreRepairPlanDetail.class);
	}

	public void storePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.store(preRepairPlanDetail);
	}

	public void deletePreRepairPlanDetail(PreRepairPlanDetail preRepairPlanDetail) {
		this.delete(preRepairPlanDetail);
	}

	public void deleteAllPreRepairPlamDetail(Collection<PreRepairPlanDetail> preRepairPlanDetails) {
		this.deleteAll(preRepairPlanDetails);
	}
	@SuppressWarnings("unchecked")
	public Date GetMinProcEstimateExecDateByPreRepairProcId(final Long procId) {
		return (Date) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetMinProcEstimateExecDateByPreRepairProcId")
								.setParameter("procId", procId)
								.uniqueResult();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public PreRepairPlanDetail getPreRepairPlanDetailByPlanIdAndDeviceId(final Long planId, final Long deviceId) {
		return (PreRepairPlanDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetPreRepairPlanDetailByPlanIdAndDeviceId")
								.setParameter("planId", planId)
								.setParameter("deviceId",deviceId)
								.uniqueResult();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public PreRepairPlanDetail getSparePreRepairPlanDetailByPlanIdAndDeviceId(final Long planId, final Long deviceId) {
		return (PreRepairPlanDetail) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSparePreRepairPlanDetailByPlanIdAndDeviceId")
								.setParameter("planId", planId)
								.setParameter("deviceId",deviceId)
								.uniqueResult();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List getPreRepairPlanDetailByAndDeviceNo(final String deviceNo) {
		return (List) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetPreRepairPlanDetailByDeviceNo")
								.setParameter("deviceNo", deviceNo).list();
								
								
					}
				});
	}
}
