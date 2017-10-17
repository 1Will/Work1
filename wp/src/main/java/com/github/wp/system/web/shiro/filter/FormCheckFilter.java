package com.github.wp.system.web.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;


/**
 * <p>User: dupeng
 * <p>Date: 16-09-01
 * <p>Version: 1.0
 */
public class FormCheckFilter extends FormAuthenticationFilter {


    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject,
            ServletRequest request, ServletResponse response) throws Exception {
    	
    WebUtils.issueRedirect(request, response, getSuccessUrl());

	return false;
	}
}
