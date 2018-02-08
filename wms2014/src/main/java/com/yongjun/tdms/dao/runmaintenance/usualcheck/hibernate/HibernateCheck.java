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
package com.yongjun.tdms.dao.runmaintenance.usualcheck.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.usualcheck.CheckDao;
import com.yongjun.tdms.model.runmaintenance.usualcheck.Check;
/**
 * <p>Title: HibernateCheck
 * <p>Description: 日常检查数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.runmaintenance.usualcheck.CheckDao
 * @version $Id:  $
 */
public class HibernateCheck extends BaseHibernateDao implements CheckDao {

	public Check loadCheck(Long checkId) {
		return this.load(Check.class, checkId);
	}

	public List<Check> loadAllChecks(Long[] checkIds) {
		return this.loadAll(Check.class, checkIds);
	}

	public List<Check> loadAllChecks() {
		return this.loadAll(Check.class);
	}

	public void storeCheck(Check check) {
		this.store(check);
	}

	public void deleteCheck(Check check) {
		this.delete(check);
	}

	public void deleteAllChecks(Collection<Check> checks) {
		this.deleteAll(checks);
	}

}
