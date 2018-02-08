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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

import com.yongjun.pluto.webwork.action.remote.RemoteScriptingAction;


/**
 * @author qs
 * @version $Id: RemoteCustomSelectorAction.java 7058 2007-09-08 12:35:26Z qsun $
 */
public class RemoteCustomSelectorAction extends RemoteScriptingAction implements BeanFactoryAware {
	private static final long serialVersionUID = -4013435586309966113L;
	protected String callback;
    protected String message;
	private BeanFactory beanFactory;
	private String customSelectorName;
	private String para1;
	private String para2;
	private String para3;
	private String para4;
    
	public String remoteInvoke() throws Exception{
    	if (callback == null){
    	    throw new IllegalArgumentException("Callback function can't be null.");
        }
		message = invokeMethod0();
		return "success";
		
	}

	private String invokeMethod0() {
		RemoteCustomSelector selector = (RemoteCustomSelector) this.beanFactory.getBean(customSelectorName);
		return selector.remoteInvoke(para1,para2,para3,para4);
	}

	public void setC(String callback) {
    	this.callback = callback;
    }
        
    public String getCallback() {
    	return callback;
    }
    
    public String getMessage() {
    	return message;
    }
    /* BeanFactoryAware Methods */
    public void setBeanFactory(BeanFactory beanFactory)
            throws BeansException {
        this.beanFactory = beanFactory;
    }

    /* properties */
    public void setP0(String para0) {
        this.customSelectorName = para0;
    }

    public void setP1(String para1) {
    	this.para1 = para1;
    }

    public void setP2(String para2) {
    	this.para2 = para2;
    }

    public void setP3(String para3) {
    	this.para3 = para3;
    }

    public void setP4(String para4){
    	this.para4 = para4;
    }

	@Override
	protected String[] invokeMethod() {
		return null;
	}

}

