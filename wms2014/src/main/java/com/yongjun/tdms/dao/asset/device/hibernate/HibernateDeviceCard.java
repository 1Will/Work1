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
package com.yongjun.tdms.dao.asset.device.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.util.DateUtil;
import com.yongjun.tdms.dao.asset.device.DeviceCardDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;

/**
 * <p>Title: HibernateDeviceCard
 * <p>Description: 资产数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: HibernateDeviceCard.java 11056 2008-02-22 01:39:11Z zbzhang $
 */
public class HibernateDeviceCard extends BaseHibernateDao implements
		DeviceCardDao {

	public void storeDevice(DeviceCard device) {
		this.store(device);
	}

	public DeviceCard loadDevice(Long deviceId) {
		return this.load(DeviceCard.class, deviceId);
	}

	public List<DeviceCard> loadAllDevices() {
		return this.loadAll(DeviceCard.class);
	}

	public DeviceCard getDeviceByNo(final String deviceNo) {
		return (DeviceCard) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetDeviceByNo")
								.setParameter("deviceNo", deviceNo)
								.uniqueResult();
					}
				});
	}

//	@SuppressWarnings("unchecked")
//	public List<DeviceCard> loadAllUnrelatedDevices(final Long id) {
//		return (List<DeviceCard>) this.getHibernateTemplate().execute(
//				new HibernateCallback() {
//					public Object doInHibernate(Session session)
//							throws HibernateException, SQLException {
//						return session.getNamedQuery("LoadAllUnrelatedDevices")
//								.setParameter("id", id).list();
//					}
//				});
//	}

	public List<DeviceCard> loadAllDevices(Long[] deviceCardIds) {
		return this.loadAll(DeviceCard.class, deviceCardIds);
	}

	@SuppressWarnings("unchecked")
	public String getMaxDeviceNoByAssetCode(final String code) {
		return (String) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetMaxDeviceNoByAssetCode")
								.setParameter("assetCode", code)
								.uniqueResult();
					}
				});
	}
	
	@SuppressWarnings("unchecked")
	public List<DeviceCard> loadAllMatchOptionDevices(Map searchOption) {
		List<DeviceCard> list = null;
		StringBuffer hql = new StringBuffer("from DeviceCard device where (1=1) and device.toolingDevFlag='DEVICE' ");
		if (searchOption.containsKey("deviceNo")) {
			hql.append("and device.deviceNo like :deviceNo ");
		}
		if (searchOption.containsKey("name")) {
			hql.append("and device.name like :name ");
		}
		if (searchOption.containsKey("categoryId")) {
			hql.append("and device.deviceType.id = :categoryId ");
		}
		if (searchOption.containsKey("departmentId")) {
			hql.append("and device.department.id = :departmentId ");
		}
		if (searchOption.containsKey("installPlace")) {
			hql.append("and device.installPlace like :installPlace ");
		}
		if (searchOption.containsKey("cardCreatedTime_start")) {
			hql.append("and device.cardCreatedTime >= :cardCreatedTime_start ");
		}
		if (searchOption.containsKey("cardCreatedTime_end")) {
			hql.append("and device.cardCreatedTime <= :cardCreatedTime_end ");
		}
		if (searchOption.containsKey("orgId")) {
			hql.append("and device.organization.id = :orgId");
		}
		try {
			Query queryDevice = this.getSession().createQuery(hql.toString());
			if (searchOption.containsKey("deviceNo")) {
				queryDevice.setParameter("deviceNo",'%' + searchOption.get("deviceNo").toString()+'%');
			}
			if (searchOption.containsKey("name")) {
				queryDevice.setParameter("name",'%' + searchOption.get("name").toString() + '%');
			}
			if (searchOption.containsKey("categoryId")) {
				queryDevice.setParameter("categoryId",Long.valueOf(searchOption.get("categoryId").toString()));
			}
			if (searchOption.containsKey("departmentId")) {
				queryDevice.setParameter("departmentId",searchOption.get("departmentId"));
			}
			if (searchOption.containsKey("installPlace")) {
				queryDevice.setParameter("installPlace",'%' + searchOption.get("installPlace").toString() + '%');
			}
			if (searchOption.containsKey("cardCreatedTime_start")) {
				queryDevice.setParameter("cardCreatedTime_start",DateUtil.toDate(searchOption.get("cardCreatedTime_start").toString(),"yyyy-MM-dd"),Hibernate.DATE);
			}
			if (searchOption.containsKey("cardCreatedTime_end")) {
				queryDevice.setParameter("cardCreatedTime_end",DateUtil.toDate(searchOption.get("cardCreatedTime_end").toString(),"yyyy-MM-dd"),Hibernate.DATE);
			}
			if (searchOption.containsKey("orgId")) {
				queryDevice.setParameter("orgId",Long.valueOf(searchOption.get("orgId").toString()),Hibernate.LONG);
			}
			list = (List<DeviceCard>)queryDevice.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
		
	}
	
	public List Query(String[] queryInfo){
		List result=null;
		try {
			//System.out.println("queryInfo[6]=="+queryInfo[0]);
			String hql = " from DeviceCard as spare where 1=1";
			
			if (queryInfo[0] != "") {
				hql += " and spare.deviceNo=:deviceNo ";
			}
		
			Query query = getSession().createQuery(hql);
			//System.out.println("hql=="+hql);
			
			if (queryInfo[0] != "") {
				query.setParameter("deviceNo", queryInfo[0]);
			}
			result = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@SuppressWarnings("unchecked")
	public List<DeviceCard> loadAllMatchOptionToolings(Map searchOption) {
		List<DeviceCard> list = null;
		StringBuffer hql = new StringBuffer("from DeviceCard tooling where (1=1) and tooling.toolingDevFlag='TOOLING' ");
		if (searchOption.containsKey("deviceNo")) {
			hql.append("and tooling.deviceNo like :deviceNo ");
		}
		if (searchOption.containsKey("graphNo")) {
			hql.append("and tooling.graphNo like :graphNo ");
		}
		if (searchOption.containsKey("name")) {
			hql.append("and tooling.name like :name ");
		}
		if (searchOption.containsKey("productId")) {
			hql.append("and tooling.product.id = :productId ");
		}
		if (searchOption.containsKey("toolingTypeId")) {
			hql.append("and tooling.toolingType.id = :toolingTypeId ");
		}
		if (searchOption.containsKey("toolingTypeDetailId")) {
			hql.append("and tooling.toolingTypeDetail.id = :toolingTypeDetailId ");
		}
		if (searchOption.containsKey("departmentId")) {
			hql.append("and tooling.department.id = :departmentId ");
		}
		if (searchOption.containsKey("toolingStatusCode")) {
			hql.append("and tooling.toolingStatus.code = :toolingStatusCode ");
		}
		if (!searchOption.containsKey("includeDisabled")) {
			hql.append("and tooling.enabled = true ");
		}
		if (searchOption.containsKey("orgId")) {
			hql.append("and tooling.organization.id = :orgId");
		}
		try {
			Query queryDevice = this.getSession().createQuery(hql.toString());
			if (searchOption.containsKey("deviceNo")) {
				queryDevice.setParameter("deviceNo",'%' + searchOption.get("deviceNo").toString()+'%');
			}
			if (searchOption.containsKey("name")) {
				queryDevice.setParameter("name",'%' + searchOption.get("name").toString() + '%');
			}
			if (searchOption.containsKey("graphNo")) {
				queryDevice.setParameter("graphNo",'%' + searchOption.get("graphNo").toString() + '%');
			}
			if (searchOption.containsKey("productId")) {
				queryDevice.setParameter("productId",Long.valueOf(searchOption.get("productId").toString()));
			}
			if (searchOption.containsKey("toolingTypeId")) {
				queryDevice.setParameter("toolingTypeId",Long.valueOf(searchOption.get("toolingTypeId").toString()));
			}
			if (searchOption.containsKey("toolingTypeDetailId")) {
				queryDevice.setParameter("toolingTypeDetailId",searchOption.get("toolingTypeDetailId"));
			}
			if (searchOption.containsKey("departmentId")) {
				queryDevice.setParameter("departmentId",searchOption.get("departmentId"));
			}
			if (searchOption.containsKey("toolingStatusCode")) {
				queryDevice.setParameter("toolingStatusCode",searchOption.get("toolingStatusCode").toString());
			}
			if (searchOption.containsKey("orgId")) {
				queryDevice.setParameter("orgId",Long.valueOf(searchOption.get("orgId").toString()),Hibernate.LONG);
			}
			list = (List<DeviceCard>)queryDevice.list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	public List<DeviceCard> loadAllDeviceByStatusAndAssetType() {
		return (List<DeviceCard>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetDeviceByStatusAndAssetType").list();
					}
				});
	}

	public DeviceCard loadDeviceByAcceptBill(final Long acceptBillId) {
		return (DeviceCard) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetDeviceByAcceptId")
								.setParameter("acceptBillId", acceptBillId)
								.uniqueResult();
					}
				});
	}
	@SuppressWarnings("unchecked")
	public List getToolingGroupNoByGroupNo(final String groupNo) {
		 return (List) this.getHibernateTemplate().execute(new HibernateCallback() {
	            public Object doInHibernate(Session session)
	                    throws HibernateException {
	                return session.getNamedQuery("GetToolingGroupNoByGroupNo")
	                        .setParameter("groupNo", groupNo)
	                        .list();
	            }
	        });
	}

	public int getEstalishAfterGroupNumber(final String groupNo) {
		return (Integer) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAcceptGroupNoByGroupNo")
								.setParameter("groupNo", groupNo)
								.uniqueResult();
					}
				});
		
	}
}
