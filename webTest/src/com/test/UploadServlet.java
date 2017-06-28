package com.test;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 手动处理文件上传
 * Created by cenyu on 16-12-20.
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /*
        request.getQueryString();//获取GET：username=Tom&pwd=888
        request.getInputStream();//获取POST：inputStream
        request.getParameter("");//使用这个可以不区分get和post
     */
        //获取表单(POST)数据
        ServletInputStream in = request.getInputStream();//此方法得到所有的提交信息，不仅仅只有内容
        //转换流
        InputStreamReader inReaser = new InputStreamReader(in);
        //缓冲流
        BufferedReader reader = new BufferedReader(inReaser);
        String str = null;
        while ((str=reader.readLine()) != null){
            System.out.println(str);
        }
    }
}
