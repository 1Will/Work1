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
package com.yongjun.tdms.dao.runmaintenance.spareBorrow.hibernate;

import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDetailDao;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrowDetail;

/**
 * <p>Title: HibernateSpareBorrowDetail
 * <p>Description: 备件领用单明细数据实现类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id: HibernateSpareBorrowDetail 11237 2008-03-15 14:21:42Z xschen $
 */
public class HibernateSpareBorrowDetail extends BaseHibernateDao implements SpareBorrowDetailDao{

	public SpareBorrowDetail loadSpareBorrowDetail(Long id) {
		
		return this.load(SpareBorrowDetail.class,id);
	}
	public void storeSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail) {
		this.store(spareBorrowDetail);
		
	}
	public List<SpareBorrowDetail> loadAllSpareBorrowDetails(Long[] ids) {
		
		return this.loadAll(SpareBorrowDetail.class,ids);
	}
	public void deleteSpareBorrowDetail(SpareBorrowDetail spareBorrowDetail) {
		this.delete(spareBorrowDetail);
		
	}
	public void deleteAllSpareBorrowDetails(List<SpareBorrowDetail> spareBorrowDetails) {
		this.deleteAll(spareBorrowDetails);
	}

}
