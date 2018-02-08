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
package com.yongjun.tdms.dao.base.financeType.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.financeType.FinanceTypeDao;
import com.yongjun.tdms.model.base.financeType.FinanceType;

/**
 * <p>Title: HibernateFinanceType
 * <p>Description: 财务分类数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author qs@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.dao.base.financeType.FinanceTypeDao
 */
public class HibernateFinanceType extends BaseHibernateDao implements
		FinanceTypeDao {

	public FinanceType loadFinanceType(Long financeTypeId) {
		return this.load(FinanceType.class, financeTypeId);
	}

	public List<FinanceType> loadAllFinanceTypes(Long[] financeTypeIds) {
		return this.loadAll(FinanceType.class,financeTypeIds);
	}

	public List<FinanceType> loadAllFinanceTypes() {
		return this.loadAll(FinanceType.class);
	}

	public void storeFinanceType(FinanceType financeType) {
		this.store(financeType);
	}

	public void deleteFinanceType(FinanceType financeType) {
		this.delete(financeType);
	}

	public void deleteAllFinanceTypes(Collection<FinanceType> financeTypes) {
		this.deleteAll(financeTypes);
	}

}
