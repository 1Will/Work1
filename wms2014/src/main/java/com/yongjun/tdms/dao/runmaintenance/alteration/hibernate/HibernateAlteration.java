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
package com.yongjun.tdms.dao.runmaintenance.alteration.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.alteration.AlterationDao;
import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;

/**
 * <p>
 * Title: HibernateAlteration
 * <p>
 * Description: 报废单数据访问实现类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author wzou@yj-technology.com
 * @version $Id: HibernateAlteration.java 9044 2007-12-05 13:14:52Z mwei $
 */
public class HibernateAlteration extends BaseHibernateDao implements
		AlterationDao {
	public void storeAlteration(Alteration alteration){
		this.store(alteration);
	}

	public Alteration loadAlteration(Long id){
		return this.load(Alteration.class,id);
	}

	public void deleteAllAlteration(List<Alteration> list){
		this.deleteAll(list);
	}

	public List<Alteration> loadAllAlteration(Long[] AlterationIds){
		return this.loadAll(Alteration.class,AlterationIds);
	}

	public List<Alteration> loadAllAlteration(){
		return this.loadAll(Alteration.class);
	}
}
