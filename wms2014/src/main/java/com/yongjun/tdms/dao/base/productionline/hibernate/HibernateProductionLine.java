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
package com.yongjun.tdms.dao.base.productionline.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.pluto.model.security.Organization;
import com.yongjun.pluto.model.security.ProductionLine;
import com.yongjun.tdms.dao.base.productionline.ProductionLineDao;

/**
 * @author qs
 * @version $Id: HibernateProductionLine.java 10818 2008-01-31 02:25:29Z qsun $
 */
public class HibernateProductionLine extends BaseHibernateDao implements
		ProductionLineDao {

	@SuppressWarnings("unchecked")
	public List<ProductionLine> loadAllProductionLines(
			final Organization organization) {
		return (List<ProductionLine>) this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						return session.getNamedQuery(
								"LoadAllProductLinesByOrgId").setParameter(
								"orgId", organization.getId()).list();
					}
				});
	}

	public ProductionLine loadProductionLine(Long id) {
		return this.load(ProductionLine.class, id);
	}
	
	public void StoreProductionLine(ProductionLine productionLine){
		this.store(productionLine);
	}
	
	public List<ProductionLine> loadAllProductionLines(Long[] ids){
		return this.loadAll(ProductionLine.class,ids);
	}
}
