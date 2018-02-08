/**
 * 
 */
package com.yongjun.tdms.dao.base.codevalue.hibernate;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.codevalue.SpareTypeDao;
import com.yongjun.tdms.model.base.codevalue.SpareType;

/**
 *<p>Title:HibernateSpareType.java
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author yangli@yj-technology.com
 * @version 
 */
public class HibernateSpareType extends BaseHibernateDao implements SpareTypeDao {

	public void deleteAllSpareType(Collection<SpareType> spareTypes) {
		this.deleteAll(spareTypes);
	}

	public void deleteSpareType(SpareType spareType) {
		this.delete(spareType);
	}

	public List<SpareType> loadAllSpareType(Long[] spareTypeIds) {
		return this.loadAll(SpareType.class, spareTypeIds);
	}

	public List<SpareType> loadAllSpareType() {
		return this.loadAll(SpareType.class);
	}

	public SpareType loadSpareType(Long spareTypeId) {
		return this.load(SpareType.class, spareTypeId);
	}

	public void storeSpareType(SpareType spareType) {
		this.store(spareType);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpareType> loadAllSpareTypeByToolingDevFlag(final String toolingDevFlag) {
		return (List<SpareType>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						return session.getNamedQuery("GetAllSpareTypeByToolingDevFlag")
								.setParameter("toolingDevFlag", toolingDevFlag).list();
					}
				});
	}

}
