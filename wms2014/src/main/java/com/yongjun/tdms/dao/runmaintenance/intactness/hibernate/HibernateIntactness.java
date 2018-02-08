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
package com.yongjun.tdms.dao.runmaintenance.intactness.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.intactness.IntactnessDao;
import com.yongjun.tdms.model.runmaintenance.intactness.Intactness;
/**
 * <p>Title: HibernateIntactness
 * <p>Description: 设备鉴定数据库访问实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @see com.yongjun.tdms.dao.runmaintenance.intactness.IntactnessDao
 * @version $Id:  $
 */
public class HibernateIntactness extends BaseHibernateDao implements
		IntactnessDao {

	public Intactness loadIntactness(Long IntactnessId) {
		return this.load(Intactness.class, IntactnessId);
	}

	public List<Intactness> loadAllIntactnesss(Long[] IntactnessIds) {
		return this.loadAll(Intactness.class, IntactnessIds);
	}

	public List<Intactness> loadAllIntactnesss() {
		return this.loadAll(Intactness.class);
	}

	public void storeIntactness(Intactness intactness) {
		this.store(intactness);
	}

	public void deleteIntactness(Intactness intactness) {
		this.delete(intactness);
	}

	public void deleteAllIntactness(Collection<Intactness> intactnesss) {
		this.deleteAll(intactnesss);
	}

}
