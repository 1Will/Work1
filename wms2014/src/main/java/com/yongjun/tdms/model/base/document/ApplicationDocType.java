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
package com.yongjun.tdms.model.base.document;

/**
 * <p>Title: ApplicationDocType
 * <p>Description: 文档上传得枚举类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id: ApplicationDocType.java 27787 2010-10-14 02:47:51Z zbzhang $
 */
public enum ApplicationDocType {
	/* 制度文档 */
	SYSTEM_DOC,
	/* 工装变更文档 */
	TOOLING_CHANGE_DOC,
	/* 采购文档 */
	PURCHASE_DOC,
	/* 验收单文档 */
	ACCEPT_DOC,
	/* 备件单文档 */
	SPARE_DOC,
	/* 预防性维修文档 */
	PREREPAIR_DOC,
	/* 大项修文档 */
	YEAR_REPAIR_DOC,
	/* 故障维修文档 */
	FAULT_REPAIR_DOC,
	/* 帮助手册文档 */
	HELP_MANUAL_DOC
	
	
	
}
