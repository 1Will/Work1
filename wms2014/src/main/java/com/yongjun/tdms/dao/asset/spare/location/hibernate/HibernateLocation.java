package com.yongjun.tdms.dao.asset.spare.location.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.location.LocationDao;
import com.yongjun.tdms.model.asset.spare.Location;

/**
 * <p>
 * Title: HibernateLocation
 * <p>
 * Description: 库位数据库访问实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class HibernateLocation extends BaseHibernateDao implements LocationDao {

	public List<Location> loadAllLocations(Long[] locationIds) {
		return this.loadAll(Location.class, locationIds);
	}

	public Location loadLocation(Long locationId) {
		return this.load(Location.class, locationId);
	}

	public void storeLocation(Location location) {
		this.store(location);
	}

	public void deleteLocation(Location location) {
		this.delete(location);
	}

	public void deleteAllLocation(Collection<Location> locations) {
		this.deleteAll(locations);
	}

	public Location getLocationByCodeOnlyValid(final String code) {
		return (Location) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery(
								"GetLocationByCodeAndOnlyValid").setParameter(
								"code", code).uniqueResult();
					}
				});
	}

	@SuppressWarnings("unchecked")
	public List<Location> getLocationByCodeOnlyInvalid(final String code) {
		return (List<Location>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery(
								"GetLocationByCodeAndOnlyInvalid")
								.setParameter("code", code).list();
					}
				});
	}
	
	/**
	 * 根据库区查找库位
	 * @param regionalId
	 * @return
	 */
	public List<Location> findLocationByRegional(String regionalId)throws Exception{
		String hql = "SELECT l.id,l.code From Location l where l.regional.id= "+regionalId; 
		Session session = this.getSession();
		Query query = session.createQuery(hql);
//		List<Location> list = null;
//		if(null != regionalId && !"".equals(regionalId)){
//			 list = this.loadByKey(Location.class, "regional", regionalId);
//		}
//		System.out.println("---------");
//		if(null != list && list.size()>0){
//			for(Location l : list){
//				System.out.println("------code---"+l.getCode());
//			}
//		}
		return query.list();
		
	}
	
	/**
	 * 根据库位号获取有效的库位实体
	 * 
	 * @param  code表示库位号
	 * @param warehoustId 仓库ID
	 * @return 有效的库位实体
	 */
	public Location getLocationByCodeOnlyValid(final String code,final Long warehouseId){
		return (Location) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery(
								"GetLocationByCodeAndOnlyValidAndWarehouse").setParameter(
								"code", code).setParameter("warehouseId", warehouseId)
								.uniqueResult();
					}
				});
	}
	
	public Location findLocationByWarehouse(String locationName, long warehouseId) {
		String hql = "SELECT From Location l where l.code='"+locationName+"' and l.warehouse.id="+warehouseId;
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		return (Location)query.list().get(0);
	}
}
