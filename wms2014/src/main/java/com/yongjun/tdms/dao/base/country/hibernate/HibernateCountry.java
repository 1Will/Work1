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
package com.yongjun.tdms.dao.base.country.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.Organization;
import com.yongjun.tdms.dao.base.country.CountryDao;
import com.yongjun.tdms.model.base.country.Country;

/**
 * @author qs
 * @version $Id: HibernateCountry.java 8099 2007-10-29 04:08:46Z qsun $
 */
public class HibernateCountry extends BaseHibernateDao implements CountryDao {
	@SuppressWarnings("unchecked")
	public List<Country> loadAllCountries(final Organization organization) {
		return (List<Country>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						return session.getNamedQuery(
								"LoadAllCountriesByOrgId").setParameter(
								"orgId", organization.getId()).list();
					}
				});
	}

	public List<Country> loadAllCountries() {
		return this.loadAll(Country.class);
	}


	public void storeCountry(Country country) {
		this.store(country);
		
	}

	public List<Country> loadAllCountry(Long[] countryIds) {
		return this.loadAll(Country.class,countryIds);
	}

	public Country loadCountry(Long countryId) {
		return this.load(Country.class, countryId);
	}



}
