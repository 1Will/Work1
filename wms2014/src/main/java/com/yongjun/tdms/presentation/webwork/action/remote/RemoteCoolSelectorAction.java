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

package com.yongjun.tdms.presentation.webwork.action.remote;

import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.BeansException;
import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.webwork.action.remote.RemoteScriptingAction;
import com.yongjun.pluto.webwork.action.remote.support.Option;
import com.yongjun.pluto.webwork.action.remote.support.RemoteSelector;

/**
 * @author qs
 * @version $Id: RemoteCoolSelectorAction.java 7058 2007-09-08 12:35:26Z qsun $
 */
public class RemoteCoolSelectorAction extends RemoteScriptingAction
        implements BeanFactoryAware {
	private static final long serialVersionUID = -6779652551285654036L;
	private BeanFactory beanFactory;
    private String selectorName;
    private String code;
    private String idName;
    private String[] displayNames;
    private String codeName;

    protected String[] invokeMethod() {
        RemoteSelector selector = (RemoteSelector) beanFactory.getBean(selectorName);
        Option[] options = selector.getByCode(code, idName, displayNames,codeName);
        String[] result = new String[options.length * Option.length()];
        for (int i = 0; i < options.length; i++) {
            Option option = options[i];
            System.arraycopy(option.toStrings(), 0, result, Option.length() * i, Option.length());
        }
        return result;
    }

    /* BeanFactoryAware Methods */
    public void setBeanFactory(BeanFactory beanFactory)
            throws BeansException {
        this.beanFactory = beanFactory;
    }

    /* properties */
    public void setP0(String selectorName) {
        this.selectorName = selectorName;
    }

    public void setP1(String code) {
        this.code = code;
    }

    public void setP2(String idName) {
        this.idName = idName;
    }

    public void setP3(String displayNames) {
        this.displayNames = StringUtils.split(displayNames, '|');
    }

    public void setP4(String codeName){
        this.codeName = codeName;
    }
}

