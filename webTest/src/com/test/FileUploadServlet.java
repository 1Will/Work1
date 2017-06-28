package com.test;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.attribute.FileTime;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传组件完整使用
 * 引入commons-fileupload-1.2.1.jar
 * 引入依赖包commons-io-1.4.jar
 * Created by cenyu on 16-12-20.
 */
@WebServlet("/load")
public class FileUploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建文件上传工厂类
        DiskFileItemFactory fac = new DiskFileItemFactory();
        //2.创建文件上传核心类对象
        ServletFileUpload upload = new ServletFileUpload(fac);
        //【一、设置单个文件最大30M】
        upload.setFileSizeMax(30*1024*1024);//30M
        //【二、设置总文件大小：50M】
        upload.setSizeMax(50*1024*1024); //50M
        String aname ="";//测试的，可删
        //判断，当前表单是否为文件上传表单
        if (upload.isMultipartContent(request)){

            try {
                //3.把请求数据转换为FileItem对象的集合
                List<FileItem> list = upload.parseRequest(request);
                //遍历，得到每一个上传项
                for (FileItem item : list){
                    //判断：是普通表单项，还是文件上传表单项
                    if (item.isFormField()){
                        //普通表单x
                        String fieldName = item.getFieldName();//获取元素名称
                        String value = item.getString("UTF-8"); //获取元素值
                        System.out.println(fieldName+" : "+value);

                    }else {
                        //文件上传表单

                        String name = item.getName(); //上传的文件名称
                        /**
                         * 【四、文件重名】
                         * 对于不同的用户的test.txt文件，不希望覆盖，
                         * 后台处理：给用户添加一个唯一标记！
                         */
                        //a.随机生成一个唯一标记
                        String id = UUID.randomUUID().toString();
                        //与文件名拼接
                        name = id +"_"+ name;

                        //【三、上传到指定目录：获取上传目录路径】
                        //String realPath = getServletContext().getRealPath("/upload");
                        String realPath = "D:\\_a_my\\upload";
                        new File(realPath).mkdirs();
                        //创建文件对象
                        File file = new File(realPath, name);
                        item.write(file);
                        System.out.println(file.getPath());
                        aname= file.getName();
                        System.out.println(file.getName());
                        item.delete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("不处理！");
        }
        response.sendRedirect("downLoad.jsp?filename="+URLEncoder.encode(aname, "UTF-8"));
    }
}
