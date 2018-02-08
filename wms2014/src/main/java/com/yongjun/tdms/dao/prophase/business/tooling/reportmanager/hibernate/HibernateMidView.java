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
package com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate;

 
import java.util.List;
import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.MidViewDao;
import com.yongjun.tdms.model.prophase.business.tooling.reportmanager.MidView;
 

/**
 * <p>Title: com.yongjun.tdms.dao.prophase.business.tooling.reportmanager.hibernate.HibernateMidView.java</p>
 * <p>Description: the HibernateMidView class</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * <p>@author zzb @yj-technology.com</p>
 * <p>@version $ Id:HibernateMidView.java 2011-3-14 zzb $</p>
 */
public class HibernateMidView extends BaseHibernateDao implements MidViewDao {
  
	public List<MidView> loadByKey(String key, Object value)throws Exception {
		return super.loadByKey(MidView.class, key, value);
	}

 
	public List<MidView> loadByKeyArry(String[] keyArrys, Object[] values) throws Exception{
		return super.loadByKeyArray(MidView.class, keyArrys, values);
	}
	
	

}

