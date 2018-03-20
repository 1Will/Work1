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
package com.yongjun.tdms.dao.base.lubricationOil.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.lubricationOil.LubricationOilDao;
import com.yongjun.tdms.model.base.lubricationOil.LubricationOil;

/**
 * <p>Title: HibernateLubricationOil
 * <p>Description: 润滑油数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: $
 * @see com.yongjun.tdms.dao.base.lubricationOil.LubricationOilDao
 */
public class HibernateLubricationOil extends BaseHibernateDao implements
		LubricationOilDao {

	public LubricationOil loadLubricationOil(Long lubricationOilId) {
		return this.load(LubricationOil.class, lubricationOilId);
	}

	public List<LubricationOil> loadAllLubricationOils(Long[] lubricationOilId) {
		return this.loadAll(LubricationOil.class, lubricationOilId);
	}

	public List<LubricationOil> loadAllLubricationOils() {
		return this.loadAll(LubricationOil.class);
	}

	public void storeLubricationOil(LubricationOil lubricationOil) {
		this.store(lubricationOil);
	}

	public void deleteLubricationOil(LubricationOil lubricationOil) {
		this.delete(lubricationOil);
	}

	public void deleteAllLubricationOils(
			Collection<LubricationOil> lubricationOils) {
		this.deleteAll(lubricationOils);
	}

}