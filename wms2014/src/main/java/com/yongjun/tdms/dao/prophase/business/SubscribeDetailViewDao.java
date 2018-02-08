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
package com.yongjun.tdms.dao.prophase.business;

import java.util.List;

import com.yongjun.tdms.model.prophase.business.SubscribeDetailView;

/**
 * <p>Title:SubscribeDetailView
 * <p>Description:</P>
 * <p>Copyright:Copyright (c) 2008 yj-technology</P>
 * <p>Company:www.yj-technology.com</P>
 * @author yli@yj-technology.com
 * @version $Id: SubscribeDetailView.java 2008-12-15 13:38:59 yli$
 */
public interface SubscribeDetailViewDao {
	/**
	 * 根据采购单ID来查询采购单明细中的记录并打印成报表
	 * @param id
	 * @return
	 */
	List<SubscribeDetailView> loadSubscribeDetailView(List id,String flag);
	
	/**
	 * 根据申购单ID集合来查询申购单明细的记录，并打印报表
	 * @param BillIds
	 * @return
	 */
	List <SubscribeDetailView> loadSubscribeDetailViewByBillIds(String BillIds);
}
