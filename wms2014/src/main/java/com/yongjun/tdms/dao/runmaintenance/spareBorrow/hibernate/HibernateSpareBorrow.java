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
import com.yongjun.tdms.dao.runmaintenance.spareBorrow.SpareBorrowDao;
import com.yongjun.tdms.model.runmaintenance.spareBorrow.SpareBorrow;

/**
 * <p>Title: HibernateSpareBorrow
 * <p>Description: 备件领用单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author xschen@yj-technology.com
 * @version $Id:  $
 */
public class HibernateSpareBorrow extends BaseHibernateDao implements SpareBorrowDao{

	public SpareBorrow loadSpareBorrow(Long id) {
		return this.load(SpareBorrow.class,id);
	}
	public void storeSpareBorrow(SpareBorrow spareBorrow) {
		this.store(spareBorrow);
	}
	public List<SpareBorrow> loadAllSpareBorrows(Long[] ids) {
		return this.loadAll(SpareBorrow.class,ids);
	}
	public void deleteSpareBorrow(SpareBorrow sparePart) {
		this.delete(sparePart);
	}
	public void deleteAllSpareBorrows(List<SpareBorrow> spareBorrows) {
		this.deleteAll(spareBorrows);
	}

}
