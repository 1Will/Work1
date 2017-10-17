package com.github.wp.system.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录Controller
 * @author wangping
 * @version 1.0
 * @since 2014年2月15日, 下午5:53:43
 */
@Controller
public class LoginController {

    /**
     * 用户登录请求
     * @param req
     * @param model
     * @return
     * @author wangping
     */
    @RequestMapping(value = "/login")
    public String showLoginForm(HttpServletRequest req, HttpServletResponse response,Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误";
        }else if(DisabledAccountException.class.getName().equals(exceptionClassName)){
        	error = "账号被禁用";
        }else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
        	error = "登录失败次数太多";
        }else if(LockedAccountException.class.getName().equals(exceptionClassName)){
        	error = "账号已被锁定";
        }else if(ExpiredCredentialsException.class.getName().equals(exceptionClassName)){
        	error = "账号已过期";
        }else if(UnauthorizedException.class.getName().equals(exceptionClassName)){
        	error = "账号没有权限";
        }else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        if(req.getParameter("forceLogout") != null) {
            model.addAttribute("error", "您已经被管理员强制退出，请重新登录");
        }
			response.setContentType("text/html; charset=UTF-8"); //转码
			PrintWriter out = null;
			try {
				out = response.getWriter();
			} catch (IOException e) {
				e.printStackTrace();
			}
			    out.flush();
			    out.println("<script>");
			    out.println("window.top.location.href='/login1';");
			    out.println("</script>");
        return null;
    }

    @RequestMapping(value = "/login1")
    public String login1() {
    	return "login";
    }

    @RequestMapping(value = "/index1")
    public String index1() {
    	return "index";
    }
}
