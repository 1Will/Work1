/*
 * Copyright (c) 2001-2005 YongJun Digital Information Technology Co.,Ltd. All
 * Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Digital Information Technology Co.,Ltd. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with
 * YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */

package com.yongjun.tdms.presentation.webwork.tag;

import com.opensymphony.webwork.views.jsp.ui.HiddenTag;
import com.opensymphony.xwork.util.OgnlValueStack;

public class OrgIdHiddenTag
        extends HiddenTag {
	private static final long serialVersionUID = -6994917502749890443L;
	public static final String TEMPLATE = "datepicker";
//    protected String dateFormat;
//    protected String showsTime;
//
//    public void setShowsTime(String showsTime) {
//        this.showsTime = showsTime;
//    }
//
//    public void setDateFormat(String dateFormat) {
//        this.dateFormat = dateFormat;
//    }

    public void evaluateExtraParams(OgnlValueStack ognlValueStack) {
        super.evaluateExtraParams(ognlValueStack);

        if (this.id == null) {
            addParameter("id", findString(this.nameAttr));
        }

//        if (dateFormat != null) {
//            addParameter("dateformat", findString(dateFormat));
//        }
//
//        if (showsTime != null) {
//            addParameter("showsTime", findString(showsTime));
//        }
//
//        if (this.pageContext.getAttribute("datePickerMoreThanOnce") == null) {
//            this.pageContext.setAttribute("datePickerMoreThanOnce", true);
//            addParameter("isFirst", Boolean.TRUE);
//        } else {
//            addParameter("isFirst", Boolean.FALSE);
//        }
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }
}

