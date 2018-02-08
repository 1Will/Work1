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

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.service.Manager;
import com.yongjun.pluto.webwork.action.remote.support.Option;
import com.yongjun.pluto.webwork.action.remote.support.RemoteScriptingException;
import com.yongjun.pluto.webwork.action.remote.support.RemoteSelector;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

/**
 * @author qs
 * @version $Id: DefaultRemoteCoolSelector.java 7058 2007-09-08 12:35:26Z qsun $
 */
public class DefaultRemoteCoolSelector
        implements RemoteSelector {
    private final Manager manager;
    private String methodName = "getByCode";

    public DefaultRemoteCoolSelector(Manager manager) {
        this.manager = manager;
    }

    public Option[] getByCode(String code, String idName, String[] displayNames, String codeName) {
        try {
            Object result = MethodUtils.invokeMethod(manager, methodName, new Object[]{code});
            if (result == null) {
                return new Option[0];
            }

            if (result.getClass().isArray()) {
                Option[] options = new Option[Array.getLength(result)];
                for (int i = 0; i < options.length; i++) {
                    options[i] = createOption(Array.get(result, i), idName, displayNames, codeName);
                }
                return options;
            }

            return new Option[]{createOption(result, idName, displayNames, codeName)};
        } catch (IllegalAccessException e) {
            throw new RemoteScriptingException(e);
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else {
                throw new RemoteScriptingException(cause);
            }
        } catch (NoSuchMethodException e) {
            throw new RemoteScriptingException(e);
        }
    }

    private Option createOption(Object model, String idName, String[] displayNames, String codeName)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Long id = (Long) PropertyUtils.getProperty(model, idName);
        StringBuffer display = new StringBuffer();
        for (int i = 0; i < displayNames.length; i++) {
            String displayName = displayNames[i];

            if (displayName.indexOf(".") > 0) {
                String[] disAr = StringUtils.split(displayName, ".");
                Object parentModel = model;
                Object currAtt = null;
                for (String subAtt : disAr) {
                    currAtt = PropertyUtils.getProperty(parentModel, subAtt);
                    if (currAtt == null) {
                        break;
                    } else {
                        parentModel = currAtt;
                    }
                }
                if (currAtt == null) {
                    display.append("|");
                } else {
                    display.append(String.valueOf(currAtt) + "|");
                }

            } else {
                if (PropertyUtils.getProperty(model, displayName) != null) {
                    display.append(String.valueOf(PropertyUtils.getProperty(model, displayName)) + "|");
                } else {
                    display.append("|");
                }
            }
        }
        String code = (String) PropertyUtils.getProperty(model, codeName);
        return new Option(id, StringUtils.substringBeforeLast(display.toString(), "|"), code);
    }

    /* properties */
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}




