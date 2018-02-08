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
package com.yongjun.tdms.presentation.webwork.action.remote.support;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.service.Manager;
import com.yongjun.tdms.presentation.webwork.action.remote.RemoteCustomSelector;

/**
 * @author qs
 * @version $Id: DefaultRemoteCustomSelector.java 7058 2007-09-08 12:35:26Z qsun $
 */
public class DefaultRemoteCustomSelector implements RemoteCustomSelector{
    protected final Log logger = LogFactory.getLog(getClass());
    protected final Manager manager;
    private String methodName ;
    
    public DefaultRemoteCustomSelector(Manager manager) {
        this.manager = manager;
    }

	public String remoteInvoke(String para1, String para2, String para3, String para4){
		Object[] parameters = parameterFliter(para1,para2,para3,para4);
        Object result = null;
		try {
			result = MethodUtils.invokeMethod(manager, methodName, parameters);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        if(logger.isDebugEnabled()){
            logger.debug("Method invoke returned result :"+ result);
        }
        return result == null ? "" : (String)result;
	}
	
    private Object[] parameterFliter(String para1, String para2, String para3, String para4) {
		String[] parameters = new String[]{para1,para2,para3,para4};
    	List<String> list = new ArrayList<String>();
		for(int i=0;i<4;i++){
			if(!(StringUtils.isEmpty(parameters[i]) || parameters[i].equals("undefined"))){
				list.add(parameters[i]);
			}
		}
		return list.toArray();
	}

	/* properties */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}

