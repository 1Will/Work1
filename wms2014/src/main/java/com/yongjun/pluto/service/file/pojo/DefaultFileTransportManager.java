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
package com.yongjun.pluto.service.file.pojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.util.GenerateUUID;

/**
 * @author qs
 * @version $Id: DefaultFileTransportManager.java 9835 2007-12-27 07:46:24Z qsun $
 */
public class DefaultFileTransportManager extends BaseManager implements FileTransportManager {
	protected  Properties systemParameterConfiguration;
	
	public Properties getSystemParameterConfiguration() {
		return this.systemParameterConfiguration;
	}
	
	public void setSystemParameterConfiguration(Properties systemParameterConfiguration) {
		this.systemParameterConfiguration = systemParameterConfiguration;
	}
	
	public void download(HttpServletRequest request,
			HttpServletResponse response, String fileName, String position) {
		response.setContentType("unknown");
		String name = "";
		
		try {
			name = new String(fileName.getBytes("gb2312"), "iso8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		response.setHeader("Content-Disposition", "attachment; filename="
				+ name);

		try {
			java.io.OutputStream os = response.getOutputStream();
			File f = new File(getFileName(request, position));
			java.io.FileInputStream fis = new java.io.FileInputStream(f);

			byte[] b = new byte[1024 * 1024];
			int i = 0;

			while ((i = fis.read(b)) > 0) {
				os.write(b, 0, i);
			}

			fis.close();
			os.flush();
			os.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String upload(HttpServletRequest request, File file, String sourceFileName) throws Exception{
		String uuid = GenerateUUID.getInstance().getUUID();
		logger.debug("[name] : " + file.getName());
		logger.debug("[fileUUID] : " + uuid);
		logger.debug("[origFileName] : " + request.getParameter(sourceFileName));
		String location[] = createFileName(request, uuid);
		
		final String realPath = location[0];
		//final String relativePath = location[1];
		System.out.println("location[0]=="+location[0]);
		
	    try
	    {
	    	FileOutputStream fos = new FileOutputStream(realPath);
	    	InputStream is = new FileInputStream(file);

	        byte[] b = new byte[1024*1024];
	        int    i = 0;

	        while ( (i = is.read(b)) > 0 ) 
	        {
	            fos.write(b, 0, i);
	        }

	        fos.close();
	        is.close();
	    }
	    catch ( Exception e )
	    {
	    	e.printStackTrace();
	    	throw e;
	    }
	    
		return uuid;
	}
	
	private String getSysDir() {
		String sysDir = systemParameterConfiguration.getProperty("file_upload_dir");
		if (StringUtils.isEmpty(sysDir)) {
			logger.fatal("system's [file_upload_dir] not configurate in systemParameterConfiguration.properties!");
			logger.fatal("using default: c:\\");
			sysDir = "c:\\";
		}
		return sysDir;
	}
	
	@SuppressWarnings("deprecation")
	private String getFileName(HttpServletRequest request, String position) {
		String f = getSysDir() + position;
        return f;
	}
	
	@SuppressWarnings("deprecation")
	private String[] createFileName(HttpServletRequest request, String position) {
		File file = new File(getSysDir());
		
		if(!file.exists()) {      
			file.mkdir(); 
		}
		
		String[] paths = new String[2];
		paths[0] = file.getPath()  + File.separator + position;
		//paths[1] = "upload" +File.separator + position;
		return paths;
	}

	@SuppressWarnings("deprecation")
	public void delete(HttpServletRequest request, String position){
		File file = new File(getSysDir() + position);
		if (file.isFile()) {
			boolean success = file.delete();
			if (!success) {
				logger.debug("delete file " + position + " failed!");
			}
		} 
	}
	
	public boolean isFileValid(File file) {
		// TODO Auto-generated method stub
		return false;
	}
}
