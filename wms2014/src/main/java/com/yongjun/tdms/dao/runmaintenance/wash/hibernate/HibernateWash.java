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
package com.yongjun.tdms.dao.runmaintenance.wash.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.wash.WashDao;
import com.yongjun.tdms.model.runmaintenance.wash.Wash;

/**
 * <p>Title: HibernateWash
 * <p>Description: 清洗数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.dao.runmaintenance.wash.WashDao
 */
public class HibernateWash extends BaseHibernateDao implements WashDao {

	public Wash loadWash(Long washId) {
		return this.load(Wash.class, washId);
	}

	public List<Wash> loadAllWashs(Long[] washIds) {
		return this.loadAll(Wash.class, washIds);
	}

	public List<Wash> loadAllWashs() {
		return this.loadAll(Wash.class);
	}

	public void storeWash(Wash wash) {
		this.store(wash);
	}

	public void deleteWash(Wash wash) {
		this.delete(wash);
	}

	public void deleteAllWashs(Collection<Wash> washs) {
		this.deleteAll(washs);
	}

}
