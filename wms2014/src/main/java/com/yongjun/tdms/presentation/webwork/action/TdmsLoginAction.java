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
package com.yongjun.tdms.presentation.webwork.action;

import java.util.Map;

import com.yongjun.pluto.webwork.action.UserLoginAction;

/**
 * 
 * @author <a href="mailto:268889u.xp@gmail.com">qsun</a>
 * @version $Id: $
 */
public class TdmsLoginAction extends UserLoginAction {
	private static final long serialVersionUID = -2286308339990443246L;
	private Map wmsSession;
	
    public String execute() throws Exception {
		String result = super.execute();
		String targetUrl = (String) wmsSession.get("ACEGI_SECURITY_TARGET_URL");
		if (targetUrl != null && !targetUrl.contains("frameset.html")) {
			return "logout";
		}
		return result;
	}
    
    public void setSession(Map session) {
    	super.setSession(session);
        this.wmsSession = session;
    }
}

