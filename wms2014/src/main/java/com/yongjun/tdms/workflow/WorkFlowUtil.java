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
package com.yongjun.tdms.workflow;

import java.util.StringTokenizer;


import com.yongjun.tdms.model.BaseInfoEntity;

/**
 * @author qs
 * @version $Id: WorkFlowUtil.java 8064 2007-10-26 08:12:04Z qsun $
 */
public abstract class WorkFlowUtil {
	public static String getClzID(BaseInfoEntity clz) {
		String clzID = clz.getClass().getName();
		StringTokenizer st = new StringTokenizer(clzID, "$$");
		while(st.hasMoreTokens()) {
			clzID = st.nextToken();
			break;
		}
		return clzID;
	}

}
