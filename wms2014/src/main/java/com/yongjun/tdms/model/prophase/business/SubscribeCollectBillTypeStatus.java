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
package com.yongjun.tdms.model.prophase.business;

/**
 * <p>Title: SubscribeCollectBillTypeStatus
 * <p>Description: 申购汇总单状态枚举</p>
 * <p>Copyright: Copyright (c) 2010 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: SubscribeCollectBillTypeStatus.java 28658 2010-11-11 03:35:01Z zbzhang $
 */
public enum SubscribeCollectBillTypeStatus {
	/**
	 * 新建
	 */
	NEW,
	/**
	 * 采购中
	 */
	PURCHASING,
	/**
	 * 已采购
	 */
	PURCHASEED,
	/**
	 * 入库中
	 */
	INSPECTING,
	/**
	 * 已入库
	 */
	INSPECTED

}
