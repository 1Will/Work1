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
package com.yongjun.tdms.dao.year.tooling;

import java.util.Collection;
import java.util.List;

import com.yongjun.tdms.model.year.tooling.SparePurchaseDetail;

/**
 * 
 * <p>Title: SparePurchaseDetailDao
 * <p>Description: 备件采购明细数据访问接口类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:$
 */
public interface SparePurchaseDetailDao {
	/**
	 * 根据传入的备件采购明细ID,获取备件采购明细
	 * @param sparePurchaseDetailId 备件采购明细ID
	 * @return SparePurchaseDetail 备件采购明细实体
	 */
	SparePurchaseDetail loadSparePurchaseDetail(Long sparePurchaseDetailId);
	
	/**
	 * 根据传入的备件采购明细ID集合,获取备件采购明细集合
	 * @param sparePurchaseDetailIds 备件采购明细ID集合
	 * @return List 备件采购明细集合
	 */
	List<SparePurchaseDetail> loadAllSparePurchaseDetails(Long [] sparePurchaseDetailIds);
	
	/**
	 * 获取备件采购明细集合
	 * @return List 备件采购明细集合
	 */
	List<SparePurchaseDetail> loadAllSparePurchaseDetails();
	
	/**
	 * 保存传入的备件采购明细
	 * @param sparePurchaseDetail 备件采购明细实体
	 */
	void storeSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail);
	
	/**
	 * 删除传入的备件采购明细
	 * @param sparePurchaseDetail  备件采购明细实体
	 */
	void deleteSparePurchaseDetail(SparePurchaseDetail sparePurchaseDetail);
	
	/**
	 * 删除传入的集合备件采购明细
	 * @param sparePurchaseDetails 集合备件采购明细
	 */
	void deleteAllSparePurchaseDetail(Collection<SparePurchaseDetail> sparePurchaseDetails);
}
