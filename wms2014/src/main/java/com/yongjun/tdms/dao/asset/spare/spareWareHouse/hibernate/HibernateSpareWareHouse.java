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
package com.yongjun.tdms.dao.asset.spare.spareWareHouse.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.asset.spare.spareWareHouse.SpareWareHouseDao;
import com.yongjun.tdms.model.asset.spare.spareWareHouse.SpareWareHouse;

/**
 * <p>Title: HibernateSpareWareHouse
 * <p>Description: 备件库总台帐数据库访问实体类</p>
 * <p>Copyright: Copyright (c) 2011 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $
 */
public class HibernateSpareWareHouse extends BaseHibernateDao implements
		SpareWareHouseDao {

	public SpareWareHouse loadSpareWareHouse(Long spareWareHouseId) {
		return this.load(SpareWareHouse.class, spareWareHouseId);
	}

	public SpareWareHouse loadSpareWareHouse(final Long spareId, final Long wareHouseId) {
		return (SpareWareHouse) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetSpareWareHouseBySpareIdAndWareHouseId")
								.setParameter("wareHouseId", wareHouseId)
								.setParameter("spareId",spareId)
								.uniqueResult();
					}
				});
	}

	public void storeSpareWareHouse(SpareWareHouse spareWareHouse) {
		this.store(spareWareHouse);
	}

}
