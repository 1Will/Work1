/*
 * Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All Rights Reserved.
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
package com.yongjun.tdms.dao.asset.spare.warehouseInfo.warehouse.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Warehouse;
import com.yongjun.tdms.dao.asset.spare.warehouseInfo.warehouse.WarehouseDao;

/**
 * <p>Title: HibernateWarehouse</p>
 * <p>Description: 仓库信息数据访问层接口实现类</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author wliu@yj-technology.com</p>
 * <p>@version $Id: HibernateWarehouse.java 2010-03-10 wliu $</p>
 */
@Deprecated
public class HibernateWarehouse 
extends BaseHibernateDao
implements WarehouseDao{

	/**
	 * 保存当前仓库信息
	 * @param wh 仓库信息
	 */
	public void storeWarehouse(Warehouse wh){
		
		super.store(wh);
	}
	
	/**
	 * 删除当前选定的仓库信息
	 * @param wh 仓库信息
	 */
	public void deleteWarehouse(Warehouse wh){
		
		super.delete(wh);
	}

	/**
	 * 删除多个选定的仓库信息
	 * @param whs 仓库信息集合
	 */
	public void deleteAllWarehouse(Collection<Warehouse> whs){
		
		super.deleteAll(whs);
	}

	/**
	 * 根据多个标识查询多个仓库信息
	 * @param whIds 仓库信息集合
	 * @return 仓库信息集合
	 */
	public List<Warehouse> loadAllWarehouse(Long[] whIds){
		
		return super.loadAll(Warehouse.class, whIds);
	}

	/**
	 * 根据标识查询指定的仓库信息
	 * @param whId 仓库信息标识
	 * @return 仓库信息
	 */
	public Warehouse loadWarehouse(Long whId){
		
		return super.load(Warehouse.class, whId);
	}

	/**
	 * 查询所有仓库信息
	 * @return 仓库信息集合
	 */
	public List<Warehouse> loadAllWarehouse(){
		
		return super.loadAll(Warehouse.class);
	}

	/**
	 * 根据非主键查询实体类
	 * @param keyName 非主键标识
	 * @param keyValue 非主键值
	 * @return 实体类集合
	 */
	public List<Warehouse> loadByKey(String keyName, Object keyValue) throws DaoException{
		
		return super.loadByKey(Warehouse.class, keyName, keyValue);
	}
	
	/**
	 * 根据非主键集合查询实体类
	 * @param keyNamess 非主键标识集合
	 * @param keyValues 非主键值集合
	 * @return 实体类集合
	 */
	public List<Warehouse> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException{
		
		return super.loadByKeyArray(Warehouse.class, keyNames, keyValues);
	}

	public List loadWareHouseByStorageGradeId(String id) {
		Long Id = Long.valueOf(id);
		String hql="";
		if(Id==208){
			 hql = "select new com.yongjun.pluto.model.security.Warehouse(w.id,w.name) from Warehouse as w where w.disabled=0 and w.storageGrade.id=" + Id;
		}else{
		 hql = "select new com.yongjun.pluto.model.security.Warehouse(w.id,w.name) from Warehouse as w where w.disabled=0";
		}
		List list = this.getSession().createQuery(hql).list();		
		return list;
		 
	}

	public Warehouse loadJJWareHouse(String code) {
		// TODO Auto-generated method stub
		Session session = getSession();
		String hql = "from Warehouse s where s.code = '"+code+"'";
		Query query = session.createQuery(hql);
		List<Warehouse> wlist = query.list();
		if(wlist!=null&&wlist.size()>0){
			return wlist.get(0);
		}
		return null;
	}

	public List<Integer> loadWareHouseByUser(long userid) {
		final String sql = "SELECT WAREHOUSE_ID as wid  FROM t_user_warehouse WHERE  USER_ID = "+userid;
		return this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addScalar("wid",Hibernate.INTEGER);
						return query.list();
					}
				});
	}
	
	public List<Integer> loadWareHouseByUserAndNew(long userid) {
		final String sql = "SELECT WAREHOUSE_ID as wid  FROM t_user_warehouse twu, t_warehouse tw WHERE twu.WAREHOUSE_ID=tw.id and tw.old_warehouse ='N' and twu.USER_ID = "+userid;
		return this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql).addScalar("wid",Hibernate.INTEGER);
						return query.list();
					}
				});
	}

	public List<Warehouse> loadByAber(String wids) {
		Session session = getSession();
		String hql = "from Warehouse s where 1=1 ";
		if(wids!=null&&!wids.equals("")){
			hql +=" and s.id  not in ("+wids+")";
		}
		Query query = session.createQuery(hql);
		List<Warehouse> wlist = query.list();
		return wlist;
	}
	
}
