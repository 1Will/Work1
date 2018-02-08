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
package com.yongjun.tdms.dao.asset.spare.sparelocation.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.tdms.dao.asset.spare.sparelocation.SpareLocationDao;
import com.yongjun.tdms.model.asset.spare.Spare;
import com.yongjun.tdms.model.asset.spare.SpareLocation;

/**
 * <p>Title: HibernateSpareLocation
 * <p>Description: 备件明细数据库访问控制实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class HibernateSpareLocation extends BaseHibernateDao implements
		SpareLocationDao {

	public List<SpareLocation> loadAllSpareLocations(Long[] spareLocationIds) {
		return this.loadAll(SpareLocation.class, spareLocationIds);
	}


	public SpareLocation loadSpareLocation(Long spareLocationId) {
		return this.load(SpareLocation.class,spareLocationId);
	}

	public void storeSpareLocation(SpareLocation spareLocation) {
		this.store(spareLocation);
	}

	public void deleteSpareLocation(SpareLocation spareLocation) {
		this.delete(spareLocation);
	}

	public void deleteAllSpareLocation(Collection<SpareLocation> spareLocations) {
		this.deleteAll(spareLocations);
	}

    /**
     * 从备件t_spare_Location表中通过部门的id,库位的id,备件的id,获取此备件的记录数
     */
	public Integer getNumByDeptAndLocationAndSpare(final Long deptId,
			final Long locationId, final Long spareId) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetNumByDeptAndLocationAndSpare")
								.setParameter("deptId", deptId)
								.setParameter("locationId",locationId)
								.setParameter("spareId",spareId)
								.uniqueResult();
					}
				});
	}

   /**
    * 根据部门id,库位id,备件id从t_spare_location表中获得符合条件的最顶端的spareLocation记录
    */
	public SpareLocation getTop1SpareLocationByDeptAndLocationAndSpare(
			Long deptId, Long locationId, Long spareId, boolean isIncludeStocksZeroStatus) {
		SpareLocation spareLocation = null;
		try {
			String hql = "FROM 	SpareLocation spareLocation WHERE 1=1";
			
			if (null != deptId) {
				hql += " AND spareLocation.department.id = :deptId";
			}
			if (null != locationId) {
				hql += " AND spareLocation.location.id = :locationId";
			}
			if (null != spareId) {
				hql += " AND spareLocation.spare.id = :spareId";
			}
			if (isIncludeStocksZeroStatus) {
				hql += " AND spareLocation.stocks >= 0";
			} 
//鲍杨涛于2017年6月26号去掉下面的条件 
//			else {
//				hql += " AND spareLocation.stocks > 0";
//			}   
			Query query = getSession().createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			if (null != deptId) {
				query.setParameter("deptId",deptId);
			}
			if (null != locationId) {
				query.setParameter("locationId",locationId);
			}
			if (null != spareId) {
				query.setParameter("spareId",spareId);
			}
			spareLocation = (SpareLocation)query.uniqueResult();
			
		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return spareLocation;
	}
	public SpareLocation getTop1SpareLocationByLocationAndSpare(
			Long locationId, Long spareId) {
		SpareLocation spareLocation = null;
		try {
			String hql = "FROM 	SpareLocation spareLocation WHERE " +
            " spareLocation.location.id = :locationId AND spareLocation.spare.id = :spareId " +
            " AND spareLocation.department is null"; // 鲍杨涛于2017年6月26号去掉 一个条件 AND spareLocation.stocks >= 0
			
			Query query = getSession().createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			query.setParameter("locationId",locationId)
				.setParameter("spareId",spareId);
			spareLocation = (SpareLocation)query.uniqueResult();

		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return spareLocation;
	}
	
	public SpareLocation getTop1SpareLocationByLocationIdAndSpareId(
			Long locationId, Long spareId) {
		SpareLocation spareLocation = null;
		try {
			String hql = "FROM 	SpareLocation spareLocation WHERE " +
            " spareLocation.location.id = :locationId AND spareLocation.spare.id = :spareId ";
			
			Query query = getSession().createQuery(hql);
			query.setFirstResult(0);
			query.setMaxResults(1);
			query.setParameter("locationId",locationId)
				.setParameter("spareId",spareId);
			spareLocation = (SpareLocation)query.uniqueResult();

		}catch (HibernateException e) {
			e.printStackTrace();
		}
		return spareLocation;
	}
	
	public SpareLocation getTop1SpareLocationByDeptAndSpare(final Long deptId, final String spareNo) {
			Integer count = (Integer) this.getHibernateTemplate().execute(
							new HibernateCallback() {
							public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetNumByDeptAndSpare")
								.setParameter("deptId", deptId)
								.setParameter("spareNo",spareNo)
								.uniqueResult();
					}
				});
			if(count==0){
				return null;
			}else{
				SpareLocation spareLocation =null;
				try {
					Transaction tx = null; 
					Session session = getSession();
					String hql = "From SpareLocation as spareLocation where spareLocation.department.id =:deptId AND spareLocation.spare.spareNo =:spareNo";
					tx = session.beginTransaction();
					Query query = session.createQuery(hql).setFirstResult(0).setMaxResults(1);
					query.setParameter("deptId", deptId);
					query.setParameter("spareNo", spareNo);
					spareLocation = (SpareLocation)query.uniqueResult();
				} catch (DataAccessResourceFailureException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (HibernateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return spareLocation;
			}
	}

	public Long getSumBySpare(final Long spareId) {
		return (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSumBySpare")
								.setParameter("spareId",spareId)
								.uniqueResult();
					}
				});
	}
	
	public Long getSumByLocation(final Long locationId) {
		return (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSumByLocation")
								.setParameter("locationId",locationId)
								.uniqueResult();
					}
				});
	}
	
	
	

	/**
	 * 根据备件ID和库位ID判断在t_spare_location中是否存在记录
	 */
	public Long getStocksBySpareIdAndLocationId(final Long spareId, final Long locationId) {
		return (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetStocksBySpareIdANDLocationId")
								.setParameter("spareId",spareId)
								.setParameter("locationId", locationId)
								.uniqueResult();
					}
				});
	}
	
	public List<SpareLocation> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.loadByKey(SpareLocation.class, keyName, keyValue);
	}


	public Long getSumStocksBySpareAndWareHouse(final Long spareId, final Long wareHouseId) {
		return (Long) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSumBySpareAndWareHouse")
								.setParameter("spareId",spareId)
								.setParameter("wareHouseId", wareHouseId)
								.uniqueResult();
					}
				});
	}

}
